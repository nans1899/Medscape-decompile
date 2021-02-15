package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbl implements zzeoy<zzcab<zzbvs>> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfmh;
    private final zzeph<zzdnv> zzfou;
    private final zzeph<zzdok> zzfqr;
    private final zzcbf zzfwx;

    private zzcbl(zzcbf zzcbf, zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzdok> zzeph4) {
        this.zzfwx = zzcbf;
        this.zzesu = zzeph;
        this.zzfmh = zzeph2;
        this.zzfou = zzeph3;
        this.zzfqr = zzeph4;
    }

    public static zzcbl zza(zzcbf zzcbf, zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzdok> zzeph4) {
        return new zzcbl(zzcbf, zzeph, zzeph2, zzeph3, zzeph4);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(new zzcbi(this.zzesu.get(), this.zzfmh.get(), this.zzfou.get(), this.zzfqr.get()), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
