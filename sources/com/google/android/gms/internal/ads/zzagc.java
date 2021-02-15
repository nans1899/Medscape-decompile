package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzagc extends zzgt implements zzafz {
    public zzagc() {
        super("com.google.android.gms.ads.internal.formats.client.IUnconfirmedClickListener");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            onUnconfirmedClickReceived(parcel.readString());
        } else if (i != 2) {
            return false;
        } else {
            onUnconfirmedClickCancelled();
        }
        parcel2.writeNoException();
        return true;
    }
}
