package com.google.android.gms.ads.mediation;

import android.content.Context;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class Adapter implements MediationExtrasReceiver {
    public abstract VersionInfo getSDKVersionInfo();

    public abstract VersionInfo getVersionInfo();

    public abstract void initialize(Context context, InitializationCompleteCallback initializationCompleteCallback, List<MediationConfiguration> list);

    public void loadBannerAd(MediationBannerAdConfiguration mediationBannerAdConfiguration, MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(String.valueOf(getClass().getSimpleName()).concat(" does not support banner ads."));
    }

    public void loadInterstitialAd(MediationInterstitialAdConfiguration mediationInterstitialAdConfiguration, MediationAdLoadCallback<MediationInterstitialAd, MediationInterstitialAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(String.valueOf(getClass().getSimpleName()).concat(" does not support interstitial ads."));
    }

    public void loadRewardedAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(String.valueOf(getClass().getSimpleName()).concat(" does not support rewarded ads."));
    }

    public void loadRewardedInterstitialAd(MediationRewardedAdConfiguration mediationRewardedAdConfiguration, MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(String.valueOf(getClass().getSimpleName()).concat(" does not support rewarded interstitial ads."));
    }

    public void loadNativeAd(MediationNativeAdConfiguration mediationNativeAdConfiguration, MediationAdLoadCallback<UnifiedNativeAdMapper, MediationNativeAdCallback> mediationAdLoadCallback) {
        mediationAdLoadCallback.onFailure(String.valueOf(getClass().getSimpleName()).concat(" does not support native ads."));
    }
}
