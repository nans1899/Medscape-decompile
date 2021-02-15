package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbic implements View.OnAttachStateChangeListener {
    private final /* synthetic */ zzawl zzenh;
    private final /* synthetic */ zzbib zzeqq;

    zzbic(zzbib zzbib, zzawl zzawl) {
        this.zzeqq = zzbib;
        this.zzenh = zzawl;
    }

    public final void onViewDetachedFromWindow(View view) {
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzeqq.zza(view, this.zzenh, 10);
    }
}
