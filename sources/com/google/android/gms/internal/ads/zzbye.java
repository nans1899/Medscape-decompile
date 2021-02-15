package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbye implements zzbyt {
    private final String zzdgm;
    private final String zzdgt;

    zzbye(String str, String str2) {
        this.zzdgt = str;
        this.zzdgm = str2;
    }

    public final void zzp(Object obj) {
        ((AppEventListener) obj).onAppEvent(this.zzdgt, this.zzdgm);
    }
}
