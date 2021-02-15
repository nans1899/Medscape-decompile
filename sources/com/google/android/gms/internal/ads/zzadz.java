package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzadz extends IInterface {
    float getAspectRatio() throws RemoteException;

    float getCurrentTime() throws RemoteException;

    float getDuration() throws RemoteException;

    zzyo getVideoController() throws RemoteException;

    boolean hasVideoContent() throws RemoteException;

    void zza(zzafq zzafq) throws RemoteException;

    void zzo(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzsj() throws RemoteException;
}
