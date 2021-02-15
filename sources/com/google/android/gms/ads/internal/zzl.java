package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzl implements View.OnTouchListener {
    private final /* synthetic */ zzj zzbpm;

    zzl(zzj zzj) {
        this.zzbpm = zzj;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzbpm.zzbpk == null) {
            return false;
        }
        this.zzbpm.zzbpk.zza(motionEvent);
        return false;
    }
}
