package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdnf implements Runnable {
    private final zzdnc zzhcu;

    zzdnf(zzdnc zzdnc) {
        this.zzhcu = zzdnc;
    }

    public final void run() {
        this.zzhcu.zzhct.zzhcq.onAdLoaded();
    }
}
