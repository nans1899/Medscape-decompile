package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhx implements Runnable {
    private final /* synthetic */ zzad zza;
    private final /* synthetic */ int zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzgy zze;

    zzhx(zzgy zzgy, zzad zzad, int i, long j, boolean z) {
        this.zze = zzgy;
        this.zza = zzad;
        this.zzb = i;
        this.zzc = j;
        this.zzd = z;
    }

    public final void run() {
        this.zze.zza(this.zza);
        this.zze.zza(this.zza, this.zzb, this.zzc, false, this.zzd);
    }
}
