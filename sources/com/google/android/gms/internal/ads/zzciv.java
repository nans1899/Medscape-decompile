package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzv;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzciv implements zzv {
    private final zzbvh zzger;

    private zzciv(zzbvh zzbvh) {
        this.zzger = zzbvh;
    }

    static zzv zza(zzbvh zzbvh) {
        return new zzciv(zzbvh);
    }

    public final void zzvd() {
        this.zzger.onAdLeftApplication();
    }
}
