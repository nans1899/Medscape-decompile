package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zze;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbia implements Runnable {
    private final zzbib zzeqo;

    zzbia(zzbib zzbib) {
        this.zzeqo = zzbib;
    }

    public final void run() {
        zzbib zzbib = this.zzeqo;
        zzbib.zzemq.zzacf();
        zze zzabs = zzbib.zzemq.zzabs();
        if (zzabs != null) {
            zzabs.zzuu();
        }
    }
}
