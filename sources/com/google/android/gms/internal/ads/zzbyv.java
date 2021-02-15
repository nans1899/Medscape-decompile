package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyv implements zzeoy<Set<zzcab<zzbuy>>> {
    private final zzbys zzfwg;

    private zzbyv(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbyv zzh(zzbys zzbys) {
        return new zzbyv(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
