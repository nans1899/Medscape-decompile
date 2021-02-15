package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzakj implements Runnable {
    private final String zzdgm;
    private final zzakk zzdgx;

    zzakj(zzakk zzakk, String str) {
        this.zzdgx = zzakk;
        this.zzdgm = str;
    }

    public final void run() {
        this.zzdgx.zzdh(this.zzdgm);
    }
}
