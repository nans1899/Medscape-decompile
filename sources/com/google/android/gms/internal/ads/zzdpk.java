package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpk implements zzeoy<zzcab<zzbui>> {
    private final zzeph<zzdpj> zzfsz;
    private final zzdph zzhgm;

    private zzdpk(zzdph zzdph, zzeph<zzdpj> zzeph) {
        this.zzhgm = zzdph;
        this.zzfsz = zzeph;
    }

    public static zzdpk zza(zzdph zzdph, zzeph<zzdpj> zzeph) {
        return new zzdpk(zzdph, zzeph);
    }

    public final /* synthetic */ Object get() {
        return (zzcab) zzepe.zza(new zzcab(this.zzfsz.get(), zzbbz.zzeeu), "Cannot return null from a non-@Nullable @Provides method");
    }
}
