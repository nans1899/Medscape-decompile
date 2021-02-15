package org.unbescape.properties;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class PropertiesEscape {
    public static String escapePropertiesValueMinimal(String str) {
        return escapePropertiesValue(str, PropertiesValueEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static String escapePropertiesValue(String str) {
        return escapePropertiesValue(str, PropertiesValueEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static String escapePropertiesValue(String str, PropertiesValueEscapeLevel propertiesValueEscapeLevel) {
        if (propertiesValueEscapeLevel != null) {
            return PropertiesValueEscapeUtil.escape(str, propertiesValueEscapeLevel);
        }
        throw new IllegalArgumentException("The 'level' argument cannot be null");
    }

    public static void escapePropertiesValueMinimal(String str, Writer writer) throws IOException {
        escapePropertiesValue(str, writer, PropertiesValueEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesValue(String str, Writer writer) throws IOException {
        escapePropertiesValue(str, writer, PropertiesValueEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesValue(String str, Writer writer, PropertiesValueEscapeLevel propertiesValueEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (propertiesValueEscapeLevel != null) {
            PropertiesValueEscapeUtil.escape(new InternalStringReader(str), writer, propertiesValueEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapePropertiesValueMinimal(Reader reader, Writer writer) throws IOException {
        escapePropertiesValue(reader, writer, PropertiesValueEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesValue(Reader reader, Writer writer) throws IOException {
        escapePropertiesValue(reader, writer, PropertiesValueEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesValue(Reader reader, Writer writer, PropertiesValueEscapeLevel propertiesValueEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (propertiesValueEscapeLevel != null) {
            PropertiesValueEscapeUtil.escape(reader, writer, propertiesValueEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapePropertiesValueMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapePropertiesValue(cArr, i, i2, writer, PropertiesValueEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesValue(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapePropertiesValue(cArr, i, i2, writer, PropertiesValueEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesValue(char[] cArr, int i, int i2, Writer writer, PropertiesValueEscapeLevel propertiesValueEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (propertiesValueEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                PropertiesValueEscapeUtil.escape(cArr, i, i2, writer, propertiesValueEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String escapePropertiesKeyMinimal(String str) {
        return escapePropertiesKey(str, PropertiesKeyEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static String escapePropertiesKey(String str) {
        return escapePropertiesKey(str, PropertiesKeyEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static String escapePropertiesKey(String str, PropertiesKeyEscapeLevel propertiesKeyEscapeLevel) {
        if (propertiesKeyEscapeLevel != null) {
            return PropertiesKeyEscapeUtil.escape(str, propertiesKeyEscapeLevel);
        }
        throw new IllegalArgumentException("The 'level' argument cannot be null");
    }

    public static void escapePropertiesKeyMinimal(String str, Writer writer) throws IOException {
        escapePropertiesKey(str, writer, PropertiesKeyEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesKey(String str, Writer writer) throws IOException {
        escapePropertiesKey(str, writer, PropertiesKeyEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesKey(String str, Writer writer, PropertiesKeyEscapeLevel propertiesKeyEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (propertiesKeyEscapeLevel != null) {
            PropertiesKeyEscapeUtil.escape(new InternalStringReader(str), writer, propertiesKeyEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapePropertiesKeyMinimal(Reader reader, Writer writer) throws IOException {
        escapePropertiesKey(reader, writer, PropertiesKeyEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesKey(Reader reader, Writer writer) throws IOException {
        escapePropertiesKey(reader, writer, PropertiesKeyEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesKey(Reader reader, Writer writer, PropertiesKeyEscapeLevel propertiesKeyEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (propertiesKeyEscapeLevel != null) {
            PropertiesKeyEscapeUtil.escape(reader, writer, propertiesKeyEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapePropertiesKeyMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapePropertiesKey(cArr, i, i2, writer, PropertiesKeyEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesKey(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapePropertiesKey(cArr, i, i2, writer, PropertiesKeyEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapePropertiesKey(char[] cArr, int i, int i2, Writer writer, PropertiesKeyEscapeLevel propertiesKeyEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (propertiesKeyEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                PropertiesKeyEscapeUtil.escape(cArr, i, i2, writer, propertiesKeyEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String unescapeProperties(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(92) < 0) {
            return str;
        }
        return PropertiesUnescapeUtil.unescape(str);
    }

    public static void unescapeProperties(String str, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            if (str.indexOf(92) < 0) {
                writer.write(str);
            } else {
                PropertiesUnescapeUtil.unescape(new InternalStringReader(str), writer);
            }
        }
    }

    public static void unescapeProperties(Reader reader, Writer writer) throws IOException {
        if (writer != null) {
            PropertiesUnescapeUtil.unescape(reader, writer);
            return;
        }
        throw new IllegalArgumentException("Argument 'writer' cannot be null");
    }

    public static void unescapeProperties(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (writer != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                PropertiesUnescapeUtil.unescape(cArr, i, i2, writer);
            }
        } else {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        }
    }

    private PropertiesEscape() {
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
