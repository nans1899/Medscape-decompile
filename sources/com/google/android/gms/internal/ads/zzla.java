package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzla implements zzky {
    private final zzpk zzawi;
    private final int zzaxc = this.zzawi.zzjf();
    private final int zzaxg = this.zzawi.zzjf();

    public zzla(zzkt zzkt) {
        zzpk zzpk = zzkt.zzawi;
        this.zzawi = zzpk;
        zzpk.zzbo(12);
    }

    public final int zzgy() {
        return this.zzaxc;
    }

    public final int zzgz() {
        int i = this.zzaxg;
        return i == 0 ? this.zzawi.zzjf() : i;
    }

    public final boolean zzha() {
        return this.zzaxg != 0;
    }
}
