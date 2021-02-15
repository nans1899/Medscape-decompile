package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzfa implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzfb zzb;

    zzfa(zzfb zzfb, boolean z) {
        this.zzb = zzfb;
        this.zza = z;
    }

    public final void run() {
        this.zzb.zzb.zza(this.zza);
    }
}
