package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.rewarded.RewardedAdCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavp extends zzavc {
    private final RewardedAdCallback zzdwe;

    public zzavp(RewardedAdCallback rewardedAdCallback) {
        this.zzdwe = rewardedAdCallback;
    }

    public final void onRewardedAdOpened() {
        RewardedAdCallback rewardedAdCallback = this.zzdwe;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onRewardedAdOpened();
        }
    }

    public final void onRewardedAdClosed() {
        RewardedAdCallback rewardedAdCallback = this.zzdwe;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onRewardedAdClosed();
        }
    }

    public final void zza(zzaux zzaux) {
        RewardedAdCallback rewardedAdCallback = this.zzdwe;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onUserEarnedReward(new zzavm(zzaux));
        }
    }

    public final void onRewardedAdFailedToShow(int i) {
        RewardedAdCallback rewardedAdCallback = this.zzdwe;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onRewardedAdFailedToShow(i);
        }
    }

    public final void zzh(zzva zzva) {
        RewardedAdCallback rewardedAdCallback = this.zzdwe;
        if (rewardedAdCallback != null) {
            rewardedAdCallback.onRewardedAdFailedToShow(zzva.zzph());
        }
    }
}
