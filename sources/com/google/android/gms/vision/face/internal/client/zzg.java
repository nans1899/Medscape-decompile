package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.vision.zza;
import com.google.android.gms.internal.vision.zzd;
import com.google.android.gms.internal.vision.zzu;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public abstract class zzg extends zza implements zzh {
    public zzg() {
        super("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            FaceParcel[] zzc = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzu) zzd.zza(parcel, zzu.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedArray(zzc, 1);
        } else if (i == 2) {
            boolean zzd = zzd(parcel.readInt());
            parcel2.writeNoException();
            zzd.writeBoolean(parcel2, zzd);
        } else if (i == 3) {
            zzo();
            parcel2.writeNoException();
        } else if (i != 4) {
            return false;
        } else {
            FaceParcel[] zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), (zzu) zzd.zza(parcel, zzu.CREATOR));
            parcel2.writeNoException();
            parcel2.writeTypedArray(zza, 1);
        }
        return true;
    }
}
