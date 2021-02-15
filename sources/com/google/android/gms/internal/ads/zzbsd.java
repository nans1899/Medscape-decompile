package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsd implements zzeoy<zzcab<zzbuv>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbmh> zzfpj;

    private zzbsd(zzeph<zzbmh> zzeph, zzeph<Executor> zzeph2) {
        this.zzfpj = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzbsd zzg(zzeph<zzbmh> zzeph, zzeph<Executor> zzeph2) {
        return new zzbsd(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
