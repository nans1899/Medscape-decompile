package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcuc implements zzeoy<zzctt> {
    private final zzeph<Context> zzesu;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzcjt> zzgbz;
    private final zzeph<zzbpm> zzgof;
    private final zzeph<zzdvu<zzdnv, zzazp>> zzgon;

    public zzcuc(zzeph<zzbpm> zzeph, zzeph<Context> zzeph2, zzeph<Executor> zzeph3, zzeph<zzcjt> zzeph4, zzeph<zzdok> zzeph5, zzeph<zzdvu<zzdnv, zzazp>> zzeph6) {
        this.zzgof = zzeph;
        this.zzesu = zzeph2;
        this.zzfqk = zzeph3;
        this.zzgbz = zzeph4;
        this.zzfqr = zzeph5;
        this.zzgon = zzeph6;
    }

    public final /* synthetic */ Object get() {
        return new zzctt(this.zzgof.get(), this.zzesu.get(), this.zzfqk.get(), this.zzgbz.get(), this.zzfqr.get(), this.zzgon.get());
    }
}
