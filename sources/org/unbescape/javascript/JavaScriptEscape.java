package org.unbescape.javascript;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class JavaScriptEscape {
    public static String escapeJavaScriptMinimal(String str) {
        return escapeJavaScript(str, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static String escapeJavaScript(String str) {
        return escapeJavaScript(str, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static String escapeJavaScript(String str, JavaScriptEscapeType javaScriptEscapeType, JavaScriptEscapeLevel javaScriptEscapeLevel) {
        if (javaScriptEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (javaScriptEscapeLevel != null) {
            return JavaScriptEscapeUtil.escape(str, javaScriptEscapeType, javaScriptEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJavaScriptMinimal(String str, Writer writer) throws IOException {
        escapeJavaScript(str, writer, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJavaScript(String str, Writer writer) throws IOException {
        escapeJavaScript(str, writer, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJavaScript(String str, Writer writer, JavaScriptEscapeType javaScriptEscapeType, JavaScriptEscapeLevel javaScriptEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (javaScriptEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (javaScriptEscapeLevel != null) {
            JavaScriptEscapeUtil.escape(new InternalStringReader(str), writer, javaScriptEscapeType, javaScriptEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJavaScriptMinimal(Reader reader, Writer writer) throws IOException {
        escapeJavaScript(reader, writer, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJavaScript(Reader reader, Writer writer) throws IOException {
        escapeJavaScript(reader, writer, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJavaScript(Reader reader, Writer writer, JavaScriptEscapeType javaScriptEscapeType, JavaScriptEscapeLevel javaScriptEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (javaScriptEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (javaScriptEscapeLevel != null) {
            JavaScriptEscapeUtil.escape(reader, writer, javaScriptEscapeType, javaScriptEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJavaScriptMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeJavaScript(cArr, i, i2, writer, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJavaScript(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeJavaScript(cArr, i, i2, writer, JavaScriptEscapeType.SINGLE_ESCAPE_CHARS_DEFAULT_TO_XHEXA_AND_UHEXA, JavaScriptEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJavaScript(char[] cArr, int i, int i2, Writer writer, JavaScriptEscapeType javaScriptEscapeType, JavaScriptEscapeLevel javaScriptEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (javaScriptEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (javaScriptEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                JavaScriptEscapeUtil.escape(cArr, i, i2, writer, javaScriptEscapeType, javaScriptEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String unescapeJavaScript(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(92) < 0) {
            return str;
        }
        return JavaScriptEscapeUtil.unescape(str);
    }

    public static void unescapeJavaScript(String str, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            if (str.indexOf(92) < 0) {
                writer.write(str);
            } else {
                JavaScriptEscapeUtil.unescape(new InternalStringReader(str), writer);
            }
        }
    }

    public static void unescapeJavaScript(Reader reader, Writer writer) throws IOException {
        if (writer != null) {
            JavaScriptEscapeUtil.unescape(reader, writer);
            return;
        }
        throw new IllegalArgumentException("Argument 'writer' cannot be null");
    }

    public static void unescapeJavaScript(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (writer != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                JavaScriptEscapeUtil.unescape(cArr, i, i2, writer);
            }
        } else {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        }
    }

    private JavaScriptEscape() {
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
