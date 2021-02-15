package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzais extends IInterface {
    void onInitializationFailed(String str) throws RemoteException;

    void onInitializationSucceeded() throws RemoteException;
}
