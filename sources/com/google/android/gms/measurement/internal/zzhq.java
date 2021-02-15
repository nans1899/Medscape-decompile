package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhq implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzgy zzb;

    zzhq(zzgy zzgy, AtomicReference atomicReference) {
        this.zzb = zzgy;
        this.zza = atomicReference;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(Long.valueOf(this.zzb.zzs().zza(this.zzb.zzf().zzaa(), zzat.zzal)));
                this.zza.notify();
            } catch (Throwable th) {
                this.zza.notify();
                throw th;
            }
        }
    }
}
