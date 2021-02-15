package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzmq implements zzmn {
    private static final zzdh<Boolean> zza;
    private static final zzdh<Boolean> zzb;
    private static final zzdh<Boolean> zzc;
    private static final zzdh<Long> zzd;

    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    static {
        zzdm zzdm = new zzdm(zzde.zza("com.google.android.gms.measurement"));
        zza = zzdm.zza("measurement.sdk.dynamite.allow_remote_dynamite3", false);
        zzb = zzdm.zza("measurement.collection.init_params_control_enabled", true);
        zzc = zzdm.zza("measurement.sdk.dynamite.use_dynamite3", true);
        zzd = zzdm.zza("measurement.id.sdk.dynamite.use_dynamite", 0);
    }
}
