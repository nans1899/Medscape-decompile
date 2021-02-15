package org.unbescape.html;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class HtmlEscape {
    public static String escapeHtml5(String str) {
        return escapeHtml(str, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static String escapeHtml5Xml(String str) {
        return escapeHtml(str, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static String escapeHtml4(String str) {
        return escapeHtml(str, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static String escapeHtml4Xml(String str) {
        return escapeHtml(str, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static String escapeHtml(String str, HtmlEscapeType htmlEscapeType, HtmlEscapeLevel htmlEscapeLevel) {
        if (htmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (htmlEscapeLevel != null) {
            return HtmlEscapeUtil.escape(str, htmlEscapeType, htmlEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeHtml5(String str, Writer writer) throws IOException {
        escapeHtml(str, writer, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml5Xml(String str, Writer writer) throws IOException {
        escapeHtml(str, writer, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml4(String str, Writer writer) throws IOException {
        escapeHtml(str, writer, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml4Xml(String str, Writer writer) throws IOException {
        escapeHtml(str, writer, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml(String str, Writer writer, HtmlEscapeType htmlEscapeType, HtmlEscapeLevel htmlEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (htmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (htmlEscapeLevel != null) {
            HtmlEscapeUtil.escape(new InternalStringReader(str), writer, htmlEscapeType, htmlEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeHtml5(Reader reader, Writer writer) throws IOException {
        escapeHtml(reader, writer, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml5Xml(Reader reader, Writer writer) throws IOException {
        escapeHtml(reader, writer, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml4(Reader reader, Writer writer) throws IOException {
        escapeHtml(reader, writer, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml4Xml(Reader reader, Writer writer) throws IOException {
        escapeHtml(reader, writer, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml(Reader reader, Writer writer, HtmlEscapeType htmlEscapeType, HtmlEscapeLevel htmlEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (htmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (htmlEscapeLevel != null) {
            HtmlEscapeUtil.escape(reader, writer, htmlEscapeType, htmlEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeHtml5(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeHtml(cArr, i, i2, writer, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml5Xml(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeHtml(cArr, i, i2, writer, HtmlEscapeType.HTML5_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml4(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeHtml(cArr, i, i2, writer, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml4Xml(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeHtml(cArr, i, i2, writer, HtmlEscapeType.HTML4_NAMED_REFERENCES_DEFAULT_TO_DECIMAL, HtmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeHtml(char[] cArr, int i, int i2, Writer writer, HtmlEscapeType htmlEscapeType, HtmlEscapeLevel htmlEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (htmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (htmlEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                HtmlEscapeUtil.escape(cArr, i, i2, writer, htmlEscapeType, htmlEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String unescapeHtml(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(38) < 0) {
            return str;
        }
        return HtmlEscapeUtil.unescape(str);
    }

    public static void unescapeHtml(String str, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            if (str.indexOf(38) < 0) {
                writer.write(str);
            } else {
                HtmlEscapeUtil.unescape(new InternalStringReader(str), writer);
            }
        }
    }

    public static void unescapeHtml(Reader reader, Writer writer) throws IOException {
        if (writer != null) {
            HtmlEscapeUtil.unescape(reader, writer);
            return;
        }
        throw new IllegalArgumentException("Argument 'writer' cannot be null");
    }

    public static void unescapeHtml(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (writer != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                HtmlEscapeUtil.unescape(cArr, i, i2, writer);
            }
        } else {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        }
    }

    private HtmlEscape() {
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
