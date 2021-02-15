package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final /* synthetic */ class zzjq implements Runnable {
    private final zzjr zza;
    private final int zzb;
    private final zzer zzc;
    private final Intent zzd;

    zzjq(zzjr zzjr, int i, zzer zzer, Intent intent) {
        this.zza = zzjr;
        this.zzb = i;
        this.zzc = zzer;
        this.zzd = intent;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}
