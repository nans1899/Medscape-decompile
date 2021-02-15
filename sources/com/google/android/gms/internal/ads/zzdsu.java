package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdsu implements zzeoy<zzdsr> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdoj> zzfkv;
    private final zzeph<zzeg> zzfnq;
    private final zzeph<Clock> zzfok;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<String> zzgvp;
    private final zzeph<String> zzgvz;
    private final zzeph<zzcwj> zzhlo;

    private zzdsu(zzeph<zzcwj> zzeph, zzeph<zzbbx> zzeph2, zzeph<String> zzeph3, zzeph<String> zzeph4, zzeph<Context> zzeph5, zzeph<zzdoj> zzeph6, zzeph<Clock> zzeph7, zzeph<zzeg> zzeph8) {
        this.zzhlo = zzeph;
        this.zzfov = zzeph2;
        this.zzgvp = zzeph3;
        this.zzgvz = zzeph4;
        this.zzesu = zzeph5;
        this.zzfkv = zzeph6;
        this.zzfok = zzeph7;
        this.zzfnq = zzeph8;
    }

    public static zzdsu zza(zzeph<zzcwj> zzeph, zzeph<zzbbx> zzeph2, zzeph<String> zzeph3, zzeph<String> zzeph4, zzeph<Context> zzeph5, zzeph<zzdoj> zzeph6, zzeph<Clock> zzeph7, zzeph<zzeg> zzeph8) {
        return new zzdsu(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7, zzeph8);
    }

    public final /* synthetic */ Object get() {
        return new zzdsr(this.zzhlo.get(), this.zzfov.get(), this.zzgvp.get(), this.zzgvz.get(), this.zzesu.get(), this.zzfkv.get(), this.zzfok.get(), this.zzfnq.get());
    }
}
