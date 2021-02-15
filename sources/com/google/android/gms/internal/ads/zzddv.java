package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzddv implements zzeoy<zzddp<zzddg>> {
    private final zzeph<Clock> zzfok;
    private final zzeph<zzddj> zzgwo;

    public zzddv(zzeph<zzddj> zzeph, zzeph<Clock> zzeph2) {
        this.zzgwo = zzeph;
        this.zzfok = zzeph2;
    }

    public final /* synthetic */ Object get() {
        return (zzddp) zzepe.zza(new zzddp(this.zzgwo.get(), 10000, this.zzfok.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
