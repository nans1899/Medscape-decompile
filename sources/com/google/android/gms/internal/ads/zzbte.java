package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbte implements zzeoy<zzdvu<zzdnv, zzazp>> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfmh;
    private final zzeph<zzdok> zzfqr;

    public zzbte(zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzdok> zzeph3) {
        this.zzesu = zzeph;
        this.zzfmh = zzeph2;
        this.zzfqr = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return (zzdvu) zzepe.zza(new zzbtb(this.zzesu.get(), this.zzfmh.get(), this.zzfqr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
