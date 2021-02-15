package com.google.android.gms.internal.icing;

import android.os.Parcelable;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzv implements Parcelable.Creator<zzt> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzt[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r15) {
        /*
            r14 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r15)
            r1 = 0
            r2 = 0
            r3 = 1
            r5 = r2
            r6 = r5
            r10 = r6
            r11 = r10
            r12 = r11
            r13 = r12
            r7 = 0
            r8 = 1
            r9 = 0
        L_0x0010:
            int r1 = r15.dataPosition()
            if (r1 >= r0) goto L_0x0064
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r15)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            r3 = 11
            if (r2 == r3) goto L_0x005f
            r3 = 12
            if (r2 == r3) goto L_0x0055
            switch(r2) {
                case 1: goto L_0x0050;
                case 2: goto L_0x004b;
                case 3: goto L_0x0046;
                case 4: goto L_0x0041;
                case 5: goto L_0x003c;
                case 6: goto L_0x0037;
                case 7: goto L_0x002d;
                default: goto L_0x0029;
            }
        L_0x0029:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r15, r1)
            goto L_0x0010
        L_0x002d:
            android.os.Parcelable$Creator<com.google.android.gms.internal.icing.zzm> r2 = com.google.android.gms.internal.icing.zzm.CREATOR
            java.lang.Object[] r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r15, r1, r2)
            r11 = r1
            com.google.android.gms.internal.icing.zzm[] r11 = (com.google.android.gms.internal.icing.zzm[]) r11
            goto L_0x0010
        L_0x0037:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x003c:
            boolean r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r15, r1)
            goto L_0x0010
        L_0x0041:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r15, r1)
            goto L_0x0010
        L_0x0046:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r15, r1)
            goto L_0x0010
        L_0x004b:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x0050:
            java.lang.String r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x0055:
            android.os.Parcelable$Creator<com.google.android.gms.internal.icing.zzu> r2 = com.google.android.gms.internal.icing.zzu.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r15, r1, r2)
            r13 = r1
            com.google.android.gms.internal.icing.zzu r13 = (com.google.android.gms.internal.icing.zzu) r13
            goto L_0x0010
        L_0x005f:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r15, r1)
            goto L_0x0010
        L_0x0064:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r15, r0)
            com.google.android.gms.internal.icing.zzt r15 = new com.google.android.gms.internal.icing.zzt
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzv.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
