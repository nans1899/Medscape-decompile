package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzit implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzkr zzb;
    private final /* synthetic */ zzn zzc;
    private final /* synthetic */ zzio zzd;

    zzit(zzio zzio, boolean z, zzkr zzkr, zzn zzn) {
        this.zzd = zzio;
        this.zza = z;
        this.zzb = zzkr;
        this.zzc = zzn;
    }

    public final void run() {
        zzej zzd2 = this.zzd.zzb;
        if (zzd2 == null) {
            this.zzd.zzq().zze().zza("Discarding data. Failed to set user property");
            return;
        }
        this.zzd.zza(zzd2, (AbstractSafeParcelable) this.zza ? null : this.zzb, this.zzc);
        this.zzd.zzaj();
    }
}
