package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzadv extends zzgt implements zzadw {
    public zzadv() {
        super("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
    }

    public static zzadw zzm(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.IAttributionInfo");
        if (queryLocalInterface instanceof zzadw) {
            return (zzadw) queryLocalInterface;
        }
        return new zzady(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 2) {
            String text = getText();
            parcel2.writeNoException();
            parcel2.writeString(text);
            return true;
        } else if (i != 3) {
            return false;
        } else {
            List<zzaee> zzsb = zzsb();
            parcel2.writeNoException();
            parcel2.writeList(zzsb);
            return true;
        }
    }
}
