package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbe implements zzeoy<zzdaq> {
    private final zzeph<zzdzb> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzeg> zzfnq;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<zzdoz<zzcil>> zzgpq;
    private final zzeph<zzbix> zzguq;

    public zzdbe(zzeph<zzbix> zzeph, zzeph<Context> zzeph2, zzeph<zzeg> zzeph3, zzeph<zzbbx> zzeph4, zzeph<zzdoz<zzcil>> zzeph5, zzeph<zzdzb> zzeph6, zzeph<ScheduledExecutorService> zzeph7) {
        this.zzguq = zzeph;
        this.zzesu = zzeph2;
        this.zzfnq = zzeph3;
        this.zzfov = zzeph4;
        this.zzgpq = zzeph5;
        this.zzesr = zzeph6;
        this.zzfnm = zzeph7;
    }

    public final /* synthetic */ Object get() {
        return new zzdaq(this.zzguq.get(), this.zzesu.get(), this.zzfnq.get(), this.zzfov.get(), this.zzgpq.get(), this.zzesr.get(), this.zzfnm.get());
    }
}
