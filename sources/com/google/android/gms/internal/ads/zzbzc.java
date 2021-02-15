package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzc implements zzeoy<Set<zzcab<zzbui>>> {
    private final zzbys zzfwg;

    private zzbzc(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzc zzo(zzbys zzbys) {
        return new zzbzc(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakh(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
