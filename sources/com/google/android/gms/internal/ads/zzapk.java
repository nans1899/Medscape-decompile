package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzapk extends zzgt implements zzaph {
    public zzapk() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public static zzaph zzaf(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
        if (queryLocalInterface instanceof zzaph) {
            return (zzaph) queryLocalInterface;
        }
        return new zzapj(iBinder);
    }

    /* JADX WARNING: type inference failed for: r2v7, types: [com.google.android.gms.internal.ads.zzaov] */
    /* JADX WARNING: type inference failed for: r2v12, types: [com.google.android.gms.internal.ads.zzapc] */
    /* JADX WARNING: type inference failed for: r2v13, types: [com.google.android.gms.internal.ads.zzapa] */
    /* JADX WARNING: type inference failed for: r5v8, types: [com.google.android.gms.internal.ads.zzapa] */
    /* JADX WARNING: type inference failed for: r2v17, types: [com.google.android.gms.internal.ads.zzapi] */
    /* JADX WARNING: type inference failed for: r2v18, types: [com.google.android.gms.internal.ads.zzapg] */
    /* JADX WARNING: type inference failed for: r5v10, types: [com.google.android.gms.internal.ads.zzapg] */
    /* JADX WARNING: type inference failed for: r2v23, types: [com.google.android.gms.internal.ads.zzapd] */
    /* JADX WARNING: type inference failed for: r2v24, types: [com.google.android.gms.internal.ads.zzapb] */
    /* JADX WARNING: type inference failed for: r5v13, types: [com.google.android.gms.internal.ads.zzapb] */
    /* JADX WARNING: type inference failed for: r2v28, types: [com.google.android.gms.internal.ads.zzapi] */
    /* JADX WARNING: type inference failed for: r2v29, types: [com.google.android.gms.internal.ads.zzapg] */
    /* JADX WARNING: type inference failed for: r5v15, types: [com.google.android.gms.internal.ads.zzapg] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(int r14, android.os.Parcel r15, android.os.Parcel r16, int r17) throws android.os.RemoteException {
        /*
            r13 = this;
            r8 = r13
            r0 = r14
            r1 = r15
            r9 = r16
            r10 = 1
            r2 = 0
            if (r0 == r10) goto L_0x0218
            r3 = 2
            if (r0 == r3) goto L_0x020d
            r3 = 3
            if (r0 == r3) goto L_0x0202
            r3 = 5
            if (r0 == r3) goto L_0x01f6
            r3 = 10
            if (r0 == r3) goto L_0x01e6
            r3 = 11
            if (r0 == r3) goto L_0x01d2
            java.lang.String r3 = "com.google.android.gms.ads.internal.mediation.client.rtb.IRewardedCallback"
            switch(r0) {
                case 13: goto L_0x017d;
                case 14: goto L_0x0132;
                case 15: goto L_0x011e;
                case 16: goto L_0x00d5;
                case 17: goto L_0x00c1;
                case 18: goto L_0x0076;
                case 19: goto L_0x006a;
                case 20: goto L_0x0021;
                default: goto L_0x001f;
            }
        L_0x001f:
            r0 = 0
            return r0
        L_0x0021:
            java.lang.String r4 = r15.readString()
            java.lang.String r5 = r15.readString()
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r0 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r6 = r0
            com.google.android.gms.internal.ads.zzvg r6 = (com.google.android.gms.internal.ads.zzvg) r6
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r15.readStrongBinder()
            if (r0 != 0) goto L_0x0042
        L_0x0040:
            r11 = r2
            goto L_0x0053
        L_0x0042:
            android.os.IInterface r2 = r0.queryLocalInterface(r3)
            boolean r3 = r2 instanceof com.google.android.gms.internal.ads.zzapg
            if (r3 == 0) goto L_0x004d
            com.google.android.gms.internal.ads.zzapg r2 = (com.google.android.gms.internal.ads.zzapg) r2
            goto L_0x0040
        L_0x004d:
            com.google.android.gms.internal.ads.zzapi r2 = new com.google.android.gms.internal.ads.zzapi
            r2.<init>(r0)
            goto L_0x0040
        L_0x0053:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.internal.ads.zzanh r12 = com.google.android.gms.internal.ads.zzank.zzad(r0)
            r0 = r13
            r1 = r4
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r11
            r6 = r12
            r0.zzb(r1, r2, r3, r4, r5, r6)
            r16.writeNoException()
            goto L_0x0267
        L_0x006a:
            java.lang.String r0 = r15.readString()
            r13.zzdr(r0)
            r16.writeNoException()
            goto L_0x0267
        L_0x0076:
            java.lang.String r3 = r15.readString()
            java.lang.String r4 = r15.readString()
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r0 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r5 = r0
            com.google.android.gms.internal.ads.zzvg r5 = (com.google.android.gms.internal.ads.zzvg) r5
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r15.readStrongBinder()
            if (r0 != 0) goto L_0x0097
        L_0x0095:
            r7 = r2
            goto L_0x00aa
        L_0x0097:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.rtb.INativeCallback"
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r7 = r2 instanceof com.google.android.gms.internal.ads.zzapb
            if (r7 == 0) goto L_0x00a4
            com.google.android.gms.internal.ads.zzapb r2 = (com.google.android.gms.internal.ads.zzapb) r2
            goto L_0x0095
        L_0x00a4:
            com.google.android.gms.internal.ads.zzapd r2 = new com.google.android.gms.internal.ads.zzapd
            r2.<init>(r0)
            goto L_0x0095
        L_0x00aa:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.internal.ads.zzanh r11 = com.google.android.gms.internal.ads.zzank.zzad(r0)
            r0 = r13
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r11
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (com.google.android.gms.internal.ads.zzvg) r3, (com.google.android.gms.dynamic.IObjectWrapper) r4, (com.google.android.gms.internal.ads.zzapb) r5, (com.google.android.gms.internal.ads.zzanh) r6)
            r16.writeNoException()
            goto L_0x0267
        L_0x00c1:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            boolean r0 = r13.zzaa(r0)
            r16.writeNoException()
            com.google.android.gms.internal.ads.zzgw.writeBoolean(r9, r0)
            goto L_0x0267
        L_0x00d5:
            java.lang.String r4 = r15.readString()
            java.lang.String r5 = r15.readString()
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r0 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r6 = r0
            com.google.android.gms.internal.ads.zzvg r6 = (com.google.android.gms.internal.ads.zzvg) r6
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r7 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r15.readStrongBinder()
            if (r0 != 0) goto L_0x00f6
        L_0x00f4:
            r11 = r2
            goto L_0x0107
        L_0x00f6:
            android.os.IInterface r2 = r0.queryLocalInterface(r3)
            boolean r3 = r2 instanceof com.google.android.gms.internal.ads.zzapg
            if (r3 == 0) goto L_0x0101
            com.google.android.gms.internal.ads.zzapg r2 = (com.google.android.gms.internal.ads.zzapg) r2
            goto L_0x00f4
        L_0x0101:
            com.google.android.gms.internal.ads.zzapi r2 = new com.google.android.gms.internal.ads.zzapi
            r2.<init>(r0)
            goto L_0x00f4
        L_0x0107:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.internal.ads.zzanh r12 = com.google.android.gms.internal.ads.zzank.zzad(r0)
            r0 = r13
            r1 = r4
            r2 = r5
            r3 = r6
            r4 = r7
            r5 = r11
            r6 = r12
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (com.google.android.gms.internal.ads.zzvg) r3, (com.google.android.gms.dynamic.IObjectWrapper) r4, (com.google.android.gms.internal.ads.zzapg) r5, (com.google.android.gms.internal.ads.zzanh) r6)
            r16.writeNoException()
            goto L_0x0267
        L_0x011e:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            boolean r0 = r13.zzz(r0)
            r16.writeNoException()
            com.google.android.gms.internal.ads.zzgw.writeBoolean(r9, r0)
            goto L_0x0267
        L_0x0132:
            java.lang.String r3 = r15.readString()
            java.lang.String r4 = r15.readString()
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r0 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r5 = r0
            com.google.android.gms.internal.ads.zzvg r5 = (com.google.android.gms.internal.ads.zzvg) r5
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r15.readStrongBinder()
            if (r0 != 0) goto L_0x0153
        L_0x0151:
            r7 = r2
            goto L_0x0166
        L_0x0153:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.rtb.IInterstitialCallback"
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r7 = r2 instanceof com.google.android.gms.internal.ads.zzapa
            if (r7 == 0) goto L_0x0160
            com.google.android.gms.internal.ads.zzapa r2 = (com.google.android.gms.internal.ads.zzapa) r2
            goto L_0x0151
        L_0x0160:
            com.google.android.gms.internal.ads.zzapc r2 = new com.google.android.gms.internal.ads.zzapc
            r2.<init>(r0)
            goto L_0x0151
        L_0x0166:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.internal.ads.zzanh r11 = com.google.android.gms.internal.ads.zzank.zzad(r0)
            r0 = r13
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r11
            r0.zza((java.lang.String) r1, (java.lang.String) r2, (com.google.android.gms.internal.ads.zzvg) r3, (com.google.android.gms.dynamic.IObjectWrapper) r4, (com.google.android.gms.internal.ads.zzapa) r5, (com.google.android.gms.internal.ads.zzanh) r6)
            r16.writeNoException()
            goto L_0x0267
        L_0x017d:
            java.lang.String r3 = r15.readString()
            java.lang.String r4 = r15.readString()
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r0 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r5 = r0
            com.google.android.gms.internal.ads.zzvg r5 = (com.google.android.gms.internal.ads.zzvg) r5
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r6 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            android.os.IBinder r0 = r15.readStrongBinder()
            if (r0 != 0) goto L_0x019e
        L_0x019c:
            r7 = r2
            goto L_0x01b1
        L_0x019e:
            java.lang.String r2 = "com.google.android.gms.ads.internal.mediation.client.rtb.IBannerCallback"
            android.os.IInterface r2 = r0.queryLocalInterface(r2)
            boolean r7 = r2 instanceof com.google.android.gms.internal.ads.zzaov
            if (r7 == 0) goto L_0x01ab
            com.google.android.gms.internal.ads.zzaov r2 = (com.google.android.gms.internal.ads.zzaov) r2
            goto L_0x019c
        L_0x01ab:
            com.google.android.gms.internal.ads.zzaox r2 = new com.google.android.gms.internal.ads.zzaox
            r2.<init>(r0)
            goto L_0x019c
        L_0x01b1:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.internal.ads.zzanh r11 = com.google.android.gms.internal.ads.zzank.zzad(r0)
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvn> r0 = com.google.android.gms.internal.ads.zzvn.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r12 = r0
            com.google.android.gms.internal.ads.zzvn r12 = (com.google.android.gms.internal.ads.zzvn) r12
            r0 = r13
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r11
            r7 = r12
            r0.zza(r1, r2, r3, r4, r5, r6, r7)
            r16.writeNoException()
            goto L_0x0267
        L_0x01d2:
            java.lang.String[] r0 = r15.createStringArray()
            android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
            java.lang.Object[] r1 = r15.createTypedArray(r2)
            android.os.Bundle[] r1 = (android.os.Bundle[]) r1
            r13.zza(r0, r1)
            r16.writeNoException()
            goto L_0x0267
        L_0x01e6:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            r13.zzy(r0)
            r16.writeNoException()
            goto L_0x0267
        L_0x01f6:
            com.google.android.gms.internal.ads.zzyo r0 = r13.getVideoController()
            r16.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r9, (android.os.IInterface) r0)
            goto L_0x0267
        L_0x0202:
            com.google.android.gms.internal.ads.zzapv r0 = r13.zzub()
            r16.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r9, r0)
            goto L_0x0267
        L_0x020d:
            com.google.android.gms.internal.ads.zzapv r0 = r13.zzua()
            r16.writeNoException()
            com.google.android.gms.internal.ads.zzgw.zzb(r9, r0)
            goto L_0x0267
        L_0x0218:
            android.os.IBinder r0 = r15.readStrongBinder()
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(r0)
            java.lang.String r4 = r15.readString()
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r5 = r0
            android.os.Bundle r5 = (android.os.Bundle) r5
            android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r6 = r0
            android.os.Bundle r6 = (android.os.Bundle) r6
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvn> r0 = com.google.android.gms.internal.ads.zzvn.CREATOR
            android.os.Parcelable r0 = com.google.android.gms.internal.ads.zzgw.zza((android.os.Parcel) r15, r0)
            r7 = r0
            com.google.android.gms.internal.ads.zzvn r7 = (com.google.android.gms.internal.ads.zzvn) r7
            android.os.IBinder r0 = r15.readStrongBinder()
            if (r0 != 0) goto L_0x0247
            r11 = r2
            goto L_0x025a
        L_0x0247:
            java.lang.String r1 = "com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback"
            android.os.IInterface r1 = r0.queryLocalInterface(r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.ads.zzapm
            if (r2 == 0) goto L_0x0254
            com.google.android.gms.internal.ads.zzapm r1 = (com.google.android.gms.internal.ads.zzapm) r1
            goto L_0x0259
        L_0x0254:
            com.google.android.gms.internal.ads.zzapo r1 = new com.google.android.gms.internal.ads.zzapo
            r1.<init>(r0)
        L_0x0259:
            r11 = r1
        L_0x025a:
            r0 = r13
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r6
            r5 = r7
            r6 = r11
            r0.zza((com.google.android.gms.dynamic.IObjectWrapper) r1, (java.lang.String) r2, (android.os.Bundle) r3, (android.os.Bundle) r4, (com.google.android.gms.internal.ads.zzvn) r5, (com.google.android.gms.internal.ads.zzapm) r6)
            r16.writeNoException()
        L_0x0267:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzapk.zza(int, android.os.Parcel, android.os.Parcel, int):boolean");
    }
}
