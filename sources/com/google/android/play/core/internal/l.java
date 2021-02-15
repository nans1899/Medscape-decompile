package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class l extends i implements n {
    l(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.appupdate.protocol.IAppUpdateService");
    }

    public final void a(String str, Bundle bundle, p pVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) pVar);
        a(2, a);
    }

    public final void b(String str, Bundle bundle, p pVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) pVar);
        a(3, a);
    }
}
