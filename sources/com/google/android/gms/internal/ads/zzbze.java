package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbze implements zzeoy<Set<zzcab<zzbuz>>> {
    private final zzbys zzfwg;

    private zzbze(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbze zzq(zzbys zzbys) {
        return new zzbze(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
