package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzil implements Runnable {
    private final /* synthetic */ zzij zzajd;
    private final /* synthetic */ String zzajg;
    private final /* synthetic */ long zzajh;
    private final /* synthetic */ long zzaji;

    zzil(zzij zzij, String str, long j, long j2) {
        this.zzajd = zzij;
        this.zzajg = str;
        this.zzajh = j;
        this.zzaji = j2;
    }

    public final void run() {
        this.zzajd.zzaje.zza(this.zzajg, this.zzajh, this.zzaji);
    }
}
