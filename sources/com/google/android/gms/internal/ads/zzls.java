package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzls {
    public final int[] zzaon;
    public final long[] zzaoo;
    public final int zzaxc;
    public final int zzaxi;
    public final int[] zzaxk;
    public final long[] zzbbh;

    public zzls(long[] jArr, int[] iArr, int i, long[] jArr2, int[] iArr2) {
        boolean z = true;
        zzoz.checkArgument(iArr.length == jArr2.length);
        zzoz.checkArgument(jArr.length == jArr2.length);
        zzoz.checkArgument(iArr2.length != jArr2.length ? false : z);
        this.zzaoo = jArr;
        this.zzaon = iArr;
        this.zzaxi = i;
        this.zzbbh = jArr2;
        this.zzaxk = iArr2;
        this.zzaxc = jArr.length;
    }

    public final int zzec(long j) {
        for (int zza = zzpq.zza(this.zzbbh, j, true, false); zza >= 0; zza--) {
            if ((this.zzaxk[zza] & 1) != 0) {
                return zza;
            }
        }
        return -1;
    }

    public final int zzed(long j) {
        for (int zzb = zzpq.zzb(this.zzbbh, j, true, false); zzb < this.zzbbh.length; zzb++) {
            if ((this.zzaxk[zzb] & 1) != 0) {
                return zzb;
            }
        }
        return -1;
    }
}
