package io.github.matsumotory.vhjapanese;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

/**
 * Vault Hunters 3rd Editionの日本語化companion mod。
 *
 * lang層とガイドブックの訳文はassetsとして同梱され本体に自動で読まれる。
 * config層の差し込み、VaultPatcher設定の供給、ガイドブックの実ファイル配置は
 * コンストラクタで行う (mods.tomlのordering=BEFOREによりthe_vaultの構築より先)。
 * どの層が失敗してもゲームの起動は妨げない (壊さないことが最優先)。
 */
@Mod(VhJapaneseMod.MOD_ID)
public class VhJapaneseMod {
    public static final String MOD_ID = "vhjapanese";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VhJapaneseMod() {
        long start = System.nanoTime();
        try {
            TranslationInstaller.runAll();
        } catch (Throwable t) {
            LOGGER.error("[vhjapanese] 翻訳データの適用に失敗した (ゲームは通常どおり起動する)", t);
        }
        LOGGER.info("[vhjapanese] Vault Hunters 3 Japanese Translation 適用処理 {}ms",
                (System.nanoTime() - start) / 1_000_000);
    }
}
