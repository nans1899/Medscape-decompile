package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzoq implements zzok {
    private final boolean zzbiv;
    private final int zzbiw;
    private final byte[] zzbix;
    private final zzoh[] zzbiy;
    private int zzbiz;
    private int zzbja;
    private int zzbjb;
    private zzoh[] zzbjc;

    public zzoq(boolean z, int i) {
        this(true, 65536, 0);
    }

    private zzoq(boolean z, int i, int i2) {
        zzoz.checkArgument(true);
        zzoz.checkArgument(true);
        this.zzbiv = true;
        this.zzbiw = 65536;
        this.zzbjb = 0;
        this.zzbjc = new zzoh[100];
        this.zzbix = null;
        this.zzbiy = new zzoh[1];
    }

    public final synchronized void reset() {
        if (this.zzbiv) {
            zzbi(0);
        }
    }

    public final synchronized void zzbi(int i) {
        boolean z = i < this.zzbiz;
        this.zzbiz = i;
        if (z) {
            zzn();
        }
    }

    public final synchronized zzoh zzin() {
        zzoh zzoh;
        this.zzbja++;
        if (this.zzbjb > 0) {
            zzoh[] zzohArr = this.zzbjc;
            int i = this.zzbjb - 1;
            this.zzbjb = i;
            zzoh = zzohArr[i];
            this.zzbjc[i] = null;
        } else {
            zzoh = new zzoh(new byte[this.zzbiw], 0);
        }
        return zzoh;
    }

    public final synchronized void zza(zzoh zzoh) {
        this.zzbiy[0] = zzoh;
        zza(this.zzbiy);
    }

    public final synchronized void zza(zzoh[] zzohArr) {
        boolean z;
        if (this.zzbjb + zzohArr.length >= this.zzbjc.length) {
            this.zzbjc = (zzoh[]) Arrays.copyOf(this.zzbjc, Math.max(this.zzbjc.length << 1, this.zzbjb + zzohArr.length));
        }
        for (zzoh zzoh : zzohArr) {
            if (zzoh.data != null) {
                if (zzoh.data.length != this.zzbiw) {
                    z = false;
                    zzoz.checkArgument(z);
                    zzoh[] zzohArr2 = this.zzbjc;
                    int i = this.zzbjb;
                    this.zzbjb = i + 1;
                    zzohArr2[i] = zzoh;
                }
            }
            z = true;
            zzoz.checkArgument(z);
            zzoh[] zzohArr22 = this.zzbjc;
            int i2 = this.zzbjb;
            this.zzbjb = i2 + 1;
            zzohArr22[i2] = zzoh;
        }
        this.zzbja -= zzohArr.length;
        notifyAll();
    }

    public final synchronized void zzn() {
        int max = Math.max(0, zzpq.zzf(this.zzbiz, this.zzbiw) - this.zzbja);
        if (max < this.zzbjb) {
            Arrays.fill(this.zzbjc, max, this.zzbjb, (Object) null);
            this.zzbjb = max;
        }
    }

    public final synchronized int zzir() {
        return this.zzbja * this.zzbiw;
    }

    public final int zzio() {
        return this.zzbiw;
    }
}
