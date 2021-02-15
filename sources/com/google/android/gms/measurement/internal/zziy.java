package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zziy implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzio zzc;

    zziy(zzio zzio, Bundle bundle, zzn zzn) {
        this.zzc = zzio;
        this.zza = bundle;
        this.zzb = zzn;
    }

    public final void run() {
        zzej zzd = this.zzc.zzb;
        if (zzd == null) {
            this.zzc.zzq().zze().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            zzd.zza(this.zza, this.zzb);
        } catch (RemoteException e) {
            this.zzc.zzq().zze().zza("Failed to send default event parameters to service", e);
        }
    }
}
