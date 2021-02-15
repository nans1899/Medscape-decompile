package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzd implements zzeoy<Set<zzcab<zzbuh>>> {
    private final zzbys zzfwg;

    private zzbzd(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzd zzp(zzbys zzbys) {
        return new zzbzd(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakf(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
