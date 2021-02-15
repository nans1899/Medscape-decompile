package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzaez extends zzgt implements zzafa {
    public zzaez() {
        super("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzafa zzr(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        if (queryLocalInterface instanceof zzafa) {
            return (zzafa) queryLocalInterface;
        }
        return new zzafc(iBinder);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String zzcx = zzcx(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(zzcx);
                return true;
            case 2:
                zzaee zzcy = zzcy(parcel.readString());
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzcy);
                return true;
            case 3:
                List<String> availableAssetNames = getAvailableAssetNames();
                parcel2.writeNoException();
                parcel2.writeStringList(availableAssetNames);
                return true;
            case 4:
                String customTemplateId = getCustomTemplateId();
                parcel2.writeNoException();
                parcel2.writeString(customTemplateId);
                return true;
            case 5:
                performClick(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                recordImpression();
                parcel2.writeNoException();
                return true;
            case 7:
                zzyo videoController = getVideoController();
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) videoController);
                return true;
            case 8:
                destroy();
                parcel2.writeNoException();
                return true;
            case 9:
                IObjectWrapper zzsp = zzsp();
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzsp);
                return true;
            case 10:
                boolean zzp = zzp(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzgw.writeBoolean(parcel2, zzp);
                return true;
            case 11:
                IObjectWrapper zzsk = zzsk();
                parcel2.writeNoException();
                zzgw.zza(parcel2, (IInterface) zzsk);
                return true;
            case 12:
                boolean zzsq = zzsq();
                parcel2.writeNoException();
                zzgw.writeBoolean(parcel2, zzsq);
                return true;
            case 13:
                boolean zzsr = zzsr();
                parcel2.writeNoException();
                zzgw.writeBoolean(parcel2, zzsr);
                return true;
            case 14:
                zzq(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 15:
                zzss();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
