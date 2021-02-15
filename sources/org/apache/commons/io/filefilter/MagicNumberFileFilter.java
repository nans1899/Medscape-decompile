package org.apache.commons.io.filefilter;

import java.io.Serializable;
import java.nio.charset.Charset;

public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public MagicNumberFileFilter(byte[] bArr) {
        this(bArr, 0);
    }

    public MagicNumberFileFilter(String str) {
        this(str, 0);
    }

    public MagicNumberFileFilter(String str, long j) {
        if (str == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        } else if (j >= 0) {
            this.magicNumbers = str.getBytes(Charset.defaultCharset());
            this.byteOffset = j;
        } else {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
    }

    public MagicNumberFileFilter(byte[] bArr, long j) {
        if (bArr == null) {
            throw new IllegalArgumentException("The magic number cannot be null");
        } else if (bArr.length == 0) {
            throw new IllegalArgumentException("The magic number must contain at least one byte");
        } else if (j >= 0) {
            byte[] bArr2 = new byte[bArr.length];
            this.magicNumbers = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.byteOffset = j;
        } else {
            throw new IllegalArgumentException("The offset cannot be negative");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean accept(java.io.File r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L_0x0043
            boolean r1 = r5.isFile()
            if (r1 == 0) goto L_0x0043
            boolean r1 = r5.canRead()
            if (r1 == 0) goto L_0x0043
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0043 }
            java.lang.String r2 = "r"
            r1.<init>(r5, r2)     // Catch:{ IOException -> 0x0043 }
            byte[] r5 = r4.magicNumbers     // Catch:{ all -> 0x0037 }
            int r5 = r5.length     // Catch:{ all -> 0x0037 }
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x0037 }
            long r2 = r4.byteOffset     // Catch:{ all -> 0x0037 }
            r1.seek(r2)     // Catch:{ all -> 0x0037 }
            int r2 = r1.read(r5)     // Catch:{ all -> 0x0037 }
            byte[] r3 = r4.magicNumbers     // Catch:{ all -> 0x0037 }
            int r3 = r3.length     // Catch:{ all -> 0x0037 }
            if (r2 == r3) goto L_0x002d
            r1.close()     // Catch:{ IOException -> 0x0043 }
            return r0
        L_0x002d:
            byte[] r2 = r4.magicNumbers     // Catch:{ all -> 0x0037 }
            boolean r5 = java.util.Arrays.equals(r2, r5)     // Catch:{ all -> 0x0037 }
            r1.close()     // Catch:{ IOException -> 0x0043 }
            return r5
        L_0x0037:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r2 = move-exception
            r1.close()     // Catch:{ all -> 0x003e }
            goto L_0x0042
        L_0x003e:
            r1 = move-exception
            r5.addSuppressed(r1)     // Catch:{ IOException -> 0x0043 }
        L_0x0042:
            throw r2     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.filefilter.MagicNumberFileFilter.accept(java.io.File):boolean");
    }

    public String toString() {
        return super.toString() + "(" + new String(this.magicNumbers, Charset.defaultCharset()) + "," + this.byteOffset + ")";
    }
}
