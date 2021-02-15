package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnUserEarnedRewardListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavx extends zzavc {
    private FullScreenContentCallback zzdwi;
    private OnUserEarnedRewardListener zzdwj;

    public final void onRewardedAdFailedToShow(int i) {
    }

    public final void setFullScreenContentCallback(FullScreenContentCallback fullScreenContentCallback) {
        this.zzdwi = fullScreenContentCallback;
    }

    public final void zza(OnUserEarnedRewardListener onUserEarnedRewardListener) {
        this.zzdwj = onUserEarnedRewardListener;
    }

    public final void onRewardedAdOpened() {
        FullScreenContentCallback fullScreenContentCallback = this.zzdwi;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdShowedFullScreenContent();
        }
    }

    public final void onRewardedAdClosed() {
        FullScreenContentCallback fullScreenContentCallback = this.zzdwi;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdDismissedFullScreenContent();
        }
    }

    public final void zzh(zzva zzva) {
        FullScreenContentCallback fullScreenContentCallback = this.zzdwi;
        if (fullScreenContentCallback != null) {
            fullScreenContentCallback.onAdFailedToShowFullScreenContent(zzva.zzph());
        }
    }

    public final void zza(zzaux zzaux) {
        OnUserEarnedRewardListener onUserEarnedRewardListener = this.zzdwj;
        if (onUserEarnedRewardListener != null) {
            onUserEarnedRewardListener.onUserEarnedReward(new zzavm(zzaux));
        }
    }
}
