package com.google.android.play.core.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class t extends j implements u {
    public t() {
        super("com.google.android.play.core.assetpacks.protocol.IAssetModuleServiceCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean a(int i, Parcel parcel) throws RemoteException {
        switch (i) {
            case 2:
                a(parcel.readInt(), (Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 3:
                int readInt = parcel.readInt();
                Bundle bundle = (Bundle) k.a(parcel, Bundle.CREATOR);
                a(readInt);
                return true;
            case 4:
                int readInt2 = parcel.readInt();
                Bundle bundle2 = (Bundle) k.a(parcel, Bundle.CREATOR);
                b(readInt2);
                return true;
            case 5:
                a((List<Bundle>) parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 6:
                Bundle bundle3 = (Bundle) k.a(parcel, Bundle.CREATOR);
                b((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 7:
                a((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 8:
                Bundle bundle4 = (Bundle) k.a(parcel, Bundle.CREATOR);
                c((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 10:
                Bundle bundle5 = (Bundle) k.a(parcel, Bundle.CREATOR);
                d((Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 11:
                a((Bundle) k.a(parcel, Bundle.CREATOR), (Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 12:
                b((Bundle) k.a(parcel, Bundle.CREATOR), (Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 13:
                c((Bundle) k.a(parcel, Bundle.CREATOR), (Bundle) k.a(parcel, Bundle.CREATOR));
                return true;
            case 14:
                Bundle bundle6 = (Bundle) k.a(parcel, Bundle.CREATOR);
                Bundle bundle7 = (Bundle) k.a(parcel, Bundle.CREATOR);
                b();
                return true;
            case 15:
                Bundle bundle8 = (Bundle) k.a(parcel, Bundle.CREATOR);
                a();
                return true;
            default:
                return false;
        }
    }
}
