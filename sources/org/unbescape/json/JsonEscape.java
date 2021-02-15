package org.unbescape.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class JsonEscape {
    public static String escapeJsonMinimal(String str) {
        return escapeJson(str, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static String escapeJson(String str) {
        return escapeJson(str, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static String escapeJson(String str, JsonEscapeType jsonEscapeType, JsonEscapeLevel jsonEscapeLevel) {
        if (jsonEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (jsonEscapeLevel != null) {
            return JsonEscapeUtil.escape(str, jsonEscapeType, jsonEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJsonMinimal(String str, Writer writer) throws IOException {
        escapeJson(str, writer, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJson(String str, Writer writer) throws IOException {
        escapeJson(str, writer, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJson(String str, Writer writer, JsonEscapeType jsonEscapeType, JsonEscapeLevel jsonEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (jsonEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (jsonEscapeLevel != null) {
            JsonEscapeUtil.escape(new InternalStringReader(str), writer, jsonEscapeType, jsonEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJsonMinimal(Reader reader, Writer writer) throws IOException {
        escapeJson(reader, writer, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJson(Reader reader, Writer writer) throws IOException {
        escapeJson(reader, writer, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJson(Reader reader, Writer writer, JsonEscapeType jsonEscapeType, JsonEscapeLevel jsonEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (jsonEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (jsonEscapeLevel != null) {
            JsonEscapeUtil.escape(reader, writer, jsonEscapeType, jsonEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJsonMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeJson(cArr, i, i2, writer, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJson(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeJson(cArr, i, i2, writer, JsonEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_UHEXA, JsonEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJson(char[] cArr, int i, int i2, Writer writer, JsonEscapeType jsonEscapeType, JsonEscapeLevel jsonEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (jsonEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (jsonEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                JsonEscapeUtil.escape(cArr, i, i2, writer, jsonEscapeType, jsonEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String unescapeJson(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(92) < 0) {
            return str;
        }
        return JsonEscapeUtil.unescape(str);
    }

    public static void unescapeJson(String str, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            if (str.indexOf(92) < 0) {
                writer.write(str);
            } else {
                JsonEscapeUtil.unescape(new InternalStringReader(str), writer);
            }
        }
    }

    public static void unescapeJson(Reader reader, Writer writer) throws IOException {
        if (writer != null) {
            JsonEscapeUtil.unescape(reader, writer);
            return;
        }
        throw new IllegalArgumentException("Argument 'writer' cannot be null");
    }

    public static void unescapeJson(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (writer != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                JsonEscapeUtil.unescape(cArr, i, i2, writer);
            }
        } else {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        }
    }

    private JsonEscape() {
    }

    private static final class InternalStringReader extends Reader {
        private int length;
        private int next = 0;
        private String str;

        public InternalStringReader(String str2) {
            this.str = str2;
            this.length = str2.length();
        }

        public int read() throws IOException {
            int i = this.next;
            if (i >= this.length) {
                return -1;
            }
            String str2 = this.str;
            this.next = i + 1;
            return str2.charAt(i);
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            int i3;
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 == 0) {
                return 0;
            } else {
                int i4 = this.next;
                int i5 = this.length;
                if (i4 >= i5) {
                    return -1;
                }
                int min = Math.min(i5 - i4, i2);
                String str2 = this.str;
                int i6 = this.next;
                str2.getChars(i6, i6 + min, cArr, i);
                this.next += min;
                return min;
            }
        }

        public void close() throws IOException {
            this.str = null;
        }
    }
}
