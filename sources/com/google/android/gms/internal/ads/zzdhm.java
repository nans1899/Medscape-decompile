package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdhm implements zzeoy<zzdhl> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<zzaxx> zzetv;
    private final zzeph<ScheduledExecutorService> zzfnm;

    public zzdhm(zzeph<zzaxx> zzeph, zzeph<Context> zzeph2, zzeph<ScheduledExecutorService> zzeph3, zzeph<Executor> zzeph4) {
        this.zzetv = zzeph;
        this.zzesu = zzeph2;
        this.zzfnm = zzeph3;
        this.zzesr = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return new zzdhl(this.zzetv.get(), this.zzesu.get(), this.zzfnm.get(), this.zzesr.get());
    }
}
