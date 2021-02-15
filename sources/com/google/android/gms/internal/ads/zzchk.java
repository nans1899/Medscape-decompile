package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchk implements zzeoy<zzchg> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcgw> zzgdc;

    public zzchk(zzeph<Executor> zzeph, zzeph<zzcgw> zzeph2) {
        this.zzesr = zzeph;
        this.zzgdc = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return new zzchg(this.zzesr.get(), this.zzgdc.get());
    }
}
