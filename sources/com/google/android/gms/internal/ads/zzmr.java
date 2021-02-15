package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzmr implements Runnable {
    private final /* synthetic */ zzmn zzbek;
    private final /* synthetic */ IOException zzbel;

    zzmr(zzmn zzmn, IOException iOException) {
        this.zzbek = zzmn;
        this.zzbel = iOException;
    }

    public final void run() {
        this.zzbek.zzbdk.zzb(this.zzbel);
    }
}
