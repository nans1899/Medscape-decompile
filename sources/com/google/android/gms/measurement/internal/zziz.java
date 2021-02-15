package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zziz implements Runnable {
    private final /* synthetic */ zzig zza;
    private final /* synthetic */ zzio zzb;

    zziz(zzio zzio, zzig zzig) {
        this.zzb = zzio;
        this.zza = zzig;
    }

    public final void run() {
        zzej zzd = this.zzb.zzb;
        if (zzd == null) {
            this.zzb.zzq().zze().zza("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zza == null) {
                zzd.zza(0, (String) null, (String) null, this.zzb.zzm().getPackageName());
            } else {
                zzd.zza(this.zza.zzc, this.zza.zza, this.zza.zzb, this.zzb.zzm().getPackageName());
            }
            this.zzb.zzaj();
        } catch (RemoteException e) {
            this.zzb.zzq().zze().zza("Failed to send current screen to the service", e);
        }
    }
}
