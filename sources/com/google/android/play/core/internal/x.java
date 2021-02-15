package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class x extends i implements y {
    x(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    public final void a(Bundle bundle) throws RemoteException {
        Parcel a = a();
        k.a(a, (Parcelable) bundle);
        a(3, a);
    }

    public final void a(Bundle bundle, Bundle bundle2) throws RemoteException {
        Parcel a = a();
        k.a(a, (Parcelable) bundle);
        k.a(a, (Parcelable) bundle2);
        a(2, a);
    }

    public final void b(Bundle bundle) throws RemoteException {
        Parcel a = a();
        k.a(a, (Parcelable) bundle);
        a(4, a);
    }
}
