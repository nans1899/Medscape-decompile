package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcjz implements View.OnTouchListener {
    private final zzcjv zzgfm;

    zzcjz(zzcjv zzcjv) {
        this.zzgfm = zzcjv;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return this.zzgfm.zza(view, motionEvent);
    }
}
