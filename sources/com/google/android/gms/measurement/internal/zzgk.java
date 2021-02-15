package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgk implements Runnable {
    private final /* synthetic */ zzkr zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzfw zzc;

    zzgk(zzfw zzfw, zzkr zzkr, zzn zzn) {
        this.zzc = zzfw;
        this.zza = zzkr;
        this.zzb = zzn;
    }

    public final void run() {
        this.zzc.zza.zzr();
        if (this.zza.zza() == null) {
            this.zzc.zza.zzb(this.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}
