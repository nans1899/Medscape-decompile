package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgm implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzfw zzb;

    zzgm(zzfw zzfw, zzn zzn) {
        this.zzb = zzfw;
        this.zza = zzn;
    }

    public final void run() {
        this.zzb.zza.zzr();
        this.zzb.zza.zzb(this.zza);
    }
}
