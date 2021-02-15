package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrd implements zzeoy<zzdnv> {
    private final zzbre zzfsf;

    private zzbrd(zzbre zzbre) {
        this.zzfsf = zzbre;
    }

    public static zzbrd zza(zzbre zzbre) {
        return new zzbrd(zzbre);
    }

    public static zzdnv zzb(zzbre zzbre) {
        return (zzdnv) zzepe.zza(zzbre.zzajd(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzb(this.zzfsf);
    }
}
