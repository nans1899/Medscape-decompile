package com.google.android.play.core.assetpacks;

import java.io.IOException;
import java.io.InputStream;

final class be extends InputStream {
    private final InputStream a;
    private long b;

    be(InputStream inputStream, long j) {
        this.a = inputStream;
        this.b = j;
    }

    public final int read() throws IOException {
        long j = this.b;
        if (j <= 0) {
            return -1;
        }
        this.b = j - 1;
        return this.a.read();
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.b;
        if (j <= 0) {
            return -1;
        }
        int read = this.a.read(bArr, i, (int) Math.min((long) i2, j));
        if (read != -1) {
            this.b -= (long) read;
        }
        return read;
    }
}
