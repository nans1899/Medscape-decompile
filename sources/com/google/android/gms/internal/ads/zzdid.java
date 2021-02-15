package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdid implements zzeoy<zzdhz> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzaxx> zzetv;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzayb> zzgvj;
    private final zzeph<Integer> zzgzc;

    public zzdid(zzeph<zzaxx> zzeph, zzeph<Integer> zzeph2, zzeph<Context> zzeph3, zzeph<zzayb> zzeph4, zzeph<ScheduledExecutorService> zzeph5, zzeph<Executor> zzeph6) {
        this.zzetv = zzeph;
        this.zzgzc = zzeph2;
        this.zzesu = zzeph3;
        this.zzgvj = zzeph4;
        this.zzfnm = zzeph5;
        this.zzesr = zzeph6;
    }

    public final /* synthetic */ Object get() {
        return new zzdhz(this.zzetv.get(), this.zzgzc.get().intValue(), this.zzesu.get(), this.zzgvj.get(), this.zzfnm.get(), this.zzesr.get());
    }
}
