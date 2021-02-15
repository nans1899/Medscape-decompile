package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavw extends zzavj {
    private final RewardedInterstitialAdLoadCallback zzdwg;
    private final zzavz zzdwh;

    public zzavw(RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback, zzavz zzavz) {
        this.zzdwg = rewardedInterstitialAdLoadCallback;
        this.zzdwh = zzavz;
    }

    public final void onRewardedAdLoaded() {
        zzavz zzavz;
        RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback = this.zzdwg;
        if (rewardedInterstitialAdLoadCallback != null && (zzavz = this.zzdwh) != null) {
            rewardedInterstitialAdLoadCallback.onRewardedInterstitialAdLoaded(zzavz);
        }
    }

    public final void onRewardedAdFailedToLoad(int i) {
        RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback = this.zzdwg;
        if (rewardedInterstitialAdLoadCallback != null) {
            rewardedInterstitialAdLoadCallback.onRewardedInterstitialAdFailedToLoad(i);
        }
    }

    public final void zzi(zzva zzva) {
        RewardedInterstitialAdLoadCallback rewardedInterstitialAdLoadCallback = this.zzdwg;
        if (rewardedInterstitialAdLoadCallback != null) {
            rewardedInterstitialAdLoadCallback.onRewardedInterstitialAdFailedToLoad(zzva.zzpi());
        }
    }
}
