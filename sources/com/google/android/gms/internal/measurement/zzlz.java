package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzlz implements zzma {
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
        zza = zzdm.zza("measurement.service.directly_maybe_log_error_events", false);
        zzb = zzdm.zza("measurement.id.service.directly_maybe_log_error_events", 0);
    }
}
