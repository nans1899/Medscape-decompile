package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqf implements Runnable {
    private final /* synthetic */ zzjl zzajc;
    private final /* synthetic */ zzqg zzbmx;

    zzqf(zzqg zzqg, zzjl zzjl) {
        this.zzbmx = zzqg;
        this.zzajc = zzjl;
    }

    public final void run() {
        this.zzbmx.zzbmy.zze(this.zzajc);
    }
}
