package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzgi implements Runnable {
    private final /* synthetic */ zzar zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzfw zzc;

    zzgi(zzfw zzfw, zzar zzar, String str) {
        this.zzc = zzfw;
        this.zza = zzar;
        this.zzb = str;
    }

    public final void run() {
        this.zzc.zza.zzr();
        this.zzc.zza.zza(this.zza, this.zzb);
    }
}
