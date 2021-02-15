package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzw;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.6.0 */
final class zzj implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzar zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzj(AppMeasurementDynamiteService appMeasurementDynamiteService, zzw zzw, zzar zzar, String str) {
        this.zzd = appMeasurementDynamiteService;
        this.zza = zzw;
        this.zzb = zzar;
        this.zzc = str;
    }

    public final void run() {
        this.zzd.zza.zzv().zza(this.zza, this.zzb, this.zzc);
    }
}
