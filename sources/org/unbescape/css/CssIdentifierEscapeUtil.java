package org.unbescape.css;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import kotlin.text.Typography;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.pool.TypePool;

final class CssIdentifierEscapeUtil {
    private static char[] BACKSLASH_CHARS = null;
    private static int BACKSLASH_CHARS_LEN = 127;
    private static char BACKSLASH_CHARS_NO_ESCAPE = 0;
    private static final byte[] ESCAPE_LEVELS;
    private static final char ESCAPE_LEVELS_LEN = '¡';
    private static final char ESCAPE_PREFIX = '\\';
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();

    static {
        char[] cArr = new char[127];
        BACKSLASH_CHARS = cArr;
        Arrays.fill(cArr, 0);
        char[] cArr2 = BACKSLASH_CHARS;
        cArr2[32] = ' ';
        cArr2[33] = '!';
        cArr2[34] = '\"';
        cArr2[35] = '#';
        cArr2[36] = Typography.dollar;
        cArr2[37] = '%';
        cArr2[38] = Typography.amp;
        cArr2[39] = '\'';
        cArr2[40] = ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN;
        cArr2[41] = ASCIIPropertyListParser.ARRAY_END_TOKEN;
        cArr2[42] = '*';
        cArr2[43] = SignatureVisitor.EXTENDS;
        cArr2[44] = ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN;
        cArr2[45] = '-';
        cArr2[46] = '.';
        cArr2[47] = '/';
        cArr2[59] = ';';
        cArr2[60] = '<';
        cArr2[61] = '=';
        cArr2[62] = '>';
        cArr2[63] = '?';
        cArr2[64] = '@';
        cArr2[91] = TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH;
        cArr2[92] = '\\';
        cArr2[93] = ']';
        cArr2[94] = '^';
        cArr2[95] = '_';
        cArr2[96] = '`';
        cArr2[123] = ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN;
        cArr2[124] = '|';
        cArr2[125] = ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        cArr2[126] = '~';
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
        bArr2[32] = 1;
        bArr2[33] = 1;
        bArr2[34] = 1;
        bArr2[35] = 1;
        bArr2[36] = 1;
        bArr2[37] = 1;
        bArr2[38] = 1;
        bArr2[39] = 1;
        bArr2[40] = 1;
        bArr2[41] = 1;
        bArr2[42] = 1;
        bArr2[43] = 1;
        bArr2[44] = 1;
        bArr2[45] = 1;
        bArr2[46] = 1;
        bArr2[47] = 1;
        bArr2[58] = 1;
        bArr2[59] = 1;
        bArr2[60] = 1;
        bArr2[61] = 1;
        bArr2[62] = 1;
        bArr2[63] = 1;
        bArr2[64] = 1;
        bArr2[91] = 1;
        bArr2[92] = 1;
        bArr2[93] = 1;
        bArr2[94] = 1;
        bArr2[95] = 1;
        bArr2[96] = 1;
        bArr2[123] = 1;
        bArr2[124] = 1;
        bArr2[125] = 1;
        bArr2[126] = 1;
        for (char c5 = 0; c5 <= 31; c5 = (char) (c5 + 1)) {
            ESCAPE_LEVELS[c5] = 1;
        }
        for (char c6 = Ascii.MAX; c6 <= 159; c6 = (char) (c6 + 1)) {
            ESCAPE_LEVELS[c6] = 1;
        }
    }

    private CssIdentifierEscapeUtil() {
    }

    static char[] toCompactHexa(int i, char c, int i2) {
        int i3 = 0;
        boolean z = i2 < 4 && ((c >= '0' && c <= '9') || ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')));
        if (i != 0) {
            int i4 = 20;
            char[] cArr = null;
            while (cArr == null && i4 >= 0) {
                if ((i >>> i4) % 16 > 0) {
                    cArr = new char[((i4 / 4) + (z ? 2 : 1))];
                }
                i4 -= 4;
            }
            for (int length = z ? cArr.length - 2 : cArr.length - 1; length >= 0; length--) {
                cArr[length] = HEXA_CHARS_UPPER[(i >>> i3) % 16];
                i3 += 4;
            }
            if (z) {
                cArr[cArr.length - 1] = ' ';
            }
            return cArr;
        } else if (z) {
            return new char[]{'0', ' '};
        } else {
            return new char[]{'0'};
        }
    }

    static char[] toSixDigitHexa(int i, char c, int i2) {
        char[] cArr = new char[6];
        char[] cArr2 = HEXA_CHARS_UPPER;
        cArr[5] = cArr2[i % 16];
        cArr[4] = cArr2[(i >>> 4) % 16];
        cArr[3] = cArr2[(i >>> 8) % 16];
        cArr[2] = cArr2[(i >>> 12) % 16];
        cArr[1] = cArr2[(i >>> 16) % 16];
        cArr[0] = cArr2[(i >>> 20) % 16];
        return cArr;
    }

    static String escape(String str, CssIdentifierEscapeType cssIdentifierEscapeType, CssIdentifierEscapeLevel cssIdentifierEscapeLevel) {
        char c;
        int i;
        char charAt;
        String str2 = str;
        StringBuilder sb = null;
        if (str2 == null) {
            return null;
        }
        int escapeLevel = cssIdentifierEscapeLevel.getEscapeLevel();
        boolean useBackslashEscapes = cssIdentifierEscapeType.getUseBackslashEscapes();
        boolean useCompactHexa = cssIdentifierEscapeType.getUseCompactHexa();
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(str2, i2);
            if ((codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt] || (i2 <= 0 && codePointAt >= 48 && codePointAt <= 57)) && ((codePointAt != 45 || escapeLevel >= 3 || (i2 <= 0 && (i = i2 + 1) < length && ((charAt = str2.charAt(i)) == '-' || (charAt >= '0' && charAt <= '9')))) && (codePointAt != 95 || escapeLevel >= 3 || i2 <= 0))) {
                if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
                    if (sb == null) {
                        sb = new StringBuilder(length + 20);
                    }
                    if (i2 - i3 > 0) {
                        sb.append(str2, i3, i2);
                    }
                    if (Character.charCount(codePointAt) > 1) {
                        i2++;
                    }
                    i3 = i2 + 1;
                    if (!useBackslashEscapes || codePointAt >= BACKSLASH_CHARS_LEN || (c = BACKSLASH_CHARS[codePointAt]) == BACKSLASH_CHARS_NO_ESCAPE) {
                        char charAt2 = i3 < length ? str2.charAt(i3) : 0;
                        if (useCompactHexa) {
                            sb.append('\\');
                            sb.append(toCompactHexa(codePointAt, charAt2, escapeLevel));
                        } else {
                            sb.append('\\');
                            sb.append(toSixDigitHexa(codePointAt, charAt2, escapeLevel));
                        }
                    } else {
                        sb.append('\\');
                        sb.append(c);
                    }
                } else if (Character.charCount(codePointAt) > 1) {
                    i2++;
                }
            }
            i2++;
        }
        if (sb == null) {
            return str2;
        }
        if (length - i3 > 0) {
            sb.append(str2, i3, length);
        }
        return sb.toString();
    }

    static void escape(Reader reader, Writer writer, CssIdentifierEscapeType cssIdentifierEscapeType, CssIdentifierEscapeLevel cssIdentifierEscapeLevel) throws IOException {
        char c;
        if (reader != null) {
            int escapeLevel = cssIdentifierEscapeLevel.getEscapeLevel();
            boolean useBackslashEscapes = cssIdentifierEscapeType.getUseBackslashEscapes();
            boolean useCompactHexa = cssIdentifierEscapeType.getUseCompactHexa();
            int read = reader.read();
            int i = -1;
            while (read >= 0) {
                int read2 = reader.read();
                int codePointAt = codePointAt((char) read, (char) read2);
                if (codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt] || (i < 0 && codePointAt >= 48 && codePointAt <= 57)) {
                    if (codePointAt == 45 && escapeLevel < 3) {
                        if (i >= 0 || read2 < 0) {
                            writer.write(read);
                        } else if (read2 != 45 && (read2 < 48 || read2 > 57)) {
                            writer.write(read);
                        }
                    }
                    if (codePointAt == 95 && escapeLevel < 3 && i >= 0) {
                        writer.write(read);
                    } else if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
                        if (Character.charCount(codePointAt) > 1) {
                            read = reader.read();
                            i = read2;
                        } else {
                            i = read;
                            read = read2;
                        }
                        if (!useBackslashEscapes || codePointAt >= BACKSLASH_CHARS_LEN || (c = BACKSLASH_CHARS[codePointAt]) == BACKSLASH_CHARS_NO_ESCAPE) {
                            char c2 = read >= 0 ? (char) read : 0;
                            if (useCompactHexa) {
                                writer.write(92);
                                writer.write(toCompactHexa(codePointAt, c2, escapeLevel));
                            } else {
                                writer.write(92);
                                writer.write(toSixDigitHexa(codePointAt, c2, escapeLevel));
                            }
                        } else {
                            writer.write(92);
                            writer.write(c);
                        }
                    } else {
                        writer.write(read);
                        if (Character.charCount(codePointAt) > 1) {
                            writer.write(read2);
                            read = reader.read();
                            i = read2;
                        }
                    }
                } else {
                    writer.write(read);
                }
                i = read;
                read = read2;
            }
        }
    }

    static void escape(char[] cArr, int i, int i2, Writer writer, CssIdentifierEscapeType cssIdentifierEscapeType, CssIdentifierEscapeLevel cssIdentifierEscapeLevel) throws IOException {
        char c;
        int i3;
        char c2;
        char[] cArr2 = cArr;
        int i4 = i;
        Writer writer2 = writer;
        if (cArr2 != null && cArr2.length != 0) {
            int escapeLevel = cssIdentifierEscapeLevel.getEscapeLevel();
            boolean useBackslashEscapes = cssIdentifierEscapeType.getUseBackslashEscapes();
            boolean useCompactHexa = cssIdentifierEscapeType.getUseCompactHexa();
            int i5 = i4 + i2;
            int i6 = i4;
            int i7 = i6;
            while (i6 < i5) {
                int codePointAt = Character.codePointAt(cArr2, i6);
                if ((codePointAt > 159 || escapeLevel >= ESCAPE_LEVELS[codePointAt] || (i6 <= i4 && codePointAt >= 48 && codePointAt <= 57)) && ((codePointAt != 45 || escapeLevel >= 3 || (i6 <= i4 && (i3 = i6 + 1) < i5 && ((c2 = cArr2[i3]) == '-' || (c2 >= '0' && c2 <= '9')))) && (codePointAt != 95 || escapeLevel >= 3 || i6 <= i4))) {
                    if (codePointAt <= 159 || escapeLevel >= ESCAPE_LEVELS[160]) {
                        int i8 = i6 - i7;
                        if (i8 > 0) {
                            writer2.write(cArr2, i7, i8);
                        }
                        if (Character.charCount(codePointAt) > 1) {
                            i6++;
                        }
                        i7 = i6 + 1;
                        if (!useBackslashEscapes || codePointAt >= BACKSLASH_CHARS_LEN || (c = BACKSLASH_CHARS[codePointAt]) == BACKSLASH_CHARS_NO_ESCAPE) {
                            char c3 = i7 < i5 ? cArr2[i7] : 0;
                            if (useCompactHexa) {
                                writer2.write(92);
                                writer2.write(toCompactHexa(codePointAt, c3, escapeLevel));
                            } else {
                                writer2.write(92);
                                writer2.write(toSixDigitHexa(codePointAt, c3, escapeLevel));
                            }
                        } else {
                            writer2.write(92);
                            writer2.write(c);
                        }
                    } else if (Character.charCount(codePointAt) > 1) {
                        i6++;
                    }
                }
                i6++;
            }
            int i9 = i5 - i7;
            if (i9 > 0) {
                writer2.write(cArr2, i7, i9);
            }
        }
    }

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
