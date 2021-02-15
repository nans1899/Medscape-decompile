package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbt implements zzeoy<zzcab<zzbuh>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzccs> zzfpj;
    private final zzcbf zzfwx;

    private zzcbt(zzcbf zzcbf, zzeph<zzccs> zzeph, zzeph<Executor> zzeph2) {
        this.zzfwx = zzcbf;
        this.zzfpj = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcbt zzb(zzcbf zzcbf, zzeph<zzccs> zzeph, zzeph<Executor> zzeph2) {
        return new zzcbt(zzcbf, zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfpj.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
