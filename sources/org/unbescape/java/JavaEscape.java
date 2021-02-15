package org.unbescape.java;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class JavaEscape {
    public static String escapeJavaMinimal(String str) {
        return escapeJava(str, JavaEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static String escapeJava(String str) {
        return escapeJava(str, JavaEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static String escapeJava(String str, JavaEscapeLevel javaEscapeLevel) {
        if (javaEscapeLevel != null) {
            return JavaEscapeUtil.escape(str, javaEscapeLevel);
        }
        throw new IllegalArgumentException("The 'level' argument cannot be null");
    }

    public static void escapeJavaMinimal(String str, Writer writer) throws IOException {
        escapeJava(str, writer, JavaEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJava(String str, Writer writer) throws IOException {
        escapeJava(str, writer, JavaEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJava(String str, Writer writer, JavaEscapeLevel javaEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (javaEscapeLevel != null) {
            JavaEscapeUtil.escape(new InternalStringReader(str), writer, javaEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJavaMinimal(Reader reader, Writer writer) throws IOException {
        escapeJava(reader, writer, JavaEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJava(Reader reader, Writer writer) throws IOException {
        escapeJava(reader, writer, JavaEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJava(Reader reader, Writer writer, JavaEscapeLevel javaEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (javaEscapeLevel != null) {
            JavaEscapeUtil.escape(reader, writer, javaEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeJavaMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeJava(cArr, i, i2, writer, JavaEscapeLevel.LEVEL_1_BASIC_ESCAPE_SET);
    }

    public static void escapeJava(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeJava(cArr, i, i2, writer, JavaEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_BASIC_ESCAPE_SET);
    }

    public static void escapeJava(char[] cArr, int i, int i2, Writer writer, JavaEscapeLevel javaEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (javaEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                JavaEscapeUtil.escape(cArr, i, i2, writer, javaEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String unescapeJava(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(92) < 0) {
            return str;
        }
        return JavaEscapeUtil.unescape(str);
    }

    public static void unescapeJava(String str, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            if (str.indexOf(92) < 0) {
                writer.write(str);
            } else {
                JavaEscapeUtil.unescape(new InternalStringReader(str), writer);
            }
        }
    }

    public static void unescapeJava(Reader reader, Writer writer) throws IOException {
        if (writer != null) {
            JavaEscapeUtil.unescape(reader, writer);
            return;
        }
        throw new IllegalArgumentException("Argument 'writer' cannot be null");
    }

    public static void unescapeJava(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (writer != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                JavaEscapeUtil.unescape(cArr, i, i2, writer);
            }
        } else {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        }
    }

    private JavaEscape() {
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
