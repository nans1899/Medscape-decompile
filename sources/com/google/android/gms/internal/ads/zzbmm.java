package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbmm implements zzeoy<zzbmh> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzdsr> zzezc;
    private final zzeph<zzacb> zzezs;
    private final zzeph<zzdor> zzfcc;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzdog> zzfnn;
    private final zzeph<zzdnv> zzfno;
    private final zzeph<View> zzfnp;
    private final zzeph<zzeg> zzfnq;
    private final zzeph<zzacg> zzfnr;

    private zzbmm(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<ScheduledExecutorService> zzeph3, zzeph<zzdog> zzeph4, zzeph<zzdnv> zzeph5, zzeph<zzdsr> zzeph6, zzeph<zzdor> zzeph7, zzeph<View> zzeph8, zzeph<zzeg> zzeph9, zzeph<zzacb> zzeph10, zzeph<zzacg> zzeph11) {
        this.zzesu = zzeph;
        this.zzesr = zzeph2;
        this.zzfnm = zzeph3;
        this.zzfnn = zzeph4;
        this.zzfno = zzeph5;
        this.zzezc = zzeph6;
        this.zzfcc = zzeph7;
        this.zzfnp = zzeph8;
        this.zzfnq = zzeph9;
        this.zzezs = zzeph10;
        this.zzfnr = zzeph11;
    }

    public static zzbmm zza(zzeph<Context> zzeph, zzeph<Executor> zzeph2, zzeph<ScheduledExecutorService> zzeph3, zzeph<zzdog> zzeph4, zzeph<zzdnv> zzeph5, zzeph<zzdsr> zzeph6, zzeph<zzdor> zzeph7, zzeph<View> zzeph8, zzeph<zzeg> zzeph9, zzeph<zzacb> zzeph10, zzeph<zzacg> zzeph11) {
        return new zzbmm(zzeph, zzeph2, zzeph3, zzeph4, zzeph5, zzeph6, zzeph7, zzeph8, zzeph9, zzeph10, zzeph11);
    }

    public final /* synthetic */ Object get() {
        return new zzbmh(this.zzesu.get(), this.zzesr.get(), this.zzfnm.get(), this.zzfnn.get(), this.zzfno.get(), this.zzezc.get(), this.zzfcc.get(), this.zzfnp.get(), this.zzfnq.get(), this.zzezs.get(), this.zzfnr.get());
    }
}
