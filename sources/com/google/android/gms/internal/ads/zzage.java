package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzage extends IInterface {
    void cancelUnconfirmedClick() throws RemoteException;

    void destroy() throws RemoteException;

    String getAdvertiser() throws RemoteException;

    String getBody() throws RemoteException;

    String getCallToAction() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    String getHeadline() throws RemoteException;

    List getImages() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    List getMuteThisAdReasons() throws RemoteException;

    String getPrice() throws RemoteException;

    double getStarRating() throws RemoteException;

    String getStore() throws RemoteException;

    zzyo getVideoController() throws RemoteException;

    boolean isCustomClickGestureEnabled() throws RemoteException;

    boolean isCustomMuteThisAdEnabled() throws RemoteException;

    void performClick(Bundle bundle) throws RemoteException;

    void recordCustomClickGesture() throws RemoteException;

    boolean recordImpression(Bundle bundle) throws RemoteException;

    void reportTouchEvent(Bundle bundle) throws RemoteException;

    void zza(zzafz zzafz) throws RemoteException;

    void zza(zzxz zzxz) throws RemoteException;

    void zza(zzyd zzyd) throws RemoteException;

    void zza(zzyi zzyi) throws RemoteException;

    zzyn zzki() throws RemoteException;

    IObjectWrapper zzsk() throws RemoteException;

    zzaee zzsl() throws RemoteException;

    zzadw zzsm() throws RemoteException;

    IObjectWrapper zzsn() throws RemoteException;

    void zzsv() throws RemoteException;

    zzadz zzsw() throws RemoteException;
}
