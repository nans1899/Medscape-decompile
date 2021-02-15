package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyx implements zzeoy<Set<zzcab<zzp>>> {
    private final zzbys zzfwg;

    private zzbyx(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbyx zzi(zzbys zzbys) {
        return new zzbyx(zzbys);
    }

    public final /* synthetic */ Object get() {
        return (Set) zzepe.zza(this.zzfwg.zzakp(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
