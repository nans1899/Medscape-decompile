package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zze implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zza zzb;

    zze(zza zza2, long j) {
        this.zzb = zza2;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzb(this.zza);
    }
}
