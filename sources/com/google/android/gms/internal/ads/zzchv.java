package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchv implements zzeoy<zzchs> {
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzbnd> zzgca;
    private final zzeph<zzcae> zzgea;

    public zzchv(zzeph<Executor> zzeph, zzeph<zzbnd> zzeph2, zzeph<zzcae> zzeph3) {
        this.zzfqk = zzeph;
        this.zzgca = zzeph2;
        this.zzgea = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzchs(this.zzfqk.get(), this.zzgca.get(), this.zzgea.get());
    }
}
