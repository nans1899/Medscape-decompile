package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqk implements Runnable {
    private final /* synthetic */ zzqg zzbmx;
    private final /* synthetic */ int zzbnd;
    private final /* synthetic */ long zzbne;

    zzqk(zzqg zzqg, int i, long j) {
        this.zzbmx = zzqg;
        this.zzbnd = i;
        this.zzbne = j;
    }

    public final void run() {
        this.zzbmx.zzbmy.zze(this.zzbnd, this.zzbne);
    }
}
