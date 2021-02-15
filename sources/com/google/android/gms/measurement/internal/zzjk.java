package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzjk implements Runnable {
    private final /* synthetic */ ComponentName zza;
    private final /* synthetic */ zzji zzb;

    zzjk(zzji zzji, ComponentName componentName) {
        this.zzb = zzji;
        this.zza = componentName;
    }

    public final void run() {
        this.zzb.zza.zza(this.zza);
    }
}
