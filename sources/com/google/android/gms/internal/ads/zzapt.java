package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzapt implements MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> {
    private final /* synthetic */ zzanh zzdmh;
    private final /* synthetic */ zzapq zzdmn;
    private final /* synthetic */ zzapg zzdmo;

    zzapt(zzapq zzapq, zzapg zzapg, zzanh zzanh) {
        this.zzdmn = zzapq;
        this.zzdmo = zzapg;
        this.zzdmh = zzanh;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final MediationRewardedAdCallback onSuccess(MediationRewardedAd mediationRewardedAd) {
        if (mediationRewardedAd == null) {
            zzbbq.zzfe("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdmo.zzdq("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzbbq.zzc("", e);
                return null;
            }
        } else {
            try {
                MediationRewardedAd unused = this.zzdmn.zzdlj = mediationRewardedAd;
                this.zzdmo.zzul();
            } catch (RemoteException e2) {
                zzbbq.zzc("", e2);
            }
            return new zzapw(this.zzdmh);
        }
    }

    public final void onFailure(String str) {
        try {
            this.zzdmo.zzdq(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onFailure(AdError adError) {
        try {
            this.zzdmo.zzf(adError.zzdo());
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
