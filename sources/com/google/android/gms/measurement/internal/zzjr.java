package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzae;
import com.google.android.gms.measurement.internal.zzjv;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public final class zzjr<T extends Context & zzjv> {
    private final T zza;

    public zzjr(T t) {
        Preconditions.checkNotNull(t);
        this.zza = t;
    }

    public final void zza() {
        zzfv.zza(this.zza, (zzae) null, (Long) null).zzq().zzw().zza("Local AppMeasurementService is starting up");
    }

    public final void zzb() {
        zzfv.zza(this.zza, (zzae) null, (Long) null).zzq().zzw().zza("Local AppMeasurementService is shutting down");
    }

    public final int zza(Intent intent, int i, int i2) {
        zzer zzq = zzfv.zza(this.zza, (zzae) null, (Long) null).zzq();
        if (intent == null) {
            zzq.zzh().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzq.zzw().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zza((Runnable) new zzjq(this, i2, zzq, intent));
        }
        return 2;
    }

    private final void zza(Runnable runnable) {
        zzki zza2 = zzki.zza((Context) this.zza);
        zza2.zzp().zza((Runnable) new zzjs(this, zza2, runnable));
    }

    public final IBinder zza(Intent intent) {
        if (intent == null) {
            zzc().zze().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzfw(zzki.zza((Context) this.zza));
        }
        zzc().zzh().zza("onBind received unknown action", action);
        return null;
    }

    public final boolean zzb(Intent intent) {
        if (intent == null) {
            zzc().zze().zza("onUnbind called with null intent");
            return true;
        }
        zzc().zzw().zza("onUnbind called for intent. action", intent.getAction());
        return true;
    }

    public final boolean zza(JobParameters jobParameters) {
        zzer zzq = zzfv.zza(this.zza, (zzae) null, (Long) null).zzq();
        String string = jobParameters.getExtras().getString("action");
        zzq.zzw().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza((Runnable) new zzjt(this, zzq, jobParameters));
        return true;
    }

    public final void zzc(Intent intent) {
        if (intent == null) {
            zzc().zze().zza("onRebind called with null intent");
            return;
        }
        zzc().zzw().zza("onRebind called. action", intent.getAction());
    }

    private final zzer zzc() {
        return zzfv.zza(this.zza, (zzae) null, (Long) null).zzq();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzer zzer, JobParameters jobParameters) {
        zzer.zzw().zza("AppMeasurementJobService processed last upload request.");
        ((zzjv) this.zza).zza(jobParameters, false);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzer zzer, Intent intent) {
        if (((zzjv) this.zza).zza(i)) {
            zzer.zzw().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzc().zzw().zza("Completed wakeful intent.");
            ((zzjv) this.zza).zza(intent);
        }
    }
}
