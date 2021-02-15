package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzamv implements Runnable {
    private final Context zzckh;
    private final zzamt zzdjj;

    zzamv(zzamt zzamt, Context context) {
        this.zzdjj = zzamt;
        this.zzckh = context;
    }

    public final void run() {
        zzamt.zzo(this.zzckh);
    }
}
