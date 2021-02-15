package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbzj implements zzeoy<zzbys> {
    private final zzbys zzfwg;

    private zzbzj(zzbys zzbys) {
        this.zzfwg = zzbys;
    }

    public static zzbzj zzw(zzbys zzbys) {
        return new zzbzj(zzbys);
    }

    public final /* synthetic */ Object get() {
        zzbys zzbys = this.zzfwg;
        if (zzbys != null) {
            return (zzbys) zzepe.zza(zzbys, "Cannot return null from a non-@Nullable @Provides method");
        }
        throw null;
    }
}
