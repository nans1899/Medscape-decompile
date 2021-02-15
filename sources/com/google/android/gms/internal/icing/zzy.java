package com.google.android.gms.internal.icing;

import android.os.Parcelable;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzy implements Parcelable.Creator<zzw> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzw[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r19) {
        /*
            r18 = this;
            r0 = r19
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r19)
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = -1
            r8 = r3
            r12 = r8
            r13 = r12
            r17 = r13
            r9 = r4
            r11 = 0
            r14 = 0
            r15 = -1
            r16 = 0
        L_0x0016:
            int r2 = r19.dataPosition()
            if (r2 >= r1) goto L_0x0062
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r19)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x0058;
                case 2: goto L_0x0053;
                case 3: goto L_0x004e;
                case 4: goto L_0x0049;
                case 5: goto L_0x003f;
                case 6: goto L_0x003a;
                case 7: goto L_0x0035;
                case 8: goto L_0x0030;
                case 9: goto L_0x002b;
                default: goto L_0x0027;
            }
        L_0x0027:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0016
        L_0x002b:
            java.lang.String r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0016
        L_0x0030:
            int r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0016
        L_0x0035:
            int r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0016
        L_0x003a:
            boolean r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0016
        L_0x003f:
            android.os.Parcelable$Creator<com.google.android.gms.internal.icing.zzh> r3 = com.google.android.gms.internal.icing.zzh.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r13 = r2
            com.google.android.gms.internal.icing.zzh r13 = (com.google.android.gms.internal.icing.zzh) r13
            goto L_0x0016
        L_0x0049:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0016
        L_0x004e:
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0016
        L_0x0053:
            long r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0016
        L_0x0058:
            android.os.Parcelable$Creator<com.google.android.gms.internal.icing.zzi> r3 = com.google.android.gms.internal.icing.zzi.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r8 = r2
            com.google.android.gms.internal.icing.zzi r8 = (com.google.android.gms.internal.icing.zzi) r8
            goto L_0x0016
        L_0x0062:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.internal.icing.zzw r0 = new com.google.android.gms.internal.icing.zzw
            r7 = r0
            r7.<init>(r8, r9, r11, r12, r13, r14, r15, r16, r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzy.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
