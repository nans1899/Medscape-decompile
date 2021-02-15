package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzh implements zzeoy<Set<zzcab<zzbwl>>> {
    private final zzbys zzfwg;

    private zzbzh(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzh zzu(zzbys zzbys) {
        return new zzbzh(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzako(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
