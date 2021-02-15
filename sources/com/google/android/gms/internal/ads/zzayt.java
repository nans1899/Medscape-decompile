package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzayt implements Runnable {
    private final Context zzckh;
    private final String zzdjk;
    private final zzayq zzeax;

    zzayt(zzayq zzayq, Context context, String str) {
        this.zzeax = zzayq;
        this.zzckh = context;
        this.zzdjk = str;
    }

    public final void run() {
        this.zzeax.zzq(this.zzckh, this.zzdjk);
    }
}
