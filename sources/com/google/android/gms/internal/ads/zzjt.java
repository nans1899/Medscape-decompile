package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzjt implements zzke {
    private final int length;
    private final long zzaih;
    private final int[] zzaon;
    private final long[] zzaoo;
    private final long[] zzaop;
    private final long[] zzaoq;

    public zzjt(int[] iArr, long[] jArr, long[] jArr2, long[] jArr3) {
        this.zzaon = iArr;
        this.zzaoo = jArr;
        this.zzaop = jArr2;
        this.zzaoq = jArr3;
        int length2 = iArr.length;
        this.length = length2;
        if (length2 > 0) {
            this.zzaih = jArr2[length2 - 1] + jArr3[length2 - 1];
        } else {
            this.zzaih = 0;
        }
    }

    public final boolean isSeekable() {
        return true;
    }

    public final long getDurationUs() {
        return this.zzaih;
    }

    public final long zzdz(long j) {
        return this.zzaoo[zzpq.zza(this.zzaoq, j, true, true)];
    }
}
