package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbec implements Runnable {
    private final String zzdgm;
    private final zzbeb zzejq;

    zzbec(zzbeb zzbeb, String str) {
        this.zzejq = zzbeb;
        this.zzdgm = str;
    }

    public final void run() {
        this.zzejq.zzfk(this.zzdgm);
    }
}
