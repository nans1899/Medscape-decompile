package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbhf implements Runnable {
    private final int zzebs;
    private final int zzebt;
    private final boolean zzens;
    private final boolean zzent;
    private final zzbhd zzepy;

    zzbhf(zzbhd zzbhd, int i, int i2, boolean z, boolean z2) {
        this.zzepy = zzbhd;
        this.zzebs = i;
        this.zzebt = i2;
        this.zzens = z;
        this.zzent = z2;
    }

    public final void run() {
        this.zzepy.zzb(this.zzebs, this.zzebt, this.zzens, this.zzent);
    }
}
