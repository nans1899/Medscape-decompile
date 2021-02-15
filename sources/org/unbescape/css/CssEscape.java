package org.unbescape.css;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class CssEscape {
    public static String escapeCssStringMinimal(String str) {
        return escapeCssString(str, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static String escapeCssString(String str) {
        return escapeCssString(str, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static String escapeCssString(String str, CssStringEscapeType cssStringEscapeType, CssStringEscapeLevel cssStringEscapeLevel) {
        if (cssStringEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssStringEscapeLevel != null) {
            return CssStringEscapeUtil.escape(str, cssStringEscapeType, cssStringEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeCssStringMinimal(String str, Writer writer) throws IOException {
        escapeCssString(str, writer, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeCssString(String str, Writer writer) throws IOException {
        escapeCssString(str, writer, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeCssString(String str, Writer writer, CssStringEscapeType cssStringEscapeType, CssStringEscapeLevel cssStringEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (cssStringEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssStringEscapeLevel != null) {
            CssStringEscapeUtil.escape(new InternalStringReader(str), writer, cssStringEscapeType, cssStringEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeCssStringMinimal(Reader reader, Writer writer) throws IOException {
        escapeCssString(reader, writer, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeCssString(Reader reader, Writer writer) throws IOException {
        escapeCssString(reader, writer, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeCssString(Reader reader, Writer writer, CssStringEscapeType cssStringEscapeType, CssStringEscapeLevel cssStringEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (cssStringEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssStringEscapeLevel != null) {
            CssStringEscapeUtil.escape(reader, writer, cssStringEscapeType, cssStringEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeCssStringMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeCssString(cArr, i, i2, writer, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeCssString(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeCssString(cArr, i, i2, writer, CssStringEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssStringEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeCssString(char[] cArr, int i, int i2, Writer writer, CssStringEscapeType cssStringEscapeType, CssStringEscapeLevel cssStringEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (cssStringEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssStringEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                CssStringEscapeUtil.escape(cArr, i, i2, writer, cssStringEscapeType, cssStringEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String escapeCssIdentifierMinimal(String str) {
        return escapeCssIdentifier(str, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static String escapeCssIdentifier(String str) {
        return escapeCssIdentifier(str, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static String escapeCssIdentifier(String str, CssIdentifierEscapeType cssIdentifierEscapeType, CssIdentifierEscapeLevel cssIdentifierEscapeLevel) {
        if (cssIdentifierEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssIdentifierEscapeLevel != null) {
            return CssIdentifierEscapeUtil.escape(str, cssIdentifierEscapeType, cssIdentifierEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeCssIdentifierMinimal(String str, Writer writer) throws IOException {
        escapeCssIdentifier(str, writer, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeCssIdentifier(String str, Writer writer) throws IOException {
        escapeCssIdentifier(str, writer, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeCssIdentifier(String str, Writer writer, CssIdentifierEscapeType cssIdentifierEscapeType, CssIdentifierEscapeLevel cssIdentifierEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (cssIdentifierEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssIdentifierEscapeLevel != null) {
            CssIdentifierEscapeUtil.escape(new InternalStringReader(str), writer, cssIdentifierEscapeType, cssIdentifierEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeCssIdentifierMinimal(Reader reader, Writer writer) throws IOException {
        escapeCssIdentifier(reader, writer, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeCssIdentifier(Reader reader, Writer writer) throws IOException {
        escapeCssIdentifier(reader, writer, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeCssIdentifier(Reader reader, Writer writer, CssIdentifierEscapeType cssIdentifierEscapeType, CssIdentifierEscapeLevel cssIdentifierEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (cssIdentifierEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssIdentifierEscapeLevel != null) {
            CssIdentifierEscapeUtil.escape(reader, writer, cssIdentifierEscapeType, cssIdentifierEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeCssIdentifierMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeCssIdentifier(cArr, i, i2, writer, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeCssIdentifier(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeCssIdentifier(cArr, i, i2, writer, CssIdentifierEscapeType.BACKSLASH_ESCAPES_DEFAULT_TO_COMPACT_HEXA, CssIdentifierEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeCssIdentifier(char[] cArr, int i, int i2, Writer writer, CssIdentifierEscapeType cssIdentifierEscapeType, CssIdentifierEscapeLevel cssIdentifierEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (cssIdentifierEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (cssIdentifierEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                CssIdentifierEscapeUtil.escape(cArr, i, i2, writer, cssIdentifierEscapeType, cssIdentifierEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String unescapeCss(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(92) < 0) {
            return str;
        }
        return CssUnescapeUtil.unescape(str);
    }

    public static void unescapeCss(String str, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            if (str.indexOf(92) < 0) {
                writer.write(str);
            } else {
                CssUnescapeUtil.unescape(new InternalStringReader(str), writer);
            }
        }
    }

    public static void unescapeCss(Reader reader, Writer writer) throws IOException {
        if (writer != null) {
            CssUnescapeUtil.unescape(reader, writer);
            return;
        }
        throw new IllegalArgumentException("Argument 'writer' cannot be null");
    }

    public static void unescapeCss(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (writer != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                CssUnescapeUtil.unescape(cArr, i, i2, writer);
            }
        } else {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        }
    }

    private CssEscape() {
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
