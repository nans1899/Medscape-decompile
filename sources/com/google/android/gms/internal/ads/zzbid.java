package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbid implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzawl zzenh;
    private final /* synthetic */ int zzenj;
    private final /* synthetic */ zzbib zzeqq;

    zzbid(zzbib zzbib, View view, zzawl zzawl, int i) {
        this.zzeqq = zzbib;
        this.val$view = view;
        this.zzenh = zzawl;
        this.zzenj = i;
    }

    public final void run() {
        this.zzeqq.zza(this.val$view, this.zzenh, this.zzenj - 1);
    }
}
