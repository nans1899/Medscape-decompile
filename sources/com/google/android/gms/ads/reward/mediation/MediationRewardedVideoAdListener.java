package com.google.android.gms.ads.reward.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.reward.RewardItem;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface MediationRewardedVideoAdListener {
    void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i);

    void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i);

    void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, RewardItem rewardItem);

    void onVideoCompleted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter);

    void zzb(Bundle bundle);
}
