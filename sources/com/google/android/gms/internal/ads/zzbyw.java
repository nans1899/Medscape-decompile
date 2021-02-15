package com.google.android.gms.internal.ads;

import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyw implements zzeoy<zzbug> {
    private final zzbys zzfwg;
    private final zzeph<Set<zzcab<zzbui>>> zzfwh;

    private zzbyw(zzbys zzbys, zzeph<Set<zzcab<zzbui>>> zzeph) {
        this.zzfwg = zzbys;
        this.zzfwh = zzeph;
    }

    public static zzbyw zza(zzbys zzbys, zzeph<Set<zzcab<zzbui>>> zzeph) {
        return new zzbyw(zzbys, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzbug) zzepe.zza(this.zzfwg.zzc(this.zzfwh.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
