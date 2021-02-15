package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzawb implements Runnable {
    private final zzavy zzdwx;
    private final Bitmap zzdwy;

    zzawb(zzavy zzavy, Bitmap bitmap) {
        this.zzdwx = zzavy;
        this.zzdwy = bitmap;
    }

    public final void run() {
        this.zzdwx.zza(this.zzdwy);
    }
}
