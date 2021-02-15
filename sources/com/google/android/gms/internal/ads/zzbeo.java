package com.google.android.gms.internal.ads;

import androidx.lifecycle.CoroutineLiveDataKt;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbeo implements zzhs {
    private int zzbiz;
    private final zzoq zzekj;
    private long zzekk;
    private long zzekl;
    private long zzekm;
    private long zzekn;
    private boolean zzeko;

    zzbeo() {
        this(15000, 30000, 2500, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    }

    private zzbeo(int i, int i2, long j, long j2) {
        this.zzekj = new zzoq(true, 65536);
        this.zzekk = 15000000;
        this.zzekl = 30000000;
        this.zzekm = 2500000;
        this.zzekn = 5000000;
    }

    public final void zzfb() {
        zzk(false);
    }

    public final void zza(zzhx[] zzhxArr, zznp zznp, zzod zzod) {
        this.zzbiz = 0;
        for (int i = 0; i < zzhxArr.length; i++) {
            if (zzod.zzbg(i) != null) {
                this.zzbiz += zzpq.zzbs(zzhxArr[i].getTrackType());
            }
        }
        this.zzekj.zzbi(this.zzbiz);
    }

    public final void onStopped() {
        zzk(true);
    }

    public final void zzfc() {
        zzk(true);
    }

    public final zzok zzfd() {
        return this.zzekj;
    }

    public final synchronized boolean zzc(long j, boolean z) {
        boolean z2;
        long j2 = z ? this.zzekn : this.zzekm;
        if (j2 <= 0 || j >= j2) {
            z2 = true;
        } else {
            z2 = false;
        }
        return z2;
    }

    public final synchronized boolean zzdt(long j) {
        boolean z;
        char c;
        z = false;
        if (j > this.zzekl) {
            c = 0;
        } else {
            c = j < this.zzekk ? (char) 2 : 1;
        }
        boolean z2 = this.zzekj.zzir() >= this.zzbiz;
        if (c == 2 || (c == 1 && this.zzeko && !z2)) {
            z = true;
        }
        this.zzeko = z;
        return z;
    }

    public final synchronized void zzds(int i) {
        this.zzekk = ((long) i) * 1000;
    }

    public final synchronized void zzdt(int i) {
        this.zzekl = ((long) i) * 1000;
    }

    public final synchronized void zzdn(int i) {
        this.zzekm = ((long) i) * 1000;
    }

    public final synchronized void zzdo(int i) {
        this.zzekn = ((long) i) * 1000;
    }

    private final void zzk(boolean z) {
        this.zzbiz = 0;
        this.zzeko = false;
        if (z) {
            this.zzekj.reset();
        }
    }
}
