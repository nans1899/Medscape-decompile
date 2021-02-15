package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzza;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class PublisherInterstitialAd {
    private final zzza zzadi;

    public PublisherInterstitialAd(Context context) {
        this.zzadi = new zzza(context, this);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    @Deprecated
    public final void setCorrelator(Correlator correlator) {
    }

    public final AdListener getAdListener() {
        return this.zzadi.getAdListener();
    }

    public final String getAdUnitId() {
        return this.zzadi.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzadi.getAppEventListener();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzadi.getOnCustomRenderedAdLoadedListener();
    }

    public final boolean isLoaded() {
        return this.zzadi.isLoaded();
    }

    public final boolean isLoading() {
        return this.zzadi.isLoading();
    }

    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zzadi.zza(publisherAdRequest.zzdr());
    }

    public final void setAdListener(AdListener adListener) {
        this.zzadi.setAdListener(adListener);
    }

    public final void setAdUnitId(String str) {
        this.zzadi.setAdUnitId(str);
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        this.zzadi.setAppEventListener(appEventListener);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzadi.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }

    @Deprecated
    public final String getMediationAdapterClassName() {
        return this.zzadi.getMediationAdapterClassName();
    }

    public final void show() {
        this.zzadi.show();
    }

    public final void setImmersiveMode(boolean z) {
        this.zzadi.setImmersiveMode(z);
    }

    public final ResponseInfo getResponseInfo() {
        return this.zzadi.getResponseInfo();
    }
}
