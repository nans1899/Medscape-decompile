package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzea implements Runnable {
    private final /* synthetic */ Context zzxp;

    zzea(zzdy zzdy, Context context) {
        this.zzxp = context;
    }

    public final void run() {
        try {
            zzdy.zzxe.zzb(this.zzxp);
        } catch (Exception e) {
            zzdy.zzxg.zza(2019, -1, e);
        }
    }
}
