package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqj implements Runnable {
    private final /* synthetic */ zzqg zzbmx;
    private final /* synthetic */ int zzbmz;
    private final /* synthetic */ int zzbna;
    private final /* synthetic */ int zzbnb;
    private final /* synthetic */ float zzbnc;

    zzqj(zzqg zzqg, int i, int i2, int i3, float f) {
        this.zzbmx = zzqg;
        this.zzbmz = i;
        this.zzbna = i2;
        this.zzbnb = i3;
        this.zzbnc = f;
    }

    public final void run() {
        this.zzbmx.zzbmy.zza(this.zzbmz, this.zzbna, this.zzbnb, this.zzbnc);
    }
}
