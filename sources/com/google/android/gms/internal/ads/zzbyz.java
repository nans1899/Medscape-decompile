package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyz implements zzeoy<Set<zzcab<zzux>>> {
    private final zzbys zzfwg;

    private zzbyz(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbyz zzl(zzbys zzbys) {
        return new zzbyz(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakl(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
