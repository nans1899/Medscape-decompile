package com.google.android.gms.internal.location;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public final class zzbb implements Parcelable.Creator<zzbc> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbc[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r21) {
        /*
            r20 = this;
            r0 = r21
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r21)
            java.util.List<com.google.android.gms.common.internal.ClientIdentity> r2 = com.google.android.gms.internal.location.zzbc.zza
            r3 = 0
            r4 = 0
            r5 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r9 = r2
            r8 = r3
            r10 = r8
            r14 = r10
            r17 = r14
            r18 = r5
            r11 = 0
            r12 = 0
            r13 = 0
            r15 = 0
            r16 = 0
        L_0x001d:
            int r2 = r21.dataPosition()
            if (r2 >= r1) goto L_0x0073
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r21)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            r4 = 1
            if (r3 == r4) goto L_0x0069
            switch(r3) {
                case 5: goto L_0x0062;
                case 6: goto L_0x005d;
                case 7: goto L_0x0058;
                case 8: goto L_0x0053;
                case 9: goto L_0x004e;
                case 10: goto L_0x0049;
                case 11: goto L_0x0044;
                case 12: goto L_0x003f;
                case 13: goto L_0x003a;
                case 14: goto L_0x0035;
                default: goto L_0x0031;
            }
        L_0x0031:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x001d
        L_0x0035:
            long r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x001d
        L_0x003a:
            java.lang.String r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x001d
        L_0x003f:
            boolean r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x001d
        L_0x0044:
            boolean r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x001d
        L_0x0049:
            java.lang.String r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x001d
        L_0x004e:
            boolean r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x001d
        L_0x0053:
            boolean r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x001d
        L_0x0058:
            boolean r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x001d
        L_0x005d:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x001d
        L_0x0062:
            android.os.Parcelable$Creator<com.google.android.gms.common.internal.ClientIdentity> r3 = com.google.android.gms.common.internal.ClientIdentity.CREATOR
            java.util.ArrayList r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x001d
        L_0x0069:
            android.os.Parcelable$Creator<com.google.android.gms.location.LocationRequest> r3 = com.google.android.gms.location.LocationRequest.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r8 = r2
            com.google.android.gms.location.LocationRequest r8 = (com.google.android.gms.location.LocationRequest) r8
            goto L_0x001d
        L_0x0073:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.internal.location.zzbc r0 = new com.google.android.gms.internal.location.zzbc
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzbb.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
