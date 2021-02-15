package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzamw implements Runnable {
    private final Context zzckh;
    private final zzamt zzdjj;
    private final String zzdjk;

    zzamw(zzamt zzamt, Context context, String str) {
        this.zzdjj = zzamt;
        this.zzckh = context;
        this.zzdjk = str;
    }

    public final void run() {
        zzamt.zze(this.zzckh, this.zzdjk);
    }
}
