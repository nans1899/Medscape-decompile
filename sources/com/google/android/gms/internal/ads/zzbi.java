package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbi extends ByteArrayOutputStream {
    private final zzat zzch;

    public zzbi(zzat zzat, int i) {
        this.zzch = zzat;
        this.buf = zzat.zzf(Math.max(i, 256));
    }

    public final void close() throws IOException {
        this.zzch.zza(this.buf);
        this.buf = null;
        super.close();
    }

    public final void finalize() {
        this.zzch.zza(this.buf);
    }

    private final void zzg(int i) {
        if (this.count + i > this.buf.length) {
            byte[] zzf = this.zzch.zzf((this.count + i) << 1);
            System.arraycopy(this.buf, 0, zzf, 0, this.count);
            this.zzch.zza(this.buf);
            this.buf = zzf;
        }
    }

    public final synchronized void write(byte[] bArr, int i, int i2) {
        zzg(i2);
        super.write(bArr, i, i2);
    }

    public final synchronized void write(int i) {
        zzg(1);
        super.write(i);
    }
}
