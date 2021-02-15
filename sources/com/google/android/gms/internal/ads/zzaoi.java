package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaoi<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    /* access modifiers changed from: private */
    public final zzanh zzdlq;

    public zzaoi(zzanh zzanh) {
        this.zzdlq = zzanh;
    }

    public final void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbq.zzef("Adapter called onClick.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaoh(this));
            return;
        }
        try {
            this.zzdlq.onAdClicked();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbq.zzef("Adapter called onDismissScreen.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zzfe("#008 Must be called on the main UI thread.");
            zzbbg.zzaah.post(new zzaom(this));
            return;
        }
        try {
            this.zzdlq.onAdClosed();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, AdRequest.ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error. ");
        sb.append(valueOf);
        zzbbq.zzef(sb.toString());
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaol(this, errorCode));
            return;
        }
        try {
            this.zzdlq.onAdFailedToLoad(zzaou.zza(errorCode));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbq.zzef("Adapter called onLeaveApplication.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaoo(this));
            return;
        }
        try {
            this.zzdlq.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbq.zzef("Adapter called onPresentScreen.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaon(this));
            return;
        }
        try {
            this.zzdlq.onAdOpened();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzbbq.zzef("Adapter called onReceivedAd.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaoq(this));
            return;
        }
        try {
            this.zzdlq.onAdLoaded();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbq.zzef("Adapter called onDismissScreen.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaop(this));
            return;
        }
        try {
            this.zzdlq.onAdClosed();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, AdRequest.ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
        sb.append("Adapter called onFailedToReceiveAd with error ");
        sb.append(valueOf);
        sb.append(".");
        zzbbq.zzef(sb.toString());
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaos(this, errorCode));
            return;
        }
        try {
            this.zzdlq.onAdFailedToLoad(zzaou.zza(errorCode));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbq.zzef("Adapter called onLeaveApplication.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaor(this));
            return;
        }
        try {
            this.zzdlq.onAdLeftApplication();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbq.zzef("Adapter called onPresentScreen.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaok(this));
            return;
        }
        try {
            this.zzdlq.onAdOpened();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzbbq.zzef("Adapter called onReceivedAd.");
        zzwm.zzpt();
        if (!zzbbg.zzyn()) {
            zzbbq.zze("#008 Must be called on the main UI thread.", (Throwable) null);
            zzbbg.zzaah.post(new zzaoj(this));
            return;
        }
        try {
            this.zzdlq.onAdLoaded();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
