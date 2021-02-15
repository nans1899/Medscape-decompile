package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbq implements zzeoy<zzcab<zzp>> {
    private final zzeph<zzcco> zzfpj;
    private final zzcbf zzfwx;

    private zzcbq(zzcbf zzcbf, zzeph<zzcco> zzeph) {
        this.zzfwx = zzcbf;
        this.zzfpj = zzeph;
    }

    public static zzcbq zzd(zzcbf zzcbf, zzeph<zzcco> zzeph) {
        return new zzcbq(zzcbf, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), zzbbz.zzeet), "Cannot return null from a non-@Nullable @Provides method");
    }
}
