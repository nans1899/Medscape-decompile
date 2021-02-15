package com.google.android.gms.internal.ads;

import android.view.MotionEvent;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzacb {
    private MotionEvent zzczr = MotionEvent.obtain(0, 0, 1, 0.0f, 0.0f, 0);
    private MotionEvent zzczs = MotionEvent.obtain(0, 0, 0, 0.0f, 0.0f, 0);

    public final void zza(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && motionEvent.getEventTime() > this.zzczr.getEventTime()) {
            this.zzczr = MotionEvent.obtain(motionEvent);
        } else if (motionEvent.getAction() == 0 && motionEvent.getEventTime() > this.zzczs.getEventTime()) {
            this.zzczs = MotionEvent.obtain(motionEvent);
        }
    }

    public final MotionEvent zzrv() {
        return this.zzczr;
    }

    public final MotionEvent zzrw() {
        return this.zzczs;
    }
}
