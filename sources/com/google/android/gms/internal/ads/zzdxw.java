package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdxw implements Runnable {
    private final /* synthetic */ zzdwk zzhsl;
    private final /* synthetic */ zzdxu zzhsm;

    zzdxw(zzdxu zzdxu, zzdwk zzdwk) {
        this.zzhsm = zzdxu;
        this.zzhsl = zzdwk;
    }

    public final void run() {
        this.zzhsm.zza(this.zzhsl);
    }
}
