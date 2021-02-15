package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzql implements Runnable {
    private final /* synthetic */ zzjl zzajj;
    private final /* synthetic */ zzqg zzbmx;

    zzql(zzqg zzqg, zzjl zzjl) {
        this.zzbmx = zzqg;
        this.zzajj = zzjl;
    }

    public final void run() {
        this.zzajj.zzgn();
        this.zzbmx.zzbmy.zzf(this.zzajj);
    }
}
