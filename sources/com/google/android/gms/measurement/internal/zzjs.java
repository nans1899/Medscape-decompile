package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzjs implements Runnable {
    private final /* synthetic */ zzki zza;
    private final /* synthetic */ Runnable zzb;

    zzjs(zzjr zzjr, zzki zzki, Runnable runnable) {
        this.zza = zzki;
        this.zzb = runnable;
    }

    public final void run() {
        this.zza.zzr();
        this.zza.zza(this.zzb);
        this.zza.zzo();
    }
}
