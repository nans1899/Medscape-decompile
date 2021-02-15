package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class bq extends j implements br {
    public bq() {
        super("com.google.android.play.core.splitinstall.protocol.ISplitInstallServiceCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel) throws RemoteException {
        switch (i) {
            case 2:
                c(parcel.readInt(), (Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                int readInt = parcel.readInt();
                Bundle bundle = (Bundle) k.a(parcel, Bundle.CREATOR);
                a(readInt);
                return true;
            case 4:
                a(parcel.readInt(), (Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 5:
                b(parcel.readInt(), (Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 6:
                e((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 7:
                a((List<Bundle>) parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 8:
                d((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 9:
                a((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 10:
                Bundle bundle2 = (Bundle) k.a(parcel, Bundle.CREATOR);
                b();
                return true;
            case 11:
                Bundle bundle3 = (Bundle) k.a(parcel, Bundle.CREATOR);
                a();
                return true;
            case 12:
                b((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                c((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
