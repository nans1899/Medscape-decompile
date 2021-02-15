package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbza implements zzeoy<Set<zzcab<zzbxz>>> {
    private final zzbys zzfwg;

    private zzbza(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbza zzm(zzbys zzbys) {
        return new zzbza(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(Collections.emptySet(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
