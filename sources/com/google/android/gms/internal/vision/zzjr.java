package com.google.android.gms.internal.vision;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzjr<T> implements zzkf<T> {
    private static final int[] zzaai = new int[0];
    private static final Unsafe zzaaj = zzld.zzjg();
    private final int[] zzaak;
    private final Object[] zzaal;
    private final int zzaam;
    private final int zzaan;
    private final zzjn zzaao;
    private final boolean zzaap;
    private final boolean zzaaq;
    private final boolean zzaar;
    private final boolean zzaas;
    private final int[] zzaat;
    private final int zzaau;
    private final int zzaav;
    private final zzjv zzaaw;
    private final zzix zzaax;
    private final zzkx<?, ?> zzaay;
    private final zzhq<?> zzaaz;
    private final zzjg zzaba;

    private zzjr(int[] iArr, Object[] objArr, int i, int i2, zzjn zzjn, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzjv zzjv, zzix zzix, zzkx<?, ?> zzkx, zzhq<?> zzhq, zzjg zzjg) {
        this.zzaak = iArr;
        this.zzaal = objArr;
        this.zzaam = i;
        this.zzaan = i2;
        this.zzaaq = zzjn instanceof zzid;
        this.zzaar = z;
        this.zzaap = zzhq != null && zzhq.zze(zzjn);
        this.zzaas = false;
        this.zzaat = iArr2;
        this.zzaau = i3;
        this.zzaav = i4;
        this.zzaaw = zzjv;
        this.zzaax = zzix;
        this.zzaay = zzkx;
        this.zzaaz = zzhq;
        this.zzaao = zzjn;
        this.zzaba = zzjg;
    }

    private static boolean zzbz(int i) {
        return (i & 536870912) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:159:0x033e  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x038c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> com.google.android.gms.internal.vision.zzjr<T> zza(java.lang.Class<T> r33, com.google.android.gms.internal.vision.zzjl r34, com.google.android.gms.internal.vision.zzjv r35, com.google.android.gms.internal.vision.zzix r36, com.google.android.gms.internal.vision.zzkx<?, ?> r37, com.google.android.gms.internal.vision.zzhq<?> r38, com.google.android.gms.internal.vision.zzjg r39) {
        /*
            r0 = r34
            boolean r1 = r0 instanceof com.google.android.gms.internal.vision.zzkd
            if (r1 == 0) goto L_0x040f
            com.google.android.gms.internal.vision.zzkd r0 = (com.google.android.gms.internal.vision.zzkd) r0
            int r1 = r0.zzid()
            int r2 = com.google.android.gms.internal.vision.zzjy.zzabe
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L_0x0014
            r11 = 1
            goto L_0x0015
        L_0x0014:
            r11 = 0
        L_0x0015:
            java.lang.String r1 = r0.zzil()
            int r2 = r1.length()
            char r5 = r1.charAt(r3)
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r6) goto L_0x0031
            r5 = 1
        L_0x0027:
            int r7 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x0032
            r5 = r7
            goto L_0x0027
        L_0x0031:
            r7 = 1
        L_0x0032:
            int r5 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x0051
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x003e:
            int r10 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x004e
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            int r5 = r5 << r9
            r7 = r7 | r5
            int r9 = r9 + 13
            r5 = r10
            goto L_0x003e
        L_0x004e:
            int r5 = r5 << r9
            r7 = r7 | r5
            r5 = r10
        L_0x0051:
            if (r7 != 0) goto L_0x005e
            int[] r7 = zzaai
            r14 = r7
            r7 = 0
            r9 = 0
            r10 = 0
            r12 = 0
            r13 = 0
            r15 = 0
            goto L_0x016f
        L_0x005e:
            int r7 = r5 + 1
            char r5 = r1.charAt(r5)
            if (r5 < r6) goto L_0x007d
            r5 = r5 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x006a:
            int r10 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x007a
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r9
            r5 = r5 | r7
            int r9 = r9 + 13
            r7 = r10
            goto L_0x006a
        L_0x007a:
            int r7 = r7 << r9
            r5 = r5 | r7
            r7 = r10
        L_0x007d:
            int r9 = r7 + 1
            char r7 = r1.charAt(r7)
            if (r7 < r6) goto L_0x009c
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0089:
            int r12 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r6) goto L_0x0099
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r7 = r7 | r9
            int r10 = r10 + 13
            r9 = r12
            goto L_0x0089
        L_0x0099:
            int r9 = r9 << r10
            r7 = r7 | r9
            r9 = r12
        L_0x009c:
            int r10 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r6) goto L_0x00bb
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00a8:
            int r13 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r6) goto L_0x00b8
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r12
            r9 = r9 | r10
            int r12 = r12 + 13
            r10 = r13
            goto L_0x00a8
        L_0x00b8:
            int r10 = r10 << r12
            r9 = r9 | r10
            r10 = r13
        L_0x00bb:
            int r12 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r6) goto L_0x00da
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00c7:
            int r14 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r6) goto L_0x00d7
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r10 = r10 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00c7
        L_0x00d7:
            int r12 = r12 << r13
            r10 = r10 | r12
            r12 = r14
        L_0x00da:
            int r13 = r12 + 1
            char r12 = r1.charAt(r12)
            if (r12 < r6) goto L_0x00f9
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x00e6:
            int r15 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r6) goto L_0x00f6
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x00e6
        L_0x00f6:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x00f9:
            int r14 = r13 + 1
            char r13 = r1.charAt(r13)
            if (r13 < r6) goto L_0x011a
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0105:
            int r16 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r6) goto L_0x0116
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x0105
        L_0x0116:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x011a:
            int r15 = r14 + 1
            char r14 = r1.charAt(r14)
            if (r14 < r6) goto L_0x013d
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            r16 = 13
        L_0x0126:
            int r17 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0138
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            int r15 = r15 << r16
            r14 = r14 | r15
            int r16 = r16 + 13
            r15 = r17
            goto L_0x0126
        L_0x0138:
            int r15 = r15 << r16
            r14 = r14 | r15
            r15 = r17
        L_0x013d:
            int r16 = r15 + 1
            char r15 = r1.charAt(r15)
            if (r15 < r6) goto L_0x0162
            r15 = r15 & 8191(0x1fff, float:1.1478E-41)
            r3 = r16
            r16 = 13
        L_0x014b:
            int r17 = r3 + 1
            char r3 = r1.charAt(r3)
            if (r3 < r6) goto L_0x015d
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r16
            r15 = r15 | r3
            int r16 = r16 + 13
            r3 = r17
            goto L_0x014b
        L_0x015d:
            int r3 = r3 << r16
            r15 = r15 | r3
            r16 = r17
        L_0x0162:
            int r3 = r15 + r13
            int r3 = r3 + r14
            int[] r3 = new int[r3]
            int r14 = r5 << 1
            int r14 = r14 + r7
            r7 = r14
            r14 = r3
            r3 = r5
            r5 = r16
        L_0x016f:
            sun.misc.Unsafe r8 = zzaaj
            java.lang.Object[] r16 = r0.zzim()
            com.google.android.gms.internal.vision.zzjn r17 = r0.zzif()
            java.lang.Class r6 = r17.getClass()
            r17 = r5
            int r5 = r12 * 3
            int[] r5 = new int[r5]
            int r12 = r12 << r4
            java.lang.Object[] r12 = new java.lang.Object[r12]
            int r19 = r15 + r13
            r13 = r7
            r21 = r15
            r7 = r17
            r22 = r19
            r17 = 0
            r20 = 0
        L_0x0193:
            if (r7 >= r2) goto L_0x03e3
            int r23 = r7 + 1
            char r7 = r1.charAt(r7)
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r7 < r4) goto L_0x01c5
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r4 = r23
            r23 = 13
        L_0x01a6:
            int r25 = r4 + 1
            char r4 = r1.charAt(r4)
            r26 = r2
            r2 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r2) goto L_0x01bf
            r2 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r2 = r2 << r23
            r7 = r7 | r2
            int r23 = r23 + 13
            r4 = r25
            r2 = r26
            goto L_0x01a6
        L_0x01bf:
            int r2 = r4 << r23
            r7 = r7 | r2
            r2 = r25
            goto L_0x01c9
        L_0x01c5:
            r26 = r2
            r2 = r23
        L_0x01c9:
            int r4 = r2 + 1
            char r2 = r1.charAt(r2)
            r23 = r4
            r4 = 55296(0xd800, float:7.7486E-41)
            if (r2 < r4) goto L_0x01fb
            r2 = r2 & 8191(0x1fff, float:1.1478E-41)
            r4 = r23
            r23 = 13
        L_0x01dc:
            int r25 = r4 + 1
            char r4 = r1.charAt(r4)
            r27 = r15
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r15) goto L_0x01f5
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r23
            r2 = r2 | r4
            int r23 = r23 + 13
            r4 = r25
            r15 = r27
            goto L_0x01dc
        L_0x01f5:
            int r4 = r4 << r23
            r2 = r2 | r4
            r4 = r25
            goto L_0x01ff
        L_0x01fb:
            r27 = r15
            r4 = r23
        L_0x01ff:
            r15 = r2 & 255(0xff, float:3.57E-43)
            r23 = r10
            r10 = r2 & 1024(0x400, float:1.435E-42)
            if (r10 == 0) goto L_0x020d
            int r10 = r17 + 1
            r14[r17] = r20
            r17 = r10
        L_0x020d:
            r10 = 51
            r29 = r9
            if (r15 < r10) goto L_0x02aa
            int r10 = r4 + 1
            char r4 = r1.charAt(r4)
            r9 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r9) goto L_0x023c
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r31 = 13
        L_0x0222:
            int r32 = r10 + 1
            char r10 = r1.charAt(r10)
            if (r10 < r9) goto L_0x0237
            r9 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r31
            r4 = r4 | r9
            int r31 = r31 + 13
            r10 = r32
            r9 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0222
        L_0x0237:
            int r9 = r10 << r31
            r4 = r4 | r9
            r10 = r32
        L_0x023c:
            int r9 = r15 + -51
            r31 = r10
            r10 = 9
            if (r9 == r10) goto L_0x025d
            r10 = 17
            if (r9 != r10) goto L_0x0249
            goto L_0x025d
        L_0x0249:
            r10 = 12
            if (r9 != r10) goto L_0x025b
            if (r11 != 0) goto L_0x025b
            int r9 = r20 / 3
            r10 = 1
            int r9 = r9 << r10
            int r9 = r9 + r10
            int r10 = r13 + 1
            r13 = r16[r13]
            r12[r9] = r13
            r13 = r10
        L_0x025b:
            r10 = 1
            goto L_0x026a
        L_0x025d:
            int r9 = r20 / 3
            r10 = 1
            int r9 = r9 << r10
            int r9 = r9 + r10
            int r24 = r13 + 1
            r13 = r16[r13]
            r12[r9] = r13
            r13 = r24
        L_0x026a:
            int r4 = r4 << r10
            r9 = r16[r4]
            boolean r10 = r9 instanceof java.lang.reflect.Field
            if (r10 == 0) goto L_0x0274
            java.lang.reflect.Field r9 = (java.lang.reflect.Field) r9
            goto L_0x027c
        L_0x0274:
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = zza((java.lang.Class<?>) r6, (java.lang.String) r9)
            r16[r4] = r9
        L_0x027c:
            long r9 = r8.objectFieldOffset(r9)
            int r10 = (int) r9
            int r4 = r4 + 1
            r9 = r16[r4]
            r25 = r10
            boolean r10 = r9 instanceof java.lang.reflect.Field
            if (r10 == 0) goto L_0x028e
            java.lang.reflect.Field r9 = (java.lang.reflect.Field) r9
            goto L_0x0296
        L_0x028e:
            java.lang.String r9 = (java.lang.String) r9
            java.lang.reflect.Field r9 = zza((java.lang.Class<?>) r6, (java.lang.String) r9)
            r16[r4] = r9
        L_0x0296:
            long r9 = r8.objectFieldOffset(r9)
            int r4 = (int) r9
            r30 = r1
            r9 = r4
            r1 = r11
            r24 = r12
            r10 = r25
            r11 = r31
            r4 = 0
            r18 = 1
            goto L_0x03a8
        L_0x02aa:
            int r9 = r13 + 1
            r10 = r16[r13]
            java.lang.String r10 = (java.lang.String) r10
            java.lang.reflect.Field r10 = zza((java.lang.Class<?>) r6, (java.lang.String) r10)
            r13 = 9
            if (r15 == r13) goto L_0x031e
            r13 = 17
            if (r15 != r13) goto L_0x02bd
            goto L_0x031e
        L_0x02bd:
            r13 = 27
            if (r15 == r13) goto L_0x030d
            r13 = 49
            if (r15 != r13) goto L_0x02c6
            goto L_0x030d
        L_0x02c6:
            r13 = 12
            if (r15 == r13) goto L_0x02f9
            r13 = 30
            if (r15 == r13) goto L_0x02f9
            r13 = 44
            if (r15 != r13) goto L_0x02d3
            goto L_0x02f9
        L_0x02d3:
            r13 = 50
            if (r15 != r13) goto L_0x032c
            int r13 = r21 + 1
            r14[r21] = r20
            int r21 = r20 / 3
            r24 = 1
            int r21 = r21 << 1
            int r25 = r9 + 1
            r9 = r16[r9]
            r12[r21] = r9
            r9 = r2 & 2048(0x800, float:2.87E-42)
            if (r9 == 0) goto L_0x02f6
            int r21 = r21 + 1
            int r9 = r25 + 1
            r25 = r16[r25]
            r12[r21] = r25
            r21 = r13
            goto L_0x032c
        L_0x02f6:
            r21 = r13
            goto L_0x031b
        L_0x02f9:
            if (r11 != 0) goto L_0x030a
            int r13 = r20 / 3
            r24 = 1
            int r13 = r13 << 1
            int r13 = r13 + 1
            int r25 = r9 + 1
            r9 = r16[r9]
            r12[r13] = r9
            goto L_0x031b
        L_0x030a:
            r24 = 1
            goto L_0x032c
        L_0x030d:
            r24 = 1
            int r13 = r20 / 3
            int r13 = r13 << 1
            int r13 = r13 + 1
            int r25 = r9 + 1
            r9 = r16[r9]
            r12[r13] = r9
        L_0x031b:
            r13 = r25
            goto L_0x032d
        L_0x031e:
            r24 = 1
            int r13 = r20 / 3
            int r13 = r13 << 1
            int r13 = r13 + 1
            java.lang.Class r25 = r10.getType()
            r12[r13] = r25
        L_0x032c:
            r13 = r9
        L_0x032d:
            long r9 = r8.objectFieldOffset(r10)
            int r10 = (int) r9
            r9 = r2 & 4096(0x1000, float:5.74E-42)
            r25 = r13
            r13 = 4096(0x1000, float:5.74E-42)
            if (r9 != r13) goto L_0x038c
            r9 = 17
            if (r15 > r9) goto L_0x038c
            int r9 = r4 + 1
            char r4 = r1.charAt(r4)
            r13 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r13) goto L_0x0364
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r18 = 13
        L_0x034d:
            int r28 = r9 + 1
            char r9 = r1.charAt(r9)
            if (r9 < r13) goto L_0x035f
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r18
            r4 = r4 | r9
            int r18 = r18 + 13
            r9 = r28
            goto L_0x034d
        L_0x035f:
            int r9 = r9 << r18
            r4 = r4 | r9
            r9 = r28
        L_0x0364:
            r18 = 1
            int r24 = r3 << 1
            int r28 = r4 / 32
            int r24 = r24 + r28
            r13 = r16[r24]
            r30 = r1
            boolean r1 = r13 instanceof java.lang.reflect.Field
            if (r1 == 0) goto L_0x0377
            java.lang.reflect.Field r13 = (java.lang.reflect.Field) r13
            goto L_0x037f
        L_0x0377:
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = zza((java.lang.Class<?>) r6, (java.lang.String) r13)
            r16[r24] = r13
        L_0x037f:
            r1 = r11
            r24 = r12
            long r11 = r8.objectFieldOffset(r13)
            int r12 = (int) r11
            int r4 = r4 % 32
            r11 = r9
            r9 = r12
            goto L_0x0398
        L_0x038c:
            r30 = r1
            r1 = r11
            r24 = r12
            r18 = 1
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r11 = r4
            r4 = 0
        L_0x0398:
            r12 = 18
            if (r15 < r12) goto L_0x03a6
            r12 = 49
            if (r15 > r12) goto L_0x03a6
            int r12 = r22 + 1
            r14[r22] = r10
            r22 = r12
        L_0x03a6:
            r13 = r25
        L_0x03a8:
            int r12 = r20 + 1
            r5[r20] = r7
            int r7 = r12 + 1
            r20 = r3
            r3 = r2 & 512(0x200, float:7.175E-43)
            if (r3 == 0) goto L_0x03b7
            r3 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x03b8
        L_0x03b7:
            r3 = 0
        L_0x03b8:
            r2 = r2 & 256(0x100, float:3.59E-43)
            if (r2 == 0) goto L_0x03bf
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x03c0
        L_0x03bf:
            r2 = 0
        L_0x03c0:
            r2 = r2 | r3
            int r3 = r15 << 20
            r2 = r2 | r3
            r2 = r2 | r10
            r5[r12] = r2
            int r2 = r7 + 1
            int r3 = r4 << 20
            r3 = r3 | r9
            r5[r7] = r3
            r7 = r11
            r3 = r20
            r10 = r23
            r12 = r24
            r15 = r27
            r9 = r29
            r4 = 1
            r11 = r1
            r20 = r2
            r2 = r26
            r1 = r30
            goto L_0x0193
        L_0x03e3:
            r29 = r9
            r23 = r10
            r1 = r11
            r24 = r12
            r27 = r15
            com.google.android.gms.internal.vision.zzjr r2 = new com.google.android.gms.internal.vision.zzjr
            com.google.android.gms.internal.vision.zzjn r10 = r0.zzif()
            r12 = 0
            r0 = r5
            r5 = r2
            r6 = r0
            r7 = r24
            r8 = r29
            r9 = r23
            r13 = r14
            r14 = r27
            r15 = r19
            r16 = r35
            r17 = r36
            r18 = r37
            r19 = r38
            r20 = r39
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return r2
        L_0x040f:
            com.google.android.gms.internal.vision.zzkq r0 = (com.google.android.gms.internal.vision.zzkq) r0
            int r0 = r0.zzid()
            int r1 = com.google.android.gms.internal.vision.zzjy.zzabe
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Class, com.google.android.gms.internal.vision.zzjl, com.google.android.gms.internal.vision.zzjv, com.google.android.gms.internal.vision.zzix, com.google.android.gms.internal.vision.zzkx, com.google.android.gms.internal.vision.zzhq, com.google.android.gms.internal.vision.zzjg):com.google.android.gms.internal.vision.zzjr");
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    public final T newInstance() {
        return this.zzaaw.newInstance(this.zzaao);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x006a, code lost:
        if (com.google.android.gms.internal.vision.zzkh.zze(com.google.android.gms.internal.vision.zzld.zzp(r10, r6), com.google.android.gms.internal.vision.zzld.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x007e, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzl(r10, r6) == com.google.android.gms.internal.vision.zzld.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0090, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzk(r10, r6) == com.google.android.gms.internal.vision.zzld.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzl(r10, r6) == com.google.android.gms.internal.vision.zzld.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00b6, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzk(r10, r6) == com.google.android.gms.internal.vision.zzld.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c8, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzk(r10, r6) == com.google.android.gms.internal.vision.zzld.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00da, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzk(r10, r6) == com.google.android.gms.internal.vision.zzld.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f0, code lost:
        if (com.google.android.gms.internal.vision.zzkh.zze(com.google.android.gms.internal.vision.zzld.zzp(r10, r6), com.google.android.gms.internal.vision.zzld.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0106, code lost:
        if (com.google.android.gms.internal.vision.zzkh.zze(com.google.android.gms.internal.vision.zzld.zzp(r10, r6), com.google.android.gms.internal.vision.zzld.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x011c, code lost:
        if (com.google.android.gms.internal.vision.zzkh.zze(com.google.android.gms.internal.vision.zzld.zzp(r10, r6), com.google.android.gms.internal.vision.zzld.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012e, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzm(r10, r6) == com.google.android.gms.internal.vision.zzld.zzm(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0140, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzk(r10, r6) == com.google.android.gms.internal.vision.zzld.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0154, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzl(r10, r6) == com.google.android.gms.internal.vision.zzld.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0165, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzk(r10, r6) == com.google.android.gms.internal.vision.zzld.zzk(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0178, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzl(r10, r6) == com.google.android.gms.internal.vision.zzld.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018b, code lost:
        if (com.google.android.gms.internal.vision.zzld.zzl(r10, r6) == com.google.android.gms.internal.vision.zzld.zzl(r11, r6)) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01a4, code lost:
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zzld.zzn(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.vision.zzld.zzn(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01bf, code lost:
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zzld.zzo(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.vision.zzld.zzo(r11, r6))) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01c1, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
        if (com.google.android.gms.internal.vision.zzkh.zze(com.google.android.gms.internal.vision.zzld.zzp(r10, r6), com.google.android.gms.internal.vision.zzld.zzp(r11, r6)) != false) goto L_0x01c2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r10, T r11) {
        /*
            r9 = this;
            int[] r0 = r9.zzaak
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            r3 = 1
            if (r2 >= r0) goto L_0x01c9
            int r4 = r9.zzbx(r2)
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r6 = r4 & r5
            long r6 = (long) r6
            r8 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = r4 & r8
            int r4 = r4 >>> 20
            switch(r4) {
                case 0: goto L_0x01a7;
                case 1: goto L_0x018e;
                case 2: goto L_0x017b;
                case 3: goto L_0x0168;
                case 4: goto L_0x0157;
                case 5: goto L_0x0144;
                case 6: goto L_0x0132;
                case 7: goto L_0x0120;
                case 8: goto L_0x010a;
                case 9: goto L_0x00f4;
                case 10: goto L_0x00de;
                case 11: goto L_0x00cc;
                case 12: goto L_0x00ba;
                case 13: goto L_0x00a8;
                case 14: goto L_0x0094;
                case 15: goto L_0x0082;
                case 16: goto L_0x006e;
                case 17: goto L_0x0058;
                case 18: goto L_0x004a;
                case 19: goto L_0x004a;
                case 20: goto L_0x004a;
                case 21: goto L_0x004a;
                case 22: goto L_0x004a;
                case 23: goto L_0x004a;
                case 24: goto L_0x004a;
                case 25: goto L_0x004a;
                case 26: goto L_0x004a;
                case 27: goto L_0x004a;
                case 28: goto L_0x004a;
                case 29: goto L_0x004a;
                case 30: goto L_0x004a;
                case 31: goto L_0x004a;
                case 32: goto L_0x004a;
                case 33: goto L_0x004a;
                case 34: goto L_0x004a;
                case 35: goto L_0x004a;
                case 36: goto L_0x004a;
                case 37: goto L_0x004a;
                case 38: goto L_0x004a;
                case 39: goto L_0x004a;
                case 40: goto L_0x004a;
                case 41: goto L_0x004a;
                case 42: goto L_0x004a;
                case 43: goto L_0x004a;
                case 44: goto L_0x004a;
                case 45: goto L_0x004a;
                case 46: goto L_0x004a;
                case 47: goto L_0x004a;
                case 48: goto L_0x004a;
                case 49: goto L_0x004a;
                case 50: goto L_0x003c;
                case 51: goto L_0x001c;
                case 52: goto L_0x001c;
                case 53: goto L_0x001c;
                case 54: goto L_0x001c;
                case 55: goto L_0x001c;
                case 56: goto L_0x001c;
                case 57: goto L_0x001c;
                case 58: goto L_0x001c;
                case 59: goto L_0x001c;
                case 60: goto L_0x001c;
                case 61: goto L_0x001c;
                case 62: goto L_0x001c;
                case 63: goto L_0x001c;
                case 64: goto L_0x001c;
                case 65: goto L_0x001c;
                case 66: goto L_0x001c;
                case 67: goto L_0x001c;
                case 68: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x01c2
        L_0x001c:
            int r4 = r9.zzby(r2)
            r4 = r4 & r5
            long r4 = (long) r4
            int r8 = com.google.android.gms.internal.vision.zzld.zzk(r10, r4)
            int r4 = com.google.android.gms.internal.vision.zzld.zzk(r11, r4)
            if (r8 != r4) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzld.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzkh.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x003c:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzld.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.vision.zzkh.zze(r3, r4)
            goto L_0x01c2
        L_0x004a:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r10, r6)
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzld.zzp(r11, r6)
            boolean r3 = com.google.android.gms.internal.vision.zzkh.zze(r3, r4)
            goto L_0x01c2
        L_0x0058:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzld.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzkh.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x006e:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zzld.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zzld.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0082:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zzld.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0094:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zzld.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zzld.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00a8:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zzld.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00ba:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zzld.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00cc:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zzld.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x00de:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzld.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzkh.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x00f4:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzld.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzkh.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x010a:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzld.zzp(r10, r6)
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r11, r6)
            boolean r4 = com.google.android.gms.internal.vision.zzkh.zze(r4, r5)
            if (r4 != 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0120:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            boolean r4 = com.google.android.gms.internal.vision.zzld.zzm(r10, r6)
            boolean r5 = com.google.android.gms.internal.vision.zzld.zzm(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0132:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zzld.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0144:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zzld.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zzld.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x0157:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            int r4 = com.google.android.gms.internal.vision.zzld.zzk(r10, r6)
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r11, r6)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x0168:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zzld.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zzld.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x017b:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            long r4 = com.google.android.gms.internal.vision.zzld.zzl(r10, r6)
            long r6 = com.google.android.gms.internal.vision.zzld.zzl(r11, r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            goto L_0x01c1
        L_0x018e:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            float r4 = com.google.android.gms.internal.vision.zzld.zzn(r10, r6)
            int r4 = java.lang.Float.floatToIntBits(r4)
            float r5 = com.google.android.gms.internal.vision.zzld.zzn(r11, r6)
            int r5 = java.lang.Float.floatToIntBits(r5)
            if (r4 == r5) goto L_0x01c2
            goto L_0x01c1
        L_0x01a7:
            boolean r4 = r9.zzc(r10, r11, (int) r2)
            if (r4 == 0) goto L_0x01c1
            double r4 = com.google.android.gms.internal.vision.zzld.zzo(r10, r6)
            long r4 = java.lang.Double.doubleToLongBits(r4)
            double r6 = com.google.android.gms.internal.vision.zzld.zzo(r11, r6)
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
        L_0x01c1:
            r3 = 0
        L_0x01c2:
            if (r3 != 0) goto L_0x01c5
            return r1
        L_0x01c5:
            int r2 = r2 + 3
            goto L_0x0005
        L_0x01c9:
            com.google.android.gms.internal.vision.zzkx<?, ?> r0 = r9.zzaay
            java.lang.Object r0 = r0.zzy(r10)
            com.google.android.gms.internal.vision.zzkx<?, ?> r2 = r9.zzaay
            java.lang.Object r2 = r2.zzy(r11)
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x01dc
            return r1
        L_0x01dc:
            boolean r0 = r9.zzaap
            if (r0 == 0) goto L_0x01f1
            com.google.android.gms.internal.vision.zzhq<?> r0 = r9.zzaaz
            com.google.android.gms.internal.vision.zzht r10 = r0.zzh(r10)
            com.google.android.gms.internal.vision.zzhq<?> r0 = r9.zzaaz
            com.google.android.gms.internal.vision.zzht r11 = r0.zzh(r11)
            boolean r10 = r10.equals(r11)
            return r10
        L_0x01f1:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c3, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0227, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0228, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r9) {
        /*
            r8 = this;
            int[] r0 = r8.zzaak
            int r0 = r0.length
            r1 = 0
            r2 = 0
        L_0x0005:
            if (r1 >= r0) goto L_0x022c
            int r3 = r8.zzbx(r1)
            int[] r4 = r8.zzaak
            r4 = r4[r1]
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r5 = r5 & r3
            long r5 = (long) r5
            r7 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = r3 & r7
            int r3 = r3 >>> 20
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0219;
                case 1: goto L_0x020e;
                case 2: goto L_0x0203;
                case 3: goto L_0x01f8;
                case 4: goto L_0x01f1;
                case 5: goto L_0x01e6;
                case 6: goto L_0x01df;
                case 7: goto L_0x01d4;
                case 8: goto L_0x01c7;
                case 9: goto L_0x01b9;
                case 10: goto L_0x01ad;
                case 11: goto L_0x01a5;
                case 12: goto L_0x019d;
                case 13: goto L_0x0195;
                case 14: goto L_0x0189;
                case 15: goto L_0x0181;
                case 16: goto L_0x0175;
                case 17: goto L_0x016a;
                case 18: goto L_0x015e;
                case 19: goto L_0x015e;
                case 20: goto L_0x015e;
                case 21: goto L_0x015e;
                case 22: goto L_0x015e;
                case 23: goto L_0x015e;
                case 24: goto L_0x015e;
                case 25: goto L_0x015e;
                case 26: goto L_0x015e;
                case 27: goto L_0x015e;
                case 28: goto L_0x015e;
                case 29: goto L_0x015e;
                case 30: goto L_0x015e;
                case 31: goto L_0x015e;
                case 32: goto L_0x015e;
                case 33: goto L_0x015e;
                case 34: goto L_0x015e;
                case 35: goto L_0x015e;
                case 36: goto L_0x015e;
                case 37: goto L_0x015e;
                case 38: goto L_0x015e;
                case 39: goto L_0x015e;
                case 40: goto L_0x015e;
                case 41: goto L_0x015e;
                case 42: goto L_0x015e;
                case 43: goto L_0x015e;
                case 44: goto L_0x015e;
                case 45: goto L_0x015e;
                case 46: goto L_0x015e;
                case 47: goto L_0x015e;
                case 48: goto L_0x015e;
                case 49: goto L_0x015e;
                case 50: goto L_0x0152;
                case 51: goto L_0x013c;
                case 52: goto L_0x012a;
                case 53: goto L_0x0118;
                case 54: goto L_0x0106;
                case 55: goto L_0x00f8;
                case 56: goto L_0x00e6;
                case 57: goto L_0x00d8;
                case 58: goto L_0x00c6;
                case 59: goto L_0x00b2;
                case 60: goto L_0x00a0;
                case 61: goto L_0x008e;
                case 62: goto L_0x0080;
                case 63: goto L_0x0072;
                case 64: goto L_0x0064;
                case 65: goto L_0x0052;
                case 66: goto L_0x0044;
                case 67: goto L_0x0032;
                case 68: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x0228
        L_0x0020:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x0032:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x0044:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0052:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x0064:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0072:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0080:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x008e:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00a0:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00b2:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x00c6:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            boolean r3 = zzj(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzm(r3)
            goto L_0x0227
        L_0x00d8:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x00e6:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x00f8:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            int r3 = zzh(r9, r5)
            goto L_0x0227
        L_0x0106:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x0118:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            long r3 = zzi(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x012a:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            float r3 = zzg(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x013c:
            boolean r3 = r8.zzb(r9, (int) r4, (int) r1)
            if (r3 == 0) goto L_0x0228
            int r2 = r2 * 53
            double r3 = zzf(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x0152:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x015e:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x016a:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
            goto L_0x01c3
        L_0x0175:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zzld.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x0181:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zzld.zzk(r9, r5)
            goto L_0x0227
        L_0x0189:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zzld.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x0195:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zzld.zzk(r9, r5)
            goto L_0x0227
        L_0x019d:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zzld.zzk(r9, r5)
            goto L_0x0227
        L_0x01a5:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zzld.zzk(r9, r5)
            goto L_0x0227
        L_0x01ad:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01b9:
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            if (r3 == 0) goto L_0x01c3
            int r7 = r3.hashCode()
        L_0x01c3:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0228
        L_0x01c7:
            int r2 = r2 * 53
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzld.zzp(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x0227
        L_0x01d4:
            int r2 = r2 * 53
            boolean r3 = com.google.android.gms.internal.vision.zzld.zzm(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzm(r3)
            goto L_0x0227
        L_0x01df:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zzld.zzk(r9, r5)
            goto L_0x0227
        L_0x01e6:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zzld.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x01f1:
            int r2 = r2 * 53
            int r3 = com.google.android.gms.internal.vision.zzld.zzk(r9, r5)
            goto L_0x0227
        L_0x01f8:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zzld.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x0203:
            int r2 = r2 * 53
            long r3 = com.google.android.gms.internal.vision.zzld.zzl(r9, r5)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
            goto L_0x0227
        L_0x020e:
            int r2 = r2 * 53
            float r3 = com.google.android.gms.internal.vision.zzld.zzn(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x0227
        L_0x0219:
            int r2 = r2 * 53
            double r3 = com.google.android.gms.internal.vision.zzld.zzo(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = com.google.android.gms.internal.vision.zzie.zzab(r3)
        L_0x0227:
            int r2 = r2 + r3
        L_0x0228:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022c:
            int r2 = r2 * 53
            com.google.android.gms.internal.vision.zzkx<?, ?> r0 = r8.zzaay
            java.lang.Object r0 = r0.zzy(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.zzaap
            if (r0 == 0) goto L_0x024a
            int r2 = r2 * 53
            com.google.android.gms.internal.vision.zzhq<?> r0 = r8.zzaaz
            com.google.android.gms.internal.vision.zzht r9 = r0.zzh(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x024a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.hashCode(java.lang.Object):int");
    }

    public final void zzd(T t, T t2) {
        if (t2 != null) {
            for (int i = 0; i < this.zzaak.length; i += 3) {
                int zzbx = zzbx(i);
                long j = (long) (1048575 & zzbx);
                int i2 = this.zzaak[i];
                switch ((zzbx & 267386880) >>> 20) {
                    case 0:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzo(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 1:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzn(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 2:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzl(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 3:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzl(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 4:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zzb((Object) t, j, zzld.zzk(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 5:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzl(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 6:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zzb((Object) t, j, zzld.zzk(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 7:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzm(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 8:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzp(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 9:
                        zza(t, t2, i);
                        break;
                    case 10:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzp(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 11:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zzb((Object) t, j, zzld.zzk(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 12:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zzb((Object) t, j, zzld.zzk(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 13:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zzb((Object) t, j, zzld.zzk(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 14:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzl(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 15:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zzb((Object) t, j, zzld.zzk(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 16:
                        if (!zzc(t2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzl(t2, j));
                            zzd(t, i);
                            break;
                        }
                    case 17:
                        zza(t, t2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzaax.zza(t, t2, j);
                        break;
                    case 50:
                        zzkh.zza(this.zzaba, t, t2, j);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (!zzb(t2, i2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzp(t2, j));
                            zzc(t, i2, i);
                            break;
                        }
                    case 60:
                        zzb(t, t2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (!zzb(t2, i2, i)) {
                            break;
                        } else {
                            zzld.zza((Object) t, j, zzld.zzp(t2, j));
                            zzc(t, i2, i);
                            break;
                        }
                    case 68:
                        zzb(t, t2, i);
                        break;
                }
            }
            zzkh.zza(this.zzaay, t, t2);
            if (this.zzaap) {
                zzkh.zza(this.zzaaz, t, t2);
                return;
            }
            return;
        }
        throw null;
    }

    private final void zza(T t, T t2, int i) {
        long zzbx = (long) (zzbx(i) & 1048575);
        if (zzc(t2, i)) {
            Object zzp = zzld.zzp(t, zzbx);
            Object zzp2 = zzld.zzp(t2, zzbx);
            if (zzp != null && zzp2 != null) {
                zzld.zza((Object) t, zzbx, zzie.zzb(zzp, zzp2));
                zzd(t, i);
            } else if (zzp2 != null) {
                zzld.zza((Object) t, zzbx, zzp2);
                zzd(t, i);
            }
        }
    }

    private final void zzb(T t, T t2, int i) {
        int zzbx = zzbx(i);
        int i2 = this.zzaak[i];
        long j = (long) (zzbx & 1048575);
        if (zzb(t2, i2, i)) {
            Object zzp = zzld.zzp(t, j);
            Object zzp2 = zzld.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzld.zza((Object) t, j, zzie.zzb(zzp, zzp2));
                zzc(t, i2, i);
            } else if (zzp2 != null) {
                zzld.zza((Object) t, j, zzp2);
                zzc(t, i2, i);
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:303:0x072e, code lost:
        r8 = (r8 + r9) + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:320:0x07fa, code lost:
        r5 = r5 + r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x080f, code lost:
        r13 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0854, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x0902, code lost:
        r5 = r5 + r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x0925, code lost:
        r3 = r3 + 3;
        r9 = r13;
        r7 = 1048575;
        r8 = 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzu(T r19) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            boolean r2 = r0.zzaar
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r9 = 0
            r11 = 0
            if (r2 == 0) goto L_0x047d
            sun.misc.Unsafe r2 = zzaaj
            r12 = 0
            r13 = 0
        L_0x0016:
            int[] r14 = r0.zzaak
            int r14 = r14.length
            if (r12 >= r14) goto L_0x0475
            int r14 = r0.zzbx(r12)
            r15 = r14 & r3
            int r15 = r15 >>> 20
            int[] r3 = r0.zzaak
            r3 = r3[r12]
            r14 = r14 & r7
            long r5 = (long) r14
            com.google.android.gms.internal.vision.zzhy r14 = com.google.android.gms.internal.vision.zzhy.DOUBLE_LIST_PACKED
            int r14 = r14.id()
            if (r15 < r14) goto L_0x003f
            com.google.android.gms.internal.vision.zzhy r14 = com.google.android.gms.internal.vision.zzhy.SINT64_LIST_PACKED
            int r14 = r14.id()
            if (r15 > r14) goto L_0x003f
            int[] r14 = r0.zzaak
            int r17 = r12 + 2
            r14 = r14[r17]
        L_0x003f:
            switch(r15) {
                case 0: goto L_0x0461;
                case 1: goto L_0x0455;
                case 2: goto L_0x0445;
                case 3: goto L_0x0435;
                case 4: goto L_0x0425;
                case 5: goto L_0x0419;
                case 6: goto L_0x040d;
                case 7: goto L_0x0401;
                case 8: goto L_0x03e3;
                case 9: goto L_0x03cf;
                case 10: goto L_0x03be;
                case 11: goto L_0x03af;
                case 12: goto L_0x03a0;
                case 13: goto L_0x0395;
                case 14: goto L_0x038a;
                case 15: goto L_0x037b;
                case 16: goto L_0x036c;
                case 17: goto L_0x0357;
                case 18: goto L_0x034c;
                case 19: goto L_0x0343;
                case 20: goto L_0x033a;
                case 21: goto L_0x0331;
                case 22: goto L_0x0328;
                case 23: goto L_0x031f;
                case 24: goto L_0x0316;
                case 25: goto L_0x030d;
                case 26: goto L_0x0304;
                case 27: goto L_0x02f7;
                case 28: goto L_0x02ee;
                case 29: goto L_0x02e5;
                case 30: goto L_0x02db;
                case 31: goto L_0x02d1;
                case 32: goto L_0x02c7;
                case 33: goto L_0x02bd;
                case 34: goto L_0x02b3;
                case 35: goto L_0x029b;
                case 36: goto L_0x0286;
                case 37: goto L_0x0271;
                case 38: goto L_0x025c;
                case 39: goto L_0x0247;
                case 40: goto L_0x0232;
                case 41: goto L_0x021c;
                case 42: goto L_0x0206;
                case 43: goto L_0x01f0;
                case 44: goto L_0x01da;
                case 45: goto L_0x01c4;
                case 46: goto L_0x01ae;
                case 47: goto L_0x0198;
                case 48: goto L_0x0182;
                case 49: goto L_0x0174;
                case 50: goto L_0x0164;
                case 51: goto L_0x0156;
                case 52: goto L_0x014a;
                case 53: goto L_0x013a;
                case 54: goto L_0x012a;
                case 55: goto L_0x011a;
                case 56: goto L_0x010e;
                case 57: goto L_0x0102;
                case 58: goto L_0x00f6;
                case 59: goto L_0x00d8;
                case 60: goto L_0x00c4;
                case 61: goto L_0x00b2;
                case 62: goto L_0x00a2;
                case 63: goto L_0x0092;
                case 64: goto L_0x0086;
                case 65: goto L_0x007a;
                case 66: goto L_0x006a;
                case 67: goto L_0x005a;
                case 68: goto L_0x0044;
                default: goto L_0x0042;
            }
        L_0x0042:
            goto L_0x046f
        L_0x0044:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzjn r5 = (com.google.android.gms.internal.vision.zzjn) r5
            com.google.android.gms.internal.vision.zzkf r6 = r0.zzbu(r12)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzc(r3, r5, r6)
            goto L_0x0354
        L_0x005a:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzf(r3, r5)
            goto L_0x0354
        L_0x006a:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzp(r3, r5)
            goto L_0x0354
        L_0x007a:
            boolean r5 = r0.zzb(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzh(r3, r9)
            goto L_0x0354
        L_0x0086:
            boolean r5 = r0.zzb(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzr(r3, r11)
            goto L_0x0354
        L_0x0092:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzs(r3, r5)
            goto L_0x0354
        L_0x00a2:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzo(r3, r5)
            goto L_0x0354
        L_0x00b2:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzgs r5 = (com.google.android.gms.internal.vision.zzgs) r5
            int r3 = com.google.android.gms.internal.vision.zzhl.zzc((int) r3, (com.google.android.gms.internal.vision.zzgs) r5)
            goto L_0x0354
        L_0x00c4:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzkf r6 = r0.zzbu(r12)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzc((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.vision.zzkf) r6)
            goto L_0x0354
        L_0x00d8:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.vision.zzgs
            if (r6 == 0) goto L_0x00ee
            com.google.android.gms.internal.vision.zzgs r5 = (com.google.android.gms.internal.vision.zzgs) r5
            int r3 = com.google.android.gms.internal.vision.zzhl.zzc((int) r3, (com.google.android.gms.internal.vision.zzgs) r5)
            goto L_0x0354
        L_0x00ee:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (java.lang.String) r5)
            goto L_0x0354
        L_0x00f6:
            boolean r5 = r0.zzb(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (boolean) r8)
            goto L_0x0354
        L_0x0102:
            boolean r5 = r0.zzb(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzq(r3, r11)
            goto L_0x0354
        L_0x010e:
            boolean r5 = r0.zzb(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzg(r3, r9)
            goto L_0x0354
        L_0x011a:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            int r5 = zzh(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzn(r3, r5)
            goto L_0x0354
        L_0x012a:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zze(r3, r5)
            goto L_0x0354
        L_0x013a:
            boolean r14 = r0.zzb(r1, (int) r3, (int) r12)
            if (r14 == 0) goto L_0x046f
            long r5 = zzi(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzd((int) r3, (long) r5)
            goto L_0x0354
        L_0x014a:
            boolean r5 = r0.zzb(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (float) r4)
            goto L_0x0354
        L_0x0156:
            boolean r5 = r0.zzb(r1, (int) r3, (int) r12)
            if (r5 == 0) goto L_0x046f
            r5 = 0
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (double) r5)
            goto L_0x0354
        L_0x0164:
            com.google.android.gms.internal.vision.zzjg r14 = r0.zzaba
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            java.lang.Object r6 = r0.zzbv(r12)
            int r3 = r14.zzb(r3, r5, r6)
            goto L_0x0354
        L_0x0174:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.vision.zzkf r6 = r0.zzbu(r12)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzd(r3, r5, r6)
            goto L_0x0354
        L_0x0182:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzs(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x0198:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzw(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x01ae:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzy(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x01c4:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzx(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x01da:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzt(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x01f0:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzv(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x0206:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzz(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x021c:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzx(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x0232:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzy(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x0247:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzu(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x025c:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzr(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x0271:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzq(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x0286:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzx(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
            goto L_0x02af
        L_0x029b:
            java.lang.Object r5 = r2.getObject(r1, r5)
            java.util.List r5 = (java.util.List) r5
            int r5 = com.google.android.gms.internal.vision.zzkh.zzy(r5)
            if (r5 <= 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzbh(r3)
            int r6 = com.google.android.gms.internal.vision.zzhl.zzbj(r5)
        L_0x02af:
            int r3 = r3 + r6
            int r3 = r3 + r5
            goto L_0x0354
        L_0x02b3:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzq(r3, r5, r11)
            goto L_0x0354
        L_0x02bd:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzu(r3, r5, r11)
            goto L_0x0354
        L_0x02c7:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzw(r3, r5, r11)
            goto L_0x0354
        L_0x02d1:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzv(r3, r5, r11)
            goto L_0x0354
        L_0x02db:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzr(r3, r5, r11)
            goto L_0x0354
        L_0x02e5:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzt(r3, r5, r11)
            goto L_0x0354
        L_0x02ee:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzd(r3, r5)
            goto L_0x0354
        L_0x02f7:
            java.util.List r5 = zze(r1, r5)
            com.google.android.gms.internal.vision.zzkf r6 = r0.zzbu(r12)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzc((int) r3, (java.util.List<?>) r5, (com.google.android.gms.internal.vision.zzkf) r6)
            goto L_0x0354
        L_0x0304:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzc(r3, r5)
            goto L_0x0354
        L_0x030d:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzx(r3, r5, r11)
            goto L_0x0354
        L_0x0316:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzv(r3, r5, r11)
            goto L_0x0354
        L_0x031f:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzw(r3, r5, r11)
            goto L_0x0354
        L_0x0328:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzs(r3, r5, r11)
            goto L_0x0354
        L_0x0331:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzp(r3, r5, r11)
            goto L_0x0354
        L_0x033a:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzo(r3, r5, r11)
            goto L_0x0354
        L_0x0343:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzv(r3, r5, r11)
            goto L_0x0354
        L_0x034c:
            java.util.List r5 = zze(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzw(r3, r5, r11)
        L_0x0354:
            int r13 = r13 + r3
            goto L_0x046f
        L_0x0357:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzjn r5 = (com.google.android.gms.internal.vision.zzjn) r5
            com.google.android.gms.internal.vision.zzkf r6 = r0.zzbu(r12)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzc(r3, r5, r6)
            goto L_0x0354
        L_0x036c:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            long r5 = com.google.android.gms.internal.vision.zzld.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzf(r3, r5)
            goto L_0x0354
        L_0x037b:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzp(r3, r5)
            goto L_0x0354
        L_0x038a:
            boolean r5 = r0.zzc(r1, r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzh(r3, r9)
            goto L_0x0354
        L_0x0395:
            boolean r5 = r0.zzc(r1, r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzr(r3, r11)
            goto L_0x0354
        L_0x03a0:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzs(r3, r5)
            goto L_0x0354
        L_0x03af:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzo(r3, r5)
            goto L_0x0354
        L_0x03be:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzgs r5 = (com.google.android.gms.internal.vision.zzgs) r5
            int r3 = com.google.android.gms.internal.vision.zzhl.zzc((int) r3, (com.google.android.gms.internal.vision.zzgs) r5)
            goto L_0x0354
        L_0x03cf:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            com.google.android.gms.internal.vision.zzkf r6 = r0.zzbu(r12)
            int r3 = com.google.android.gms.internal.vision.zzkh.zzc((int) r3, (java.lang.Object) r5, (com.google.android.gms.internal.vision.zzkf) r6)
            goto L_0x0354
        L_0x03e3:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r1, r5)
            boolean r6 = r5 instanceof com.google.android.gms.internal.vision.zzgs
            if (r6 == 0) goto L_0x03f9
            com.google.android.gms.internal.vision.zzgs r5 = (com.google.android.gms.internal.vision.zzgs) r5
            int r3 = com.google.android.gms.internal.vision.zzhl.zzc((int) r3, (com.google.android.gms.internal.vision.zzgs) r5)
            goto L_0x0354
        L_0x03f9:
            java.lang.String r5 = (java.lang.String) r5
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (java.lang.String) r5)
            goto L_0x0354
        L_0x0401:
            boolean r5 = r0.zzc(r1, r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (boolean) r8)
            goto L_0x0354
        L_0x040d:
            boolean r5 = r0.zzc(r1, r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzq(r3, r11)
            goto L_0x0354
        L_0x0419:
            boolean r5 = r0.zzc(r1, r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzg(r3, r9)
            goto L_0x0354
        L_0x0425:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            int r5 = com.google.android.gms.internal.vision.zzld.zzk(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzn(r3, r5)
            goto L_0x0354
        L_0x0435:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            long r5 = com.google.android.gms.internal.vision.zzld.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zze(r3, r5)
            goto L_0x0354
        L_0x0445:
            boolean r14 = r0.zzc(r1, r12)
            if (r14 == 0) goto L_0x046f
            long r5 = com.google.android.gms.internal.vision.zzld.zzl(r1, r5)
            int r3 = com.google.android.gms.internal.vision.zzhl.zzd((int) r3, (long) r5)
            goto L_0x0354
        L_0x0455:
            boolean r5 = r0.zzc(r1, r12)
            if (r5 == 0) goto L_0x046f
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (float) r4)
            goto L_0x0354
        L_0x0461:
            boolean r5 = r0.zzc(r1, r12)
            if (r5 == 0) goto L_0x046f
            r5 = 0
            int r3 = com.google.android.gms.internal.vision.zzhl.zzb((int) r3, (double) r5)
            goto L_0x0354
        L_0x046f:
            int r12 = r12 + 3
            r3 = 267386880(0xff00000, float:2.3665827E-29)
            goto L_0x0016
        L_0x0475:
            com.google.android.gms.internal.vision.zzkx<?, ?> r2 = r0.zzaay
            int r1 = zza(r2, r1)
            int r13 = r13 + r1
            return r13
        L_0x047d:
            sun.misc.Unsafe r2 = zzaaj
            r3 = 0
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            r12 = 0
        L_0x0485:
            int[] r13 = r0.zzaak
            int r13 = r13.length
            if (r3 >= r13) goto L_0x0930
            int r13 = r0.zzbx(r3)
            int[] r14 = r0.zzaak
            r15 = r14[r3]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r17 = r13 & r16
            int r4 = r17 >>> 20
            r11 = 17
            if (r4 > r11) goto L_0x04b0
            int r11 = r3 + 2
            r11 = r14[r11]
            r14 = r11 & r7
            int r11 = r11 >>> 20
            int r11 = r8 << r11
            if (r14 == r6) goto L_0x04b1
            long r8 = (long) r14
            int r6 = r2.getInt(r1, r8)
            r12 = r6
            r6 = r14
            goto L_0x04b1
        L_0x04b0:
            r11 = 0
        L_0x04b1:
            r8 = r13 & r7
            long r8 = (long) r8
            switch(r4) {
                case 0: goto L_0x0915;
                case 1: goto L_0x0904;
                case 2: goto L_0x08f3;
                case 3: goto L_0x08e3;
                case 4: goto L_0x08d3;
                case 5: goto L_0x08c6;
                case 6: goto L_0x08b9;
                case 7: goto L_0x08ad;
                case 8: goto L_0x0891;
                case 9: goto L_0x087f;
                case 10: goto L_0x0870;
                case 11: goto L_0x0863;
                case 12: goto L_0x0856;
                case 13: goto L_0x084b;
                case 14: goto L_0x0840;
                case 15: goto L_0x0833;
                case 16: goto L_0x0826;
                case 17: goto L_0x0813;
                case 18: goto L_0x07ff;
                case 19: goto L_0x07ef;
                case 20: goto L_0x07e3;
                case 21: goto L_0x07d7;
                case 22: goto L_0x07cb;
                case 23: goto L_0x07bf;
                case 24: goto L_0x07b3;
                case 25: goto L_0x07a7;
                case 26: goto L_0x079c;
                case 27: goto L_0x078c;
                case 28: goto L_0x0780;
                case 29: goto L_0x0773;
                case 30: goto L_0x0766;
                case 31: goto L_0x0759;
                case 32: goto L_0x074c;
                case 33: goto L_0x073f;
                case 34: goto L_0x0732;
                case 35: goto L_0x071a;
                case 36: goto L_0x0705;
                case 37: goto L_0x06f0;
                case 38: goto L_0x06db;
                case 39: goto L_0x06c6;
                case 40: goto L_0x06b1;
                case 41: goto L_0x069b;
                case 42: goto L_0x0685;
                case 43: goto L_0x066f;
                case 44: goto L_0x0659;
                case 45: goto L_0x0643;
                case 46: goto L_0x062d;
                case 47: goto L_0x0617;
                case 48: goto L_0x0601;
                case 49: goto L_0x05f1;
                case 50: goto L_0x05e1;
                case 51: goto L_0x05d3;
                case 52: goto L_0x05c6;
                case 53: goto L_0x05b6;
                case 54: goto L_0x05a6;
                case 55: goto L_0x0596;
                case 56: goto L_0x0588;
                case 57: goto L_0x057b;
                case 58: goto L_0x056e;
                case 59: goto L_0x0550;
                case 60: goto L_0x053c;
                case 61: goto L_0x052a;
                case 62: goto L_0x051a;
                case 63: goto L_0x050a;
                case 64: goto L_0x04fd;
                case 65: goto L_0x04ef;
                case 66: goto L_0x04df;
                case 67: goto L_0x04cf;
                case 68: goto L_0x04b9;
                default: goto L_0x04b7;
            }
        L_0x04b7:
            goto L_0x080b
        L_0x04b9:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.vision.zzjn r4 = (com.google.android.gms.internal.vision.zzjn) r4
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r3)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzc(r15, r4, r8)
            goto L_0x080a
        L_0x04cf:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzf(r15, r8)
            goto L_0x080a
        L_0x04df:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzp(r15, r4)
            goto L_0x080a
        L_0x04ef:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            r8 = 0
            int r4 = com.google.android.gms.internal.vision.zzhl.zzh(r15, r8)
            goto L_0x080a
        L_0x04fd:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            r4 = 0
            int r8 = com.google.android.gms.internal.vision.zzhl.zzr(r15, r4)
            goto L_0x0854
        L_0x050a:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzs(r15, r4)
            goto L_0x080a
        L_0x051a:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzo(r15, r4)
            goto L_0x080a
        L_0x052a:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.vision.zzgs r4 = (com.google.android.gms.internal.vision.zzgs) r4
            int r4 = com.google.android.gms.internal.vision.zzhl.zzc((int) r15, (com.google.android.gms.internal.vision.zzgs) r4)
            goto L_0x080a
        L_0x053c:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r3)
            int r4 = com.google.android.gms.internal.vision.zzkh.zzc((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.vision.zzkf) r8)
            goto L_0x080a
        L_0x0550:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.vision.zzgs
            if (r8 == 0) goto L_0x0566
            com.google.android.gms.internal.vision.zzgs r4 = (com.google.android.gms.internal.vision.zzgs) r4
            int r4 = com.google.android.gms.internal.vision.zzhl.zzc((int) r15, (com.google.android.gms.internal.vision.zzgs) r4)
            goto L_0x080a
        L_0x0566:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (java.lang.String) r4)
            goto L_0x080a
        L_0x056e:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            r4 = 1
            int r8 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (boolean) r4)
            goto L_0x0854
        L_0x057b:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            r4 = 0
            int r8 = com.google.android.gms.internal.vision.zzhl.zzq(r15, r4)
            goto L_0x0854
        L_0x0588:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            r8 = 0
            int r4 = com.google.android.gms.internal.vision.zzhl.zzg(r15, r8)
            goto L_0x080a
        L_0x0596:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            int r4 = zzh(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzn(r15, r4)
            goto L_0x080a
        L_0x05a6:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zze(r15, r8)
            goto L_0x080a
        L_0x05b6:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            long r8 = zzi(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzd((int) r15, (long) r8)
            goto L_0x080a
        L_0x05c6:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            r4 = 0
            int r8 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (float) r4)
            goto L_0x0854
        L_0x05d3:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r3)
            if (r4 == 0) goto L_0x080b
            r8 = 0
            int r4 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (double) r8)
            goto L_0x080a
        L_0x05e1:
            com.google.android.gms.internal.vision.zzjg r4 = r0.zzaba
            java.lang.Object r8 = r2.getObject(r1, r8)
            java.lang.Object r9 = r0.zzbv(r3)
            int r4 = r4.zzb(r15, r8, r9)
            goto L_0x080a
        L_0x05f1:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r3)
            int r4 = com.google.android.gms.internal.vision.zzkh.zzd(r15, r4, r8)
            goto L_0x080a
        L_0x0601:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzs(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x0617:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzw(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x062d:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzy(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x0643:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzx(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x0659:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzt(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x066f:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzv(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x0685:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzz(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x069b:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzx(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x06b1:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzy(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x06c6:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzu(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x06db:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzr(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x06f0:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzq(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x0705:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzx(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
            goto L_0x072e
        L_0x071a:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzy(r4)
            if (r4 <= 0) goto L_0x080b
            int r8 = com.google.android.gms.internal.vision.zzhl.zzbh(r15)
            int r9 = com.google.android.gms.internal.vision.zzhl.zzbj(r4)
        L_0x072e:
            int r8 = r8 + r9
            int r8 = r8 + r4
            goto L_0x0854
        L_0x0732:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.vision.zzkh.zzq(r15, r4, r10)
            goto L_0x07fa
        L_0x073f:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzu(r15, r4, r10)
            goto L_0x07fa
        L_0x074c:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzw(r15, r4, r10)
            goto L_0x07fa
        L_0x0759:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzv(r15, r4, r10)
            goto L_0x07fa
        L_0x0766:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzr(r15, r4, r10)
            goto L_0x07fa
        L_0x0773:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzt(r15, r4, r10)
            goto L_0x080a
        L_0x0780:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzd(r15, r4)
            goto L_0x080a
        L_0x078c:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r3)
            int r4 = com.google.android.gms.internal.vision.zzkh.zzc((int) r15, (java.util.List<?>) r4, (com.google.android.gms.internal.vision.zzkf) r8)
            goto L_0x080a
        L_0x079c:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzc(r15, r4)
            goto L_0x080a
        L_0x07a7:
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            r10 = 0
            int r4 = com.google.android.gms.internal.vision.zzkh.zzx(r15, r4, r10)
            goto L_0x07fa
        L_0x07b3:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzv(r15, r4, r10)
            goto L_0x07fa
        L_0x07bf:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzw(r15, r4, r10)
            goto L_0x07fa
        L_0x07cb:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzs(r15, r4, r10)
            goto L_0x07fa
        L_0x07d7:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzp(r15, r4, r10)
            goto L_0x07fa
        L_0x07e3:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzo(r15, r4, r10)
            goto L_0x07fa
        L_0x07ef:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzv(r15, r4, r10)
        L_0x07fa:
            int r5 = r5 + r4
            r4 = 1
        L_0x07fc:
            r7 = 0
            goto L_0x080f
        L_0x07ff:
            r10 = 0
            java.lang.Object r4 = r2.getObject(r1, r8)
            java.util.List r4 = (java.util.List) r4
            int r4 = com.google.android.gms.internal.vision.zzkh.zzw(r15, r4, r10)
        L_0x080a:
            int r5 = r5 + r4
        L_0x080b:
            r4 = 1
        L_0x080c:
            r7 = 0
            r10 = 0
        L_0x080f:
            r13 = 0
            goto L_0x0925
        L_0x0813:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.vision.zzjn r4 = (com.google.android.gms.internal.vision.zzjn) r4
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r3)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzc(r15, r4, r8)
            goto L_0x080a
        L_0x0826:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            long r8 = r2.getLong(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzf(r15, r8)
            goto L_0x080a
        L_0x0833:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzp(r15, r4)
            goto L_0x080a
        L_0x0840:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            r8 = 0
            int r4 = com.google.android.gms.internal.vision.zzhl.zzh(r15, r8)
            goto L_0x080a
        L_0x084b:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            r4 = 0
            int r8 = com.google.android.gms.internal.vision.zzhl.zzr(r15, r4)
        L_0x0854:
            int r5 = r5 + r8
            goto L_0x080b
        L_0x0856:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzs(r15, r4)
            goto L_0x080a
        L_0x0863:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            int r4 = r2.getInt(r1, r8)
            int r4 = com.google.android.gms.internal.vision.zzhl.zzo(r15, r4)
            goto L_0x080a
        L_0x0870:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.vision.zzgs r4 = (com.google.android.gms.internal.vision.zzgs) r4
            int r4 = com.google.android.gms.internal.vision.zzhl.zzc((int) r15, (com.google.android.gms.internal.vision.zzgs) r4)
            goto L_0x080a
        L_0x087f:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r3)
            int r4 = com.google.android.gms.internal.vision.zzkh.zzc((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.vision.zzkf) r8)
            goto L_0x080a
        L_0x0891:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r2.getObject(r1, r8)
            boolean r8 = r4 instanceof com.google.android.gms.internal.vision.zzgs
            if (r8 == 0) goto L_0x08a5
            com.google.android.gms.internal.vision.zzgs r4 = (com.google.android.gms.internal.vision.zzgs) r4
            int r4 = com.google.android.gms.internal.vision.zzhl.zzc((int) r15, (com.google.android.gms.internal.vision.zzgs) r4)
            goto L_0x080a
        L_0x08a5:
            java.lang.String r4 = (java.lang.String) r4
            int r4 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (java.lang.String) r4)
            goto L_0x080a
        L_0x08ad:
            r4 = r12 & r11
            if (r4 == 0) goto L_0x080b
            r4 = 1
            int r8 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (boolean) r4)
            int r5 = r5 + r8
            goto L_0x080c
        L_0x08b9:
            r4 = 1
            r8 = r12 & r11
            r10 = 0
            if (r8 == 0) goto L_0x07fc
            int r8 = com.google.android.gms.internal.vision.zzhl.zzq(r15, r10)
            int r5 = r5 + r8
            goto L_0x07fc
        L_0x08c6:
            r4 = 1
            r10 = 0
            r8 = r12 & r11
            r13 = 0
            if (r8 == 0) goto L_0x0912
            int r8 = com.google.android.gms.internal.vision.zzhl.zzg(r15, r13)
            goto L_0x0902
        L_0x08d3:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r11 & r12
            if (r11 == 0) goto L_0x0912
            int r8 = r2.getInt(r1, r8)
            int r8 = com.google.android.gms.internal.vision.zzhl.zzn(r15, r8)
            goto L_0x0902
        L_0x08e3:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r11 & r12
            if (r11 == 0) goto L_0x0912
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.vision.zzhl.zze(r15, r8)
            goto L_0x0902
        L_0x08f3:
            r4 = 1
            r10 = 0
            r13 = 0
            r11 = r11 & r12
            if (r11 == 0) goto L_0x0912
            long r8 = r2.getLong(r1, r8)
            int r8 = com.google.android.gms.internal.vision.zzhl.zzd((int) r15, (long) r8)
        L_0x0902:
            int r5 = r5 + r8
            goto L_0x0912
        L_0x0904:
            r4 = 1
            r10 = 0
            r13 = 0
            r8 = r12 & r11
            if (r8 == 0) goto L_0x0912
            r8 = 0
            int r9 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (float) r8)
            int r5 = r5 + r9
        L_0x0912:
            r7 = 0
            goto L_0x0925
        L_0x0915:
            r4 = 1
            r8 = 0
            r10 = 0
            r13 = 0
            r9 = r12 & r11
            if (r9 == 0) goto L_0x0912
            r7 = 0
            int r11 = com.google.android.gms.internal.vision.zzhl.zzb((int) r15, (double) r7)
            int r5 = r5 + r11
        L_0x0925:
            int r3 = r3 + 3
            r9 = r13
            r4 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
            r8 = 1
            r11 = 0
            goto L_0x0485
        L_0x0930:
            r10 = 0
            com.google.android.gms.internal.vision.zzkx<?, ?> r2 = r0.zzaay
            int r2 = zza(r2, r1)
            int r5 = r5 + r2
            boolean r2 = r0.zzaap
            if (r2 == 0) goto L_0x098a
            com.google.android.gms.internal.vision.zzhq<?> r2 = r0.zzaaz
            com.google.android.gms.internal.vision.zzht r1 = r2.zzh(r1)
            r11 = 0
        L_0x0943:
            com.google.android.gms.internal.vision.zzkg<T, java.lang.Object> r2 = r1.zzux
            int r2 = r2.zzin()
            if (r11 >= r2) goto L_0x0963
            com.google.android.gms.internal.vision.zzkg<T, java.lang.Object> r2 = r1.zzux
            java.util.Map$Entry r2 = r2.zzcc(r11)
            java.lang.Object r3 = r2.getKey()
            com.google.android.gms.internal.vision.zzhv r3 = (com.google.android.gms.internal.vision.zzhv) r3
            java.lang.Object r2 = r2.getValue()
            int r2 = com.google.android.gms.internal.vision.zzht.zzc(r3, r2)
            int r10 = r10 + r2
            int r11 = r11 + 1
            goto L_0x0943
        L_0x0963:
            com.google.android.gms.internal.vision.zzkg<T, java.lang.Object> r1 = r1.zzux
            java.lang.Iterable r1 = r1.zzio()
            java.util.Iterator r1 = r1.iterator()
        L_0x096d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0989
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            com.google.android.gms.internal.vision.zzhv r3 = (com.google.android.gms.internal.vision.zzhv) r3
            java.lang.Object r2 = r2.getValue()
            int r2 = com.google.android.gms.internal.vision.zzht.zzc(r3, r2)
            int r10 = r10 + r2
            goto L_0x096d
        L_0x0989:
            int r5 = r5 + r10
        L_0x098a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zzu(java.lang.Object):int");
    }

    private static <UT, UB> int zza(zzkx<UT, UB> zzkx, T t) {
        return zzkx.zzu(zzkx.zzy(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzld.zzp(obj, j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0513  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x0552  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x0a2a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r14, com.google.android.gms.internal.vision.zzlq r15) throws java.io.IOException {
        /*
            r13 = this;
            int r0 = r15.zzgd()
            int r1 = com.google.android.gms.internal.vision.zzlt.zzaey
            r2 = 267386880(0xff00000, float:2.3665827E-29)
            r3 = 0
            r4 = 1
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
            if (r0 != r1) goto L_0x0529
            com.google.android.gms.internal.vision.zzkx<?, ?> r0 = r13.zzaay
            zza(r0, r14, (com.google.android.gms.internal.vision.zzlq) r15)
            boolean r0 = r13.zzaap
            if (r0 == 0) goto L_0x0032
            com.google.android.gms.internal.vision.zzhq<?> r0 = r13.zzaaz
            com.google.android.gms.internal.vision.zzht r0 = r0.zzh(r14)
            com.google.android.gms.internal.vision.zzkg<T, java.lang.Object> r1 = r0.zzux
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0032
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0034
        L_0x0032:
            r0 = r3
            r1 = r0
        L_0x0034:
            int[] r7 = r13.zzaak
            int r7 = r7.length
            int r7 = r7 + -3
        L_0x0039:
            if (r7 < 0) goto L_0x0511
            int r8 = r13.zzbx(r7)
            int[] r9 = r13.zzaak
            r9 = r9[r7]
        L_0x0043:
            if (r1 == 0) goto L_0x0061
            com.google.android.gms.internal.vision.zzhq<?> r10 = r13.zzaaz
            int r10 = r10.zza(r1)
            if (r10 <= r9) goto L_0x0061
            com.google.android.gms.internal.vision.zzhq<?> r10 = r13.zzaaz
            r10.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005f
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0043
        L_0x005f:
            r1 = r3
            goto L_0x0043
        L_0x0061:
            r10 = r8 & r2
            int r10 = r10 >>> 20
            switch(r10) {
                case 0: goto L_0x04fe;
                case 1: goto L_0x04ee;
                case 2: goto L_0x04de;
                case 3: goto L_0x04ce;
                case 4: goto L_0x04be;
                case 5: goto L_0x04ae;
                case 6: goto L_0x049e;
                case 7: goto L_0x048d;
                case 8: goto L_0x047c;
                case 9: goto L_0x0467;
                case 10: goto L_0x0454;
                case 11: goto L_0x0443;
                case 12: goto L_0x0432;
                case 13: goto L_0x0421;
                case 14: goto L_0x0410;
                case 15: goto L_0x03ff;
                case 16: goto L_0x03ee;
                case 17: goto L_0x03d9;
                case 18: goto L_0x03c8;
                case 19: goto L_0x03b7;
                case 20: goto L_0x03a6;
                case 21: goto L_0x0395;
                case 22: goto L_0x0384;
                case 23: goto L_0x0373;
                case 24: goto L_0x0362;
                case 25: goto L_0x0351;
                case 26: goto L_0x0340;
                case 27: goto L_0x032b;
                case 28: goto L_0x031a;
                case 29: goto L_0x0309;
                case 30: goto L_0x02f8;
                case 31: goto L_0x02e7;
                case 32: goto L_0x02d6;
                case 33: goto L_0x02c5;
                case 34: goto L_0x02b4;
                case 35: goto L_0x02a3;
                case 36: goto L_0x0292;
                case 37: goto L_0x0281;
                case 38: goto L_0x0270;
                case 39: goto L_0x025f;
                case 40: goto L_0x024e;
                case 41: goto L_0x023d;
                case 42: goto L_0x022c;
                case 43: goto L_0x021b;
                case 44: goto L_0x020a;
                case 45: goto L_0x01f9;
                case 46: goto L_0x01e8;
                case 47: goto L_0x01d7;
                case 48: goto L_0x01c6;
                case 49: goto L_0x01b1;
                case 50: goto L_0x01a6;
                case 51: goto L_0x0195;
                case 52: goto L_0x0184;
                case 53: goto L_0x0173;
                case 54: goto L_0x0162;
                case 55: goto L_0x0151;
                case 56: goto L_0x0140;
                case 57: goto L_0x012f;
                case 58: goto L_0x011e;
                case 59: goto L_0x010d;
                case 60: goto L_0x00f8;
                case 61: goto L_0x00e5;
                case 62: goto L_0x00d4;
                case 63: goto L_0x00c3;
                case 64: goto L_0x00b2;
                case 65: goto L_0x00a1;
                case 66: goto L_0x0090;
                case 67: goto L_0x007f;
                case 68: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            goto L_0x050d
        L_0x006a:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzkf r10 = r13.zzbu(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzkf) r10)
            goto L_0x050d
        L_0x007f:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x050d
        L_0x0090:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzl(r9, r8)
            goto L_0x050d
        L_0x00a1:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzj((int) r9, (long) r10)
            goto L_0x050d
        L_0x00b2:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzt(r9, r8)
            goto L_0x050d
        L_0x00c3:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzu(r9, r8)
            goto L_0x050d
        L_0x00d4:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzk(r9, r8)
            goto L_0x050d
        L_0x00e5:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzgs r8 = (com.google.android.gms.internal.vision.zzgs) r8
            r15.zza((int) r9, (com.google.android.gms.internal.vision.zzgs) r8)
            goto L_0x050d
        L_0x00f8:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzkf r10 = r13.zzbu(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzkf) r10)
            goto L_0x050d
        L_0x010d:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzlq) r15)
            goto L_0x050d
        L_0x011e:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = zzj(r14, r10)
            r15.zza((int) r9, (boolean) r8)
            goto L_0x050d
        L_0x012f:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050d
        L_0x0140:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050d
        L_0x0151:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = zzh(r14, r10)
            r15.zzj((int) r9, (int) r8)
            goto L_0x050d
        L_0x0162:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zza((int) r9, (long) r10)
            goto L_0x050d
        L_0x0173:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = zzi(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x0184:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = zzg(r14, r10)
            r15.zza((int) r9, (float) r8)
            goto L_0x050d
        L_0x0195:
            boolean r10 = r13.zzb(r14, (int) r9, (int) r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = zzf(r14, r10)
            r15.zza((int) r9, (double) r10)
            goto L_0x050d
        L_0x01a6:
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            r13.zza((com.google.android.gms.internal.vision.zzlq) r15, (int) r9, (java.lang.Object) r8, (int) r7)
            goto L_0x050d
        L_0x01b1:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkf r10 = r13.zzbu(r7)
            com.google.android.gms.internal.vision.zzkh.zzb((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.vision.zzlq) r15, (com.google.android.gms.internal.vision.zzkf) r10)
            goto L_0x050d
        L_0x01c6:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zze(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01d7:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzj(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01e8:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzg(r9, r8, r15, r4)
            goto L_0x050d
        L_0x01f9:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzl(r9, r8, r15, r4)
            goto L_0x050d
        L_0x020a:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzm(r9, r8, r15, r4)
            goto L_0x050d
        L_0x021b:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzi(r9, r8, r15, r4)
            goto L_0x050d
        L_0x022c:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzn(r9, r8, r15, r4)
            goto L_0x050d
        L_0x023d:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzk(r9, r8, r15, r4)
            goto L_0x050d
        L_0x024e:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzf(r9, r8, r15, r4)
            goto L_0x050d
        L_0x025f:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzh(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0270:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzd(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0281:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzc(r9, r8, r15, r4)
            goto L_0x050d
        L_0x0292:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r4)
            goto L_0x050d
        L_0x02a3:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r4)
            goto L_0x050d
        L_0x02b4:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zze(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02c5:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzj(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02d6:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzg(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02e7:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzl(r9, r8, r15, r5)
            goto L_0x050d
        L_0x02f8:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzm(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0309:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzi(r9, r8, r15, r5)
            goto L_0x050d
        L_0x031a:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzb(r9, r8, r15)
            goto L_0x050d
        L_0x032b:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkf r10 = r13.zzbu(r7)
            com.google.android.gms.internal.vision.zzkh.zza((int) r9, (java.util.List<?>) r8, (com.google.android.gms.internal.vision.zzlq) r15, (com.google.android.gms.internal.vision.zzkf) r10)
            goto L_0x050d
        L_0x0340:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zza((int) r9, (java.util.List<java.lang.String>) r8, (com.google.android.gms.internal.vision.zzlq) r15)
            goto L_0x050d
        L_0x0351:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzn(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0362:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzk(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0373:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzf(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0384:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzh(r9, r8, r15, r5)
            goto L_0x050d
        L_0x0395:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzd(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03a6:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzc(r9, r8, r15, r5)
            goto L_0x050d
        L_0x03b7:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzb((int) r9, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r5)
            goto L_0x050d
        L_0x03c8:
            int[] r9 = r13.zzaak
            r9 = r9[r7]
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zza((int) r9, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r5)
            goto L_0x050d
        L_0x03d9:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzkf r10 = r13.zzbu(r7)
            r15.zzb((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzkf) r10)
            goto L_0x050d
        L_0x03ee:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzld.zzl(r14, r10)
            r15.zzb((int) r9, (long) r10)
            goto L_0x050d
        L_0x03ff:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzld.zzk(r14, r10)
            r15.zzl(r9, r8)
            goto L_0x050d
        L_0x0410:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzld.zzl(r14, r10)
            r15.zzj((int) r9, (long) r10)
            goto L_0x050d
        L_0x0421:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzld.zzk(r14, r10)
            r15.zzt(r9, r8)
            goto L_0x050d
        L_0x0432:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzld.zzk(r14, r10)
            r15.zzu(r9, r8)
            goto L_0x050d
        L_0x0443:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzld.zzk(r14, r10)
            r15.zzk(r9, r8)
            goto L_0x050d
        L_0x0454:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzgs r8 = (com.google.android.gms.internal.vision.zzgs) r8
            r15.zza((int) r9, (com.google.android.gms.internal.vision.zzgs) r8)
            goto L_0x050d
        L_0x0467:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            com.google.android.gms.internal.vision.zzkf r10 = r13.zzbu(r7)
            r15.zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzkf) r10)
            goto L_0x050d
        L_0x047c:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            java.lang.Object r8 = com.google.android.gms.internal.vision.zzld.zzp(r14, r10)
            zza((int) r9, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzlq) r15)
            goto L_0x050d
        L_0x048d:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            boolean r8 = com.google.android.gms.internal.vision.zzld.zzm(r14, r10)
            r15.zza((int) r9, (boolean) r8)
            goto L_0x050d
        L_0x049e:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzld.zzk(r14, r10)
            r15.zzm(r9, r8)
            goto L_0x050d
        L_0x04ae:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzld.zzl(r14, r10)
            r15.zzc(r9, r10)
            goto L_0x050d
        L_0x04be:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            int r8 = com.google.android.gms.internal.vision.zzld.zzk(r14, r10)
            r15.zzj((int) r9, (int) r8)
            goto L_0x050d
        L_0x04ce:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzld.zzl(r14, r10)
            r15.zza((int) r9, (long) r10)
            goto L_0x050d
        L_0x04de:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            long r10 = com.google.android.gms.internal.vision.zzld.zzl(r14, r10)
            r15.zzi(r9, r10)
            goto L_0x050d
        L_0x04ee:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            float r8 = com.google.android.gms.internal.vision.zzld.zzn(r14, r10)
            r15.zza((int) r9, (float) r8)
            goto L_0x050d
        L_0x04fe:
            boolean r10 = r13.zzc(r14, r7)
            if (r10 == 0) goto L_0x050d
            r8 = r8 & r6
            long r10 = (long) r8
            double r10 = com.google.android.gms.internal.vision.zzld.zzo(r14, r10)
            r15.zza((int) r9, (double) r10)
        L_0x050d:
            int r7 = r7 + -3
            goto L_0x0039
        L_0x0511:
            if (r1 == 0) goto L_0x0528
            com.google.android.gms.internal.vision.zzhq<?> r14 = r13.zzaaz
            r14.zza(r15, r1)
            boolean r14 = r0.hasNext()
            if (r14 == 0) goto L_0x0526
            java.lang.Object r14 = r0.next()
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14
            r1 = r14
            goto L_0x0511
        L_0x0526:
            r1 = r3
            goto L_0x0511
        L_0x0528:
            return
        L_0x0529:
            boolean r0 = r13.zzaar
            if (r0 == 0) goto L_0x0a44
            boolean r0 = r13.zzaap
            if (r0 == 0) goto L_0x054a
            com.google.android.gms.internal.vision.zzhq<?> r0 = r13.zzaaz
            com.google.android.gms.internal.vision.zzht r0 = r0.zzh(r14)
            com.google.android.gms.internal.vision.zzkg<T, java.lang.Object> r1 = r0.zzux
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x054a
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x054c
        L_0x054a:
            r0 = r3
            r1 = r0
        L_0x054c:
            int[] r7 = r13.zzaak
            int r7 = r7.length
            r8 = 0
        L_0x0550:
            if (r8 >= r7) goto L_0x0a28
            int r9 = r13.zzbx(r8)
            int[] r10 = r13.zzaak
            r10 = r10[r8]
        L_0x055a:
            if (r1 == 0) goto L_0x0578
            com.google.android.gms.internal.vision.zzhq<?> r11 = r13.zzaaz
            int r11 = r11.zza(r1)
            if (r11 > r10) goto L_0x0578
            com.google.android.gms.internal.vision.zzhq<?> r11 = r13.zzaaz
            r11.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0576
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x055a
        L_0x0576:
            r1 = r3
            goto L_0x055a
        L_0x0578:
            r11 = r9 & r2
            int r11 = r11 >>> 20
            switch(r11) {
                case 0: goto L_0x0a15;
                case 1: goto L_0x0a05;
                case 2: goto L_0x09f5;
                case 3: goto L_0x09e5;
                case 4: goto L_0x09d5;
                case 5: goto L_0x09c5;
                case 6: goto L_0x09b5;
                case 7: goto L_0x09a4;
                case 8: goto L_0x0993;
                case 9: goto L_0x097e;
                case 10: goto L_0x096b;
                case 11: goto L_0x095a;
                case 12: goto L_0x0949;
                case 13: goto L_0x0938;
                case 14: goto L_0x0927;
                case 15: goto L_0x0916;
                case 16: goto L_0x0905;
                case 17: goto L_0x08f0;
                case 18: goto L_0x08df;
                case 19: goto L_0x08ce;
                case 20: goto L_0x08bd;
                case 21: goto L_0x08ac;
                case 22: goto L_0x089b;
                case 23: goto L_0x088a;
                case 24: goto L_0x0879;
                case 25: goto L_0x0868;
                case 26: goto L_0x0857;
                case 27: goto L_0x0842;
                case 28: goto L_0x0831;
                case 29: goto L_0x0820;
                case 30: goto L_0x080f;
                case 31: goto L_0x07fe;
                case 32: goto L_0x07ed;
                case 33: goto L_0x07dc;
                case 34: goto L_0x07cb;
                case 35: goto L_0x07ba;
                case 36: goto L_0x07a9;
                case 37: goto L_0x0798;
                case 38: goto L_0x0787;
                case 39: goto L_0x0776;
                case 40: goto L_0x0765;
                case 41: goto L_0x0754;
                case 42: goto L_0x0743;
                case 43: goto L_0x0732;
                case 44: goto L_0x0721;
                case 45: goto L_0x0710;
                case 46: goto L_0x06ff;
                case 47: goto L_0x06ee;
                case 48: goto L_0x06dd;
                case 49: goto L_0x06c8;
                case 50: goto L_0x06bd;
                case 51: goto L_0x06ac;
                case 52: goto L_0x069b;
                case 53: goto L_0x068a;
                case 54: goto L_0x0679;
                case 55: goto L_0x0668;
                case 56: goto L_0x0657;
                case 57: goto L_0x0646;
                case 58: goto L_0x0635;
                case 59: goto L_0x0624;
                case 60: goto L_0x060f;
                case 61: goto L_0x05fc;
                case 62: goto L_0x05eb;
                case 63: goto L_0x05da;
                case 64: goto L_0x05c9;
                case 65: goto L_0x05b8;
                case 66: goto L_0x05a7;
                case 67: goto L_0x0596;
                case 68: goto L_0x0581;
                default: goto L_0x057f;
            }
        L_0x057f:
            goto L_0x0a24
        L_0x0581:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzkf r11 = r13.zzbu(r8)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.vision.zzkf) r11)
            goto L_0x0a24
        L_0x0596:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0a24
        L_0x05a7:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzl(r10, r9)
            goto L_0x0a24
        L_0x05b8:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzj((int) r10, (long) r11)
            goto L_0x0a24
        L_0x05c9:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzt(r10, r9)
            goto L_0x0a24
        L_0x05da:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzu(r10, r9)
            goto L_0x0a24
        L_0x05eb:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzk(r10, r9)
            goto L_0x0a24
        L_0x05fc:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzgs r9 = (com.google.android.gms.internal.vision.zzgs) r9
            r15.zza((int) r10, (com.google.android.gms.internal.vision.zzgs) r9)
            goto L_0x0a24
        L_0x060f:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzkf r11 = r13.zzbu(r8)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.vision.zzkf) r11)
            goto L_0x0a24
        L_0x0624:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.vision.zzlq) r15)
            goto L_0x0a24
        L_0x0635:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = zzj(r14, r11)
            r15.zza((int) r10, (boolean) r9)
            goto L_0x0a24
        L_0x0646:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a24
        L_0x0657:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a24
        L_0x0668:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = zzh(r14, r11)
            r15.zzj((int) r10, (int) r9)
            goto L_0x0a24
        L_0x0679:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zza((int) r10, (long) r11)
            goto L_0x0a24
        L_0x068a:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = zzi(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a24
        L_0x069b:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = zzg(r14, r11)
            r15.zza((int) r10, (float) r9)
            goto L_0x0a24
        L_0x06ac:
            boolean r11 = r13.zzb(r14, (int) r10, (int) r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = zzf(r14, r11)
            r15.zza((int) r10, (double) r11)
            goto L_0x0a24
        L_0x06bd:
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            r13.zza((com.google.android.gms.internal.vision.zzlq) r15, (int) r10, (java.lang.Object) r9, (int) r8)
            goto L_0x0a24
        L_0x06c8:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkf r11 = r13.zzbu(r8)
            com.google.android.gms.internal.vision.zzkh.zzb((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.vision.zzlq) r15, (com.google.android.gms.internal.vision.zzkf) r11)
            goto L_0x0a24
        L_0x06dd:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zze(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x06ee:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzj(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x06ff:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzg(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0710:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzl(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0721:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzm(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0732:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzi(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0743:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzn(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0754:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzk(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0765:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzf(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0776:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzh(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0787:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzd(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x0798:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzc(r10, r9, r15, r4)
            goto L_0x0a24
        L_0x07a9:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r4)
            goto L_0x0a24
        L_0x07ba:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r4)
            goto L_0x0a24
        L_0x07cb:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zze(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x07dc:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzj(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x07ed:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzg(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x07fe:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzl(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x080f:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzm(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x0820:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzi(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x0831:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzb(r10, r9, r15)
            goto L_0x0a24
        L_0x0842:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkf r11 = r13.zzbu(r8)
            com.google.android.gms.internal.vision.zzkh.zza((int) r10, (java.util.List<?>) r9, (com.google.android.gms.internal.vision.zzlq) r15, (com.google.android.gms.internal.vision.zzkf) r11)
            goto L_0x0a24
        L_0x0857:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zza((int) r10, (java.util.List<java.lang.String>) r9, (com.google.android.gms.internal.vision.zzlq) r15)
            goto L_0x0a24
        L_0x0868:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzn(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x0879:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzk(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x088a:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzf(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x089b:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzh(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x08ac:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzd(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x08bd:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzc(r10, r9, r15, r5)
            goto L_0x0a24
        L_0x08ce:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zzb((int) r10, (java.util.List<java.lang.Float>) r9, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r5)
            goto L_0x0a24
        L_0x08df:
            int[] r10 = r13.zzaak
            r10 = r10[r8]
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            java.util.List r9 = (java.util.List) r9
            com.google.android.gms.internal.vision.zzkh.zza((int) r10, (java.util.List<java.lang.Double>) r9, (com.google.android.gms.internal.vision.zzlq) r15, (boolean) r5)
            goto L_0x0a24
        L_0x08f0:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzkf r11 = r13.zzbu(r8)
            r15.zzb((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.vision.zzkf) r11)
            goto L_0x0a24
        L_0x0905:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzld.zzl(r14, r11)
            r15.zzb((int) r10, (long) r11)
            goto L_0x0a24
        L_0x0916:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzld.zzk(r14, r11)
            r15.zzl(r10, r9)
            goto L_0x0a24
        L_0x0927:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzld.zzl(r14, r11)
            r15.zzj((int) r10, (long) r11)
            goto L_0x0a24
        L_0x0938:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzld.zzk(r14, r11)
            r15.zzt(r10, r9)
            goto L_0x0a24
        L_0x0949:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzld.zzk(r14, r11)
            r15.zzu(r10, r9)
            goto L_0x0a24
        L_0x095a:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzld.zzk(r14, r11)
            r15.zzk(r10, r9)
            goto L_0x0a24
        L_0x096b:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzgs r9 = (com.google.android.gms.internal.vision.zzgs) r9
            r15.zza((int) r10, (com.google.android.gms.internal.vision.zzgs) r9)
            goto L_0x0a24
        L_0x097e:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            com.google.android.gms.internal.vision.zzkf r11 = r13.zzbu(r8)
            r15.zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.vision.zzkf) r11)
            goto L_0x0a24
        L_0x0993:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            java.lang.Object r9 = com.google.android.gms.internal.vision.zzld.zzp(r14, r11)
            zza((int) r10, (java.lang.Object) r9, (com.google.android.gms.internal.vision.zzlq) r15)
            goto L_0x0a24
        L_0x09a4:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            boolean r9 = com.google.android.gms.internal.vision.zzld.zzm(r14, r11)
            r15.zza((int) r10, (boolean) r9)
            goto L_0x0a24
        L_0x09b5:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzld.zzk(r14, r11)
            r15.zzm(r10, r9)
            goto L_0x0a24
        L_0x09c5:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzld.zzl(r14, r11)
            r15.zzc(r10, r11)
            goto L_0x0a24
        L_0x09d5:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            int r9 = com.google.android.gms.internal.vision.zzld.zzk(r14, r11)
            r15.zzj((int) r10, (int) r9)
            goto L_0x0a24
        L_0x09e5:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzld.zzl(r14, r11)
            r15.zza((int) r10, (long) r11)
            goto L_0x0a24
        L_0x09f5:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            long r11 = com.google.android.gms.internal.vision.zzld.zzl(r14, r11)
            r15.zzi(r10, r11)
            goto L_0x0a24
        L_0x0a05:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            float r9 = com.google.android.gms.internal.vision.zzld.zzn(r14, r11)
            r15.zza((int) r10, (float) r9)
            goto L_0x0a24
        L_0x0a15:
            boolean r11 = r13.zzc(r14, r8)
            if (r11 == 0) goto L_0x0a24
            r9 = r9 & r6
            long r11 = (long) r9
            double r11 = com.google.android.gms.internal.vision.zzld.zzo(r14, r11)
            r15.zza((int) r10, (double) r11)
        L_0x0a24:
            int r8 = r8 + 3
            goto L_0x0550
        L_0x0a28:
            if (r1 == 0) goto L_0x0a3e
            com.google.android.gms.internal.vision.zzhq<?> r2 = r13.zzaaz
            r2.zza(r15, r1)
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0a3c
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            goto L_0x0a28
        L_0x0a3c:
            r1 = r3
            goto L_0x0a28
        L_0x0a3e:
            com.google.android.gms.internal.vision.zzkx<?, ?> r0 = r13.zzaay
            zza(r0, r14, (com.google.android.gms.internal.vision.zzlq) r15)
            return
        L_0x0a44:
            r13.zzb(r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Object, com.google.android.gms.internal.vision.zzlq):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:170:0x0495  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzb(T r18, com.google.android.gms.internal.vision.zzlq r19) throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            boolean r3 = r0.zzaap
            if (r3 == 0) goto L_0x0023
            com.google.android.gms.internal.vision.zzhq<?> r3 = r0.zzaaz
            com.google.android.gms.internal.vision.zzht r3 = r3.zzh(r1)
            com.google.android.gms.internal.vision.zzkg<T, java.lang.Object> r5 = r3.zzux
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0023
            java.util.Iterator r3 = r3.iterator()
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x0025
        L_0x0023:
            r3 = 0
            r5 = 0
        L_0x0025:
            int[] r6 = r0.zzaak
            int r6 = r6.length
            sun.misc.Unsafe r7 = zzaaj
            r10 = 0
            r11 = 1048575(0xfffff, float:1.469367E-39)
            r12 = 0
        L_0x002f:
            if (r10 >= r6) goto L_0x0493
            int r13 = r0.zzbx(r10)
            int[] r14 = r0.zzaak
            r15 = r14[r10]
            r16 = 267386880(0xff00000, float:2.3665827E-29)
            r16 = r13 & r16
            int r4 = r16 >>> 20
            boolean r9 = r0.zzaar
            if (r9 != 0) goto L_0x005e
            r9 = 17
            if (r4 > r9) goto L_0x005e
            int r9 = r10 + 2
            r9 = r14[r9]
            r14 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r9 & r14
            if (r8 == r11) goto L_0x0058
            long r11 = (long) r8
            int r12 = r7.getInt(r1, r11)
            r11 = r8
        L_0x0058:
            int r8 = r9 >>> 20
            r9 = 1
            int r8 = r9 << r8
            goto L_0x005f
        L_0x005e:
            r8 = 0
        L_0x005f:
            if (r5 == 0) goto L_0x007d
            com.google.android.gms.internal.vision.zzhq<?> r9 = r0.zzaaz
            int r9 = r9.zza(r5)
            if (r9 > r15) goto L_0x007d
            com.google.android.gms.internal.vision.zzhq<?> r9 = r0.zzaaz
            r9.zza(r2, r5)
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x007b
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            goto L_0x005f
        L_0x007b:
            r5 = 0
            goto L_0x005f
        L_0x007d:
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r13 & r9
            long r13 = (long) r13
            switch(r4) {
                case 0: goto L_0x0484;
                case 1: goto L_0x0478;
                case 2: goto L_0x046c;
                case 3: goto L_0x0460;
                case 4: goto L_0x0454;
                case 5: goto L_0x0448;
                case 6: goto L_0x043c;
                case 7: goto L_0x0430;
                case 8: goto L_0x0424;
                case 9: goto L_0x0413;
                case 10: goto L_0x0404;
                case 11: goto L_0x03f7;
                case 12: goto L_0x03ea;
                case 13: goto L_0x03dd;
                case 14: goto L_0x03d0;
                case 15: goto L_0x03c3;
                case 16: goto L_0x03b6;
                case 17: goto L_0x03a5;
                case 18: goto L_0x0395;
                case 19: goto L_0x0385;
                case 20: goto L_0x0375;
                case 21: goto L_0x0365;
                case 22: goto L_0x0355;
                case 23: goto L_0x0345;
                case 24: goto L_0x0335;
                case 25: goto L_0x0325;
                case 26: goto L_0x0316;
                case 27: goto L_0x0303;
                case 28: goto L_0x02f4;
                case 29: goto L_0x02e4;
                case 30: goto L_0x02d4;
                case 31: goto L_0x02c4;
                case 32: goto L_0x02b4;
                case 33: goto L_0x02a4;
                case 34: goto L_0x0294;
                case 35: goto L_0x0284;
                case 36: goto L_0x0274;
                case 37: goto L_0x0264;
                case 38: goto L_0x0254;
                case 39: goto L_0x0244;
                case 40: goto L_0x0234;
                case 41: goto L_0x0224;
                case 42: goto L_0x0214;
                case 43: goto L_0x0204;
                case 44: goto L_0x01f4;
                case 45: goto L_0x01e4;
                case 46: goto L_0x01d4;
                case 47: goto L_0x01c4;
                case 48: goto L_0x01b4;
                case 49: goto L_0x01a1;
                case 50: goto L_0x0198;
                case 51: goto L_0x0189;
                case 52: goto L_0x017a;
                case 53: goto L_0x016b;
                case 54: goto L_0x015c;
                case 55: goto L_0x014d;
                case 56: goto L_0x013e;
                case 57: goto L_0x012f;
                case 58: goto L_0x0120;
                case 59: goto L_0x0111;
                case 60: goto L_0x00fe;
                case 61: goto L_0x00ee;
                case 62: goto L_0x00e0;
                case 63: goto L_0x00d2;
                case 64: goto L_0x00c4;
                case 65: goto L_0x00b6;
                case 66: goto L_0x00a8;
                case 67: goto L_0x009a;
                case 68: goto L_0x0088;
                default: goto L_0x0085;
            }
        L_0x0085:
            r4 = 0
            goto L_0x048f
        L_0x0088:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r10)
            r2.zzb((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.vision.zzkf) r8)
            goto L_0x0085
        L_0x009a:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            long r13 = zzi(r1, r13)
            r2.zzb((int) r15, (long) r13)
            goto L_0x0085
        L_0x00a8:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            int r4 = zzh(r1, r13)
            r2.zzl(r15, r4)
            goto L_0x0085
        L_0x00b6:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            long r13 = zzi(r1, r13)
            r2.zzj((int) r15, (long) r13)
            goto L_0x0085
        L_0x00c4:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            int r4 = zzh(r1, r13)
            r2.zzt(r15, r4)
            goto L_0x0085
        L_0x00d2:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            int r4 = zzh(r1, r13)
            r2.zzu(r15, r4)
            goto L_0x0085
        L_0x00e0:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            int r4 = zzh(r1, r13)
            r2.zzk(r15, r4)
            goto L_0x0085
        L_0x00ee:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzgs r4 = (com.google.android.gms.internal.vision.zzgs) r4
            r2.zza((int) r15, (com.google.android.gms.internal.vision.zzgs) r4)
            goto L_0x0085
        L_0x00fe:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzkf r8 = r0.zzbu(r10)
            r2.zza((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.vision.zzkf) r8)
            goto L_0x0085
        L_0x0111:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            java.lang.Object r4 = r7.getObject(r1, r13)
            zza((int) r15, (java.lang.Object) r4, (com.google.android.gms.internal.vision.zzlq) r2)
            goto L_0x0085
        L_0x0120:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            boolean r4 = zzj(r1, r13)
            r2.zza((int) r15, (boolean) r4)
            goto L_0x0085
        L_0x012f:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            int r4 = zzh(r1, r13)
            r2.zzm(r15, r4)
            goto L_0x0085
        L_0x013e:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            long r13 = zzi(r1, r13)
            r2.zzc(r15, r13)
            goto L_0x0085
        L_0x014d:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            int r4 = zzh(r1, r13)
            r2.zzj((int) r15, (int) r4)
            goto L_0x0085
        L_0x015c:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            long r13 = zzi(r1, r13)
            r2.zza((int) r15, (long) r13)
            goto L_0x0085
        L_0x016b:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            long r13 = zzi(r1, r13)
            r2.zzi(r15, r13)
            goto L_0x0085
        L_0x017a:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            float r4 = zzg(r1, r13)
            r2.zza((int) r15, (float) r4)
            goto L_0x0085
        L_0x0189:
            boolean r4 = r0.zzb(r1, (int) r15, (int) r10)
            if (r4 == 0) goto L_0x0085
            double r13 = zzf(r1, r13)
            r2.zza((int) r15, (double) r13)
            goto L_0x0085
        L_0x0198:
            java.lang.Object r4 = r7.getObject(r1, r13)
            r0.zza((com.google.android.gms.internal.vision.zzlq) r2, (int) r15, (java.lang.Object) r4, (int) r10)
            goto L_0x0085
        L_0x01a1:
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkf r13 = r0.zzbu(r10)
            com.google.android.gms.internal.vision.zzkh.zzb((int) r4, (java.util.List<?>) r8, (com.google.android.gms.internal.vision.zzlq) r2, (com.google.android.gms.internal.vision.zzkf) r13)
            goto L_0x0085
        L_0x01b4:
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            r15 = 1
            com.google.android.gms.internal.vision.zzkh.zze(r4, r8, r2, r15)
            goto L_0x0085
        L_0x01c4:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzj(r4, r8, r2, r15)
            goto L_0x0085
        L_0x01d4:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzg(r4, r8, r2, r15)
            goto L_0x0085
        L_0x01e4:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzl(r4, r8, r2, r15)
            goto L_0x0085
        L_0x01f4:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzm(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0204:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzi(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0214:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzn(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0224:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzk(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0234:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzf(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0244:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzh(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0254:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzd(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0264:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzc(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0274:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzb((int) r4, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.vision.zzlq) r2, (boolean) r15)
            goto L_0x0085
        L_0x0284:
            r15 = 1
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zza((int) r4, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.vision.zzlq) r2, (boolean) r15)
            goto L_0x0085
        L_0x0294:
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            r15 = 0
            com.google.android.gms.internal.vision.zzkh.zze(r4, r8, r2, r15)
            goto L_0x0085
        L_0x02a4:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzj(r4, r8, r2, r15)
            goto L_0x0085
        L_0x02b4:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzg(r4, r8, r2, r15)
            goto L_0x0085
        L_0x02c4:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzl(r4, r8, r2, r15)
            goto L_0x0085
        L_0x02d4:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzm(r4, r8, r2, r15)
            goto L_0x0085
        L_0x02e4:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzi(r4, r8, r2, r15)
            goto L_0x0085
        L_0x02f4:
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzb(r4, r8, r2)
            goto L_0x0085
        L_0x0303:
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkf r13 = r0.zzbu(r10)
            com.google.android.gms.internal.vision.zzkh.zza((int) r4, (java.util.List<?>) r8, (com.google.android.gms.internal.vision.zzlq) r2, (com.google.android.gms.internal.vision.zzkf) r13)
            goto L_0x0085
        L_0x0316:
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zza((int) r4, (java.util.List<java.lang.String>) r8, (com.google.android.gms.internal.vision.zzlq) r2)
            goto L_0x0085
        L_0x0325:
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            r15 = 0
            com.google.android.gms.internal.vision.zzkh.zzn(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0335:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzk(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0345:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzf(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0355:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzh(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0365:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzd(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0375:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzc(r4, r8, r2, r15)
            goto L_0x0085
        L_0x0385:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zzb((int) r4, (java.util.List<java.lang.Float>) r8, (com.google.android.gms.internal.vision.zzlq) r2, (boolean) r15)
            goto L_0x0085
        L_0x0395:
            r15 = 0
            int[] r4 = r0.zzaak
            r4 = r4[r10]
            java.lang.Object r8 = r7.getObject(r1, r13)
            java.util.List r8 = (java.util.List) r8
            com.google.android.gms.internal.vision.zzkh.zza((int) r4, (java.util.List<java.lang.Double>) r8, (com.google.android.gms.internal.vision.zzlq) r2, (boolean) r15)
            goto L_0x0085
        L_0x03a5:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            java.lang.Object r8 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzkf r13 = r0.zzbu(r10)
            r2.zzb((int) r15, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzkf) r13)
            goto L_0x048f
        L_0x03b6:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            long r13 = r7.getLong(r1, r13)
            r2.zzb((int) r15, (long) r13)
            goto L_0x048f
        L_0x03c3:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            int r8 = r7.getInt(r1, r13)
            r2.zzl(r15, r8)
            goto L_0x048f
        L_0x03d0:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            long r13 = r7.getLong(r1, r13)
            r2.zzj((int) r15, (long) r13)
            goto L_0x048f
        L_0x03dd:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            int r8 = r7.getInt(r1, r13)
            r2.zzt(r15, r8)
            goto L_0x048f
        L_0x03ea:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            int r8 = r7.getInt(r1, r13)
            r2.zzu(r15, r8)
            goto L_0x048f
        L_0x03f7:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            int r8 = r7.getInt(r1, r13)
            r2.zzk(r15, r8)
            goto L_0x048f
        L_0x0404:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            java.lang.Object r8 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzgs r8 = (com.google.android.gms.internal.vision.zzgs) r8
            r2.zza((int) r15, (com.google.android.gms.internal.vision.zzgs) r8)
            goto L_0x048f
        L_0x0413:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            java.lang.Object r8 = r7.getObject(r1, r13)
            com.google.android.gms.internal.vision.zzkf r13 = r0.zzbu(r10)
            r2.zza((int) r15, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzkf) r13)
            goto L_0x048f
        L_0x0424:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            java.lang.Object r8 = r7.getObject(r1, r13)
            zza((int) r15, (java.lang.Object) r8, (com.google.android.gms.internal.vision.zzlq) r2)
            goto L_0x048f
        L_0x0430:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            boolean r8 = com.google.android.gms.internal.vision.zzld.zzm(r1, r13)
            r2.zza((int) r15, (boolean) r8)
            goto L_0x048f
        L_0x043c:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            int r8 = r7.getInt(r1, r13)
            r2.zzm(r15, r8)
            goto L_0x048f
        L_0x0448:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            long r13 = r7.getLong(r1, r13)
            r2.zzc(r15, r13)
            goto L_0x048f
        L_0x0454:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            int r8 = r7.getInt(r1, r13)
            r2.zzj((int) r15, (int) r8)
            goto L_0x048f
        L_0x0460:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            long r13 = r7.getLong(r1, r13)
            r2.zza((int) r15, (long) r13)
            goto L_0x048f
        L_0x046c:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            long r13 = r7.getLong(r1, r13)
            r2.zzi(r15, r13)
            goto L_0x048f
        L_0x0478:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            float r8 = com.google.android.gms.internal.vision.zzld.zzn(r1, r13)
            r2.zza((int) r15, (float) r8)
            goto L_0x048f
        L_0x0484:
            r4 = 0
            r8 = r8 & r12
            if (r8 == 0) goto L_0x048f
            double r13 = com.google.android.gms.internal.vision.zzld.zzo(r1, r13)
            r2.zza((int) r15, (double) r13)
        L_0x048f:
            int r10 = r10 + 3
            goto L_0x002f
        L_0x0493:
            if (r5 == 0) goto L_0x04aa
            com.google.android.gms.internal.vision.zzhq<?> r4 = r0.zzaaz
            r4.zza(r2, r5)
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x04a8
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            r5 = r4
            goto L_0x0493
        L_0x04a8:
            r5 = 0
            goto L_0x0493
        L_0x04aa:
            com.google.android.gms.internal.vision.zzkx<?, ?> r3 = r0.zzaay
            zza(r3, r1, (com.google.android.gms.internal.vision.zzlq) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zzb(java.lang.Object, com.google.android.gms.internal.vision.zzlq):void");
    }

    private final <K, V> void zza(zzlq zzlq, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzlq.zza(i, this.zzaba.zzs(zzbv(i2)), this.zzaba.zzo(obj));
        }
    }

    private static <UT, UB> void zza(zzkx<UT, UB> zzkx, T t, zzlq zzlq) throws IOException {
        zzkx.zza(zzkx.zzy(t), zzlq);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:149|150|(1:152)|153|(5:174|155|(2:158|156)|256|(2:160|264)(1:265))(1:252)) */
    /* JADX WARNING: Code restructure failed: missing block: B:150:?, code lost:
        r8.zza(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x05a0, code lost:
        if (r10 == null) goto L_0x05a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x05a2, code lost:
        r10 = r8.zzz(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x05ab, code lost:
        if (r8.zza(r10, r14) == false) goto L_0x05ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x05ad, code lost:
        r14 = r12.zzaau;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x05b1, code lost:
        if (r14 < r12.zzaav) goto L_0x05b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x05b3, code lost:
        r10 = zza((java.lang.Object) r13, r12.zzaat[r14], r10, r8);
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x05be, code lost:
        if (r10 != null) goto L_0x05c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x05c0, code lost:
        r8.zzg(r13, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:264:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:149:0x059d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r13, com.google.android.gms.internal.vision.zzkc r14, com.google.android.gms.internal.vision.zzho r15) throws java.io.IOException {
        /*
            r12 = this;
            r0 = 0
            if (r15 == 0) goto L_0x05dc
            com.google.android.gms.internal.vision.zzkx<?, ?> r8 = r12.zzaay
            com.google.android.gms.internal.vision.zzhq<?> r9 = r12.zzaaz
            r1 = r0
            r10 = r1
        L_0x0009:
            int r2 = r14.zzeo()     // Catch:{ all -> 0x05c4 }
            int r3 = r12.zzca(r2)     // Catch:{ all -> 0x05c4 }
            if (r3 >= 0) goto L_0x0077
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r2 != r3) goto L_0x002f
            int r14 = r12.zzaau
        L_0x001a:
            int r15 = r12.zzaav
            if (r14 >= r15) goto L_0x0029
            int[] r15 = r12.zzaat
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza((java.lang.Object) r13, (int) r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x001a
        L_0x0029:
            if (r10 == 0) goto L_0x002e
            r8.zzg(r13, r10)
        L_0x002e:
            return
        L_0x002f:
            boolean r3 = r12.zzaap     // Catch:{ all -> 0x05c4 }
            if (r3 != 0) goto L_0x0035
            r3 = r0
            goto L_0x003c
        L_0x0035:
            com.google.android.gms.internal.vision.zzjn r3 = r12.zzaao     // Catch:{ all -> 0x05c4 }
            java.lang.Object r2 = r9.zza(r15, r3, r2)     // Catch:{ all -> 0x05c4 }
            r3 = r2
        L_0x003c:
            if (r3 == 0) goto L_0x0051
            if (r1 != 0) goto L_0x0044
            com.google.android.gms.internal.vision.zzht r1 = r9.zzi(r13)     // Catch:{ all -> 0x05c4 }
        L_0x0044:
            r11 = r1
            r1 = r9
            r2 = r14
            r4 = r15
            r5 = r11
            r6 = r10
            r7 = r8
            java.lang.Object r10 = r1.zza(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x05c4 }
            r1 = r11
            goto L_0x0009
        L_0x0051:
            r8.zza(r14)     // Catch:{ all -> 0x05c4 }
            if (r10 != 0) goto L_0x005a
            java.lang.Object r10 = r8.zzz(r13)     // Catch:{ all -> 0x05c4 }
        L_0x005a:
            boolean r2 = r8.zza(r10, (com.google.android.gms.internal.vision.zzkc) r14)     // Catch:{ all -> 0x05c4 }
            if (r2 != 0) goto L_0x0009
            int r14 = r12.zzaau
        L_0x0062:
            int r15 = r12.zzaav
            if (r14 >= r15) goto L_0x0071
            int[] r15 = r12.zzaat
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza((java.lang.Object) r13, (int) r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x0062
        L_0x0071:
            if (r10 == 0) goto L_0x0076
            r8.zzg(r13, r10)
        L_0x0076:
            return
        L_0x0077:
            int r4 = r12.zzbx(r3)     // Catch:{ all -> 0x05c4 }
            r5 = 267386880(0xff00000, float:2.3665827E-29)
            r5 = r5 & r4
            int r5 = r5 >>> 20
            r6 = 1048575(0xfffff, float:1.469367E-39)
            switch(r5) {
                case 0: goto L_0x0571;
                case 1: goto L_0x0562;
                case 2: goto L_0x0553;
                case 3: goto L_0x0544;
                case 4: goto L_0x0535;
                case 5: goto L_0x0526;
                case 6: goto L_0x0517;
                case 7: goto L_0x0508;
                case 8: goto L_0x0500;
                case 9: goto L_0x04cf;
                case 10: goto L_0x04c0;
                case 11: goto L_0x04b1;
                case 12: goto L_0x048f;
                case 13: goto L_0x0480;
                case 14: goto L_0x0471;
                case 15: goto L_0x0462;
                case 16: goto L_0x0453;
                case 17: goto L_0x0422;
                case 18: goto L_0x0414;
                case 19: goto L_0x0406;
                case 20: goto L_0x03f8;
                case 21: goto L_0x03ea;
                case 22: goto L_0x03dc;
                case 23: goto L_0x03ce;
                case 24: goto L_0x03c0;
                case 25: goto L_0x03b2;
                case 26: goto L_0x0390;
                case 27: goto L_0x037e;
                case 28: goto L_0x0370;
                case 29: goto L_0x0362;
                case 30: goto L_0x034d;
                case 31: goto L_0x033f;
                case 32: goto L_0x0331;
                case 33: goto L_0x0323;
                case 34: goto L_0x0315;
                case 35: goto L_0x0307;
                case 36: goto L_0x02f9;
                case 37: goto L_0x02eb;
                case 38: goto L_0x02dd;
                case 39: goto L_0x02cf;
                case 40: goto L_0x02c1;
                case 41: goto L_0x02b3;
                case 42: goto L_0x02a5;
                case 43: goto L_0x0297;
                case 44: goto L_0x0282;
                case 45: goto L_0x0274;
                case 46: goto L_0x0266;
                case 47: goto L_0x0258;
                case 48: goto L_0x024a;
                case 49: goto L_0x0238;
                case 50: goto L_0x01f6;
                case 51: goto L_0x01e4;
                case 52: goto L_0x01d2;
                case 53: goto L_0x01c0;
                case 54: goto L_0x01ae;
                case 55: goto L_0x019c;
                case 56: goto L_0x018a;
                case 57: goto L_0x0178;
                case 58: goto L_0x0166;
                case 59: goto L_0x015e;
                case 60: goto L_0x012d;
                case 61: goto L_0x011f;
                case 62: goto L_0x010d;
                case 63: goto L_0x00e8;
                case 64: goto L_0x00d6;
                case 65: goto L_0x00c4;
                case 66: goto L_0x00b2;
                case 67: goto L_0x00a0;
                case 68: goto L_0x008e;
                default: goto L_0x0086;
            }
        L_0x0086:
            if (r10 != 0) goto L_0x0580
            java.lang.Object r10 = r8.zzjd()     // Catch:{ zzim -> 0x059d }
            goto L_0x0580
        L_0x008e:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r6 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r6 = r14.zzc(r6, r15)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x00a0:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzfd()     // Catch:{ zzim -> 0x059d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x00b2:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            int r6 = r14.zzfc()     // Catch:{ zzim -> 0x059d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x00c4:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzfb()     // Catch:{ zzim -> 0x059d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x00d6:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            int r6 = r14.zzfa()     // Catch:{ zzim -> 0x059d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x00e8:
            int r5 = r14.zzez()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzij r7 = r12.zzbw(r3)     // Catch:{ zzim -> 0x059d }
            if (r7 == 0) goto L_0x00ff
            boolean r7 = r7.zzg(r5)     // Catch:{ zzim -> 0x059d }
            if (r7 == 0) goto L_0x00f9
            goto L_0x00ff
        L_0x00f9:
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzkh.zza((int) r2, (int) r5, r10, r8)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x00ff:
            r4 = r4 & r6
            long r6 = (long) r4     // Catch:{ zzim -> 0x059d }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r6, (java.lang.Object) r4)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x010d:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            int r6 = r14.zzey()     // Catch:{ zzim -> 0x059d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x011f:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzgs r6 = r14.zzex()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x012d:
            boolean r5 = r12.zzb(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            if (r5 == 0) goto L_0x0149
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            java.lang.Object r6 = com.google.android.gms.internal.vision.zzld.zzp(r13, r4)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r7 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r7 = r14.zza(r7, (com.google.android.gms.internal.vision.zzho) r15)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r6 = com.google.android.gms.internal.vision.zzie.zzb(r6, r7)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            goto L_0x0159
        L_0x0149:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r6 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r6 = r14.zza(r6, (com.google.android.gms.internal.vision.zzho) r15)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
        L_0x0159:
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x015e:
            r12.zza((java.lang.Object) r13, (int) r4, (com.google.android.gms.internal.vision.zzkc) r14)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0166:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            boolean r6 = r14.zzev()     // Catch:{ zzim -> 0x059d }
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0178:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            int r6 = r14.zzeu()     // Catch:{ zzim -> 0x059d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x018a:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzet()     // Catch:{ zzim -> 0x059d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x019c:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            int r6 = r14.zzes()     // Catch:{ zzim -> 0x059d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x01ae:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzeq()     // Catch:{ zzim -> 0x059d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x01c0:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzer()     // Catch:{ zzim -> 0x059d }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x01d2:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            float r6 = r14.readFloat()     // Catch:{ zzim -> 0x059d }
            java.lang.Float r6 = java.lang.Float.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x01e4:
            r4 = r4 & r6
            long r4 = (long) r4     // Catch:{ zzim -> 0x059d }
            double r6 = r14.readDouble()     // Catch:{ zzim -> 0x059d }
            java.lang.Double r6 = java.lang.Double.valueOf(r6)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzc(r13, (int) r2, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x01f6:
            java.lang.Object r2 = r12.zzbv(r3)     // Catch:{ zzim -> 0x059d }
            int r3 = r12.zzbx(r3)     // Catch:{ zzim -> 0x059d }
            r3 = r3 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzld.zzp(r13, r3)     // Catch:{ zzim -> 0x059d }
            if (r5 != 0) goto L_0x0210
            com.google.android.gms.internal.vision.zzjg r5 = r12.zzaba     // Catch:{ zzim -> 0x059d }
            java.lang.Object r5 = r5.zzr(r2)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r3, (java.lang.Object) r5)     // Catch:{ zzim -> 0x059d }
            goto L_0x0227
        L_0x0210:
            com.google.android.gms.internal.vision.zzjg r6 = r12.zzaba     // Catch:{ zzim -> 0x059d }
            boolean r6 = r6.zzp(r5)     // Catch:{ zzim -> 0x059d }
            if (r6 == 0) goto L_0x0227
            com.google.android.gms.internal.vision.zzjg r6 = r12.zzaba     // Catch:{ zzim -> 0x059d }
            java.lang.Object r6 = r6.zzr(r2)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzjg r7 = r12.zzaba     // Catch:{ zzim -> 0x059d }
            r7.zzc(r6, r5)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r3, (java.lang.Object) r6)     // Catch:{ zzim -> 0x059d }
            r5 = r6
        L_0x0227:
            com.google.android.gms.internal.vision.zzjg r3 = r12.zzaba     // Catch:{ zzim -> 0x059d }
            java.util.Map r3 = r3.zzn(r5)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzjg r4 = r12.zzaba     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzje r2 = r4.zzs(r2)     // Catch:{ zzim -> 0x059d }
            r14.zza(r3, r2, (com.google.android.gms.internal.vision.zzho) r15)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0238:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r2 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzix r3 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            java.util.List r3 = r3.zza(r13, r4)     // Catch:{ zzim -> 0x059d }
            r14.zzb(r3, r2, r15)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x024a:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzp(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0258:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzo(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0266:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzn(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0274:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzm(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0282:
            com.google.android.gms.internal.vision.zzix r5 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r4 = r4 & r6
            long r6 = (long) r4     // Catch:{ zzim -> 0x059d }
            java.util.List r4 = r5.zza(r13, r6)     // Catch:{ zzim -> 0x059d }
            r14.zzl(r4)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzij r3 = r12.zzbw(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzkh.zza(r2, r4, r3, r10, r8)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0297:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzk(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x02a5:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzh(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x02b3:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzg(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x02c1:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzf(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x02cf:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zze(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x02dd:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzc(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x02eb:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzd(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x02f9:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzb(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0307:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zza(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0315:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzp(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0323:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzo(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0331:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzn(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x033f:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzm(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x034d:
            com.google.android.gms.internal.vision.zzix r5 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r4 = r4 & r6
            long r6 = (long) r4     // Catch:{ zzim -> 0x059d }
            java.util.List r4 = r5.zza(r13, r6)     // Catch:{ zzim -> 0x059d }
            r14.zzl(r4)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzij r3 = r12.zzbw(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzkh.zza(r2, r4, r3, r10, r8)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0362:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzk(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0370:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzj(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x037e:
            com.google.android.gms.internal.vision.zzkf r2 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzix r5 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            java.util.List r3 = r5.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zza(r3, r2, (com.google.android.gms.internal.vision.zzho) r15)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0390:
            boolean r2 = zzbz(r4)     // Catch:{ zzim -> 0x059d }
            if (r2 == 0) goto L_0x03a4
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzi(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x03a4:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.readStringList(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x03b2:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzh(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x03c0:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzg(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x03ce:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzf(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x03dc:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zze(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x03ea:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzc(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x03f8:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzd(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0406:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zzb(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0414:
            com.google.android.gms.internal.vision.zzix r2 = r12.zzaax     // Catch:{ zzim -> 0x059d }
            r3 = r4 & r6
            long r3 = (long) r3     // Catch:{ zzim -> 0x059d }
            java.util.List r2 = r2.zza(r13, r3)     // Catch:{ zzim -> 0x059d }
            r14.zza(r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0422:
            boolean r2 = r12.zzc(r13, r3)     // Catch:{ zzim -> 0x059d }
            if (r2 == 0) goto L_0x0440
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            java.lang.Object r2 = com.google.android.gms.internal.vision.zzld.zzp(r13, r4)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r3 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r3 = r14.zzc(r3, r15)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r2 = com.google.android.gms.internal.vision.zzie.zzb(r2, r3)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0440:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r2 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r2 = r14.zzc(r2, r15)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0453:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzfd()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (long) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0462:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            int r2 = r14.zzfc()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zzb((java.lang.Object) r13, (long) r4, (int) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0471:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzfb()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (long) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0480:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            int r2 = r14.zzfa()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zzb((java.lang.Object) r13, (long) r4, (int) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x048f:
            int r5 = r14.zzez()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzij r7 = r12.zzbw(r3)     // Catch:{ zzim -> 0x059d }
            if (r7 == 0) goto L_0x04a6
            boolean r7 = r7.zzg(r5)     // Catch:{ zzim -> 0x059d }
            if (r7 == 0) goto L_0x04a0
            goto L_0x04a6
        L_0x04a0:
            java.lang.Object r10 = com.google.android.gms.internal.vision.zzkh.zza((int) r2, (int) r5, r10, r8)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x04a6:
            r2 = r4 & r6
            long r6 = (long) r2     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zzb((java.lang.Object) r13, (long) r6, (int) r5)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x04b1:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            int r2 = r14.zzey()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zzb((java.lang.Object) r13, (long) r4, (int) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x04c0:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzgs r2 = r14.zzex()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x04cf:
            boolean r2 = r12.zzc(r13, r3)     // Catch:{ zzim -> 0x059d }
            if (r2 == 0) goto L_0x04ed
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            java.lang.Object r2 = com.google.android.gms.internal.vision.zzld.zzp(r13, r4)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r3 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r3 = r14.zza(r3, (com.google.android.gms.internal.vision.zzho) r15)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r2 = com.google.android.gms.internal.vision.zzie.zzb(r2, r3)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r2)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x04ed:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzkf r2 = r12.zzbu(r3)     // Catch:{ zzim -> 0x059d }
            java.lang.Object r2 = r14.zza(r2, (com.google.android.gms.internal.vision.zzho) r15)     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (java.lang.Object) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0500:
            r12.zza((java.lang.Object) r13, (int) r4, (com.google.android.gms.internal.vision.zzkc) r14)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0508:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            boolean r2 = r14.zzev()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (boolean) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0517:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            int r2 = r14.zzeu()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zzb((java.lang.Object) r13, (long) r4, (int) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0526:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzet()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (long) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0535:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            int r2 = r14.zzes()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zzb((java.lang.Object) r13, (long) r4, (int) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0544:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzeq()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (long) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0553:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            long r6 = r14.zzer()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (long) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0562:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            float r2 = r14.readFloat()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (float) r2)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0571:
            r2 = r4 & r6
            long r4 = (long) r2     // Catch:{ zzim -> 0x059d }
            double r6 = r14.readDouble()     // Catch:{ zzim -> 0x059d }
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r13, (long) r4, (double) r6)     // Catch:{ zzim -> 0x059d }
            r12.zzd(r13, (int) r3)     // Catch:{ zzim -> 0x059d }
            goto L_0x0009
        L_0x0580:
            boolean r2 = r8.zza(r10, (com.google.android.gms.internal.vision.zzkc) r14)     // Catch:{ zzim -> 0x059d }
            if (r2 != 0) goto L_0x0009
            int r14 = r12.zzaau
        L_0x0588:
            int r15 = r12.zzaav
            if (r14 >= r15) goto L_0x0597
            int[] r15 = r12.zzaat
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza((java.lang.Object) r13, (int) r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x0588
        L_0x0597:
            if (r10 == 0) goto L_0x059c
            r8.zzg(r13, r10)
        L_0x059c:
            return
        L_0x059d:
            r8.zza(r14)     // Catch:{ all -> 0x05c4 }
            if (r10 != 0) goto L_0x05a7
            java.lang.Object r2 = r8.zzz(r13)     // Catch:{ all -> 0x05c4 }
            r10 = r2
        L_0x05a7:
            boolean r2 = r8.zza(r10, (com.google.android.gms.internal.vision.zzkc) r14)     // Catch:{ all -> 0x05c4 }
            if (r2 != 0) goto L_0x0009
            int r14 = r12.zzaau
        L_0x05af:
            int r15 = r12.zzaav
            if (r14 >= r15) goto L_0x05be
            int[] r15 = r12.zzaat
            r15 = r15[r14]
            java.lang.Object r10 = r12.zza((java.lang.Object) r13, (int) r15, r10, r8)
            int r14 = r14 + 1
            goto L_0x05af
        L_0x05be:
            if (r10 == 0) goto L_0x05c3
            r8.zzg(r13, r10)
        L_0x05c3:
            return
        L_0x05c4:
            r14 = move-exception
            int r15 = r12.zzaau
        L_0x05c7:
            int r0 = r12.zzaav
            if (r15 >= r0) goto L_0x05d6
            int[] r0 = r12.zzaat
            r0 = r0[r15]
            java.lang.Object r10 = r12.zza((java.lang.Object) r13, (int) r0, r10, r8)
            int r15 = r15 + 1
            goto L_0x05c7
        L_0x05d6:
            if (r10 == 0) goto L_0x05db
            r8.zzg(r13, r10)
        L_0x05db:
            throw r14
        L_0x05dc:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Object, com.google.android.gms.internal.vision.zzkc, com.google.android.gms.internal.vision.zzho):void");
    }

    private static zzkw zzv(Object obj) {
        zzid zzid = (zzid) obj;
        zzkw zzkw = zzid.zzxz;
        if (zzkw != zzkw.zzja()) {
            return zzkw;
        }
        zzkw zzjb = zzkw.zzjb();
        zzid.zzxz = zzjb;
        return zzjb;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return r2 + 8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zza(byte[] r1, int r2, int r3, com.google.android.gms.internal.vision.zzlk r4, java.lang.Class<?> r5, com.google.android.gms.internal.vision.zzgm r6) throws java.io.IOException {
        /*
            int[] r0 = com.google.android.gms.internal.vision.zzjq.zztn
            int r4 = r4.ordinal()
            r4 = r0[r4]
            switch(r4) {
                case 1: goto L_0x0099;
                case 2: goto L_0x0094;
                case 3: goto L_0x0087;
                case 4: goto L_0x007a;
                case 5: goto L_0x007a;
                case 6: goto L_0x006f;
                case 7: goto L_0x006f;
                case 8: goto L_0x0064;
                case 9: goto L_0x0057;
                case 10: goto L_0x0057;
                case 11: goto L_0x0057;
                case 12: goto L_0x004a;
                case 13: goto L_0x004a;
                case 14: goto L_0x003d;
                case 15: goto L_0x002b;
                case 16: goto L_0x0019;
                case 17: goto L_0x0013;
                default: goto L_0x000b;
            }
        L_0x000b:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "unsupported field type."
            r1.<init>(r2)
            throw r1
        L_0x0013:
            int r1 = com.google.android.gms.internal.vision.zzgk.zzd(r1, r2, r6)
            goto L_0x00ae
        L_0x0019:
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r1, r2, r6)
            long r2 = r6.zztl
            long r2 = com.google.android.gms.internal.vision.zzhe.zzr(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zztm = r2
            goto L_0x00ae
        L_0x002b:
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r1, r2, r6)
            int r2 = r6.zztk
            int r2 = com.google.android.gms.internal.vision.zzhe.zzbb(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zztm = r2
            goto L_0x00ae
        L_0x003d:
            com.google.android.gms.internal.vision.zzkb r4 = com.google.android.gms.internal.vision.zzkb.zzik()
            com.google.android.gms.internal.vision.zzkf r4 = r4.zzf(r5)
            int r1 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r4, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.vision.zzgm) r6)
            goto L_0x00ae
        L_0x004a:
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r1, r2, r6)
            long r2 = r6.zztl
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r6.zztm = r2
            goto L_0x00ae
        L_0x0057:
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r1, r2, r6)
            int r2 = r6.zztk
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r6.zztm = r2
            goto L_0x00ae
        L_0x0064:
            float r1 = com.google.android.gms.internal.vision.zzgk.zzd(r1, r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r1)
            r6.zztm = r1
            goto L_0x0084
        L_0x006f:
            long r3 = com.google.android.gms.internal.vision.zzgk.zzb(r1, r2)
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r6.zztm = r1
            goto L_0x0091
        L_0x007a:
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r1, r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r6.zztm = r1
        L_0x0084:
            int r1 = r2 + 4
            goto L_0x00ae
        L_0x0087:
            double r3 = com.google.android.gms.internal.vision.zzgk.zzc(r1, r2)
            java.lang.Double r1 = java.lang.Double.valueOf(r3)
            r6.zztm = r1
        L_0x0091:
            int r1 = r2 + 8
            goto L_0x00ae
        L_0x0094:
            int r1 = com.google.android.gms.internal.vision.zzgk.zze(r1, r2, r6)
            goto L_0x00ae
        L_0x0099:
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r1, r2, r6)
            long r2 = r6.zztl
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00a7
            r2 = 1
            goto L_0x00a8
        L_0x00a7:
            r2 = 0
        L_0x00a8:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r6.zztm = r2
        L_0x00ae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(byte[], int, int, com.google.android.gms.internal.vision.zzlk, java.lang.Class, com.google.android.gms.internal.vision.zzgm):int");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x0422 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01eb  */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, long r25, int r27, long r28, com.google.android.gms.internal.vision.zzgm r30) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r2 = r21
            r6 = r23
            r8 = r24
            r9 = r28
            r7 = r30
            sun.misc.Unsafe r11 = zzaaj
            java.lang.Object r11 = r11.getObject(r1, r9)
            com.google.android.gms.internal.vision.zzik r11 = (com.google.android.gms.internal.vision.zzik) r11
            boolean r12 = r11.zzei()
            r13 = 1
            if (r12 != 0) goto L_0x0036
            int r12 = r11.size()
            if (r12 != 0) goto L_0x002c
            r12 = 10
            goto L_0x002d
        L_0x002c:
            int r12 = r12 << r13
        L_0x002d:
            com.google.android.gms.internal.vision.zzik r11 = r11.zzan(r12)
            sun.misc.Unsafe r12 = zzaaj
            r12.putObject(r1, r9, r11)
        L_0x0036:
            r9 = 5
            r14 = 0
            r10 = 2
            switch(r27) {
                case 18: goto L_0x03e4;
                case 19: goto L_0x03a6;
                case 20: goto L_0x0365;
                case 21: goto L_0x0365;
                case 22: goto L_0x034b;
                case 23: goto L_0x030c;
                case 24: goto L_0x02cd;
                case 25: goto L_0x0276;
                case 26: goto L_0x01c3;
                case 27: goto L_0x01a9;
                case 28: goto L_0x0151;
                case 29: goto L_0x034b;
                case 30: goto L_0x0119;
                case 31: goto L_0x02cd;
                case 32: goto L_0x030c;
                case 33: goto L_0x00cc;
                case 34: goto L_0x007f;
                case 35: goto L_0x03e4;
                case 36: goto L_0x03a6;
                case 37: goto L_0x0365;
                case 38: goto L_0x0365;
                case 39: goto L_0x034b;
                case 40: goto L_0x030c;
                case 41: goto L_0x02cd;
                case 42: goto L_0x0276;
                case 43: goto L_0x034b;
                case 44: goto L_0x0119;
                case 45: goto L_0x02cd;
                case 46: goto L_0x030c;
                case 47: goto L_0x00cc;
                case 48: goto L_0x007f;
                case 49: goto L_0x003f;
                default: goto L_0x003d;
            }
        L_0x003d:
            goto L_0x0422
        L_0x003f:
            r1 = 3
            if (r6 != r1) goto L_0x0422
            com.google.android.gms.internal.vision.zzkf r1 = r0.zzbu(r8)
            r6 = r2 & -8
            r6 = r6 | 4
            r22 = r1
            r23 = r18
            r24 = r19
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r22, (byte[]) r23, (int) r24, (int) r25, (int) r26, (com.google.android.gms.internal.vision.zzgm) r27)
            java.lang.Object r8 = r7.zztm
            r11.add(r8)
        L_0x005f:
            if (r4 >= r5) goto L_0x0422
            int r8 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r9 = r7.zztk
            if (r2 != r9) goto L_0x0422
            r22 = r1
            r23 = r18
            r24 = r8
            r25 = r20
            r26 = r6
            r27 = r30
            int r4 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r22, (byte[]) r23, (int) r24, (int) r25, (int) r26, (com.google.android.gms.internal.vision.zzgm) r27)
            java.lang.Object r8 = r7.zztm
            r11.add(r8)
            goto L_0x005f
        L_0x007f:
            if (r6 != r10) goto L_0x00a3
            com.google.android.gms.internal.vision.zzjb r11 = (com.google.android.gms.internal.vision.zzjb) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r2 = r7.zztk
            int r2 = r2 + r1
        L_0x008a:
            if (r1 >= r2) goto L_0x009a
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r1, r7)
            long r4 = r7.zztl
            long r4 = com.google.android.gms.internal.vision.zzhe.zzr(r4)
            r11.zzac(r4)
            goto L_0x008a
        L_0x009a:
            if (r1 != r2) goto L_0x009e
            goto L_0x0423
        L_0x009e:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x00a3:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzjb r11 = (com.google.android.gms.internal.vision.zzjb) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r7)
            long r8 = r7.zztl
            long r8 = com.google.android.gms.internal.vision.zzhe.zzr(r8)
            r11.zzac(r8)
        L_0x00b4:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r7)
            long r8 = r7.zztl
            long r8 = com.google.android.gms.internal.vision.zzhe.zzr(r8)
            r11.zzac(r8)
            goto L_0x00b4
        L_0x00cc:
            if (r6 != r10) goto L_0x00f0
            com.google.android.gms.internal.vision.zzif r11 = (com.google.android.gms.internal.vision.zzif) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r2 = r7.zztk
            int r2 = r2 + r1
        L_0x00d7:
            if (r1 >= r2) goto L_0x00e7
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r4 = r7.zztk
            int r4 = com.google.android.gms.internal.vision.zzhe.zzbb(r4)
            r11.zzbs(r4)
            goto L_0x00d7
        L_0x00e7:
            if (r1 != r2) goto L_0x00eb
            goto L_0x0423
        L_0x00eb:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x00f0:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzif r11 = (com.google.android.gms.internal.vision.zzif) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r4 = r7.zztk
            int r4 = com.google.android.gms.internal.vision.zzhe.zzbb(r4)
            r11.zzbs(r4)
        L_0x0101:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r4 = r7.zztk
            int r4 = com.google.android.gms.internal.vision.zzhe.zzbb(r4)
            r11.zzbs(r4)
            goto L_0x0101
        L_0x0119:
            if (r6 != r10) goto L_0x0120
            int r2 = com.google.android.gms.internal.vision.zzgk.zza((byte[]) r3, (int) r4, (com.google.android.gms.internal.vision.zzik<?>) r11, (com.google.android.gms.internal.vision.zzgm) r7)
            goto L_0x0131
        L_0x0120:
            if (r6 != 0) goto L_0x0422
            r2 = r21
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r11
            r7 = r30
            int r2 = com.google.android.gms.internal.vision.zzgk.zza((int) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.vision.zzik<?>) r6, (com.google.android.gms.internal.vision.zzgm) r7)
        L_0x0131:
            com.google.android.gms.internal.vision.zzid r1 = (com.google.android.gms.internal.vision.zzid) r1
            com.google.android.gms.internal.vision.zzkw r3 = r1.zzxz
            com.google.android.gms.internal.vision.zzkw r4 = com.google.android.gms.internal.vision.zzkw.zzja()
            if (r3 != r4) goto L_0x013c
            r3 = 0
        L_0x013c:
            com.google.android.gms.internal.vision.zzij r4 = r0.zzbw(r8)
            com.google.android.gms.internal.vision.zzkx<?, ?> r5 = r0.zzaay
            r6 = r22
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzkh.zza(r6, r11, r4, r3, r5)
            com.google.android.gms.internal.vision.zzkw r3 = (com.google.android.gms.internal.vision.zzkw) r3
            if (r3 == 0) goto L_0x014e
            r1.zzxz = r3
        L_0x014e:
            r1 = r2
            goto L_0x0423
        L_0x0151:
            if (r6 != r10) goto L_0x0422
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r4 = r7.zztk
            if (r4 < 0) goto L_0x01a4
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x019f
            if (r4 != 0) goto L_0x0167
            com.google.android.gms.internal.vision.zzgs r4 = com.google.android.gms.internal.vision.zzgs.zztt
            r11.add(r4)
            goto L_0x016f
        L_0x0167:
            com.google.android.gms.internal.vision.zzgs r6 = com.google.android.gms.internal.vision.zzgs.zza(r3, r1, r4)
            r11.add(r6)
        L_0x016e:
            int r1 = r1 + r4
        L_0x016f:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r4 = r7.zztk
            if (r4 < 0) goto L_0x019a
            int r6 = r3.length
            int r6 = r6 - r1
            if (r4 > r6) goto L_0x0195
            if (r4 != 0) goto L_0x018d
            com.google.android.gms.internal.vision.zzgs r4 = com.google.android.gms.internal.vision.zzgs.zztt
            r11.add(r4)
            goto L_0x016f
        L_0x018d:
            com.google.android.gms.internal.vision.zzgs r6 = com.google.android.gms.internal.vision.zzgs.zza(r3, r1, r4)
            r11.add(r6)
            goto L_0x016e
        L_0x0195:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x019a:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhi()
            throw r1
        L_0x019f:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x01a4:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhi()
            throw r1
        L_0x01a9:
            if (r6 != r10) goto L_0x0422
            com.google.android.gms.internal.vision.zzkf r1 = r0.zzbu(r8)
            r22 = r1
            r23 = r21
            r24 = r18
            r25 = r19
            r26 = r20
            r27 = r11
            r28 = r30
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r22, r23, r24, r25, r26, r27, r28)
            goto L_0x0423
        L_0x01c3:
            if (r6 != r10) goto L_0x0422
            r8 = 536870912(0x20000000, double:2.652494739E-315)
            long r8 = r25 & r8
            java.lang.String r1 = ""
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 != 0) goto L_0x0216
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r6 = r7.zztk
            if (r6 < 0) goto L_0x0211
            if (r6 != 0) goto L_0x01de
            r11.add(r1)
            goto L_0x01e9
        L_0x01de:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.vision.zzie.UTF_8
            r8.<init>(r3, r4, r6, r9)
            r11.add(r8)
        L_0x01e8:
            int r4 = r4 + r6
        L_0x01e9:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r8 = r7.zztk
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r6, r7)
            int r6 = r7.zztk
            if (r6 < 0) goto L_0x020c
            if (r6 != 0) goto L_0x0201
            r11.add(r1)
            goto L_0x01e9
        L_0x0201:
            java.lang.String r8 = new java.lang.String
            java.nio.charset.Charset r9 = com.google.android.gms.internal.vision.zzie.UTF_8
            r8.<init>(r3, r4, r6, r9)
            r11.add(r8)
            goto L_0x01e8
        L_0x020c:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhi()
            throw r1
        L_0x0211:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhi()
            throw r1
        L_0x0216:
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r6 = r7.zztk
            if (r6 < 0) goto L_0x0271
            if (r6 != 0) goto L_0x0224
            r11.add(r1)
            goto L_0x0237
        L_0x0224:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.vision.zzlf.zzf((byte[]) r3, (int) r4, (int) r8)
            if (r9 == 0) goto L_0x026c
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.vision.zzie.UTF_8
            r9.<init>(r3, r4, r6, r10)
            r11.add(r9)
        L_0x0236:
            r4 = r8
        L_0x0237:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r8 = r7.zztk
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r6, r7)
            int r6 = r7.zztk
            if (r6 < 0) goto L_0x0267
            if (r6 != 0) goto L_0x024f
            r11.add(r1)
            goto L_0x0237
        L_0x024f:
            int r8 = r4 + r6
            boolean r9 = com.google.android.gms.internal.vision.zzlf.zzf((byte[]) r3, (int) r4, (int) r8)
            if (r9 == 0) goto L_0x0262
            java.lang.String r9 = new java.lang.String
            java.nio.charset.Charset r10 = com.google.android.gms.internal.vision.zzie.UTF_8
            r9.<init>(r3, r4, r6, r10)
            r11.add(r9)
            goto L_0x0236
        L_0x0262:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzho()
            throw r1
        L_0x0267:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhi()
            throw r1
        L_0x026c:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzho()
            throw r1
        L_0x0271:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhi()
            throw r1
        L_0x0276:
            r1 = 0
            if (r6 != r10) goto L_0x029e
            com.google.android.gms.internal.vision.zzgq r11 = (com.google.android.gms.internal.vision.zzgq) r11
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r4 = r7.zztk
            int r4 = r4 + r2
        L_0x0282:
            if (r2 >= r4) goto L_0x0295
            int r2 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r2, r7)
            long r5 = r7.zztl
            int r8 = (r5 > r14 ? 1 : (r5 == r14 ? 0 : -1))
            if (r8 == 0) goto L_0x0290
            r5 = 1
            goto L_0x0291
        L_0x0290:
            r5 = 0
        L_0x0291:
            r11.addBoolean(r5)
            goto L_0x0282
        L_0x0295:
            if (r2 != r4) goto L_0x0299
            goto L_0x014e
        L_0x0299:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x029e:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzgq r11 = (com.google.android.gms.internal.vision.zzgq) r11
            int r4 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r7)
            long r8 = r7.zztl
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x02ae
            r6 = 1
            goto L_0x02af
        L_0x02ae:
            r6 = 0
        L_0x02af:
            r11.addBoolean(r6)
        L_0x02b2:
            if (r4 >= r5) goto L_0x0422
            int r6 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r8 = r7.zztk
            if (r2 != r8) goto L_0x0422
            int r4 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r6, r7)
            long r8 = r7.zztl
            int r6 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r6 == 0) goto L_0x02c8
            r6 = 1
            goto L_0x02c9
        L_0x02c8:
            r6 = 0
        L_0x02c9:
            r11.addBoolean(r6)
            goto L_0x02b2
        L_0x02cd:
            if (r6 != r10) goto L_0x02ed
            com.google.android.gms.internal.vision.zzif r11 = (com.google.android.gms.internal.vision.zzif) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r2 = r7.zztk
            int r2 = r2 + r1
        L_0x02d8:
            if (r1 >= r2) goto L_0x02e4
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1)
            r11.zzbs(r4)
            int r1 = r1 + 4
            goto L_0x02d8
        L_0x02e4:
            if (r1 != r2) goto L_0x02e8
            goto L_0x0423
        L_0x02e8:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x02ed:
            if (r6 != r9) goto L_0x0422
            com.google.android.gms.internal.vision.zzif r11 = (com.google.android.gms.internal.vision.zzif) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r18, r19)
            r11.zzbs(r1)
        L_0x02f8:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4)
            r11.zzbs(r1)
            goto L_0x02f8
        L_0x030c:
            if (r6 != r10) goto L_0x032c
            com.google.android.gms.internal.vision.zzjb r11 = (com.google.android.gms.internal.vision.zzjb) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r2 = r7.zztk
            int r2 = r2 + r1
        L_0x0317:
            if (r1 >= r2) goto L_0x0323
            long r4 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r1)
            r11.zzac(r4)
            int r1 = r1 + 8
            goto L_0x0317
        L_0x0323:
            if (r1 != r2) goto L_0x0327
            goto L_0x0423
        L_0x0327:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x032c:
            if (r6 != r13) goto L_0x0422
            com.google.android.gms.internal.vision.zzjb r11 = (com.google.android.gms.internal.vision.zzjb) r11
            long r8 = com.google.android.gms.internal.vision.zzgk.zzb(r18, r19)
            r11.zzac(r8)
        L_0x0337:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            long r8 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4)
            r11.zzac(r8)
            goto L_0x0337
        L_0x034b:
            if (r6 != r10) goto L_0x0353
            int r1 = com.google.android.gms.internal.vision.zzgk.zza((byte[]) r3, (int) r4, (com.google.android.gms.internal.vision.zzik<?>) r11, (com.google.android.gms.internal.vision.zzgm) r7)
            goto L_0x0423
        L_0x0353:
            if (r6 != 0) goto L_0x0422
            r22 = r18
            r23 = r19
            r24 = r20
            r25 = r11
            r26 = r30
            int r1 = com.google.android.gms.internal.vision.zzgk.zza((int) r21, (byte[]) r22, (int) r23, (int) r24, (com.google.android.gms.internal.vision.zzik<?>) r25, (com.google.android.gms.internal.vision.zzgm) r26)
            goto L_0x0423
        L_0x0365:
            if (r6 != r10) goto L_0x0385
            com.google.android.gms.internal.vision.zzjb r11 = (com.google.android.gms.internal.vision.zzjb) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r2 = r7.zztk
            int r2 = r2 + r1
        L_0x0370:
            if (r1 >= r2) goto L_0x037c
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r1, r7)
            long r4 = r7.zztl
            r11.zzac(r4)
            goto L_0x0370
        L_0x037c:
            if (r1 != r2) goto L_0x0380
            goto L_0x0423
        L_0x0380:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x0385:
            if (r6 != 0) goto L_0x0422
            com.google.android.gms.internal.vision.zzjb r11 = (com.google.android.gms.internal.vision.zzjb) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r7)
            long r8 = r7.zztl
            r11.zzac(r8)
        L_0x0392:
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            int r1 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r7)
            long r8 = r7.zztl
            r11.zzac(r8)
            goto L_0x0392
        L_0x03a6:
            if (r6 != r10) goto L_0x03c5
            com.google.android.gms.internal.vision.zzhz r11 = (com.google.android.gms.internal.vision.zzhz) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r2 = r7.zztk
            int r2 = r2 + r1
        L_0x03b1:
            if (r1 >= r2) goto L_0x03bd
            float r4 = com.google.android.gms.internal.vision.zzgk.zzd(r3, r1)
            r11.zzu(r4)
            int r1 = r1 + 4
            goto L_0x03b1
        L_0x03bd:
            if (r1 != r2) goto L_0x03c0
            goto L_0x0423
        L_0x03c0:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x03c5:
            if (r6 != r9) goto L_0x0422
            com.google.android.gms.internal.vision.zzhz r11 = (com.google.android.gms.internal.vision.zzhz) r11
            float r1 = com.google.android.gms.internal.vision.zzgk.zzd(r18, r19)
            r11.zzu(r1)
        L_0x03d0:
            int r1 = r4 + 4
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            float r1 = com.google.android.gms.internal.vision.zzgk.zzd(r3, r4)
            r11.zzu(r1)
            goto L_0x03d0
        L_0x03e4:
            if (r6 != r10) goto L_0x0403
            com.google.android.gms.internal.vision.zzhm r11 = (com.google.android.gms.internal.vision.zzhm) r11
            int r1 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r7)
            int r2 = r7.zztk
            int r2 = r2 + r1
        L_0x03ef:
            if (r1 >= r2) goto L_0x03fb
            double r4 = com.google.android.gms.internal.vision.zzgk.zzc(r3, r1)
            r11.zzc(r4)
            int r1 = r1 + 8
            goto L_0x03ef
        L_0x03fb:
            if (r1 != r2) goto L_0x03fe
            goto L_0x0423
        L_0x03fe:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r1
        L_0x0403:
            if (r6 != r13) goto L_0x0422
            com.google.android.gms.internal.vision.zzhm r11 = (com.google.android.gms.internal.vision.zzhm) r11
            double r8 = com.google.android.gms.internal.vision.zzgk.zzc(r18, r19)
            r11.zzc(r8)
        L_0x040e:
            int r1 = r4 + 8
            if (r1 >= r5) goto L_0x0423
            int r4 = com.google.android.gms.internal.vision.zzgk.zza(r3, r1, r7)
            int r6 = r7.zztk
            if (r2 != r6) goto L_0x0423
            double r8 = com.google.android.gms.internal.vision.zzgk.zzc(r3, r4)
            r11.zzc(r8)
            goto L_0x040e
        L_0x0422:
            r1 = r4
        L_0x0423:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.vision.zzgm):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r8, byte[] r9, int r10, int r11, int r12, long r13, com.google.android.gms.internal.vision.zzgm r15) throws java.io.IOException {
        /*
            r7 = this;
            sun.misc.Unsafe r0 = zzaaj
            java.lang.Object r12 = r7.zzbv(r12)
            java.lang.Object r1 = r0.getObject(r8, r13)
            com.google.android.gms.internal.vision.zzjg r2 = r7.zzaba
            boolean r2 = r2.zzp(r1)
            if (r2 == 0) goto L_0x0021
            com.google.android.gms.internal.vision.zzjg r2 = r7.zzaba
            java.lang.Object r2 = r2.zzr(r12)
            com.google.android.gms.internal.vision.zzjg r3 = r7.zzaba
            r3.zzc(r2, r1)
            r0.putObject(r8, r13, r2)
            r1 = r2
        L_0x0021:
            com.google.android.gms.internal.vision.zzjg r8 = r7.zzaba
            com.google.android.gms.internal.vision.zzje r8 = r8.zzs(r12)
            com.google.android.gms.internal.vision.zzjg r12 = r7.zzaba
            java.util.Map r12 = r12.zzn(r1)
            int r10 = com.google.android.gms.internal.vision.zzgk.zza(r9, r10, r15)
            int r13 = r15.zztk
            if (r13 < 0) goto L_0x0097
            int r14 = r11 - r10
            if (r13 > r14) goto L_0x0097
            int r13 = r13 + r10
            K r14 = r8.zzaad
            V r0 = r8.zzgk
        L_0x003e:
            if (r10 >= r13) goto L_0x008c
            int r1 = r10 + 1
            byte r10 = r9[r10]
            if (r10 >= 0) goto L_0x004c
            int r1 = com.google.android.gms.internal.vision.zzgk.zza((int) r10, (byte[]) r9, (int) r1, (com.google.android.gms.internal.vision.zzgm) r15)
            int r10 = r15.zztk
        L_0x004c:
            r2 = r1
            int r1 = r10 >>> 3
            r3 = r10 & 7
            r4 = 1
            if (r1 == r4) goto L_0x0072
            r4 = 2
            if (r1 == r4) goto L_0x0058
            goto L_0x0087
        L_0x0058:
            com.google.android.gms.internal.vision.zzlk r1 = r8.zzaae
            int r1 = r1.zzjl()
            if (r3 != r1) goto L_0x0087
            com.google.android.gms.internal.vision.zzlk r4 = r8.zzaae
            V r10 = r8.zzgk
            java.lang.Class r5 = r10.getClass()
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza((byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.vision.zzlk) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.vision.zzgm) r6)
            java.lang.Object r0 = r15.zztm
            goto L_0x003e
        L_0x0072:
            com.google.android.gms.internal.vision.zzlk r1 = r8.zzaac
            int r1 = r1.zzjl()
            if (r3 != r1) goto L_0x0087
            com.google.android.gms.internal.vision.zzlk r4 = r8.zzaac
            r5 = 0
            r1 = r9
            r3 = r11
            r6 = r15
            int r10 = zza((byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.vision.zzlk) r4, (java.lang.Class<?>) r5, (com.google.android.gms.internal.vision.zzgm) r6)
            java.lang.Object r14 = r15.zztm
            goto L_0x003e
        L_0x0087:
            int r10 = com.google.android.gms.internal.vision.zzgk.zza((int) r10, (byte[]) r9, (int) r2, (int) r11, (com.google.android.gms.internal.vision.zzgm) r15)
            goto L_0x003e
        L_0x008c:
            if (r10 != r13) goto L_0x0092
            r12.put(r14, r0)
            return r13
        L_0x0092:
            com.google.android.gms.internal.vision.zzin r8 = com.google.android.gms.internal.vision.zzin.zzhn()
            throw r8
        L_0x0097:
            com.google.android.gms.internal.vision.zzin r8 = com.google.android.gms.internal.vision.zzin.zzhh()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Object, byte[], int, int, int, long, com.google.android.gms.internal.vision.zzgm):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:62:0x018a, code lost:
        r2 = r4 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x019b, code lost:
        r2 = r4 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x019d, code lost:
        r12.putInt(r1, r13, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zza(T r17, byte[] r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long r26, int r28, com.google.android.gms.internal.vision.zzgm r29) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = r19
            r2 = r21
            r8 = r22
            r5 = r23
            r9 = r26
            r6 = r28
            r11 = r29
            sun.misc.Unsafe r12 = zzaaj
            int[] r7 = r0.zzaak
            int r13 = r6 + 2
            r7 = r7[r13]
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r7 = r7 & r13
            long r13 = (long) r7
            r7 = 5
            r15 = 2
            switch(r25) {
                case 51: goto L_0x018d;
                case 52: goto L_0x017d;
                case 53: goto L_0x016d;
                case 54: goto L_0x016d;
                case 55: goto L_0x015d;
                case 56: goto L_0x014e;
                case 57: goto L_0x0140;
                case 58: goto L_0x0127;
                case 59: goto L_0x00f3;
                case 60: goto L_0x00c5;
                case 61: goto L_0x00b8;
                case 62: goto L_0x015d;
                case 63: goto L_0x008a;
                case 64: goto L_0x0140;
                case 65: goto L_0x014e;
                case 66: goto L_0x0075;
                case 67: goto L_0x0060;
                case 68: goto L_0x0028;
                default: goto L_0x0026;
            }
        L_0x0026:
            goto L_0x01a1
        L_0x0028:
            r7 = 3
            if (r5 != r7) goto L_0x01a1
            r2 = r2 & -8
            r7 = r2 | 4
            com.google.android.gms.internal.vision.zzkf r2 = r0.zzbu(r6)
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r7
            r7 = r29
            int r2 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r2, (byte[]) r3, (int) r4, (int) r5, (int) r6, (com.google.android.gms.internal.vision.zzgm) r7)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x004b
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x004c
        L_0x004b:
            r15 = 0
        L_0x004c:
            if (r15 != 0) goto L_0x0055
            java.lang.Object r3 = r11.zztm
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0055:
            java.lang.Object r3 = r11.zztm
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzie.zzb(r15, r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0060:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r11)
            long r3 = r11.zztl
            long r3 = com.google.android.gms.internal.vision.zzhe.zzr(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0075:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r11)
            int r3 = r11.zztk
            int r3 = com.google.android.gms.internal.vision.zzhe.zzbb(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x008a:
            if (r5 != 0) goto L_0x01a1
            int r3 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r11)
            int r4 = r11.zztk
            com.google.android.gms.internal.vision.zzij r5 = r0.zzbw(r6)
            if (r5 == 0) goto L_0x00ae
            boolean r5 = r5.zzg(r4)
            if (r5 == 0) goto L_0x009f
            goto L_0x00ae
        L_0x009f:
            com.google.android.gms.internal.vision.zzkw r1 = zzv(r17)
            long r4 = (long) r4
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r1.zzb(r2, r4)
            r2 = r3
            goto L_0x01a2
        L_0x00ae:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r12.putObject(r1, r9, r2)
            r2 = r3
            goto L_0x019d
        L_0x00b8:
            if (r5 != r15) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zze(r3, r4, r11)
            java.lang.Object r3 = r11.zztm
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x00c5:
            if (r5 != r15) goto L_0x01a1
            com.google.android.gms.internal.vision.zzkf r2 = r0.zzbu(r6)
            r5 = r20
            int r2 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r2, (byte[]) r3, (int) r4, (int) r5, (com.google.android.gms.internal.vision.zzgm) r11)
            int r3 = r12.getInt(r1, r13)
            if (r3 != r8) goto L_0x00dc
            java.lang.Object r15 = r12.getObject(r1, r9)
            goto L_0x00dd
        L_0x00dc:
            r15 = 0
        L_0x00dd:
            if (r15 != 0) goto L_0x00e5
            java.lang.Object r3 = r11.zztm
            r12.putObject(r1, r9, r3)
            goto L_0x00ee
        L_0x00e5:
            java.lang.Object r3 = r11.zztm
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzie.zzb(r15, r3)
            r12.putObject(r1, r9, r3)
        L_0x00ee:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x00f3:
            if (r5 != r15) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r11)
            int r4 = r11.zztk
            if (r4 != 0) goto L_0x0103
            java.lang.String r3 = ""
            r12.putObject(r1, r9, r3)
            goto L_0x0122
        L_0x0103:
            r5 = 536870912(0x20000000, float:1.0842022E-19)
            r5 = r24 & r5
            if (r5 == 0) goto L_0x0117
            int r5 = r2 + r4
            boolean r5 = com.google.android.gms.internal.vision.zzlf.zzf((byte[]) r3, (int) r2, (int) r5)
            if (r5 == 0) goto L_0x0112
            goto L_0x0117
        L_0x0112:
            com.google.android.gms.internal.vision.zzin r1 = com.google.android.gms.internal.vision.zzin.zzho()
            throw r1
        L_0x0117:
            java.lang.String r5 = new java.lang.String
            java.nio.charset.Charset r6 = com.google.android.gms.internal.vision.zzie.UTF_8
            r5.<init>(r3, r2, r4, r6)
            r12.putObject(r1, r9, r5)
            int r2 = r2 + r4
        L_0x0122:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x0127:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r11)
            long r3 = r11.zztl
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0137
            r15 = 1
            goto L_0x0138
        L_0x0137:
            r15 = 0
        L_0x0138:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r15)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x0140:
            if (r5 != r7) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r18, r19)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r12.putObject(r1, r9, r2)
            goto L_0x018a
        L_0x014e:
            r2 = 1
            if (r5 != r2) goto L_0x01a1
            long r2 = com.google.android.gms.internal.vision.zzgk.zzb(r18, r19)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r12.putObject(r1, r9, r2)
            goto L_0x019b
        L_0x015d:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r3, r4, r11)
            int r3 = r11.zztk
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x016d:
            if (r5 != 0) goto L_0x01a1
            int r2 = com.google.android.gms.internal.vision.zzgk.zzb(r3, r4, r11)
            long r3 = r11.zztl
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r12.putObject(r1, r9, r3)
            goto L_0x019d
        L_0x017d:
            if (r5 != r7) goto L_0x01a1
            float r2 = com.google.android.gms.internal.vision.zzgk.zzd(r18, r19)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            r12.putObject(r1, r9, r2)
        L_0x018a:
            int r2 = r4 + 4
            goto L_0x019d
        L_0x018d:
            r2 = 1
            if (r5 != r2) goto L_0x01a1
            double r2 = com.google.android.gms.internal.vision.zzgk.zzc(r18, r19)
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r12.putObject(r1, r9, r2)
        L_0x019b:
            int r2 = r4 + 8
        L_0x019d:
            r12.putInt(r1, r13, r8)
            goto L_0x01a2
        L_0x01a1:
            r2 = r4
        L_0x01a2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Object, byte[], int, int, int, int, int, int, int, long, int, com.google.android.gms.internal.vision.zzgm):int");
    }

    private final zzkf zzbu(int i) {
        int i2 = (i / 3) << 1;
        zzkf zzkf = (zzkf) this.zzaal[i2];
        if (zzkf != null) {
            return zzkf;
        }
        zzkf zzf = zzkb.zzik().zzf((Class) this.zzaal[i2 + 1]);
        this.zzaal[i2] = zzf;
        return zzf;
    }

    private final Object zzbv(int i) {
        return this.zzaal[(i / 3) << 1];
    }

    private final zzij zzbw(int i) {
        return (zzij) this.zzaal[((i / 3) << 1) + 1];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: com.google.android.gms.internal.vision.zzkw} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02f9, code lost:
        r5 = r6 | r24;
        r11 = r39;
        r3 = r0;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02ff, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0300, code lost:
        r2 = r13;
        r6 = r21;
        r13 = r38;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0307, code lost:
        r15 = r39;
        r24 = r6;
        r23 = r7;
        r31 = r10;
        r2 = r11;
        r18 = r13;
        r6 = r21;
        r7 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x03be, code lost:
        if (r0 == r22) goto L_0x042b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0405, code lost:
        if (r0 == r14) goto L_0x042b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x05bd, code lost:
        r2 = r2 + 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x05cc, code lost:
        r2 = r2 + 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a8, code lost:
        r13 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0187, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01ec, code lost:
        r1 = r6 | r24;
        r11 = r39;
        r3 = r5;
        r2 = r13;
        r6 = r21;
        r5 = r1;
        r13 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0242, code lost:
        r1 = r6 | r24;
        r11 = r39;
        r3 = r5;
        r2 = r13;
        r6 = r21;
        r13 = r38;
        r5 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x024d, code lost:
        r1 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0277, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x02b9, code lost:
        r5 = r6 | r24;
        r1 = r7;
        r0 = r8;
        r3 = r11;
        r2 = r13;
        r6 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x02c3, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(T r35, byte[] r36, int r37, int r38, int r39, com.google.android.gms.internal.vision.zzgm r40) throws java.io.IOException {
        /*
            r34 = this;
            r15 = r34
            r14 = r35
            r12 = r36
            r13 = r38
            r11 = r39
            r9 = r40
            sun.misc.Unsafe r10 = zzaaj
            r16 = 0
            r0 = r37
            r1 = -1
            r2 = 0
            r3 = 0
            r5 = 0
            r6 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0019:
            if (r0 >= r13) goto L_0x0650
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0028
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((int) r0, (byte[]) r12, (int) r3, (com.google.android.gms.internal.vision.zzgm) r9)
            int r3 = r9.zztk
            goto L_0x002d
        L_0x0028:
            r32 = r3
            r3 = r0
            r0 = r32
        L_0x002d:
            int r7 = r3 >>> 3
            r8 = r3 & 7
            r4 = 3
            if (r7 <= r1) goto L_0x003a
            int r2 = r2 / r4
            int r1 = r15.zzv(r7, r2)
            goto L_0x003e
        L_0x003a:
            int r1 = r15.zzca(r7)
        L_0x003e:
            r2 = r1
            r19 = 0
            r4 = -1
            if (r2 != r4) goto L_0x0055
            r2 = r0
            r24 = r5
            r23 = r7
            r31 = r10
            r15 = r11
            r17 = -1
            r18 = 0
            r21 = 1
            r7 = r3
            goto L_0x042f
        L_0x0055:
            int[] r4 = r15.zzaak
            int r23 = r2 + 1
            r1 = r4[r23]
            r23 = 267386880(0xff00000, float:2.3665827E-29)
            r23 = r1 & r23
            int r11 = r23 >>> 20
            r17 = r0
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r1 & r13
            long r13 = (long) r0
            r0 = 17
            if (r11 > r0) goto L_0x0319
            int r24 = r2 + 2
            r4 = r4[r24]
            int r24 = r4 >>> 20
            r22 = 1
            int r24 = r22 << r24
            r25 = r13
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r4 & r13
            if (r4 == r6) goto L_0x0096
            if (r6 == r13) goto L_0x008a
            long r13 = (long) r6
            r6 = r35
            r27 = r25
            r10.putInt(r6, r13, r5)
            goto L_0x008e
        L_0x008a:
            r6 = r35
            r27 = r25
        L_0x008e:
            long r13 = (long) r4
            int r5 = r10.getInt(r6, r13)
            r13 = r4
            r14 = r6
            goto L_0x009b
        L_0x0096:
            r14 = r35
            r27 = r25
            r13 = r6
        L_0x009b:
            r6 = r5
            r4 = 5
            switch(r11) {
                case 0: goto L_0x02e0;
                case 1: goto L_0x02c5;
                case 2: goto L_0x0299;
                case 3: goto L_0x0299;
                case 4: goto L_0x027a;
                case 5: goto L_0x0250;
                case 6: goto L_0x022a;
                case 7: goto L_0x01f8;
                case 8: goto L_0x01c7;
                case 9: goto L_0x018e;
                case 10: goto L_0x016f;
                case 11: goto L_0x027a;
                case 12: goto L_0x0139;
                case 13: goto L_0x022a;
                case 14: goto L_0x0250;
                case 15: goto L_0x011e;
                case 16: goto L_0x00f6;
                case 17: goto L_0x00ae;
                default: goto L_0x00a0;
            }
        L_0x00a0:
            r0 = r3
            r21 = r13
            r11 = r17
            r4 = 1
            r17 = -1
        L_0x00a8:
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            goto L_0x0307
        L_0x00ae:
            r5 = 3
            if (r8 != r5) goto L_0x00ed
            int r0 = r7 << 3
            r4 = r0 | 4
            com.google.android.gms.internal.vision.zzkf r0 = r15.zzbu(r2)
            r11 = r17
            r1 = r36
            r8 = r2
            r2 = r11
            r11 = r3
            r3 = r38
            r17 = -1
            r5 = r40
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (com.google.android.gms.internal.vision.zzgm) r5)
            r1 = r6 & r24
            if (r1 != 0) goto L_0x00d6
            java.lang.Object r1 = r9.zztm
            r2 = r27
            r10.putObject(r14, r2, r1)
            goto L_0x00e5
        L_0x00d6:
            r2 = r27
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r4 = r9.zztm
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzie.zzb(r1, r4)
            r10.putObject(r14, r2, r1)
        L_0x00e5:
            r5 = r6 | r24
            r1 = r7
            r2 = r8
            r3 = r11
            r6 = r13
            goto L_0x036d
        L_0x00ed:
            r11 = r17
            r17 = -1
            r0 = r3
            r21 = r13
            r4 = 1
            goto L_0x00a8
        L_0x00f6:
            r5 = r2
            r4 = r3
            r11 = r17
            r2 = r27
            r17 = -1
            if (r8 != 0) goto L_0x0118
            int r8 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r11, r9)
            long r0 = r9.zztl
            long r18 = com.google.android.gms.internal.vision.zzhe.zzr(r0)
            r0 = r10
            r1 = r35
            r11 = r4
            r21 = r13
            r13 = r5
            r4 = r18
            r0.putLong(r1, r2, r4)
            goto L_0x02b9
        L_0x0118:
            r21 = r13
            r13 = r5
            r0 = r4
            goto L_0x0188
        L_0x011e:
            r5 = r3
            r21 = r13
            r11 = r17
            r17 = -1
            r13 = r2
            r2 = r27
            if (r8 != 0) goto L_0x0187
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r11, r9)
            int r1 = r9.zztk
            int r1 = com.google.android.gms.internal.vision.zzhe.zzbb(r1)
            r10.putInt(r14, r2, r1)
            goto L_0x0242
        L_0x0139:
            r5 = r3
            r21 = r13
            r11 = r17
            r17 = -1
            r13 = r2
            r2 = r27
            if (r8 != 0) goto L_0x0187
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r11, r9)
            int r1 = r9.zztk
            com.google.android.gms.internal.vision.zzij r4 = r15.zzbw(r13)
            if (r4 == 0) goto L_0x016a
            boolean r4 = r4.zzg(r1)
            if (r4 == 0) goto L_0x0158
            goto L_0x016a
        L_0x0158:
            com.google.android.gms.internal.vision.zzkw r2 = zzv(r35)
            long r3 = (long) r1
            java.lang.Long r1 = java.lang.Long.valueOf(r3)
            r2.zzb(r5, r1)
            r11 = r39
            r3 = r5
            r5 = r6
            goto L_0x02ff
        L_0x016a:
            r10.putInt(r14, r2, r1)
            goto L_0x0242
        L_0x016f:
            r5 = r3
            r21 = r13
            r11 = r17
            r1 = 2
            r17 = -1
            r13 = r2
            r2 = r27
            if (r8 != r1) goto L_0x0187
            int r0 = com.google.android.gms.internal.vision.zzgk.zze(r12, r11, r9)
            java.lang.Object r1 = r9.zztm
            r10.putObject(r14, r2, r1)
            goto L_0x0242
        L_0x0187:
            r0 = r5
        L_0x0188:
            r4 = 1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0307
        L_0x018e:
            r5 = r3
            r21 = r13
            r11 = r17
            r1 = 2
            r17 = -1
            r13 = r2
            r2 = r27
            if (r8 != r1) goto L_0x01c0
            com.google.android.gms.internal.vision.zzkf r0 = r15.zzbu(r13)
            r4 = r38
            r18 = 1048575(0xfffff, float:1.469367E-39)
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r0, (byte[]) r12, (int) r11, (int) r4, (com.google.android.gms.internal.vision.zzgm) r9)
            r1 = r6 & r24
            if (r1 != 0) goto L_0x01b2
            java.lang.Object r1 = r9.zztm
            r10.putObject(r14, r2, r1)
            goto L_0x01ec
        L_0x01b2:
            java.lang.Object r1 = r10.getObject(r14, r2)
            java.lang.Object r8 = r9.zztm
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzie.zzb(r1, r8)
            r10.putObject(r14, r2, r1)
            goto L_0x01ec
        L_0x01c0:
            r4 = r38
            r18 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x0277
        L_0x01c7:
            r4 = r38
            r5 = r3
            r21 = r13
            r11 = r17
            r0 = 2
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != r0) goto L_0x0277
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r0 & r1
            if (r0 != 0) goto L_0x01e3
            int r0 = com.google.android.gms.internal.vision.zzgk.zzc(r12, r11, r9)
            goto L_0x01e7
        L_0x01e3:
            int r0 = com.google.android.gms.internal.vision.zzgk.zzd(r12, r11, r9)
        L_0x01e7:
            java.lang.Object r1 = r9.zztm
            r10.putObject(r14, r2, r1)
        L_0x01ec:
            r1 = r6 | r24
            r11 = r39
            r3 = r5
            r2 = r13
            r6 = r21
            r5 = r1
            r13 = r4
            goto L_0x024d
        L_0x01f8:
            r4 = r38
            r5 = r3
            r21 = r13
            r11 = r17
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != 0) goto L_0x0277
            int r0 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r11, r9)
            r37 = r0
            long r0 = r9.zztl
            int r8 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r8 == 0) goto L_0x0217
            r0 = 1
            goto L_0x0218
        L_0x0217:
            r0 = 0
        L_0x0218:
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r14, (long) r2, (boolean) r0)
            r0 = r6 | r24
            r11 = r39
            r3 = r5
            r1 = r7
            r2 = r13
            r6 = r21
            r5 = r0
            r13 = r4
            r0 = r37
            goto L_0x0019
        L_0x022a:
            r5 = r3
            r21 = r13
            r11 = r17
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != r4) goto L_0x0277
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r11)
            r10.putInt(r14, r2, r0)
            int r0 = r11 + 4
        L_0x0242:
            r1 = r6 | r24
            r11 = r39
            r3 = r5
            r2 = r13
            r6 = r21
            r13 = r38
            r5 = r1
        L_0x024d:
            r1 = r7
            goto L_0x0019
        L_0x0250:
            r5 = r3
            r21 = r13
            r11 = r17
            r0 = 1
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != r0) goto L_0x0277
            long r19 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r11)
            r0 = r10
            r1 = r35
            r8 = r5
            r4 = r19
            r0.putLong(r1, r2, r4)
            int r0 = r11 + 8
            r5 = r6 | r24
            r11 = r39
            r1 = r7
            r3 = r8
            goto L_0x0300
        L_0x0277:
            r0 = r5
            goto L_0x02de
        L_0x027a:
            r4 = r3
            r21 = r13
            r11 = r17
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != 0) goto L_0x02c3
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r11, r9)
            int r1 = r9.zztk
            r10.putInt(r14, r2, r1)
            r5 = r6 | r24
            r11 = r39
            r3 = r4
            goto L_0x02ff
        L_0x0299:
            r4 = r3
            r21 = r13
            r11 = r17
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != 0) goto L_0x02c3
            int r8 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r11, r9)
            long r0 = r9.zztl
            r19 = r0
            r0 = r10
            r1 = r35
            r11 = r4
            r4 = r19
            r0.putLong(r1, r2, r4)
        L_0x02b9:
            r5 = r6 | r24
            r1 = r7
            r0 = r8
            r3 = r11
            r2 = r13
            r6 = r21
            goto L_0x036d
        L_0x02c3:
            r0 = r4
            goto L_0x02de
        L_0x02c5:
            r0 = r3
            r21 = r13
            r11 = r17
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != r4) goto L_0x02de
            float r1 = com.google.android.gms.internal.vision.zzgk.zzd(r12, r11)
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r14, (long) r2, (float) r1)
            int r1 = r11 + 4
            goto L_0x02f9
        L_0x02de:
            r4 = 1
            goto L_0x0307
        L_0x02e0:
            r0 = r3
            r21 = r13
            r11 = r17
            r4 = 1
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r13 = r2
            r2 = r27
            if (r8 != r4) goto L_0x0307
            double r4 = com.google.android.gms.internal.vision.zzgk.zzc(r12, r11)
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r14, (long) r2, (double) r4)
            int r1 = r11 + 8
        L_0x02f9:
            r5 = r6 | r24
            r11 = r39
            r3 = r0
            r0 = r1
        L_0x02ff:
            r1 = r7
        L_0x0300:
            r2 = r13
            r6 = r21
            r13 = r38
            goto L_0x0019
        L_0x0307:
            r15 = r39
            r24 = r6
            r23 = r7
            r31 = r10
            r2 = r11
            r18 = r13
            r6 = r21
            r21 = 1
            r7 = r0
            goto L_0x042f
        L_0x0319:
            r0 = r3
            r22 = r17
            r17 = -1
            r18 = 1048575(0xfffff, float:1.469367E-39)
            r32 = r13
            r14 = r35
            r13 = r2
            r2 = r32
            r4 = 27
            if (r11 != r4) goto L_0x0387
            r4 = 2
            if (r8 != r4) goto L_0x0373
            java.lang.Object r1 = r10.getObject(r14, r2)
            com.google.android.gms.internal.vision.zzik r1 = (com.google.android.gms.internal.vision.zzik) r1
            boolean r4 = r1.zzei()
            if (r4 != 0) goto L_0x034d
            int r4 = r1.size()
            if (r4 != 0) goto L_0x0344
            r4 = 10
            goto L_0x0346
        L_0x0344:
            int r4 = r4 << 1
        L_0x0346:
            com.google.android.gms.internal.vision.zzik r1 = r1.zzan(r4)
            r10.putObject(r14, r2, r1)
        L_0x034d:
            r8 = r1
            com.google.android.gms.internal.vision.zzkf r1 = r15.zzbu(r13)
            r11 = r0
            r0 = r1
            r1 = r11
            r2 = r36
            r3 = r22
            r4 = r38
            r24 = r5
            r5 = r8
            r25 = r6
            r6 = r40
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r0, r1, r2, r3, r4, r5, r6)
            r1 = r7
            r3 = r11
            r2 = r13
            r5 = r24
            r6 = r25
        L_0x036d:
            r13 = r38
            r11 = r39
            goto L_0x0019
        L_0x0373:
            r24 = r5
            r25 = r6
            r15 = r39
            r37 = r0
            r23 = r7
            r31 = r10
            r18 = r13
            r14 = r22
            r21 = 1
            goto L_0x0408
        L_0x0387:
            r24 = r5
            r25 = r6
            r6 = r0
            r0 = 49
            if (r11 > r0) goto L_0x03db
            long r4 = (long) r1
            r1 = 17
            r0 = r34
            r1 = r35
            r26 = r2
            r2 = r36
            r3 = r22
            r29 = r4
            r5 = 0
            r21 = 1
            r4 = r38
            r5 = r6
            r37 = r6
            r6 = r7
            r23 = r7
            r7 = r8
            r8 = r13
            r31 = r10
            r9 = r29
            r15 = r39
            r18 = r13
            r12 = r26
            r14 = r40
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (com.google.android.gms.internal.vision.zzgm) r14)
            r14 = r22
            if (r0 != r14) goto L_0x03c2
            goto L_0x042b
        L_0x03c2:
            r14 = r35
            r12 = r36
            r3 = r37
            r13 = r38
            r9 = r40
            r11 = r15
            r2 = r18
            r1 = r23
            r5 = r24
            r6 = r25
            r10 = r31
            r15 = r34
            goto L_0x0019
        L_0x03db:
            r15 = r39
            r26 = r2
            r37 = r6
            r23 = r7
            r31 = r10
            r18 = r13
            r14 = r22
            r21 = 1
            r0 = 50
            if (r11 != r0) goto L_0x040e
            r0 = 2
            if (r8 != r0) goto L_0x0408
            r0 = r34
            r1 = r35
            r2 = r36
            r3 = r14
            r4 = r38
            r5 = r18
            r6 = r26
            r8 = r40
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (long) r6, (com.google.android.gms.internal.vision.zzgm) r8)
            if (r0 != r14) goto L_0x03c2
            goto L_0x042b
        L_0x0408:
            r7 = r37
            r2 = r14
        L_0x040b:
            r6 = r25
            goto L_0x042f
        L_0x040e:
            r0 = r34
            r9 = r1
            r1 = r35
            r2 = r36
            r3 = r14
            r4 = r38
            r5 = r37
            r6 = r23
            r7 = r8
            r8 = r9
            r9 = r11
            r10 = r26
            r12 = r18
            r13 = r40
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (com.google.android.gms.internal.vision.zzgm) r13)
            if (r0 != r14) goto L_0x0634
        L_0x042b:
            r7 = r37
            r2 = r0
            goto L_0x040b
        L_0x042f:
            if (r7 != r15) goto L_0x0446
            if (r15 != 0) goto L_0x0434
            goto L_0x0446
        L_0x0434:
            r8 = r34
            r13 = r35
            r0 = r2
            r1 = r6
            r3 = r7
            r9 = r15
            r5 = r24
            r2 = 1048575(0xfffff, float:1.469367E-39)
            r4 = 0
            r6 = r38
            goto L_0x0660
        L_0x0446:
            r8 = r34
            r9 = r15
            boolean r0 = r8.zzaap
            if (r0 == 0) goto L_0x0607
            r10 = r40
            com.google.android.gms.internal.vision.zzho r0 = r10.zzcu
            com.google.android.gms.internal.vision.zzho r1 = com.google.android.gms.internal.vision.zzho.zzgf()
            if (r0 == r1) goto L_0x0602
            com.google.android.gms.internal.vision.zzjn r0 = r8.zzaao
            com.google.android.gms.internal.vision.zzkx<?, ?> r1 = r8.zzaay
            com.google.android.gms.internal.vision.zzho r3 = r10.zzcu
            r11 = r23
            com.google.android.gms.internal.vision.zzid$zzg r12 = r3.zza(r0, r11)
            if (r12 != 0) goto L_0x047e
            com.google.android.gms.internal.vision.zzkw r4 = zzv(r35)
            r0 = r7
            r1 = r36
            r3 = r38
            r5 = r40
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.vision.zzkw) r4, (com.google.android.gms.internal.vision.zzgm) r5)
            r13 = r35
            r15 = r36
            r37 = r6
            r6 = r38
            goto L_0x0622
        L_0x047e:
            r13 = r35
            r0 = r13
            com.google.android.gms.internal.vision.zzid$zze r0 = (com.google.android.gms.internal.vision.zzid.zze) r0
            r0.zzhe()
            com.google.android.gms.internal.vision.zzht<com.google.android.gms.internal.vision.zzid$zzd> r14 = r0.zzyg
            com.google.android.gms.internal.vision.zzid$zzd r3 = r12.zzyr
            boolean r3 = r3.zzye
            com.google.android.gms.internal.vision.zzid$zzd r3 = r12.zzyr
            com.google.android.gms.internal.vision.zzlk r3 = r3.zzyd
            com.google.android.gms.internal.vision.zzlk r4 = com.google.android.gms.internal.vision.zzlk.ENUM
            if (r3 != r4) goto L_0x04c3
            r15 = r36
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r15, r2, r10)
            int r3 = r10.zztk
            r4 = 0
            com.google.android.gms.internal.vision.zzih r3 = r4.zzh(r3)
            if (r3 != 0) goto L_0x04bc
            com.google.android.gms.internal.vision.zzkw r3 = r0.zzxz
            com.google.android.gms.internal.vision.zzkw r4 = com.google.android.gms.internal.vision.zzkw.zzja()
            if (r3 != r4) goto L_0x04b1
            com.google.android.gms.internal.vision.zzkw r3 = com.google.android.gms.internal.vision.zzkw.zzjb()
            r0.zzxz = r3
        L_0x04b1:
            int r0 = r10.zztk
            com.google.android.gms.internal.vision.zzkh.zza((int) r11, (int) r0, r3, r1)
            r37 = r6
            r6 = r38
            goto L_0x0600
        L_0x04bc:
            int r0 = r10.zztk
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            goto L_0x04d5
        L_0x04c3:
            r15 = r36
            r4 = 0
            int[] r0 = com.google.android.gms.internal.vision.zzgn.zztn
            com.google.android.gms.internal.vision.zzid$zzd r1 = r12.zzyr
            com.google.android.gms.internal.vision.zzlk r1 = r1.zzyd
            int r1 = r1.ordinal()
            r0 = r0[r1]
            switch(r0) {
                case 1: goto L_0x05c0;
                case 2: goto L_0x05b1;
                case 3: goto L_0x05a2;
                case 4: goto L_0x05a2;
                case 5: goto L_0x0593;
                case 6: goto L_0x0593;
                case 7: goto L_0x0586;
                case 8: goto L_0x0586;
                case 9: goto L_0x0579;
                case 10: goto L_0x0579;
                case 11: goto L_0x0563;
                case 12: goto L_0x054f;
                case 13: goto L_0x053b;
                case 14: goto L_0x0533;
                case 15: goto L_0x0527;
                case 16: goto L_0x051b;
                case 17: goto L_0x04f6;
                case 18: goto L_0x04db;
                default: goto L_0x04d5;
            }
        L_0x04d5:
            r37 = r6
            r6 = r38
            goto L_0x05ce
        L_0x04db:
            com.google.android.gms.internal.vision.zzkb r0 = com.google.android.gms.internal.vision.zzkb.zzik()
            com.google.android.gms.internal.vision.zzjn r1 = r12.zzyq
            java.lang.Class r1 = r1.getClass()
            com.google.android.gms.internal.vision.zzkf r0 = r0.zzf(r1)
            r5 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r0, (byte[]) r15, (int) r2, (int) r5, (com.google.android.gms.internal.vision.zzgm) r10)
            java.lang.Object r4 = r10.zztm
            r37 = r6
            r6 = r5
            goto L_0x05ce
        L_0x04f6:
            r5 = r38
            int r0 = r11 << 3
            r4 = r0 | 4
            com.google.android.gms.internal.vision.zzkb r0 = com.google.android.gms.internal.vision.zzkb.zzik()
            com.google.android.gms.internal.vision.zzjn r1 = r12.zzyq
            java.lang.Class r1 = r1.getClass()
            com.google.android.gms.internal.vision.zzkf r0 = r0.zzf(r1)
            r1 = r36
            r3 = r38
            r37 = r6
            r6 = r5
            r5 = r40
            int r2 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r0, (byte[]) r1, (int) r2, (int) r3, (int) r4, (com.google.android.gms.internal.vision.zzgm) r5)
            java.lang.Object r4 = r10.zztm
            goto L_0x05ce
        L_0x051b:
            r37 = r6
            r6 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zzc(r15, r2, r10)
            java.lang.Object r4 = r10.zztm
            goto L_0x05ce
        L_0x0527:
            r37 = r6
            r6 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zze(r15, r2, r10)
            java.lang.Object r4 = r10.zztm
            goto L_0x05ce
        L_0x0533:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Shouldn't reach here."
            r0.<init>(r1)
            throw r0
        L_0x053b:
            r37 = r6
            r6 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zzb(r15, r2, r10)
            long r0 = r10.zztl
            long r0 = com.google.android.gms.internal.vision.zzhe.zzr(r0)
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            goto L_0x05ce
        L_0x054f:
            r37 = r6
            r6 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r15, r2, r10)
            int r0 = r10.zztk
            int r0 = com.google.android.gms.internal.vision.zzhe.zzbb(r0)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            goto L_0x05ce
        L_0x0563:
            r37 = r6
            r6 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zzb(r15, r2, r10)
            long r0 = r10.zztl
            int r3 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r3 == 0) goto L_0x0573
            r4 = 1
            goto L_0x0574
        L_0x0573:
            r4 = 0
        L_0x0574:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
            goto L_0x05ce
        L_0x0579:
            r37 = r6
            r6 = r38
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r15, r2)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            goto L_0x05bd
        L_0x0586:
            r37 = r6
            r6 = r38
            long r0 = com.google.android.gms.internal.vision.zzgk.zzb(r15, r2)
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            goto L_0x05cc
        L_0x0593:
            r37 = r6
            r6 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zza(r15, r2, r10)
            int r0 = r10.zztk
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            goto L_0x05ce
        L_0x05a2:
            r37 = r6
            r6 = r38
            int r2 = com.google.android.gms.internal.vision.zzgk.zzb(r15, r2, r10)
            long r0 = r10.zztl
            java.lang.Long r4 = java.lang.Long.valueOf(r0)
            goto L_0x05ce
        L_0x05b1:
            r37 = r6
            r6 = r38
            float r0 = com.google.android.gms.internal.vision.zzgk.zzd(r15, r2)
            java.lang.Float r4 = java.lang.Float.valueOf(r0)
        L_0x05bd:
            int r2 = r2 + 4
            goto L_0x05ce
        L_0x05c0:
            r37 = r6
            r6 = r38
            double r0 = com.google.android.gms.internal.vision.zzgk.zzc(r15, r2)
            java.lang.Double r4 = java.lang.Double.valueOf(r0)
        L_0x05cc:
            int r2 = r2 + 8
        L_0x05ce:
            com.google.android.gms.internal.vision.zzid$zzd r0 = r12.zzyr
            boolean r0 = r0.zzye
            if (r0 == 0) goto L_0x05da
            com.google.android.gms.internal.vision.zzid$zzd r0 = r12.zzyr
            r14.zzb(r0, (java.lang.Object) r4)
            goto L_0x0600
        L_0x05da:
            int[] r0 = com.google.android.gms.internal.vision.zzgn.zztn
            com.google.android.gms.internal.vision.zzid$zzd r1 = r12.zzyr
            com.google.android.gms.internal.vision.zzlk r1 = r1.zzyd
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 17
            if (r0 == r1) goto L_0x05ef
            r1 = 18
            if (r0 == r1) goto L_0x05ef
            goto L_0x05fb
        L_0x05ef:
            com.google.android.gms.internal.vision.zzid$zzd r0 = r12.zzyr
            java.lang.Object r0 = r14.zza(r0)
            if (r0 == 0) goto L_0x05fb
            java.lang.Object r4 = com.google.android.gms.internal.vision.zzie.zzb(r0, r4)
        L_0x05fb:
            com.google.android.gms.internal.vision.zzid$zzd r0 = r12.zzyr
            r14.zza(r0, (java.lang.Object) r4)
        L_0x0600:
            r0 = r2
            goto L_0x0622
        L_0x0602:
            r13 = r35
            r15 = r36
            goto L_0x060d
        L_0x0607:
            r13 = r35
            r15 = r36
            r10 = r40
        L_0x060d:
            r37 = r6
            r11 = r23
            r6 = r38
            com.google.android.gms.internal.vision.zzkw r4 = zzv(r35)
            r0 = r7
            r1 = r36
            r3 = r38
            r5 = r40
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.vision.zzkw) r4, (com.google.android.gms.internal.vision.zzgm) r5)
        L_0x0622:
            r3 = r7
            r1 = r11
            r14 = r13
            r12 = r15
            r2 = r18
            r5 = r24
            r13 = r6
            r15 = r8
            r11 = r9
            r9 = r10
            r10 = r31
            r6 = r37
            goto L_0x0019
        L_0x0634:
            r7 = r37
            r9 = r15
            r11 = r23
            r15 = r34
            r14 = r35
            r12 = r36
            r13 = r38
            r3 = r7
            r1 = r11
            r2 = r18
            r5 = r24
            r6 = r25
            r10 = r31
            r11 = r9
            r9 = r40
            goto L_0x0019
        L_0x0650:
            r24 = r5
            r25 = r6
            r31 = r10
            r9 = r11
            r6 = r13
            r13 = r14
            r8 = r15
            r4 = 0
            r1 = r25
            r2 = 1048575(0xfffff, float:1.469367E-39)
        L_0x0660:
            if (r1 == r2) goto L_0x0668
            long r1 = (long) r1
            r7 = r31
            r7.putInt(r13, r1, r5)
        L_0x0668:
            int r1 = r8.zzaau
        L_0x066a:
            int r2 = r8.zzaav
            if (r1 >= r2) goto L_0x067e
            int[] r2 = r8.zzaat
            r2 = r2[r1]
            com.google.android.gms.internal.vision.zzkx<?, ?> r5 = r8.zzaay
            java.lang.Object r2 = r8.zza((java.lang.Object) r13, (int) r2, r4, r5)
            r4 = r2
            com.google.android.gms.internal.vision.zzkw r4 = (com.google.android.gms.internal.vision.zzkw) r4
            int r1 = r1 + 1
            goto L_0x066a
        L_0x067e:
            if (r4 == 0) goto L_0x0685
            com.google.android.gms.internal.vision.zzkx<?, ?> r1 = r8.zzaay
            r1.zzg(r13, r4)
        L_0x0685:
            if (r9 != 0) goto L_0x068f
            if (r0 != r6) goto L_0x068a
            goto L_0x0693
        L_0x068a:
            com.google.android.gms.internal.vision.zzin r0 = com.google.android.gms.internal.vision.zzin.zzhn()
            throw r0
        L_0x068f:
            if (r0 > r6) goto L_0x0694
            if (r3 != r9) goto L_0x0694
        L_0x0693:
            return r0
        L_0x0694:
            com.google.android.gms.internal.vision.zzin r0 = com.google.android.gms.internal.vision.zzin.zzhn()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Object, byte[], int, int, int, com.google.android.gms.internal.vision.zzgm):int");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02dc, code lost:
        if (r0 == r15) goto L_0x0348;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0323, code lost:
        if (r0 == r15) goto L_0x0348;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0346, code lost:
        if (r0 == r15) goto L_0x0348;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0348, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01ca, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0234, code lost:
        r6 = r6 | r23;
        r9 = r7;
        r2 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0239, code lost:
        r2 = r5;
        r29 = r7;
        r18 = r10;
        r7 = r20;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.vision.zzgm r35) throws java.io.IOException {
        /*
            r30 = this;
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            boolean r0 = r15.zzaar
            if (r0 == 0) goto L_0x038d
            sun.misc.Unsafe r9 = zzaaj
            r10 = -1
            r16 = 0
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r33
            r1 = -1
            r2 = 0
            r6 = 0
            r7 = 1048575(0xfffff, float:1.469367E-39)
        L_0x001e:
            if (r0 >= r13) goto L_0x0370
            int r3 = r0 + 1
            byte r0 = r12[r0]
            if (r0 >= 0) goto L_0x0030
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((int) r0, (byte[]) r12, (int) r3, (com.google.android.gms.internal.vision.zzgm) r11)
            int r3 = r11.zztk
            r4 = r0
            r17 = r3
            goto L_0x0033
        L_0x0030:
            r17 = r0
            r4 = r3
        L_0x0033:
            int r5 = r17 >>> 3
            r3 = r17 & 7
            if (r5 <= r1) goto L_0x0040
            int r2 = r2 / 3
            int r0 = r15.zzv(r5, r2)
            goto L_0x0044
        L_0x0040:
            int r0 = r15.zzca(r5)
        L_0x0044:
            r2 = r0
            if (r2 != r10) goto L_0x0052
            r2 = r4
            r25 = r5
            r29 = r9
            r18 = 0
        L_0x004e:
            r20 = -1
            goto L_0x034a
        L_0x0052:
            int[] r0 = r15.zzaak
            int r1 = r2 + 1
            r1 = r0[r1]
            r18 = 267386880(0xff00000, float:2.3665827E-29)
            r18 = r1 & r18
            int r10 = r18 >>> 20
            r33 = r5
            r5 = r1 & r8
            r18 = r9
            long r8 = (long) r5
            r5 = 17
            r21 = r1
            if (r10 > r5) goto L_0x0242
            int r5 = r2 + 2
            r0 = r0[r5]
            int r5 = r0 >>> 20
            r1 = 1
            int r23 = r1 << r5
            r5 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r0 & r5
            r20 = r2
            if (r0 == r7) goto L_0x0092
            if (r7 == r5) goto L_0x0085
            long r1 = (long) r7
            r7 = r18
            r7.putInt(r14, r1, r6)
            goto L_0x0087
        L_0x0085:
            r7 = r18
        L_0x0087:
            if (r0 == r5) goto L_0x008f
            long r1 = (long) r0
            int r1 = r7.getInt(r14, r1)
            r6 = r1
        L_0x008f:
            r2 = r7
            r7 = r0
            goto L_0x0094
        L_0x0092:
            r2 = r18
        L_0x0094:
            r0 = 5
            switch(r10) {
                case 0: goto L_0x021d;
                case 1: goto L_0x0206;
                case 2: goto L_0x01e4;
                case 3: goto L_0x01e4;
                case 4: goto L_0x01cd;
                case 5: goto L_0x01ab;
                case 6: goto L_0x0194;
                case 7: goto L_0x0174;
                case 8: goto L_0x0151;
                case 9: goto L_0x0124;
                case 10: goto L_0x010c;
                case 11: goto L_0x01cd;
                case 12: goto L_0x00f5;
                case 13: goto L_0x0194;
                case 14: goto L_0x01ab;
                case 15: goto L_0x00da;
                case 16: goto L_0x00a5;
                default: goto L_0x0098;
            }
        L_0x0098:
            r25 = r33
            r5 = r4
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            goto L_0x0239
        L_0x00a5:
            if (r3 != 0) goto L_0x00cc
            int r10 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r4, r11)
            long r0 = r11.zztl
            long r17 = com.google.android.gms.internal.vision.zzhe.zzr(r0)
            r0 = r2
            r1 = r31
            r4 = r20
            r20 = r7
            r7 = r2
            r2 = r8
            r25 = r33
            r8 = r4
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r17
            r0.putLong(r1, r2, r4)
            r6 = r6 | r23
            r9 = r7
            r2 = r8
            r0 = r10
            goto L_0x028c
        L_0x00cc:
            r25 = r33
            r8 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            r5 = r4
            r10 = r8
            goto L_0x0239
        L_0x00da:
            r25 = r33
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x01ca
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r4, r11)
            int r1 = r11.zztk
            int r1 = com.google.android.gms.internal.vision.zzhe.zzbb(r1)
            r7.putInt(r14, r8, r1)
            goto L_0x0234
        L_0x00f5:
            r25 = r33
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x01ca
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r4, r11)
            int r1 = r11.zztk
            r7.putInt(r14, r8, r1)
            goto L_0x0234
        L_0x010c:
            r25 = r33
            r10 = r20
            r0 = 2
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x01ca
            int r0 = com.google.android.gms.internal.vision.zzgk.zze(r12, r4, r11)
            java.lang.Object r1 = r11.zztm
            r7.putObject(r14, r8, r1)
            goto L_0x0234
        L_0x0124:
            r25 = r33
            r10 = r20
            r0 = 2
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x01ca
            com.google.android.gms.internal.vision.zzkf r0 = r15.zzbu(r10)
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((com.google.android.gms.internal.vision.zzkf) r0, (byte[]) r12, (int) r4, (int) r13, (com.google.android.gms.internal.vision.zzgm) r11)
            java.lang.Object r1 = r7.getObject(r14, r8)
            if (r1 != 0) goto L_0x0146
            java.lang.Object r1 = r11.zztm
            r7.putObject(r14, r8, r1)
            goto L_0x0234
        L_0x0146:
            java.lang.Object r2 = r11.zztm
            java.lang.Object r1 = com.google.android.gms.internal.vision.zzie.zzb(r1, r2)
            r7.putObject(r14, r8, r1)
            goto L_0x0234
        L_0x0151:
            r25 = r33
            r10 = r20
            r0 = 2
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x01ca
            r0 = 536870912(0x20000000, float:1.0842022E-19)
            r0 = r21 & r0
            if (r0 != 0) goto L_0x0169
            int r0 = com.google.android.gms.internal.vision.zzgk.zzc(r12, r4, r11)
            goto L_0x016d
        L_0x0169:
            int r0 = com.google.android.gms.internal.vision.zzgk.zzd(r12, r4, r11)
        L_0x016d:
            java.lang.Object r1 = r11.zztm
            r7.putObject(r14, r8, r1)
            goto L_0x0234
        L_0x0174:
            r25 = r33
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x01ca
            int r0 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r4, r11)
            long r1 = r11.zztl
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x018e
            r1 = 1
            goto L_0x018f
        L_0x018e:
            r1 = 0
        L_0x018f:
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r14, (long) r8, (boolean) r1)
            goto L_0x0234
        L_0x0194:
            r25 = r33
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x01ca
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r4)
            r7.putInt(r14, r8, r0)
            int r0 = r4 + 4
            goto L_0x0234
        L_0x01ab:
            r25 = r33
            r10 = r20
            r0 = 1
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x01ca
            long r17 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r4)
            r0 = r7
            r1 = r31
            r2 = r8
            r8 = r4
            r4 = r17
            r0.putLong(r1, r2, r4)
            int r0 = r8 + 8
            goto L_0x0234
        L_0x01ca:
            r5 = r4
            goto L_0x0239
        L_0x01cd:
            r25 = r33
            r5 = r4
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x0239
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r12, r5, r11)
            int r1 = r11.zztk
            r7.putInt(r14, r8, r1)
            goto L_0x0234
        L_0x01e4:
            r25 = r33
            r5 = r4
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != 0) goto L_0x0239
            int r17 = com.google.android.gms.internal.vision.zzgk.zzb(r12, r5, r11)
            long r4 = r11.zztl
            r0 = r7
            r1 = r31
            r2 = r8
            r0.putLong(r1, r2, r4)
            r6 = r6 | r23
            r9 = r7
            r2 = r10
            r0 = r17
            goto L_0x028c
        L_0x0206:
            r25 = r33
            r5 = r4
            r10 = r20
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x0239
            float r0 = com.google.android.gms.internal.vision.zzgk.zzd(r12, r5)
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r14, (long) r8, (float) r0)
            int r0 = r5 + 4
            goto L_0x0234
        L_0x021d:
            r25 = r33
            r5 = r4
            r10 = r20
            r0 = 1
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r20 = r7
            r7 = r2
            if (r3 != r0) goto L_0x0239
            double r0 = com.google.android.gms.internal.vision.zzgk.zzc(r12, r5)
            com.google.android.gms.internal.vision.zzld.zza((java.lang.Object) r14, (long) r8, (double) r0)
            int r0 = r5 + 8
        L_0x0234:
            r6 = r6 | r23
            r9 = r7
            r2 = r10
            goto L_0x028c
        L_0x0239:
            r2 = r5
            r29 = r7
            r18 = r10
            r7 = r20
            goto L_0x004e
        L_0x0242:
            r25 = r33
            r5 = r4
            r20 = r7
            r7 = r18
            r26 = 1048575(0xfffff, float:1.469367E-39)
            r4 = r2
            r0 = 27
            if (r10 != r0) goto L_0x029f
            r0 = 2
            if (r3 != r0) goto L_0x0292
            java.lang.Object r0 = r7.getObject(r14, r8)
            com.google.android.gms.internal.vision.zzik r0 = (com.google.android.gms.internal.vision.zzik) r0
            boolean r1 = r0.zzei()
            if (r1 != 0) goto L_0x0272
            int r1 = r0.size()
            if (r1 != 0) goto L_0x0269
            r1 = 10
            goto L_0x026b
        L_0x0269:
            int r1 = r1 << 1
        L_0x026b:
            com.google.android.gms.internal.vision.zzik r0 = r0.zzan(r1)
            r7.putObject(r14, r8, r0)
        L_0x0272:
            r8 = r0
            com.google.android.gms.internal.vision.zzkf r0 = r15.zzbu(r4)
            r1 = r17
            r2 = r32
            r3 = r5
            r18 = r4
            r4 = r34
            r5 = r8
            r8 = r6
            r6 = r35
            int r0 = com.google.android.gms.internal.vision.zzgk.zza(r0, r1, r2, r3, r4, r5, r6)
            r9 = r7
            r6 = r8
            r2 = r18
        L_0x028c:
            r7 = r20
            r1 = r25
            goto L_0x036a
        L_0x0292:
            r18 = r4
            r15 = r5
            r27 = r6
            r29 = r7
            r28 = r20
            r20 = -1
            goto L_0x0326
        L_0x029f:
            r18 = r4
            r0 = 49
            if (r10 > r0) goto L_0x02f4
            r1 = r21
            long r1 = (long) r1
            r0 = r30
            r21 = r1
            r1 = r31
            r2 = r32
            r4 = r3
            r3 = r5
            r33 = r4
            r4 = r34
            r15 = r5
            r5 = r17
            r27 = r6
            r6 = r25
            r28 = r20
            r20 = r7
            r7 = r33
            r23 = r8
            r9 = 1048575(0xfffff, float:1.469367E-39)
            r8 = r18
            r19 = r10
            r29 = r20
            r20 = -1
            r9 = r21
            r11 = r19
            r12 = r23
            r14 = r35
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (long) r9, (int) r11, (long) r12, (com.google.android.gms.internal.vision.zzgm) r14)
            if (r0 != r15) goto L_0x02e0
            goto L_0x0348
        L_0x02e0:
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r18
            r1 = r25
            r6 = r27
            r7 = r28
            goto L_0x0368
        L_0x02f4:
            r33 = r3
            r15 = r5
            r27 = r6
            r29 = r7
            r23 = r8
            r19 = r10
            r28 = r20
            r1 = r21
            r20 = -1
            r0 = 50
            r9 = r19
            if (r9 != r0) goto L_0x032c
            r7 = r33
            r0 = 2
            if (r7 != r0) goto L_0x0326
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r18
            r6 = r23
            r8 = r35
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (long) r6, (com.google.android.gms.internal.vision.zzgm) r8)
            if (r0 != r15) goto L_0x02e0
            goto L_0x0348
        L_0x0326:
            r2 = r15
        L_0x0327:
            r6 = r27
            r7 = r28
            goto L_0x034a
        L_0x032c:
            r7 = r33
            r0 = r30
            r8 = r1
            r1 = r31
            r2 = r32
            r3 = r15
            r4 = r34
            r5 = r17
            r6 = r25
            r10 = r23
            r12 = r18
            r13 = r35
            int r0 = r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (int) r6, (int) r7, (int) r8, (int) r9, (long) r10, (int) r12, (com.google.android.gms.internal.vision.zzgm) r13)
            if (r0 != r15) goto L_0x02e0
        L_0x0348:
            r2 = r0
            goto L_0x0327
        L_0x034a:
            com.google.android.gms.internal.vision.zzkw r4 = zzv(r31)
            r0 = r17
            r1 = r32
            r3 = r34
            r5 = r35
            int r0 = com.google.android.gms.internal.vision.zzgk.zza((int) r0, (byte[]) r1, (int) r2, (int) r3, (com.google.android.gms.internal.vision.zzkw) r4, (com.google.android.gms.internal.vision.zzgm) r5)
            r15 = r30
            r14 = r31
            r12 = r32
            r13 = r34
            r11 = r35
            r2 = r18
            r1 = r25
        L_0x0368:
            r9 = r29
        L_0x036a:
            r8 = 1048575(0xfffff, float:1.469367E-39)
            r10 = -1
            goto L_0x001e
        L_0x0370:
            r27 = r6
            r29 = r9
            r1 = 1048575(0xfffff, float:1.469367E-39)
            if (r7 == r1) goto L_0x0383
            long r1 = (long) r7
            r3 = r31
            r6 = r27
            r4 = r29
            r4.putInt(r3, r1, r6)
        L_0x0383:
            r4 = r34
            if (r0 != r4) goto L_0x0388
            return
        L_0x0388:
            com.google.android.gms.internal.vision.zzin r0 = com.google.android.gms.internal.vision.zzin.zzhn()
            throw r0
        L_0x038d:
            r4 = r13
            r3 = r14
            r5 = 0
            r0 = r30
            r1 = r31
            r2 = r32
            r3 = r33
            r4 = r34
            r6 = r35
            r0.zza(r1, (byte[]) r2, (int) r3, (int) r4, (int) r5, (com.google.android.gms.internal.vision.zzgm) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzjr.zza(java.lang.Object, byte[], int, int, com.google.android.gms.internal.vision.zzgm):void");
    }

    public final void zzj(T t) {
        int i;
        int i2 = this.zzaau;
        while (true) {
            i = this.zzaav;
            if (i2 >= i) {
                break;
            }
            long zzbx = (long) (zzbx(this.zzaat[i2]) & 1048575);
            Object zzp = zzld.zzp(t, zzbx);
            if (zzp != null) {
                zzld.zza((Object) t, zzbx, this.zzaba.zzq(zzp));
            }
            i2++;
        }
        int length = this.zzaat.length;
        while (i < length) {
            this.zzaax.zzb(t, (long) this.zzaat[i]);
            i++;
        }
        this.zzaay.zzj(t);
        if (this.zzaap) {
            this.zzaaz.zzj(t);
        }
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzkx<UT, UB> zzkx) {
        zzij zzbw;
        int i2 = this.zzaak[i];
        Object zzp = zzld.zzp(obj, (long) (zzbx(i) & 1048575));
        if (zzp == null || (zzbw = zzbw(i)) == null) {
            return ub;
        }
        return zza(i, i2, this.zzaba.zzn(zzp), zzbw, ub, zzkx);
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzij zzij, UB ub, zzkx<UT, UB> zzkx) {
        zzje<?, ?> zzs = this.zzaba.zzs(zzbv(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!zzij.zzg(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzkx.zzjd();
                }
                zzha zzaw = zzgs.zzaw(zzjf.zza(zzs, next.getKey(), next.getValue()));
                try {
                    zzjf.zza(zzaw.zzfq(), zzs, next.getKey(), next.getValue());
                    zzkx.zza(ub, i2, zzaw.zzfp());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    public final boolean zzw(T t) {
        int i;
        int i2;
        T t2 = t;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            boolean z = true;
            if (i5 >= this.zzaau) {
                return !this.zzaap || this.zzaaz.zzh(t2).isInitialized();
            }
            int i6 = this.zzaat[i5];
            int i7 = this.zzaak[i6];
            int zzbx = zzbx(i6);
            int i8 = this.zzaak[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzaaj.getInt(t2, (long) i9);
                }
                i = i4;
                i2 = i9;
            } else {
                i2 = i3;
                i = i4;
            }
            if (((268435456 & zzbx) != 0) && !zza(t, i6, i2, i, i10)) {
                return false;
            }
            int i11 = (267386880 & zzbx) >>> 20;
            if (i11 != 9 && i11 != 17) {
                if (i11 != 27) {
                    if (i11 == 60 || i11 == 68) {
                        if (zzb(t2, i7, i6) && !zza((Object) t2, zzbx, zzbu(i6))) {
                            return false;
                        }
                    } else if (i11 != 49) {
                        if (i11 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzo = this.zzaba.zzo(zzld.zzp(t2, (long) (zzbx & 1048575)));
                            if (!zzo.isEmpty()) {
                                if (this.zzaba.zzs(zzbv(i6)).zzaae.zzjk() == zzlr.MESSAGE) {
                                    zzkf<?> zzkf = null;
                                    Iterator<?> it = zzo.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        if (zzkf == null) {
                                            zzkf = zzkb.zzik().zzf(next.getClass());
                                        }
                                        if (!zzkf.zzw(next)) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzld.zzp(t2, (long) (zzbx & 1048575));
                if (!list.isEmpty()) {
                    zzkf zzbu = zzbu(i6);
                    int i12 = 0;
                    while (true) {
                        if (i12 >= list.size()) {
                            break;
                        } else if (!zzbu.zzw(list.get(i12))) {
                            z = false;
                            break;
                        } else {
                            i12++;
                        }
                    }
                }
                if (!z) {
                    return false;
                }
            } else if (zza(t, i6, i2, i, i10) && !zza((Object) t2, zzbx, zzbu(i6))) {
                return false;
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
    }

    private static boolean zza(Object obj, int i, zzkf zzkf) {
        return zzkf.zzw(zzld.zzp(obj, (long) (i & 1048575)));
    }

    private static void zza(int i, Object obj, zzlq zzlq) throws IOException {
        if (obj instanceof String) {
            zzlq.zza(i, (String) obj);
        } else {
            zzlq.zza(i, (zzgs) obj);
        }
    }

    private final void zza(Object obj, int i, zzkc zzkc) throws IOException {
        if (zzbz(i)) {
            zzld.zza(obj, (long) (i & 1048575), (Object) zzkc.zzew());
        } else if (this.zzaaq) {
            zzld.zza(obj, (long) (i & 1048575), (Object) zzkc.readString());
        } else {
            zzld.zza(obj, (long) (i & 1048575), (Object) zzkc.zzex());
        }
    }

    private final int zzbx(int i) {
        return this.zzaak[i + 1];
    }

    private final int zzby(int i) {
        return this.zzaak[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzld.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzld.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzld.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzld.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzld.zzp(t, j)).booleanValue();
    }

    private final boolean zzc(T t, T t2, int i) {
        return zzc(t, i) == zzc(t2, i);
    }

    private final boolean zza(T t, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzc(t, i);
        }
        return (i3 & i4) != 0;
    }

    private final boolean zzc(T t, int i) {
        int zzby = zzby(i);
        long j = (long) (zzby & 1048575);
        if (j == 1048575) {
            int zzbx = zzbx(i);
            long j2 = (long) (zzbx & 1048575);
            switch ((zzbx & 267386880) >>> 20) {
                case 0:
                    return zzld.zzo(t, j2) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                case 1:
                    return zzld.zzn(t, j2) != 0.0f;
                case 2:
                    return zzld.zzl(t, j2) != 0;
                case 3:
                    return zzld.zzl(t, j2) != 0;
                case 4:
                    return zzld.zzk(t, j2) != 0;
                case 5:
                    return zzld.zzl(t, j2) != 0;
                case 6:
                    return zzld.zzk(t, j2) != 0;
                case 7:
                    return zzld.zzm(t, j2);
                case 8:
                    Object zzp = zzld.zzp(t, j2);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzgs) {
                        return !zzgs.zztt.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzld.zzp(t, j2) != null;
                case 10:
                    return !zzgs.zztt.equals(zzld.zzp(t, j2));
                case 11:
                    return zzld.zzk(t, j2) != 0;
                case 12:
                    return zzld.zzk(t, j2) != 0;
                case 13:
                    return zzld.zzk(t, j2) != 0;
                case 14:
                    return zzld.zzl(t, j2) != 0;
                case 15:
                    return zzld.zzk(t, j2) != 0;
                case 16:
                    return zzld.zzl(t, j2) != 0;
                case 17:
                    return zzld.zzp(t, j2) != null;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            return (zzld.zzk(t, j) & (1 << (zzby >>> 20))) != 0;
        }
    }

    private final void zzd(T t, int i) {
        int zzby = zzby(i);
        long j = (long) (1048575 & zzby);
        if (j != 1048575) {
            zzld.zzb((Object) t, j, (1 << (zzby >>> 20)) | zzld.zzk(t, j));
        }
    }

    private final boolean zzb(T t, int i, int i2) {
        return zzld.zzk(t, (long) (zzby(i2) & 1048575)) == i;
    }

    private final void zzc(T t, int i, int i2) {
        zzld.zzb((Object) t, (long) (zzby(i2) & 1048575), i);
    }

    private final int zzca(int i) {
        if (i < this.zzaam || i > this.zzaan) {
            return -1;
        }
        return zzw(i, 0);
    }

    private final int zzv(int i, int i2) {
        if (i < this.zzaam || i > this.zzaan) {
            return -1;
        }
        return zzw(i, i2);
    }

    private final int zzw(int i, int i2) {
        int length = (this.zzaak.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzaak[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
