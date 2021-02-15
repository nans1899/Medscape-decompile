package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzwy extends zzgt implements zzwz {
    public zzwy() {
        super("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
    }

    /* JADX WARNING: type inference failed for: r4v1 */
    /* JADX WARNING: type inference failed for: r4v2, types: [com.google.android.gms.internal.ads.zzwt] */
    /* JADX WARNING: type inference failed for: r4v9, types: [com.google.android.gms.internal.ads.zzxq] */
    /* JADX WARNING: type inference failed for: r4v15 */
    /* JADX WARNING: type inference failed for: r4v16 */
    /* JADX WARNING: type inference failed for: r4v17 */
    /* JADX WARNING: type inference failed for: r4v18 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r1, android.os.Parcel r2, android.os.Parcel r3, int r4) throws android.os.RemoteException {
        /*
            r0 = this;
            r4 = 0
            switch(r1) {
                case 1: goto L_0x00e9;
                case 2: goto L_0x00c8;
                case 3: goto L_0x00b9;
                case 4: goto L_0x00aa;
                case 5: goto L_0x008f;
                case 6: goto L_0x0080;
                case 7: goto L_0x005e;
                case 8: goto L_0x0046;
                case 9: goto L_0x0036;
                case 10: goto L_0x0026;
                case 11: goto L_0x0004;
                case 12: goto L_0x0004;
                case 13: goto L_0x0016;
                case 14: goto L_0x0006;
                default: goto L_0x0004;
            }
        L_0x0004:
            r1 = 0
            return r1
        L_0x0006:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzajk r1 = com.google.android.gms.internal.ads.zzajj.zzab(r1)
            r0.zza((com.google.android.gms.internal.ads.zzajk) r1)
            r3.writeNoException()
            goto L_0x00f3
        L_0x0016:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzajc> r1 = com.google.android.gms.internal.ads.zzajc.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r2, r1)
            com.google.android.gms.internal.ads.zzajc r1 = (com.google.android.gms.internal.ads.zzajc) r1
            r0.zza((com.google.android.gms.internal.ads.zzajc) r1)
            r3.writeNoException()
            goto L_0x00f3
        L_0x0026:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzaft r1 = com.google.android.gms.internal.ads.zzafw.zzx(r1)
            r0.zza((com.google.android.gms.internal.ads.zzaft) r1)
            r3.writeNoException()
            goto L_0x00f3
        L_0x0036:
            android.os.Parcelable$Creator<com.google.android.gms.ads.formats.PublisherAdViewOptions> r1 = com.google.android.gms.ads.formats.PublisherAdViewOptions.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r2, r1)
            com.google.android.gms.ads.formats.PublisherAdViewOptions r1 = (com.google.android.gms.ads.formats.PublisherAdViewOptions) r1
            r0.zza((com.google.android.gms.ads.formats.PublisherAdViewOptions) r1)
            r3.writeNoException()
            goto L_0x00f3
        L_0x0046:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzafs r1 = com.google.android.gms.internal.ads.zzafr.zzw(r1)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvn> r4 = com.google.android.gms.internal.ads.zzvn.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r2, r4)
            com.google.android.gms.internal.ads.zzvn r2 = (com.google.android.gms.internal.ads.zzvn) r2
            r0.zza(r1, r2)
            r3.writeNoException()
            goto L_0x00f3
        L_0x005e:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x0065
            goto L_0x0078
        L_0x0065:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.ICorrelationIdProvider"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzxq
            if (r4 == 0) goto L_0x0073
            r4 = r2
            com.google.android.gms.internal.ads.zzxq r4 = (com.google.android.gms.internal.ads.zzxq) r4
            goto L_0x0078
        L_0x0073:
            com.google.android.gms.internal.ads.zzxt r4 = new com.google.android.gms.internal.ads.zzxt
            r4.<init>(r1)
        L_0x0078:
            r0.zzb((com.google.android.gms.internal.ads.zzxq) r4)
            r3.writeNoException()
            goto L_0x00f3
        L_0x0080:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzadu> r1 = com.google.android.gms.internal.ads.zzadu.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r2, r1)
            com.google.android.gms.internal.ads.zzadu r1 = (com.google.android.gms.internal.ads.zzadu) r1
            r0.zza((com.google.android.gms.internal.ads.zzadu) r1)
            r3.writeNoException()
            goto L_0x00f3
        L_0x008f:
            java.lang.String r1 = r2.readString()
            android.os.IBinder r4 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzafl r4 = com.google.android.gms.internal.ads.zzafo.zzv(r4)
            android.os.IBinder r2 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzafk r2 = com.google.android.gms.internal.ads.zzafj.zzu(r2)
            r0.zza(r1, r4, r2)
            r3.writeNoException()
            goto L_0x00f3
        L_0x00aa:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzaff r1 = com.google.android.gms.internal.ads.zzafi.zzt(r1)
            r0.zza((com.google.android.gms.internal.ads.zzaff) r1)
            r3.writeNoException()
            goto L_0x00f3
        L_0x00b9:
            android.os.IBinder r1 = r2.readStrongBinder()
            com.google.android.gms.internal.ads.zzafe r1 = com.google.android.gms.internal.ads.zzafd.zzs(r1)
            r0.zza((com.google.android.gms.internal.ads.zzafe) r1)
            r3.writeNoException()
            goto L_0x00f3
        L_0x00c8:
            android.os.IBinder r1 = r2.readStrongBinder()
            if (r1 != 0) goto L_0x00cf
            goto L_0x00e2
        L_0x00cf:
            java.lang.String r2 = "com.google.android.gms.ads.internal.client.IAdListener"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)
            boolean r4 = r2 instanceof com.google.android.gms.internal.ads.zzwt
            if (r4 == 0) goto L_0x00dd
            r4 = r2
            com.google.android.gms.internal.ads.zzwt r4 = (com.google.android.gms.internal.ads.zzwt) r4
            goto L_0x00e2
        L_0x00dd:
            com.google.android.gms.internal.ads.zzwv r4 = new com.google.android.gms.internal.ads.zzwv
            r4.<init>(r1)
        L_0x00e2:
            r0.zzb((com.google.android.gms.internal.ads.zzwt) r4)
            r3.writeNoException()
            goto L_0x00f3
        L_0x00e9:
            com.google.android.gms.internal.ads.zzwu r1 = r0.zzqf()
            r3.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r3, (android.os.IInterface) r1)
        L_0x00f3:
            r1 = 1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzwy.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
