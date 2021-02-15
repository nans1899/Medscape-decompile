package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzvc extends zzws {
    private final AdListener zzcha;

    public zzvc(AdListener adListener) {
        this.zzcha = adListener;
    }

    public final void onAdClosed() {
        this.zzcha.onAdClosed();
    }

    public final void onAdFailedToLoad(int i) {
        this.zzcha.onAdFailedToLoad(i);
    }

    public final void zzc(zzva zzva) {
        this.zzcha.onAdFailedToLoad(zzva.zzpi());
    }

    public final void onAdLeftApplication() {
        this.zzcha.onAdLeftApplication();
    }

    public final void onAdLoaded() {
        this.zzcha.onAdLoaded();
    }

    public final void onAdOpened() {
        this.zzcha.onAdOpened();
    }

    public final void onAdClicked() {
        this.zzcha.onAdClicked();
    }

    public final void onAdImpression() {
        this.zzcha.onAdImpression();
    }

    public final AdListener getAdListener() {
        return this.zzcha;
    }
}
