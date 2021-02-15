package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcfa implements zzeoy<zzcei> {
    private final zzceu zzgar;

    private zzcfa(zzceu zzceu) {
        this.zzgar = zzceu;
    }

    public static zzcfa zzb(zzceu zzceu) {
        return new zzcfa(zzceu);
    }

    public static zzcei zzc(zzceu zzceu) {
        return (zzcei) zzepe.zza(zzceu.zzamy(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzc(this.zzgar);
    }
}
