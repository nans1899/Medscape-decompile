package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzakn implements Runnable {
    private final /* synthetic */ String zzdhb;
    private final /* synthetic */ zzakk zzdhc;

    zzakn(zzakk zzakk, String str) {
        this.zzdhc = zzakk;
        this.zzdhb = str;
    }

    public final void run() {
        this.zzdhc.zzdgy.loadData(this.zzdhb, "text/html", "UTF-8");
    }
}
