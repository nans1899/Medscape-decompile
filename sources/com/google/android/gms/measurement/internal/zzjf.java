package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzjf implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzar zzc;
    private final /* synthetic */ zzn zzd;
    private final /* synthetic */ String zze;
    private final /* synthetic */ zzio zzf;

    zzjf(zzio zzio, boolean z, boolean z2, zzar zzar, zzn zzn, String str) {
        this.zzf = zzio;
        this.zza = z;
        this.zzb = z2;
        this.zzc = zzar;
        this.zzd = zzn;
        this.zze = str;
    }

    public final void run() {
        zzej zzd2 = this.zzf.zzb;
        if (zzd2 == null) {
            this.zzf.zzq().zze().zza("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zza) {
            this.zzf.zza(zzd2, (AbstractSafeParcelable) this.zzb ? null : this.zzc, this.zzd);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze)) {
                    zzd2.zza(this.zzc, this.zzd);
                } else {
                    zzd2.zza(this.zzc, this.zze, this.zzf.zzq().zzx());
                }
            } catch (RemoteException e) {
                this.zzf.zzq().zze().zza("Failed to send event to the service", e);
            }
        }
        this.zzf.zzaj();
    }
}
