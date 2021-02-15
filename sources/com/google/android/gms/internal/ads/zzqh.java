package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqh implements Runnable {
    private final /* synthetic */ zzho zzajf;
    private final /* synthetic */ zzqg zzbmx;

    zzqh(zzqg zzqg, zzho zzho) {
        this.zzbmx = zzqg;
        this.zzajf = zzho;
    }

    public final void run() {
        this.zzbmx.zzbmy.zzk(this.zzajf);
    }
}
