package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzapj extends zzgu implements zzaph {
    zzapj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, Bundle bundle2, zzvn zzvn, zzapm zzapm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) bundle2);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvn);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzapm);
        zza(1, obtainAndWriteInterfaceToken);
    }

    public final zzapv zzua() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        zzapv zzapv = (zzapv) zzgw.zza(transactAndReadException, zzapv.CREATOR);
        transactAndReadException.recycle();
        return zzapv;
    }

    public final zzapv zzub() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        zzapv zzapv = (zzapv) zzgw.zza(transactAndReadException, zzapv.CREATOR);
        transactAndReadException.recycle();
        return zzapv;
    }

    public final zzyo getVideoController() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        zzyo zzk = zzyr.zzk(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzk;
    }

    public final void zzy(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zza(10, obtainAndWriteInterfaceToken);
    }

    public final void zza(String[] strArr, Bundle[] bundleArr) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeStringArray(strArr);
        obtainAndWriteInterfaceToken.writeTypedArray(bundleArr, 0);
        zza(11, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzaov zzaov, zzanh zzanh, zzvn zzvn) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvg);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzaov);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzanh);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvn);
        zza(13, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapa zzapa, zzanh zzanh) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvg);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzapa);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzanh);
        zza(14, obtainAndWriteInterfaceToken);
    }

    public final boolean zzz(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(15, obtainAndWriteInterfaceToken);
        boolean zza = zzgw.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapg zzapg, zzanh zzanh) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvg);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzapg);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzanh);
        zza(16, obtainAndWriteInterfaceToken);
    }

    public final boolean zzaa(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(17, obtainAndWriteInterfaceToken);
        boolean zza = zzgw.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }

    public final void zza(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapb zzapb, zzanh zzanh) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvg);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzapb);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzanh);
        zza(18, obtainAndWriteInterfaceToken);
    }

    public final void zzdr(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zza(19, obtainAndWriteInterfaceToken);
    }

    public final void zzb(String str, String str2, zzvg zzvg, IObjectWrapper iObjectWrapper, zzapg zzapg, zzanh zzanh) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        obtainAndWriteInterfaceToken.writeString(str2);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvg);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzapg);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzanh);
        zza(20, obtainAndWriteInterfaceToken);
    }
}
