package com.google.android.gms.internal.ads;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzadt implements Parcelable.Creator<zzadu> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzadu[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r13) {
        /*
            r12 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r13)
            r1 = 0
            r2 = 0
            r9 = r2
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r10 = 0
            r11 = 0
        L_0x000e:
            int r1 = r13.dataPosition()
            if (r1 >= r0) goto L_0x0050
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r13)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x004b;
                case 2: goto L_0x0046;
                case 3: goto L_0x0041;
                case 4: goto L_0x003c;
                case 5: goto L_0x0037;
                case 6: goto L_0x002d;
                case 7: goto L_0x0028;
                case 8: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r13, r1)
            goto L_0x000e
        L_0x0023:
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r13, r1)
            goto L_0x000e
        L_0x0028:
            boolean r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r1)
            goto L_0x000e
        L_0x002d:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzaak> r2 = com.google.android.gms.internal.ads.zzaak.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r13, r1, r2)
            r9 = r1
            com.google.android.gms.internal.ads.zzaak r9 = (com.google.android.gms.internal.ads.zzaak) r9
            goto L_0x000e
        L_0x0037:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r13, r1)
            goto L_0x000e
        L_0x003c:
            boolean r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r1)
            goto L_0x000e
        L_0x0041:
            int r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r13, r1)
            goto L_0x000e
        L_0x0046:
            boolean r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r13, r1)
            goto L_0x000e
        L_0x004b:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r13, r1)
            goto L_0x000e
        L_0x0050:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r13, r0)
            com.google.android.gms.internal.ads.zzadu r13 = new com.google.android.gms.internal.ads.zzadu
            r3 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzadt.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
