package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzii implements Runnable {
    private final /* synthetic */ zzjl zzajc;
    private final /* synthetic */ zzij zzajd;

    zzii(zzij zzij, zzjl zzjl) {
        this.zzajd = zzij;
        this.zzajc = zzjl;
    }

    public final void run() {
        this.zzajd.zzaje.zza(this.zzajc);
    }
}
