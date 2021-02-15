package org.unbescape.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

final class XmlEscapeUtil {
    private static char[] HEXA_CHARS_LOWER = "0123456789abcdef".toCharArray();
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();
    private static final char[] REFERENCE_DECIMAL_PREFIX = "&#".toCharArray();
    private static final char[] REFERENCE_HEXA_PREFIX = "&#x".toCharArray();
    private static final char REFERENCE_HEXA_PREFIX3 = 'x';
    private static final char REFERENCE_NUMERIC_PREFIX2 = '#';
    private static final char REFERENCE_PREFIX = '&';
    private static final char REFERENCE_SUFFIX = ';';

    private XmlEscapeUtil() {
    }

    static String escape(String str, XmlEscapeSymbols xmlEscapeSymbols, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) {
        int binarySearch;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int escapeLevel = xmlEscapeLevel.getEscapeLevel();
        boolean useCERs = xmlEscapeType.getUseCERs();
        boolean useHexa = xmlEscapeType.getUseHexa();
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int codePointAt = Character.codePointAt(str, i);
            boolean isValid = xmlEscapeSymbols.CODEPOINT_VALIDATOR.isValid(codePointAt);
            if (codePointAt > 159 || escapeLevel >= xmlEscapeSymbols.ESCAPE_LEVELS[codePointAt] || !isValid) {
                if (codePointAt <= 159 || escapeLevel >= xmlEscapeSymbols.ESCAPE_LEVELS[160] || !isValid) {
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
                    if (isValid) {
                        if (!useCERs || (binarySearch = Arrays.binarySearch(xmlEscapeSymbols.SORTED_CODEPOINTS, codePointAt)) < 0) {
                            if (useHexa) {
                                sb.append(REFERENCE_HEXA_PREFIX);
                                sb.append(Integer.toHexString(codePointAt));
                            } else {
                                sb.append(REFERENCE_DECIMAL_PREFIX);
                                sb.append(String.valueOf(codePointAt));
                            }
                            sb.append(';');
                        } else {
                            sb.append(xmlEscapeSymbols.SORTED_CERS_BY_CODEPOINT[binarySearch]);
                        }
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

    static void escape(Reader reader, Writer writer, XmlEscapeSymbols xmlEscapeSymbols, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        int binarySearch;
        if (reader != null) {
            int escapeLevel = xmlEscapeLevel.getEscapeLevel();
            boolean useCERs = xmlEscapeType.getUseCERs();
            boolean useHexa = xmlEscapeType.getUseHexa();
            int read = reader.read();
            while (read >= 0) {
                int read2 = reader.read();
                int codePointAt = codePointAt((char) read, (char) read2);
                boolean isValid = xmlEscapeSymbols.CODEPOINT_VALIDATOR.isValid(codePointAt);
                if (codePointAt <= 159 && escapeLevel < xmlEscapeSymbols.ESCAPE_LEVELS[codePointAt] && isValid) {
                    writer.write(read);
                } else if (codePointAt <= 159 || escapeLevel >= xmlEscapeSymbols.ESCAPE_LEVELS[160] || !isValid) {
                    if (Character.charCount(codePointAt) > 1) {
                        read = reader.read();
                    } else {
                        read = read2;
                    }
                    if (isValid) {
                        if (!useCERs || (binarySearch = Arrays.binarySearch(xmlEscapeSymbols.SORTED_CODEPOINTS, codePointAt)) < 0) {
                            if (useHexa) {
                                writer.write(REFERENCE_HEXA_PREFIX);
                                writer.write(Integer.toHexString(codePointAt));
                            } else {
                                writer.write(REFERENCE_DECIMAL_PREFIX);
                                writer.write(String.valueOf(codePointAt));
                            }
                            writer.write(59);
                        } else {
                            writer.write(xmlEscapeSymbols.SORTED_CERS_BY_CODEPOINT[binarySearch]);
                        }
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

    static void escape(char[] cArr, int i, int i2, Writer writer, XmlEscapeSymbols xmlEscapeSymbols, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        int binarySearch;
        if (cArr != null && cArr.length != 0) {
            int escapeLevel = xmlEscapeLevel.getEscapeLevel();
            boolean useCERs = xmlEscapeType.getUseCERs();
            boolean useHexa = xmlEscapeType.getUseHexa();
            int i3 = i2 + i;
            int i4 = i;
            while (i < i3) {
                int codePointAt = Character.codePointAt(cArr, i);
                boolean isValid = xmlEscapeSymbols.CODEPOINT_VALIDATOR.isValid(codePointAt);
                if (codePointAt > 159 || escapeLevel >= xmlEscapeSymbols.ESCAPE_LEVELS[codePointAt] || !isValid) {
                    if (codePointAt <= 159 || escapeLevel >= xmlEscapeSymbols.ESCAPE_LEVELS[160] || !isValid) {
                        int i5 = i - i4;
                        if (i5 > 0) {
                            writer.write(cArr, i4, i5);
                        }
                        if (Character.charCount(codePointAt) > 1) {
                            i++;
                        }
                        i4 = i + 1;
                        if (isValid) {
                            if (!useCERs || (binarySearch = Arrays.binarySearch(xmlEscapeSymbols.SORTED_CODEPOINTS, codePointAt)) < 0) {
                                if (useHexa) {
                                    writer.write(REFERENCE_HEXA_PREFIX);
                                    writer.write(Integer.toHexString(codePointAt));
                                } else {
                                    writer.write(REFERENCE_DECIMAL_PREFIX);
                                    writer.write(String.valueOf(codePointAt));
                                }
                                writer.write(59);
                            } else {
                                writer.write(xmlEscapeSymbols.SORTED_CERS_BY_CODEPOINT[binarySearch]);
                            }
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

    static String unescape(String str, XmlEscapeSymbols xmlEscapeSymbols) {
        int i;
        int i2;
        int i3;
        String str2 = str;
        XmlEscapeSymbols xmlEscapeSymbols2 = xmlEscapeSymbols;
        StringBuilder sb = null;
        if (str2 == null) {
            return null;
        }
        int length = str.length();
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < length) {
            char charAt = str2.charAt(i4);
            if (charAt == '&' && (i = i4 + 1) < length) {
                if (charAt == '&') {
                    char charAt2 = str2.charAt(i);
                    if (!(charAt2 == ' ' || charAt2 == 10 || charAt2 == 9 || charAt2 == 12 || charAt2 == '<' || charAt2 == '&')) {
                        if (charAt2 == '#') {
                            int i7 = i4 + 2;
                            if (i7 < length) {
                                char charAt3 = str2.charAt(i7);
                                if (charAt3 == 'x' && (i3 = i4 + 3) < length) {
                                    int i8 = i3;
                                    while (i8 < length) {
                                        char charAt4 = str2.charAt(i8);
                                        if ((charAt4 < '0' || charAt4 > '9') && ((charAt4 < 'A' || charAt4 > 'F') && (charAt4 < 'a' || charAt4 > 'f'))) {
                                            break;
                                        }
                                        i8++;
                                    }
                                    if (i8 - i3 > 0 && i8 < length && str2.charAt(i8) == ';') {
                                        int i9 = (i8 + 1) - 1;
                                        i2 = parseIntFromReference(str2, i3, i9, 16);
                                        i6 = i9;
                                    }
                                } else if (charAt3 >= '0' && charAt3 <= '9') {
                                    int i10 = i7;
                                    while (i10 < length) {
                                        char charAt5 = str2.charAt(i10);
                                        if (charAt5 < '0' || charAt5 > '9') {
                                            break;
                                        }
                                        i10++;
                                    }
                                    if (i10 - i7 > 0 && i10 < length && str2.charAt(i10) == ';') {
                                        int i11 = (i10 + 1) - 1;
                                        i2 = parseIntFromReference(str2, i7, i11, 10);
                                        i6 = i11;
                                    }
                                }
                            }
                        } else {
                            int i12 = i;
                            while (i12 < length) {
                                char charAt6 = str2.charAt(i12);
                                if ((charAt6 < 'a' || charAt6 > 'z') && ((charAt6 < 'A' || charAt6 > 'Z') && (charAt6 < '0' || charAt6 > '9'))) {
                                    break;
                                }
                                i12++;
                            }
                            if (i12 - i > 0) {
                                if (i12 < length && str2.charAt(i12) == ';') {
                                    i12++;
                                }
                                int binarySearch = XmlEscapeSymbols.binarySearch(xmlEscapeSymbols2.SORTED_CERS, str2, i4, i12);
                                if (binarySearch >= 0) {
                                    i2 = xmlEscapeSymbols2.SORTED_CODEPOINTS_BY_CER[binarySearch];
                                    i6 = i12 - 1;
                                }
                            }
                        }
                    }
                } else {
                    i2 = 0;
                }
                if (sb == null) {
                    sb = new StringBuilder(length + 5);
                }
                if (i4 - i5 > 0) {
                    sb.append(str2, i5, i4);
                }
                i5 = i6 + 1;
                if (i2 > 65535) {
                    sb.append(Character.toChars(i2));
                } else {
                    sb.append((char) i2);
                }
                i4 = i6;
            }
            i4++;
        }
        if (sb == null) {
            return str2;
        }
        if (length - i5 > 0) {
            sb.append(str2, i5, length);
        }
        return sb.toString();
    }

    static void unescape(Reader reader, Writer writer, XmlEscapeSymbols xmlEscapeSymbols) throws IOException {
        int i;
        int parseIntFromReference;
        Writer writer2 = writer;
        XmlEscapeSymbols xmlEscapeSymbols2 = xmlEscapeSymbols;
        if (reader != null) {
            char[] cArr = new char[10];
            int read = reader.read();
            while (read >= 0) {
                int read2 = reader.read();
                if (read != 38 || read2 < 0) {
                    writer2.write(read);
                } else {
                    int i2 = 0;
                    if (read != 38) {
                        read = read2;
                    } else if (read2 == 32 || read2 == 10 || read2 == 9 || read2 == 12 || read2 == 60 || read2 == 38) {
                        writer2.write(read);
                    } else {
                        int i3 = 97;
                        if (read2 == 35) {
                            int read3 = reader.read();
                            if (read3 < 0) {
                                writer2.write(read);
                                writer2.write(read2);
                            } else {
                                if (read3 == 120) {
                                    int read4 = reader.read();
                                    int i4 = 0;
                                    while (read4 >= 0 && ((read4 >= 48 && read4 <= 57) || ((read4 >= 65 && read4 <= 70) || (read4 >= i3 && read4 <= 102)))) {
                                        if (i4 == cArr.length) {
                                            char[] cArr2 = new char[(cArr.length + 4)];
                                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                                            cArr = cArr2;
                                        }
                                        cArr[i4] = (char) read4;
                                        read4 = reader.read();
                                        i4++;
                                        i3 = 97;
                                    }
                                    if (i4 == 0) {
                                        writer2.write(read);
                                        writer2.write(read2);
                                        writer2.write(read3);
                                    } else if (read4 != 59) {
                                        writer2.write(read);
                                        writer2.write(read2);
                                        writer2.write(read3);
                                        writer2.write(cArr, 0, i4);
                                        char c = cArr[i4 - 1];
                                    } else {
                                        read = reader.read();
                                        parseIntFromReference = parseIntFromReference(cArr, 0, i4, 16);
                                    }
                                    read = read4;
                                } else if (read3 < 48 || read3 > 57) {
                                    writer2.write(read);
                                    writer2.write(read2);
                                } else {
                                    int i5 = read3;
                                    int i6 = 0;
                                    while (i5 >= 0 && i5 >= 48 && i5 <= 57) {
                                        if (i6 == cArr.length) {
                                            char[] cArr3 = new char[(cArr.length + 4)];
                                            System.arraycopy(cArr, 0, cArr3, 0, cArr.length);
                                            cArr = cArr3;
                                        }
                                        cArr[i6] = (char) i5;
                                        i5 = reader.read();
                                        i6++;
                                    }
                                    if (i6 == 0) {
                                        writer2.write(read);
                                        writer2.write(read2);
                                    } else if (i5 != 59) {
                                        writer2.write(read);
                                        writer2.write(read2);
                                        writer2.write(cArr, 0, i6);
                                        char c2 = cArr[i6 - 1];
                                        read = i5;
                                    } else {
                                        read = reader.read();
                                        parseIntFromReference = parseIntFromReference(cArr, 0, i6, 10);
                                    }
                                }
                                i2 = parseIntFromReference;
                            }
                            read = read3;
                        } else {
                            int i7 = read2;
                            int i8 = 0;
                            while (i7 >= 0) {
                                if ((i7 < 48 || i7 > 57) && (i7 < 65 || i7 > 90)) {
                                    if (i7 >= 97) {
                                        if (i7 > 122) {
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                if (i8 == cArr.length) {
                                    char[] cArr4 = new char[(cArr.length + 4)];
                                    System.arraycopy(cArr, 0, cArr4, 0, cArr.length);
                                    cArr = cArr4;
                                }
                                cArr[i8] = (char) i7;
                                i7 = reader.read();
                                i8++;
                            }
                            if (i8 == 0) {
                                writer2.write(read);
                            } else {
                                if (i8 + 2 >= cArr.length) {
                                    char[] cArr5 = new char[(cArr.length + 4)];
                                    System.arraycopy(cArr, 0, cArr5, 0, cArr.length);
                                    cArr = cArr5;
                                }
                                System.arraycopy(cArr, 0, cArr, 1, i8);
                                cArr[0] = (char) read;
                                int i9 = i8 + 1;
                                if (i7 == 59) {
                                    cArr[i9] = (char) i7;
                                    i9++;
                                    i = reader.read();
                                } else {
                                    i = i7;
                                }
                                char c3 = cArr[i9 - 1];
                                int binarySearch = XmlEscapeSymbols.binarySearch(xmlEscapeSymbols2.SORTED_CERS, cArr, 0, i9);
                                if (binarySearch >= 0) {
                                    i2 = xmlEscapeSymbols2.SORTED_CODEPOINTS_BY_CER[binarySearch];
                                } else {
                                    writer2.write(cArr, 0, i9);
                                }
                            }
                        }
                    }
                    if (i2 > 65535) {
                        writer2.write(Character.toChars(i2));
                    } else {
                        writer2.write((char) i2);
                    }
                }
                read = read2;
            }
        }
    }

    static void unescape(char[] cArr, int i, int i2, Writer writer, XmlEscapeSymbols xmlEscapeSymbols) throws IOException {
        int i3;
        int i4;
        if (cArr != null) {
            int i5 = i2 + i;
            int i6 = i;
            int i7 = i6;
            while (i < i5) {
                char c = cArr[i];
                if (c == '&' && (i3 = i + 1) < i5) {
                    int i8 = 0;
                    if (c == '&') {
                        char c2 = cArr[i3];
                        if (!(c2 == ' ' || c2 == 10 || c2 == 9 || c2 == 12 || c2 == '<' || c2 == '&')) {
                            if (c2 == '#') {
                                int i9 = i + 2;
                                if (i9 < i5) {
                                    char c3 = cArr[i9];
                                    if (c3 == 'x' && (i4 = i + 3) < i5) {
                                        int i10 = i4;
                                        while (i10 < i5) {
                                            char c4 = cArr[i10];
                                            if ((c4 < '0' || c4 > '9') && ((c4 < 'A' || c4 > 'F') && (c4 < 'a' || c4 > 'f'))) {
                                                break;
                                            }
                                            i10++;
                                        }
                                        if (i10 - i4 > 0 && i10 < i5 && cArr[i10] == ';') {
                                            int i11 = (i10 + 1) - 1;
                                            i8 = parseIntFromReference(cArr, i4, i11, 16);
                                            i7 = i11;
                                        }
                                    } else if (c3 >= '0' && c3 <= '9') {
                                        int i12 = i9;
                                        while (i12 < i5) {
                                            char c5 = cArr[i12];
                                            if (c5 < '0' || c5 > '9') {
                                                break;
                                            }
                                            i12++;
                                        }
                                        if (i12 - i9 > 0 && i12 < i5 && cArr[i12] == ';') {
                                            int i13 = (i12 + 1) - 1;
                                            i8 = parseIntFromReference(cArr, i9, i13, 10);
                                            i7 = i13;
                                        }
                                    }
                                }
                            } else {
                                int i14 = i3;
                                while (i14 < i5) {
                                    char c6 = cArr[i14];
                                    if ((c6 < 'a' || c6 > 'z') && ((c6 < 'A' || c6 > 'Z') && (c6 < '0' || c6 > '9'))) {
                                        break;
                                    }
                                    i14++;
                                }
                                if (i14 - i3 > 0) {
                                    if (i14 < i5 && cArr[i14] == ';') {
                                        i14++;
                                    }
                                    int binarySearch = XmlEscapeSymbols.binarySearch(xmlEscapeSymbols.SORTED_CERS, cArr, i, i14);
                                    if (binarySearch >= 0) {
                                        i8 = xmlEscapeSymbols.SORTED_CODEPOINTS_BY_CER[binarySearch];
                                        i7 = i14 - 1;
                                    }
                                }
                            }
                        }
                    }
                    int i15 = i - i6;
                    if (i15 > 0) {
                        writer.write(cArr, i6, i15);
                    }
                    int i16 = i7 + 1;
                    if (i8 > 65535) {
                        writer.write(Character.toChars(i8));
                    } else {
                        writer.write((char) i8);
                    }
                    i6 = i16;
                    i = i7;
                }
                i++;
            }
            int i17 = i5 - i6;
            if (i17 > 0) {
                writer.write(cArr, i6, i17);
            }
        }
    }

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
