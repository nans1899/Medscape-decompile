package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcgp implements zzeoy<zzcgn> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbag> zzezq;
    private final zzeph<Clock> zzfok;

    public zzcgp(zzeph<zzbag> zzeph, zzeph<Clock> zzeph2, zzeph<Executor> zzeph3) {
        this.zzezq = zzeph;
        this.zzfok = zzeph2;
        this.zzesr = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzcgn(this.zzezq.get(), this.zzfok.get(), this.zzesr.get());
    }
}
