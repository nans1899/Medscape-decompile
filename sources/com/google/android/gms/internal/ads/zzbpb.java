package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbpb implements zzeoy<zzcab<zzbvs>> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfmh;
    private final zzeph<zzdnv> zzfou;
    private final zzbot zzfqp;
    private final zzeph<zzdok> zzfqr;

    public zzbpb(zzbot zzbot, zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzdnv> zzeph3, zzeph<zzdok> zzeph4) {
        this.zzfqp = zzbot;
        this.zzesu = zzeph;
        this.zzfmh = zzeph2;
        this.zzfou = zzeph3;
        this.zzfqr = zzeph4;
    }

    public static zzcab<zzbvs> zza(zzbot zzbot, Context context, zzbbx zzbbx, zzdnv zzdnv, zzdok zzdok) {
        return (zzcab) zzepe.zza(new zzcab(new zzbow(context, zzbbx, zzdnv, zzdok), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfqp, this.zzesu.get(), this.zzfmh.get(), this.zzfou.get(), this.zzfqr.get());
    }
}
