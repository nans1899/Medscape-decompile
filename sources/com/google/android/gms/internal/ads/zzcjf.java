package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcjf implements Runnable {
    private final zzbgj zzeqk;

    private zzcjf(zzbgj zzbgj) {
        this.zzeqk = zzbgj;
    }

    static Runnable zzh(zzbgj zzbgj) {
        return new zzcjf(zzbgj);
    }

    public final void run() {
        this.zzeqk.destroy();
    }
}
