package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbn implements zzeoy<zzdbl> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcpc> zzgjh;

    public zzdbn(zzeph<Executor> zzeph, zzeph<zzcpc> zzeph2) {
        this.zzesr = zzeph;
        this.zzgjh = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return new zzdbl(this.zzesr.get(), this.zzgjh.get());
    }
}
