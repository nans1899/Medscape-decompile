package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzxv extends zzgt implements zzxs {
    public zzxv() {
        super("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                initialize();
                parcel2.writeNoException();
                return true;
            case 2:
                setAppVolume(parcel.readFloat());
                parcel2.writeNoException();
                return true;
            case 3:
                zzch(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 4:
                setAppMuted(zzgw.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 5:
                zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zza(parcel.readString(), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 7:
                float zzqg = zzqg();
                parcel2.writeNoException();
                parcel2.writeFloat(zzqg);
                return true;
            case 8:
                boolean zzqh = zzqh();
                parcel2.writeNoException();
                zzgw.writeBoolean(parcel2, zzqh);
                return true;
            case 9:
                String versionString = getVersionString();
                parcel2.writeNoException();
                parcel2.writeString(versionString);
                return true;
            case 10:
                zzci(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 11:
                zza(zzane.zzac(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 12:
                zza(zzaiw.zzaa(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 13:
                List<zzaiq> zzqi = zzqi();
                parcel2.writeNoException();
                parcel2.writeTypedList(zzqi);
                return true;
            case 14:
                zza((zzaae) zzgw.zza(parcel, zzaae.CREATOR));
                parcel2.writeNoException();
                return true;
            case 15:
                zzqj();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
