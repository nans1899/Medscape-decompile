package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzchy implements zzeoy<zzchj> {
    private final zzeph<Executor> zzfqk;
    private final zzeph<zzdok> zzfqr;
    private final zzeph<zzcjt> zzgbz;

    public zzchy(zzeph<zzdok> zzeph, zzeph<Executor> zzeph2, zzeph<zzcjt> zzeph3) {
        this.zzfqr = zzeph;
        this.zzfqk = zzeph2;
        this.zzgbz = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return new zzchj(this.zzfqr.get(), this.zzfqk.get(), this.zzgbz.get());
    }
}
