package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsc implements zzeoy<zzcab<zzcak>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcaj> zzfpj;

    private zzbsc(zzeph<zzcaj> zzeph, zzeph<Executor> zzeph2) {
        this.zzfpj = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzbsc zzf(zzeph<zzcaj> zzeph, zzeph<Executor> zzeph2) {
        return new zzbsc(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
