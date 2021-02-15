package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzauc extends zzgt implements zzaud {
    public zzauc() {
        super("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }

    public static zzaud zzan(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
        if (queryLocalInterface instanceof zzaud) {
            return (zzaud) queryLocalInterface;
        }
        return new zzauf(iBinder);
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.gms.internal.ads.zzaug] */
    /* JADX WARNING: type inference failed for: r1v5, types: [com.google.android.gms.internal.ads.zzaub] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
        /*
            r2 = this;
            r6 = 1
            if (r3 == r6) goto L_0x012b
            r0 = 2
            if (r3 == r0) goto L_0x0124
            r0 = 3
            r1 = 0
            if (r3 == r0) goto L_0x0103
            r0 = 34
            if (r3 == r0) goto L_0x00f8
            switch(r3) {
                case 5: goto L_0x00ed;
                case 6: goto L_0x00e6;
                case 7: goto L_0x00df;
                case 8: goto L_0x00d8;
                case 9: goto L_0x00c9;
                case 10: goto L_0x00b9;
                case 11: goto L_0x00a9;
                case 12: goto L_0x009d;
                case 13: goto L_0x0091;
                case 14: goto L_0x0081;
                case 15: goto L_0x0075;
                case 16: goto L_0x0053;
                case 17: goto L_0x0047;
                case 18: goto L_0x0037;
                case 19: goto L_0x002b;
                case 20: goto L_0x001f;
                case 21: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            r3 = 0
            return r3
        L_0x0013:
            com.google.android.gms.internal.ads.zzyn r3 = r2.zzki()
            r5.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r5, (android.os.IInterface) r3)
            goto L_0x0139
        L_0x001f:
            boolean r3 = r2.zzra()
            r5.writeNoException()
            com.google.android.gms.internal.ads.zzgw.writeBoolean(r5, r3)
            goto L_0x0139
        L_0x002b:
            java.lang.String r3 = r4.readString()
            r2.setCustomData(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x0037:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            r2.zzi(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x0047:
            java.lang.String r3 = r4.readString()
            r2.setAppPackageName(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x0053:
            android.os.IBinder r3 = r4.readStrongBinder()
            if (r3 != 0) goto L_0x005a
            goto L_0x006d
        L_0x005a:
            java.lang.String r4 = "com.google.android.gms.ads.internal.reward.client.IRewardedAdSkuListener"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r0 = r4 instanceof com.google.android.gms.internal.ads.zzaub
            if (r0 == 0) goto L_0x0068
            r1 = r4
            com.google.android.gms.internal.ads.zzaub r1 = (com.google.android.gms.internal.ads.zzaub) r1
            goto L_0x006d
        L_0x0068:
            com.google.android.gms.internal.ads.zzaua r1 = new com.google.android.gms.internal.ads.zzaua
            r1.<init>(r3)
        L_0x006d:
            r2.zza((com.google.android.gms.internal.ads.zzaub) r1)
            r5.writeNoException()
            goto L_0x0139
        L_0x0075:
            android.os.Bundle r3 = r2.getAdMetadata()
            r5.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r5, r3)
            goto L_0x0139
        L_0x0081:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.internal.ads.zzxj r3 = com.google.android.gms.internal.ads.zzxi.zzd(r3)
            r2.zza((com.google.android.gms.internal.ads.zzxj) r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x0091:
            java.lang.String r3 = r4.readString()
            r2.setUserId(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x009d:
            java.lang.String r3 = r2.getMediationAdapterClassName()
            r5.writeNoException()
            r5.writeString(r3)
            goto L_0x0139
        L_0x00a9:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            r2.zzl(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x00b9:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            r2.zzk(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x00c9:
            android.os.IBinder r3 = r4.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r3)
            r2.zzj(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x00d8:
            r2.destroy()
            r5.writeNoException()
            goto L_0x0139
        L_0x00df:
            r2.resume()
            r5.writeNoException()
            goto L_0x0139
        L_0x00e6:
            r2.pause()
            r5.writeNoException()
            goto L_0x0139
        L_0x00ed:
            boolean r3 = r2.isLoaded()
            r5.writeNoException()
            com.google.android.gms.internal.ads.zzgw.writeBoolean(r5, r3)
            goto L_0x0139
        L_0x00f8:
            boolean r3 = com.google.android.gms.internal.ads.zzgw.zza(r4)
            r2.setImmersiveMode(r3)
            r5.writeNoException()
            goto L_0x0139
        L_0x0103:
            android.os.IBinder r3 = r4.readStrongBinder()
            if (r3 != 0) goto L_0x010a
            goto L_0x011d
        L_0x010a:
            java.lang.String r4 = "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdListener"
            android.os.IInterface r4 = r3.queryLocalInterface(r4)
            boolean r0 = r4 instanceof com.google.android.gms.internal.ads.zzaug
            if (r0 == 0) goto L_0x0118
            r1 = r4
            com.google.android.gms.internal.ads.zzaug r1 = (com.google.android.gms.internal.ads.zzaug) r1
            goto L_0x011d
        L_0x0118:
            com.google.android.gms.internal.ads.zzaui r1 = new com.google.android.gms.internal.ads.zzaui
            r1.<init>(r3)
        L_0x011d:
            r2.zza((com.google.android.gms.internal.ads.zzaug) r1)
            r5.writeNoException()
            goto L_0x0139
        L_0x0124:
            r2.show()
            r5.writeNoException()
            goto L_0x0139
        L_0x012b:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaum> r3 = com.google.android.gms.internal.ads.zzaum.CREATOR
            android.os.Parcelable r3 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r4, r3)
            com.google.android.gms.internal.ads.zzaum r3 = (com.google.android.gms.internal.ads.zzaum) r3
            r2.zza((com.google.android.gms.internal.ads.zzaum) r3)
            r5.writeNoException()
        L_0x0139:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzauc.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
