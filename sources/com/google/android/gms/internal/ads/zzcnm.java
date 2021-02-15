package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcnm implements zzeoy<Set<zzcab<zzbvs>>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcns> zzggl;
    private final zzcni zzgia;

    private zzcnm(zzcni zzcni, zzeph<zzcns> zzeph, zzeph<Executor> zzeph2) {
        this.zzgia = zzcni;
        this.zzggl = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcnm zzd(zzcni zzcni, zzeph<zzcns> zzeph, zzeph<Executor> zzeph2) {
        return new zzcnm(zzcni, zzeph, zzeph2);
    }

    public static Set<zzcab<zzbvs>> zzb(zzcni zzcni, zzcns zzcns, Executor executor) {
        return (Set) zzepe.zza(zzcni.zzc(zzcns, executor), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzb(this.zzgia, this.zzggl.get(), this.zzesr.get());
    }
}
