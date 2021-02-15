package com.bea.xml.stream.reader;

import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.util.Hashtable;

public final class XmlReader extends Reader {
    private static final int MAXPUSHBACK = 512;
    private static final Hashtable charsets;
    private String assignedEncoding;
    private boolean closed;
    private Reader in;

    public static Reader createReader(InputStream inputStream) throws IOException {
        return new XmlReader(inputStream);
    }

    public static Reader createReader(InputStream inputStream, String str) throws IOException {
        if (str == null) {
            return new XmlReader(inputStream);
        }
        if ("UTF-8".equalsIgnoreCase(str) || "UTF8".equalsIgnoreCase(str)) {
            return new Utf8Reader(inputStream);
        }
        if ("US-ASCII".equalsIgnoreCase(str) || "ASCII".equalsIgnoreCase(str)) {
            return new AsciiReader(inputStream);
        }
        if ("ISO-8859-1".equalsIgnoreCase(str)) {
            return new Iso8859_1Reader(inputStream);
        }
        return new InputStreamReader(inputStream, std2java(str));
    }

    static {
        Hashtable hashtable = new Hashtable(31);
        charsets = hashtable;
        hashtable.put("UTF-16", "Unicode");
        charsets.put("ISO-10646-UCS-2", "Unicode");
        charsets.put("EBCDIC-CP-US", "cp037");
        charsets.put("EBCDIC-CP-CA", "cp037");
        charsets.put("EBCDIC-CP-NL", "cp037");
        charsets.put("EBCDIC-CP-WT", "cp037");
        charsets.put("EBCDIC-CP-DK", "cp277");
        charsets.put("EBCDIC-CP-NO", "cp277");
        charsets.put("EBCDIC-CP-FI", "cp278");
        charsets.put("EBCDIC-CP-SE", "cp278");
        charsets.put("EBCDIC-CP-IT", "cp280");
        charsets.put("EBCDIC-CP-ES", "cp284");
        charsets.put("EBCDIC-CP-GB", "cp285");
        charsets.put("EBCDIC-CP-FR", "cp297");
        charsets.put("EBCDIC-CP-AR1", "cp420");
        charsets.put("EBCDIC-CP-HE", "cp424");
        charsets.put("EBCDIC-CP-BE", "cp500");
        charsets.put("EBCDIC-CP-CH", "cp500");
        charsets.put("EBCDIC-CP-ROECE", "cp870");
        charsets.put("EBCDIC-CP-YU", "cp870");
        charsets.put("EBCDIC-CP-IS", "cp871");
        charsets.put("EBCDIC-CP-AR2", "cp918");
    }

    private static String std2java(String str) {
        String str2 = (String) charsets.get(str.toUpperCase());
        return str2 != null ? str2 : str;
    }

    public String getEncoding() {
        return this.assignedEncoding;
    }

    private XmlReader(InputStream inputStream) throws IOException {
        super(inputStream);
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 512);
        byte[] bArr = new byte[4];
        int read = pushbackInputStream.read(bArr);
        if (read > 0) {
            pushbackInputStream.unread(bArr, 0, read);
        }
        if (read == 4) {
            byte b = bArr[0] & 255;
            if (b != 0) {
                if (b == 60) {
                    byte b2 = bArr[1] & 255;
                    if (b2 != 0) {
                        if (b2 == 63 && bArr[2] == 120 && bArr[3] == 109) {
                            useEncodingDecl(pushbackInputStream, "UTF8");
                            return;
                        }
                    } else if (bArr[2] == 63 && bArr[3] == 0) {
                        setEncoding(pushbackInputStream, "UnicodeLittle");
                        return;
                    }
                } else if (b != 76) {
                    if (b != 254) {
                        if (b == 255 && (bArr[1] & 255) == 254) {
                            setEncoding(pushbackInputStream, "UTF-16");
                            return;
                        }
                    } else if ((bArr[1] & 255) == 255) {
                        setEncoding(pushbackInputStream, "UTF-16");
                        return;
                    }
                } else if (bArr[1] == 111 && (bArr[2] & 255) == 167 && (bArr[3] & 255) == 148) {
                    useEncodingDecl(pushbackInputStream, "CP037");
                    return;
                }
            } else if (bArr[1] == 60 && bArr[2] == 0 && bArr[3] == 63) {
                setEncoding(pushbackInputStream, "UnicodeBig");
                return;
            }
        }
        setEncoding(pushbackInputStream, "UTF-8");
    }

    private void useEncodingDecl(PushbackInputStream pushbackInputStream, String str) throws IOException {
        int read;
        PushbackInputStream pushbackInputStream2 = pushbackInputStream;
        byte[] bArr = new byte[512];
        int i = 0;
        int read2 = pushbackInputStream2.read(bArr, 0, 512);
        pushbackInputStream2.unread(bArr, 0, read2);
        InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(bArr, 4, read2), str);
        if (inputStreamReader.read() != 108) {
            setEncoding(pushbackInputStream2, "UTF-8");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String str2 = null;
        StringBuffer stringBuffer2 = null;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        char c = 0;
        while (true) {
            if (i2 >= 507 || (read = inputStreamReader.read()) == -1) {
                break;
            }
            if (!(read == 32 || read == 9 || read == 10 || read == 13)) {
                if (i2 == 0) {
                    break;
                }
                if (read == 63) {
                    z = true;
                } else if (z) {
                    if (read == 62) {
                        break;
                    }
                    z = false;
                }
                if (str2 != null && z2) {
                    char c2 = (char) read;
                    if (!Character.isWhitespace(c2)) {
                        if (read == 34 || read == 39) {
                            if (c == 0) {
                                stringBuffer.setLength(i);
                                c = c2;
                            } else if (read == c) {
                                if ("encoding".equals(str2)) {
                                    this.assignedEncoding = stringBuffer.toString();
                                    while (i < this.assignedEncoding.length()) {
                                        char charAt = this.assignedEncoding.charAt(i);
                                        if ((charAt >= 'A' && charAt <= 'Z') || ((charAt >= 'a' && charAt <= 'z') || (i != 0 && i > 0 && (charAt == '-' || ((charAt >= '0' && charAt <= '9') || charAt == '.' || charAt == '_'))))) {
                                            i++;
                                        }
                                    }
                                    setEncoding(pushbackInputStream2, this.assignedEncoding);
                                    return;
                                }
                                str2 = null;
                            }
                        }
                        stringBuffer.append(c2);
                    } else {
                        continue;
                    }
                } else if (stringBuffer2 == null) {
                    char c3 = (char) read;
                    if (!Character.isWhitespace(c3)) {
                        stringBuffer.setLength(i);
                        stringBuffer.append(c3);
                        stringBuffer2 = stringBuffer;
                        z2 = false;
                    }
                } else {
                    char c4 = (char) read;
                    if (Character.isWhitespace(c4)) {
                        str2 = stringBuffer2.toString();
                    } else if (read == 61) {
                        if (str2 == null) {
                            str2 = stringBuffer2.toString();
                        }
                        stringBuffer2 = null;
                        z2 = true;
                        c = 0;
                    } else {
                        stringBuffer2.append(c4);
                    }
                }
            }
            i2++;
            i = 0;
        }
        setEncoding(pushbackInputStream2, "UTF-8");
    }

    private void setEncoding(InputStream inputStream, String str) throws IOException {
        this.assignedEncoding = str;
        this.in = createReader(inputStream, str);
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        if (this.closed) {
            return -1;
        }
        int read = this.in.read(cArr, i, i2);
        if (read == -1) {
            close();
        }
        return read;
    }

    public int read() throws IOException {
        if (!this.closed) {
            int read = this.in.read();
            if (read == -1) {
                close();
            }
            return read;
        }
        throw new IOException("Stream closed");
    }

    public boolean markSupported() {
        Reader reader = this.in;
        if (reader == null) {
            return false;
        }
        return reader.markSupported();
    }

    public void mark(int i) throws IOException {
        Reader reader = this.in;
        if (reader != null) {
            reader.mark(i);
        }
    }

    public void reset() throws IOException {
        Reader reader = this.in;
        if (reader != null) {
            reader.reset();
        }
    }

    public long skip(long j) throws IOException {
        Reader reader = this.in;
        if (reader == null) {
            return 0;
        }
        return reader.skip(j);
    }

    public boolean ready() throws IOException {
        Reader reader = this.in;
        if (reader == null) {
            return false;
        }
        return reader.ready();
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.in.close();
            this.in = null;
            this.closed = true;
        }
    }

    public static abstract class BaseReader extends Reader {
        protected byte[] buffer = new byte[8192];
        protected int finish;
        protected InputStream instream;
        protected int start;

        public abstract String getEncoding();

        BaseReader(InputStream inputStream) {
            super(inputStream);
            this.instream = inputStream;
        }

        public boolean ready() throws IOException {
            InputStream inputStream = this.instream;
            return inputStream == null || this.finish - this.start > 0 || inputStream.available() != 0;
        }

        public void close() throws IOException {
            InputStream inputStream = this.instream;
            if (inputStream != null) {
                inputStream.close();
                this.finish = 0;
                this.start = 0;
                this.buffer = null;
                this.instream = null;
            }
        }
    }

    static final class Utf8Reader extends BaseReader {
        private char nextChar;

        public String getEncoding() {
            return "UTF-8";
        }

        Utf8Reader(InputStream inputStream) {
            super(inputStream);
        }

        /* JADX WARNING: Removed duplicated region for block: B:85:0x0142  */
        /* JADX WARNING: Removed duplicated region for block: B:90:0x017d A[LOOP:1: B:90:0x017d->B:93:0x018f, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int read(char[] r10, int r11, int r12) throws java.io.IOException {
            /*
                r9 = this;
                r0 = 0
                if (r12 > 0) goto L_0x0004
                return r0
            L_0x0004:
                int r1 = r11 + r12
                int r2 = r10.length
                if (r1 > r2) goto L_0x01ba
                if (r11 < 0) goto L_0x01ba
                char r1 = r9.nextChar
                r2 = 1
                if (r1 == 0) goto L_0x0018
                int r3 = r11 + 0
                r10[r3] = r1
                r9.nextChar = r0
                r1 = 1
                goto L_0x0019
            L_0x0018:
                r1 = 0
            L_0x0019:
                r3 = 0
            L_0x001a:
                r4 = -1
                if (r1 >= r12) goto L_0x01b3
                int r3 = r9.finish
                int r5 = r9.start
                if (r3 > r5) goto L_0x0041
                java.io.InputStream r3 = r9.instream
                if (r3 != 0) goto L_0x002a
            L_0x0027:
                r3 = -1
                goto L_0x01b3
            L_0x002a:
                r9.start = r0
                java.io.InputStream r3 = r9.instream
                byte[] r5 = r9.buffer
                byte[] r6 = r9.buffer
                int r6 = r6.length
                int r3 = r3.read(r5, r0, r6)
                r9.finish = r3
                int r3 = r9.finish
                if (r3 > 0) goto L_0x0041
                r9.close()
                goto L_0x0027
            L_0x0041:
                byte[] r3 = r9.buffer
                int r4 = r9.start
                byte r3 = r3[r4]
                r3 = r3 & 255(0xff, float:3.57E-43)
                r4 = r3 & 128(0x80, float:1.794E-43)
                if (r4 != 0) goto L_0x005a
                int r4 = r9.start
                int r4 = r4 + r2
                r9.start = r4
                int r4 = r1 + 1
                int r1 = r1 + r11
                char r5 = (char) r3
                r10[r1] = r5
            L_0x0058:
                r1 = r4
                goto L_0x001a
            L_0x005a:
                int r3 = r9.start
                r4 = 192(0xc0, float:2.69E-43)
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                byte r5 = r5[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                r6 = 224(0xe0, float:3.14E-43)
                r5 = r5 & r6
                if (r5 != r4) goto L_0x0082
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                int r6 = r3 + 1
                byte r3 = r5[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                r3 = r3 & 31
                int r3 = r3 << 6
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                int r7 = r6 + 1
                byte r5 = r5[r6]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r5 = r5 & 63
            L_0x0079:
                int r3 = r3 + r5
                goto L_0x013e
            L_0x007c:
                r3 = r7
                goto L_0x013c
            L_0x007f:
                r3 = r6
                goto L_0x013c
            L_0x0082:
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                byte r5 = r5[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                r7 = 240(0xf0, float:3.36E-43)
                r5 = r5 & r7
                if (r5 != r6) goto L_0x00ac
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                int r6 = r3 + 1
                byte r3 = r5[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                r3 = r3 & 15
                int r3 = r3 << 12
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                int r7 = r6 + 1
                byte r5 = r5[r6]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r5 = r5 & 63
                int r5 = r5 << 6
                int r3 = r3 + r5
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                int r6 = r7 + 1
                byte r5 = r5[r7]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                r5 = r5 & 63
                int r3 = r3 + r5
                r7 = r6
                goto L_0x013e
            L_0x00ac:
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                byte r5 = r5[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                r5 = r5 & 248(0xf8, float:3.48E-43)
                if (r5 != r7) goto L_0x0119
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                int r6 = r3 + 1
                byte r3 = r5[r3]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                r3 = r3 & 7
                int r3 = r3 << 18
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                int r7 = r6 + 1
                byte r5 = r5[r6]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r5 = r5 & 63
                int r5 = r5 << 12
                int r3 = r3 + r5
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                int r6 = r7 + 1
                byte r5 = r5[r7]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                r5 = r5 & 63
                int r5 = r5 << 6
                int r3 = r3 + r5
                byte[] r5 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007f }
                int r7 = r6 + 1
                byte r5 = r5[r6]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r5 = r5 & 63
                int r3 = r3 + r5
                r5 = 1114111(0x10ffff, float:1.561202E-39)
                if (r3 > r5) goto L_0x00f9
                r5 = 65535(0xffff, float:9.1834E-41)
                if (r3 <= r5) goto L_0x013e
                r5 = 65536(0x10000, float:9.18355E-41)
                int r3 = r3 - r5
                r5 = 56320(0xdc00, float:7.8921E-41)
                r6 = r3 & 1023(0x3ff, float:1.434E-42)
                int r6 = r6 + r5
                char r5 = (char) r6     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r9.nextChar = r5     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r5 = 55296(0xd800, float:7.7486E-41)
                int r3 = r3 >> 10
                goto L_0x0079
            L_0x00f9:
                java.io.CharConversionException r5 = new java.io.CharConversionException     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r6.<init>()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                java.lang.String r8 = "UTF-8 encoding of character 0x00"
                r6.append(r8)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r6.append(r3)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                java.lang.String r3 = " can't be converted to Unicode."
                r6.append(r3)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                java.lang.String r3 = r6.toString()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                r5.<init>(r3)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
                throw r5     // Catch:{ ArrayIndexOutOfBoundsException -> 0x007c }
            L_0x0119:
                java.io.CharConversionException r5 = new java.io.CharConversionException     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                java.lang.StringBuffer r6 = new java.lang.StringBuffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                r6.<init>()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                java.lang.String r7 = "Unconvertible UTF-8 character beginning with 0x"
                r6.append(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                byte[] r7 = r9.buffer     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                int r8 = r9.start     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                byte r7 = r7[r8]     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                r7 = r7 & 255(0xff, float:3.57E-43)
                java.lang.String r7 = java.lang.Integer.toHexString(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                r6.append(r7)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                java.lang.String r6 = r6.toString()     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                r5.<init>(r6)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
                throw r5     // Catch:{ ArrayIndexOutOfBoundsException -> 0x013c }
            L_0x013c:
                r7 = r3
                r3 = 0
            L_0x013e:
                int r5 = r9.finish
                if (r7 <= r5) goto L_0x017d
                byte[] r4 = r9.buffer
                int r5 = r9.start
                byte[] r6 = r9.buffer
                int r7 = r9.finish
                int r8 = r9.start
                int r7 = r7 - r8
                java.lang.System.arraycopy(r4, r5, r6, r0, r7)
                int r4 = r9.finish
                int r5 = r9.start
                int r4 = r4 - r5
                r9.finish = r4
                r9.start = r0
                java.io.InputStream r4 = r9.instream
                byte[] r5 = r9.buffer
                int r6 = r9.finish
                byte[] r7 = r9.buffer
                int r7 = r7.length
                int r8 = r9.finish
                int r7 = r7 - r8
                int r4 = r4.read(r5, r6, r7)
                if (r4 < 0) goto L_0x0172
                int r5 = r9.finish
                int r5 = r5 + r4
                r9.finish = r5
                goto L_0x001a
            L_0x0172:
                r9.close()
                java.io.CharConversionException r10 = new java.io.CharConversionException
                java.lang.String r11 = "Partial UTF-8 char"
                r10.<init>(r11)
                throw r10
            L_0x017d:
                int r5 = r9.start
                int r5 = r5 + r2
                r9.start = r5
                int r5 = r9.start
                if (r5 >= r7) goto L_0x019d
                byte[] r5 = r9.buffer
                int r6 = r9.start
                byte r5 = r5[r6]
                r5 = r5 & r4
                r6 = 128(0x80, float:1.794E-43)
                if (r5 != r6) goto L_0x0192
                goto L_0x017d
            L_0x0192:
                r9.close()
                java.io.CharConversionException r10 = new java.io.CharConversionException
                java.lang.String r11 = "Malformed UTF-8 char -- is an XML encoding declaration missing?"
                r10.<init>(r11)
                throw r10
            L_0x019d:
                int r4 = r1 + 1
                int r1 = r1 + r11
                char r5 = (char) r3
                r10[r1] = r5
                char r1 = r9.nextChar
                if (r1 == 0) goto L_0x0058
                if (r4 >= r12) goto L_0x0058
                int r5 = r4 + 1
                int r4 = r4 + r11
                r10[r4] = r1
                r9.nextChar = r0
                r1 = r5
                goto L_0x001a
            L_0x01b3:
                if (r1 <= 0) goto L_0x01b6
                return r1
            L_0x01b6:
                if (r3 != r4) goto L_0x01b9
                r0 = -1
            L_0x01b9:
                return r0
            L_0x01ba:
                java.lang.ArrayIndexOutOfBoundsException r10 = new java.lang.ArrayIndexOutOfBoundsException
                r10.<init>()
                throw r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.reader.XmlReader.Utf8Reader.read(char[], int, int):int");
        }
    }

    static final class AsciiReader extends BaseReader {
        public String getEncoding() {
            return "US-ASCII";
        }

        AsciiReader(InputStream inputStream) {
            super(inputStream);
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.instream == null) {
                return -1;
            }
            if (i + i2 > cArr.length || i < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = this.finish - this.start;
            int i4 = 0;
            if (i3 < 1) {
                this.start = 0;
                this.finish = this.instream.read(this.buffer, 0, this.buffer.length);
                if (this.finish <= 0) {
                    close();
                    return -1;
                } else if (i2 > this.finish) {
                    i2 = this.finish;
                }
            } else if (i2 > i3) {
                i2 = i3;
            }
            while (i4 < i2) {
                byte[] bArr = this.buffer;
                int i5 = this.start;
                this.start = i5 + 1;
                byte b = bArr[i5];
                if (b >= 0) {
                    cArr[i + i4] = (char) b;
                    i4++;
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Illegal ASCII character, 0x");
                    stringBuffer.append(Integer.toHexString(b & 255));
                    throw new CharConversionException(stringBuffer.toString());
                }
            }
            return i2;
        }
    }

    static final class Iso8859_1Reader extends BaseReader {
        public String getEncoding() {
            return "ISO-8859-1";
        }

        Iso8859_1Reader(InputStream inputStream) {
            super(inputStream);
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.instream == null) {
                return -1;
            }
            if (i + i2 > cArr.length || i < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = this.finish - this.start;
            if (i3 < 1) {
                this.start = 0;
                this.finish = this.instream.read(this.buffer, 0, this.buffer.length);
                if (this.finish <= 0) {
                    close();
                    return -1;
                } else if (i2 > this.finish) {
                    i2 = this.finish;
                }
            } else if (i2 > i3) {
                i2 = i3;
            }
            for (int i4 = 0; i4 < i2; i4++) {
                byte[] bArr = this.buffer;
                int i5 = this.start;
                this.start = i5 + 1;
                cArr[i + i4] = (char) (bArr[i5] & 255);
            }
            return i2;
        }
    }
}
