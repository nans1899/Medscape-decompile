package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmh implements zzeoy<zzcmi> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdow> zzeuo;
    private final zzeph<zzcmu> zzeup;
    private final zzeph<zzdnv> zzfno;
    private final zzeph<zzdog> zzggx;
    private final zzeph<zzcsp> zzggy;

    private zzcmh(zzeph<Context> zzeph, zzeph<zzdow> zzeph2, zzeph<zzcmu> zzeph3, zzeph<zzdog> zzeph4, zzeph<zzdnv> zzeph5, zzeph<zzcsp> zzeph6) {
        this.zzesu = zzeph;
        this.zzeuo = zzeph2;
        this.zzeup = zzeph3;
        this.zzggx = zzeph4;
        this.zzfno = zzeph5;
        this.zzggy = zzeph6;
    }

    public static zzcmh zza(zzeph<Context> zzeph, zzeph<zzdow> zzeph2, zzeph<zzcmu> zzeph3, zzeph<zzdog> zzeph4, zzeph<zzdnv> zzeph5, zzeph<zzcsp> zzeph6) {
        return new zzcmh(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6);
    }

    public final /* synthetic */ Object get() {
        return new zzcmi(this.zzesu.get(), this.zzeuo.get(), this.zzeup.get(), this.zzggx.get(), this.zzfno.get(), this.zzggy.get());
    }
}
