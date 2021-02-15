package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzctx implements Runnable {
    private final zzbgj zzeqk;

    private zzctx(zzbgj zzbgj) {
        this.zzeqk = zzbgj;
    }

    static Runnable zzh(zzbgj zzbgj) {
        return new zzctx(zzbgj);
    }

    public final void run() {
        this.zzeqk.zzacj();
    }
}
