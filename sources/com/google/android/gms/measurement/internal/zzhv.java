package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhv implements Runnable {
    private final /* synthetic */ Boolean zza;
    private final /* synthetic */ zzgy zzb;

    zzhv(zzgy zzgy, Boolean bool) {
        this.zzb = zzgy;
        this.zza = bool;
    }

    public final void run() {
        this.zzb.zza(this.zza, true);
    }
}
