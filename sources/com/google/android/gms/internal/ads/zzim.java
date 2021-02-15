package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzim implements Runnable {
    private final /* synthetic */ zzij zzajd;
    private final /* synthetic */ zzjl zzajj;

    zzim(zzij zzij, zzjl zzjl) {
        this.zzajd = zzij;
        this.zzajj = zzjl;
    }

    public final void run() {
        this.zzajj.zzgn();
        this.zzajd.zzaje.zzb(this.zzajj);
    }
}
