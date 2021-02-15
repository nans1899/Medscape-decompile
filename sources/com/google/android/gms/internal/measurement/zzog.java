package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzog implements zzod {
    private static final zzdh<Long> zza;
    private static final zzdh<Boolean> zzb;
    private static final zzdh<Boolean> zzc;
    private static final zzdh<Boolean> zzd;
    private static final zzdh<Long> zze;

    public final boolean zza() {
        return zzb.zzc().booleanValue();
    }

    public final boolean zzb() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzdm zzdm = new zzdm(zzde.zza("com.google.android.gms.measurement"));
        zza = zzdm.zza("measurement.id.lifecycle.app_in_background_parameter", 0);
        zzb = zzdm.zza("measurement.lifecycle.app_backgrounded_engagement", false);
        zzc = zzdm.zza("measurement.lifecycle.app_backgrounded_tracking", true);
        zzd = zzdm.zza("measurement.lifecycle.app_in_background_parameter", false);
        zze = zzdm.zza("measurement.id.lifecycle.app_backgrounded_tracking", 0);
    }
}
