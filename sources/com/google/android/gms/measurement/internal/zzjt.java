package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final /* synthetic */ class zzjt implements Runnable {
    private final zzjr zza;
    private final zzer zzb;
    private final JobParameters zzc;

    zzjt(zzjr zzjr, zzer zzer, JobParameters jobParameters) {
        this.zza = zzjr;
        this.zzb = zzer;
        this.zzc = jobParameters;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
