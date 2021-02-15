package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhe implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzgy zzb;

    zzhe(zzgy zzgy, long j) {
        this.zzb = zzgy;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzr().zzl.zza(this.zza);
        this.zzb.zzq().zzv().zza("Session timeout duration set", Long.valueOf(this.zza));
    }
}
