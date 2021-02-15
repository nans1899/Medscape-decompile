package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzin implements Runnable {
    private final /* synthetic */ zzij zzajd;
    private final /* synthetic */ int zzajk;
    private final /* synthetic */ long zzajl;
    private final /* synthetic */ long zzajm;

    zzin(zzij zzij, int i, long j, long j2) {
        this.zzajd = zzij;
        this.zzajk = i;
        this.zzajl = j;
        this.zzajm = j2;
    }

    public final void run() {
        this.zzajd.zzaje.zza(this.zzajk, this.zzajl, this.zzajm);
    }
}
