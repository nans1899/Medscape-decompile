package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzxb extends zzgu implements zzwz {
    zzxb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [android.os.IInterface] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzwu zzqf() throws android.os.RemoteException {
        /*
            r4 = this;
            android.os.Parcel r0 = r4.obtainAndWriteInterfaceToken()
            r1 = 1
            android.os.Parcel r0 = r4.transactAndReadException(r1, r0)
            android.os.IBinder r1 = r0.readStrongBinder()
            if (r1 != 0) goto L_0x0011
            r1 = 0
            goto L_0x0025
        L_0x0011:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdLoader"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.ads.zzwu
            if (r3 == 0) goto L_0x001f
            r1 = r2
            com.google.android.gms.internal.ads.zzwu r1 = (com.google.android.gms.internal.ads.zzwu) r1
            goto L_0x0025
        L_0x001f:
            com.google.android.gms.internal.ads.zzww r2 = new com.google.android.gms.internal.ads.zzww
            r2.<init>(r1)
            r1 = r2
        L_0x0025:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxb.zzqf():com.google.android.gms.internal.ads.zzwu");
    }

    public final void zzb(zzwt zzwt) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzwt);
        zza(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzafe zzafe) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzafe);
        zza(3, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaff zzaff) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzaff);
        zza(4, obtainAndWriteInterfaceToken);
    }

    public final void zza(String str, zzafl zzafl, zzafk zzafk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzafl);
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzafk);
        zza(5, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzadu zzadu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzadu);
        zza(6, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzxq zzxq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzxq);
        zza(7, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzafs zzafs, zzvn zzvn) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzafs);
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzvn);
        zza(8, obtainAndWriteInterfaceToken);
    }

    public final void zza(PublisherAdViewOptions publisherAdViewOptions) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) publisherAdViewOptions);
        zza(9, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzaft zzaft) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzaft);
        zza(10, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzajc zzajc) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (Parcelable) zzajc);
        zza(13, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzajk zzajk) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzgw.zza(obtainAndWriteInterfaceToken, (IInterface) zzajk);
        zza(14, obtainAndWriteInterfaceToken);
    }
}
