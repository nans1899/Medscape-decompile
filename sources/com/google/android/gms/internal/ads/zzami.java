package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzami implements zzbci {
    private final /* synthetic */ zzbcg zzdfq;
    private final /* synthetic */ zzali zzdit;

    zzami(zzamg zzamg, zzbcg zzbcg, zzali zzali) {
        this.zzdfq = zzbcg;
        this.zzdit = zzali;
    }

    public final void run() {
        this.zzdfq.setException(new zzalu("Unable to obtain a JavascriptEngine."));
        this.zzdit.release();
    }
}
