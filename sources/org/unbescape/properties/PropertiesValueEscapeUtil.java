package org.unbescape.properties;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import net.bytebuddy.asm.Advice;

final class PropertiesValueEscapeUtil {
    private static final byte[] ESCAPE_LEVELS;
    private static final char ESCAPE_LEVELS_LEN = '¡';
    private static final char ESCAPE_PREFIX = '\\';
    private static final char[] ESCAPE_UHEXA_PREFIX = "\\u".toCharArray();
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();
    private static char[] SEC_CHARS;
    private static int SEC_CHARS_LEN = 93;
    private static char SEC_CHARS_NO_SEC = '*';

    static {
        char[] cArr = new char[93];
        SEC_CHARS = cArr;
        Arrays.fill(cArr, '*');
        char[] cArr2 = SEC_CHARS;
        cArr2[9] = Advice.OffsetMapping.ForOrigin.Renderer.ForTypeName.SYMBOL;
        cArr2[10] = 'n';
        cArr2[12] = 'f';
        cArr2[13] = Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName.SYMBOL;
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
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[92] = 1;
        for (char c5 = 0; c5 <= 31; c5 = (char) (c5 + 1)) {
            ESCAPE_LEVELS[c5] = 1;
        }
        for (char c6 = Ascii.MAX; c6 <= 159; c6 = (char) (c6 + 1)) {
            ESCAPE_LEVELS[c6] = 1;
        }
    }

    private PropertiesValueEscapeUtil() {
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

    static String escape(String str, PropertiesValueEscapeLevel propertiesValueEscapeLevel) {
        char c;
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int escapeLevel = propertiesValueEscapeLevel.getEscapeLevel();
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

    static void escape(Reader reader, Writer writer, PropertiesValueEscapeLevel propertiesValueEscapeLevel) throws IOException {
        char c;
        if (reader != null) {
            int escapeLevel = propertiesValueEscapeLevel.getEscapeLevel();
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

    static void escape(char[] cArr, int i, int i2, Writer writer, PropertiesValueEscapeLevel propertiesValueEscapeLevel) throws IOException {
        char c;
        if (cArr != null && cArr.length != 0) {
            int escapeLevel = propertiesValueEscapeLevel.getEscapeLevel();
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

    private static int codePointAt(char c, char c2) {
        return (!Character.isHighSurrogate(c) || c2 < 0 || !Character.isLowSurrogate(c2)) ? c : Character.toCodePoint(c, c2);
    }
}
