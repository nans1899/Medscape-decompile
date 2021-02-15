package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAdCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardItem;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzapw implements MediationBannerAdCallback, MediationInterstitialAdCallback, MediationNativeAdCallback, MediationRewardedAdCallback {
    private zzanh zzdmr;

    zzapw(zzanh zzanh) {
        this.zzdmr = zzanh;
    }

    public final void onVideoMute() {
    }

    public final void onVideoPause() {
    }

    public final void onVideoUnmute() {
    }

    public final void reportAdClicked() {
        try {
            this.zzdmr.onAdClicked();
        } catch (RemoteException unused) {
        }
    }

    public final void reportAdImpression() {
        try {
            this.zzdmr.onAdImpression();
        } catch (RemoteException unused) {
        }
    }

    public final void onAdOpened() {
        try {
            this.zzdmr.onAdOpened();
        } catch (RemoteException unused) {
        }
    }

    public final void onAdClosed() {
        try {
            this.zzdmr.onAdClosed();
        } catch (RemoteException unused) {
        }
    }

    public final void onAdLeftApplication() {
        try {
            this.zzdmr.onAdLeftApplication();
        } catch (RemoteException unused) {
        }
    }

    public final void onVideoPlay() {
        try {
            this.zzdmr.onVideoPlay();
        } catch (RemoteException unused) {
        }
    }

    public final void onUserEarnedReward(RewardItem rewardItem) {
        try {
            this.zzdmr.zza((zzaux) new zzavu(rewardItem));
        } catch (RemoteException unused) {
        }
    }

    public final void onVideoStart() {
        try {
            this.zzdmr.zzuc();
        } catch (RemoteException unused) {
        }
    }

    public final void onVideoComplete() {
        try {
            this.zzdmr.onVideoEnd();
        } catch (RemoteException unused) {
        }
    }

    public final void onAdFailedToShow(String str) {
        try {
            String valueOf = String.valueOf(str);
            zzbbq.zzfe(valueOf.length() != 0 ? "Mediated ad failed to show: ".concat(valueOf) : new String("Mediated ad failed to show: "));
            this.zzdmr.zzdo(str);
        } catch (RemoteException unused) {
        }
    }

    public final void onAdFailedToShow(AdError adError) {
        try {
            int code = adError.getCode();
            String message = adError.getMessage();
            String domain = adError.getDomain();
            StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 86 + String.valueOf(domain).length());
            sb.append("Mediated ad failed to show: Error Code = ");
            sb.append(code);
            sb.append(". Error Message = ");
            sb.append(message);
            sb.append(" Error Domain = ");
            sb.append(domain);
            zzbbq.zzfe(sb.toString());
            this.zzdmr.zze(adError.zzdo());
        } catch (RemoteException unused) {
        }
    }
}
