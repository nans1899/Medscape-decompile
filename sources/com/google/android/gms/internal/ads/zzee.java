package com.google.android.gms.internal.ads;

import android.view.MotionEvent;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzee implements Runnable {
    private final /* synthetic */ int zzxu;
    private final /* synthetic */ int zzxv;
    private final /* synthetic */ int zzxw;

    zzee(zzdy zzdy, int i, int i2, int i3) {
        this.zzxu = i;
        this.zzxv = i2;
        this.zzxw = i3;
    }

    public final void run() {
        try {
            zzdy.zzxe.zza(MotionEvent.obtain(0, (long) this.zzxu, 0, (float) this.zzxv, (float) this.zzxw, 0));
        } catch (Exception e) {
            zzdy.zzxg.zza(2022, -1, e);
        }
    }
}
