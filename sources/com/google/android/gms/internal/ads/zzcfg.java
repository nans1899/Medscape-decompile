package com.google.android.gms.internal.ads;

import android.view.ViewGroup;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcfg implements Runnable {
    private final zzcfe zzgbg;
    private final ViewGroup zzgbn;

    zzcfg(zzcfe zzcfe, ViewGroup viewGroup) {
        this.zzgbg = zzcfe;
        this.zzgbn = viewGroup;
    }

    public final void run() {
        this.zzgbg.zzb(this.zzgbn);
    }
}
