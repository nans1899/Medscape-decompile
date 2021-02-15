package org.unbescape.javascript;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import net.bytebuddy.asm.Advice;

final class JavaScriptEscapeUtil {
    private static final byte[] ESCAPE_LEVELS;
    private static final char ESCAPE_LEVELS_LEN = '¡';
    private static final char ESCAPE_PREFIX = '\\';
    private static final char[] ESCAPE_UHEXA_PREFIX = "\\u".toCharArray();
    private static final char ESCAPE_UHEXA_PREFIX2 = 'u';
    private static final char[] ESCAPE_XHEXA_PREFIX = "\\x".toCharArray();
    private static final char ESCAPE_XHEXA_PREFIX2 = 'x';
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
        cArr2[0] = '0';
        cArr2[8] = 'b';
        cArr2[9] = Advice.OffsetMapping.ForOrigin.Renderer.ForTypeName.SYMBOL;
        cArr2[10] = 'n';
        cArr2[12] = 'f';
        cArr2[13] = Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName.SYMBOL;
        cArr2[34] = '\"';
        cArr2[39] = '\'';
        cArr2[92] = '\\';
        cArr2[47] = '/';
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
        bArr2[0] = 1;
        bArr2[8] = 1;
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[34] = 1;
        bArr2[39] = 1;
        bArr2[92] = 1;
        bArr2[47] = 1;
        bArr2[38] = 1;
        for (char c5 = 1; c5 <= 31; c5 = (char) (c5 + 1)) {
            ESCAPE_LEVELS[c5] = 1;
        }
        for (char c6 = Ascii.MAX; c6 <= 159; c6 = (char) (c6 + 1)) {
            ESCAPE_LEVELS[c6] = 1;
        }
    }

    private JavaScriptEscapeUtil() {
    }

    static char[] toXHexa(int i) {
        char[] cArr = new char[2];
        char[] cArr2 = HEXA_CHARS_UPPER;
        cArr[1] = cArr2[i % 16];
        cArr[0] = cArr2[(i >>> 4) % 16];
        return cArr;
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

    static String escape(String str, JavaScriptEscapeType javaScriptEscapeType, JavaScriptEscapeLevel javaScriptEscapeLevel) {
        char c;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int escapeLevel = javaScriptEscapeLevel.getEscapeLevel();
        boolean useSECs = javaScriptEscapeType.getUseSECs();
        boolean useXHexa = javaScriptEscapeType.getUseXHexa();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int codePointAt = Character.codePointAt(str, i);
            if ((codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt]) && (codePointAt != 47 || escapeLevel >= 3 || (i != 0 && str.charAt(i - 1) == '<'))) {
                if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160] || codePointAt == 8232 || codePointAt == 8233) {
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
                    if (useSECs && codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
                        sb.append('\\');
                        sb.append(c);
                    } else if (useXHexa && codePointAt <= 255) {
                        sb.append(ESCAPE_XHEXA_PREFIX);
                        sb.append(toXHexa(codePointAt));
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

    static void escape(Reader reader, Writer writer, JavaScriptEscapeType javaScriptEscapeType, JavaScriptEscapeLevel javaScriptEscapeLevel) throws IOException {
        char c;
        if (reader != null) {
            int escapeLevel = javaScriptEscapeLevel.getEscapeLevel();
            boolean useSECs = javaScriptEscapeType.getUseSECs();
            boolean useXHexa = javaScriptEscapeType.getUseXHexa();
            int read = reader.read();
            int i = -1;
            while (read >= 0) {
                int read2 = reader.read();
                int codePointAt = codePointAt((char) read, (char) read2);
                if (codePointAt <= 159 && escapeLevel < ESCAPE_LEVELS[codePointAt]) {
                    writer.write(read);
                } else if (codePointAt != 47 || escapeLevel >= 3 || i == 60) {
                    if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160] || codePointAt == 8232 || codePointAt == 8233) {
                        if (Character.charCount(codePointAt) > 1) {
                            read = reader.read();
                        } else {
                            int i2 = read2;
                            read2 = read;
                            read = i2;
                        }
                        if (useSECs && codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
                            writer.write(92);
                            writer.write(c);
                        } else if (useXHexa && codePointAt <= 255) {
                            writer.write(ESCAPE_XHEXA_PREFIX);
                            writer.write(toXHexa(codePointAt));
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
                    i = read2;
                } else {
                    writer.write(read);
                }
                i = read;
                read = read2;
            }
        }
    }

    static void escape(char[] cArr, int i, int i2, Writer writer, JavaScriptEscapeType javaScriptEscapeType, JavaScriptEscapeLevel javaScriptEscapeLevel) throws IOException {
        char c;
        if (cArr != null && cArr.length != 0) {
            int escapeLevel = javaScriptEscapeLevel.getEscapeLevel();
            boolean useSECs = javaScriptEscapeType.getUseSECs();
            boolean useXHexa = javaScriptEscapeType.getUseXHexa();
            int i3 = i2 + i;
            int i4 = i;
            while (i < i3) {
                int codePointAt = Character.codePointAt(cArr, i);
                if ((codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt]) && (codePointAt != 47 || escapeLevel >= 3 || (i != 0 && cArr[i - 1] == '<'))) {
                    if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160] || codePointAt == 8232 || codePointAt == 8233) {
                        int i5 = i - i4;
                        if (i5 > 0) {
                            writer.write(cArr, i4, i5);
                        }
                        if (Character.charCount(codePointAt) > 1) {
                            i++;
                        }
                        i4 = i + 1;
                        if (useSECs && codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
                            writer.write(92);
                            writer.write(c);
                        } else if (useXHexa && codePointAt <= 255) {
                            writer.write(ESCAPE_XHEXA_PREFIX);
                            writer.write(toXHexa(codePointAt));
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

    static int parseIntFromReference(int[] iArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i < i2) {
            char c = (char) iArr[i];
            int i5 = -1;
            int i6 = 0;
            while (true) {
                char[] cArr = HEXA_CHARS_UPPER;
                if (i6 >= cArr.length) {
                    break;
                } else if (c == cArr[i6] || c == HEXA_CHARS_LOWER[i6]) {
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

    static String unescape(String str) {
        int i;
        int i2;
        String str2 = str;
        StringBuilder sb = null;
        if (str2 == null) {
            return null;
        }
        int length = str.length();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < length) {
            char charAt = str2.charAt(i3);
            char c = '\\';
            if (charAt == '\\' && (i = i3 + 1) < length) {
                char c2 = 65535;
                if (charAt == '\\') {
                    char charAt2 = str2.charAt(i);
                    if (charAt2 == 10) {
                        i5 = i;
                        c = 65534;
                    } else if (charAt2 == '\"') {
                        i5 = i;
                        c = '\"';
                    } else if (charAt2 != '\'') {
                        if (charAt2 != '\\') {
                            if (charAt2 == 'b') {
                                i5 = i;
                                c = 8;
                            } else if (charAt2 == 'f') {
                                c = 12;
                            } else if (charAt2 == 'n') {
                                i5 = i;
                                c = 10;
                            } else if (charAt2 == 'r') {
                                i5 = i;
                                c = ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN;
                            } else if (charAt2 == 't') {
                                c = 9;
                            } else if (charAt2 == 'v') {
                                c = 11;
                            } else if (charAt2 == '/') {
                                i5 = i;
                                c = '/';
                            } else if (charAt2 == '0' && !isOctalEscape(str2, i, length)) {
                                i5 = i;
                                c = 0;
                            } else {
                                c = 65535;
                            }
                        }
                        i5 = i;
                    } else {
                        i5 = i;
                        c = '\'';
                    }
                    if (c == 65535) {
                        if (charAt2 == 'x') {
                            int i6 = i3 + 2;
                            i2 = i6;
                            while (i2 < i3 + 4 && i2 < length && (((r14 = str2.charAt(i2)) >= '0' && r14 <= '9') || ((r14 >= 'A' && r14 <= 'F') || (r14 >= 'a' && r14 <= 'f')))) {
                                i2++;
                            }
                            if (i2 - i6 >= 2) {
                                c2 = parseIntFromReference(str2, i6, i2, 16);
                            }
                            i3 = i;
                        } else {
                            if (charAt2 == 'u') {
                                int i7 = i3 + 2;
                                int i8 = i7;
                                while (i2 < i3 + 6 && i2 < length && (((r14 = str2.charAt(i2)) >= '0' && r14 <= '9') || ((r14 >= 'A' && r14 <= 'F') || (r14 >= 'a' && r14 <= 'f')))) {
                                    i8 = i2 + 1;
                                }
                                if (i2 - i7 >= 4) {
                                    c2 = parseIntFromReference(str2, i7, i2, 16);
                                }
                            } else if (charAt2 >= '0' && charAt2 <= '7') {
                                int i9 = i3 + 2;
                                while (i9 < i3 + 4 && i9 < length && (r7 = str2.charAt(i9)) >= '0' && r7 <= '7') {
                                    i9++;
                                }
                                int parseIntFromReference = parseIntFromReference(str2, i, i9, 8);
                                if (parseIntFromReference > 255) {
                                    i5 = i9 - 2;
                                    c2 = parseIntFromReference(str2, i, i9 - 1, 8);
                                } else {
                                    i5 = i9 - 1;
                                    c2 = parseIntFromReference;
                                }
                            } else if (!(charAt2 == '8' || charAt2 == '9' || charAt2 == 13 || charAt2 == 8232 || charAt2 == 8233)) {
                                c2 = charAt2;
                                i5 = i;
                            }
                            i3 = i;
                        }
                        i5 = i2 - 1;
                    } else {
                        c2 = c;
                    }
                }
                if (sb == null) {
                    sb = new StringBuilder(length + 5);
                }
                if (i3 - i4 > 0) {
                    sb.append(str2, i4, i3);
                }
                i4 = i5 + 1;
                if (c2 > 65535) {
                    sb.append(Character.toChars(c2));
                } else if (c2 != 65534) {
                    sb.append((char) c2);
                }
                i3 = i5;
            }
            i3++;
        }
        if (sb == null) {
            return str2;
        }
        if (length - i4 > 0) {
            sb.append(str2, i4, length);
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006e, code lost:
        r16 = r4;
        r4 = r3;
        r3 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0073, code lost:
        if (r5 != -1) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0086, code lost:
        if (r4 != 120) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0088, code lost:
        r5 = r17.read();
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008e, code lost:
        if (r5 < 0) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0090, code lost:
        if (r11 >= 2) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0092, code lost:
        if (r5 < 48) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0094, code lost:
        if (r5 <= 57) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0096, code lost:
        if (r5 < 65) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0098, code lost:
        if (r5 <= 70) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009a, code lost:
        if (r5 < 97) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x009c, code lost:
        if (r5 <= 102) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009f, code lost:
        r2[r11] = (char) r5;
        r5 = r17.read();
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a9, code lost:
        if (r11 >= 2) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00ab, code lost:
        r0.write(r3);
        r0.write(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b1, code lost:
        if (r9 >= r11) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b3, code lost:
        r0.write(r2[r9]);
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00bb, code lost:
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        r3 = r2[3];
        r7 = parseIntFromReference(r2, 0, 2, 16);
        r3 = r5;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00cb, code lost:
        if (r4 != 117) goto L_0x010e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00cd, code lost:
        r1 = r17.read();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d2, code lost:
        if (r1 < 0) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d5, code lost:
        if (r5 >= 4) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d7, code lost:
        if (r1 < 48) goto L_0x00db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00d9, code lost:
        if (r1 <= 57) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00db, code lost:
        if (r1 < 65) goto L_0x00df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00dd, code lost:
        if (r1 <= 70) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00df, code lost:
        if (r1 < 97) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e1, code lost:
        if (r1 <= 102) goto L_0x00e4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e4, code lost:
        r2[r5] = (char) r1;
        r1 = r17.read();
        r5 = r5 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ef, code lost:
        if (r5 >= 4) goto L_0x0104;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00f1, code lost:
        r0.write(r3);
        r0.write(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f7, code lost:
        if (r9 >= r5) goto L_0x0101;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00f9, code lost:
        r0.write(r2[r9]);
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0101, code lost:
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0104, code lost:
        r3 = r2[3];
        r7 = parseIntFromReference(r2, 0, 4, 16);
        r3 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x010f, code lost:
        if (r4 < 48) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0113, code lost:
        if (r4 > 55) goto L_0x014e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0115, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0116, code lost:
        if (r4 < 0) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0118, code lost:
        if (r3 >= 3) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x011a, code lost:
        if (r4 < 48) goto L_0x0129;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x011c, code lost:
        if (r4 <= 55) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x011f, code lost:
        r2[r3] = (char) r4;
        r4 = r17.read();
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0129, code lost:
        r1 = r3 - 1;
        r5 = r2[r1];
        r7 = parseIntFromReference(r2, 0, r3, 8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0133, code lost:
        if (r7 <= 255) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0135, code lost:
        r7 = parseIntFromReference(r2, 0, r1, 8);
        java.lang.System.arraycopy(r2, r3 - 2, r2, 0, 1);
        r3 = r4;
        r8 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0143, code lost:
        if (r7 != 0) goto L_0x0178;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0145, code lost:
        if (r3 <= 1) goto L_0x0178;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0147, code lost:
        java.lang.System.arraycopy(r2, 1, r2, 0, r1);
        r8 = r3 - 1;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0150, code lost:
        if (r4 == 56) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0152, code lost:
        if (r4 == 57) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0156, code lost:
        if (r4 == 13) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x015a, code lost:
        if (r4 == 8232) goto L_0x0168;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x015e, code lost:
        if (r4 != 8233) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0161, code lost:
        r3 = r17.read();
        r7 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0168, code lost:
        r0.write(r3);
        r0.write(r4);
        r3 = r17.read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0173, code lost:
        r3 = r4;
        r7 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void unescape(java.io.Reader r17, java.io.Writer r18) throws java.io.IOException {
        /*
            r0 = r18
            if (r17 != 0) goto L_0x0005
            return
        L_0x0005:
            r1 = 4
            char[] r2 = new char[r1]
            int r3 = r17.read()
        L_0x000c:
            if (r3 < 0) goto L_0x019c
            int r4 = r17.read()
            r5 = 92
            if (r3 != r5) goto L_0x0194
            if (r4 >= 0) goto L_0x001a
            goto L_0x0194
        L_0x001a:
            r7 = -1
            r9 = 0
            if (r3 != r5) goto L_0x0177
            r11 = 8
            switch(r4) {
                case 10: goto L_0x0069;
                case 34: goto L_0x0062;
                case 39: goto L_0x005b;
                case 47: goto L_0x0054;
                case 92: goto L_0x004f;
                case 98: goto L_0x0048;
                case 102: goto L_0x0041;
                case 110: goto L_0x003a;
                case 114: goto L_0x0033;
                case 116: goto L_0x002c;
                case 118: goto L_0x0025;
                default: goto L_0x0023;
            }
        L_0x0023:
            r5 = -1
            goto L_0x0073
        L_0x0025:
            r5 = 11
            int r3 = r17.read()
            goto L_0x006e
        L_0x002c:
            r5 = 9
            int r3 = r17.read()
            goto L_0x006e
        L_0x0033:
            int r3 = r17.read()
            r5 = 13
            goto L_0x006e
        L_0x003a:
            r5 = 10
            int r3 = r17.read()
            goto L_0x006e
        L_0x0041:
            r5 = 12
            int r3 = r17.read()
            goto L_0x006e
        L_0x0048:
            int r3 = r17.read()
            r5 = 8
            goto L_0x006e
        L_0x004f:
            int r3 = r17.read()
            goto L_0x006e
        L_0x0054:
            r5 = 47
            int r3 = r17.read()
            goto L_0x006e
        L_0x005b:
            r5 = 39
            int r3 = r17.read()
            goto L_0x006e
        L_0x0062:
            r5 = 34
            int r3 = r17.read()
            goto L_0x006e
        L_0x0069:
            int r3 = r17.read()
            r5 = -2
        L_0x006e:
            r16 = r4
            r4 = r3
            r3 = r16
        L_0x0073:
            if (r5 != r7) goto L_0x0173
            r5 = 120(0x78, float:1.68E-43)
            r7 = 102(0x66, float:1.43E-43)
            r12 = 70
            r13 = 97
            r14 = 65
            r15 = 16
            r6 = 3
            r10 = 57
            r8 = 48
            if (r4 != r5) goto L_0x00c9
            int r5 = r17.read()
            r11 = 0
        L_0x008d:
            r1 = 2
            if (r5 < 0) goto L_0x00a9
            if (r11 >= r1) goto L_0x00a9
            if (r5 < r8) goto L_0x0096
            if (r5 <= r10) goto L_0x009f
        L_0x0096:
            if (r5 < r14) goto L_0x009a
            if (r5 <= r12) goto L_0x009f
        L_0x009a:
            if (r5 < r13) goto L_0x00a9
            if (r5 <= r7) goto L_0x009f
            goto L_0x00a9
        L_0x009f:
            char r1 = (char) r5
            r2[r11] = r1
            int r5 = r17.read()
            int r11 = r11 + 1
            goto L_0x008d
        L_0x00a9:
            if (r11 >= r1) goto L_0x00be
            r0.write(r3)
            r0.write(r4)
        L_0x00b1:
            if (r9 >= r11) goto L_0x00bb
            char r1 = r2[r9]
            r0.write(r1)
            int r9 = r9 + 1
            goto L_0x00b1
        L_0x00bb:
            r3 = r5
            goto L_0x0199
        L_0x00be:
            char r3 = r2[r6]
            int r7 = parseIntFromReference((char[]) r2, (int) r9, (int) r1, (int) r15)
            r3 = r5
            r8 = 0
            r12 = 4
            goto L_0x017a
        L_0x00c9:
            r1 = 117(0x75, float:1.64E-43)
            if (r4 != r1) goto L_0x010e
            int r1 = r17.read()
            r5 = 0
        L_0x00d2:
            if (r1 < 0) goto L_0x00ee
            r11 = 4
            if (r5 >= r11) goto L_0x00ee
            if (r1 < r8) goto L_0x00db
            if (r1 <= r10) goto L_0x00e4
        L_0x00db:
            if (r1 < r14) goto L_0x00df
            if (r1 <= r12) goto L_0x00e4
        L_0x00df:
            if (r1 < r13) goto L_0x00ee
            if (r1 <= r7) goto L_0x00e4
            goto L_0x00ee
        L_0x00e4:
            char r1 = (char) r1
            r2[r5] = r1
            int r1 = r17.read()
            int r5 = r5 + 1
            goto L_0x00d2
        L_0x00ee:
            r7 = 4
            if (r5 >= r7) goto L_0x0104
            r0.write(r3)
            r0.write(r4)
        L_0x00f7:
            if (r9 >= r5) goto L_0x0101
            char r3 = r2[r9]
            r0.write(r3)
            int r9 = r9 + 1
            goto L_0x00f7
        L_0x0101:
            r3 = r1
            goto L_0x0199
        L_0x0104:
            char r3 = r2[r6]
            r12 = 4
            int r7 = parseIntFromReference((char[]) r2, (int) r9, (int) r12, (int) r15)
            r3 = r1
            goto L_0x0179
        L_0x010e:
            r12 = 4
            if (r4 < r8) goto L_0x014e
            r1 = 55
            if (r4 > r1) goto L_0x014e
            r3 = 0
        L_0x0116:
            if (r4 < 0) goto L_0x0129
            if (r3 >= r6) goto L_0x0129
            if (r4 < r8) goto L_0x0129
            if (r4 <= r1) goto L_0x011f
            goto L_0x0129
        L_0x011f:
            char r4 = (char) r4
            r2[r3] = r4
            int r4 = r17.read()
            int r3 = r3 + 1
            goto L_0x0116
        L_0x0129:
            int r1 = r3 + -1
            char r5 = r2[r1]
            int r7 = parseIntFromReference((char[]) r2, (int) r9, (int) r3, (int) r11)
            r5 = 255(0xff, float:3.57E-43)
            if (r7 <= r5) goto L_0x0142
            int r7 = parseIntFromReference((char[]) r2, (int) r9, (int) r1, (int) r11)
            int r3 = r3 + -2
            r5 = 1
            java.lang.System.arraycopy(r2, r3, r2, r9, r5)
            r3 = r4
            r8 = 1
            goto L_0x017a
        L_0x0142:
            r5 = 1
            if (r7 != 0) goto L_0x0178
            if (r3 <= r5) goto L_0x0178
            java.lang.System.arraycopy(r2, r5, r2, r9, r1)
            int r8 = r3 + -1
            r3 = r4
            goto L_0x017a
        L_0x014e:
            r1 = 56
            if (r4 == r1) goto L_0x0168
            if (r4 == r10) goto L_0x0168
            r1 = 13
            if (r4 == r1) goto L_0x0168
            r1 = 8232(0x2028, float:1.1535E-41)
            if (r4 == r1) goto L_0x0168
            r1 = 8233(0x2029, float:1.1537E-41)
            if (r4 != r1) goto L_0x0161
            goto L_0x0168
        L_0x0161:
            int r1 = r17.read()
            r3 = r1
            r7 = r4
            goto L_0x0179
        L_0x0168:
            r0.write(r3)
            r0.write(r4)
            int r3 = r17.read()
            goto L_0x0199
        L_0x0173:
            r12 = 4
            r3 = r4
            r7 = r5
            goto L_0x0179
        L_0x0177:
            r12 = 4
        L_0x0178:
            r3 = r4
        L_0x0179:
            r8 = 0
        L_0x017a:
            r1 = 65535(0xffff, float:9.1834E-41)
            if (r7 <= r1) goto L_0x0187
            char[] r1 = java.lang.Character.toChars(r7)
            r0.write(r1)
            goto L_0x018e
        L_0x0187:
            r1 = -2
            if (r7 == r1) goto L_0x018e
            char r1 = (char) r7
            r0.write(r1)
        L_0x018e:
            if (r8 <= 0) goto L_0x0199
            r0.write(r2, r9, r8)
            goto L_0x0199
        L_0x0194:
            r12 = 4
            r0.write(r3)
            r3 = r4
        L_0x0199:
            r1 = 4
            goto L_0x000c
        L_0x019c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.javascript.JavaScriptEscapeUtil.unescape(java.io.Reader, java.io.Writer):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v19, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v21, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v24, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v27, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v29, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v34, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v35, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v38, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v39, resolved type: char} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void unescape(char[] r16, int r17, int r18, java.io.Writer r19) throws java.io.IOException {
        /*
            r0 = r16
            r1 = r19
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            int r2 = r17 + r18
            r3 = r17
            r4 = r3
            r5 = r4
        L_0x000d:
            if (r3 >= r2) goto L_0x014e
            char r6 = r0[r3]
            r7 = 92
            if (r6 != r7) goto L_0x014a
            int r8 = r3 + 1
            if (r8 < r2) goto L_0x001b
            goto L_0x014a
        L_0x001b:
            r10 = -1
            if (r6 != r7) goto L_0x012c
            char r6 = r0[r8]
            r12 = 47
            r13 = 39
            r14 = 34
            r15 = 10
            r11 = 102(0x66, float:1.43E-43)
            r9 = 48
            if (r6 == r15) goto L_0x007c
            if (r6 == r14) goto L_0x0078
            if (r6 == r13) goto L_0x0074
            if (r6 == r7) goto L_0x0072
            r7 = 98
            if (r6 == r7) goto L_0x006e
            if (r6 == r11) goto L_0x006b
            r7 = 110(0x6e, float:1.54E-43)
            if (r6 == r7) goto L_0x0067
            r7 = 114(0x72, float:1.6E-43)
            if (r6 == r7) goto L_0x0063
            r7 = 116(0x74, float:1.63E-43)
            if (r6 == r7) goto L_0x0060
            r7 = 118(0x76, float:1.65E-43)
            if (r6 == r7) goto L_0x005d
            if (r6 == r12) goto L_0x0059
            if (r6 == r9) goto L_0x004f
            goto L_0x0057
        L_0x004f:
            boolean r7 = isOctalEscape((char[]) r0, (int) r8, (int) r2)
            if (r7 != 0) goto L_0x0057
            r7 = 0
            goto L_0x0072
        L_0x0057:
            r7 = -1
            goto L_0x007e
        L_0x0059:
            r5 = r8
            r7 = 47
            goto L_0x007e
        L_0x005d:
            r7 = 11
            goto L_0x0072
        L_0x0060:
            r7 = 9
            goto L_0x0072
        L_0x0063:
            r5 = r8
            r7 = 13
            goto L_0x007e
        L_0x0067:
            r5 = r8
            r7 = 10
            goto L_0x007e
        L_0x006b:
            r7 = 12
            goto L_0x0072
        L_0x006e:
            r5 = r8
            r7 = 8
            goto L_0x007e
        L_0x0072:
            r5 = r8
            goto L_0x007e
        L_0x0074:
            r5 = r8
            r7 = 39
            goto L_0x007e
        L_0x0078:
            r5 = r8
            r7 = 34
            goto L_0x007e
        L_0x007c:
            r5 = r8
            r7 = -2
        L_0x007e:
            if (r7 != r10) goto L_0x012b
            r7 = 120(0x78, float:1.68E-43)
            r10 = 70
            r12 = 97
            r13 = 65
            r15 = 57
            if (r6 != r7) goto L_0x00b8
            int r6 = r3 + 2
            r7 = r6
        L_0x008f:
            int r14 = r3 + 4
            if (r7 >= r14) goto L_0x00a7
            if (r7 >= r2) goto L_0x00a7
            char r14 = r0[r7]
            if (r14 < r9) goto L_0x009b
            if (r14 <= r15) goto L_0x00a4
        L_0x009b:
            if (r14 < r13) goto L_0x009f
            if (r14 <= r10) goto L_0x00a4
        L_0x009f:
            if (r14 < r12) goto L_0x00a7
            if (r14 <= r11) goto L_0x00a4
            goto L_0x00a7
        L_0x00a4:
            int r7 = r7 + 1
            goto L_0x008f
        L_0x00a7:
            int r9 = r7 - r6
            r10 = 2
            if (r9 >= r10) goto L_0x00ae
            goto L_0x0129
        L_0x00ae:
            r5 = 16
            int r10 = parseIntFromReference((char[]) r0, (int) r6, (int) r7, (int) r5)
        L_0x00b4:
            int r5 = r7 + -1
            goto L_0x012c
        L_0x00b8:
            r7 = 117(0x75, float:1.64E-43)
            if (r6 != r7) goto L_0x00e4
            int r6 = r3 + 2
            r7 = r6
        L_0x00bf:
            int r14 = r3 + 6
            if (r7 >= r14) goto L_0x00d7
            if (r7 >= r2) goto L_0x00d7
            char r14 = r0[r7]
            if (r14 < r9) goto L_0x00cb
            if (r14 <= r15) goto L_0x00d4
        L_0x00cb:
            if (r14 < r13) goto L_0x00cf
            if (r14 <= r10) goto L_0x00d4
        L_0x00cf:
            if (r14 < r12) goto L_0x00d7
            if (r14 <= r11) goto L_0x00d4
            goto L_0x00d7
        L_0x00d4:
            int r7 = r7 + 1
            goto L_0x00bf
        L_0x00d7:
            int r9 = r7 - r6
            r10 = 4
            if (r9 >= r10) goto L_0x00dd
            goto L_0x0129
        L_0x00dd:
            r5 = 16
            int r10 = parseIntFromReference((char[]) r0, (int) r6, (int) r7, (int) r5)
            goto L_0x00b4
        L_0x00e4:
            if (r6 < r9) goto L_0x0113
            r7 = 55
            if (r6 > r7) goto L_0x0113
            int r5 = r3 + 2
        L_0x00ec:
            int r6 = r3 + 4
            if (r5 >= r6) goto L_0x00fc
            if (r5 >= r2) goto L_0x00fc
            char r6 = r0[r5]
            if (r6 < r9) goto L_0x00fc
            if (r6 <= r7) goto L_0x00f9
            goto L_0x00fc
        L_0x00f9:
            int r5 = r5 + 1
            goto L_0x00ec
        L_0x00fc:
            r6 = 8
            int r7 = parseIntFromReference((char[]) r0, (int) r8, (int) r5, (int) r6)
            r9 = 255(0xff, float:3.57E-43)
            if (r7 <= r9) goto L_0x0110
            int r7 = r5 + -1
            int r6 = parseIntFromReference((char[]) r0, (int) r8, (int) r7, (int) r6)
            int r5 = r5 + -2
            r10 = r6
            goto L_0x012c
        L_0x0110:
            int r5 = r5 + -1
            goto L_0x012b
        L_0x0113:
            r7 = 56
            if (r6 == r7) goto L_0x0129
            if (r6 == r15) goto L_0x0129
            r7 = 13
            if (r6 == r7) goto L_0x0129
            r7 = 8232(0x2028, float:1.1535E-41)
            if (r6 == r7) goto L_0x0129
            r7 = 8233(0x2029, float:1.1537E-41)
            if (r6 != r7) goto L_0x0126
            goto L_0x0129
        L_0x0126:
            r10 = r6
            r5 = r8
            goto L_0x012c
        L_0x0129:
            r3 = r8
            goto L_0x014a
        L_0x012b:
            r10 = r7
        L_0x012c:
            int r3 = r3 - r4
            if (r3 <= 0) goto L_0x0132
            r1.write(r0, r4, r3)
        L_0x0132:
            int r3 = r5 + 1
            r4 = 65535(0xffff, float:9.1834E-41)
            if (r10 <= r4) goto L_0x0141
            char[] r4 = java.lang.Character.toChars(r10)
            r1.write(r4)
            goto L_0x0148
        L_0x0141:
            r4 = -2
            if (r10 == r4) goto L_0x0148
            char r4 = (char) r10
            r1.write(r4)
        L_0x0148:
            r4 = r3
            r3 = r5
        L_0x014a:
            int r3 = r3 + 1
            goto L_0x000d
        L_0x014e:
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x0154
            r1.write(r0, r4, r2)
        L_0x0154:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.javascript.JavaScriptEscapeUtil.unescape(char[], int, int, java.io.Writer):void");
    }

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
