package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgm implements View.OnAttachStateChangeListener {
    private final /* synthetic */ zzawl zzenh;
    private final /* synthetic */ zzbgi zzeni;

    zzbgm(zzbgi zzbgi, zzawl zzawl) {
        this.zzeni = zzbgi;
        this.zzenh = zzawl;
    }

    public final void onViewDetachedFromWindow(View view) {
    }

    public final void onViewAttachedToWindow(View view) {
        this.zzeni.zza(view, this.zzenh, 10);
    }
}
