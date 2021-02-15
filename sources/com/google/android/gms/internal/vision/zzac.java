package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class zzac extends zzb implements zzad {
    zzac(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
    }

    public final zzah[] zza(IObjectWrapper iObjectWrapper, zzu zzu, zzaj zzaj) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) iObjectWrapper);
        zzd.zza(zza, (Parcelable) zzu);
        zzd.zza(zza, (Parcelable) zzaj);
        Parcel zza2 = zza(3, zza);
        zzah[] zzahArr = (zzah[]) zza2.createTypedArray(zzah.CREATOR);
        zza2.recycle();
        return zzahArr;
    }

    public final void zzs() throws RemoteException {
        zzb(2, zza());
    }
}
