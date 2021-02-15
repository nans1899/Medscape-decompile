package org.unbescape.uri;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import org.unbescape.uri.UriEscapeUtil;

public final class UriEscape {
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static String escapeUriPath(String str) {
        return escapeUriPath(str, "UTF-8");
    }

    public static String escapeUriPath(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.escape(str, UriEscapeUtil.UriEscapeType.PATH, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static String escapeUriPathSegment(String str) {
        return escapeUriPathSegment(str, "UTF-8");
    }

    public static String escapeUriPathSegment(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.escape(str, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static String escapeUriQueryParam(String str) {
        return escapeUriQueryParam(str, "UTF-8");
    }

    public static String escapeUriQueryParam(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.escape(str, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static String escapeUriFragmentId(String str) {
        return escapeUriFragmentId(str, "UTF-8");
    }

    public static String escapeUriFragmentId(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.escape(str, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static void escapeUriPath(String str, Writer writer) throws IOException {
        escapeUriPath(str, writer, "UTF-8");
    }

    public static void escapeUriPath(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.escape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.PATH, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriPathSegment(String str, Writer writer) throws IOException {
        escapeUriPathSegment(str, writer, "UTF-8");
    }

    public static void escapeUriPathSegment(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.escape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriQueryParam(String str, Writer writer) throws IOException {
        escapeUriQueryParam(str, writer, "UTF-8");
    }

    public static void escapeUriQueryParam(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.escape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriFragmentId(String str, Writer writer) throws IOException {
        escapeUriFragmentId(str, writer, "UTF-8");
    }

    public static void escapeUriFragmentId(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.escape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriPath(Reader reader, Writer writer) throws IOException {
        escapeUriPath(reader, writer, "UTF-8");
    }

    public static void escapeUriPath(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.escape(reader, writer, UriEscapeUtil.UriEscapeType.PATH, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriPathSegment(Reader reader, Writer writer) throws IOException {
        escapeUriPathSegment(reader, writer, "UTF-8");
    }

    public static void escapeUriPathSegment(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.escape(reader, writer, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriQueryParam(Reader reader, Writer writer) throws IOException {
        escapeUriQueryParam(reader, writer, "UTF-8");
    }

    public static void escapeUriQueryParam(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.escape(reader, writer, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriFragmentId(Reader reader, Writer writer) throws IOException {
        escapeUriFragmentId(reader, writer, "UTF-8");
    }

    public static void escapeUriFragmentId(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.escape(reader, writer, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriPath(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeUriPath(cArr, i, i2, writer, "UTF-8");
    }

    public static void escapeUriPath(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.escape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.PATH, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriPathSegment(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeUriPathSegment(cArr, i, i2, writer, "UTF-8");
    }

    public static void escapeUriPathSegment(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.escape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriQueryParam(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeUriQueryParam(cArr, i, i2, writer, "UTF-8");
    }

    public static void escapeUriQueryParam(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.escape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void escapeUriFragmentId(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeUriFragmentId(cArr, i, i2, writer, "UTF-8");
    }

    public static void escapeUriFragmentId(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.escape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static String unescapeUriPath(String str) {
        return unescapeUriPath(str, "UTF-8");
    }

    public static String unescapeUriPath(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.unescape(str, UriEscapeUtil.UriEscapeType.PATH, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static String unescapeUriPathSegment(String str) {
        return unescapeUriPathSegment(str, "UTF-8");
    }

    public static String unescapeUriPathSegment(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.unescape(str, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static String unescapeUriQueryParam(String str) {
        return unescapeUriQueryParam(str, "UTF-8");
    }

    public static String unescapeUriQueryParam(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.unescape(str, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static String unescapeUriFragmentId(String str) {
        return unescapeUriFragmentId(str, "UTF-8");
    }

    public static String unescapeUriFragmentId(String str, String str2) {
        if (str2 != null) {
            return UriEscapeUtil.unescape(str, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str2);
        }
        throw new IllegalArgumentException("Argument 'encoding' cannot be null");
    }

    public static void unescapeUriPath(String str, Writer writer) throws IOException {
        unescapeUriPath(str, writer, "UTF-8");
    }

    public static void unescapeUriPath(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.unescape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.PATH, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriPathSegment(String str, Writer writer) throws IOException {
        unescapeUriPathSegment(str, writer, "UTF-8");
    }

    public static void unescapeUriPathSegment(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.unescape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriQueryParam(String str, Writer writer) throws IOException {
        unescapeUriQueryParam(str, writer, "UTF-8");
    }

    public static void unescapeUriQueryParam(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.unescape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriFragmentId(String str, Writer writer) throws IOException {
        unescapeUriFragmentId(str, writer, "UTF-8");
    }

    public static void unescapeUriFragmentId(String str, Writer writer, String str2) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str2 != null) {
            UriEscapeUtil.unescape(new InternalStringReader(str), writer, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str2);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriPath(Reader reader, Writer writer) throws IOException {
        unescapeUriPath(reader, writer, "UTF-8");
    }

    public static void unescapeUriPath(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.unescape(reader, writer, UriEscapeUtil.UriEscapeType.PATH, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriPathSegment(Reader reader, Writer writer) throws IOException {
        unescapeUriPathSegment(reader, writer, "UTF-8");
    }

    public static void unescapeUriPathSegment(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.unescape(reader, writer, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriQueryParam(Reader reader, Writer writer) throws IOException {
        unescapeUriQueryParam(reader, writer, "UTF-8");
    }

    public static void unescapeUriQueryParam(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.unescape(reader, writer, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriFragmentId(Reader reader, Writer writer) throws IOException {
        unescapeUriFragmentId(reader, writer, "UTF-8");
    }

    public static void unescapeUriFragmentId(Reader reader, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            UriEscapeUtil.unescape(reader, writer, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str);
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriPath(char[] cArr, int i, int i2, Writer writer) throws IOException {
        unescapeUriPath(cArr, i, i2, writer, "UTF-8");
    }

    public static void unescapeUriPath(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.unescape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.PATH, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriPathSegment(char[] cArr, int i, int i2, Writer writer) throws IOException {
        unescapeUriPathSegment(cArr, i, i2, writer, "UTF-8");
    }

    public static void unescapeUriPathSegment(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.unescape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.PATH_SEGMENT, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriQueryParam(char[] cArr, int i, int i2, Writer writer) throws IOException {
        unescapeUriQueryParam(cArr, i, i2, writer, "UTF-8");
    }

    public static void unescapeUriQueryParam(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.unescape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.QUERY_PARAM, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    public static void unescapeUriFragmentId(char[] cArr, int i, int i2, Writer writer) throws IOException {
        unescapeUriFragmentId(cArr, i, i2, writer, "UTF-8");
    }

    public static void unescapeUriFragmentId(char[] cArr, int i, int i2, Writer writer, String str) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                UriEscapeUtil.unescape(cArr, i, i2, writer, UriEscapeUtil.UriEscapeType.FRAGMENT_ID, str);
            }
        } else {
            throw new IllegalArgumentException("Argument 'encoding' cannot be null");
        }
    }

    private UriEscape() {
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
