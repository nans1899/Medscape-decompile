package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzi implements zzeoy<Set<zzcab<zzbwb>>> {
    private final zzbys zzfwg;

    private zzbzi(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzi zzv(zzbys zzbys) {
        return new zzbzi(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakn(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
