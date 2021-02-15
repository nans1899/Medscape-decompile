package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsg implements zzeoy<zzcab<zzbuz>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzbmh> zzfpj;

    private zzbsg(zzeph<zzbmh> zzeph, zzeph<Executor> zzeph2) {
        this.zzfpj = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzbsg zzj(zzeph<zzbmh> zzeph, zzeph<Executor> zzeph2) {
        return new zzbsg(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
