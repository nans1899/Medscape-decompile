package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zznj implements zznk {
    private static final zzdh<Boolean> zza;
    private static final zzdh<Boolean> zzb;
    private static final zzdh<Boolean> zzc;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzdm zzdm = new zzdm(zzde.zza("com.google.android.gms.measurement"));
        zza = zzdm.zza("measurement.client.sessions.check_on_reset_and_enable2", true);
        zzb = zzdm.zza("measurement.client.sessions.check_on_startup", true);
        zzc = zzdm.zza("measurement.client.sessions.start_session_before_view_screen", true);
    }
}
