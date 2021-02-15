package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzaxl extends IInterface {
    void zza(IObjectWrapper iObjectWrapper, zzaxr zzaxr, zzaxg zzaxg) throws RemoteException;

    void zza(zzasa zzasa) throws RemoteException;

    void zza(List<Uri> list, IObjectWrapper iObjectWrapper, zzart zzart) throws RemoteException;

    void zzan(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzao(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException;

    void zzb(List<Uri> list, IObjectWrapper iObjectWrapper, zzart zzart) throws RemoteException;
}
