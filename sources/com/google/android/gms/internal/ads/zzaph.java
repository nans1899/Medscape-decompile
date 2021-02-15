package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzaph extends IInterface {
    zzyo getVideoController() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzvn zzvn, zzapm zzapm) throws RemoteException;

    void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzaov zzaov, zzanh zzanh, zzvn zzvn) throws RemoteException;

    void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapa zzapa, zzanh zzanh) throws RemoteException;

    void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapb zzapb, zzanh zzanh) throws RemoteException;

    void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapg zzapg, zzanh zzanh) throws RemoteException;

    void zza(String[] strArr, Bundle[] bundleArr) throws RemoteException;

    boolean zzaa(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzb(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapg zzapg, zzanh zzanh) throws RemoteException;

    void zzdr(String str) throws RemoteException;

    zzapv zzua() throws RemoteException;

    zzapv zzub() throws RemoteException;

    void zzy(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzz(IObjectWrapper iObjectWrapper) throws RemoteException;
}
