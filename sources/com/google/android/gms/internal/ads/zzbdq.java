package com.google.android.gms.internal.ads;

import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbdq implements Runnable {
    zzbdq(zzbdr zzbdr) {
    }

    public final void run() {
        Looper.myLooper().quit();
    }
}
