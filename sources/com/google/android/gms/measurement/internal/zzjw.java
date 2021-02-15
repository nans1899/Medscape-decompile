package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzjw implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzju zzb;

    zzjw(zzju zzju, long j) {
        this.zzb = zzju;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzc(this.zza);
    }
}
