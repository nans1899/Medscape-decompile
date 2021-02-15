package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzxc extends IInterface {
    void destroy() throws RemoteException;

    Bundle getAdMetadata() throws RemoteException;

    String getAdUnitId() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    zzyo getVideoController() throws RemoteException;

    boolean isLoading() throws RemoteException;

    boolean isReady() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void setManualImpressionsEnabled(boolean z) throws RemoteException;

    void setUserId(String str) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void stopLoading() throws RemoteException;

    void zza(zzaak zzaak) throws RemoteException;

    void zza(zzaby zzaby) throws RemoteException;

    void zza(zzari zzari) throws RemoteException;

    void zza(zzaro zzaro, String str) throws RemoteException;

    void zza(zzaug zzaug) throws RemoteException;

    void zza(zzsh zzsh) throws RemoteException;

    void zza(zzvn zzvn) throws RemoteException;

    void zza(zzvs zzvs) throws RemoteException;

    void zza(zzwo zzwo) throws RemoteException;

    void zza(zzwt zzwt) throws RemoteException;

    void zza(zzxj zzxj) throws RemoteException;

    void zza(zzxk zzxk) throws RemoteException;

    void zza(zzxq zzxq) throws RemoteException;

    void zza(zzyi zzyi) throws RemoteException;

    void zza(zzyu zzyu) throws RemoteException;

    boolean zza(zzvg zzvg) throws RemoteException;

    void zzbp(String str) throws RemoteException;

    IObjectWrapper zzke() throws RemoteException;

    void zzkf() throws RemoteException;

    zzvn zzkg() throws RemoteException;

    String zzkh() throws RemoteException;

    zzyn zzki() throws RemoteException;

    zzxk zzkj() throws RemoteException;

    zzwt zzkk() throws RemoteException;
}
