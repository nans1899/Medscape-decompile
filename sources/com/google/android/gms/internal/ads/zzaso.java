package com.google.android.gms.internal.ads;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzaso implements Parcelable.Creator<zzasp> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzasp[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r64) {
        /*
            r63 = this;
            r0 = r64
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r64)
            r2 = 0
            r4 = 0
            r5 = 0
            r13 = r2
            r16 = r13
            r19 = r16
            r23 = r19
            r8 = r5
            r9 = r8
            r10 = r9
            r12 = r10
            r18 = r12
            r22 = r18
            r25 = r22
            r27 = r25
            r28 = r27
            r34 = r28
            r35 = r34
            r36 = r35
            r39 = r36
            r40 = r39
            r41 = r40
            r43 = r41
            r45 = r43
            r46 = r45
            r48 = r46
            r49 = r48
            r50 = r49
            r53 = r50
            r57 = r53
            r59 = r57
            r60 = r59
            r7 = 0
            r11 = 0
            r15 = 0
            r21 = 0
            r26 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r37 = 0
            r38 = 0
            r42 = 0
            r44 = 0
            r47 = 0
            r51 = 0
            r52 = 0
            r54 = 0
            r55 = 0
            r56 = 0
            r58 = 0
            r61 = 0
            r62 = 0
        L_0x006a:
            int r2 = r64.dataPosition()
            if (r2 >= r1) goto L_0x01bc
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r64)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x01b6;
                case 2: goto L_0x01b0;
                case 3: goto L_0x01aa;
                case 4: goto L_0x01a4;
                case 5: goto L_0x019e;
                case 6: goto L_0x0198;
                case 7: goto L_0x0192;
                case 8: goto L_0x018c;
                case 9: goto L_0x0186;
                case 10: goto L_0x0180;
                case 11: goto L_0x017a;
                case 12: goto L_0x0174;
                case 13: goto L_0x016e;
                case 14: goto L_0x0168;
                case 15: goto L_0x0162;
                case 16: goto L_0x007b;
                case 17: goto L_0x007b;
                case 18: goto L_0x015c;
                case 19: goto L_0x0156;
                case 20: goto L_0x007b;
                case 21: goto L_0x0150;
                case 22: goto L_0x014a;
                case 23: goto L_0x0144;
                case 24: goto L_0x013e;
                case 25: goto L_0x0138;
                case 26: goto L_0x0132;
                case 27: goto L_0x007b;
                case 28: goto L_0x0126;
                case 29: goto L_0x0120;
                case 30: goto L_0x011a;
                case 31: goto L_0x0114;
                case 32: goto L_0x010e;
                case 33: goto L_0x0102;
                case 34: goto L_0x00fc;
                case 35: goto L_0x00f6;
                case 36: goto L_0x00f0;
                case 37: goto L_0x00e4;
                case 38: goto L_0x00df;
                case 39: goto L_0x00da;
                case 40: goto L_0x00d5;
                case 41: goto L_0x007b;
                case 42: goto L_0x00d0;
                case 43: goto L_0x00cb;
                case 44: goto L_0x00c0;
                case 45: goto L_0x00bb;
                case 46: goto L_0x00b6;
                case 47: goto L_0x00b1;
                case 48: goto L_0x00ac;
                case 49: goto L_0x00a7;
                case 50: goto L_0x00a2;
                case 51: goto L_0x009d;
                case 52: goto L_0x0098;
                case 53: goto L_0x0093;
                case 54: goto L_0x008e;
                case 55: goto L_0x0089;
                case 56: goto L_0x0084;
                case 57: goto L_0x007f;
                default: goto L_0x007b;
            }
        L_0x007b:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x006a
        L_0x007f:
            boolean r62 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0084:
            boolean r61 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0089:
            java.lang.String r60 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x008e:
            java.lang.String r59 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x0093:
            boolean r58 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0098:
            java.util.ArrayList r57 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x006a
        L_0x009d:
            boolean r56 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x00a2:
            int r55 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x006a
        L_0x00a7:
            boolean r54 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x00ac:
            android.os.Bundle r53 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x006a
        L_0x00b1:
            boolean r52 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x00b6:
            boolean r51 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x00bb:
            java.lang.String r50 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x00c0:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzawg> r3 = com.google.android.gms.internal.ads.zzawg.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r49 = r2
            com.google.android.gms.internal.ads.zzawg r49 = (com.google.android.gms.internal.ads.zzawg) r49
            goto L_0x006a
        L_0x00cb:
            java.lang.String r48 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x00d0:
            boolean r47 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x00d5:
            java.util.ArrayList r46 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x006a
        L_0x00da:
            java.lang.String r45 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x00df:
            boolean r44 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x00e4:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzasr> r3 = com.google.android.gms.internal.ads.zzasr.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r43 = r2
            com.google.android.gms.internal.ads.zzasr r43 = (com.google.android.gms.internal.ads.zzasr) r43
            goto L_0x006a
        L_0x00f0:
            boolean r42 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x00f6:
            java.util.ArrayList r41 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x006a
        L_0x00fc:
            java.util.ArrayList r40 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x006a
        L_0x0102:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzauv> r3 = com.google.android.gms.internal.ads.zzauv.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r39 = r2
            com.google.android.gms.internal.ads.zzauv r39 = (com.google.android.gms.internal.ads.zzauv) r39
            goto L_0x006a
        L_0x010e:
            boolean r38 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0114:
            boolean r37 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x011a:
            java.lang.String r36 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x0120:
            java.lang.String r35 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x0126:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzatb> r3 = com.google.android.gms.internal.ads.zzatb.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r34 = r2
            com.google.android.gms.internal.ads.zzatb r34 = (com.google.android.gms.internal.ads.zzatb) r34
            goto L_0x006a
        L_0x0132:
            boolean r33 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0138:
            boolean r32 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x013e:
            boolean r31 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0144:
            boolean r30 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x014a:
            boolean r29 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0150:
            java.lang.String r28 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x0156:
            java.lang.String r27 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x015c:
            boolean r26 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0162:
            java.lang.String r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x0168:
            long r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x006a
        L_0x016e:
            java.lang.String r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x0174:
            int r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x006a
        L_0x017a:
            long r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x006a
        L_0x0180:
            java.util.ArrayList r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x006a
        L_0x0186:
            long r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x006a
        L_0x018c:
            boolean r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x006a
        L_0x0192:
            long r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x006a
        L_0x0198:
            java.util.ArrayList r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x006a
        L_0x019e:
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x006a
        L_0x01a4:
            java.util.ArrayList r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x006a
        L_0x01aa:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x01b0:
            java.lang.String r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x006a
        L_0x01b6:
            int r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x006a
        L_0x01bc:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.internal.ads.zzasp r0 = new com.google.android.gms.internal.ads.zzasp
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r15, r16, r18, r19, r21, r22, r23, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaso.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
