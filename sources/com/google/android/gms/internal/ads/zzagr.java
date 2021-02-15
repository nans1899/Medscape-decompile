package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzagr extends zzagc {
    private final UnifiedNativeAd.UnconfirmedClickListener zzden;

    public zzagr(UnifiedNativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        this.zzden = unconfirmedClickListener;
    }

    public final void onUnconfirmedClickReceived(String str) {
        this.zzden.onUnconfirmedClickReceived(str);
    }

    public final void onUnconfirmedClickCancelled() {
        this.zzden.onUnconfirmedClickCancelled();
    }
}
