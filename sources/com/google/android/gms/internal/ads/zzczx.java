package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzczx implements Runnable {
    private final zzva zzgte;
    private final zzczt zzgtn;

    zzczx(zzczt zzczt, zzva zzva) {
        this.zzgtn = zzczt;
        this.zzgte = zzva;
    }

    public final void run() {
        zzczt zzczt = this.zzgtn;
        zzczt.zzgtj.zzgtk.zzk(this.zzgte);
    }
}
