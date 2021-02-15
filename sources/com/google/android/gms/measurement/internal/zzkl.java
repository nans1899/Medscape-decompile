package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzkl implements Runnable {
    private final /* synthetic */ zzkq zza;
    private final /* synthetic */ zzki zzb;

    zzkl(zzki zzki, zzkq zzkq) {
        this.zzb = zzki;
        this.zza = zzkq;
    }

    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}
