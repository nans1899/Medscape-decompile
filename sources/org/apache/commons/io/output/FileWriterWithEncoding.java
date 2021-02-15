package org.apache.commons.io.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class FileWriterWithEncoding extends Writer {
    private final Writer out;

    public FileWriterWithEncoding(String str, String str2) throws IOException {
        this(new File(str), str2, false);
    }

    public FileWriterWithEncoding(String str, String str2, boolean z) throws IOException {
        this(new File(str), str2, z);
    }

    public FileWriterWithEncoding(String str, Charset charset) throws IOException {
        this(new File(str), charset, false);
    }

    public FileWriterWithEncoding(String str, Charset charset, boolean z) throws IOException {
        this(new File(str), charset, z);
    }

    public FileWriterWithEncoding(String str, CharsetEncoder charsetEncoder) throws IOException {
        this(new File(str), charsetEncoder, false);
    }

    public FileWriterWithEncoding(String str, CharsetEncoder charsetEncoder, boolean z) throws IOException {
        this(new File(str), charsetEncoder, z);
    }

    public FileWriterWithEncoding(File file, String str) throws IOException {
        this(file, str, false);
    }

    public FileWriterWithEncoding(File file, String str, boolean z) throws IOException {
        this.out = initWriter(file, str, z);
    }

    public FileWriterWithEncoding(File file, Charset charset) throws IOException {
        this(file, charset, false);
    }

    public FileWriterWithEncoding(File file, Charset charset, boolean z) throws IOException {
        this.out = initWriter(file, charset, z);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder) throws IOException {
        this(file, charsetEncoder, false);
    }

    public FileWriterWithEncoding(File file, CharsetEncoder charsetEncoder, boolean z) throws IOException {
        this.out = initWriter(file, charsetEncoder, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.Writer initWriter(java.io.File r3, java.lang.Object r4, boolean r5) throws java.io.IOException {
        /*
            java.lang.String r0 = "file"
            java.util.Objects.requireNonNull(r3, r0)
            java.lang.String r0 = "encoding"
            java.util.Objects.requireNonNull(r4, r0)
            boolean r0 = r3.exists()
            r1 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x003b, RuntimeException -> 0x0039 }
            r2.<init>(r3, r5)     // Catch:{ IOException -> 0x003b, RuntimeException -> 0x0039 }
            boolean r5 = r4 instanceof java.nio.charset.Charset     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            if (r5 == 0) goto L_0x0020
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            java.nio.charset.Charset r4 = (java.nio.charset.Charset) r4     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            r5.<init>(r2, r4)     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            return r5
        L_0x0020:
            boolean r5 = r4 instanceof java.nio.charset.CharsetEncoder     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            if (r5 == 0) goto L_0x002c
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            java.nio.charset.CharsetEncoder r4 = (java.nio.charset.CharsetEncoder) r4     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            r5.<init>(r2, r4)     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            return r5
        L_0x002c:
            java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            r5.<init>(r2, r4)     // Catch:{ IOException -> 0x0036, RuntimeException -> 0x0034 }
            return r5
        L_0x0034:
            r4 = move-exception
            goto L_0x0037
        L_0x0036:
            r4 = move-exception
        L_0x0037:
            r1 = r2
            goto L_0x003c
        L_0x0039:
            r4 = move-exception
            goto L_0x003c
        L_0x003b:
            r4 = move-exception
        L_0x003c:
            org.apache.commons.io.IOUtils.close((java.io.Closeable) r1)     // Catch:{ IOException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r5 = move-exception
            r4.addSuppressed(r5)
        L_0x0044:
            if (r0 != 0) goto L_0x0049
            org.apache.commons.io.FileUtils.deleteQuietly(r3)
        L_0x0049:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.output.FileWriterWithEncoding.initWriter(java.io.File, java.lang.Object, boolean):java.io.Writer");
    }

    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public void write(char[] cArr) throws IOException {
        this.out.write(cArr);
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        this.out.write(cArr, i, i2);
    }

    public void write(String str) throws IOException {
        this.out.write(str);
    }

    public void write(String str, int i, int i2) throws IOException {
        this.out.write(str, i, i2);
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public void close() throws IOException {
        this.out.close();
    }
}
