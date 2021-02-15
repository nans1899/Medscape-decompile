package com.google.android.gms.internal.ads;

import android.os.Parcelable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzasm implements Parcelable.Creator<zzasn> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzasn[i];
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v6, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v7, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v8, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v9, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v10, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r70) {
        /*
            r69 = this;
            r0 = r70
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r70)
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r28 = r2
            r35 = r28
            r9 = r6
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r19 = r18
            r21 = r19
            r22 = r21
            r27 = r22
            r30 = r27
            r31 = r30
            r32 = r31
            r33 = r32
            r34 = r33
            r37 = r34
            r44 = r37
            r45 = r44
            r48 = r45
            r49 = r48
            r50 = r49
            r52 = r50
            r53 = r52
            r54 = r53
            r55 = r54
            r57 = r55
            r58 = r57
            r59 = r58
            r64 = r59
            r65 = r64
            r66 = r65
            r67 = r66
            r68 = r67
            r8 = 0
            r20 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r38 = 0
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 0
            r46 = 0
            r47 = 0
            r51 = 0
            r56 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
        L_0x0079:
            int r2 = r70.dataPosition()
            if (r2 >= r1) goto L_0x0209
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r70)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 1: goto L_0x0203;
                case 2: goto L_0x01fd;
                case 3: goto L_0x01f2;
                case 4: goto L_0x01e7;
                case 5: goto L_0x01e1;
                case 6: goto L_0x01d6;
                case 7: goto L_0x01cb;
                case 8: goto L_0x01c5;
                case 9: goto L_0x01bf;
                case 10: goto L_0x01b9;
                case 11: goto L_0x01ad;
                case 12: goto L_0x01a7;
                case 13: goto L_0x01a1;
                case 14: goto L_0x019b;
                case 15: goto L_0x0195;
                case 16: goto L_0x018f;
                case 17: goto L_0x008a;
                case 18: goto L_0x0189;
                case 19: goto L_0x0183;
                case 20: goto L_0x017d;
                case 21: goto L_0x0177;
                case 22: goto L_0x008a;
                case 23: goto L_0x008a;
                case 24: goto L_0x008a;
                case 25: goto L_0x0171;
                case 26: goto L_0x016b;
                case 27: goto L_0x0165;
                case 28: goto L_0x015f;
                case 29: goto L_0x0153;
                case 30: goto L_0x014d;
                case 31: goto L_0x0147;
                case 32: goto L_0x008a;
                case 33: goto L_0x0141;
                case 34: goto L_0x013b;
                case 35: goto L_0x0135;
                case 36: goto L_0x012f;
                case 37: goto L_0x0129;
                case 38: goto L_0x0123;
                case 39: goto L_0x011d;
                case 40: goto L_0x0117;
                case 41: goto L_0x0111;
                case 42: goto L_0x010b;
                case 43: goto L_0x0105;
                case 44: goto L_0x00ff;
                case 45: goto L_0x00f9;
                case 46: goto L_0x00ee;
                case 47: goto L_0x00e9;
                case 48: goto L_0x00e4;
                case 49: goto L_0x00df;
                case 50: goto L_0x00da;
                case 51: goto L_0x00d5;
                case 52: goto L_0x00d0;
                case 53: goto L_0x00cb;
                case 54: goto L_0x00c6;
                case 55: goto L_0x00c1;
                case 56: goto L_0x00bc;
                case 57: goto L_0x00b7;
                case 58: goto L_0x00b2;
                case 59: goto L_0x00ad;
                case 60: goto L_0x00a8;
                case 61: goto L_0x00a3;
                case 62: goto L_0x008a;
                case 63: goto L_0x0098;
                case 64: goto L_0x0093;
                case 65: goto L_0x008e;
                default: goto L_0x008a;
            }
        L_0x008a:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0079
        L_0x008e:
            android.os.Bundle r68 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0079
        L_0x0093:
            java.lang.String r67 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x0098:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzajc> r3 = com.google.android.gms.internal.ads.zzajc.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r66 = r2
            com.google.android.gms.internal.ads.zzajc r66 = (com.google.android.gms.internal.ads.zzajc) r66
            goto L_0x0079
        L_0x00a3:
            java.lang.String r65 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x00a8:
            java.util.ArrayList r64 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0079
        L_0x00ad:
            boolean r63 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x00b2:
            boolean r62 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x00b7:
            boolean r61 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x00bc:
            int r60 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x00c1:
            java.util.ArrayList r59 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0079
        L_0x00c6:
            java.lang.String r58 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x00cb:
            java.util.ArrayList r57 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createIntegerList(r0, r2)
            goto L_0x0079
        L_0x00d0:
            boolean r56 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x00d5:
            java.lang.String r55 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x00da:
            java.lang.String r54 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x00df:
            java.lang.String r53 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x00e4:
            android.os.Bundle r52 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0079
        L_0x00e9:
            boolean r51 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x00ee:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzyu> r3 = com.google.android.gms.internal.ads.zzyu.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r50 = r2
            com.google.android.gms.internal.ads.zzyu r50 = (com.google.android.gms.internal.ads.zzyu) r50
            goto L_0x0079
        L_0x00f9:
            java.lang.String r49 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x00ff:
            android.os.Bundle r48 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0079
        L_0x0105:
            int r47 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x010b:
            boolean r46 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x0111:
            java.lang.String r45 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x0117:
            boolean r39 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x011d:
            java.lang.String r44 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x0123:
            boolean r43 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x0129:
            boolean r42 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x012f:
            int r41 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x0135:
            int r40 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x013b:
            float r38 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0079
        L_0x0141:
            java.lang.String r37 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x0147:
            long r35 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0079
        L_0x014d:
            java.util.ArrayList r34 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0079
        L_0x0153:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzadu> r3 = com.google.android.gms.internal.ads.zzadu.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r33 = r2
            com.google.android.gms.internal.ads.zzadu r33 = (com.google.android.gms.internal.ads.zzadu) r33
            goto L_0x0079
        L_0x015f:
            java.lang.String r32 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x0165:
            java.util.ArrayList r31 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0079
        L_0x016b:
            java.lang.String r30 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x0171:
            long r28 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0079
        L_0x0177:
            java.lang.String r27 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x017d:
            float r26 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readFloat(r0, r2)
            goto L_0x0079
        L_0x0183:
            int r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x0189:
            int r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x018f:
            boolean r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0079
        L_0x0195:
            android.os.Bundle r22 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0079
        L_0x019b:
            java.util.ArrayList r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0079
        L_0x01a1:
            int r20 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x01a7:
            android.os.Bundle r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0079
        L_0x01ad:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzbbx> r3 = com.google.android.gms.internal.ads.zzbbx.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r18 = r2
            com.google.android.gms.internal.ads.zzbbx r18 = (com.google.android.gms.internal.ads.zzbbx) r18
            goto L_0x0079
        L_0x01b9:
            java.lang.String r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x01bf:
            java.lang.String r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x01c5:
            java.lang.String r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x01cb:
            android.os.Parcelable$Creator r3 = android.content.pm.PackageInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r14 = r2
            android.content.pm.PackageInfo r14 = (android.content.pm.PackageInfo) r14
            goto L_0x0079
        L_0x01d6:
            android.os.Parcelable$Creator r3 = android.content.pm.ApplicationInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r13 = r2
            android.content.pm.ApplicationInfo r13 = (android.content.pm.ApplicationInfo) r13
            goto L_0x0079
        L_0x01e1:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0079
        L_0x01e7:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvn> r3 = com.google.android.gms.internal.ads.zzvn.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r11 = r2
            com.google.android.gms.internal.ads.zzvn r11 = (com.google.android.gms.internal.ads.zzvn) r11
            goto L_0x0079
        L_0x01f2:
            android.os.Parcelable$Creator<com.google.android.gms.internal.ads.zzvg> r3 = com.google.android.gms.internal.ads.zzvg.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r10 = r2
            com.google.android.gms.internal.ads.zzvg r10 = (com.google.android.gms.internal.ads.zzvg) r10
            goto L_0x0079
        L_0x01fd:
            android.os.Bundle r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r0, r2)
            goto L_0x0079
        L_0x0203:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0079
        L_0x0209:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.internal.ads.zzasn r0 = new com.google.android.gms.internal.ads.zzasn
            r7 = r0
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r30, r31, r32, r33, r34, r35, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzasm.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
