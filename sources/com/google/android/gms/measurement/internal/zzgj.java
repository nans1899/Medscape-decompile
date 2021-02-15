package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgj implements Runnable {
    private final /* synthetic */ zzar zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzfw zzc;

    zzgj(zzfw zzfw, zzar zzar, zzn zzn) {
        this.zzc = zzfw;
        this.zza = zzar;
        this.zzb = zzn;
    }

    public final void run() {
        zzar zzb2 = this.zzc.zzb(this.zza, this.zzb);
        this.zzc.zza.zzr();
        this.zzc.zza.zza(zzb2, this.zzb);
    }
}
