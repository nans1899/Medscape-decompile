package org.apache.commons.io.input.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class PeekableInputStream extends CircularBufferInputStream {
    public PeekableInputStream(InputStream inputStream, int i) {
        super(inputStream, i);
    }

    public PeekableInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public boolean peek(byte[] bArr) throws IOException {
        Objects.requireNonNull(bArr, "Buffer");
        if (bArr.length <= this.bufferSize) {
            if (this.buffer.getCurrentNumberOfBytes() < bArr.length) {
                fillBuffer();
            }
            return this.buffer.peek(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("Peek request size of " + bArr.length + " bytes exceeds buffer size of " + this.bufferSize + " bytes");
    }

    public boolean peek(byte[] bArr, int i, int i2) throws IOException {
        Objects.requireNonNull(bArr, "Buffer");
        if (bArr.length <= this.bufferSize) {
            if (this.buffer.getCurrentNumberOfBytes() < bArr.length) {
                fillBuffer();
            }
            return this.buffer.peek(bArr, i, i2);
        }
        throw new IllegalArgumentException("Peek request size of " + bArr.length + " bytes exceeds buffer size of " + this.bufferSize + " bytes");
    }
}
