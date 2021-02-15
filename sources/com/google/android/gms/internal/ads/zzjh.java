package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzjh {
    private int flags;

    public void clear() {
        this.flags = 0;
    }

    public final boolean zzgk() {
        return zzad(Integer.MIN_VALUE);
    }

    public final boolean zzgl() {
        return zzad(4);
    }

    public final boolean zzgm() {
        return zzad(1);
    }

    public final void setFlags(int i) {
        this.flags = i;
    }

    public final void zzac(int i) {
        this.flags |= Integer.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    public final boolean zzad(int i) {
        return (this.flags & i) == i;
    }
}
