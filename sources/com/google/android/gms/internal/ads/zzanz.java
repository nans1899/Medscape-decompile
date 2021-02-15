package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationRewardedAd;
import com.google.android.gms.ads.mediation.MediationRewardedAdCallback;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzanz implements MediationAdLoadCallback<MediationRewardedAd, MediationRewardedAdCallback> {
    private final /* synthetic */ zzanh zzdlm;
    private final /* synthetic */ zzanx zzdln;

    zzanz(zzanx zzanx, zzanh zzanh) {
        this.zzdln = zzanx;
        this.zzdlm = zzanh;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final MediationRewardedAdCallback onSuccess(MediationRewardedAd mediationRewardedAd) {
        try {
            MediationRewardedAd unused = this.zzdln.zzdlj = mediationRewardedAd;
            this.zzdlm.onAdLoaded();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
        return new zzavv(this.zzdlm);
    }

    public final void onFailure(String str) {
        try {
            String canonicalName = this.zzdln.zzdlf.getClass().getCanonicalName();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 31 + String.valueOf(str).length());
            sb.append(canonicalName);
            sb.append("failed to loaded mediation ad: ");
            sb.append(str);
            zzbbq.zzef(sb.toString());
            this.zzdlm.zzc(0, str);
            this.zzdlm.onAdFailedToLoad(0);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onFailure(AdError adError) {
        try {
            String canonicalName = this.zzdln.zzdlf.getClass().getCanonicalName();
            int code = adError.getCode();
            String message = adError.getMessage();
            String domain = adError.getDomain();
            StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 87 + String.valueOf(message).length() + String.valueOf(domain).length());
            sb.append(canonicalName);
            sb.append("failed to loaded mediation ad: ErrorCode = ");
            sb.append(code);
            sb.append(". ErrorMessage = ");
            sb.append(message);
            sb.append(". ErrorDomain = ");
            sb.append(domain);
            zzbbq.zzef(sb.toString());
            this.zzdlm.zzc(adError.zzdo());
            this.zzdlm.zzc(adError.getCode(), adError.getMessage());
            this.zzdlm.onAdFailedToLoad(adError.getCode());
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
