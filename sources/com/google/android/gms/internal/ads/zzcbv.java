package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbv implements zzeoy<Set<zzcab<zzbzy>>> {
    private final zzeph<zzccq> zzfpj;

    private zzcbv(zzeph<zzccq> zzeph) {
        this.zzfpj = zzeph;
    }

    public static zzcbv zzw(zzeph<zzccq> zzeph) {
        return new zzcbv(zzeph);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.singleton(zzcab.zzb(this.zzfpj.get(), zzbbz.zzeeu)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
