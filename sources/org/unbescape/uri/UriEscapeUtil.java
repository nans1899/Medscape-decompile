package org.unbescape.uri;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

final class UriEscapeUtil {
    private static final char ESCAPE_PREFIX = '%';
    private static char[] HEXA_CHARS_LOWER = "0123456789abcdef".toCharArray();
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();

    enum UriEscapeType {
        PATH {
            public boolean isAllowed(int i) {
                return UriEscapeType.isPchar(i) || 47 == i;
            }
        },
        PATH_SEGMENT {
            public boolean isAllowed(int i) {
                return UriEscapeType.isPchar(i);
            }
        },
        QUERY_PARAM {
            public boolean canPlusEscapeWhitespace() {
                return true;
            }

            public boolean isAllowed(int i) {
                if (61 == i || 38 == i || 43 == i || 35 == i) {
                    return false;
                }
                return UriEscapeType.isPchar(i) || 47 == i || 63 == i;
            }
        },
        FRAGMENT_ID {
            public boolean isAllowed(int i) {
                return UriEscapeType.isPchar(i) || 47 == i || 63 == i;
            }
        };

        static boolean isAlpha(int i) {
            return (i >= 65 && i <= 90) || (i >= 97 && i <= 122);
        }

        private static boolean isDigit(int i) {
            return i >= 48 && i <= 57;
        }

        private static boolean isGenDelim(int i) {
            return 58 == i || 47 == i || 63 == i || 35 == i || 91 == i || 93 == i || 64 == i;
        }

        private static boolean isSubDelim(int i) {
            return 33 == i || 36 == i || 38 == i || 39 == i || 40 == i || 41 == i || 42 == i || 43 == i || 44 == i || 59 == i || 61 == i;
        }

        public boolean canPlusEscapeWhitespace() {
            return false;
        }

        public abstract boolean isAllowed(int i);

        /* access modifiers changed from: private */
        public static boolean isPchar(int i) {
            return isUnreserved(i) || isSubDelim(i) || 58 == i || 64 == i;
        }

        private static boolean isUnreserved(int i) {
            return isAlpha(i) || isDigit(i) || 45 == i || 46 == i || 95 == i || 126 == i;
        }

        private static boolean isReserved(int i) {
            return isGenDelim(i) || isSubDelim(i);
        }
    }

    private UriEscapeUtil() {
    }

    static char[] printHexa(byte b) {
        char[] cArr = HEXA_CHARS_UPPER;
        return new char[]{cArr[(b >> 4) & 15], cArr[b & Ascii.SI]};
    }

    static byte parseHexa(char c, char c2) {
        byte b;
        int i = 0;
        int i2 = 0;
        while (true) {
            char[] cArr = HEXA_CHARS_UPPER;
            if (i2 >= cArr.length) {
                b = 0;
                break;
            } else if (c == cArr[i2] || c == HEXA_CHARS_LOWER[i2]) {
                b = (byte) ((i2 << 4) + 0);
            } else {
                i2++;
            }
        }
        b = (byte) ((i2 << 4) + 0);
        while (true) {
            char[] cArr2 = HEXA_CHARS_UPPER;
            if (i >= cArr2.length) {
                return b;
            }
            if (c2 != cArr2[i] && c2 != HEXA_CHARS_LOWER[i]) {
                i++;
            }
        }
        return (byte) (b + i);
    }

    static String escape(String str, UriEscapeType uriEscapeType, String str2) {
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int codePointAt = Character.codePointAt(str, i);
            if (!UriEscapeType.isAlpha(codePointAt) && !uriEscapeType.isAllowed(codePointAt)) {
                if (sb == null) {
                    sb = new StringBuilder(length + 20);
                }
                if (i - i2 > 0) {
                    sb.append(str, i2, i);
                }
                if (Character.charCount(codePointAt) > 1) {
                    i++;
                }
                i2 = i + 1;
                try {
                    for (byte printHexa : new String(Character.toChars(codePointAt)).getBytes(str2)) {
                        sb.append(ESCAPE_PREFIX);
                        sb.append(printHexa(printHexa));
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalArgumentException("Exception while escaping URI: Bad encoding '" + str2 + "'", e);
                }
            }
            i++;
        }
        if (sb == null) {
            return str;
        }
        if (length - i2 > 0) {
            sb.append(str, i2, length);
        }
        return sb.toString();
    }

    static void escape(Reader reader, Writer writer, UriEscapeType uriEscapeType, String str) throws IOException {
        if (reader != null) {
            int read = reader.read();
            while (read >= 0) {
                int read2 = reader.read();
                int codePointAt = codePointAt((char) read, (char) read2);
                if (UriEscapeType.isAlpha(codePointAt)) {
                    writer.write(read);
                } else if (uriEscapeType.isAllowed(codePointAt)) {
                    writer.write(read);
                } else {
                    if (Character.charCount(codePointAt) > 1) {
                        read = reader.read();
                    } else {
                        read = read2;
                    }
                    try {
                        for (byte printHexa : new String(Character.toChars(codePointAt)).getBytes(str)) {
                            writer.write(37);
                            writer.write(printHexa(printHexa));
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new IllegalArgumentException("Exception while escaping URI: Bad encoding '" + str + "'", e);
                    }
                }
                read = read2;
            }
        }
    }

    static void escape(char[] cArr, int i, int i2, Writer writer, UriEscapeType uriEscapeType, String str) throws IOException {
        if (cArr != null && cArr.length != 0) {
            int i3 = i2 + i;
            int i4 = i;
            while (i < i3) {
                int codePointAt = Character.codePointAt(cArr, i);
                if (!UriEscapeType.isAlpha(codePointAt) && !uriEscapeType.isAllowed(codePointAt)) {
                    int i5 = i - i4;
                    if (i5 > 0) {
                        writer.write(cArr, i4, i5);
                    }
                    if (Character.charCount(codePointAt) > 1) {
                        i++;
                    }
                    i4 = i + 1;
                    try {
                        for (byte printHexa : new String(Character.toChars(codePointAt)).getBytes(str)) {
                            writer.write(37);
                            writer.write(printHexa(printHexa));
                        }
                    } catch (UnsupportedEncodingException e) {
                        throw new IllegalArgumentException("Exception while escaping URI: Bad encoding '" + str + "'", e);
                    }
                }
                i++;
            }
            int i6 = i3 - i4;
            if (i6 > 0) {
                writer.write(cArr, i4, i6);
            }
        }
    }

    static String unescape(String str, UriEscapeType uriEscapeType, String str2) {
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '%' || (charAt == '+' && uriEscapeType.canPlusEscapeWhitespace())) {
                if (sb == null) {
                    sb = new StringBuilder(length + 5);
                }
                if (i - i2 > 0) {
                    sb.append(str, i2, i);
                }
                if (charAt == '+') {
                    sb.append(' ');
                    i2 = i + 1;
                } else {
                    byte[] bArr = new byte[((length - i) / 3)];
                    int i3 = 0;
                    while (true) {
                        int i4 = i + 2;
                        if (i4 < length && charAt == '%') {
                            int i5 = i3 + 1;
                            bArr[i3] = parseHexa(str.charAt(i + 1), str.charAt(i4));
                            i += 3;
                            if (i < length) {
                                charAt = str.charAt(i);
                            }
                            i3 = i5;
                        } else if (i < length || charAt != '%') {
                            sb.append(new String(bArr, 0, i3, str2));
                            i2 = i;
                        } else {
                            throw new IllegalArgumentException("Incomplete escaping sequence in input");
                        }
                    }
                    if (i < length) {
                    }
                    try {
                        sb.append(new String(bArr, 0, i3, str2));
                        i2 = i;
                    } catch (UnsupportedEncodingException e) {
                        throw new IllegalArgumentException("Exception while escaping URI: Bad encoding '" + str2 + "'", e);
                    }
                }
            }
            i++;
        }
        if (sb == null) {
            return str;
        }
        if (length - i2 > 0) {
            sb.append(str, i2, length);
        }
        return sb.toString();
    }

    static void unescape(Reader reader, Writer writer, UriEscapeType uriEscapeType, String str) throws IOException {
        if (reader != null) {
            byte[] bArr = new byte[4];
            int read = reader.read();
            while (read >= 0) {
                int read2 = reader.read();
                if ((read != 37 || read2 < 0) && (read != 43 || !uriEscapeType.canPlusEscapeWhitespace())) {
                    writer.write(read);
                } else if (read == 43) {
                    writer.write(32);
                } else {
                    int read3 = reader.read();
                    int i = 0;
                    while (read == 37 && read2 >= 0 && read3 >= 0) {
                        if (i == bArr.length) {
                            byte[] bArr2 = new byte[(bArr.length + 4)];
                            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                            bArr = bArr2;
                        }
                        int i2 = i + 1;
                        bArr[i] = parseHexa((char) read2, (char) read3);
                        int read4 = reader.read();
                        int read5 = read4 < 0 ? read4 : read4 != 37 ? 0 : reader.read();
                        int read6 = read5 < 0 ? read5 : read4 != 37 ? 0 : reader.read();
                        i = i2;
                        read = read4;
                        read2 = read5;
                        read3 = read6;
                    }
                    if (read != 37) {
                        try {
                            writer.write(new String(bArr, 0, i, str));
                        } catch (UnsupportedEncodingException e) {
                            throw new IllegalArgumentException("Exception while escaping URI: Bad encoding '" + str + "'", e);
                        }
                    } else {
                        throw new IllegalArgumentException("Incomplete escaping sequence in input");
                    }
                }
                read = read2;
            }
        }
    }

    static void unescape(char[] cArr, int i, int i2, Writer writer, UriEscapeType uriEscapeType, String str) throws IOException {
        if (cArr != null) {
            int i3 = i2 + i;
            int i4 = i;
            while (i < i3) {
                char c = cArr[i];
                if (c == '%' || (c == '+' && uriEscapeType.canPlusEscapeWhitespace())) {
                    int i5 = i - i4;
                    if (i5 > 0) {
                        writer.write(cArr, i4, i5);
                    }
                    if (c == '+') {
                        writer.write(32);
                        i4 = i + 1;
                    } else {
                        byte[] bArr = new byte[((i3 - i) / 3)];
                        int i6 = 0;
                        while (true) {
                            int i7 = i + 2;
                            if (i7 < i3 && c == '%') {
                                int i8 = i6 + 1;
                                bArr[i6] = parseHexa(cArr[i + 1], cArr[i7]);
                                i += 3;
                                if (i < i3) {
                                    c = cArr[i];
                                }
                                i6 = i8;
                            } else if (i < i3 || c != '%') {
                                writer.write(new String(bArr, 0, i6, str));
                                i4 = i;
                            } else {
                                throw new IllegalArgumentException("Incomplete escaping sequence in input");
                            }
                        }
                        if (i < i3) {
                        }
                        try {
                            writer.write(new String(bArr, 0, i6, str));
                            i4 = i;
                        } catch (UnsupportedEncodingException e) {
                            throw new IllegalArgumentException("Exception while escaping URI: Bad encoding '" + str + "'", e);
                        }
                    }
                }
                i++;
            }
            int i9 = i3 - i4;
            if (i9 > 0) {
                writer.write(cArr, i4, i9);
            }
        }
    }

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
