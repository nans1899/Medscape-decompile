package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzm implements Runnable {
    private final zzj zzdpj;
    private final Drawable zzdpk;

    zzm(zzj zzj, Drawable drawable) {
        this.zzdpj = zzj;
        this.zzdpk = drawable;
    }

    public final void run() {
        zzj zzj = this.zzdpj;
        zzj.zzdph.zzaas.getWindow().setBackgroundDrawable(this.zzdpk);
    }
}
