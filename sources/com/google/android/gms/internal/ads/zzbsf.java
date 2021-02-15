package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsf implements zzeoy<zzcab<zzbvs>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbmh> zzfpj;

    private zzbsf(zzeph<zzbmh> zzeph, zzeph<Executor> zzeph2) {
        this.zzfpj = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzbsf zzi(zzeph<zzbmh> zzeph, zzeph<Executor> zzeph2) {
        return new zzbsf(zzeph, zzeph2);
    }

    public static zzcab<zzbvs> zza(zzbmh zzbmh, Executor executor) {
        return (zzcab) zzepe.zza(new zzcab(zzbmh, executor), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zza(this.zzfpj.get(), this.zzesr.get());
    }
}
