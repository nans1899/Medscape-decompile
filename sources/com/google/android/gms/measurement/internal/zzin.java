package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzin implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzij zzb;

    zzin(zzij zzij, long j) {
        this.zzb = zzij;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzd().zza(this.zza);
        this.zzb.zza = null;
    }
}
