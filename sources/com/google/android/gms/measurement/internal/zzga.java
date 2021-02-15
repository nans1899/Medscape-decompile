package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzga implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzfw zzb;

    zzga(zzfw zzfw, zzw zzw) {
        this.zzb = zzfw;
        this.zza = zzw;
    }

    public final void run() {
        this.zzb.zza.zzr();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zzb(this.zza);
        } else {
            this.zzb.zza.zza(this.zza);
        }
    }
}
