package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyy implements zzeoy<Set<zzcab<zzbvs>>> {
    private final zzbys zzfwg;

    private zzbyy(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbyy zzj(zzbys zzbys) {
        return new zzbyy(zzbys);
    }

    public static Set<zzcab<zzbvs>> zzk(zzbys zzbys) {
        return (Set) zzepe.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzk(this.zzfwg);
    }
}
