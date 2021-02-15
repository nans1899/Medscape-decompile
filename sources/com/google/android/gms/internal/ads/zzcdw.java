package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcdw implements zzeoy<zzbnd> {
    private final zzeph<Executor> zzesr;
    private final zzeph<Context> zzesu;
    private final zzeph<Clock> zzfok;
    private final zzeph<zzqo> zzfys;

    public zzcdw(zzeph<zzqo> zzeph, zzeph<Executor> zzeph2, zzeph<Context> zzeph3, zzeph<Clock> zzeph4) {
        this.zzfys = zzeph;
        this.zzesr = zzeph2;
        this.zzesu = zzeph3;
        this.zzfok = zzeph4;
    }

    public final /* synthetic */ Object get() {
        return (zzbnd) zzepe.zza(new zzbnd(this.zzesr.get(), new zzbms(this.zzesu.get(), this.zzfys.get()), this.zzfok.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
