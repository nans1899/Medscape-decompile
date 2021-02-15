package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcnh implements zzeoy<Set<zzcab<zzbuh>>> {
    private final zzeph<Executor> zzesr;
    private final zzeph<zzcns> zzggl;
    private final zzcni zzgia;

    private zzcnh(zzcni zzcni, zzeph<zzcns> zzeph, zzeph<Executor> zzeph2) {
        this.zzgia = zzcni;
        this.zzggl = zzeph;
        this.zzesr = zzeph2;
    }

    public static zzcnh zza(zzcni zzcni, zzeph<zzcns> zzeph, zzeph<Executor> zzeph2) {
        return new zzcnh(zzcni, zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(zzcni.zze(this.zzggl.get(), this.zzesr.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
