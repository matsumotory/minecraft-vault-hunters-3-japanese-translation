package io.github.matsumotory.vhjapanese;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 同梱の訳文データ (assets/vhjapanese/vhdata/) をインスタンスへ適用する。
 *
 * 方針 (CLAUDE.md第3節): 壊さないことが最優先。configの差し込みは元ファイルを
 * バックアップしてから行い、JSONとして読めたものだけを書き、失敗した層は飛ばして
 * ゲームは通常どおり起動させる。翻訳が無い文字列は英語のまま表示されるのが正しい。
 * 全処理は冪等で、二回目以降の起動では差分が無ければ何も書かない。
 */
final class TranslationInstaller {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String DATA_ROOT = "/assets/vhjapanese/vhdata/";
    private static final String VP_MODULE = "the_vault_vp";

    private TranslationInstaller() {}

    static void runAll() {
        JsonObject index = readResourceJson("index.json").getAsJsonObject();
        Path gameDir = FMLPaths.GAMEDIR.get();
        Path configDir = FMLPaths.CONFIGDIR.get();
        installPatchouli(index, gameDir);
        installVaultPatcher(configDir);
        mergeConfigs(index, configDir);
    }

    // ---- ガイドブック (Patchouli)。外部ブックのためassetsでは届かず、実ファイルとして置く ----
    private static void installPatchouli(JsonObject index, Path gameDir) {
        Path bookRoot = gameDir.resolve("patchouli_books").resolve("the_vault_main_guide").resolve("ja_jp");
        int written = 0;
        int kept = 0;
        int failed = 0;
        for (JsonElement el : index.getAsJsonArray("patchouli")) {
            String rel = el.getAsString();
            try {
                byte[] data = readResourceBytes("patchouli/" + rel);
                Path dest = bookRoot.resolve(rel);
                if (Files.isRegularFile(dest) && Arrays.equals(Files.readAllBytes(dest), data)) {
                    kept++;
                    continue;
                }
                Files.createDirectories(dest.getParent());
                Files.write(dest, data);
                written++;
            } catch (Exception e) {
                failed++;
                LOGGER.warn("[vhjapanese] ガイドブックの書き込みに失敗: {} ({})", rel, e.toString());
            }
        }
        LOGGER.info("[vhjapanese] ガイドブック: 書き込み{} 既適用{} 失敗{}", written, kept, failed);
    }

    // ---- ハードコード層 (VaultPatcher設定の供給) ----
    private static void installVaultPatcher(Path configDir) {
        try {
            Path dir = configDir.resolve("vaultpatcher_asm");
            Files.createDirectories(dir);
            boolean changed = false;

            byte[] module = readResourceBytes("vaultpatcher/" + VP_MODULE + ".json");
            Path moduleDest = dir.resolve(VP_MODULE + ".json");
            if (!Files.isRegularFile(moduleDest) || !Arrays.equals(Files.readAllBytes(moduleDest), module)) {
                Files.write(moduleDest, module);
                changed = true;
            }

            Path cfg = dir.resolve("config.json");
            if (!Files.isRegularFile(cfg)) {
                Files.write(cfg, readResourceBytes("vaultpatcher/config.default.json"));
                changed = true;
            } else {
                JsonObject obj = JsonParser
                        .parseString(new String(Files.readAllBytes(cfg), StandardCharsets.UTF_8))
                        .getAsJsonObject();
                JsonArray mods = obj.getAsJsonArray("mods");
                if (mods == null) {
                    mods = new JsonArray();
                    obj.add("mods", mods);
                }
                boolean present = false;
                for (JsonElement m : mods) {
                    if (m.isJsonPrimitive() && VP_MODULE.equals(m.getAsString())) {
                        present = true;
                        break;
                    }
                }
                if (!present) {
                    mods.add(VP_MODULE);
                    Files.write(cfg, (AsciiJson.write(obj) + "\n").getBytes(StandardCharsets.UTF_8));
                    changed = true;
                }
            }
            if (changed) {
                LOGGER.info("[vhjapanese] VaultPatcher設定を配置した。置換はVaultPatcherが設定を読む次回起動から有効");
            } else {
                LOGGER.info("[vhjapanese] VaultPatcher設定: 既適用");
            }
        } catch (Exception e) {
            LOGGER.warn("[vhjapanese] VaultPatcher設定の配置に失敗 (ハードコード層は英語のまま): {}", e.toString());
        }
    }

    // ---- config層 (手元の英語configへ訳文を差し込む) ----
    private static void mergeConfigs(JsonObject index, Path configDir) {
        Path vaultConfigRoot = configDir.resolve("the_vault");
        if (!Files.isDirectory(vaultConfigRoot)) {
            LOGGER.warn("[vhjapanese] config/the_vault/が無いためconfig層を飛ばした (Vault Huntersのインスタンスでない可能性)");
            return;
        }
        String stamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        Path backupRoot = configDir.resolve("vhjapanese").resolve("backup").resolve(stamp);
        int mergedFiles = 0;
        int skippedSame = 0;
        int missing = 0;
        int failed = 0;
        int[] stats = new int[2]; // [採用した訳の数, 訳と認めず原文を残した数]
        for (JsonElement el : index.getAsJsonArray("configmap")) {
            String rel = el.getAsString();
            Path target = vaultConfigRoot.resolve(rel);
            try {
                if (!Files.isRegularFile(target)) {
                    missing++;
                    continue;
                }
                JsonObject map = readResourceJson("configmap/" + rel).getAsJsonObject();
                Map<String, String> strings = new LinkedHashMap<>();
                for (Map.Entry<String, JsonElement> e : map.getAsJsonObject("strings").entrySet()) {
                    strings.put(e.getKey(), e.getValue().getAsString());
                }
                String currentText = new String(Files.readAllBytes(target), StandardCharsets.UTF_8);
                JsonElement merged = ConfigMerger.merge(JsonParser.parseString(currentText), strings, "", stats);
                String out = AsciiJson.write(merged) + "\n";
                if (out.equals(currentText)) {
                    skippedSame++;
                    continue;
                }
                Path backup = backupRoot.resolve(rel);
                Files.createDirectories(backup.getParent());
                Files.copy(target, backup, StandardCopyOption.REPLACE_EXISTING);
                Path tmp = target.resolveSibling(target.getFileName() + ".vhja.tmp");
                Files.write(tmp, out.getBytes(StandardCharsets.UTF_8));
                Files.move(tmp, target, StandardCopyOption.REPLACE_EXISTING);
                mergedFiles++;
            } catch (Exception e) {
                failed++;
                LOGGER.warn("[vhjapanese] configの差し込みに失敗 (原文のまま残す): {} ({})", rel, e.toString());
            }
        }
        LOGGER.info("[vhjapanese] config層: 差し込み{}ファイル (採用{}件) / 既適用{} / 対象なし{} / 失敗{}{}",
                mergedFiles, stats[0], skippedSame, missing, failed,
                mergedFiles > 0 ? " / バックアップ: " + backupRoot : "");
    }

    // ---- 同梱リソースの読み出し ----
    private static byte[] readResourceBytes(String rel) {
        try (InputStream in = TranslationInstaller.class.getResourceAsStream(DATA_ROOT + rel)) {
            if (in == null) {
                throw new IllegalStateException("同梱リソースが無い: " + rel);
            }
            return in.readAllBytes();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static JsonElement readResourceJson(String rel) {
        return JsonParser.parseString(new String(readResourceBytes(rel), StandardCharsets.UTF_8));
    }
}
