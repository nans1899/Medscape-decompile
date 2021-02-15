package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbrg implements zzeoy<String> {
    private final zzbre zzfsf;

    private zzbrg(zzbre zzbre) {
        this.zzfsf = zzbre;
    }

    public static zzbrg zzd(zzbre zzbre) {
        return new zzbrg(zzbre);
    }

    public static String zze(zzbre zzbre) {
        return (String) zzepe.zza(zzbre.zzajf(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        return zze(this.zzfsf);
    }
}
