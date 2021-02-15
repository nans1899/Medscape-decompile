package com.google.android.gms.internal.ads;

import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdaw implements zzdvu {
    private final Uri zzebh;
    private final zzdaq zzguc;

    zzdaw(zzdaq zzdaq, Uri uri) {
        this.zzguc = zzdaq;
        this.zzebh = uri;
    }

    public final Object apply(Object obj) {
        return zzdaq.zzc(this.zzebh, (String) obj);
    }
}
