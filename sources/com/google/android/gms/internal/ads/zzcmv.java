package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcmv implements zzeoy<zzcmu> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcmz> zzetn;

    public zzcmv(zzeph<zzcmz> zzeph, zzeph<Executor> zzeph2) {
        this.zzetn = zzeph;
        this.zzesr = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return new zzcmu(this.zzetn.get(), this.zzesr.get());
    }
}
