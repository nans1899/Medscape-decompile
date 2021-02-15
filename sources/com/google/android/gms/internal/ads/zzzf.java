package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzzf implements Runnable {
    private final zzzd zzckg;
    private final OnInitializationCompleteListener zzckp;

    zzzf(zzzd zzzd, OnInitializationCompleteListener onInitializationCompleteListener) {
        this.zzckg = zzzd;
        this.zzckp = onInitializationCompleteListener;
    }

    public final void run() {
        this.zzckg.zza(this.zzckp);
    }
}
