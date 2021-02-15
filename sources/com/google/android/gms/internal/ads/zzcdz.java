package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcdz implements Runnable {
    private final zzceq zzfzf;

    private zzcdz(zzceq zzceq) {
        this.zzfzf = zzceq;
    }

    static Runnable zza(zzceq zzceq) {
        return new zzcdz(zzceq);
    }

    public final void run() {
        this.zzfzf.zzalr();
    }
}
