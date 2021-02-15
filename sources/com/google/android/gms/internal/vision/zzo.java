package com.google.android.gms.internal.vision;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.vision.barcode.Barcode;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class zzo extends zzb implements zzl {
    zzo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
    }

    public final Barcode[] zza(IObjectWrapper iObjectWrapper, zzu zzu) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) iObjectWrapper);
        zzd.zza(zza, (Parcelable) zzu);
        Parcel zza2 = zza(1, zza);
        Barcode[] barcodeArr = (Barcode[]) zza2.createTypedArray(Barcode.CREATOR);
        zza2.recycle();
        return barcodeArr;
    }

    public final Barcode[] zzb(IObjectWrapper iObjectWrapper, zzu zzu) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, (IInterface) iObjectWrapper);
        zzd.zza(zza, (Parcelable) zzu);
        Parcel zza2 = zza(2, zza);
        Barcode[] barcodeArr = (Barcode[]) zza2.createTypedArray(Barcode.CREATOR);
        zza2.recycle();
        return barcodeArr;
    }

    public final void zzo() throws RemoteException {
        zzb(3, zza());
    }
}
