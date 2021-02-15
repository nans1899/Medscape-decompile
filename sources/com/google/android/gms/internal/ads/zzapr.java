package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.MediationAdLoadCallback;
import com.google.android.gms.ads.mediation.MediationNativeAdCallback;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzapr implements MediationAdLoadCallback<UnifiedNativeAdMapper, MediationNativeAdCallback> {
    private final /* synthetic */ zzanh zzdmh;
    private final /* synthetic */ zzapb zzdml;

    zzapr(zzapq zzapq, zzapb zzapb, zzanh zzanh) {
        this.zzdml = zzapb;
        this.zzdmh = zzanh;
    }

    /* access modifiers changed from: private */
    /* renamed from: zza */
    public final MediationNativeAdCallback onSuccess(UnifiedNativeAdMapper unifiedNativeAdMapper) {
        if (unifiedNativeAdMapper == null) {
            zzbbq.zzfe("Adapter incorrectly returned a null ad. The onFailure() callback should be called if an adapter fails to load an ad.");
            try {
                this.zzdml.zzdq("Adapter returned null.");
                return null;
            } catch (RemoteException e) {
                zzbbq.zzc("", e);
                return null;
            }
        } else {
            try {
                this.zzdml.zza(new zzaow(unifiedNativeAdMapper));
            } catch (RemoteException e2) {
                zzbbq.zzc("", e2);
            }
            return new zzapw(this.zzdmh);
        }
    }

    public final void onFailure(String str) {
        try {
            this.zzdml.zzdq(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onFailure(AdError adError) {
        try {
            this.zzdml.zzf(adError.zzdo());
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
