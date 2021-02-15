package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzafa extends IInterface {
    void destroy() throws RemoteException;

    List<String> getAvailableAssetNames() throws RemoteException;

    String getCustomTemplateId() throws RemoteException;

    zzyo getVideoController() throws RemoteException;

    void performClick(String str) throws RemoteException;

    void recordImpression() throws RemoteException;

    String zzcx(String str) throws RemoteException;

    zzaee zzcy(String str) throws RemoteException;

    boolean zzp(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzq(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzsk() throws RemoteException;

    IObjectWrapper zzsp() throws RemoteException;

    boolean zzsq() throws RemoteException;

    boolean zzsr() throws RemoteException;

    void zzss() throws RemoteException;
}
