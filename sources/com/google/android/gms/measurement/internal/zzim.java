package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzim implements Runnable {
    private final /* synthetic */ zzig zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzij zzc;

    zzim(zzij zzij, zzig zzig, long j) {
        this.zzc = zzij;
        this.zza = zzig;
        this.zzb = j;
    }

    public final void run() {
        this.zzc.zza(this.zza, false, this.zzb);
        this.zzc.zza = null;
        this.zzc.zzg().zza((zzig) null);
    }
}
