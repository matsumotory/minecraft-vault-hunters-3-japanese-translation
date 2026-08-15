package io.github.matsumotory.vhjapanese;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.Map;
import java.util.Set;

/**
 * JSONをASCIIエスケープ (非ASCIIをすべてユニコードエスケープ) のインデント2で書き出す。
 *
 * the_vaultはconfigをJVM既定文字コードで読むため (日本語WindowsではCP932)、
 * UTF-8生バイトの日本語は化ける。翻訳作業側の配布物と同じ出力規則
 * (Pythonのjson.dumps(ensure_ascii=True, indent=2)相当) に合わせる。
 */
final class AsciiJson {
    private AsciiJson() {}

    static String write(JsonElement element) {
        StringBuilder sb = new StringBuilder(1 << 16);
        writeElement(element, sb, 0);
        return sb.toString();
    }

    private static void writeElement(JsonElement element, StringBuilder sb, int level) {
        if (element == null || element.isJsonNull()) {
            sb.append("null");
            return;
        }
        if (element.isJsonPrimitive()) {
            JsonPrimitive p = element.getAsJsonPrimitive();
            if (p.isBoolean()) {
                sb.append(p.getAsBoolean());
            } else if (p.isNumber()) {
                sb.append(p.getAsNumber().toString());
            } else {
                quote(p.getAsString(), sb);
            }
            return;
        }
        if (element.isJsonObject()) {
            Set<Map.Entry<String, JsonElement>> entries = element.getAsJsonObject().entrySet();
            if (entries.isEmpty()) {
                sb.append("{}");
                return;
            }
            sb.append("{\n");
            boolean first = true;
            for (Map.Entry<String, JsonElement> entry : entries) {
                if (!first) {
                    sb.append(",\n");
                }
                first = false;
                indent(sb, level + 1);
                quote(entry.getKey(), sb);
                sb.append(": ");
                writeElement(entry.getValue(), sb, level + 1);
            }
            sb.append('\n');
            indent(sb, level);
            sb.append('}');
            return;
        }
        JsonArray array = element.getAsJsonArray();
        if (array.size() == 0) {
            sb.append("[]");
            return;
        }
        sb.append("[\n");
        boolean first = true;
        for (JsonElement item : array) {
            if (!first) {
                sb.append(",\n");
            }
            first = false;
            indent(sb, level + 1);
            writeElement(item, sb, level + 1);
        }
        sb.append('\n');
        indent(sb, level);
        sb.append(']');
    }

    private static void indent(StringBuilder sb, int level) {
        sb.append("  ".repeat(level));
    }

    private static void quote(String value, StringBuilder sb) {
        sb.append('"');
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            switch (c) {
                case '"' -> sb.append("\\\"");
                case '\\' -> sb.append("\\\\");
                case '\b' -> sb.append("\\b");
                case '\f' -> sb.append("\\f");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                default -> {
                    if (c < 0x20 || c > 0x7f) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        sb.append('"');
    }
}
