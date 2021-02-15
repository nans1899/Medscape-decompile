package com.google.android.gms.internal.ads;

import android.util.Log;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzhk {
    public final int index;
    private final zzhx[] zzaep;
    private final zzog zzaeq;
    public final zzmx zzafm;
    public final Object zzafn;
    public final zznm[] zzafo;
    private final boolean[] zzafp;
    public final long zzafq;
    public int zzafr;
    public long zzafs;
    public boolean zzaft;
    public boolean zzafu;
    public boolean zzafv;
    public zzhk zzafw;
    public zzoi zzafx;
    private final zzhw[] zzafy;
    private final zzhs zzafz;
    private final zzmz zzaga;
    private zzoi zzagb;

    public zzhk(zzhx[] zzhxArr, zzhw[] zzhwArr, long j, zzog zzog, zzhs zzhs, zzmz zzmz, Object obj, int i, int i2, boolean z, long j2) {
        this.zzaep = zzhxArr;
        this.zzafy = zzhwArr;
        this.zzafq = j;
        this.zzaeq = zzog;
        this.zzafz = zzhs;
        this.zzaga = zzmz;
        this.zzafn = zzoz.checkNotNull(obj);
        this.index = i;
        this.zzafr = i2;
        this.zzaft = z;
        this.zzafs = j2;
        this.zzafo = new zznm[zzhxArr.length];
        this.zzafp = new boolean[zzhxArr.length];
        this.zzafm = zzmz.zza(i2, zzhs.zzfd());
    }

    public final long zzep() {
        return this.zzafq - this.zzafs;
    }

    public final void zzc(int i, boolean z) {
        this.zzafr = i;
        this.zzaft = z;
    }

    public final boolean zzeq() {
        if (this.zzafu) {
            return !this.zzafv || this.zzafm.zzhr() == Long.MIN_VALUE;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzer() throws com.google.android.gms.internal.ads.zzhd {
        /*
            r6 = this;
            com.google.android.gms.internal.ads.zzog r0 = r6.zzaeq
            com.google.android.gms.internal.ads.zzhw[] r1 = r6.zzafy
            com.google.android.gms.internal.ads.zzmx r2 = r6.zzafm
            com.google.android.gms.internal.ads.zznp r2 = r2.zzho()
            com.google.android.gms.internal.ads.zzoi r0 = r0.zza(r1, r2)
            com.google.android.gms.internal.ads.zzoi r1 = r6.zzagb
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L_0x0016
        L_0x0014:
            r1 = 0
            goto L_0x0028
        L_0x0016:
            r4 = 0
        L_0x0017:
            com.google.android.gms.internal.ads.zzod r5 = r0.zzbhx
            int r5 = r5.length
            if (r4 >= r5) goto L_0x0027
            boolean r5 = r0.zza(r1, r4)
            if (r5 != 0) goto L_0x0024
            goto L_0x0014
        L_0x0024:
            int r4 = r4 + 1
            goto L_0x0017
        L_0x0027:
            r1 = 1
        L_0x0028:
            if (r1 == 0) goto L_0x002b
            return r3
        L_0x002b:
            r6.zzafx = r0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhk.zzer():boolean");
    }

    public final long zzb(long j, boolean z) {
        return zza(j, false, new boolean[this.zzaep.length]);
    }

    public final long zza(long j, boolean z, boolean[] zArr) {
        zzod zzod = this.zzafx.zzbhx;
        int i = 0;
        while (true) {
            boolean z2 = true;
            if (i >= zzod.length) {
                break;
            }
            boolean[] zArr2 = this.zzafp;
            if (z || !this.zzafx.zza(this.zzagb, i)) {
                z2 = false;
            }
            zArr2[i] = z2;
            i++;
        }
        long zza = this.zzafm.zza(zzod.zzim(), this.zzafp, this.zzafo, zArr, j);
        this.zzagb = this.zzafx;
        this.zzafv = false;
        int i2 = 0;
        while (true) {
            zznm[] zznmArr = this.zzafo;
            if (i2 < zznmArr.length) {
                if (zznmArr[i2] != null) {
                    zzoz.checkState(zzod.zzbg(i2) != null);
                    this.zzafv = true;
                } else {
                    zzoz.checkState(zzod.zzbg(i2) == null);
                }
                i2++;
            } else {
                this.zzafz.zza(this.zzaep, this.zzafx.zzbhw, zzod);
                return zza;
            }
        }
    }

    public final void release() {
        try {
            this.zzaga.zzb(this.zzafm);
        } catch (RuntimeException e) {
            Log.e("ExoPlayerImplInternal", "Period release failed.", e);
        }
    }
}
