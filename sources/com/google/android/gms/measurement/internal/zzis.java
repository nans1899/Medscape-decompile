package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzis implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzio zzd;

    zzis(zzio zzio, AtomicReference atomicReference, zzn zzn, boolean z) {
        this.zzd = zzio;
        this.zza = atomicReference;
        this.zzb = zzn;
        this.zzc = z;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                zzej zzd2 = this.zzd.zzb;
                if (zzd2 == null) {
                    this.zzd.zzq().zze().zza("Failed to get all user properties; not connected to service");
                    this.zza.notify();
                    return;
                }
                this.zza.set(zzd2.zza(this.zzb, this.zzc));
                this.zzd.zzaj();
                this.zza.notify();
            } catch (RemoteException e) {
                try {
                    this.zzd.zzq().zze().zza("Failed to get all user properties; remote exception", e);
                    this.zza.notify();
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
