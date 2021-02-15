package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzanf extends zzgt implements zzang {
    public zzanf() {
        super("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }

    /* JADX WARNING: type inference failed for: r11v3, types: [android.os.IInterface] */
    /* JADX WARNING: type inference failed for: r11v4, types: [android.os.IInterface] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
        /*
            r9 = this;
            r13 = 0
            java.lang.String r0 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapterListener"
            switch(r10) {
                case 1: goto L_0x02e8;
                case 2: goto L_0x02dd;
                case 3: goto L_0x02ab;
                case 4: goto L_0x02a3;
                case 5: goto L_0x029b;
                case 6: goto L_0x0256;
                case 7: goto L_0x021a;
                case 8: goto L_0x0212;
                case 9: goto L_0x020a;
                case 10: goto L_0x01e0;
                case 11: goto L_0x01cc;
                case 12: goto L_0x01c4;
                case 13: goto L_0x01b8;
                case 14: goto L_0x0170;
                case 15: goto L_0x0164;
                case 16: goto L_0x0158;
                case 17: goto L_0x014c;
                case 18: goto L_0x0140;
                case 19: goto L_0x0134;
                case 20: goto L_0x011c;
                case 21: goto L_0x010c;
                case 22: goto L_0x0100;
                case 23: goto L_0x00e4;
                case 24: goto L_0x00d8;
                case 25: goto L_0x00cc;
                case 26: goto L_0x00c0;
                case 27: goto L_0x00b4;
                case 28: goto L_0x0081;
                case 29: goto L_0x0006;
                case 30: goto L_0x0071;
                case 31: goto L_0x0053;
                case 32: goto L_0x0020;
                case 33: goto L_0x0014;
                case 34: goto L_0x0008;
                default: goto L_0x0006;
            }
        L_0x0006:
            r10 = 0
            return r10
        L_0x0008:
            com.google.android.gms.internal.ads.zzapv r10 = r9.zzub()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r12, r10)
            goto L_0x0327
        L_0x0014:
            com.google.android.gms.internal.ads.zzapv r10 = r9.zzua()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r12, r10)
            goto L_0x0327
        L_0x0020:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r1 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r1)
            com.google.android.gms.internal.ads.zzvg r1 = (com.google.android.gms.internal.ads.zzvg) r1
            java.lang.String r2 = r11.readString()
            android.os.IBinder r11 = r11.readStrongBinder()
            if (r11 != 0) goto L_0x003b
            goto L_0x004b
        L_0x003b:
            android.os.IInterface r13 = r11.queryLocalInterface(r0)
            boolean r0 = r13 instanceof com.google.android.gms.internal.ads.zzanh
            if (r0 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzanh r13 = (com.google.android.gms.internal.ads.zzanh) r13
            goto L_0x004b
        L_0x0046:
            com.google.android.gms.internal.ads.zzanj r13 = new com.google.android.gms.internal.ads.zzanj
            r13.<init>(r11)
        L_0x004b:
            r9.zzc(r10, r1, r2, r13)
            r12.writeNoException()
            goto L_0x0327
        L_0x0053:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.IBinder r13 = r11.readStrongBinder()
            com.google.android.gms.internal.ads.zzais r13 = com.google.android.gms.internal.ads.zzair.zzz(r13)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaja> r0 = com.google.android.gms.internal.ads.zzaja.CREATOR
            java.util.ArrayList r11 = r11.createTypedArrayList(r0)
            r9.zza((com.google.android.gms.dynamic.IObjectWrapper) r10, (com.google.android.gms.internal.ads.zzais) r13, (java.util.List<com.google.android.gms.internal.ads.zzaja>) r11)
            r12.writeNoException()
            goto L_0x0327
        L_0x0071:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            r9.zzt(r10)
            r12.writeNoException()
            goto L_0x0327
        L_0x0081:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r1 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r1)
            com.google.android.gms.internal.ads.zzvg r1 = (com.google.android.gms.internal.ads.zzvg) r1
            java.lang.String r2 = r11.readString()
            android.os.IBinder r11 = r11.readStrongBinder()
            if (r11 != 0) goto L_0x009c
            goto L_0x00ac
        L_0x009c:
            android.os.IInterface r13 = r11.queryLocalInterface(r0)
            boolean r0 = r13 instanceof com.google.android.gms.internal.ads.zzanh
            if (r0 == 0) goto L_0x00a7
            com.google.android.gms.internal.ads.zzanh r13 = (com.google.android.gms.internal.ads.zzanh) r13
            goto L_0x00ac
        L_0x00a7:
            com.google.android.gms.internal.ads.zzanj r13 = new com.google.android.gms.internal.ads.zzanj
            r13.<init>(r11)
        L_0x00ac:
            r9.zzb(r10, r1, r2, r13)
            r12.writeNoException()
            goto L_0x0327
        L_0x00b4:
            com.google.android.gms.internal.ads.zzanu r10 = r9.zztz()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r12, (android.os.IInterface) r10)
            goto L_0x0327
        L_0x00c0:
            com.google.android.gms.internal.ads.zzyo r10 = r9.getVideoController()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r12, (android.os.IInterface) r10)
            goto L_0x0327
        L_0x00cc:
            boolean r10 = com.google.android.gms.internal.ads.zzgw.zza(r11)
            r9.setImmersiveMode(r10)
            r12.writeNoException()
            goto L_0x0327
        L_0x00d8:
            com.google.android.gms.internal.ads.zzafa r10 = r9.zzty()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r12, (android.os.IInterface) r10)
            goto L_0x0327
        L_0x00e4:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.IBinder r13 = r11.readStrongBinder()
            com.google.android.gms.internal.ads.zzaur r13 = com.google.android.gms.internal.ads.zzauq.zzap(r13)
            java.util.ArrayList r11 = r11.createStringArrayList()
            r9.zza((com.google.android.gms.dynamic.IObjectWrapper) r10, (com.google.android.gms.internal.ads.zzaur) r13, (java.util.List<java.lang.String>) r11)
            r12.writeNoException()
            goto L_0x0327
        L_0x0100:
            boolean r10 = r9.zztx()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.writeBoolean(r12, r10)
            goto L_0x0327
        L_0x010c:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            r9.zzs(r10)
            r12.writeNoException()
            goto L_0x0327
        L_0x011c:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r10 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            com.google.android.gms.internal.ads.zzvg r10 = (com.google.android.gms.internal.ads.zzvg) r10
            java.lang.String r13 = r11.readString()
            java.lang.String r11 = r11.readString()
            r9.zza((com.google.android.gms.internal.ads.zzvg) r10, (java.lang.String) r13, (java.lang.String) r11)
            r12.writeNoException()
            goto L_0x0327
        L_0x0134:
            android.os.Bundle r10 = r9.zztw()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r12, r10)
            goto L_0x0327
        L_0x0140:
            android.os.Bundle r10 = r9.getInterstitialAdapterInfo()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r12, r10)
            goto L_0x0327
        L_0x014c:
            android.os.Bundle r10 = r9.zztv()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r12, r10)
            goto L_0x0327
        L_0x0158:
            com.google.android.gms.internal.ads.zzanp r10 = r9.zztu()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r12, (android.os.IInterface) r10)
            goto L_0x0327
        L_0x0164:
            com.google.android.gms.internal.ads.zzano r10 = r9.zztt()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r12, (android.os.IInterface) r10)
            goto L_0x0327
        L_0x0170:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r10 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzvg r3 = (com.google.android.gms.internal.ads.zzvg) r3
            java.lang.String r4 = r11.readString()
            java.lang.String r5 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x0191
        L_0x018f:
            r6 = r13
            goto L_0x01a2
        L_0x0191:
            android.os.IInterface r13 = r10.queryLocalInterface(r0)
            boolean r0 = r13 instanceof com.google.android.gms.internal.ads.zzanh
            if (r0 == 0) goto L_0x019c
            com.google.android.gms.internal.ads.zzanh r13 = (com.google.android.gms.internal.ads.zzanh) r13
            goto L_0x018f
        L_0x019c:
            com.google.android.gms.internal.ads.zzanj r13 = new com.google.android.gms.internal.ads.zzanj
            r13.<init>(r10)
            goto L_0x018f
        L_0x01a2:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzadu> r10 = com.google.android.gms.internal.ads.zzadu.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r7 = r10
            com.google.android.gms.internal.ads.zzadu r7 = (com.google.android.gms.internal.ads.zzadu) r7
            java.util.ArrayList r8 = r11.createStringArrayList()
            r1 = r9
            r1.zza(r2, r3, r4, r5, r6, r7, r8)
            r12.writeNoException()
            goto L_0x0327
        L_0x01b8:
            boolean r10 = r9.isInitialized()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.writeBoolean(r12, r10)
            goto L_0x0327
        L_0x01c4:
            r9.showVideo()
            r12.writeNoException()
            goto L_0x0327
        L_0x01cc:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r10 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            com.google.android.gms.internal.ads.zzvg r10 = (com.google.android.gms.internal.ads.zzvg) r10
            java.lang.String r11 = r11.readString()
            r9.zza(r10, r11)
            r12.writeNoException()
            goto L_0x0327
        L_0x01e0:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r1 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r10 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r2 = r10
            com.google.android.gms.internal.ads.zzvg r2 = (com.google.android.gms.internal.ads.zzvg) r2
            java.lang.String r3 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.internal.ads.zzaur r4 = com.google.android.gms.internal.ads.zzauq.zzap(r10)
            java.lang.String r5 = r11.readString()
            r0 = r9
            r0.zza((com.google.android.gms.dynamic.IObjectWrapper) r1, (com.google.android.gms.internal.ads.zzvg) r2, (java.lang.String) r3, (com.google.android.gms.internal.ads.zzaur) r4, (java.lang.String) r5)
            r12.writeNoException()
            goto L_0x0327
        L_0x020a:
            r9.resume()
            r12.writeNoException()
            goto L_0x0327
        L_0x0212:
            r9.pause()
            r12.writeNoException()
            goto L_0x0327
        L_0x021a:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r10 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzvg r3 = (com.google.android.gms.internal.ads.zzvg) r3
            java.lang.String r4 = r11.readString()
            java.lang.String r5 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x023b
        L_0x0239:
            r6 = r13
            goto L_0x024d
        L_0x023b:
            android.os.IInterface r11 = r10.queryLocalInterface(r0)
            boolean r13 = r11 instanceof com.google.android.gms.internal.ads.zzanh
            if (r13 == 0) goto L_0x0247
            r13 = r11
            com.google.android.gms.internal.ads.zzanh r13 = (com.google.android.gms.internal.ads.zzanh) r13
            goto L_0x0239
        L_0x0247:
            com.google.android.gms.internal.ads.zzanj r13 = new com.google.android.gms.internal.ads.zzanj
            r13.<init>(r10)
            goto L_0x0239
        L_0x024d:
            r1 = r9
            r1.zza((com.google.android.gms.dynamic.IObjectWrapper) r2, (com.google.android.gms.internal.ads.zzvg) r3, (java.lang.String) r4, (java.lang.String) r5, (com.google.android.gms.internal.ads.zzanh) r6)
            r12.writeNoException()
            goto L_0x0327
        L_0x0256:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvn> r10 = com.google.android.gms.internal.ads.zzvn.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzvn r3 = (com.google.android.gms.internal.ads.zzvn) r3
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r10 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r4 = r10
            com.google.android.gms.internal.ads.zzvg r4 = (com.google.android.gms.internal.ads.zzvg) r4
            java.lang.String r5 = r11.readString()
            java.lang.String r6 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x0280
        L_0x027e:
            r7 = r13
            goto L_0x0292
        L_0x0280:
            android.os.IInterface r11 = r10.queryLocalInterface(r0)
            boolean r13 = r11 instanceof com.google.android.gms.internal.ads.zzanh
            if (r13 == 0) goto L_0x028c
            r13 = r11
            com.google.android.gms.internal.ads.zzanh r13 = (com.google.android.gms.internal.ads.zzanh) r13
            goto L_0x027e
        L_0x028c:
            com.google.android.gms.internal.ads.zzanj r13 = new com.google.android.gms.internal.ads.zzanj
            r13.<init>(r10)
            goto L_0x027e
        L_0x0292:
            r1 = r9
            r1.zza(r2, r3, r4, r5, r6, r7)
            r12.writeNoException()
            goto L_0x0327
        L_0x029b:
            r9.destroy()
            r12.writeNoException()
            goto L_0x0327
        L_0x02a3:
            r9.showInterstitial()
            r12.writeNoException()
            goto L_0x0327
        L_0x02ab:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r10 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r1 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r1)
            com.google.android.gms.internal.ads.zzvg r1 = (com.google.android.gms.internal.ads.zzvg) r1
            java.lang.String r2 = r11.readString()
            android.os.IBinder r11 = r11.readStrongBinder()
            if (r11 != 0) goto L_0x02c6
            goto L_0x02d6
        L_0x02c6:
            android.os.IInterface r13 = r11.queryLocalInterface(r0)
            boolean r0 = r13 instanceof com.google.android.gms.internal.ads.zzanh
            if (r0 == 0) goto L_0x02d1
            com.google.android.gms.internal.ads.zzanh r13 = (com.google.android.gms.internal.ads.zzanh) r13
            goto L_0x02d6
        L_0x02d1:
            com.google.android.gms.internal.ads.zzanj r13 = new com.google.android.gms.internal.ads.zzanj
            r13.<init>(r11)
        L_0x02d6:
            r9.zza(r10, r1, r2, r13)
            r12.writeNoException()
            goto L_0x0327
        L_0x02dd:
            com.google.android.gms.dynamic.IObjectWrapper r10 = r9.zzts()
            r12.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r12, (android.os.IInterface) r10)
            goto L_0x0327
        L_0x02e8:
            android.os.IBinder r10 = r11.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r2 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r10)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvn> r10 = com.google.android.gms.internal.ads.zzvn.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r3 = r10
            com.google.android.gms.internal.ads.zzvn r3 = (com.google.android.gms.internal.ads.zzvn) r3
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r10 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r10 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r11, r10)
            r4 = r10
            com.google.android.gms.internal.ads.zzvg r4 = (com.google.android.gms.internal.ads.zzvg) r4
            java.lang.String r5 = r11.readString()
            android.os.IBinder r10 = r11.readStrongBinder()
            if (r10 != 0) goto L_0x030e
        L_0x030c:
            r6 = r13
            goto L_0x0320
        L_0x030e:
            android.os.IInterface r11 = r10.queryLocalInterface(r0)
            boolean r13 = r11 instanceof com.google.android.gms.internal.ads.zzanh
            if (r13 == 0) goto L_0x031a
            r13 = r11
            com.google.android.gms.internal.ads.zzanh r13 = (com.google.android.gms.internal.ads.zzanh) r13
            goto L_0x030c
        L_0x031a:
            com.google.android.gms.internal.ads.zzanj r13 = new com.google.android.gms.internal.ads.zzanj
            r13.<init>(r10)
            goto L_0x030c
        L_0x0320:
            r1 = r9
            r1.zza((com.google.android.gms.dynamic.IObjectWrapper) r2, (com.google.android.gms.internal.ads.zzvn) r3, (com.google.android.gms.internal.ads.zzvg) r4, (java.lang.String) r5, (com.google.android.gms.internal.ads.zzanh) r6)
            r12.writeNoException()
        L_0x0327:
            r10 = 1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanf.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
