package org.apache.commons.io.output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class UnsynchronizedByteArrayOutputStream extends AbstractByteArrayOutputStream {
    public UnsynchronizedByteArrayOutputStream() {
        this(1024);
    }

    public UnsynchronizedByteArrayOutputStream(int i) {
        if (i >= 0) {
            needNewBuffer(i);
            return;
        }
        throw new IllegalArgumentException("Negative initial size: " + i);
    }

    public void write(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException(String.format("offset=%,d, length=%,d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        } else if (i2 != 0) {
            writeImpl(bArr, i, i2);
        }
    }

    public void write(int i) {
        writeImpl(i);
    }

    public int write(InputStream inputStream) throws IOException {
        return writeImpl(inputStream);
    }

    public int size() {
        return this.count;
    }

    public void reset() {
        resetImpl();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
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
            org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream r0 = new org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream.toBufferedInputStream(java.io.InputStream, int):java.io.InputStream");
    }

    public InputStream toInputStream() {
        return toInputStream($$Lambda$qVVqGGHzb92MzMLlu6a3zYTvls.INSTANCE);
    }

    public byte[] toByteArray() {
        return toByteArrayImpl();
    }
}
