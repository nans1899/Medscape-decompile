package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzzc implements Runnable {
    private final zzzd zzckg;
    private final Context zzckh;

    zzzc(zzzd zzzd, Context context) {
        this.zzckg = zzzd;
        this.zzckh = context;
    }

    public final void run() {
        this.zzckg.getRewardedVideoAdInstance(this.zzckh);
    }
}
