package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzmu;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzkc {
    final /* synthetic */ zzju zza;

    zzkc(zzju zzju) {
        this.zza = zzju;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zza.zzc();
        if (this.zza.zzr().zza(this.zza.zzl().currentTimeMillis())) {
            this.zza.zzr().zzm.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.zza.zzq().zzw().zza("Detected application was in foreground");
                zzb(this.zza.zzl().currentTimeMillis(), false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j, boolean z) {
        this.zza.zzc();
        this.zza.zzaa();
        if (this.zza.zzr().zza(j)) {
            this.zza.zzr().zzm.zza(true);
        }
        this.zza.zzr().zzp.zza(j);
        if (this.zza.zzr().zzm.zza()) {
            zzb(j, z);
        }
    }

    private final void zzb(long j, boolean z) {
        this.zza.zzc();
        if (this.zza.zzy.zzaa()) {
            this.zza.zzr().zzp.zza(j);
            this.zza.zzq().zzw().zza("Session started, time", Long.valueOf(this.zza.zzl().elapsedRealtime()));
            Long valueOf = Long.valueOf(j / 1000);
            this.zza.zze().zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_sid", (Object) valueOf, j);
            this.zza.zzr().zzm.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", valueOf.longValue());
            if (this.zza.zzs().zza(zzat.zzbj) && z) {
                bundle.putLong("_aib", 1);
            }
            this.zza.zze().zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_s", j, bundle);
            if (zzmu.zzb() && this.zza.zzs().zza(zzat.zzbo)) {
                String zza2 = this.zza.zzr().zzu.zza();
                if (!TextUtils.isEmpty(zza2)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("_ffr", zza2);
                    this.zza.zze().zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ssr", j, bundle2);
                }
            }
        }
    }
}
