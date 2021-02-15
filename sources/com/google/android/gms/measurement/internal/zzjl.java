package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzjl implements Runnable {
    private final /* synthetic */ zzej zza;
    private final /* synthetic */ zzji zzb;

    zzjl(zzji zzji, zzej zzej) {
        this.zzb = zzji;
        this.zza = zzej;
    }

    public final void run() {
        synchronized (this.zzb) {
            boolean unused = this.zzb.zzb = false;
            if (!this.zzb.zza.zzaa()) {
                this.zzb.zza.zzq().zzw().zza("Connected to service");
                this.zzb.zza.zza(this.zza);
            }
        }
    }
}
