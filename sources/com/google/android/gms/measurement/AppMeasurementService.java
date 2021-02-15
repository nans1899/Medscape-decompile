package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.measurement.internal.zzjr;
import com.google.android.gms.measurement.internal.zzjv;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public final class AppMeasurementService extends Service implements zzjv {
    private zzjr<AppMeasurementService> zza;

    private final zzjr<AppMeasurementService> zza() {
        if (this.zza == null) {
            this.zza = new zzjr<>(this);
        }
        return this.zza;
    }

    public final void onCreate() {
        super.onCreate();
        zza().zza();
    }

    public final void onDestroy() {
        zza().zzb();
        super.onDestroy();
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return zza().zza(intent, i, i2);
    }

    public final IBinder onBind(Intent intent) {
        return zza().zza(intent);
    }

    public final boolean onUnbind(Intent intent) {
        return zza().zzb(intent);
    }

    public final void onRebind(Intent intent) {
        zza().zzc(intent);
    }

    public final boolean zza(int i) {
        return stopSelfResult(i);
    }

    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    public final void zza(Intent intent) {
        AppMeasurementReceiver.completeWakefulIntent(intent);
    }
}
