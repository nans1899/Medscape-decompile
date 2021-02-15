package com.google.android.gms.internal.icing;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.firebase.appindexing.internal.zza;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzad extends zzb implements zzaa {
    zzad(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch");
    }

    public final void zza(zzac zzac, String str, zzw[] zzwArr) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzac);
        zza.writeString((String) null);
        zza.writeTypedArray(zzwArr, 0);
        zzb(1, zza);
    }

    public final void zza(zzac zzac, zza[] zzaArr) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) zzac);
        zza.writeTypedArray(zzaArr, 0);
        zzb(7, zza);
    }
}
