package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzym extends zzgt implements zzyn {
    public zzym() {
        super("com.google.android.gms.ads.internal.client.IResponseInfo");
    }

    public static zzyn zzj(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IResponseInfo");
        if (queryLocalInterface instanceof zzyn) {
            return (zzyn) queryLocalInterface;
        }
        return new zzyp(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            String mediationAdapterClassName = getMediationAdapterClassName();
            parcel2.writeNoException();
            parcel2.writeString(mediationAdapterClassName);
        } else if (i == 2) {
            String responseId = getResponseId();
            parcel2.writeNoException();
            parcel2.writeString(responseId);
        } else if (i != 3) {
            return false;
        } else {
            List<zzvr> adapterResponses = getAdapterResponses();
            parcel2.writeNoException();
            parcel2.writeTypedList(adapterResponses);
        }
        return true;
    }
}
