package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzls implements zzlp {
    private static final zzdh<Boolean> zza;
    private static final zzdh<Boolean> zzb;
    private static final zzdh<Boolean> zzc;
    private static final zzdh<Long> zzd;

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    public final boolean zzd() {
        return zzc.zzc().booleanValue();
    }

    static {
        zzdm zzdm = new zzdm(zzde.zza("com.google.android.gms.measurement"));
        zza = zzdm.zza("measurement.client.ad_impression", true);
        zzb = zzdm.zza("measurement.service.separate_public_internal_event_blacklisting", false);
        zzc = zzdm.zza("measurement.service.ad_impression", false);
        zzd = zzdm.zza("measurement.id.service.ad_impression", 0);
    }
}
