package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzajg extends zzgu implements zzaje {
    zzajg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    public final zzyo getVideoController() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        zzyo zzk = zzyr.zzk(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzk;
    }

    public final void destroy() throws RemoteException {
        zza(4, obtainAndWriteInterfaceToken());
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzajf zzajf) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzajf);
        zza(5, obtainAndWriteInterfaceToken);
    }

    public final void zzr(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zza(6, obtainAndWriteInterfaceToken);
    }

    public final zzadz zzsw() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken());
        zzadz zzn = zzaec.zzn(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzn;
    }
}
