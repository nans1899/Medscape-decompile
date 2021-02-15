package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzai implements Runnable {
    private final /* synthetic */ zzgq zza;
    private final /* synthetic */ zzaj zzb;

    zzai(zzaj zzaj, zzgq zzgq) {
        this.zzb = zzaj;
        this.zza = zzgq;
    }

    public final void run() {
        this.zza.zzt();
        if (zzx.zza()) {
            this.zza.zzp().zza((Runnable) this);
            return;
        }
        boolean zzb2 = this.zzb.zzb();
        long unused = this.zzb.zzd = 0;
        if (zzb2) {
            this.zzb.zza();
        }
    }
}
