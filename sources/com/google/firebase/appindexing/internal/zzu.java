package com.google.firebase.appindexing.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.internal.icing.zzb;
import com.google.android.gms.internal.icing.zzd;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzu extends zzb implements zzr {
    zzu(IBinder iBinder) {
        super(iBinder, "com.google.firebase.appindexing.internal.IAppIndexingService");
    }

    public final zzg zza(IStatusCallback iStatusCallback, zzy zzy) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) iStatusCallback);
        zzd.zza(zza, (Parcelable) zzy);
        Parcel zza2 = zza(8, zza);
        zzg zzg = (zzg) zzd.zza(zza2, zzg.CREATOR);
        zza2.recycle();
        return zzg;
    }
}
