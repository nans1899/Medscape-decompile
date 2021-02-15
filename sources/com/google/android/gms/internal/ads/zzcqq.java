package com.google.android.gms.internal.ads;

import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcqq implements zzdya {
    private final zzatc zzfvc;

    zzcqq(zzatc zzatc) {
        this.zzfvc = zzatc;
    }

    public final zzdzc zzf(Object obj) {
        zzatc zzatc = this.zzfvc;
        zzatc.zzduc = new String(zzdxi.toByteArray((InputStream) obj), zzdvs.UTF_8);
        return zzdyq.zzaf(zzatc);
    }
}
