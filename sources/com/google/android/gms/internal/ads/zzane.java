package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzane extends zzgt implements zzanb {
    public zzane() {
        super("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
    }

    public static zzanb zzac(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        if (queryLocalInterface instanceof zzanb) {
            return (zzanb) queryLocalInterface;
        }
        return new zzand(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzang zzdi = zzdi(parcel.readString());
            parcel2.writeNoException();
            zzgw.zza(parcel2, (IInterface) zzdi);
        } else if (i == 2) {
            boolean zzdj = zzdj(parcel.readString());
            parcel2.writeNoException();
            zzgw.writeBoolean(parcel2, zzdj);
        } else if (i != 3) {
            return false;
        } else {
            zzaph zzdk = zzdk(parcel.readString());
            parcel2.writeNoException();
            zzgw.zza(parcel2, (IInterface) zzdk);
        }
        return true;
    }
}
