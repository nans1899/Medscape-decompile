package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zznv extends zzoa {
    private static final int[] zzbgz = new int[0];
    private final zzoe zzbha;
    private final AtomicReference<zzny> zzbhb;

    public zznv() {
        this((zzoe) null);
    }

    private static int zze(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    private static boolean zze(int i, boolean z) {
        int i2 = i & 3;
        if (i2 != 3) {
            return z && i2 == 2;
        }
        return true;
    }

    private zznv(zzoe zzoe) {
        this.zzbha = null;
        this.zzbhb = new AtomicReference<>(new zzny());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0184, code lost:
        if (r10.zzagy <= r12) goto L_0x0189;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01da  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzob[] zza(com.google.android.gms.internal.ads.zzhw[] r36, com.google.android.gms.internal.ads.zznp[] r37, int[][][] r38) throws com.google.android.gms.internal.ads.zzhd {
        /*
            r35 = this;
            r0 = r36
            int r1 = r0.length
            com.google.android.gms.internal.ads.zzob[] r2 = new com.google.android.gms.internal.ads.zzob[r1]
            r3 = r35
            java.util.concurrent.atomic.AtomicReference<com.google.android.gms.internal.ads.zzny> r4 = r3.zzbhb
            java.lang.Object r4 = r4.get()
            com.google.android.gms.internal.ads.zzny r4 = (com.google.android.gms.internal.ads.zzny) r4
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x0012:
            r9 = 2
            if (r6 >= r1) goto L_0x026a
            r13 = r0[r6]
            int r13 = r13.getTrackType()
            if (r9 != r13) goto L_0x0252
            if (r7 != 0) goto L_0x023c
            r7 = r37[r6]
            r13 = r38[r6]
            int r14 = r4.zzbhg
            int r15 = r4.zzbhh
            int r11 = r4.zzbhi
            int r9 = r4.viewportWidth
            int r5 = r4.viewportHeight
            boolean r10 = r4.zzbhl
            boolean r12 = r4.zzbhj
            boolean r3 = r4.zzbhk
            r20 = r1
            r19 = r4
            r24 = r8
            r0 = 0
            r1 = 0
            r4 = 0
            r21 = 0
            r22 = -1
            r23 = -1
        L_0x0042:
            int r8 = r7.length
            if (r0 >= r8) goto L_0x021c
            com.google.android.gms.internal.ads.zznq r8 = r7.zzbd(r0)
            r25 = r7
            java.util.ArrayList r7 = new java.util.ArrayList
            r26 = r2
            int r2 = r8.length
            r7.<init>(r2)
            r27 = r6
            r2 = 0
        L_0x0058:
            int r6 = r8.length
            if (r2 >= r6) goto L_0x0066
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r7.add(r6)
            int r2 = r2 + 1
            goto L_0x0058
        L_0x0066:
            r2 = 2147483647(0x7fffffff, float:NaN)
            if (r9 == r2) goto L_0x0138
            if (r5 != r2) goto L_0x006f
            goto L_0x0138
        L_0x006f:
            r28 = r1
            r6 = 0
        L_0x0072:
            int r1 = r8.length
            if (r6 >= r1) goto L_0x0102
            com.google.android.gms.internal.ads.zzho r1 = r8.zzbe(r6)
            r29 = r4
            int r4 = r1.width
            if (r4 <= 0) goto L_0x00e8
            int r4 = r1.height
            if (r4 <= 0) goto L_0x00e8
            int r4 = r1.width
            r30 = r12
            int r12 = r1.height
            if (r10 == 0) goto L_0x00a2
            r31 = r10
            if (r4 <= r12) goto L_0x0092
            r10 = 1
            goto L_0x0093
        L_0x0092:
            r10 = 0
        L_0x0093:
            r32 = r5
            if (r9 <= r5) goto L_0x0099
            r5 = 1
            goto L_0x009a
        L_0x0099:
            r5 = 0
        L_0x009a:
            if (r10 == r5) goto L_0x00a6
            r5 = r9
            r33 = r5
            r10 = r32
            goto L_0x00ab
        L_0x00a2:
            r32 = r5
            r31 = r10
        L_0x00a6:
            r10 = r9
            r33 = r10
            r5 = r32
        L_0x00ab:
            int r9 = r4 * r5
            r34 = r11
            int r11 = r12 * r10
            if (r9 < r11) goto L_0x00bd
            android.graphics.Point r5 = new android.graphics.Point
            int r4 = com.google.android.gms.internal.ads.zzpq.zzf(r11, r4)
            r5.<init>(r10, r4)
            goto L_0x00c7
        L_0x00bd:
            android.graphics.Point r4 = new android.graphics.Point
            int r9 = com.google.android.gms.internal.ads.zzpq.zzf(r9, r12)
            r4.<init>(r9, r5)
            r5 = r4
        L_0x00c7:
            int r4 = r1.width
            int r9 = r1.height
            int r4 = r4 * r9
            int r9 = r1.width
            int r10 = r5.x
            float r10 = (float) r10
            r11 = 1065017672(0x3f7ae148, float:0.98)
            float r10 = r10 * r11
            int r10 = (int) r10
            if (r9 < r10) goto L_0x00f2
            int r1 = r1.height
            int r5 = r5.y
            float r5 = (float) r5
            float r5 = r5 * r11
            int r5 = (int) r5
            if (r1 < r5) goto L_0x00f2
            if (r4 >= r2) goto L_0x00f2
            r2 = r4
            goto L_0x00f2
        L_0x00e8:
            r32 = r5
            r33 = r9
            r31 = r10
            r34 = r11
            r30 = r12
        L_0x00f2:
            int r6 = r6 + 1
            r4 = r29
            r12 = r30
            r10 = r31
            r5 = r32
            r9 = r33
            r11 = r34
            goto L_0x0072
        L_0x0102:
            r29 = r4
            r32 = r5
            r33 = r9
            r31 = r10
            r34 = r11
            r30 = r12
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r2 == r1) goto L_0x0146
            int r1 = r7.size()
            r4 = 1
            int r1 = r1 - r4
        L_0x0119:
            if (r1 < 0) goto L_0x0146
            java.lang.Object r4 = r7.get(r1)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            com.google.android.gms.internal.ads.zzho r4 = r8.zzbe(r4)
            int r4 = r4.zzez()
            r5 = -1
            if (r4 == r5) goto L_0x0132
            if (r4 <= r2) goto L_0x0135
        L_0x0132:
            r7.remove(r1)
        L_0x0135:
            int r1 = r1 + -1
            goto L_0x0119
        L_0x0138:
            r28 = r1
            r29 = r4
            r32 = r5
            r33 = r9
            r31 = r10
            r34 = r11
            r30 = r12
        L_0x0146:
            r1 = r13[r0]
            r5 = r21
            r6 = r22
            r9 = r23
            r4 = r29
            r2 = 0
        L_0x0151:
            int r10 = r8.length
            if (r2 >= r10) goto L_0x01fb
            r10 = r1[r2]
            boolean r10 = zze((int) r10, (boolean) r3)
            if (r10 == 0) goto L_0x01e7
            com.google.android.gms.internal.ads.zzho r10 = r8.zzbe(r2)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r2)
            boolean r11 = r7.contains(r11)
            if (r11 == 0) goto L_0x018b
            int r11 = r10.width
            r12 = -1
            if (r11 == r12) goto L_0x0174
            int r11 = r10.width
            if (r11 > r14) goto L_0x018b
        L_0x0174:
            int r11 = r10.height
            if (r11 == r12) goto L_0x017c
            int r11 = r10.height
            if (r11 > r15) goto L_0x018b
        L_0x017c:
            int r11 = r10.zzagy
            if (r11 == r12) goto L_0x0187
            int r11 = r10.zzagy
            r12 = r34
            if (r11 > r12) goto L_0x018d
            goto L_0x0189
        L_0x0187:
            r12 = r34
        L_0x0189:
            r11 = 1
            goto L_0x018e
        L_0x018b:
            r12 = r34
        L_0x018d:
            r11 = 0
        L_0x018e:
            if (r11 != 0) goto L_0x019a
            if (r30 == 0) goto L_0x0193
            goto L_0x019a
        L_0x0193:
            r23 = r1
            r21 = r3
            r22 = r4
            goto L_0x01ef
        L_0x019a:
            r21 = r3
            r22 = r4
            if (r11 == 0) goto L_0x01a2
            r3 = 2
            goto L_0x01a3
        L_0x01a2:
            r3 = 1
        L_0x01a3:
            r4 = r1[r2]
            r23 = r1
            r1 = 0
            boolean r4 = zze((int) r4, (boolean) r1)
            if (r4 == 0) goto L_0x01b0
            int r3 = r3 + 1000
        L_0x01b0:
            if (r3 <= r5) goto L_0x01b4
            r1 = 1
            goto L_0x01b5
        L_0x01b4:
            r1 = 0
        L_0x01b5:
            if (r3 != r5) goto L_0x01d8
            int r1 = r10.zzez()
            if (r1 == r6) goto L_0x01c6
            int r1 = r10.zzez()
            int r1 = zze((int) r1, (int) r6)
            goto L_0x01cc
        L_0x01c6:
            int r1 = r10.zzagy
            int r1 = zze((int) r1, (int) r9)
        L_0x01cc:
            if (r4 == 0) goto L_0x01d3
            if (r11 == 0) goto L_0x01d3
            if (r1 <= 0) goto L_0x01d7
            goto L_0x01d5
        L_0x01d3:
            if (r1 >= 0) goto L_0x01d7
        L_0x01d5:
            r1 = 1
            goto L_0x01d8
        L_0x01d7:
            r1 = 0
        L_0x01d8:
            if (r1 == 0) goto L_0x01ef
            int r1 = r10.zzagy
            int r4 = r10.zzez()
            r9 = r1
            r28 = r2
            r5 = r3
            r6 = r4
            r4 = r8
            goto L_0x01f1
        L_0x01e7:
            r23 = r1
            r21 = r3
            r22 = r4
            r12 = r34
        L_0x01ef:
            r4 = r22
        L_0x01f1:
            int r2 = r2 + 1
            r34 = r12
            r3 = r21
            r1 = r23
            goto L_0x0151
        L_0x01fb:
            r21 = r3
            r22 = r4
            r12 = r34
            int r0 = r0 + 1
            r23 = r9
            r11 = r12
            r7 = r25
            r2 = r26
            r1 = r28
            r12 = r30
            r10 = r31
            r9 = r33
            r21 = r5
            r22 = r6
            r6 = r27
            r5 = r32
            goto L_0x0042
        L_0x021c:
            r28 = r1
            r26 = r2
            r29 = r4
            r27 = r6
            if (r29 != 0) goto L_0x0228
            r11 = 0
            goto L_0x0231
        L_0x0228:
            com.google.android.gms.internal.ads.zznx r11 = new com.google.android.gms.internal.ads.zznx
            r1 = r28
            r0 = r29
            r11.<init>(r0, r1)
        L_0x0231:
            r26[r27] = r11
            r0 = r26[r27]
            if (r0 == 0) goto L_0x0239
            r0 = 1
            goto L_0x023a
        L_0x0239:
            r0 = 0
        L_0x023a:
            r7 = r0
            goto L_0x0246
        L_0x023c:
            r20 = r1
            r26 = r2
            r19 = r4
            r27 = r6
            r24 = r8
        L_0x0246:
            r0 = r37[r27]
            int r0 = r0.length
            if (r0 <= 0) goto L_0x024e
            r12 = 1
            goto L_0x024f
        L_0x024e:
            r12 = 0
        L_0x024f:
            r8 = r24 | r12
            goto L_0x025c
        L_0x0252:
            r20 = r1
            r26 = r2
            r19 = r4
            r27 = r6
            r24 = r8
        L_0x025c:
            int r6 = r27 + 1
            r3 = r35
            r0 = r36
            r4 = r19
            r1 = r20
            r2 = r26
            goto L_0x0012
        L_0x026a:
            r26 = r2
            r19 = r4
            r24 = r8
            r3 = r1
            r0 = 0
            r1 = 0
            r2 = 0
        L_0x0274:
            if (r1 >= r3) goto L_0x043d
            r4 = r36[r1]
            int r4 = r4.getTrackType()
            r6 = 3
            r7 = 1
            if (r4 == r7) goto L_0x03aa
            r7 = 2
            if (r4 == r7) goto L_0x03a3
            if (r4 == r6) goto L_0x02fb
            r4 = r36[r1]
            r4.getTrackType()
            r4 = r37[r1]
            r5 = r38[r1]
            r7 = r19
            boolean r6 = r7.zzbhk
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x0296:
            int r12 = r4.length
            if (r9 >= r12) goto L_0x02e8
            com.google.android.gms.internal.ads.zznq r12 = r4.zzbd(r9)
            r13 = r5[r9]
            r14 = 0
        L_0x02a1:
            int r15 = r12.length
            if (r14 >= r15) goto L_0x02e1
            r15 = r13[r14]
            boolean r15 = zze((int) r15, (boolean) r6)
            if (r15 == 0) goto L_0x02d6
            com.google.android.gms.internal.ads.zzho r15 = r12.zzbe(r14)
            int r15 = r15.zzahs
            r18 = 1
            r15 = r15 & 1
            if (r15 == 0) goto L_0x02bb
            r15 = 1
            goto L_0x02bc
        L_0x02bb:
            r15 = 0
        L_0x02bc:
            r20 = r3
            if (r15 == 0) goto L_0x02c2
            r15 = 2
            goto L_0x02c3
        L_0x02c2:
            r15 = 1
        L_0x02c3:
            r3 = r13[r14]
            r19 = r4
            r4 = 0
            boolean r3 = zze((int) r3, (boolean) r4)
            if (r3 == 0) goto L_0x02d0
            int r15 = r15 + 1000
        L_0x02d0:
            if (r15 <= r11) goto L_0x02da
            r8 = r12
            r10 = r14
            r11 = r15
            goto L_0x02da
        L_0x02d6:
            r20 = r3
            r19 = r4
        L_0x02da:
            int r14 = r14 + 1
            r4 = r19
            r3 = r20
            goto L_0x02a1
        L_0x02e1:
            r20 = r3
            r19 = r4
            int r9 = r9 + 1
            goto L_0x0296
        L_0x02e8:
            r20 = r3
            if (r8 != 0) goto L_0x02ee
            r3 = 0
            goto L_0x02f3
        L_0x02ee:
            com.google.android.gms.internal.ads.zznx r3 = new com.google.android.gms.internal.ads.zznx
            r3.<init>(r8, r10)
        L_0x02f3:
            r26[r1] = r3
            r5 = -1
            r15 = 0
            r17 = 2
            goto L_0x039f
        L_0x02fb:
            r20 = r3
            r7 = r19
            if (r2 != 0) goto L_0x03a7
            r2 = r37[r1]
            r3 = r38[r1]
            boolean r4 = r7.zzbhk
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x030b:
            int r12 = r2.length
            if (r8 >= r12) goto L_0x0388
            com.google.android.gms.internal.ads.zznq r12 = r2.zzbd(r8)
            r13 = r3[r8]
            r14 = r11
            r11 = r10
            r10 = r9
            r9 = 0
        L_0x0319:
            int r15 = r12.length
            if (r9 >= r15) goto L_0x037d
            r15 = r13[r9]
            boolean r15 = zze((int) r15, (boolean) r4)
            if (r15 == 0) goto L_0x0373
            com.google.android.gms.internal.ads.zzho r15 = r12.zzbe(r9)
            int r5 = r15.zzahs
            r18 = 1
            r5 = r5 & 1
            if (r5 == 0) goto L_0x0333
            r5 = 1
            goto L_0x0334
        L_0x0333:
            r5 = 0
        L_0x0334:
            int r6 = r15.zzahs
            r17 = 2
            r6 = r6 & 2
            r22 = r2
            r2 = 0
            if (r6 == 0) goto L_0x0341
            r6 = 1
            goto L_0x0342
        L_0x0341:
            r6 = 0
        L_0x0342:
            boolean r23 = zza(r15, r2)
            if (r23 == 0) goto L_0x0352
            if (r5 == 0) goto L_0x034c
            r2 = 6
            goto L_0x0362
        L_0x034c:
            if (r6 != 0) goto L_0x0350
            r2 = 5
            goto L_0x0362
        L_0x0350:
            r2 = 4
            goto L_0x0362
        L_0x0352:
            if (r5 == 0) goto L_0x0356
            r2 = 3
            goto L_0x0362
        L_0x0356:
            if (r6 == 0) goto L_0x0377
            r2 = 0
            boolean r5 = zza(r15, r2)
            if (r5 == 0) goto L_0x0361
            r2 = 2
            goto L_0x0362
        L_0x0361:
            r2 = 1
        L_0x0362:
            r5 = r13[r9]
            r6 = 0
            boolean r5 = zze((int) r5, (boolean) r6)
            if (r5 == 0) goto L_0x036d
            int r2 = r2 + 1000
        L_0x036d:
            if (r2 <= r11) goto L_0x0377
            r11 = r2
            r10 = r9
            r14 = r12
            goto L_0x0377
        L_0x0373:
            r22 = r2
            r17 = 2
        L_0x0377:
            int r9 = r9 + 1
            r2 = r22
            r6 = 3
            goto L_0x0319
        L_0x037d:
            r22 = r2
            r17 = 2
            int r8 = r8 + 1
            r9 = r10
            r10 = r11
            r11 = r14
            r6 = 3
            goto L_0x030b
        L_0x0388:
            r17 = 2
            if (r11 != 0) goto L_0x038e
            r2 = 0
            goto L_0x0393
        L_0x038e:
            com.google.android.gms.internal.ads.zznx r2 = new com.google.android.gms.internal.ads.zznx
            r2.<init>(r11, r9)
        L_0x0393:
            r26[r1] = r2
            r2 = r26[r1]
            if (r2 == 0) goto L_0x039b
            r4 = 1
            goto L_0x039c
        L_0x039b:
            r4 = 0
        L_0x039c:
            r2 = r4
        L_0x039d:
            r5 = -1
            r15 = 0
        L_0x039f:
            r18 = 1
            goto L_0x0435
        L_0x03a3:
            r20 = r3
            r7 = r19
        L_0x03a7:
            r17 = 2
            goto L_0x039d
        L_0x03aa:
            r20 = r3
            r7 = r19
            r17 = 2
            if (r0 != 0) goto L_0x039d
            r0 = r37[r1]
            r3 = r38[r1]
            boolean r4 = r7.zzbhk
            r5 = 0
            r6 = 0
            r8 = -1
            r9 = -1
        L_0x03bc:
            int r10 = r0.length
            if (r5 >= r10) goto L_0x041a
            com.google.android.gms.internal.ads.zznq r10 = r0.zzbd(r5)
            r11 = r3[r5]
            r12 = r9
            r9 = r8
            r8 = r6
            r6 = 0
        L_0x03ca:
            int r13 = r10.length
            if (r6 >= r13) goto L_0x0411
            r13 = r11[r6]
            boolean r13 = zze((int) r13, (boolean) r4)
            if (r13 == 0) goto L_0x040b
            com.google.android.gms.internal.ads.zzho r13 = r10.zzbe(r6)
            r14 = r11[r6]
            int r15 = r13.zzahs
            r18 = 1
            r15 = r15 & 1
            if (r15 == 0) goto L_0x03e8
            r15 = 0
            r16 = 1
            goto L_0x03eb
        L_0x03e8:
            r15 = 0
            r16 = 0
        L_0x03eb:
            boolean r13 = zza(r13, r15)
            if (r13 == 0) goto L_0x03f7
            if (r16 == 0) goto L_0x03f5
            r13 = 4
            goto L_0x03fc
        L_0x03f5:
            r13 = 3
            goto L_0x03fc
        L_0x03f7:
            if (r16 == 0) goto L_0x03fb
            r13 = 2
            goto L_0x03fc
        L_0x03fb:
            r13 = 1
        L_0x03fc:
            r15 = 0
            boolean r14 = zze((int) r14, (boolean) r15)
            if (r14 == 0) goto L_0x0405
            int r13 = r13 + 1000
        L_0x0405:
            if (r13 <= r8) goto L_0x040e
            r9 = r5
            r12 = r6
            r8 = r13
            goto L_0x040e
        L_0x040b:
            r15 = 0
            r18 = 1
        L_0x040e:
            int r6 = r6 + 1
            goto L_0x03ca
        L_0x0411:
            r15 = 0
            r18 = 1
            int r5 = r5 + 1
            r6 = r8
            r8 = r9
            r9 = r12
            goto L_0x03bc
        L_0x041a:
            r5 = -1
            r15 = 0
            r18 = 1
            if (r8 != r5) goto L_0x0422
            r3 = 0
            goto L_0x042b
        L_0x0422:
            com.google.android.gms.internal.ads.zznq r0 = r0.zzbd(r8)
            com.google.android.gms.internal.ads.zznx r3 = new com.google.android.gms.internal.ads.zznx
            r3.<init>(r0, r9)
        L_0x042b:
            r26[r1] = r3
            r0 = r26[r1]
            if (r0 == 0) goto L_0x0433
            r4 = 1
            goto L_0x0434
        L_0x0433:
            r4 = 0
        L_0x0434:
            r0 = r4
        L_0x0435:
            int r1 = r1 + 1
            r19 = r7
            r3 = r20
            goto L_0x0274
        L_0x043d:
            return r26
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznv.zza(com.google.android.gms.internal.ads.zzhw[], com.google.android.gms.internal.ads.zznp[], int[][][]):com.google.android.gms.internal.ads.zzob[]");
    }

    private static boolean zza(zzho zzho, String str) {
        return str != null && TextUtils.equals(str, zzpq.zzbj(zzho.zzaht));
    }
}
