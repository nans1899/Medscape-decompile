package org.unbescape.html;

import com.comscore.streaming.WindowState;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import okio.Utf8;

final class HtmlEscapeUtil {
    private static char[] HEXA_CHARS_LOWER = "0123456789abcdef".toCharArray();
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();
    private static final char[] REFERENCE_DECIMAL_PREFIX = "&#".toCharArray();
    private static final char[] REFERENCE_HEXA_PREFIX = "&#x".toCharArray();
    private static final char REFERENCE_HEXA_PREFIX3_LOWER = 'x';
    private static final char REFERENCE_HEXA_PREFIX3_UPPER = 'X';
    private static final char REFERENCE_NUMERIC_PREFIX2 = '#';
    private static final char REFERENCE_PREFIX = '&';
    private static final char REFERENCE_SUFFIX = ';';

    static int translateIllFormedCodepoint(int i) {
        if (i == 0) {
            return Utf8.REPLACEMENT_CODE_POINT;
        }
        if (i == 128) {
            return 8364;
        }
        if (i == 142) {
            return 381;
        }
        if (i == 158) {
            return 382;
        }
        if (i == 159) {
            return 376;
        }
        switch (i) {
            case 130:
                return 8218;
            case 131:
                return WindowState.MINIMIZED;
            case 132:
                return 8222;
            case 133:
                return 8230;
            case 134:
                return 8224;
            case 135:
                return 8225;
            case 136:
                return 710;
            case 137:
                return 8240;
            case 138:
                return 352;
            case 139:
                return 8249;
            case 140:
                return 338;
            default:
                switch (i) {
                    case 145:
                        return 8216;
                    case 146:
                        return 8217;
                    case 147:
                        return 8220;
                    case 148:
                        return 8221;
                    case 149:
                        return 8226;
                    case 150:
                        return 8211;
                    case 151:
                        return 8212;
                    case 152:
                        return 732;
                    case 153:
                        return 8482;
                    case 154:
                        return 353;
                    case 155:
                        return 8250;
                    case 156:
                        return 339;
                    default:
                        return ((i < 55296 || i > 57343) && i <= 1114111) ? i : Utf8.REPLACEMENT_CODE_POINT;
                }
        }
    }

    private HtmlEscapeUtil() {
    }

    static String escape(String str, HtmlEscapeType htmlEscapeType, HtmlEscapeLevel htmlEscapeLevel) {
        Short sh;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int escapeLevel = htmlEscapeLevel.getEscapeLevel();
        boolean useHtml5 = htmlEscapeType.getUseHtml5();
        boolean useNCRs = htmlEscapeType.getUseNCRs();
        boolean useHexa = htmlEscapeType.getUseHexa();
        HtmlEscapeSymbols htmlEscapeSymbols = useHtml5 ? HtmlEscapeSymbols.HTML5_SYMBOLS : HtmlEscapeSymbols.HTML4_SYMBOLS;
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if ((charAt > 127 || escapeLevel >= htmlEscapeSymbols.ESCAPE_LEVELS[charAt]) && (charAt <= 127 || escapeLevel >= htmlEscapeSymbols.ESCAPE_LEVELS[128])) {
                int codePointAt = Character.codePointAt(str, i);
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
                if (useNCRs) {
                    if (codePointAt < 12287) {
                        short s = htmlEscapeSymbols.NCRS_BY_CODEPOINT[codePointAt];
                        if (s != 0) {
                            sb.append(htmlEscapeSymbols.SORTED_NCRS[s]);
                        }
                    } else if (!(htmlEscapeSymbols.NCRS_BY_CODEPOINT_OVERFLOW == null || (sh = htmlEscapeSymbols.NCRS_BY_CODEPOINT_OVERFLOW.get(Integer.valueOf(codePointAt))) == null)) {
                        sb.append(htmlEscapeSymbols.SORTED_NCRS[sh.shortValue()]);
                    }
                }
                if (useHexa) {
                    sb.append(REFERENCE_HEXA_PREFIX);
                    sb.append(Integer.toHexString(codePointAt));
                } else {
                    sb.append(REFERENCE_DECIMAL_PREFIX);
                    sb.append(String.valueOf(codePointAt));
                }
                sb.append(';');
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

    static void escape(Reader reader, Writer writer, HtmlEscapeType htmlEscapeType, HtmlEscapeLevel htmlEscapeLevel) throws IOException {
        Short sh;
        if (reader != null) {
            int escapeLevel = htmlEscapeLevel.getEscapeLevel();
            boolean useHtml5 = htmlEscapeType.getUseHtml5();
            boolean useNCRs = htmlEscapeType.getUseNCRs();
            boolean useHexa = htmlEscapeType.getUseHexa();
            HtmlEscapeSymbols htmlEscapeSymbols = useHtml5 ? HtmlEscapeSymbols.HTML5_SYMBOLS : HtmlEscapeSymbols.HTML4_SYMBOLS;
            int read = reader.read();
            while (read >= 0) {
                int read2 = reader.read();
                if (read <= 127 && escapeLevel < htmlEscapeSymbols.ESCAPE_LEVELS[read]) {
                    writer.write(read);
                } else if (read <= 127 || escapeLevel >= htmlEscapeSymbols.ESCAPE_LEVELS[128]) {
                    int codePointAt = codePointAt((char) read, (char) read2);
                    if (Character.charCount(codePointAt) > 1) {
                        read2 = reader.read();
                    }
                    if (useNCRs) {
                        if (codePointAt < 12287) {
                            short s = htmlEscapeSymbols.NCRS_BY_CODEPOINT[codePointAt];
                            if (s != 0) {
                                writer.write(htmlEscapeSymbols.SORTED_NCRS[s]);
                            }
                        } else if (!(htmlEscapeSymbols.NCRS_BY_CODEPOINT_OVERFLOW == null || (sh = htmlEscapeSymbols.NCRS_BY_CODEPOINT_OVERFLOW.get(Integer.valueOf(codePointAt))) == null)) {
                            writer.write(htmlEscapeSymbols.SORTED_NCRS[sh.shortValue()]);
                        }
                    }
                    if (useHexa) {
                        writer.write(REFERENCE_HEXA_PREFIX);
                        writer.write(Integer.toHexString(codePointAt));
                    } else {
                        writer.write(REFERENCE_DECIMAL_PREFIX);
                        writer.write(String.valueOf(codePointAt));
                    }
                    writer.write(59);
                } else {
                    writer.write(read);
                }
                read = read2;
            }
        }
    }

    static void escape(char[] cArr, int i, int i2, Writer writer, HtmlEscapeType htmlEscapeType, HtmlEscapeLevel htmlEscapeLevel) throws IOException {
        Short sh;
        if (cArr != null && cArr.length != 0) {
            int escapeLevel = htmlEscapeLevel.getEscapeLevel();
            boolean useHtml5 = htmlEscapeType.getUseHtml5();
            boolean useNCRs = htmlEscapeType.getUseNCRs();
            boolean useHexa = htmlEscapeType.getUseHexa();
            HtmlEscapeSymbols htmlEscapeSymbols = useHtml5 ? HtmlEscapeSymbols.HTML5_SYMBOLS : HtmlEscapeSymbols.HTML4_SYMBOLS;
            int i3 = i2 + i;
            int i4 = i;
            while (i < i3) {
                char c = cArr[i];
                if ((c > 127 || escapeLevel >= htmlEscapeSymbols.ESCAPE_LEVELS[c]) && (c <= 127 || escapeLevel >= htmlEscapeSymbols.ESCAPE_LEVELS[128])) {
                    int codePointAt = Character.codePointAt(cArr, i);
                    int i5 = i - i4;
                    if (i5 > 0) {
                        writer.write(cArr, i4, i5);
                    }
                    if (Character.charCount(codePointAt) > 1) {
                        i++;
                    }
                    i4 = i + 1;
                    if (useNCRs) {
                        if (codePointAt < 12287) {
                            short s = htmlEscapeSymbols.NCRS_BY_CODEPOINT[codePointAt];
                            if (s != 0) {
                                writer.write(htmlEscapeSymbols.SORTED_NCRS[s]);
                            }
                        } else if (!(htmlEscapeSymbols.NCRS_BY_CODEPOINT_OVERFLOW == null || (sh = htmlEscapeSymbols.NCRS_BY_CODEPOINT_OVERFLOW.get(Integer.valueOf(codePointAt))) == null)) {
                            writer.write(htmlEscapeSymbols.SORTED_NCRS[sh.shortValue()]);
                        }
                    }
                    if (useHexa) {
                        writer.write(REFERENCE_HEXA_PREFIX);
                        writer.write(Integer.toHexString(codePointAt));
                    } else {
                        writer.write(REFERENCE_DECIMAL_PREFIX);
                        writer.write(String.valueOf(codePointAt));
                    }
                    writer.write(59);
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
            int i7 = i4 * i3;
            if (i7 < 0 || (i4 = i7 + i5) < 0) {
                return Utf8.REPLACEMENT_CODE_POINT;
            }
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
            int i7 = i4 * i3;
            if (i7 < 0 || (i4 = i7 + i5) < 0) {
                return Utf8.REPLACEMENT_CODE_POINT;
            }
            i++;
        }
        return i4;
    }

    static String unescape(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        String str2 = str;
        StringBuilder sb = null;
        if (str2 == null) {
            return null;
        }
        HtmlEscapeSymbols htmlEscapeSymbols = HtmlEscapeSymbols.HTML5_SYMBOLS;
        int length = str.length();
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < length) {
            char charAt = str2.charAt(i5);
            if (charAt == '&' && (i2 = i5 + 1) < length) {
                if (charAt == '&') {
                    char charAt2 = str2.charAt(i2);
                    if (!(charAt2 == ' ' || charAt2 == 10 || charAt2 == 9 || charAt2 == 12 || charAt2 == '<' || charAt2 == '&')) {
                        if (charAt2 == '#') {
                            int i8 = i5 + 2;
                            if (i8 < length) {
                                char charAt3 = str2.charAt(i8);
                                if ((charAt3 == 'x' || charAt3 == 'X') && (i4 = i5 + 3) < length) {
                                    int i9 = i4;
                                    while (i9 < length) {
                                        char charAt4 = str2.charAt(i9);
                                        if ((charAt4 < '0' || charAt4 > '9') && ((charAt4 < 'A' || charAt4 > 'F') && (charAt4 < 'a' || charAt4 > 'f'))) {
                                            break;
                                        }
                                        i9++;
                                    }
                                    if (i9 - i4 > 0) {
                                        int parseIntFromReference = parseIntFromReference(str2, i4, i9, 16);
                                        i7 = i9 - 1;
                                        if (i9 < length && str2.charAt(i9) == ';') {
                                            i7++;
                                        }
                                        i3 = translateIllFormedCodepoint(parseIntFromReference);
                                    }
                                } else if (charAt3 >= '0' && charAt3 <= '9') {
                                    int i10 = i8;
                                    while (i10 < length) {
                                        char charAt5 = str2.charAt(i10);
                                        if (charAt5 < '0' || charAt5 > '9') {
                                            break;
                                        }
                                        i10++;
                                    }
                                    if (i10 - i8 > 0) {
                                        int parseIntFromReference2 = parseIntFromReference(str2, i8, i10, 10);
                                        int i11 = i10 - 1;
                                        if (i10 < length && str2.charAt(i10) == ';') {
                                            i11++;
                                        }
                                        i3 = translateIllFormedCodepoint(parseIntFromReference2);
                                    }
                                }
                            }
                        } else {
                            int i12 = i2;
                            while (i12 < length) {
                                char charAt6 = str2.charAt(i12);
                                if ((charAt6 < 'a' || charAt6 > 'z') && ((charAt6 < 'A' || charAt6 > 'Z') && (charAt6 < '0' || charAt6 > '9'))) {
                                    break;
                                }
                                i12++;
                            }
                            if (i12 - i2 > 0) {
                                if (i12 < length && str2.charAt(i12) == ';') {
                                    i12++;
                                }
                                int binarySearch = HtmlEscapeSymbols.binarySearch(htmlEscapeSymbols.SORTED_NCRS, str2, i5, i12);
                                if (binarySearch >= 0) {
                                    i3 = htmlEscapeSymbols.SORTED_CODEPOINTS[binarySearch];
                                } else if (binarySearch != Integer.MIN_VALUE) {
                                    if (binarySearch < -10) {
                                        int i13 = (binarySearch + 10) * -1;
                                        char[] cArr = htmlEscapeSymbols.SORTED_NCRS[i13];
                                        i3 = htmlEscapeSymbols.SORTED_CODEPOINTS[i13];
                                        i12 -= (i12 - i5) - cArr.length;
                                    } else {
                                        throw new RuntimeException("Invalid unescape codepoint after search: " + binarySearch);
                                    }
                                }
                                i7 = i12 - 1;
                            }
                        }
                    }
                } else {
                    i3 = 0;
                }
                if (sb == null) {
                    sb = new StringBuilder(length + 5);
                }
                if (i5 - i6 > 0) {
                    sb.append(str2, i6, i5);
                }
                i6 = i7 + 1;
                if (i3 > 65535) {
                    sb.append(Character.toChars(i3));
                    i = 1;
                } else if (i3 < 0) {
                    int[] iArr = htmlEscapeSymbols.DOUBLE_CODEPOINTS[(i3 * -1) - 1];
                    if (iArr[0] > 65535) {
                        sb.append(Character.toChars(iArr[0]));
                    } else {
                        sb.append((char) iArr[0]);
                    }
                    i = 1;
                    if (iArr[1] > 65535) {
                        sb.append(Character.toChars(iArr[1]));
                    } else {
                        sb.append((char) iArr[1]);
                    }
                } else {
                    i = 1;
                    sb.append((char) i3);
                }
                i5 = i7;
                i5 += i;
            }
            i = 1;
            i5 += i;
        }
        if (sb == null) {
            return str2;
        }
        if (length - i6 > 0) {
            sb.append(str2, i6, length);
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01f0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void unescape(java.io.Reader r16, java.io.Writer r17) throws java.io.IOException {
        /*
            r0 = r17
            if (r16 != 0) goto L_0x0005
            return
        L_0x0005:
            org.unbescape.html.HtmlEscapeSymbols r1 = org.unbescape.html.HtmlEscapeSymbols.HTML5_SYMBOLS
            r2 = 10
            char[] r3 = new char[r2]
            int r4 = r16.read()
        L_0x000f:
            if (r4 < 0) goto L_0x01fc
            int r5 = r16.read()
            r6 = 38
            if (r4 != r6) goto L_0x01f4
            if (r5 >= 0) goto L_0x001d
            goto L_0x01f4
        L_0x001d:
            r8 = 0
            if (r4 != r6) goto L_0x01a7
            r9 = 32
            if (r5 == r9) goto L_0x01a3
            if (r5 == r2) goto L_0x01a3
            r9 = 9
            if (r5 == r9) goto L_0x01a3
            r9 = 12
            if (r5 == r9) goto L_0x01a3
            r9 = 60
            if (r5 == r9) goto L_0x01a3
            if (r5 != r6) goto L_0x0036
            goto L_0x01a3
        L_0x0036:
            r6 = 35
            r9 = 97
            r10 = 65
            r11 = 59
            r12 = 57
            r13 = 48
            if (r5 != r6) goto L_0x00fc
            int r6 = r16.read()
            if (r6 >= 0) goto L_0x0051
            r0.write(r4)
            r0.write(r5)
            goto L_0x00a0
        L_0x0051:
            r14 = 120(0x78, float:1.68E-43)
            if (r6 == r14) goto L_0x00a3
            r14 = 88
            if (r6 != r14) goto L_0x005a
            goto L_0x00a3
        L_0x005a:
            if (r6 < r13) goto L_0x009a
            if (r6 > r12) goto L_0x009a
            r9 = r6
            r10 = 0
        L_0x0060:
            if (r9 < 0) goto L_0x007e
            if (r9 < r13) goto L_0x007e
            if (r9 <= r12) goto L_0x0067
            goto L_0x007e
        L_0x0067:
            int r14 = r3.length
            if (r10 != r14) goto L_0x0074
            int r14 = r3.length
            int r14 = r14 + 4
            char[] r14 = new char[r14]
            int r15 = r3.length
            java.lang.System.arraycopy(r3, r8, r14, r8, r15)
            r3 = r14
        L_0x0074:
            char r9 = (char) r9
            r3[r10] = r9
            int r9 = r16.read()
            int r10 = r10 + 1
            goto L_0x0060
        L_0x007e:
            if (r10 != 0) goto L_0x0087
            r0.write(r4)
            r0.write(r5)
            goto L_0x00a0
        L_0x0087:
            int r4 = r10 + -1
            char r4 = r3[r4]
            int r4 = parseIntFromReference((char[]) r3, (int) r8, (int) r10, (int) r2)
            if (r9 != r11) goto L_0x0095
            int r9 = r16.read()
        L_0x0095:
            int r4 = translateIllFormedCodepoint(r4)
            goto L_0x00f9
        L_0x009a:
            r0.write(r4)
            r0.write(r5)
        L_0x00a0:
            r4 = r6
            goto L_0x000f
        L_0x00a3:
            int r14 = r16.read()
            r15 = 0
        L_0x00a8:
            if (r14 < 0) goto L_0x00d4
            if (r14 < r13) goto L_0x00ae
            if (r14 <= r12) goto L_0x00bb
        L_0x00ae:
            if (r14 < r10) goto L_0x00b4
            r2 = 70
            if (r14 <= r2) goto L_0x00bb
        L_0x00b4:
            if (r14 < r9) goto L_0x00d4
            r2 = 102(0x66, float:1.43E-43)
            if (r14 <= r2) goto L_0x00bb
            goto L_0x00d4
        L_0x00bb:
            int r2 = r3.length
            if (r15 != r2) goto L_0x00c8
            int r2 = r3.length
            int r2 = r2 + 4
            char[] r2 = new char[r2]
            int r7 = r3.length
            java.lang.System.arraycopy(r3, r8, r2, r8, r7)
            r3 = r2
        L_0x00c8:
            char r2 = (char) r14
            r3[r15] = r2
            int r14 = r16.read()
            int r15 = r15 + 1
            r2 = 10
            goto L_0x00a8
        L_0x00d4:
            if (r15 != 0) goto L_0x00e2
            r0.write(r4)
            r0.write(r5)
            r0.write(r6)
            r4 = r14
            goto L_0x01f8
        L_0x00e2:
            int r2 = r15 + -1
            char r2 = r3[r2]
            r2 = 16
            int r2 = parseIntFromReference((char[]) r3, (int) r8, (int) r15, (int) r2)
            if (r14 != r11) goto L_0x00f4
            int r4 = r16.read()
            r9 = r4
            goto L_0x00f5
        L_0x00f4:
            r9 = r14
        L_0x00f5:
            int r4 = translateIllFormedCodepoint(r2)
        L_0x00f9:
            r5 = r9
            goto L_0x01a8
        L_0x00fc:
            r2 = r5
            r6 = 0
        L_0x00fe:
            if (r2 < 0) goto L_0x0128
            if (r2 < r13) goto L_0x0104
            if (r2 <= r12) goto L_0x0111
        L_0x0104:
            if (r2 < r10) goto L_0x010a
            r7 = 90
            if (r2 <= r7) goto L_0x0111
        L_0x010a:
            if (r2 < r9) goto L_0x0128
            r7 = 122(0x7a, float:1.71E-43)
            if (r2 <= r7) goto L_0x0111
            goto L_0x0128
        L_0x0111:
            int r7 = r3.length
            if (r6 != r7) goto L_0x011e
            int r7 = r3.length
            int r7 = r7 + 4
            char[] r7 = new char[r7]
            int r14 = r3.length
            java.lang.System.arraycopy(r3, r8, r7, r8, r14)
            r3 = r7
        L_0x011e:
            char r2 = (char) r2
            r3[r6] = r2
            int r2 = r16.read()
            int r6 = r6 + 1
            goto L_0x00fe
        L_0x0128:
            if (r6 != 0) goto L_0x012f
            r0.write(r4)
            goto L_0x01f7
        L_0x012f:
            int r5 = r6 + 2
            int r7 = r3.length
            if (r5 < r7) goto L_0x013e
            int r5 = r3.length
            int r5 = r5 + 4
            char[] r5 = new char[r5]
            int r7 = r3.length
            java.lang.System.arraycopy(r3, r8, r5, r8, r7)
            r3 = r5
        L_0x013e:
            r5 = 1
            java.lang.System.arraycopy(r3, r8, r3, r5, r6)
            char r4 = (char) r4
            r3[r8] = r4
            int r6 = r6 + 1
            if (r2 != r11) goto L_0x0153
            int r4 = r6 + 1
            char r2 = (char) r2
            r3[r6] = r2
            int r2 = r16.read()
            r6 = r4
        L_0x0153:
            r4 = r2
            int r2 = r6 + -1
            char r2 = r3[r2]
            char[][] r2 = r1.SORTED_NCRS
            int r2 = org.unbescape.html.HtmlEscapeSymbols.binarySearch((char[][]) r2, (char[]) r3, (int) r8, (int) r6)
            if (r2 < 0) goto L_0x0168
            int[] r5 = r1.SORTED_CODEPOINTS
            r2 = r5[r2]
            r5 = r4
            r6 = 0
        L_0x0166:
            r4 = r2
            goto L_0x01a9
        L_0x0168:
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 != r5) goto L_0x0171
            r0.write(r3, r8, r6)
            goto L_0x01f8
        L_0x0171:
            r5 = -10
            if (r2 >= r5) goto L_0x018c
            int r2 = r2 + 10
            int r2 = r2 * -1
            char[][] r5 = r1.SORTED_NCRS
            r5 = r5[r2]
            int[] r7 = r1.SORTED_CODEPOINTS
            r2 = r7[r2]
            int r7 = r5.length
            int r9 = r5.length
            int r9 = r6 - r9
            java.lang.System.arraycopy(r3, r7, r3, r8, r9)
            int r5 = r5.length
            int r6 = r6 - r5
            r5 = r4
            goto L_0x0166
        L_0x018c:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Invalid unescape codepoint after search: "
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x01a3:
            r0.write(r4)
            goto L_0x01f7
        L_0x01a7:
            r4 = 0
        L_0x01a8:
            r6 = 0
        L_0x01a9:
            r2 = 65535(0xffff, float:9.1834E-41)
            if (r4 <= r2) goto L_0x01b6
            char[] r2 = java.lang.Character.toChars(r4)
            r0.write(r2)
            goto L_0x01ee
        L_0x01b6:
            if (r4 >= 0) goto L_0x01ea
            int[][] r7 = r1.DOUBLE_CODEPOINTS
            int r4 = r4 * -1
            r9 = 1
            int r4 = r4 - r9
            r4 = r7[r4]
            r7 = r4[r8]
            if (r7 <= r2) goto L_0x01ce
            r7 = r4[r8]
            char[] r7 = java.lang.Character.toChars(r7)
            r0.write(r7)
            goto L_0x01d4
        L_0x01ce:
            r7 = r4[r8]
            char r7 = (char) r7
            r0.write(r7)
        L_0x01d4:
            r7 = 1
            r9 = r4[r7]
            if (r9 <= r2) goto L_0x01e3
            r2 = r4[r7]
            char[] r2 = java.lang.Character.toChars(r2)
            r0.write(r2)
            goto L_0x01ee
        L_0x01e3:
            r2 = r4[r7]
            char r2 = (char) r2
            r0.write(r2)
            goto L_0x01ee
        L_0x01ea:
            char r2 = (char) r4
            r0.write(r2)
        L_0x01ee:
            if (r6 <= 0) goto L_0x01f7
            r0.write(r3, r8, r6)
            goto L_0x01f7
        L_0x01f4:
            r0.write(r4)
        L_0x01f7:
            r4 = r5
        L_0x01f8:
            r2 = 10
            goto L_0x000f
        L_0x01fc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.html.HtmlEscapeUtil.unescape(java.io.Reader, java.io.Writer):void");
    }

    static void unescape(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        char[] cArr2 = cArr;
        Writer writer2 = writer;
        if (cArr2 != null) {
            HtmlEscapeSymbols htmlEscapeSymbols = HtmlEscapeSymbols.HTML5_SYMBOLS;
            int i9 = i + i2;
            int i10 = i;
            int i11 = i10;
            int i12 = i11;
            while (i10 < i9) {
                char c = cArr2[i10];
                if (c == '&' && (i4 = i10 + 1) < i9) {
                    if (c == '&') {
                        char c2 = cArr2[i4];
                        if (!(c2 == ' ' || c2 == 10 || c2 == 9 || c2 == 12 || c2 == '<' || c2 == '&')) {
                            if (c2 == '#') {
                                int i13 = i10 + 2;
                                if (i13 < i9) {
                                    char c3 = cArr2[i13];
                                    if ((c3 == 'x' || c3 == 'X') && (i8 = i10 + 3) < i9) {
                                        int i14 = i8;
                                        while (i14 < i9) {
                                            char c4 = cArr2[i14];
                                            if ((c4 < '0' || c4 > '9') && ((c4 < 'A' || c4 > 'F') && (c4 < 'a' || c4 > 'f'))) {
                                                break;
                                            }
                                            i14++;
                                        }
                                        if (i14 - i8 > 0) {
                                            int parseIntFromReference = parseIntFromReference(cArr2, i8, i14, 16);
                                            int i15 = i14 - 1;
                                            if (i14 < i9 && cArr2[i14] == ';') {
                                                i15++;
                                            }
                                            i5 = translateIllFormedCodepoint(parseIntFromReference);
                                            i12 = i15;
                                        }
                                    } else if (c3 >= '0' && c3 <= '9') {
                                        int i16 = i13;
                                        while (i16 < i9) {
                                            char c5 = cArr2[i16];
                                            if (c5 < '0' || c5 > '9') {
                                                break;
                                            }
                                            i16++;
                                        }
                                        if (i16 - i13 > 0) {
                                            int parseIntFromReference2 = parseIntFromReference(cArr2, i13, i16, 10);
                                            i6 = i16 - 1;
                                            if (i16 < i9 && cArr2[i16] == ';') {
                                                i6++;
                                            }
                                            i7 = translateIllFormedCodepoint(parseIntFromReference2);
                                        }
                                    }
                                }
                            } else {
                                int i17 = i4;
                                while (i17 < i9) {
                                    char c6 = cArr2[i17];
                                    if ((c6 < 'a' || c6 > 'z') && ((c6 < 'A' || c6 > 'Z') && (c6 < '0' || c6 > '9'))) {
                                        break;
                                    }
                                    i17++;
                                }
                                if (i17 - i4 > 0) {
                                    if (i17 < i9 && cArr2[i17] == ';') {
                                        i17++;
                                    }
                                    int binarySearch = HtmlEscapeSymbols.binarySearch(htmlEscapeSymbols.SORTED_NCRS, cArr2, i10, i17);
                                    if (binarySearch >= 0) {
                                        i7 = htmlEscapeSymbols.SORTED_CODEPOINTS[binarySearch];
                                    } else if (binarySearch != Integer.MIN_VALUE) {
                                        if (binarySearch < -10) {
                                            int i18 = (binarySearch + 10) * -1;
                                            char[] cArr3 = htmlEscapeSymbols.SORTED_NCRS[i18];
                                            int i19 = htmlEscapeSymbols.SORTED_CODEPOINTS[i18];
                                            i17 -= (i17 - i10) - cArr3.length;
                                            i7 = i19;
                                        } else {
                                            throw new RuntimeException("Invalid unescape codepoint after search: " + binarySearch);
                                        }
                                    }
                                    i6 = i17 - 1;
                                }
                            }
                            int i20 = i6;
                            i5 = i7;
                            i12 = i20;
                        }
                    } else {
                        i5 = 0;
                    }
                    int i21 = i10 - i11;
                    if (i21 > 0) {
                        writer2.write(cArr2, i11, i21);
                    }
                    int i22 = i12 + 1;
                    if (i5 > 65535) {
                        writer2.write(Character.toChars(i5));
                        i3 = 1;
                    } else if (i5 < 0) {
                        int[] iArr = htmlEscapeSymbols.DOUBLE_CODEPOINTS[(i5 * -1) - 1];
                        if (iArr[0] > 65535) {
                            writer2.write(Character.toChars(iArr[0]));
                        } else {
                            writer2.write((char) iArr[0]);
                        }
                        i3 = 1;
                        if (iArr[1] > 65535) {
                            writer2.write(Character.toChars(iArr[1]));
                        } else {
                            writer2.write((char) iArr[1]);
                        }
                    } else {
                        i3 = 1;
                        writer2.write((char) i5);
                    }
                    i11 = i22;
                    i10 = i12;
                    i10 += i3;
                }
                i3 = 1;
                i10 += i3;
            }
            int i23 = i9 - i11;
            if (i23 > 0) {
                writer2.write(cArr2, i11, i23);
            }
        }
    }

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
