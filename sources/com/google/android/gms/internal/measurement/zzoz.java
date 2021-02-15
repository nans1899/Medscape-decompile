package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzoz implements zzpa {
    private static final zzdh<Boolean> zza;
    private static final zzdh<Long> zzb;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzdm zzdm = new zzdm(zzde.zza("com.google.android.gms.measurement"));
        zza = zzdm.zza("measurement.service.ssaid_removal", true);
        zzb = zzdm.zza("measurement.id.ssaid_removal", 0);
    }
}
