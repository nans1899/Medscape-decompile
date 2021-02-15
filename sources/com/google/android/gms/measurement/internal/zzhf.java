package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhf implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzgy zzb;

    zzhf(zzgy zzgy, long j) {
        this.zzb = zzgy;
        this.zza = j;
    }

    public final void run() {
        this.zzb.zzr().zzk.zza(this.zza);
        this.zzb.zzq().zzv().zza("Minimum session duration set", Long.valueOf(this.zza));
    }
}
