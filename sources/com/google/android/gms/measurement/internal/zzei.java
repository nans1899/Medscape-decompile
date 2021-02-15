package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzc;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public abstract class zzei extends zzc implements zzej {
    public zzei() {
        super("com.google.android.gms.measurement.internal.IMeasurementService");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzar) zzb.zza(parcel, zzar.CREATOR), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 2:
                zza((zzkr) zzb.zza(parcel, zzkr.CREATOR), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 4:
                zza((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 5:
                zza((zzar) zzb.zza(parcel, zzar.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zzb((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 7:
                List<zzkr> zza = zza((zzn) zzb.zza(parcel, zzn.CREATOR), zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                return true;
            case 9:
                byte[] zza2 = zza((zzar) zzb.zza(parcel, zzar.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                return true;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                String zzc = zzc((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                return true;
            case 12:
                zza((zzw) zzb.zza(parcel, zzw.CREATOR), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 13:
                zza((zzw) zzb.zza(parcel, zzw.CREATOR));
                parcel2.writeNoException();
                return true;
            case 14:
                List<zzkr> zza3 = zza(parcel.readString(), parcel.readString(), zzb.zza(parcel), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                return true;
            case 15:
                List<zzkr> zza4 = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzb.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                return true;
            case 16:
                List<zzw> zza5 = zza(parcel.readString(), parcel.readString(), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                return true;
            case 17:
                List<zzw> zza6 = zza(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zza6);
                return true;
            case 18:
                zzd((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 19:
                zza((Bundle) zzb.zza(parcel, Bundle.CREATOR), (zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            case 20:
                zze((zzn) zzb.zza(parcel, zzn.CREATOR));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
