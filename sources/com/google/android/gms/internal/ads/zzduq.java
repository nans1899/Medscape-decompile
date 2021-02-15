package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzduq extends zzgu implements zzdun {
    zzduq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.gass.internal.IGassService");
    }

    public final zzdul zza(zzduj zzduj) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzduj);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzdul zzdul = (zzdul) zzgw.zza(transactAndReadException, zzdul.CREATOR);
        transactAndReadException.recycle();
        return zzdul;
    }

    public final void zza(zzdui zzdui) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzdui);
        zza(2, obtainAndWriteInterfaceToken);
    }

    public final zzduv zza(zzdut zzdut) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzdut);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        zzduv zzduv = (zzduv) zzgw.zza(transactAndReadException, zzduv.CREATOR);
        transactAndReadException.recycle();
        return zzduv;
    }
}
