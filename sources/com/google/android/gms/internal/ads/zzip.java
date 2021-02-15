package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzip implements Runnable {
    private final /* synthetic */ zzij zzajd;
    private final /* synthetic */ int zzalp;

    zzip(zzij zzij, int i) {
        this.zzajd = zzij;
        this.zzalp = i;
    }

    public final void run() {
        this.zzajd.zzaje.zzx(this.zzalp);
    }
}
