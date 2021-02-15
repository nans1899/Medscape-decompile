package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzyc extends zzgt implements zzyd {
    public zzyc() {
        super("com.google.android.gms.ads.internal.client.IMuteThisAdReason");
    }

    public static zzyd zzg(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMuteThisAdReason");
        if (queryLocalInterface instanceof zzyd) {
            return (zzyd) queryLocalInterface;
        }
        return new zzyf(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            String description = getDescription();
            parcel2.writeNoException();
            parcel2.writeString(description);
        } else if (i != 2) {
            return false;
        } else {
            String zzqk = zzqk();
            parcel2.writeNoException();
            parcel2.writeString(zzqk);
        }
        return true;
    }
}
