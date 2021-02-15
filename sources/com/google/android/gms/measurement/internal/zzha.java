package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final /* synthetic */ class zzha implements Runnable {
    private final zzgy zza;

    zzha(zzgy zzgy) {
        this.zza = zzgy;
    }

    public final void run() {
        zzgy zzgy = this.zza;
        zzgy.zzc();
        if (zzgy.zzr().zzs.zza()) {
            zzgy.zzq().zzv().zza("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long zza2 = zzgy.zzr().zzt.zza();
        zzgy.zzr().zzt.zza(1 + zza2);
        if (zza2 >= 5) {
            zzgy.zzq().zzh().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            zzgy.zzr().zzs.zza(true);
            return;
        }
        zzgy.zzy.zzag();
    }
}
