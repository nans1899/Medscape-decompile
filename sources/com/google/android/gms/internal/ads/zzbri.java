package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbri implements zzeoy<zzdog> {
    private final zzbre zzfsf;

    private zzbri(zzbre zzbre) {
        this.zzfsf = zzbre;
    }

    public static zzbri zzf(zzbre zzbre) {
        return new zzbri(zzbre);
    }

    public static zzdog zzg(zzbre zzbre) {
        return (zzdog) zzepe.zza(zzbre.zzajc(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zzg(this.zzfsf);
    }
}
