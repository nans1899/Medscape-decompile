package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzb;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchd implements zzeoy<zzcgw> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzcgn> zzezr;
    private final zzeph<zzchj> zzezu;
    private final zzeph<zzbbx> zzfmh;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzeg> zzfyn;
    private final zzeph<zzb> zzgds;
    private final zzeph<zzts> zzgdt;

    public zzchd(zzeph<Context> zzeph, zzeph<zzcgn> zzeph2, zzeph<zzeg> zzeph3, zzeph<zzbbx> zzeph4, zzeph<zzb> zzeph5, zzeph<zzts> zzeph6, zzeph<Executor> zzeph7, zzeph<zzdok> zzeph8, zzeph<zzchj> zzeph9, zzeph<ScheduledExecutorService> zzeph10) {
        this.zzesu = zzeph;
        this.zzezr = zzeph2;
        this.zzfyn = zzeph3;
        this.zzfmh = zzeph4;
        this.zzgds = zzeph5;
        this.zzgdt = zzeph6;
        this.zzesr = zzeph7;
        this.zzfqr = zzeph8;
        this.zzezu = zzeph9;
        this.zzfnm = zzeph10;
    }

    public final /* synthetic */ Object get() {
        return new zzcgw(this.zzesu.get(), this.zzezr.get(), this.zzfyn.get(), this.zzfmh.get(), this.zzgds.get(), this.zzgdt.get(), this.zzesr.get(), this.zzfqr.get(), this.zzezu.get(), this.zzfnm.get());
    }
}
