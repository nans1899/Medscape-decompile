package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzczs implements zzbuh, zzbui, zzbuz, zzbvs, zzux {
    private zzwt zzgth;

    public final void onRewardedVideoCompleted() {
    }

    public final void onRewardedVideoStarted() {
    }

    public final void zzb(zzatw zzatw, String str, String str2) {
    }

    public final synchronized void zzc(zzwt zzwt) {
        this.zzgth = zzwt;
    }

    public final synchronized zzwt zzaqt() {
        return this.zzgth;
    }

    public final synchronized void onAdClosed() {
        if (this.zzgth != null) {
            try {
                this.zzgth.onAdClosed();
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdClosed.", e);
            }
        }
    }

    public final synchronized void zzk(zzva zzva) {
        if (this.zzgth != null) {
            try {
                this.zzgth.zzc(zzva);
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdFailedToLoadWithAdError.", e);
            }
        }
        if (this.zzgth != null) {
            try {
                this.zzgth.onAdFailedToLoad(zzva.errorCode);
            } catch (RemoteException e2) {
                zzayp.zzd("Remote Exception at onAdFailedToLoad.", e2);
            }
        }
    }

    public final synchronized void onAdLeftApplication() {
        if (this.zzgth != null) {
            try {
                this.zzgth.onAdLeftApplication();
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdLeftApplication.", e);
            }
        }
    }

    public final synchronized void onAdLoaded() {
        if (this.zzgth != null) {
            try {
                this.zzgth.onAdLoaded();
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdLoaded.", e);
            }
        }
    }

    public final synchronized void onAdOpened() {
        if (this.zzgth != null) {
            try {
                this.zzgth.onAdOpened();
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdOpened.", e);
            }
        }
    }

    public final synchronized void onAdClicked() {
        if (this.zzgth != null) {
            try {
                this.zzgth.onAdClicked();
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdClicked.", e);
            }
        }
    }

    public final synchronized void onAdImpression() {
        if (this.zzgth != null) {
            try {
                this.zzgth.onAdImpression();
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception at onAdImpression.", e);
            }
        }
    }
}
