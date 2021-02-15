package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzmj;
import com.google.android.gms.internal.measurement.zzw;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzix implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzw zzb;
    private final /* synthetic */ zzio zzc;

    zzix(zzio zzio, zzn zzn, zzw zzw) {
        this.zzc = zzio;
        this.zza = zzn;
        this.zzb = zzw;
    }

    public final void run() {
        try {
            if (!zzmj.zzb() || !this.zzc.zzs().zza(zzat.zzcg) || this.zzc.zzr().zzw().zze()) {
                zzej zzd = this.zzc.zzb;
                if (zzd == null) {
                    this.zzc.zzq().zze().zza("Failed to get app instance id");
                    this.zzc.zzo().zza(this.zzb, (String) null);
                    return;
                }
                String zzc2 = zzd.zzc(this.zza);
                if (zzc2 != null) {
                    this.zzc.zze().zza(zzc2);
                    this.zzc.zzr().zzj.zza(zzc2);
                }
                this.zzc.zzaj();
                this.zzc.zzo().zza(this.zzb, zzc2);
                return;
            }
            this.zzc.zzq().zzj().zza("Analytics storage consent denied; will not get app instance id");
            this.zzc.zze().zza((String) null);
            this.zzc.zzr().zzj.zza((String) null);
        } catch (RemoteException e) {
            this.zzc.zzq().zze().zza("Failed to get app instance id", e);
        } finally {
            this.zzc.zzo().zza(this.zzb, (String) null);
        }
    }
}
