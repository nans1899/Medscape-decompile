package org.unbescape.csv;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

final class CsvEscapeUtil {
    private static final char DOUBLE_QUOTE = '\"';
    private static final char[] TWO_DOUBLE_QUOTES = "\"\"".toCharArray();

    private CsvEscapeUtil() {
    }

    static String escape(String str) {
        StringBuilder sb = null;
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if ((charAt < 'a' || charAt > 'z') && ((charAt < 'A' || charAt > 'Z') && (charAt < '0' || charAt > '9'))) {
                if (sb == null) {
                    sb = new StringBuilder(length + 20);
                    sb.append('\"');
                }
                if (i2 - i > 0) {
                    sb.append(str, i, i2);
                }
                i = i2 + 1;
                if (charAt == '\"') {
                    sb.append(TWO_DOUBLE_QUOTES);
                } else {
                    sb.append(charAt);
                }
            }
        }
        if (sb == null) {
            return str;
        }
        if (length - i > 0) {
            sb.append(str, i, length);
        }
        sb.append('\"');
        return sb.toString();
    }

    static void escape(Reader reader, Writer writer) throws IOException {
        if (reader != null) {
            int i = -1;
            char[] cArr = new char[10];
            int read = reader.read(cArr, 0, 10);
            if (read >= 0) {
                int i2 = 0;
                while (i < 0 && read >= 0) {
                    int i3 = i2 + read;
                    while (true) {
                        if (i >= 0 || i2 >= i3) {
                            break;
                        }
                        int i4 = i2 + 1;
                        char c = cArr[i2];
                        if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && (c < '0' || c > '9'))) {
                            i = 1;
                        } else {
                            i2 = i4;
                        }
                    }
                    if (i < 0 && read >= 0) {
                        if (i3 == cArr.length) {
                            char[] cArr2 = new char[(cArr.length + (cArr.length / 2))];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            cArr = cArr2;
                        }
                        read = reader.read(cArr, i3, cArr.length - i3);
                    }
                    i2 = i3;
                }
                int max = Math.max(i, 0);
                if (max == 1) {
                    writer.write(34);
                }
                if (i2 > 0) {
                    for (int i5 = 0; i5 < i2; i5++) {
                        char c2 = cArr[i5];
                        if (c2 == '\"') {
                            writer.write(TWO_DOUBLE_QUOTES);
                        } else {
                            writer.write(c2);
                        }
                    }
                }
                if (read >= 0) {
                    int read2 = reader.read();
                    while (read2 >= 0) {
                        int read3 = reader.read();
                        if (read2 == 34) {
                            writer.write(TWO_DOUBLE_QUOTES);
                        } else {
                            writer.write(read2);
                        }
                        read2 = read3;
                    }
                }
                if (max == 1) {
                    writer.write(34);
                }
            }
        }
    }

    static void escape(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (cArr != null && cArr.length != 0) {
            int i3 = i2 + i;
            int i4 = i;
            int i5 = i4;
            while (i4 < i3) {
                char c = cArr[i4];
                if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && (c < '0' || c > '9'))) {
                    if (i5 == i) {
                        writer.write(34);
                    }
                    int i6 = i4 - i5;
                    if (i6 > 0) {
                        writer.write(cArr, i5, i6);
                    }
                    i5 = i4 + 1;
                    if (c == '\"') {
                        writer.write(TWO_DOUBLE_QUOTES);
                    } else {
                        writer.write(c);
                    }
                }
                i4++;
            }
            int i7 = i3 - i5;
            if (i7 > 0) {
                writer.write(cArr, i5, i7);
            }
            if (i5 > i) {
                writer.write(34);
            }
        }
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
        boolean z = false;
        int i4 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if ((i2 <= 0 || charAt == '\"') && charAt == '\"') {
                if (i2 == 0) {
                    int i5 = i2 + 1;
                    if (i5 < length && str.charAt(length - 1) == '\"') {
                        i3 = i5;
                        i4 = i3;
                        z = true;
                    }
                } else {
                    if (z && i2 + 2 < length) {
                        int i6 = i2 + 1;
                        if (str.charAt(i6) == '\"') {
                            i4 = i6;
                        }
                    } else if (z && (i = i2 + 1) >= length) {
                        i4 = i;
                    }
                    if (sb == null) {
                        sb = new StringBuilder(length + 5);
                    }
                    if (i2 - i3 > 0) {
                        sb.append(str, i3, i2);
                    }
                    int i7 = i4 + 1;
                    if (i4 < length) {
                        sb.append(charAt);
                    }
                    i3 = i7;
                    i2 = i4;
                }
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

    static void unescape(Reader reader, Writer writer) throws IOException {
        int i;
        if (reader != null) {
            boolean z = false;
            int read = reader.read();
            if (read >= 0) {
                if (read == 34) {
                    int read2 = reader.read();
                    if (read2 < 0) {
                        writer.write(read);
                        return;
                    } else {
                        read = read2;
                        z = true;
                    }
                }
                while (i >= 0) {
                    int read3 = reader.read();
                    if (i != 34) {
                        writer.write(i);
                    } else if (read3 < 0) {
                        if (!z) {
                            writer.write(i);
                        }
                    } else if (read3 == 34) {
                        writer.write(34);
                        i = reader.read();
                    } else {
                        writer.write(34);
                    }
                    i = read3;
                }
            }
        }
    }

    static void unescape(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3;
        if (cArr != null) {
            int i4 = i2 + i;
            boolean z = false;
            int i5 = i;
            int i6 = i5;
            int i7 = i6;
            while (i5 < i4) {
                char c = cArr[i5];
                if ((i5 <= i || c == '\"') && c == '\"') {
                    if (i5 == i) {
                        int i8 = i5 + 1;
                        if (i8 < i4 && cArr[i4 - 1] == '\"') {
                            i6 = i8;
                            i7 = i6;
                            z = true;
                        }
                    } else {
                        if (z && i5 + 2 < i4) {
                            int i9 = i5 + 1;
                            if (cArr[i9] == '\"') {
                                i7 = i9;
                            }
                        } else if (z && (i3 = i5 + 1) >= i4) {
                            i7 = i3;
                        }
                        int i10 = i5 - i6;
                        if (i10 > 0) {
                            writer.write(cArr, i6, i10);
                        }
                        i6 = i7 + 1;
                        if (i7 < i4) {
                            writer.write(c);
                        }
                        i5 = i7;
                    }
                }
                i5++;
            }
            int i11 = i4 - i6;
            if (i11 > 0) {
                writer.write(cArr, i6, i11);
            }
        }
    }
}
