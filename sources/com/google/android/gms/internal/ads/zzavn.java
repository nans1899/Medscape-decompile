package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzavn {
    private final Context zzaai;
    private final zzauy zzdwd;

    public zzavn(Context context, String str) {
        this.zzaai = context.getApplicationContext();
        this.zzdwd = zzwm.zzpu().zzc(context, str, new zzanc());
    }

    public final void zza(zzyw zzyw, RewardedAdLoadCallback rewardedAdLoadCallback) {
        try {
            this.zzdwd.zza(zzvl.zza(this.zzaai, zzyw), (zzavg) new zzavq(rewardedAdLoadCallback));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzdwd.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return "";
        }
    }

    public final void setServerSideVerificationOptions(ServerSideVerificationOptions serverSideVerificationOptions) {
        try {
            this.zzdwd.zza(new zzavt(serverSideVerificationOptions));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void setOnAdMetadataChangedListener(OnAdMetadataChangedListener onAdMetadataChangedListener) {
        try {
            this.zzdwd.zza((zzyh) new zzaac(onAdMetadataChangedListener));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final Bundle getAdMetadata() {
        try {
            return this.zzdwd.getAdMetadata();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return new Bundle();
        }
    }

    public final boolean isLoaded() {
        try {
            return this.zzdwd.isLoaded();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return false;
        }
    }

    public final void show(Activity activity, RewardedAdCallback rewardedAdCallback) {
        try {
            this.zzdwd.zza((zzavd) new zzavp(rewardedAdCallback));
            this.zzdwd.zzh(ObjectWrapper.wrap(activity));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void show(Activity activity, RewardedAdCallback rewardedAdCallback, boolean z) {
        try {
            this.zzdwd.zza((zzavd) new zzavp(rewardedAdCallback));
            this.zzdwd.zza(ObjectWrapper.wrap(activity), z);
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final RewardItem getRewardItem() {
        try {
            zzaux zzqz = this.zzdwd.zzqz();
            if (zzqz == null) {
                return null;
            }
            return new zzavm(zzqz);
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }

    public final ResponseInfo getResponseInfo() {
        zzyn zzyn;
        try {
            zzyn = this.zzdwd.zzki();
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            zzyn = null;
        }
        return ResponseInfo.zza(zzyn);
    }

    public final void setOnPaidEventListener(OnPaidEventListener onPaidEventListener) {
        try {
            this.zzdwd.zza((zzyi) new zzaab(onPaidEventListener));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
