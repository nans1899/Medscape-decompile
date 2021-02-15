package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzrj implements Runnable {
    private final /* synthetic */ View zzbtd;
    private final /* synthetic */ zzrk zzbte;

    zzrj(zzrk zzrk, View view) {
        this.zzbte = zzrk;
        this.zzbtd = view;
    }

    public final void run() {
        this.zzbte.zzj(this.zzbtd);
    }
}
