package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdcs implements zzeoy<zzdcr> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzayb> zzgvj;

    private zzdcs(zzeph<Executor> zzeph, zzeph<zzayb> zzeph2) {
        this.zzesr = zzeph;
        this.zzgvj = zzeph2;
    }

    public static zzdcs zzax(zzeph<Executor> zzeph, zzeph<zzayb> zzeph2) {
        return new zzdcs(zzeph, zzeph2);
    }

    public static zzdcr zza(Executor executor, zzayb zzayb) {
        return new zzdcr(executor, zzayb);
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzesr.get(), this.zzgvj.get());
    }
}
