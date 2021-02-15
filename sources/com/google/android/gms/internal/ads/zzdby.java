package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdby implements zzeoy<zzdbx> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzdzc<String>> zzgve;

    private zzdby(zzeph<zzdzc<String>> zzeph, zzeph<Executor> zzeph2) {
        this.zzgve = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzdby zzaw(zzeph<zzdzc<String>> zzeph, zzeph<Executor> zzeph2) {
        return new zzdby(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzdbx(this.zzgve.get(), this.zzesr.get());
    }
}
