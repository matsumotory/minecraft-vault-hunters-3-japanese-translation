package io.github.matsumotory.vhjapanese;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * path-map/1形式 (JSONパス→訳文) の対応表を、原文のJSONへ差し込む。
 *
 * 翻訳作業側のビルダーと同じ規則の移植。訳と認めるのは「CJKを含む」または
 * 「原文と異なり英字を含まない」訳だけで、それ以外と文字列以外 (数値、真偽値、
 * null) は必ず原文の値を残す。これがゲームバランスの数値を絶対に変えない担保。
 * パスの記法は a.b[0].c (オブジェクトはドット、配列は添字)。
 */
final class ConfigMerger {
    private static final Pattern CJK = Pattern.compile("[\\u3040-\\u309f\\u30a0-\\u30ff\\u4e00-\\u9fff]");
    private static final Pattern ASCII_LETTER = Pattern.compile("[A-Za-z]");

    private ConfigMerger() {}

    static JsonElement merge(JsonElement original, Map<String, String> strings, String trail, int[] stats) {
        if (original.isJsonObject()) {
            JsonObject out = new JsonObject();
            for (Map.Entry<String, JsonElement> entry : original.getAsJsonObject().entrySet()) {
                String childTrail = trail.isEmpty() ? entry.getKey() : trail + "." + entry.getKey();
                out.add(entry.getKey(), merge(entry.getValue(), strings, childTrail, stats));
            }
            return out;
        }
        if (original.isJsonArray()) {
            JsonArray out = new JsonArray();
            int i = 0;
            for (JsonElement element : original.getAsJsonArray()) {
                out.add(merge(element, strings, trail + "[" + i + "]", stats));
                i++;
            }
            return out;
        }
        if (original.isJsonPrimitive() && original.getAsJsonPrimitive().isString()) {
            String orig = original.getAsString();
            String translated = strings.get(trail);
            if (translated != null) {
                if (CJK.matcher(translated).find()
                        || (!translated.equals(orig) && !ASCII_LETTER.matcher(translated).find())) {
                    stats[0]++;
                    return new JsonPrimitive(translated);
                }
                stats[1]++;
            }
            return original;
        }
        // 数値、真偽値、null。GsonのLazilyParsedNumberが数値の原文表記を保持する
        return original;
    }
}
