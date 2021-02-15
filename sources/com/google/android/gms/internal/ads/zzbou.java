package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbou implements zzeoy<zzbos> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzcaf> zzfdb;
    private final zzeph<zzbqn> zzfeb;
    private final zzeph<zzczf> zzfjc;
    private final zzeph<View> zzfnp;
    private final zzeph<zzdnu> zzfqg;
    private final zzeph<zzbgj> zzfqh;
    private final zzeph<zzbql> zzfqi;
    private final zzeph<zzcep> zzfqj;
    private final zzeph<Executor> zzfqk;

    public zzbou(zzeph<zzbqn> zzeph, zzeph<Context> zzeph2, zzeph<zzdnu> zzeph3, zzeph<View> zzeph4, zzeph<zzbgj> zzeph5, zzeph<zzbql> zzeph6, zzeph<zzcep> zzeph7, zzeph<zzcaf> zzeph8, zzeph<zzczf> zzeph9, zzeph<Executor> zzeph10) {
        this.zzfeb = zzeph;
        this.zzesu = zzeph2;
        this.zzfqg = zzeph3;
        this.zzfnp = zzeph4;
        this.zzfqh = zzeph5;
        this.zzfqi = zzeph6;
        this.zzfqj = zzeph7;
        this.zzfdb = zzeph8;
        this.zzfjc = zzeph9;
        this.zzfqk = zzeph10;
    }

    public static zzbos zza(zzbqn zzbqn, Context context, zzdnu zzdnu, View view, zzbgj zzbgj, zzbql zzbql, zzcep zzcep, zzcaf zzcaf, zzeos<zzczf> zzeos, Executor executor) {
        return new zzbos(zzbqn, context, zzdnu, view, zzbgj, zzbql, zzcep, zzcaf, zzeos, executor);
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfeb.get(), this.zzesu.get(), this.zzfqg.get(), this.zzfnp.get(), this.zzfqh.get(), this.zzfqi.get(), this.zzfqj.get(), this.zzfdb.get(), zzeov.zzat(this.zzfjc), this.zzfqk.get());
    }
}
