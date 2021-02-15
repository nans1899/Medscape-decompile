package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzemg extends InputStream {
    private int mark;
    private final /* synthetic */ zzemc zzimn;
    private zzemd zzimx;
    private zzeiw zzimy;
    private int zzimz;
    private int zzina;
    private int zzinb;

    public zzemg(zzemc zzemc) {
        this.zzimn = zzemc;
        initialize();
    }

    public final boolean markSupported() {
        return true;
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw null;
        } else if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else {
            int zzl = zzl(bArr, i, i2);
            if (zzl == 0) {
                return -1;
            }
            return zzl;
        }
    }

    public final long skip(long j) {
        if (j >= 0) {
            if (j > 2147483647L) {
                j = 2147483647L;
            }
            return (long) zzl((byte[]) null, 0, (int) j);
        }
        throw new IndexOutOfBoundsException();
    }

    private final int zzl(byte[] bArr, int i, int i2) {
        int i3 = i2;
        while (i3 > 0) {
            zzbig();
            if (this.zzimy == null) {
                break;
            }
            int min = Math.min(this.zzimz - this.zzina, i3);
            if (bArr != null) {
                this.zzimy.zza(bArr, this.zzina, i, min);
                i += min;
            }
            this.zzina += min;
            i3 -= min;
        }
        return i2 - i3;
    }

    public final int read() throws IOException {
        zzbig();
        zzeiw zzeiw = this.zzimy;
        if (zzeiw == null) {
            return -1;
        }
        int i = this.zzina;
        this.zzina = i + 1;
        return zzeiw.zzfu(i) & 255;
    }

    public final int available() throws IOException {
        return this.zzimn.size() - (this.zzinb + this.zzina);
    }

    public final void mark(int i) {
        this.mark = this.zzinb + this.zzina;
    }

    public final synchronized void reset() {
        initialize();
        zzl((byte[]) null, 0, this.mark);
    }

    private final void initialize() {
        zzemd zzemd = new zzemd(this.zzimn, (zzemb) null);
        this.zzimx = zzemd;
        zzeiw zzeiw = (zzeiw) zzemd.next();
        this.zzimy = zzeiw;
        this.zzimz = zzeiw.size();
        this.zzina = 0;
        this.zzinb = 0;
    }

    private final void zzbig() {
        int i;
        if (this.zzimy != null && this.zzina == (i = this.zzimz)) {
            this.zzinb += i;
            this.zzina = 0;
            if (this.zzimx.hasNext()) {
                zzeiw zzeiw = (zzeiw) this.zzimx.next();
                this.zzimy = zzeiw;
                this.zzimz = zzeiw.size();
                return;
            }
            this.zzimy = null;
            this.zzimz = 0;
        }
    }
}
