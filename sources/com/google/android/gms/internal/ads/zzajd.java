package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzajd extends zzgt implements zzaje {
    public zzajd() {
        super("com.google.android.gms.ads.internal.instream.client.IInstreamAd");
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            r5 = 3
            if (r2 == r5) goto L_0x005d
            r5 = 4
            if (r2 == r5) goto L_0x0056
            r5 = 5
            if (r2 == r5) goto L_0x002b
            r5 = 6
            if (r2 == r5) goto L_0x001c
            r3 = 7
            if (r2 == r3) goto L_0x0011
            r2 = 0
            return r2
        L_0x0011:
            com.google.android.gms.internal.ads.zzadz r2 = r1.zzsw()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
            goto L_0x0067
        L_0x001c:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            r1.zzr(r2)
            r4.writeNoException()
            goto L_0x0067
        L_0x002b:
            android.os.IBinder r2 = r3.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r2)
            android.os.IBinder r3 = r3.readStrongBinder()
            if (r3 != 0) goto L_0x003b
            r3 = 0
            goto L_0x004f
        L_0x003b:
            java.lang.String r5 = "com.google.android.gms.ads.internal.instream.client.IInstreamAdCallback"
            android.os.IInterface r5 = r3.queryLocalInterface(r5)
            boolean r0 = r5 instanceof com.google.android.gms.internal.ads.zzajf
            if (r0 == 0) goto L_0x0049
            r3 = r5
            com.google.android.gms.internal.ads.zzajf r3 = (com.google.android.gms.internal.ads.zzajf) r3
            goto L_0x004f
        L_0x0049:
            com.google.android.gms.internal.ads.zzajh r5 = new com.google.android.gms.internal.ads.zzajh
            r5.<init>(r3)
            r3 = r5
        L_0x004f:
            r1.zza(r2, r3)
            r4.writeNoException()
            goto L_0x0067
        L_0x0056:
            r1.destroy()
            r4.writeNoException()
            goto L_0x0067
        L_0x005d:
            com.google.android.gms.internal.ads.zzyo r2 = r1.getVideoController()
            r4.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r4, (android.os.IInterface) r2)
        L_0x0067:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajd.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
