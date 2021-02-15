package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzjz {
    final /* synthetic */ zzju zza;
    private zzjy zzb;

    zzjz(zzju zzju) {
        this.zza = zzju;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zza.zzc();
        if (this.zzb != null) {
            this.zza.zzc.removeCallbacks(this.zzb);
        }
        if (this.zza.zzs().zza(zzat.zzbu)) {
            this.zza.zzr().zzr.zza(false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j) {
        this.zzb = new zzjy(this, this.zza.zzl().currentTimeMillis(), j);
        this.zza.zzc.postDelayed(this.zzb, 2000);
    }
}
