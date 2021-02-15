package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdz implements Runnable {
    private final /* synthetic */ Context zzxp;
    private final /* synthetic */ View zzxq;
    private final /* synthetic */ Activity zzxr;

    zzdz(zzdy zzdy, Context context, View view, Activity activity) {
        this.zzxp = context;
        this.zzxq = view;
        this.zzxr = activity;
    }

    public final void run() {
        try {
            zzdy.zzxe.zza(this.zzxp, this.zzxq, this.zzxr);
        } catch (Exception e) {
            zzdy.zzxg.zza(2020, -1, e);
        }
    }
}
