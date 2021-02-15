package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcuu extends zzank implements zzbvp {
    private zzanh zzdmr;
    private zzbvo zzgpc;

    public final synchronized void zzb(zzanh zzanh) {
        this.zzdmr = zzanh;
    }

    public final synchronized void zza(zzbvo zzbvo) {
        this.zzgpc = zzbvo;
    }

    public final synchronized void onAdClicked() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAdClicked();
        }
    }

    public final synchronized void onAdClosed() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAdClosed();
        }
    }

    public final synchronized void onAdFailedToLoad(int i) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAdFailedToLoad(i);
        }
        if (this.zzgpc != null) {
            this.zzgpc.onAdFailedToLoad(i);
        }
    }

    public final synchronized void zzc(int i, String str) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzc(i, str);
        }
        if (this.zzgpc != null) {
            this.zzgpc.zzf(i, str);
        }
    }

    public final synchronized void zzc(zzva zzva) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzc(zzva);
        }
        if (this.zzgpc != null) {
            this.zzgpc.zzk(zzva);
        }
    }

    public final synchronized void onAdLeftApplication() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAdLeftApplication();
        }
    }

    public final synchronized void onAdOpened() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAdOpened();
        }
    }

    public final synchronized void onAdLoaded() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAdLoaded();
        }
        if (this.zzgpc != null) {
            this.zzgpc.onAdLoaded();
        }
    }

    public final synchronized void zza(zzanm zzanm) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zza(zzanm);
        }
    }

    public final synchronized void onAdImpression() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAdImpression();
        }
    }

    public final synchronized void onAppEvent(String str, String str2) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onAppEvent(str, str2);
        }
    }

    public final synchronized void zza(zzafa zzafa, String str) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zza(zzafa, str);
        }
    }

    public final synchronized void onVideoEnd() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onVideoEnd();
        }
    }

    public final synchronized void zzdn(String str) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzdn(str);
        }
    }

    public final synchronized void zzuc() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzuc();
        }
    }

    public final synchronized void onVideoPlay() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onVideoPlay();
        }
    }

    public final synchronized void zzb(zzauv zzauv) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzb(zzauv);
        }
    }

    public final synchronized void onVideoPause() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.onVideoPause();
        }
    }

    public final synchronized void zzb(Bundle bundle) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzb(bundle);
        }
    }

    public final synchronized void zzud() throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzud();
        }
    }

    public final synchronized void zzdd(int i) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzdd(i);
        }
    }

    public final synchronized void zzdo(String str) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zzdo(str);
        }
    }

    public final synchronized void zze(zzva zzva) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zze(zzva);
        }
    }

    public final synchronized void zza(zzaux zzaux) throws RemoteException {
        if (this.zzdmr != null) {
            this.zzdmr.zza(zzaux);
        }
    }
}
