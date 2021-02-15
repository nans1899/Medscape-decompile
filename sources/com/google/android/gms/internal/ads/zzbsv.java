package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsv implements zzeoy<zzcab<zzbuy>> {
    private final zzeph<zzbtk> zzfop;
    private final zzbsj zzftg;

    private zzbsv(zzbsj zzbsj, zzeph<zzbtk> zzeph) {
        this.zzftg = zzbsj;
        this.zzfop = zzeph;
    }

    public static zzbsv zzb(zzbsj zzbsj, zzeph<zzbtk> zzeph) {
        return new zzbsv(zzbsj, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
