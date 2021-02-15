package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzejz;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzema implements zzelh {
    private final int flags;
    private final String info;
    private final Object[] zzilp;
    private final zzelj zzils;

    zzema(zzelj zzelj, String str, Object[] objArr) {
        this.zzils = zzelj;
        this.info = str;
        this.zzilp = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        char c = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.flags = c | (charAt2 << i);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzbid() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzbie() {
        return this.zzilp;
    }

    public final zzelj zzbhu() {
        return this.zzils;
    }

    public final int zzbhs() {
        return (this.flags & 1) == 1 ? zzejz.zze.zzijs : zzejz.zze.zzijt;
    }

    public final boolean zzbht() {
        return (this.flags & 2) == 2;
    }
}
