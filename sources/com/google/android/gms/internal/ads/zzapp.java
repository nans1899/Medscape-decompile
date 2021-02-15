package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationBannerAd;
import com.google.android.gms.ads.mediation.MediationBannerAdCallback;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzapp implements MediationAdLoadCallback<MediationBannerAd, MediationBannerAdCallback> {
    private final /* synthetic */ zzaov zzdmg;
    private final /* synthetic */ zzanh zzdmh;

    zzapp(zzapq zzapq, zzaov zzaov, zzanh zzanh) {
        this.zzdmg = zzaov;
        this.zzdmh = zzanh;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final MediationBannerAdCallback onSuccess(MediationBannerAd mediationBannerAd) {
        if (mediationBannerAd == null) {
            zzbbq.zzfe("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdmg.zzdq("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzbbq.zzc("", e);
                return null;
            }
        } else {
            try {
                this.zzdmg.zzx(ObjectWrapper.wrap(mediationBannerAd.getView()));
            } catch (RemoteException e2) {
                zzbbq.zzc("", e2);
            }
            return new zzapw(this.zzdmh);
        }
    }

    public final void onFailure(String str) {
        try {
            this.zzdmg.zzdq(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onFailure(AdError adError) {
        try {
            this.zzdmg.zzf(adError.zzdo());
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
