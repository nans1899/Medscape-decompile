package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdse implements Runnable {
    private final zzdsa zzhkk;
    private final zzdrr zzhkl;

    zzdse(zzdsa zzdsa, zzdrr zzdrr) {
        this.zzhkk = zzdsa;
        this.zzhkl = zzdrr;
    }

    public final void run() {
        zzdsa zzdsa = this.zzhkk;
        zzdsa.zzhkd.zzhkb.zzb(this.zzhkl);
    }
}
