package org.unbescape.css;

import java.io.IOException;
import java.io.Writer;

final class CssUnescapeUtil {
    private static final char ESCAPE_PREFIX = '\\';
    private static char[] HEXA_CHARS_LOWER = "0123456789abcdef".toCharArray();
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();

    private CssUnescapeUtil() {
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

    static String unescape(String str) {
        int i;
        char c;
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
                    char charAt2 = str.charAt(i);
                    if (charAt2 != 10) {
                        switch (charAt2) {
                            case ' ':
                            case '!':
                            case '\"':
                            case '#':
                            case '$':
                            case '%':
                            case '&':
                            case '\'':
                            case '(':
                            case ')':
                            case '*':
                            case '+':
                            case ',':
                            case '-':
                            case '.':
                            case '/':
                                c = charAt2;
                                i4 = i;
                                break;
                            default:
                                switch (charAt2) {
                                    case ':':
                                    case ';':
                                    case '<':
                                    case '=':
                                    case '>':
                                    case '?':
                                    case '@':
                                        break;
                                    default:
                                        switch (charAt2) {
                                            case '[':
                                            case '\\':
                                            case ']':
                                            case '^':
                                            case '_':
                                            case '`':
                                                break;
                                            default:
                                                switch (charAt2) {
                                                    case '{':
                                                    case '|':
                                                    case '}':
                                                    case '~':
                                                        break;
                                                    default:
                                                        c = 65535;
                                                        break;
                                                }
                                        }
                                }
                                c = charAt2;
                                i4 = i;
                                break;
                        }
                    } else {
                        i4 = i;
                        c = 65534;
                    }
                    if (c != 65535) {
                        i5 = c;
                    } else if ((charAt2 >= '0' && charAt2 <= '9') || ((charAt2 >= 'A' && charAt2 <= 'F') || (charAt2 >= 'a' && charAt2 <= 'f'))) {
                        int i6 = i2 + 2;
                        while (i6 < i2 + 7 && i6 < length && (((r5 = str.charAt(i6)) >= '0' && r5 <= '9') || ((r5 >= 'A' && r5 <= 'F') || (r5 >= 'a' && r5 <= 'f')))) {
                            i6++;
                        }
                        i5 = parseIntFromReference(str, i, i6, 16);
                        int i7 = i6 - 1;
                        if (i6 < length && str.charAt(i6) == ' ') {
                            i7++;
                        }
                        i4 = i7;
                    } else if (charAt2 == 13 || charAt2 == 12) {
                        i2 = i;
                    } else {
                        i5 = charAt2;
                        i4 = i;
                    }
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
                } else if (i5 != -2) {
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

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void unescape(java.io.Reader r13, java.io.Writer r14) throws java.io.IOException {
        /*
            if (r13 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 6
            char[] r1 = new char[r0]
            int r2 = r13.read()
        L_0x000a:
            if (r2 < 0) goto L_0x00b8
            int r3 = r13.read()
            r4 = 92
            if (r2 != r4) goto L_0x00b2
            if (r3 >= 0) goto L_0x0018
            goto L_0x00b2
        L_0x0018:
            r5 = -2
            r6 = -1
            if (r2 != r4) goto L_0x009b
            r4 = 10
            if (r3 == r4) goto L_0x0037
            switch(r3) {
                case 32: goto L_0x0031;
                case 33: goto L_0x0031;
                case 34: goto L_0x0031;
                case 35: goto L_0x0031;
                case 36: goto L_0x0031;
                case 37: goto L_0x0031;
                case 38: goto L_0x0031;
                case 39: goto L_0x0031;
                case 40: goto L_0x0031;
                case 41: goto L_0x0031;
                case 42: goto L_0x0031;
                case 43: goto L_0x0031;
                case 44: goto L_0x0031;
                case 45: goto L_0x0031;
                case 46: goto L_0x0031;
                case 47: goto L_0x0031;
                default: goto L_0x0023;
            }
        L_0x0023:
            switch(r3) {
                case 58: goto L_0x0031;
                case 59: goto L_0x0031;
                case 60: goto L_0x0031;
                case 61: goto L_0x0031;
                case 62: goto L_0x0031;
                case 63: goto L_0x0031;
                case 64: goto L_0x0031;
                default: goto L_0x0026;
            }
        L_0x0026:
            switch(r3) {
                case 91: goto L_0x0031;
                case 92: goto L_0x0031;
                case 93: goto L_0x0031;
                case 94: goto L_0x0031;
                case 95: goto L_0x0031;
                case 96: goto L_0x0031;
                default: goto L_0x0029;
            }
        L_0x0029:
            switch(r3) {
                case 123: goto L_0x0031;
                case 124: goto L_0x0031;
                case 125: goto L_0x0031;
                case 126: goto L_0x0031;
                default: goto L_0x002c;
            }
        L_0x002c:
            r4 = -1
            r12 = r3
            r3 = r2
            r2 = r12
            goto L_0x003c
        L_0x0031:
            int r2 = r13.read()
            r4 = r3
            goto L_0x003c
        L_0x0037:
            int r2 = r13.read()
            r4 = -2
        L_0x003c:
            if (r4 != r6) goto L_0x0099
            r4 = 102(0x66, float:1.43E-43)
            r6 = 70
            r7 = 97
            r8 = 57
            r9 = 65
            r10 = 48
            if (r2 < r10) goto L_0x004e
            if (r2 <= r8) goto L_0x0056
        L_0x004e:
            if (r2 < r9) goto L_0x0052
            if (r2 <= r6) goto L_0x0056
        L_0x0052:
            if (r2 < r7) goto L_0x0085
            if (r2 > r4) goto L_0x0085
        L_0x0056:
            r3 = 0
            r11 = 0
        L_0x0058:
            if (r2 < 0) goto L_0x0073
            if (r11 >= r0) goto L_0x0073
            if (r2 < r10) goto L_0x0060
            if (r2 <= r8) goto L_0x0069
        L_0x0060:
            if (r2 < r9) goto L_0x0064
            if (r2 <= r6) goto L_0x0069
        L_0x0064:
            if (r2 < r7) goto L_0x0073
            if (r2 <= r4) goto L_0x0069
            goto L_0x0073
        L_0x0069:
            char r2 = (char) r2
            r1[r11] = r2
            int r2 = r13.read()
            int r11 = r11 + 1
            goto L_0x0058
        L_0x0073:
            r4 = 5
            char r4 = r1[r4]
            r4 = 16
            int r6 = parseIntFromReference((char[]) r1, (int) r3, (int) r11, (int) r4)
            r3 = 32
            if (r2 != r3) goto L_0x009c
            int r2 = r13.read()
            goto L_0x009c
        L_0x0085:
            r4 = 13
            if (r2 == r4) goto L_0x0094
            r4 = 12
            if (r2 != r4) goto L_0x008e
            goto L_0x0094
        L_0x008e:
            int r3 = r13.read()
            r6 = r2
            goto L_0x009b
        L_0x0094:
            r14.write(r3)
            goto L_0x000a
        L_0x0099:
            r6 = r4
            goto L_0x009c
        L_0x009b:
            r2 = r3
        L_0x009c:
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r6 <= r3) goto L_0x00aa
            char[] r3 = java.lang.Character.toChars(r6)
            r14.write(r3)
            goto L_0x000a
        L_0x00aa:
            if (r6 == r5) goto L_0x000a
            char r3 = (char) r6
            r14.write(r3)
            goto L_0x000a
        L_0x00b2:
            r14.write(r2)
            r2 = r3
            goto L_0x000a
        L_0x00b8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.css.CssUnescapeUtil.unescape(java.io.Reader, java.io.Writer):void");
    }

    static void unescape(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3;
        char c;
        if (cArr != null) {
            int i4 = i2 + i;
            int i5 = i;
            int i6 = i5;
            while (i < i4) {
                char c2 = cArr[i];
                if (c2 == '\\' && (i3 = i + 1) < i4) {
                    int i7 = -1;
                    if (c2 == '\\') {
                        char c3 = cArr[i3];
                        if (c3 != 10) {
                            switch (c3) {
                                case ' ':
                                case '!':
                                case '\"':
                                case '#':
                                case '$':
                                case '%':
                                case '&':
                                case '\'':
                                case '(':
                                case ')':
                                case '*':
                                case '+':
                                case ',':
                                case '-':
                                case '.':
                                case '/':
                                    c = c3;
                                    i6 = i3;
                                    break;
                                default:
                                    switch (c3) {
                                        case ':':
                                        case ';':
                                        case '<':
                                        case '=':
                                        case '>':
                                        case '?':
                                        case '@':
                                            break;
                                        default:
                                            switch (c3) {
                                                case '[':
                                                case '\\':
                                                case ']':
                                                case '^':
                                                case '_':
                                                case '`':
                                                    break;
                                                default:
                                                    switch (c3) {
                                                        case '{':
                                                        case '|':
                                                        case '}':
                                                        case '~':
                                                            break;
                                                        default:
                                                            c = 65535;
                                                            break;
                                                    }
                                            }
                                    }
                                    c = c3;
                                    i6 = i3;
                                    break;
                            }
                        } else {
                            i6 = i3;
                            c = 65534;
                        }
                        if (c != 65535) {
                            i7 = c;
                        } else if ((c3 >= '0' && c3 <= '9') || ((c3 >= 'A' && c3 <= 'F') || (c3 >= 'a' && c3 <= 'f'))) {
                            int i8 = i + 2;
                            while (i8 < i + 7 && i8 < i4 && (((r2 = cArr[i8]) >= '0' && r2 <= '9') || ((r2 >= 'A' && r2 <= 'F') || (r2 >= 'a' && r2 <= 'f')))) {
                                i8++;
                            }
                            i7 = parseIntFromReference(cArr, i3, i8, 16);
                            int i9 = i8 - 1;
                            if (i8 < i4 && cArr[i8] == ' ') {
                                i9++;
                            }
                            i6 = i9;
                        } else if (c3 == 13 || c3 == 12) {
                            i = i3;
                        } else {
                            i7 = c3;
                            i6 = i3;
                        }
                    }
                    int i10 = i - i5;
                    if (i10 > 0) {
                        writer.write(cArr, i5, i10);
                    }
                    int i11 = i6 + 1;
                    if (i7 > 65535) {
                        writer.write(Character.toChars(i7));
                    } else if (i7 != -2) {
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
}
