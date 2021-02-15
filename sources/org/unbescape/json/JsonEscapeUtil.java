package org.unbescape.json;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import net.bytebuddy.asm.Advice;

final class JsonEscapeUtil {
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
        bArr2[8] = 1;
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[34] = 1;
        bArr2[92] = 1;
        bArr2[47] = 1;
        bArr2[38] = 1;
        for (char c5 = 0; c5 <= 31; c5 = (char) (c5 + 1)) {
            ESCAPE_LEVELS[c5] = 1;
        }
        for (char c6 = Ascii.MAX; c6 <= 159; c6 = (char) (c6 + 1)) {
            ESCAPE_LEVELS[c6] = 1;
        }
    }

    private JsonEscapeUtil() {
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

    static String escape(String str, JsonEscapeType jsonEscapeType, JsonEscapeLevel jsonEscapeLevel) {
        char c;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int escapeLevel = jsonEscapeLevel.getEscapeLevel();
        boolean useSECs = jsonEscapeType.getUseSECs();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int codePointAt = Character.codePointAt(str, i);
            if ((codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt]) && (codePointAt != 47 || escapeLevel >= 3 || (i != 0 && str.charAt(i - 1) == '<'))) {
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
                    if (useSECs && codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
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

    static void escape(Reader reader, Writer writer, JsonEscapeType jsonEscapeType, JsonEscapeLevel jsonEscapeLevel) throws IOException {
        char c;
        if (reader != null) {
            int escapeLevel = jsonEscapeLevel.getEscapeLevel();
            boolean useSECs = jsonEscapeType.getUseSECs();
            int read = reader.read();
            int i = -1;
            while (read >= 0) {
                int read2 = reader.read();
                int codePointAt = codePointAt((char) read, (char) read2);
                if (codePointAt <= 159 && escapeLevel < ESCAPE_LEVELS[codePointAt]) {
                    writer.write(read);
                } else if (codePointAt != 47 || escapeLevel >= 3 || i == 60) {
                    if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
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

    static void escape(char[] cArr, int i, int i2, Writer writer, JsonEscapeType jsonEscapeType, JsonEscapeLevel jsonEscapeLevel) throws IOException {
        char c;
        if (cArr != null && cArr.length != 0) {
            int escapeLevel = jsonEscapeLevel.getEscapeLevel();
            boolean useSECs = jsonEscapeType.getUseSECs();
            int i3 = i2 + i;
            int i4 = i;
            int i5 = i4;
            while (i4 < i3) {
                int codePointAt = Character.codePointAt(cArr, i4);
                if ((codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt]) && (codePointAt != 47 || escapeLevel >= 3 || (i4 != i && cArr[i4 - 1] == '<'))) {
                    if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
                        int i6 = i4 - i5;
                        if (i6 > 0) {
                            writer.write(cArr, i5, i6);
                        }
                        if (Character.charCount(codePointAt) > 1) {
                            i4++;
                        }
                        i5 = i4 + 1;
                        if (useSECs && codePointAt < SEC_CHARS_LEN && (c = SEC_CHARS[codePointAt]) != SEC_CHARS_NO_SEC) {
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
                        i4++;
                    }
                }
                i4++;
            }
            int i7 = i3 - i5;
            if (i7 > 0) {
                writer.write(cArr, i5, i7);
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

    static String unescape(String str) {
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
            int i5 = 92;
            if (charAt == '\\' && (i = i2 + 1) < length) {
                int i6 = -1;
                if (charAt == '\\') {
                    char charAt2 = str.charAt(i);
                    if (charAt2 == '\"') {
                        i4 = i;
                        i5 = 34;
                    } else if (charAt2 != '/') {
                        if (charAt2 != '\\') {
                            if (charAt2 == 'b') {
                                i5 = 8;
                            } else if (charAt2 == 'f') {
                                i5 = 12;
                            } else if (charAt2 == 'n') {
                                i5 = 10;
                            } else if (charAt2 == 'r') {
                                i5 = 13;
                            } else if (charAt2 != 't') {
                                i5 = -1;
                            } else {
                                i5 = 9;
                            }
                        }
                        i4 = i;
                    } else {
                        i4 = i;
                        i5 = 47;
                    }
                    if (i5 == -1) {
                        if (charAt2 == 'u') {
                            int i7 = i2 + 2;
                            int i8 = i7;
                            while (i8 < i2 + 6 && i8 < length && (((r8 = str.charAt(i8)) >= '0' && r8 <= '9') || ((r8 >= 'A' && r8 <= 'F') || (r8 >= 'a' && r8 <= 'f')))) {
                                i8++;
                            }
                            if (i8 - i7 >= 4) {
                                i6 = parseIntFromReference(str, i7, i8, 16);
                                i4 = i8 - 1;
                            }
                        }
                        i2 = i;
                    } else {
                        i6 = i5;
                    }
                }
                if (sb == null) {
                    sb = new StringBuilder(length + 5);
                }
                if (i2 - i3 > 0) {
                    sb.append(str, i3, i2);
                }
                int i9 = i4 + 1;
                if (i6 > 65535) {
                    sb.append(Character.toChars(i6));
                } else {
                    sb.append((char) i6);
                }
                i3 = i9;
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

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00cf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void unescape(java.io.Reader r10, java.io.Writer r11) throws java.io.IOException {
        /*
            if (r10 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 4
            int[] r1 = new int[r0]
            int r2 = r10.read()
        L_0x000a:
            if (r2 < 0) goto L_0x00ed
            int r3 = r10.read()
            r4 = 92
            if (r2 != r4) goto L_0x00e7
            if (r3 >= 0) goto L_0x0018
            goto L_0x00e7
        L_0x0018:
            r5 = -1
            if (r2 != r4) goto L_0x00d2
            r6 = 102(0x66, float:1.43E-43)
            r7 = 47
            r8 = 34
            if (r3 == r8) goto L_0x006a
            if (r3 == r7) goto L_0x0063
            if (r3 == r4) goto L_0x005e
            r4 = 98
            if (r3 == r4) goto L_0x0057
            if (r3 == r6) goto L_0x0050
            r4 = 110(0x6e, float:1.54E-43)
            if (r3 == r4) goto L_0x0049
            r4 = 114(0x72, float:1.6E-43)
            if (r3 == r4) goto L_0x0042
            r4 = 116(0x74, float:1.63E-43)
            if (r3 == r4) goto L_0x003b
            r4 = -1
            goto L_0x0073
        L_0x003b:
            r4 = 9
            int r2 = r10.read()
            goto L_0x0070
        L_0x0042:
            r4 = 13
            int r2 = r10.read()
            goto L_0x0070
        L_0x0049:
            r4 = 10
            int r2 = r10.read()
            goto L_0x0070
        L_0x0050:
            r4 = 12
            int r2 = r10.read()
            goto L_0x0070
        L_0x0057:
            r4 = 8
            int r2 = r10.read()
            goto L_0x0070
        L_0x005e:
            int r2 = r10.read()
            goto L_0x0070
        L_0x0063:
            int r2 = r10.read()
            r4 = 47
            goto L_0x0070
        L_0x006a:
            int r2 = r10.read()
            r4 = 34
        L_0x0070:
            r9 = r3
            r3 = r2
            r2 = r9
        L_0x0073:
            if (r4 != r5) goto L_0x00cf
            r4 = 117(0x75, float:1.64E-43)
            if (r3 != r4) goto L_0x00c3
            int r4 = r10.read()
            r5 = 0
            r7 = 0
        L_0x007f:
            if (r4 < 0) goto L_0x00a3
            if (r7 >= r0) goto L_0x00a3
            r8 = 48
            if (r4 < r8) goto L_0x008b
            r8 = 57
            if (r4 <= r8) goto L_0x009a
        L_0x008b:
            r8 = 65
            if (r4 < r8) goto L_0x0093
            r8 = 70
            if (r4 <= r8) goto L_0x009a
        L_0x0093:
            r8 = 97
            if (r4 < r8) goto L_0x00a3
            if (r4 <= r6) goto L_0x009a
            goto L_0x00a3
        L_0x009a:
            r1[r7] = r4
            int r4 = r10.read()
            int r7 = r7 + 1
            goto L_0x007f
        L_0x00a3:
            if (r7 >= r0) goto L_0x00b8
            r11.write(r2)
            r11.write(r3)
        L_0x00ab:
            if (r5 >= r7) goto L_0x00b5
            r2 = r1[r5]
            r11.write(r2)
            int r5 = r5 + 1
            goto L_0x00ab
        L_0x00b5:
            r2 = r4
            goto L_0x000a
        L_0x00b8:
            r2 = 3
            r2 = r1[r2]
            r2 = 16
            int r5 = parseIntFromReference((int[]) r1, (int) r5, (int) r0, (int) r2)
            r2 = r4
            goto L_0x00d3
        L_0x00c3:
            r11.write(r2)
            r11.write(r3)
            int r2 = r10.read()
            goto L_0x000a
        L_0x00cf:
            r2 = r3
            r5 = r4
            goto L_0x00d3
        L_0x00d2:
            r2 = r3
        L_0x00d3:
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r5 <= r3) goto L_0x00e1
            char[] r3 = java.lang.Character.toChars(r5)
            r11.write(r3)
            goto L_0x000a
        L_0x00e1:
            char r3 = (char) r5
            r11.write(r3)
            goto L_0x000a
        L_0x00e7:
            r11.write(r2)
            r2 = r3
            goto L_0x000a
        L_0x00ed:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.json.JsonEscapeUtil.unescape(java.io.Reader, java.io.Writer):void");
    }

    static void unescape(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3;
        if (cArr != null) {
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
                        } else if (c2 != '/') {
                            if (c2 != '\\') {
                                if (c2 == 'b') {
                                    i7 = 8;
                                } else if (c2 == 'f') {
                                    i7 = 12;
                                } else if (c2 == 'n') {
                                    i7 = 10;
                                } else if (c2 == 'r') {
                                    i7 = 13;
                                } else if (c2 != 't') {
                                    i7 = -1;
                                } else {
                                    i7 = 9;
                                }
                            }
                            i6 = i3;
                        } else {
                            i6 = i3;
                            i7 = 47;
                        }
                        if (i7 == -1) {
                            if (c2 == 'u') {
                                int i9 = i + 2;
                                int i10 = i9;
                                while (i10 < i + 6 && i10 < i4 && (((r5 = cArr[i10]) >= '0' && r5 <= '9') || ((r5 >= 'A' && r5 <= 'F') || (r5 >= 'a' && r5 <= 'f')))) {
                                    i10++;
                                }
                                if (i10 - i9 >= 4) {
                                    i8 = parseIntFromReference(cArr, i9, i10, 16);
                                    i6 = i10 - 1;
                                }
                            }
                            i = i3;
                        } else {
                            i8 = i7;
                        }
                    }
                    int i11 = i - i5;
                    if (i11 > 0) {
                        writer.write(cArr, i5, i11);
                    }
                    int i12 = i6 + 1;
                    if (i8 > 65535) {
                        writer.write(Character.toChars(i8));
                    } else {
                        writer.write((char) i8);
                    }
                    i5 = i12;
                    i = i6;
                }
                i++;
            }
            int i13 = i4 - i5;
            if (i13 > 0) {
                writer.write(cArr, i5, i13);
            }
        }
    }

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
