package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfx implements Runnable {
    private final /* synthetic */ zzgz zza;
    private final /* synthetic */ zzfv zzb;

    zzfx(zzfv zzfv, zzgz zzgz) {
        this.zzb = zzfv;
        this.zza = zzgz;
    }

    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza(this.zza.zzg);
    }
}
