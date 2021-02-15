package com.google.android.gms.internal.ads;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzaqs extends zzgt implements zzaqp {
    public zzaqs() {
        super("com.google.android.gms.ads.internal.offline.IOfflineUtils");
    }

    public static zzaqp zzag(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.offline.IOfflineUtils");
        if (queryLocalInterface instanceof zzaqp) {
            return (zzaqp) queryLocalInterface;
        }
        return new zzaqr(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc((Intent) zzgw.zza(parcel, Intent.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
