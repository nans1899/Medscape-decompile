package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcgq implements zzdvu<zzy, Bitmap> {
    private final /* synthetic */ double zzgct;
    private final /* synthetic */ boolean zzgcu;
    private final /* synthetic */ zzcgn zzgcv;

    zzcgq(zzcgn zzcgn, double d, boolean z) {
        this.zzgcv = zzcgn;
        this.zzgct = d;
        this.zzgcu = z;
    }

    public final /* synthetic */ Object apply(Object obj) {
        return this.zzgcv.zza(((zzy) obj).data, this.zzgct, this.zzgcu);
    }
}
