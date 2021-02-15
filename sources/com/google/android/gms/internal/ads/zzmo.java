package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzmo implements zznl {
    private final zznl[] zzbej;

    public zzmo(zznl[] zznlArr) {
        this.zzbej = zznlArr;
    }

    public final long zzhp() {
        long j = Long.MAX_VALUE;
        for (zznl zzhp : this.zzbej) {
            long zzhp2 = zzhp.zzhp();
            if (zzhp2 != Long.MIN_VALUE) {
                j = Math.min(j, zzhp2);
            }
        }
        if (j == Long.MAX_VALUE) {
            return Long.MIN_VALUE;
        }
        return j;
    }

    public final boolean zzef(long j) {
        boolean z;
        boolean z2 = false;
        do {
            long zzhp = zzhp();
            if (zzhp == Long.MIN_VALUE) {
                break;
            }
            z = false;
            for (zznl zznl : this.zzbej) {
                if (zznl.zzhp() == zzhp) {
                    z |= zznl.zzef(j);
                }
            }
            z2 |= z;
        } while (z);
        return z2;
    }
}
