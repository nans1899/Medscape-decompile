package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzwu extends IInterface {
    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoading() throws RemoteException;

    void zza(zzvg zzvg, int i) throws RemoteException;

    void zzb(zzvg zzvg) throws RemoteException;

    String zzkh() throws RemoteException;
}
