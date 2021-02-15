package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzaiw extends zzgt implements zzait {
    public zzaiw() {
        super("com.google.android.gms.ads.internal.initialization.IInitializationCallback");
    }

    public static zzait zzaa(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.initialization.IInitializationCallback");
        if (queryLocalInterface instanceof zzait) {
            return (zzait) queryLocalInterface;
        }
        return new zzaiv(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zze(parcel.createTypedArrayList(zzaiq.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
