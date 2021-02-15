package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayOutputStream extends AbstractByteArrayOutputStream {
    public ByteArrayOutputStream() {
        this(1024);
    }

    public ByteArrayOutputStream(int i) {
        if (i >= 0) {
            synchronized (this) {
                needNewBuffer(i);
            }
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + i);
    }

    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            synchronized (this) {
                writeImpl(bArr, i, i2);
            }
        }
    }

    public synchronized void write(int i) {
        writeImpl(i);
    }

    public synchronized int write(InputStream inputStream) throws IOException {
        return writeImpl(inputStream);
    }

    public synchronized int size() {
        return this.count;
    }

    public synchronized void reset() {
        resetImpl();
    }

    public synchronized void writeTo(OutputStream outputStream) throws IOException {
        writeToImpl(outputStream);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream) throws IOException {
        return toBufferedInputStream(inputStream, 1024);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.InputStream toBufferedInputStream(java.io.InputStream r1, int r2) throws java.io.IOException {
        /*
            org.apache.commons.io.output.ByteArrayOutputStream r0 = new org.apache.commons.io.output.ByteArrayOutputStream
            r0.<init>(r2)
            r0.write((java.io.InputStream) r1)     // Catch:{ all -> 0x0010 }
            java.io.InputStream r1 = r0.toInputStream()     // Catch:{ all -> 0x0010 }
            r0.close()
            return r1
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.output.ByteArrayOutputStream.toBufferedInputStream(java.io.InputStream, int):java.io.InputStream");
    }

    public synchronized InputStream toInputStream() {
        return toInputStream($$Lambda$K_wNB0HwSLZ6a5BKmNRiunyreo.INSTANCE);
    }

    public synchronized byte[] toByteArray() {
        return toByteArrayImpl();
    }
}
