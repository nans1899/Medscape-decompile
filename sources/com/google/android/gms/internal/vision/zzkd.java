package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzkd implements zzjl {
    private final int flags;
    private final String info;
    private final Object[] zzaal;
    private final zzjn zzaao;

    zzkd(zzjn zzjn, String str, Object[] objArr) {
        this.zzaao = zzjn;
        this.info = str;
        this.zzaal = objArr;
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
    public final String zzil() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzim() {
        return this.zzaal;
    }

    public final zzjn zzif() {
        return this.zzaao;
    }

    public final int zzid() {
        return (this.flags & 1) == 1 ? zzjy.zzabd : zzjy.zzabe;
    }

    public final boolean zzie() {
        return (this.flags & 2) == 2;
    }
}
