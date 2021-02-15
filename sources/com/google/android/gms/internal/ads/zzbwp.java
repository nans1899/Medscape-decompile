package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbwp implements Runnable {
    private final WeakReference<zzbwk> zzfvb;

    private zzbwp(zzbwk zzbwk) {
        this.zzfvb = new WeakReference<>(zzbwk);
    }

    public final void run() {
        zzbwk zzbwk = (zzbwk) this.zzfvb.get();
        if (zzbwk != null) {
            zzbwk.zzaka();
        }
    }
}
