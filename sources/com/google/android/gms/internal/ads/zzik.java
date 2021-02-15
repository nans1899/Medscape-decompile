package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzik implements Runnable {
    private final /* synthetic */ zzij zzajd;
    private final /* synthetic */ zzho zzajf;

    zzik(zzij zzij, zzho zzho) {
        this.zzajd = zzij;
        this.zzajf = zzho;
    }

    public final void run() {
        this.zzajd.zzaje.zzb(this.zzajf);
    }
}
