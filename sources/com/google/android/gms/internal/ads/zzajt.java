package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzajt implements Runnable {
    private final zzaju zzdgl;
    private final String zzdgm;

    zzajt(zzaju zzaju, String str) {
        this.zzdgl = zzaju;
        this.zzdgm = str;
    }

    public final void run() {
        this.zzdgl.zzdf(this.zzdgm);
    }
}
