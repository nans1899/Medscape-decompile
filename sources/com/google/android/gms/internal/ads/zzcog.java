package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcog implements zzeoy<zzcnu> {
    private final zzeph<zzcku> zzeso;
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcne> zzeto;
    private final zzeph<ScheduledExecutorService> zzfnm;
    private final zzeph<zzbbx> zzfov;
    private final zzeph<Executor> zzfqk;
    private final zzeph<Context> zzgiz;
    private final zzeph<WeakReference<Context>> zzgja;

    public zzcog(zzeph<Executor> zzeph, zzeph<Context> zzeph2, zzeph<WeakReference<Context>> zzeph3, zzeph<Executor> zzeph4, zzeph<zzcku> zzeph5, zzeph<ScheduledExecutorService> zzeph6, zzeph<zzcne> zzeph7, zzeph<zzbbx> zzeph8) {
        this.zzfqk = zzeph;
        this.zzgiz = zzeph2;
        this.zzgja = zzeph3;
        this.zzesr = zzeph4;
        this.zzeso = zzeph5;
        this.zzfnm = zzeph6;
        this.zzeto = zzeph7;
        this.zzfov = zzeph8;
    }

    public final /* synthetic */ Object get() {
        return new zzcnu(this.zzfqk.get(), this.zzgiz.get(), this.zzgja.get(), this.zzesr.get(), this.zzeso.get(), this.zzfnm.get(), this.zzeto.get(), this.zzfov.get());
    }
}
