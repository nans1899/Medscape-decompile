package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzg implements zzeoy<Set<zzcab<zzbvs>>> {
    private final zzbys zzfwg;

    private zzbzg(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzg zzs(zzbys zzbys) {
        return new zzbzg(zzbys);
    }

    public static Set<zzcab<zzbvs>> zzt(zzbys zzbys) {
        return (Set) zzepe.zza(zzbys.zzakg(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzt(this.zzfwg);
    }
}
