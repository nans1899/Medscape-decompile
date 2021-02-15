package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzxs extends IInterface {
    String getVersionString() throws RemoteException;

    void initialize() throws RemoteException;

    void setAppMuted(boolean z) throws RemoteException;

    void setAppVolume(float f) throws RemoteException;

    void zza(zzaae zzaae) throws RemoteException;

    void zza(zzait zzait) throws RemoteException;

    void zza(zzanb zzanb) throws RemoteException;

    void zza(String str, IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zzch(String str) throws RemoteException;

    void zzci(String str) throws RemoteException;

    float zzqg() throws RemoteException;

    boolean zzqh() throws RemoteException;

    List<zzaiq> zzqi() throws RemoteException;

    void zzqj() throws RemoteException;
}
