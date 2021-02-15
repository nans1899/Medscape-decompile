package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcxm implements zzeoy<zzcxb> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzcjt> zzgbz;
    private final zzeph<zzcjj> zzgof;

    public zzcxm(zzeph<Context> zzeph, zzeph<zzbbx> zzeph2, zzeph<zzdok> zzeph3, zzeph<Executor> zzeph4, zzeph<zzcjj> zzeph5, zzeph<zzcjt> zzeph6) {
        this.zzesu = zzeph;
        this.zzfov = zzeph2;
        this.zzfqr = zzeph3;
        this.zzfqk = zzeph4;
        this.zzgof = zzeph5;
        this.zzgbz = zzeph6;
    }

    public final /* synthetic */ Object get() {
        return new zzcxb(this.zzesu.get(), this.zzfov.get(), this.zzfqr.get(), this.zzfqk.get(), this.zzgof.get(), this.zzgbz.get());
    }
}
