package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbhc implements Runnable {
    private final Map zzeei;
    private final zzbhd zzepy;

    zzbhc(zzbhd zzbhd, Map map) {
        this.zzepy = zzbhd;
        this.zzeei = map;
    }

    public final void run() {
        this.zzepy.zzk(this.zzeei);
    }
}
