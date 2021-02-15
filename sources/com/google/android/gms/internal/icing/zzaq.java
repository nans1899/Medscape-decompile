package com.google.android.gms.internal.icing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzaq extends zzb implements zzan {
    zzaq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.search.internal.ISearchAuthService");
    }

    public final void zza(zzam zzam, String str, String str2) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzam);
        zza.writeString(str);
        zza.writeString(str2);
        zzb(1, zza);
    }

    public final void zzb(zzam zzam, String str, String str2) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzam);
        zza.writeString(str);
        zza.writeString(str2);
        zzb(2, zza);
    }
}
