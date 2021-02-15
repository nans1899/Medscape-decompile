package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcfd implements Runnable {
    private final zzcfe zzgbg;
    private final zzcgc zzgbh;

    zzcfd(zzcfe zzcfe, zzcgc zzcgc) {
        this.zzgbg = zzcfe;
        this.zzgbh = zzcgc;
    }

    public final void run() {
        this.zzgbg.zze(this.zzgbh);
    }
}
