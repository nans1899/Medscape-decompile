package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public final class q extends i implements s {
    q(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetModuleService");
    }

    public final void a(String str, Bundle bundle, Bundle bundle2, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (Parcelable) bundle2);
        k.a(a, (IInterface) uVar);
        a(6, a);
    }

    public final void a(String str, Bundle bundle, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) uVar);
        a(5, a);
    }

    public final void a(String str, List<Bundle> list, Bundle bundle, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) uVar);
        a(2, a);
    }

    public final void b(String str, Bundle bundle, Bundle bundle2, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (Parcelable) bundle2);
        k.a(a, (IInterface) uVar);
        a(7, a);
    }

    public final void b(String str, Bundle bundle, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) uVar);
        a(10, a);
    }

    public final void b(String str, List<Bundle> list, Bundle bundle, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) uVar);
        a(14, a);
    }

    public final void c(String str, Bundle bundle, Bundle bundle2, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (Parcelable) bundle2);
        k.a(a, (IInterface) uVar);
        a(9, a);
    }

    public final void c(String str, List<Bundle> list, Bundle bundle, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        a.writeTypedList(list);
        k.a(a, (Parcelable) bundle);
        k.a(a, (IInterface) uVar);
        a(12, a);
    }

    public final void d(String str, Bundle bundle, Bundle bundle2, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (Parcelable) bundle2);
        k.a(a, (IInterface) uVar);
        a(11, a);
    }

    public final void e(String str, Bundle bundle, Bundle bundle2, u uVar) throws RemoteException {
        Parcel a = a();
        a.writeString(str);
        k.a(a, (Parcelable) bundle);
        k.a(a, (Parcelable) bundle2);
        k.a(a, (IInterface) uVar);
        a(13, a);
    }
}
