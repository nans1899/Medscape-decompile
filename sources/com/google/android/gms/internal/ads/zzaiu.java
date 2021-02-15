package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzaiu extends zzgu implements zzais {
    zzaiu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.initialization.IAdapterInitializationCallback");
    }

    public final void onInitializationSucceeded() throws RemoteException {
        zza(2, obtainAndWriteInterfaceToken());
    }

    public final void onInitializationFailed(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zza(3, obtainAndWriteInterfaceToken);
    }
}
