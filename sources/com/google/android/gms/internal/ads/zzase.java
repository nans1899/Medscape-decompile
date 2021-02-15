package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final /* synthetic */ class zzase implements Runnable {
    private final String zzdgm;
    private final zzbbu zzdqh;

    zzase(zzbbu zzbbu, String str) {
        this.zzdqh = zzbbu;
        this.zzdgm = str;
    }

    public final void run() {
        this.zzdqh.zzes(this.zzdgm);
    }
}
