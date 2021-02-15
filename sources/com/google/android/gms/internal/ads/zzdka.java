package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdka implements Runnable {
    private final zzva zzgte;
    private final zzdkb zzhal;

    zzdka(zzdkb zzdkb, zzva zzva) {
        this.zzhal = zzdkb;
        this.zzgte = zzva;
    }

    public final void run() {
        zzdkb zzdkb = this.zzhal;
        zzdkb.zzhan.zzhai.zzk(this.zzgte);
    }
}
