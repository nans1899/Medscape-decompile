package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzaos implements Runnable {
    private final /* synthetic */ zzaoi zzdmb;
    private final /* synthetic */ AdRequest.ErrorCode zzdmc;

    zzaos(zzaoi zzaoi, AdRequest.ErrorCode errorCode) {
        this.zzdmb = zzaoi;
        this.zzdmc = errorCode;
    }

    public final void run() {
        try {
            this.zzdmb.zzdlq.onAdFailedToLoad(zzaou.zza(this.zzdmc));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }
}
