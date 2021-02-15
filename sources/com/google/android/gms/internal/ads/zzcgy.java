package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcgy implements zzdvu {
    private final String zzdgt;
    private final int zzebt;
    private final int zzebu;
    private final double zzgdp;

    zzcgy(String str, double d, int i, int i2) {
        this.zzdgt = str;
        this.zzgdp = d;
        this.zzebt = i;
        this.zzebu = i2;
    }

    public final Object apply(Object obj) {
        String str = this.zzdgt;
        return new zzadq(new BitmapDrawable(Resources.getSystem(), (Bitmap) obj), Uri.parse(str), this.zzgdp, this.zzebt, this.zzebu);
    }
}
