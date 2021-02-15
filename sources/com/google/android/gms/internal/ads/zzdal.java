package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdal implements Runnable {
    private final zzva zzgte;
    private final zzdaj zzgub;

    zzdal(zzdaj zzdaj, zzva zzva) {
        this.zzgub = zzdaj;
        this.zzgte = zzva;
    }

    public final void run() {
        zzdaj zzdaj = this.zzgub;
        zzdaj.zzgua.zzgtw.zzara().zzk(this.zzgte);
    }
}
