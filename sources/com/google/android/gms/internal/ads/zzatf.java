package com.google.android.gms.internal.ads;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzatf implements Parcelable.Creator<zzatc> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzatc[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r17)
            r2 = 0
            r3 = 0
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r13 = r11
            r14 = r13
            r15 = r14
            r12 = 0
        L_0x0013:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x0073
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r17)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x006e;
                case 2: goto L_0x0064;
                case 3: goto L_0x005a;
                case 4: goto L_0x0055;
                case 5: goto L_0x0050;
                case 6: goto L_0x0046;
                case 7: goto L_0x0041;
                case 8: goto L_0x003c;
                case 9: goto L_0x0037;
                case 10: goto L_0x002d;
                case 11: goto L_0x0028;
                default: goto L_0x0024;
            }
        L_0x0024:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0013
        L_0x0028:
            java.lang.String r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0013
        L_0x002d:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzdqg> r3 = com.google.android.gms.internal.ads.zzdqg.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r14 = r2
            com.google.android.gms.internal.ads.zzdqg r14 = (com.google.android.gms.internal.ads.zzdqg) r14
            goto L_0x0013
        L_0x0037:
            java.lang.String r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0013
        L_0x003c:
            boolean r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0013
        L_0x0041:
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0013
        L_0x0046:
            android.os.Parcelable$Creator r3 = android.content.pm.PackageInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r10 = r2
            android.content.pm.PackageInfo r10 = (android.content.pm.PackageInfo) r10
            goto L_0x0013
        L_0x0050:
            java.util.ArrayList r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0013
        L_0x0055:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0013
        L_0x005a:
            android.os.Parcelable$Creator r3 = android.content.pm.ApplicationInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r7 = r2
            android.content.pm.ApplicationInfo r7 = (android.content.pm.ApplicationInfo) r7
            goto L_0x0013
        L_0x0064:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzbbx> r3 = com.google.android.gms.internal.ads.zzbbx.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r6 = r2
            com.google.android.gms.internal.ads.zzbbx r6 = (com.google.android.gms.internal.ads.zzbbx) r6
            goto L_0x0013
        L_0x006e:
            android.os.Bundle r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0013
        L_0x0073:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.internal.ads.zzatc r0 = new com.google.android.gms.internal.ads.zzatc
            r4 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzatf.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
