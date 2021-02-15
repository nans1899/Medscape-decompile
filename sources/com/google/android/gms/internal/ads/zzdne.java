package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdne implements Runnable {
    private final zzva zzgte;
    private final zzdnc zzhcu;

    zzdne(zzdnc zzdnc, zzva zzva) {
        this.zzhcu = zzdnc;
        this.zzgte = zzva;
    }

    public final void run() {
        zzdnc zzdnc = this.zzhcu;
        zzdnc.zzhct.zzhcq.zzk(this.zzgte);
    }
}
