package com.google.android.gms.internal.ads;

import android.view.MotionEvent;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzeb implements Runnable {
    private final /* synthetic */ MotionEvent zzxs;

    zzeb(zzdy zzdy, MotionEvent motionEvent) {
        this.zzxs = motionEvent;
    }

    public final void run() {
        try {
            zzdy.zzxe.zza(this.zzxs);
        } catch (Exception e) {
            zzdy.zzxg.zza(2022, -1, e);
        }
    }
}
