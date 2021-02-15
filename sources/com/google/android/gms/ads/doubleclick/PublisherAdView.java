package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzbbq;
import com.google.android.gms.internal.ads.zzxc;
import com.google.android.gms.internal.ads.zzyy;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class PublisherAdView extends ViewGroup {
    private final zzyy zzaea;

    public PublisherAdView(Context context) {
        super(context);
        this.zzaea = new zzyy(this);
    }

    @Deprecated
    public final void setCorrelator(Correlator correlator) {
    }

    public PublisherAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zzaea = new zzyy(this, attributeSet, true);
        Preconditions.checkNotNull(context, "Context cannot be null");
    }

    public PublisherAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zzaea = new zzyy(this, attributeSet, true);
    }

    public final VideoController getVideoController() {
        return this.zzaea.getVideoController();
    }

    public final void setVideoOptions(VideoOptions videoOptions) {
        this.zzaea.setVideoOptions(videoOptions);
    }

    public final VideoOptions getVideoOptions() {
        return this.zzaea.getVideoOptions();
    }

    public final void destroy() {
        this.zzaea.destroy();
    }

    public final AdListener getAdListener() {
        return this.zzaea.getAdListener();
    }

    public final AdSize getAdSize() {
        return this.zzaea.getAdSize();
    }

    public final AdSize[] getAdSizes() {
        return this.zzaea.getAdSizes();
    }

    public final String getAdUnitId() {
        return this.zzaea.getAdUnitId();
    }

    public final AppEventListener getAppEventListener() {
        return this.zzaea.getAppEventListener();
    }

    public final OnCustomRenderedAdLoadedListener getOnCustomRenderedAdLoadedListener() {
        return this.zzaea.getOnCustomRenderedAdLoadedListener();
    }

    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        this.zzaea.zza(publisherAdRequest.zzdr());
    }

    public final void pause() {
        this.zzaea.pause();
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzaea.setManualImpressionsEnabled(z);
    }

    public final void recordManualImpression() {
        this.zzaea.recordManualImpression();
    }

    public final void resume() {
        this.zzaea.resume();
    }

    public final void setAdListener(AdListener adListener) {
        this.zzaea.setAdListener(adListener);
    }

    public final void setAdSizes(AdSize... adSizeArr) {
        if (adSizeArr == null || adSizeArr.length <= 0) {
            throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
        }
        this.zzaea.zza(adSizeArr);
    }

    public final void setAdUnitId(String str) {
        this.zzaea.setAdUnitId(str);
    }

    public final void setAppEventListener(AppEventListener appEventListener) {
        this.zzaea.setAppEventListener(appEventListener);
    }

    public final void setOnCustomRenderedAdLoadedListener(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzaea.setOnCustomRenderedAdLoadedListener(onCustomRenderedAdLoadedListener);
    }

    @Deprecated
    public final String getMediationAdapterClassName() {
        return this.zzaea.getMediationAdapterClassName();
    }

    public final boolean isLoading() {
        return this.zzaea.isLoading();
    }

    public final ResponseInfo getResponseInfo() {
        return this.zzaea.getResponseInfo();
    }

    public final boolean zza(zzxc zzxc) {
        return this.zzaea.zza(zzxc);
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null && childAt.getVisibility() != 8) {
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int i5 = ((i3 - i) - measuredWidth) / 2;
            int i6 = ((i4 - i2) - measuredHeight) / 2;
            childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
        }
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        View childAt = getChildAt(0);
        if (childAt == null || childAt.getVisibility() == 8) {
            AdSize adSize = null;
            try {
                adSize = getAdSize();
            } catch (NullPointerException e) {
                zzbbq.zzc("Unable to retrieve ad size.", e);
            }
            if (adSize != null) {
                Context context = getContext();
                int widthInPixels = adSize.getWidthInPixels(context);
                i3 = adSize.getHeightInPixels(context);
                i4 = widthInPixels;
            } else {
                i3 = 0;
            }
        } else {
            measureChild(childAt, i, i2);
            i4 = childAt.getMeasuredWidth();
            i3 = childAt.getMeasuredHeight();
        }
        setMeasuredDimension(View.resolveSize(Math.max(i4, getSuggestedMinimumWidth()), i), View.resolveSize(Math.max(i3, getSuggestedMinimumHeight()), i2));
    }
}
