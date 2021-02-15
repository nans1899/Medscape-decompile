package org.apache.commons.io.input;

import java.io.InputStream;
import java.util.Objects;

public class CircularInputStream extends InputStream {
    private long byteCount;
    private int position = -1;
    private final byte[] repeatedContent;
    private final long targetByteCount;

    private static byte[] validate(byte[] bArr) {
        Objects.requireNonNull(bArr, "repeatContent");
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            if (bArr[i] != -1) {
                i++;
            } else {
                throw new IllegalArgumentException("repeatContent contains the end-of-stream marker -1");
            }
        }
        return bArr;
    }

    public CircularInputStream(byte[] bArr, long j) {
        this.repeatedContent = validate(bArr);
        if (bArr.length != 0) {
            this.targetByteCount = j;
            return;
        }
        throw new IllegalArgumentException("repeatContent is empty.");
    }

    public int read() {
        long j = this.targetByteCount;
        if (j >= 0) {
            long j2 = this.byteCount;
            if (j2 == j) {
                return -1;
            }
            this.byteCount = j2 + 1;
        }
        byte[] bArr = this.repeatedContent;
        int length = (this.position + 1) % bArr.length;
        this.position = length;
        return bArr[length] & 255;
    }
}
