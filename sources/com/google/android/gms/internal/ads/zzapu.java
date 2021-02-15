package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.mediation.rtb.SignalCallbacks;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzapu implements SignalCallbacks {
    private final /* synthetic */ zzapm zzdmp;

    zzapu(zzapq zzapq, zzapm zzapm) {
        this.zzdmp = zzapm;
    }

    public final void onSuccess(String str) {
        try {
            this.zzdmp.zzds(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onFailure(String str) {
        try {
            this.zzdmp.onFailure(str);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final void onFailure(AdError adError) {
        try {
            this.zzdmp.zzg(adError.zzdo());
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}
