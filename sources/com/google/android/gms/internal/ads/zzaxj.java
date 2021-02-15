package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzaxj extends zzgt implements zzaxg {
    public zzaxj() {
        super("com.google.android.gms.ads.internal.signals.ISignalCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzk(parcel.readString(), parcel.readString());
        } else if (i == 2) {
            onError(parcel.readString());
        } else if (i != 3) {
            return false;
        } else {
            zza(parcel.readString(), parcel.readString(), (Bundle) zzgw.zza(parcel, Bundle.CREATOR));
        }
        parcel2.writeNoException();
        return true;
    }
}
