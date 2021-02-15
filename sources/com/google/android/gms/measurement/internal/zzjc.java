package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzjc implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzio zzb;

    zzjc(zzio zzio, zzn zzn) {
        this.zzb = zzio;
        this.zza = zzn;
    }

    public final void run() {
        zzej zzd = this.zzb.zzb;
        if (zzd == null) {
            this.zzb.zzq().zze().zza("Failed to send consent settings to service");
            return;
        }
        try {
            zzd.zze(this.zza);
            this.zzb.zzaj();
        } catch (RemoteException e) {
            this.zzb.zzq().zze().zza("Failed to send consent settings to the service", e);
        }
    }
}
