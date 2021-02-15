package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzyl extends zzgt implements zzyi {
    public zzyl() {
        super("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
    }

    public static zzyi zzi(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
        if (queryLocalInterface instanceof zzyi) {
            return (zzyi) queryLocalInterface;
        }
        return new zzyk(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((zzvp) zzgw.zza(parcel, zzvp.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
