package org.apache.commons.io.input;

public class InfiniteCircularInputStream extends CircularInputStream {
    public InfiniteCircularInputStream(byte[] bArr) {
        super(bArr, -1);
    }
}
