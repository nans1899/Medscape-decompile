package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzii implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzig zzb;
    private final /* synthetic */ zzig zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzij zze;

    zzii(zzij zzij, Bundle bundle, zzig zzig, zzig zzig2, long j) {
        this.zze = zzij;
        this.zza = bundle;
        this.zzb = zzig;
        this.zzc = zzig2;
        this.zzd = j;
    }

    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
