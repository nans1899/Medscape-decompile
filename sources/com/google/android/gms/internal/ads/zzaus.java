package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaus implements MediationRewardedVideoAdListener {
    private final zzaur zzdwb;

    public zzaus(zzaur zzaur) {
        this.zzdwb = zzaur;
    }

    public final void onInitializationSucceeded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onInitializationSucceeded.");
        try {
            this.zzdwb.zzaf(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onInitializationFailed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onInitializationFailed.");
        try {
            this.zzdwb.zzd(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onAdLoaded.");
        try {
            this.zzdwb.zzag(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onAdOpened.");
        try {
            this.zzdwb.zzah(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onVideoStarted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onVideoStarted.");
        try {
            this.zzdwb.zzai(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onAdClosed.");
        try {
            this.zzdwb.zzaj(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onRewarded(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, RewardItem rewardItem) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onRewarded.");
        if (rewardItem != null) {
            try {
                this.zzdwb.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzauv(rewardItem));
            } catch (RemoteException e) {
                zzbbq.zze("#007 Could not call remote method.", e);
            }
        } else {
            this.zzdwb.zza(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), new zzauv("", 1));
        }
    }

    public final void onAdClicked(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onAdClicked.");
        try {
            this.zzdwb.zzak(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onAdFailedToLoad.");
        try {
            this.zzdwb.zze(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter), i);
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onAdLeftApplication.");
        try {
            this.zzdwb.zzal(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void onVideoCompleted(MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onVideoCompleted.");
        try {
            this.zzdwb.zzam(ObjectWrapper.wrap(mediationRewardedVideoAdAdapter));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void zzb(Bundle bundle) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzbbq.zzef("Adapter called onAdMetadataChanged.");
        try {
            this.zzdwb.zzb(bundle);
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
