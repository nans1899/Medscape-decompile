package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzasv extends zzgu implements zzast {
    zzasv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }

    public final zzasp zza(zzasn zzasn) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzasn);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        zzasp zzasp = (zzasp) zzgw.zza(transactAndReadException, zzasp.CREATOR);
        transactAndReadException.recycle();
        return zzasp;
    }

    public final void zza(zzasn zzasn, zzasu zzasu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzasn);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzasu);
        zza(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzatc zzatc, zzasw zzasw) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzatc);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzasw);
        zza(4, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzatc zzatc, zzasw zzasw) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzatc);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzasw);
        zza(5, obtainAndWriteInterfaceToken);
    }

    public final void zzc(zzatc zzatc, zzasw zzasw) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzatc);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzasw);
        zza(6, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, zzasw zzasw) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzasw);
        zza(7, obtainAndWriteInterfaceToken);
    }
}
