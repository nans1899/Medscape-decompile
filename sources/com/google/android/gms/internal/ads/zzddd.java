package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddd implements zzeoy<zzdcz> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<ScheduledExecutorService> zzfnm;

    private zzddd(zzeph<Context> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<Executor> zzeph3) {
        this.zzesu = zzeph;
        this.zzfnm = zzeph2;
        this.zzesr = zzeph3;
    }

    public static zzddd zzt(zzeph<Context> zzeph, zzeph<ScheduledExecutorService> zzeph2, zzeph<Executor> zzeph3) {
        return new zzddd(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzdcz(this.zzesu.get(), this.zzfnm.get(), this.zzesr.get());
    }
}
