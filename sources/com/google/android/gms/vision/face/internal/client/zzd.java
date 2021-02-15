package com.google.android.gms.vision.face.internal.client;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class zzd implements Parcelable.Creator<FaceParcel> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new FaceParcel[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r24) {
        /*
            r23 = this;
            r0 = r24
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r24)
            r2 = 0
            r3 = 0
            r4 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r5 = 0
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r17 = r2
            r21 = r17
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r15 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r16 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r18 = 0
            r19 = 0
            r20 = 0
            r22 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x0029:
            int r2 = r24.dataPosition()
            if (r2 >= r1) goto L_0x0095
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r24)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x0090;
                case 2: goto L_0x008b;
                case 3: goto L_0x0086;
                case 4: goto L_0x0081;
                case 5: goto L_0x007c;
                case 6: goto L_0x0077;
                case 7: goto L_0x0072;
                case 8: goto L_0x006d;
                case 9: goto L_0x0062;
                case 10: goto L_0x005d;
                case 11: goto L_0x0058;
                case 12: goto L_0x0053;
                case 13: goto L_0x0048;
                case 14: goto L_0x0043;
                case 15: goto L_0x003e;
                default: goto L_0x003a;
            }
        L_0x003a:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0029
        L_0x003e:
            float r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0043:
            float r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0048:
            android.os.Parcelable$Creator<com.google.android.gms.vision.face.internal.client.zza> r3 = com.google.android.gms.vision.face.internal.client.zza.CREATOR
            java.lang.Object[] r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r0, r2, r3)
            r21 = r2
            com.google.android.gms.vision.face.internal.client.zza[] r21 = (com.google.android.gms.vision.face.internal.client.zza[]) r21
            goto L_0x0029
        L_0x0053:
            float r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0058:
            float r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x005d:
            float r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0062:
            android.os.Parcelable$Creator<com.google.android.gms.vision.face.internal.client.LandmarkParcel> r3 = com.google.android.gms.vision.face.internal.client.LandmarkParcel.CREATOR
            java.lang.Object[] r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedArray(r0, r2, r3)
            r17 = r2
            com.google.android.gms.vision.face.internal.client.LandmarkParcel[] r17 = (com.google.android.gms.vision.face.internal.client.LandmarkParcel[]) r17
            goto L_0x0029
        L_0x006d:
            float r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0072:
            float r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0077:
            float r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x007c:
            float r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0081:
            float r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x0086:
            float r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0029
        L_0x008b:
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0029
        L_0x0090:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0029
        L_0x0095:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.vision.face.internal.client.FaceParcel r0 = new com.google.android.gms.vision.face.internal.client.FaceParcel
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.face.internal.client.zzd.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
