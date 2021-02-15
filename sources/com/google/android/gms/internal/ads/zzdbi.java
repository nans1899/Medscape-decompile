package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdbi implements zzeoy<zzdzc<zzdbm>> {
    private final zzeph<zzdsi> zzfqv;
    private final zzeph<zzdbl> zzgus;
    private final zzeph<zzbtm> zzgut;

    public zzdbi(zzeph<zzdsi> zzeph, zzeph<zzdbl> zzeph2, zzeph<zzbtm> zzeph3) {
        this.zzfqv = zzeph;
        this.zzgus = zzeph2;
        this.zzgut = zzeph3;
    }

    public final /* synthetic */ Object get() {
        return (zzdzc) zzepe.zza(this.zzfqv.get().zza(zzdsf.GENERATE_SIGNALS, this.zzgut.get().zzajp()).zza(this.zzgus.get()).zza((long) ((Integer) zzwm.zzpx().zzd(zzabb.zzcvb)).intValue(), TimeUnit.SECONDS).zzavs(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
