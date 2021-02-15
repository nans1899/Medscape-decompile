package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaob extends zzank {
    private final zzaur zzdlh;
    private final Adapter zzdlp;

    zzaob(Adapter adapter, zzaur zzaur) {
        this.zzdlp = adapter;
        this.zzdlh = zzaur;
    }

    public final void onAdImpression() throws RemoteException {
    }

    public final void onAdLeftApplication() throws RemoteException {
    }

    public final void onAppEvent(String str, String str2) throws RemoteException {
    }

    public final void onVideoEnd() throws RemoteException {
    }

    public final void onVideoPause() throws RemoteException {
    }

    public final void onVideoPlay() throws RemoteException {
    }

    public final void zza(zzafa zzafa, String str) throws RemoteException {
    }

    public final void zza(zzanm zzanm) throws RemoteException {
    }

    public final void zzb(Bundle bundle) throws RemoteException {
    }

    public final void zzb(zzauv zzauv) throws RemoteException {
    }

    public final void zzc(int i, String str) throws RemoteException {
    }

    public final void zzc(zzva zzva) throws RemoteException {
    }

    public final void zzdd(int i) throws RemoteException {
    }

    public final void zzdn(String str) throws RemoteException {
    }

    public final void zzdo(String str) {
    }

    public final void zze(zzva zzva) {
    }

    public final void onAdLoaded() throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zzag(ObjectWrapper.wrap(this.zzdlp));
        }
    }

    public final void onAdOpened() throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zzah(ObjectWrapper.wrap(this.zzdlp));
        }
    }

    public final void onAdClosed() throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zzaj(ObjectWrapper.wrap(this.zzdlp));
        }
    }

    public final void zza(zzaux zzaux) throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zza(ObjectWrapper.wrap(this.zzdlp), new zzauv(zzaux.getType(), zzaux.getAmount()));
        }
    }

    public final void zzuc() throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zzai(ObjectWrapper.wrap(this.zzdlp));
        }
    }

    public final void zzud() throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zzam(ObjectWrapper.wrap(this.zzdlp));
        }
    }

    public final void onAdClicked() throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zzak(ObjectWrapper.wrap(this.zzdlp));
        }
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        zzaur zzaur = this.zzdlh;
        if (zzaur != null) {
            zzaur.zze(ObjectWrapper.wrap(this.zzdlp), i);
        }
    }
}
