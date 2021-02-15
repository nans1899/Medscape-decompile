package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcbs implements zzeoy<Set<zzcab<zzbuh>>> {
    private final zzeph<zzbtd> zzfpj;
    private final zzcbf zzfwx;

    private zzcbs(zzcbf zzcbf, zzeph<zzbtd> zzeph) {
        this.zzfwx = zzcbf;
        this.zzfpj = zzeph;
    }

    public static zzcbs zze(zzcbf zzcbf, zzeph<zzbtd> zzeph) {
        return new zzcbs(zzcbf, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwx.zzb(this.zzfpj.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
