package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zze;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbgl implements Runnable {
    private final zzbgi zzeng;

    zzbgl(zzbgi zzbgi) {
        this.zzeng = zzbgi;
    }

    public final void run() {
        zzbgi zzbgi = this.zzeng;
        zzbgi.zzemq.zzacf();
        zze zzabs = zzbgi.zzemq.zzabs();
        if (zzabs != null) {
            zzabs.zzuu();
        }
    }
}
