package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzb implements zzeoy<Set<zzcab<zzbuv>>> {
    private final zzbys zzfwg;

    private zzbzb(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzb zzn(zzbys zzbys) {
        return new zzbzb(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzaki(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
