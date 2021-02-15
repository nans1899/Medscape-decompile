package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbsy implements zzeoy<zzcab<zzbvs>> {
    private final zzeph<zzbtk> zzfop;
    private final zzbsj zzftg;

    private zzbsy(zzbsj zzbsj, zzeph<zzbtk> zzeph) {
        this.zzftg = zzbsj;
        this.zzfop = zzeph;
    }

    public static zzbsy zzc(zzbsj zzbsj, zzeph<zzbtk> zzeph) {
        return new zzbsy(zzbsj, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfop.get(), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
