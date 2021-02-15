package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzaqa implements zzp {
    private final /* synthetic */ zzapx zzdmv;

    zzaqa(zzapx zzapx) {
        this.zzdmv = zzapx;
    }

    public final void zzum() {
        zzbbq.zzef("AdMobCustomTabsAdapter overlay is closed.");
        this.zzdmv.zzdmt.onAdClosed(this.zzdmv);
    }

    public final void onPause() {
        zzbbq.zzef("AdMobCustomTabsAdapter overlay is paused.");
    }

    public final void onResume() {
        zzbbq.zzef("AdMobCustomTabsAdapter overlay is resumed.");
    }

    public final void zzun() {
        zzbbq.zzef("Opening AdMobCustomTabsAdapter overlay.");
        this.zzdmv.zzdmt.onAdOpened(this.zzdmv);
    }
}
