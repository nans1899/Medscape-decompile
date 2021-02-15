package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhs implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzgy zzb;

    zzhs(zzgy zzgy, AtomicReference atomicReference) {
        this.zzb = zzgy;
        this.zza = atomicReference;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Double.valueOf(this.zzb.zzs().zzc(this.zzb.zzf().zzaa(), zzat.zzan)));
                this.zza.notify();
            } catch (Throwable th) {
                this.zza.notify();
                throw th;
            }
        }
    }
}
