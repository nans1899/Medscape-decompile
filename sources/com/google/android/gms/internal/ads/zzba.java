package com.google.android.gms.internal.ads;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzba extends FilterInputStream {
    private final long zzcp;
    private long zzcq;

    zzba(InputStream inputStream, long j) {
        super(inputStream);
        this.zzcp = j;
    }

    public final int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.zzcq++;
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.zzcq += (long) read;
        }
        return read;
    }

    /* access modifiers changed from: package-private */
    public final long zzp() {
        return this.zzcp - this.zzcq;
    }
}
