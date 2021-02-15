package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbxa implements zzbxt {
    private final String zzdgm;
    private final String zzdgt;

    zzbxa(String str, String str2) {
        this.zzdgt = str;
        this.zzdgm = str2;
    }

    public final void zzq(Object obj) {
        ((zzczr) obj).onAppEvent(this.zzdgt, this.zzdgm);
    }
}
