package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbm implements zzeoy<Set<zzcab<zzbyc>>> {
    private final zzeph<zzbtd> zzfpj;
    private final zzcbf zzfwx;

    private zzcbm(zzcbf zzcbf, zzeph<zzbtd> zzeph) {
        this.zzfwx = zzcbf;
        this.zzfpj = zzeph;
    }

    public static zzcbm zzb(zzcbf zzcbf, zzeph<zzbtd> zzeph) {
        return new zzcbm(zzcbf, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.singleton(zzcab.zzb(this.zzfpj.get(), zzbbz.zzeeu)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
