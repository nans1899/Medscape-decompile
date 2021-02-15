package com.google.android.gms.internal.ads;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzxo extends zzgt implements zzxp {
    public zzxo() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzxc zza = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzvn) zzgw.zza(parcel, zzvn.CREATOR), parcel.readString(), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zza);
                return true;
            case 2:
                zzxc zzb = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzvn) zzgw.zza(parcel, zzvn.CREATOR), parcel.readString(), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzb);
                return true;
            case 3:
                zzwz zza2 = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zza2);
                return true;
            case 4:
                zzxs zzc = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzc);
                return true;
            case 5:
                zzaei zza3 = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zza3);
                return true;
            case 6:
                zzaud zza4 = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zza4);
                return true;
            case 7:
                zzarn zzd = zzd(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzd);
                return true;
            case 8:
                zzaqy zzb2 = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzb2);
                return true;
            case 9:
                zzxs zza5 = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zza5);
                return true;
            case 10:
                zzxc zza6 = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzvn) zzgw.zza(parcel, zzvn.CREATOR), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zza6);
                return true;
            case 11:
                zzael zza7 = zza(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zza7);
                return true;
            case 12:
                zzauy zzb3 = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzb3);
                return true;
            case 13:
                zzxc zzc2 = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), (zzvn) zzgw.zza(parcel, zzvn.CREATOR), parcel.readString(), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzc2);
                return true;
            case 14:
                zzaxl zzb4 = zzb(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzb4);
                return true;
            case 15:
                zzaqp zzc3 = zzc(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()), zzane.zzac(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzc3);
                return true;
            default:
                return false;
        }
    }
}
