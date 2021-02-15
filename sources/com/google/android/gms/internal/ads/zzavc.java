package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzavc extends zzgt implements zzavd {
    public zzavc() {
        super("com.google.android.gms.ads.internal.rewarded.client.IRewardedAdCallback");
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r2, android.os.Parcel r3, android.os.Parcel r4, int r5) throws android.os.RemoteException {
        /*
            r1 = this;
            r5 = 1
            if (r2 == r5) goto L_0x0049
            r0 = 2
            if (r2 == r0) goto L_0x0045
            r0 = 3
            if (r2 == r0) goto L_0x0025
            r0 = 4
            if (r2 == r0) goto L_0x001d
            r0 = 5
            if (r2 == r0) goto L_0x0011
            r2 = 0
            return r2
        L_0x0011:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzva> r2 = com.google.android.gms.internal.ads.zzva.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r3, r2)
            com.google.android.gms.internal.ads.zzva r2 = (com.google.android.gms.internal.ads.zzva) r2
            r1.zzh(r2)
            goto L_0x004c
        L_0x001d:
            int r2 = r3.readInt()
            r1.onRewardedAdFailedToShow(r2)
            goto L_0x004c
        L_0x0025:
            android.os.IBinder r2 = r3.readStrongBinder()
            if (r2 != 0) goto L_0x002d
            r2 = 0
            goto L_0x0041
        L_0x002d:
            java.lang.String r3 = "com.google.android.gms.ads.internal.rewarded.client.IRewardItem"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)
            boolean r0 = r3 instanceof com.google.android.gms.internal.ads.zzaux
            if (r0 == 0) goto L_0x003b
            r2 = r3
            com.google.android.gms.internal.ads.zzaux r2 = (com.google.android.gms.internal.ads.zzaux) r2
            goto L_0x0041
        L_0x003b:
            com.google.android.gms.internal.ads.zzauz r3 = new com.google.android.gms.internal.ads.zzauz
            r3.<init>(r2)
            r2 = r3
        L_0x0041:
            r1.zza(r2)
            goto L_0x004c
        L_0x0045:
            r1.onRewardedAdClosed()
            goto L_0x004c
        L_0x0049:
            r1.onRewardedAdOpened()
        L_0x004c:
            r4.writeNoException()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzavc.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
