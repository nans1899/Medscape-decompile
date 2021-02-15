package com.google.android.gms.internal.ads;

import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcum implements zzdya {
    private final Uri zzebh;
    private final zzdog zzgoa;
    private final zzcuj zzgot;
    private final zzdnv zzgou;

    zzcum(zzcuj zzcuj, Uri uri, zzdog zzdog, zzdnv zzdnv) {
        this.zzgot = zzcuj;
        this.zzebh = uri;
        this.zzgoa = zzdog;
        this.zzgou = zzdnv;
    }

    public final zzdzc zzf(Object obj) {
        return this.zzgot.zza(this.zzebh, this.zzgoa, this.zzgou, obj);
    }
}
