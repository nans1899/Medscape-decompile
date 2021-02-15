package com.google.android.gms.ads.rewarded;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzavn;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class RewardedAd {
    private final zzavn zzhlv;

    public RewardedAd(Context context, String str) {
        Preconditions.checkNotNull(context, "context cannot be null");
        Preconditions.checkNotNull(str, "adUnitID cannot be null");
        this.zzhlv = new zzavn(context, str);
    }

    public final void loadAd(AdRequest adRequest, RewardedAdLoadCallback rewardedAdLoadCallback) {
        this.zzhlv.zza(adRequest.zzdr(), rewardedAdLoadCallback);
    }

    public final void loadAd(PublisherAdRequest publisherAdRequest, RewardedAdLoadCallback rewardedAdLoadCallback) {
        this.zzhlv.zza(publisherAdRequest.zzdr(), rewardedAdLoadCallback);
    }

    @Deprecated
    public final String getMediationAdapterClassName() {
        return this.zzhlv.getMediationAdapterClassName();
    }

    public final void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        this.zzhlv.setServerSideVerificationOptions(serverSideVerificationOptions);
    }

    public final void setOnAdMetadataChangedListener(OnAdMetadataChangedListener onAdMetadataChangedListener) {
        this.zzhlv.setOnAdMetadataChangedListener(onAdMetadataChangedListener);
    }

    public final Bundle getAdMetadata() {
        return this.zzhlv.getAdMetadata();
    }

    public final boolean isLoaded() {
        return this.zzhlv.isLoaded();
    }

    public final void show(Activity activity, RewardedAdCallback rewardedAdCallback) {
        this.zzhlv.show(activity, rewardedAdCallback);
    }

    public final void show(Activity activity, RewardedAdCallback rewardedAdCallback, boolean z) {
        this.zzhlv.show(activity, rewardedAdCallback, z);
    }

    public final RewardItem getRewardItem() {
        return this.zzhlv.getRewardItem();
    }

    public final ResponseInfo getResponseInfo() {
        return this.zzhlv.getResponseInfo();
    }

    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        this.zzhlv.setOnPaidEventListener(onPaidEventListener);
    }
}
