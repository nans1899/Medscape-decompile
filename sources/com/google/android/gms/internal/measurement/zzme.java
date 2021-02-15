package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzme implements zzmb {
    private static final zzdh<Boolean> zza;
    private static final zzdh<Long> zzb;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    public final long zzb() {
        return zzb.zzc().longValue();
    }

    static {
        zzdm zzdm = new zzdm(zzde.zza("com.google.android.gms.measurement"));
        zza = zzdm.zza("measurement.sdk.attribution.cache", true);
        zzb = zzdm.zza("measurement.sdk.attribution.cache.ttl", 604800000);
    }
}
