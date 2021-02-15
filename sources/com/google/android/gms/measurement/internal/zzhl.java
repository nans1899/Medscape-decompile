package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhl implements Runnable {
    private final /* synthetic */ zzgx zza;
    private final /* synthetic */ zzgy zzb;

    zzhl(zzgy zzgy, zzgx zzgx) {
        this.zzb = zzgy;
        this.zza = zzgx;
    }

    public final void run() {
        this.zzb.zza(this.zza);
    }
}
