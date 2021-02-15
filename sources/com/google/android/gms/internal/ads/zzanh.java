package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzanh extends IInterface {
    void onAdClicked() throws RemoteException;

    void onAdClosed() throws RemoteException;

    void onAdFailedToLoad(int i) throws RemoteException;

    void onAdImpression() throws RemoteException;

    void onAdLeftApplication() throws RemoteException;

    void onAdLoaded() throws RemoteException;

    void onAdOpened() throws RemoteException;

    void onAppEvent(String str, String str2) throws RemoteException;

    void onVideoEnd() throws RemoteException;

    void onVideoPause() throws RemoteException;

    void onVideoPlay() throws RemoteException;

    void zza(zzafa zzafa, String str) throws RemoteException;

    void zza(zzanm zzanm) throws RemoteException;

    void zza(zzaux zzaux) throws RemoteException;

    void zzb(Bundle bundle) throws RemoteException;

    void zzb(zzauv zzauv) throws RemoteException;

    void zzc(int i, String str) throws RemoteException;

    void zzc(zzva zzva) throws RemoteException;

    void zzdd(int i) throws RemoteException;

    void zzdn(String str) throws RemoteException;

    void zzdo(String str) throws RemoteException;

    void zze(zzva zzva) throws RemoteException;

    void zzuc() throws RemoteException;

    void zzud() throws RemoteException;
}
