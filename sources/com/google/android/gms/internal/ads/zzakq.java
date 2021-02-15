package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzakq implements Runnable {
    private final /* synthetic */ zzakk zzdhc;
    private final /* synthetic */ String zzdhe;

    zzakq(zzakk zzakk, String str) {
        this.zzdhc = zzakk;
        this.zzdhe = str;
    }

    public final void run() {
        this.zzdhc.zzdgy.loadUrl(this.zzdhe);
    }
}
