package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzyn extends IInterface {
    List<zzvr> getAdapterResponses() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    String getResponseId() throws RemoteException;
}
