package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzmc implements zzec<zzmb> {
    private static zzmc zza = new zzmc();
    private final zzec<zzmb> zzb;

    public static boolean zzb() {
        return ((zzmb) zza.zza()).zza();
    }

    public static long zzc() {
        return ((zzmb) zza.zza()).zzb();
    }

    private zzmc(zzec<zzmb> zzec) {
        this.zzb = zzef.zza(zzec);
    }

    public zzmc() {
        this(zzef.zza(new zzme()));
    }

    public final /* synthetic */ Object zza() {
        return this.zzb.zza();
    }
}
