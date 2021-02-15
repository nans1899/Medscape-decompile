package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqi implements Runnable {
    private final /* synthetic */ String zzajg;
    private final /* synthetic */ long zzajh;
    private final /* synthetic */ long zzaji;
    private final /* synthetic */ zzqg zzbmx;

    zzqi(zzqg zzqg, String str, long j, long j2) {
        this.zzbmx = zzqg;
        this.zzajg = str;
        this.zzajh = j;
        this.zzaji = j2;
    }

    public final void run() {
        this.zzbmx.zzbmy.zzd(this.zzajg, this.zzajh, this.zzaji);
    }
}
