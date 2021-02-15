package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbhg implements Runnable {
    private final String zzdgm;
    private final zzbhe zzeqj;

    zzbhg(zzbhe zzbhe, String str) {
        this.zzeqj = zzbhe;
        this.zzdgm = str;
    }

    public final void run() {
        this.zzeqj.zzfu(this.zzdgm);
    }
}
