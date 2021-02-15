package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public final class bn extends i implements bp {
    bn(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
    }

    public final void a(String str, int i, Bundle bundle, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeInt(i);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) brVar);
        a(4, a);
    }

    public final void a(String str, int i, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeInt(i);
        k.a(a, (IInterface) brVar);
        a(5, a);
    }

    public final void a(String str, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (IInterface) brVar);
        a(6, a);
    }

    public final void a(String str, List<Bundle> list, Bundle bundle, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) brVar);
        a(2, a);
    }

    public final void b(String str, List<Bundle> list, Bundle bundle, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) brVar);
        a(7, a);
    }

    public final void c(String str, List<Bundle> list, Bundle bundle, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) brVar);
        a(8, a);
    }

    public final void d(String str, List<Bundle> list, Bundle bundle, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) brVar);
        a(13, a);
    }

    public final void e(String str, List<Bundle> list, Bundle bundle, br brVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) brVar);
        a(14, a);
    }
}
