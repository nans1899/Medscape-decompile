package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationInterstitialAd;
import com.google.android.gms.ads.mediation.MediationInterstitialAdCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzaps implements MediationAdLoadCallback<MediationInterstitialAd, MediationInterstitialAdCallback> {
    private final /* synthetic */ zzanh zzdmh;
    private final /* synthetic */ zzapa zzdmm;
    private final /* synthetic */ zzapq zzdmn;

    zzaps(zzapq zzapq, zzapa zzapa, zzanh zzanh) {
        this.zzdmn = zzapq;
        this.zzdmm = zzapa;
        this.zzdmh = zzanh;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final MediationInterstitialAdCallback onSuccess(MediationInterstitialAd mediationInterstitialAd) {
        if (mediationInterstitialAd == null) {
            zzbbq.zzfe("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdmm.zzdq("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzbbq.zzc("", e);
                return null;
            }
        } else {
            try {
                MediationInterstitialAd unused = this.zzdmn.zzdmj = mediationInterstitialAd;
                this.zzdmm.zzul();
            } catch (RemoteException e2) {
                zzbbq.zzc("", e2);
            }
            return new zzapw(this.zzdmh);
        }
    }

    public final void onFailure(String str) {
        try {
            this.zzdmm.zzdq(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onFailure(AdError adError) {
        try {
            this.zzdmm.zzf(adError.zzdo());
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
