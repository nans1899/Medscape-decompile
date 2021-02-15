package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzil implements Runnable {
    private final /* synthetic */ zzig zza;
    private final /* synthetic */ zzig zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzij zze;

    zzil(zzij zzij, zzig zzig, zzig zzig2, long j, boolean z) {
        this.zze = zzij;
        this.zza = zzig;
        this.zzb = zzig2;
        this.zzc = j;
        this.zzd = z;
    }

    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
