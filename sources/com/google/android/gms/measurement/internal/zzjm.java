package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzjm implements Runnable {
    private final /* synthetic */ zzji zza;

    zzjm(zzji zzji) {
        this.zza = zzji;
    }

    public final void run() {
        this.zza.zza.zza(new ComponentName(this.zza.zza.zzm(), "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
