package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbgn implements Runnable {
    private final /* synthetic */ View val$view;
    private final /* synthetic */ zzawl zzenh;
    private final /* synthetic */ zzbgi zzeni;
    private final /* synthetic */ int zzenj;

    zzbgn(zzbgi zzbgi, View view, zzawl zzawl, int i) {
        this.zzeni = zzbgi;
        this.val$view = view;
        this.zzenh = zzawl;
        this.zzenj = i;
    }

    public final void run() {
        this.zzeni.zza(this.val$view, this.zzenh, this.zzenj - 1);
    }
}
