package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzl implements zzeoy<Set<zzcab<zzcak>>> {
    private final zzbys zzfwg;

    private zzbzl(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzl zzy(zzbys zzbys) {
        return new zzbzl(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
