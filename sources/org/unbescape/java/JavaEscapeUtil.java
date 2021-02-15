package org.unbescape.java;

import com.google.common.base.Ascii;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import net.bytebuddy.asm.Advice;

final class JavaEscapeUtil {
    private static final byte[] ESCAPE_LEVELS;
    private static final char ESCAPE_LEVELS_LEN = '¡';
    private static final char ESCAPE_PREFIX = '\\';
    private static final char[] ESCAPE_UHEXA_PREFIX = "\\u".toCharArray();
    private static final char ESCAPE_UHEXA_PREFIX2 = 'u';
    private static char[] HEXA_CHARS_LOWER = "0123456789abcdef".toCharArray();
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();
    private static char[] SEC_CHARS;
    private static int SEC_CHARS_LEN = 93;
    private static char SEC_CHARS_NO_SEC = '*';

    static {
        char[] cArr = new char[93];
        SEC_CHARS = cArr;
        Arrays.fill(cArr, '*');
        char[] cArr2 = SEC_CHARS;
        cArr2[8] = 'b';
        cArr2[9] = Advice.OffsetMapping.ForOrigin.Renderer.ForTypeName.SYMBOL;
        cArr2[10] = 'n';
        cArr2[12] = 'f';
        cArr2[13] = Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName.SYMBOL;
        cArr2[34] = '\"';
        cArr2[39] = '\'';
        cArr2[92] = '\\';
        byte[] bArr = new byte[161];
        ESCAPE_LEVELS = bArr;
        Arrays.fill(bArr, (byte) 3);
        for (char c = 128; c < 161; c = (char) (c + 1)) {
            ESCAPE_LEVELS[c] = 2;
        }
        for (char c2 = 'A'; c2 <= 'Z'; c2 = (char) (c2 + 1)) {
            ESCAPE_LEVELS[c2] = 4;
        }
        for (char c3 = 'a'; c3 <= 'z'; c3 = (char) (c3 + 1)) {
            ESCAPE_LEVELS[c3] = 4;
        }
        for (char c4 = '0'; c4 <= '9'; c4 = (char) (c4 + 1)) {
            ESCAPE_LEVELS[c4] = 4;
        }
        byte[] bArr2 = ESCAPE_LEVELS;
        bArr2[8] = 1;
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[34] = 1;
        bArr2[39] = 3;
        bArr2[92] = 1;
        for (char c5 = 0; c5 <= 31; c5 = (char) (c5 + 1)) {
            ESCAPE_LEVELS[c5] = 1;
        }
        for (char c6 = Ascii.MAX; c6 <= 159; c6 = (char) (c6 + 1)) {
            ESCAPE_LEVELS[c6] = 1;
        }
    }

    private JavaEscapeUtil() {
    }

    static char[] toUHexa(int i) {
        char[] cArr = new char[4];
        char[] cArr2 = HEXA_CHARS_UPPER;
        cArr[3] = cArr2[i % 16];
        cArr[2] = cArr2[(i >>> 4) % 16];
        cArr[1] = cArr2[(i >>> 8) % 16];
        cArr[0] = cArr2[(i >>> 12) % 16];
        return cArr;
    }

    static String escape(String str, JavaEscapeLevel javaEscapeLevel) {
        char c;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int escapeLevel = javaEscapeLevel.getEscapeLevel();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int codePointAt = Character.codePointAt(str, i);
            if (codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt]) {
                if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
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
                    if (codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
                        sb.append('\\');
                        sb.append(c);
                    } else if (Character.charCount(codePointAt) > 1) {
                        char[] chars = Character.toChars(codePointAt);
                        sb.append(ESCAPE_UHEXA_PREFIX);
                        sb.append(toUHexa(chars[0]));
                        sb.append(ESCAPE_UHEXA_PREFIX);
                        sb.append(toUHexa(chars[1]));
                    } else {
                        sb.append(ESCAPE_UHEXA_PREFIX);
                        sb.append(toUHexa(codePointAt));
                    }
                } else if (Character.charCount(codePointAt) > 1) {
                    i++;
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

    static void escape(Reader reader, Writer writer, JavaEscapeLevel javaEscapeLevel) throws IOException {
        char c;
        if (reader != null) {
            int escapeLevel = javaEscapeLevel.getEscapeLevel();
            int read = reader.read();
            while (read >= 0) {
                int read2 = reader.read();
                int codePointAt = codePointAt((char) read, (char) read2);
                if (codePointAt <= 159 && escapeLevel < ESCAPE_LEVELS[codePointAt]) {
                    writer.write(read);
                } else if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
                    if (Character.charCount(codePointAt) > 1) {
                        read = reader.read();
                    } else {
                        read = read2;
                    }
                    if (codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
                        writer.write(92);
                        writer.write(c);
                    } else if (Character.charCount(codePointAt) > 1) {
                        char[] chars = Character.toChars(codePointAt);
                        writer.write(ESCAPE_UHEXA_PREFIX);
                        writer.write(toUHexa(chars[0]));
                        writer.write(ESCAPE_UHEXA_PREFIX);
                        writer.write(toUHexa(chars[1]));
                    } else {
                        writer.write(ESCAPE_UHEXA_PREFIX);
                        writer.write(toUHexa(codePointAt));
                    }
                } else {
                    writer.write(read);
                    if (Character.charCount(codePointAt) > 1) {
                        writer.write(read2);
                        read = reader.read();
                    }
                }
                read = read2;
            }
        }
    }

    static void escape(char[] cArr, int i, int i2, Writer writer, JavaEscapeLevel javaEscapeLevel) throws IOException {
        char c;
        if (cArr != null && cArr.length != 0) {
            int escapeLevel = javaEscapeLevel.getEscapeLevel();
            int i3 = i2 + i;
            int i4 = i;
            while (i < i3) {
                int codePointAt = Character.codePointAt(cArr, i);
                if (codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt]) {
                    if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
                        int i5 = i - i4;
                        if (i5 > 0) {
                            writer.write(cArr, i4, i5);
                        }
                        if (Character.charCount(codePointAt) > 1) {
                            i++;
                        }
                        i4 = i + 1;
                        if (codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
                            writer.write(92);
                            writer.write(c);
                        } else if (Character.charCount(codePointAt) > 1) {
                            char[] chars = Character.toChars(codePointAt);
                            writer.write(ESCAPE_UHEXA_PREFIX);
                            writer.write(toUHexa(chars[0]));
                            writer.write(ESCAPE_UHEXA_PREFIX);
                            writer.write(toUHexa(chars[1]));
                        } else {
                            writer.write(ESCAPE_UHEXA_PREFIX);
                            writer.write(toUHexa(codePointAt));
                        }
                    } else if (Character.charCount(codePointAt) > 1) {
                        i++;
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

    static int parseIntFromReference(String str, int i, int i2, int i3) {
        int i4 = 0;
        while (i < i2) {
            char charAt = str.charAt(i);
            int i5 = -1;
            int i6 = 0;
            while (true) {
                char[] cArr = HEXA_CHARS_UPPER;
                if (i6 >= cArr.length) {
                    break;
                } else if (charAt == cArr[i6] || charAt == HEXA_CHARS_LOWER[i6]) {
                    i5 = i6;
                } else {
                    i6++;
                }
            }
            i4 = (i4 * i3) + i5;
            i++;
        }
        return i4;
    }

    static int parseIntFromReference(char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i < i2) {
            char c = cArr[i];
            int i5 = -1;
            int i6 = 0;
            while (true) {
                char[] cArr2 = HEXA_CHARS_UPPER;
                if (i6 >= cArr2.length) {
                    break;
                } else if (c == cArr2[i6] || c == HEXA_CHARS_LOWER[i6]) {
                    i5 = i6;
                } else {
                    i6++;
                }
            }
            i5 = i6;
            i4 = (i4 * i3) + i5;
            i++;
        }
        return i4;
    }

    static boolean isOctalEscape(String str, int i, int i2) {
        char charAt;
        if (i >= i2 || (charAt = str.charAt(i)) < '0' || charAt > '7') {
            return false;
        }
        int i3 = i + 1;
        if (i3 >= i2) {
            return charAt != '0';
        }
        char charAt2 = str.charAt(i3);
        if (charAt2 >= '0' && charAt2 <= '7') {
            int i4 = i + 2;
            if (i4 < i2) {
                char charAt3 = str.charAt(i4);
                if (charAt3 < '0' || charAt3 > '7') {
                    if (charAt == '0' && charAt2 == '0') {
                        return false;
                    }
                    return true;
                } else if (charAt == '0' && charAt2 == '0' && charAt3 == '0') {
                    return false;
                } else {
                    return true;
                }
            } else if (charAt == '0' && charAt2 == '0') {
                return false;
            } else {
                return true;
            }
        } else if (charAt != '0') {
            return true;
        } else {
            return false;
        }
    }

    static boolean isOctalEscape(char[] cArr, int i, int i2) {
        char c;
        if (i >= i2 || (c = cArr[i]) < '0' || c > '7') {
            return false;
        }
        int i3 = i + 1;
        if (i3 >= i2) {
            return c != '0';
        }
        char c2 = cArr[i3];
        if (c2 >= '0' && c2 <= '7') {
            int i4 = i + 2;
            if (i4 < i2) {
                char c3 = cArr[i4];
                if (c3 < '0' || c3 > '7') {
                    if (c == '0' && c2 == '0') {
                        return false;
                    }
                    return true;
                } else if (c == '0' && c2 == '0' && c3 == '0') {
                    return false;
                } else {
                    return true;
                }
            } else if (c == '0' && c2 == '0') {
                return false;
            } else {
                return true;
            }
        } else if (c != '0') {
            return true;
        } else {
            return false;
        }
    }

    static String unicodeUnescape(String str) {
        int i;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == '\\' && (i = i2 + 1) < length) {
                int i5 = -1;
                if (charAt == '\\') {
                    if (str.charAt(i) == 'u') {
                        int i6 = i2 + 2;
                        while (i6 < length && str.charAt(i6) == 'u') {
                            i6++;
                        }
                        int i7 = i6;
                        while (i7 < i6 + 4 && i7 < length && (((r8 = str.charAt(i7)) >= '0' && r8 <= '9') || ((r8 >= 'A' && r8 <= 'F') || (r8 >= 'a' && r8 <= 'f')))) {
                            i7++;
                        }
                        if (i7 - i6 >= 4) {
                            i5 = parseIntFromReference(str, i6, i7, 16);
                            i4 = i7 - 1;
                        }
                    }
                    i2 = i;
                }
                if (sb == null) {
                    sb = new StringBuilder(length + 5);
                }
                if (i2 - i3 > 0) {
                    sb.append(str, i3, i2);
                }
                int i8 = i4 + 1;
                if (i5 > 65535) {
                    sb.append(Character.toChars(i5));
                } else {
                    sb.append((char) i5);
                }
                i3 = i8;
                i2 = i4;
            }
            i2++;
        }
        if (sb == null) {
            return str;
        }
        if (length - i3 > 0) {
            sb.append(str, i3, length);
        }
        return sb.toString();
    }

    static boolean requiresUnicodeUnescape(char[] cArr, int i, int i2) {
        int i3;
        if (cArr == null) {
            return false;
        }
        int i4 = i2 + i;
        while (i < i4) {
            char c = cArr[i];
            if (c == '\\' && (i3 = i + 1) < i4 && c == '\\' && cArr[i3] == 'u') {
                return true;
            }
            i++;
        }
        return false;
    }

    static void unicodeUnescape(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3;
        if (cArr != null) {
            int i4 = i2 + i;
            int i5 = i;
            int i6 = i5;
            while (i < i4) {
                char c = cArr[i];
                if (c == '\\' && (i3 = i + 1) < i4) {
                    int i7 = -1;
                    if (c == '\\') {
                        if (cArr[i3] == 'u') {
                            int i8 = i + 2;
                            while (i8 < i4 && cArr[i8] == 'u') {
                                i8++;
                            }
                            int i9 = i8;
                            while (i9 < i8 + 4 && i9 < i4 && (((r5 = cArr[i9]) >= '0' && r5 <= '9') || ((r5 >= 'A' && r5 <= 'F') || (r5 >= 'a' && r5 <= 'f')))) {
                                i9++;
                            }
                            if (i9 - i8 >= 4) {
                                i7 = parseIntFromReference(cArr, i8, i9, 16);
                                i6 = i9 - 1;
                            }
                        }
                        i = i3;
                    }
                    int i10 = i - i5;
                    if (i10 > 0) {
                        writer.write(cArr, i5, i10);
                    }
                    int i11 = i6 + 1;
                    if (i7 > 65535) {
                        writer.write(Character.toChars(i7));
                    } else {
                        writer.write((char) i7);
                    }
                    i5 = i11;
                    i = i6;
                }
                i++;
            }
            int i12 = i4 - i5;
            if (i12 > 0) {
                writer.write(cArr, i5, i12);
            }
        }
    }

    static String unescape(String str) {
        int i;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        String unicodeUnescape = unicodeUnescape(str);
        int length = unicodeUnescape.length();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            char charAt = unicodeUnescape.charAt(i2);
            int i5 = 92;
            if (charAt == '\\' && (i = i2 + 1) < length) {
                int i6 = -1;
                if (charAt == '\\') {
                    char charAt2 = unicodeUnescape.charAt(i);
                    if (charAt2 == '\"') {
                        i4 = i;
                        i5 = 34;
                    } else if (charAt2 != '\'') {
                        if (charAt2 != '0') {
                            if (charAt2 != '\\') {
                                if (charAt2 == 'b') {
                                    i4 = i;
                                    i5 = 8;
                                } else if (charAt2 == 'f') {
                                    i5 = 12;
                                } else if (charAt2 == 'n') {
                                    i5 = 10;
                                } else if (charAt2 == 'r') {
                                    i5 = 13;
                                } else if (charAt2 == 't') {
                                    i5 = 9;
                                }
                            }
                            i4 = i;
                        } else if (!isOctalEscape(unicodeUnescape, i, length)) {
                            i4 = i;
                            i5 = 0;
                        }
                        i5 = -1;
                    } else {
                        i4 = i;
                        i5 = 39;
                    }
                    if (i5 != -1) {
                        i6 = i5;
                    } else if (charAt2 < '0' || charAt2 > '7') {
                        i2 = i;
                    } else {
                        int i7 = i2 + 2;
                        while (i7 < i2 + 4 && i7 < length && (r6 = unicodeUnescape.charAt(i7)) >= '0' && r6 <= '7') {
                            i7++;
                        }
                        int parseIntFromReference = parseIntFromReference(unicodeUnescape, i, i7, 8);
                        if (parseIntFromReference > 255) {
                            parseIntFromReference = parseIntFromReference(unicodeUnescape, i, i7 - 1, 8);
                            i4 = i7 - 2;
                        } else {
                            i4 = i7 - 1;
                        }
                        i6 = parseIntFromReference;
                    }
                }
                if (sb == null) {
                    sb = new StringBuilder(length + 5);
                }
                if (i2 - i3 > 0) {
                    sb.append(unicodeUnescape, i3, i2);
                }
                i3 = i4 + 1;
                if (i6 > 65535) {
                    sb.append(Character.toChars(i6));
                } else {
                    sb.append((char) i6);
                }
                i2 = i4;
            }
            i2++;
        }
        if (sb == null) {
            return unicodeUnescape;
        }
        if (length - i3 > 0) {
            sb.append(unicodeUnescape, i3, length);
        }
        return sb.toString();
    }

    static void unescape(Reader reader, Writer writer) throws IOException {
        char[] cArr;
        int read;
        int i;
        int i2;
        if (reader != null && (read = reader.read(cArr, 0, 20)) >= 0) {
            char[] cArr2 = new char[20];
            int i3 = read;
            while (true) {
                if (read > 0 || i3 >= 0) {
                    int i4 = read;
                    while (true) {
                        i = 0;
                        while (true) {
                            if (i >= 8) {
                                break;
                            }
                            i2 = i4 - 1;
                            if (i4 == 0) {
                                i4 = i2;
                                break;
                            } else if (cArr2[i2] == '\\') {
                                break;
                            } else {
                                i++;
                                i4 = i2;
                            }
                        }
                        i4 = i2;
                    }
                    if (i >= 8 || i3 < 0) {
                        int i5 = i4 < 0 ? read : i4 + i;
                        unescape(cArr2, 0, i5, writer);
                        read -= i5;
                        System.arraycopy(cArr2, i5, cArr2, 0, read);
                        i3 = reader.read(cArr2, read, cArr2.length - read);
                        if (i3 < 0) {
                        }
                    } else {
                        if (read == cArr2.length) {
                            char[] cArr3 = new char[(cArr2.length + (cArr2.length / 2))];
                            System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
                            cArr2 = cArr3;
                        }
                        i3 = reader.read(cArr2, read, cArr2.length - read);
                        if (i3 < 0) {
                        }
                    }
                    read += i3;
                } else {
                    return;
                }
            }
        }
    }

    static void unescape(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3;
        if (cArr != null) {
            if (requiresUnicodeUnescape(cArr, i, i2)) {
                CharArrayWriter charArrayWriter = new CharArrayWriter(i2 + 2);
                unicodeUnescape(cArr, i, i2, charArrayWriter);
                cArr = charArrayWriter.toCharArray();
                i2 = cArr.length;
                i = 0;
            }
            int i4 = i2 + i;
            int i5 = i;
            int i6 = i5;
            while (i < i4) {
                char c = cArr[i];
                int i7 = 92;
                if (c == '\\' && (i3 = i + 1) < i4) {
                    int i8 = -1;
                    if (c == '\\') {
                        char c2 = cArr[i3];
                        if (c2 == '\"') {
                            i6 = i3;
                            i7 = 34;
                        } else if (c2 != '\'') {
                            if (c2 != '0') {
                                if (c2 != '\\') {
                                    if (c2 == 'b') {
                                        i6 = i3;
                                        i7 = 8;
                                    } else if (c2 == 'f') {
                                        i7 = 12;
                                    } else if (c2 == 'n') {
                                        i7 = 10;
                                    } else if (c2 == 'r') {
                                        i7 = 13;
                                    } else if (c2 == 't') {
                                        i7 = 9;
                                    }
                                }
                                i6 = i3;
                            } else if (!isOctalEscape(cArr, i3, i4)) {
                                i6 = i3;
                                i7 = 0;
                            }
                            i7 = -1;
                        } else {
                            i6 = i3;
                            i7 = 39;
                        }
                        if (i7 != -1) {
                            i8 = i7;
                        } else if (c2 < '0' || c2 > '7') {
                            i = i3;
                        } else {
                            int i9 = i + 2;
                            while (i9 < i + 4 && i9 < i4 && (r3 = cArr[i9]) >= '0' && r3 <= '7') {
                                i9++;
                            }
                            int parseIntFromReference = parseIntFromReference(cArr, i3, i9, 8);
                            if (parseIntFromReference > 255) {
                                parseIntFromReference = parseIntFromReference(cArr, i3, i9 - 1, 8);
                                i6 = i9 - 2;
                            } else {
                                i6 = i9 - 1;
                            }
                            i8 = parseIntFromReference;
                        }
                    }
                    int i10 = i - i5;
                    if (i10 > 0) {
                        writer.write(cArr, i5, i10);
                    }
                    int i11 = i6 + 1;
                    if (i8 > 65535) {
                        writer.write(Character.toChars(i8));
                    } else {
                        writer.write((char) i8);
                    }
                    i5 = i11;
                    i = i6;
                }
                i++;
            }
            int i12 = i4 - i5;
            if (i12 > 0) {
                writer.write(cArr, i5, i12);
            }
        }
    }

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
