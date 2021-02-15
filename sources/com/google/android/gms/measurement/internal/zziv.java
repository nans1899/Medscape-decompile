package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zziv implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzio zzb;

    zziv(zzio zzio, zzn zzn) {
        this.zzb = zzio;
        this.zza = zzn;
    }

    public final void run() {
        zzej zzd = this.zzb.zzb;
        if (zzd == null) {
            this.zzb.zzq().zze().zza("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            zzd.zzd(this.zza);
        } catch (RemoteException e) {
            this.zzb.zzq().zze().zza("Failed to reset data on the service: remote exception", e);
        }
        this.zzb.zzaj();
    }
}
