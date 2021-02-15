package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzdx;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzfv implements zzff {
    private final int flags;
    private final String info;
    private final zzfh zzmn;
    private final Object[] zzmu;

    zzfv(zzfh zzfh, String str, Object[] objArr) {
        this.zzmn = zzfh;
        this.info = str;
        this.zzmu = objArr;
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
    public final String zzcw() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzcx() {
        return this.zzmu;
    }

    public final zzfh zzcq() {
        return this.zzmn;
    }

    public final int zzco() {
        return (this.flags & 1) == 1 ? zzdx.zze.zzku : zzdx.zze.zzkv;
    }

    public final boolean zzcp() {
        return (this.flags & 2) == 2;
    }
}
