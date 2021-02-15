package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavq extends zzavj {
    private final RewardedAdLoadCallback zzdwf;

    public zzavq(RewardedAdLoadCallback rewardedAdLoadCallback) {
        this.zzdwf = rewardedAdLoadCallback;
    }

    public final void onRewardedAdLoaded() {
        RewardedAdLoadCallback rewardedAdLoadCallback = this.zzdwf;
        if (rewardedAdLoadCallback != null) {
            rewardedAdLoadCallback.onRewardedAdLoaded();
        }
    }

    public final void onRewardedAdFailedToLoad(int i) {
        RewardedAdLoadCallback rewardedAdLoadCallback = this.zzdwf;
        if (rewardedAdLoadCallback != null) {
            rewardedAdLoadCallback.onRewardedAdFailedToLoad(i);
        }
    }

    public final void zzi(zzva zzva) {
        RewardedAdLoadCallback rewardedAdLoadCallback = this.zzdwf;
        if (rewardedAdLoadCallback != null) {
            rewardedAdLoadCallback.onRewardedAdFailedToLoad(zzva.zzpi());
        }
    }
}
