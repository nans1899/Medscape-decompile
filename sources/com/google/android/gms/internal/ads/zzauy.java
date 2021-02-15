package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzauy extends IInterface {
    Bundle getAdMetadata() throws RemoteException;

    String getMediationAdapterClassName() throws RemoteException;

    boolean isLoaded() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException;

    void zza(zzavd zzavd) throws RemoteException;

    void zza(zzavl zzavl) throws RemoteException;

    void zza(zzavt zzavt) throws RemoteException;

    void zza(zzvg zzvg, zzavg zzavg) throws RemoteException;

    void zza(zzyh zzyh) throws RemoteException;

    void zza(zzyi zzyi) throws RemoteException;

    void zzb(zzvg zzvg, zzavg zzavg) throws RemoteException;

    void zzh(IObjectWrapper iObjectWrapper) throws RemoteException;

    zzyn zzki() throws RemoteException;

    zzaux zzqz() throws RemoteException;
}
