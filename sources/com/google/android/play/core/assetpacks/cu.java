package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.aa;

final class cu {
    private static final aa a = new aa("ExtractorTaskFinder");
    private final cr b;
    private final bb c;
    private final bn d;

    cu(cr crVar, bb bbVar, bn bnVar) {
        this.b = crVar;
        this.c = bbVar;
        this.d = bnVar;
    }

    private final boolean a(co coVar, cp cpVar) {
        bb bbVar = this.c;
        cn cnVar = coVar.c;
        String str = cnVar.a;
        return bbVar.e(str, coVar.b, cnVar.b, cpVar.a).exists();
    }

    private static boolean a(cp cpVar) {
        int i = cpVar.f;
        return i == 1 || i == 2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.google.android.play.core.assetpacks.dh} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.google.android.play.core.assetpacks.dh} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: com.google.android.play.core.assetpacks.de} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: com.google.android.play.core.assetpacks.de} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: com.google.android.play.core.assetpacks.du} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.google.android.play.core.assetpacks.du} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v6, resolved type: com.google.android.play.core.assetpacks.du} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v7, resolved type: com.google.android.play.core.assetpacks.de} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v15, resolved type: com.google.android.play.core.assetpacks.dh} */
    /* JADX WARNING: type inference failed for: r0v11, types: [com.google.android.play.core.assetpacks.ct] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        a.a("Found final move task for session %s with pack %s.", java.lang.Integer.valueOf(r4.a), r4.c.a);
        r11 = r4.a;
        r8 = r4.c;
        r10 = new com.google.android.play.core.assetpacks.dh(r11, r8.a, r4.b, r8.b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02d0, code lost:
        a.a("Found extraction task for patch for session %s, pack %s, slice %s.", java.lang.Integer.valueOf(r6.a), r6.c.a, r10.a);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        r11 = r1.c;
        r3 = r6.c;
        r0 = new java.io.FileInputStream(r11.e(r3.a, r6.b, r3.b, r10.a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        r3 = r6.a;
        r4 = r6.c;
        r19 = new com.google.android.play.core.assetpacks.bv(r3, r4.a, r6.b, r4.b, r10.a, 0, 0, 1, r4.d, r4.c, r0);
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.play.core.assetpacks.ct a() {
        /*
            r33 = this;
            r1 = r33
            com.google.android.play.core.assetpacks.cr r0 = r1.b     // Catch:{ all -> 0x0433 }
            r0.a()     // Catch:{ all -> 0x0433 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0433 }
            r2.<init>()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cr r0 = r1.b     // Catch:{ all -> 0x0433 }
            java.util.Map r0 = r0.c()     // Catch:{ all -> 0x0433 }
            java.util.Collection r0 = r0.values()     // Catch:{ all -> 0x0433 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0433 }
        L_0x001a:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x0433 }
            if (r3 == 0) goto L_0x0034
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.co r3 = (com.google.android.play.core.assetpacks.co) r3     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r4 = r3.c     // Catch:{ all -> 0x0433 }
            int r4 = r4.c     // Catch:{ all -> 0x0433 }
            boolean r4 = com.google.android.play.core.assetpacks.dd.c(r4)     // Catch:{ all -> 0x0433 }
            if (r4 == 0) goto L_0x001a
            r2.add(r3)     // Catch:{ all -> 0x0433 }
            goto L_0x001a
        L_0x0034:
            boolean r0 = r2.isEmpty()     // Catch:{ all -> 0x0433 }
            if (r0 != 0) goto L_0x042c
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x0433 }
        L_0x003e:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0433 }
            r5 = 2
            r6 = 1
            r7 = 0
            if (r4 == 0) goto L_0x00ac
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.co r4 = (com.google.android.play.core.assetpacks.co) r4     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bb r8 = r1.c     // Catch:{ IOException -> 0x008d }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ IOException -> 0x008d }
            java.lang.String r10 = r9.a     // Catch:{ IOException -> 0x008d }
            int r11 = r4.b     // Catch:{ IOException -> 0x008d }
            long r12 = r9.b     // Catch:{ IOException -> 0x008d }
            int r8 = r8.d(r10, r11, r12)     // Catch:{ IOException -> 0x008d }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ IOException -> 0x008d }
            java.util.List<com.google.android.play.core.assetpacks.cp> r9 = r9.e     // Catch:{ IOException -> 0x008d }
            int r9 = r9.size()     // Catch:{ IOException -> 0x008d }
            if (r8 != r9) goto L_0x003e
            com.google.android.play.core.internal.aa r0 = a     // Catch:{ all -> 0x0433 }
            java.lang.Object[] r8 = new java.lang.Object[r5]     // Catch:{ all -> 0x0433 }
            int r9 = r4.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0433 }
            r8[r7] = r9     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r9.a     // Catch:{ all -> 0x0433 }
            r8[r6] = r9     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = "Found final move task for session %s with pack %s."
            r0.a(r9, r8)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.dh r0 = new com.google.android.play.core.assetpacks.dh     // Catch:{ all -> 0x0433 }
            int r11 = r4.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r8 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r12 = r8.a     // Catch:{ all -> 0x0433 }
            int r13 = r4.b     // Catch:{ all -> 0x0433 }
            long r14 = r8.b     // Catch:{ all -> 0x0433 }
            r10 = r0
            r10.<init>(r11, r12, r13, r14)     // Catch:{ all -> 0x0433 }
            goto L_0x00ad
        L_0x008d:
            r0 = move-exception
            com.google.android.play.core.assetpacks.by r2 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x0433 }
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ all -> 0x0433 }
            int r5 = r4.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0433 }
            r3[r7] = r5     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r5 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r5 = r5.a     // Catch:{ all -> 0x0433 }
            r3[r6] = r5     // Catch:{ all -> 0x0433 }
            java.lang.String r5 = "Failed to check number of completed merges for session %s, pack %s"
            java.lang.String r3 = java.lang.String.format(r5, r3)     // Catch:{ all -> 0x0433 }
            int r4 = r4.a     // Catch:{ all -> 0x0433 }
            r2.<init>(r3, r0, r4)     // Catch:{ all -> 0x0433 }
            throw r2     // Catch:{ all -> 0x0433 }
        L_0x00ac:
            r0 = 0
        L_0x00ad:
            if (r0 != 0) goto L_0x0426
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x0433 }
        L_0x00b3:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0433 }
            r8 = 3
            if (r4 == 0) goto L_0x0137
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.co r4 = (com.google.android.play.core.assetpacks.co) r4     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ all -> 0x0433 }
            int r9 = r9.c     // Catch:{ all -> 0x0433 }
            boolean r9 = com.google.android.play.core.assetpacks.dd.c(r9)     // Catch:{ all -> 0x0433 }
            if (r9 == 0) goto L_0x00b3
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ all -> 0x0433 }
            java.util.List<com.google.android.play.core.assetpacks.cp> r9 = r9.e     // Catch:{ all -> 0x0433 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0433 }
        L_0x00d2:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0433 }
            if (r10 == 0) goto L_0x00b3
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cp r10 = (com.google.android.play.core.assetpacks.cp) r10     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bb r11 = r1.c     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r12 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r13 = r12.a     // Catch:{ all -> 0x0433 }
            int r14 = r4.b     // Catch:{ all -> 0x0433 }
            long r5 = r12.b     // Catch:{ all -> 0x0433 }
            java.lang.String r15 = r10.a     // Catch:{ all -> 0x0433 }
            r12 = r13
            r13 = r14
            r16 = r15
            r14 = r5
            java.io.File r5 = r11.b(r12, r13, r14, r16)     // Catch:{ all -> 0x0433 }
            boolean r5 = r5.exists()     // Catch:{ all -> 0x0433 }
            if (r5 == 0) goto L_0x0134
            com.google.android.play.core.internal.aa r0 = a     // Catch:{ all -> 0x0433 }
            java.lang.Object[] r5 = new java.lang.Object[r8]     // Catch:{ all -> 0x0433 }
            int r6 = r4.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x0433 }
            r5[r7] = r6     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r6 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r6 = r6.a     // Catch:{ all -> 0x0433 }
            r9 = 1
            r5[r9] = r6     // Catch:{ all -> 0x0433 }
            java.lang.String r6 = r10.a     // Catch:{ all -> 0x0433 }
            r9 = 2
            r5[r9] = r6     // Catch:{ all -> 0x0433 }
            java.lang.String r6 = "Found merge task for session %s with pack %s and slice %s."
            r0.a(r6, r5)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.de r0 = new com.google.android.play.core.assetpacks.de     // Catch:{ all -> 0x0433 }
            int r5 = r4.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r6 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r6.a     // Catch:{ all -> 0x0433 }
            int r4 = r4.b     // Catch:{ all -> 0x0433 }
            long r11 = r6.b     // Catch:{ all -> 0x0433 }
            java.lang.String r6 = r10.a     // Catch:{ all -> 0x0433 }
            r19 = r0
            r20 = r5
            r21 = r9
            r22 = r4
            r23 = r11
            r25 = r6
            r19.<init>(r20, r21, r22, r23, r25)     // Catch:{ all -> 0x0433 }
            goto L_0x0138
        L_0x0134:
            r5 = 2
            r6 = 1
            goto L_0x00d2
        L_0x0137:
            r0 = 0
        L_0x0138:
            if (r0 != 0) goto L_0x0426
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x0433 }
        L_0x013e:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0433 }
            if (r4 == 0) goto L_0x01d8
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.co r4 = (com.google.android.play.core.assetpacks.co) r4     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r5 = r4.c     // Catch:{ all -> 0x0433 }
            int r5 = r5.c     // Catch:{ all -> 0x0433 }
            boolean r5 = com.google.android.play.core.assetpacks.dd.c(r5)     // Catch:{ all -> 0x0433 }
            if (r5 == 0) goto L_0x013e
            com.google.android.play.core.assetpacks.cn r5 = r4.c     // Catch:{ all -> 0x0433 }
            java.util.List<com.google.android.play.core.assetpacks.cp> r5 = r5.e     // Catch:{ all -> 0x0433 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0433 }
        L_0x015c:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0433 }
            if (r6 == 0) goto L_0x013e
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cp r6 = (com.google.android.play.core.assetpacks.cp) r6     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.dr r16 = new com.google.android.play.core.assetpacks.dr     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bb r10 = r1.c     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r11 = r9.a     // Catch:{ all -> 0x0433 }
            int r12 = r4.b     // Catch:{ all -> 0x0433 }
            long r13 = r9.b     // Catch:{ all -> 0x0433 }
            java.lang.String r15 = r6.a     // Catch:{ all -> 0x0433 }
            r9 = r16
            r9.<init>(r10, r11, r12, r13, r15)     // Catch:{ all -> 0x0433 }
            boolean r9 = r16.d()     // Catch:{ all -> 0x0433 }
            if (r9 == 0) goto L_0x015c
            com.google.android.play.core.assetpacks.bb r10 = r1.c     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r11 = r9.a     // Catch:{ all -> 0x0433 }
            int r12 = r4.b     // Catch:{ all -> 0x0433 }
            long r13 = r9.b     // Catch:{ all -> 0x0433 }
            java.lang.String r15 = r6.a     // Catch:{ all -> 0x0433 }
            java.io.File r9 = r10.a((java.lang.String) r11, (int) r12, (long) r13, (java.lang.String) r15)     // Catch:{ all -> 0x0433 }
            boolean r9 = r9.exists()     // Catch:{ all -> 0x0433 }
            if (r9 == 0) goto L_0x015c
            com.google.android.play.core.internal.aa r0 = a     // Catch:{ all -> 0x0433 }
            java.lang.Object[] r5 = new java.lang.Object[r8]     // Catch:{ all -> 0x0433 }
            int r9 = r4.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0433 }
            r5[r7] = r9     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r9.a     // Catch:{ all -> 0x0433 }
            r10 = 1
            r5[r10] = r9     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r6.a     // Catch:{ all -> 0x0433 }
            r10 = 2
            r5[r10] = r9     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = "Found verify task for session %s with pack %s and slice %s."
            r0.a(r9, r5)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.du r0 = new com.google.android.play.core.assetpacks.du     // Catch:{ all -> 0x0433 }
            int r5 = r4.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r4.c     // Catch:{ all -> 0x0433 }
            java.lang.String r10 = r9.a     // Catch:{ all -> 0x0433 }
            int r4 = r4.b     // Catch:{ all -> 0x0433 }
            long r11 = r9.b     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r6.a     // Catch:{ all -> 0x0433 }
            java.lang.String r13 = r6.b     // Catch:{ all -> 0x0433 }
            long r14 = r6.c     // Catch:{ all -> 0x0433 }
            r19 = r0
            r20 = r5
            r21 = r10
            r22 = r4
            r23 = r11
            r25 = r9
            r26 = r13
            r19.<init>(r20, r21, r22, r23, r25, r26)     // Catch:{ all -> 0x0433 }
            goto L_0x01d9
        L_0x01d8:
            r0 = 0
        L_0x01d9:
            if (r0 != 0) goto L_0x0426
            java.util.Iterator r4 = r2.iterator()     // Catch:{ all -> 0x0433 }
        L_0x01df:
            boolean r0 = r4.hasNext()     // Catch:{ all -> 0x0433 }
            r5 = 4
            if (r0 == 0) goto L_0x0361
            java.lang.Object r0 = r4.next()     // Catch:{ all -> 0x0433 }
            r6 = r0
            com.google.android.play.core.assetpacks.co r6 = (com.google.android.play.core.assetpacks.co) r6     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r0 = r6.c     // Catch:{ all -> 0x0433 }
            int r0 = r0.c     // Catch:{ all -> 0x0433 }
            boolean r0 = com.google.android.play.core.assetpacks.dd.c(r0)     // Catch:{ all -> 0x0433 }
            if (r0 == 0) goto L_0x01df
            com.google.android.play.core.assetpacks.cn r0 = r6.c     // Catch:{ all -> 0x0433 }
            java.util.List<com.google.android.play.core.assetpacks.cp> r0 = r0.e     // Catch:{ all -> 0x0433 }
            java.util.Iterator r9 = r0.iterator()     // Catch:{ all -> 0x0433 }
        L_0x01ff:
            boolean r0 = r9.hasNext()     // Catch:{ all -> 0x0433 }
            if (r0 == 0) goto L_0x01df
            java.lang.Object r0 = r9.next()     // Catch:{ all -> 0x0433 }
            r10 = r0
            com.google.android.play.core.assetpacks.cp r10 = (com.google.android.play.core.assetpacks.cp) r10     // Catch:{ all -> 0x0433 }
            boolean r0 = a(r10)     // Catch:{ all -> 0x0433 }
            if (r0 != 0) goto L_0x02c8
            com.google.android.play.core.assetpacks.dr r0 = new com.google.android.play.core.assetpacks.dr     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bb r11 = r1.c     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r12 = r6.c     // Catch:{ all -> 0x0433 }
            java.lang.String r13 = r12.a     // Catch:{ all -> 0x0433 }
            int r14 = r6.b     // Catch:{ all -> 0x0433 }
            r16 = r4
            long r3 = r12.b     // Catch:{ all -> 0x0433 }
            java.lang.String r12 = r10.a     // Catch:{ all -> 0x0433 }
            r19 = r0
            r20 = r11
            r21 = r13
            r22 = r14
            r23 = r3
            r25 = r12
            r19.<init>(r20, r21, r22, r23, r25)     // Catch:{ all -> 0x0433 }
            int r0 = r0.c()     // Catch:{ IOException -> 0x0236 }
            goto L_0x0245
        L_0x0236:
            r0 = move-exception
            r3 = r0
            com.google.android.play.core.internal.aa r0 = a     // Catch:{ all -> 0x0433 }
            r4 = 1
            java.lang.Object[] r11 = new java.lang.Object[r4]     // Catch:{ all -> 0x0433 }
            r11[r7] = r3     // Catch:{ all -> 0x0433 }
            java.lang.String r3 = "Slice checkpoint corrupt, restarting extraction. %s"
            r0.b(r3, r11)     // Catch:{ all -> 0x0433 }
            r0 = 0
        L_0x0245:
            r3 = -1
            if (r0 == r3) goto L_0x02c4
            java.util.List<com.google.android.play.core.assetpacks.cm> r3 = r10.d     // Catch:{ all -> 0x0433 }
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cm r3 = (com.google.android.play.core.assetpacks.cm) r3     // Catch:{ all -> 0x0433 }
            boolean r3 = r3.a     // Catch:{ all -> 0x0433 }
            if (r3 == 0) goto L_0x02c4
            com.google.android.play.core.internal.aa r3 = a     // Catch:{ all -> 0x0433 }
            r4 = 5
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0433 }
            int r9 = r10.e     // Catch:{ all -> 0x0433 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0433 }
            r4[r7] = r9     // Catch:{ all -> 0x0433 }
            int r9 = r6.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x0433 }
            r11 = 1
            r4[r11] = r9     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r6.c     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r9.a     // Catch:{ all -> 0x0433 }
            r11 = 2
            r4[r11] = r9     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r10.a     // Catch:{ all -> 0x0433 }
            r4[r8] = r9     // Catch:{ all -> 0x0433 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x0433 }
            r4[r5] = r9     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = "Found extraction task using compression format %s for session %s, pack %s, slice %s, chunk %s."
            r3.a(r9, r4)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bn r3 = r1.d     // Catch:{ all -> 0x0433 }
            int r4 = r6.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r6.c     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r9.a     // Catch:{ all -> 0x0433 }
            java.lang.String r11 = r10.a     // Catch:{ all -> 0x0433 }
            java.io.InputStream r32 = r3.a(r4, r9, r11, r0)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bv r3 = new com.google.android.play.core.assetpacks.bv     // Catch:{ all -> 0x0433 }
            int r4 = r6.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r9 = r6.c     // Catch:{ all -> 0x0433 }
            java.lang.String r11 = r9.a     // Catch:{ all -> 0x0433 }
            int r12 = r6.b     // Catch:{ all -> 0x0433 }
            long r13 = r9.b     // Catch:{ all -> 0x0433 }
            java.lang.String r9 = r10.a     // Catch:{ all -> 0x0433 }
            int r15 = r10.e     // Catch:{ all -> 0x0433 }
            java.util.List<com.google.android.play.core.assetpacks.cm> r10 = r10.d     // Catch:{ all -> 0x0433 }
            int r28 = r10.size()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r6 = r6.c     // Catch:{ all -> 0x0433 }
            long r7 = r6.d     // Catch:{ all -> 0x0433 }
            int r6 = r6.c     // Catch:{ all -> 0x0433 }
            r19 = r3
            r20 = r4
            r21 = r11
            r22 = r12
            r23 = r13
            r25 = r9
            r26 = r15
            r27 = r0
            r29 = r7
            r31 = r6
            r19.<init>(r20, r21, r22, r23, r25, r26, r27, r28, r29, r31, r32)     // Catch:{ all -> 0x0433 }
            r15 = r3
            goto L_0x0362
        L_0x02c4:
            r4 = r16
            goto L_0x01ff
        L_0x02c8:
            r16 = r4
            boolean r0 = r1.a(r6, r10)     // Catch:{ all -> 0x0433 }
            if (r0 == 0) goto L_0x035b
            com.google.android.play.core.internal.aa r0 = a     // Catch:{ all -> 0x0433 }
            r3 = 3
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ all -> 0x0433 }
            int r3 = r6.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x0433 }
            r7 = 0
            r4[r7] = r3     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r3 = r6.c     // Catch:{ all -> 0x0433 }
            java.lang.String r3 = r3.a     // Catch:{ all -> 0x0433 }
            r7 = 1
            r4[r7] = r3     // Catch:{ all -> 0x0433 }
            java.lang.String r3 = r10.a     // Catch:{ all -> 0x0433 }
            r7 = 2
            r4[r7] = r3     // Catch:{ all -> 0x0433 }
            java.lang.String r3 = "Found extraction task for patch for session %s, pack %s, slice %s."
            r0.a(r3, r4)     // Catch:{ all -> 0x0433 }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0334 }
            com.google.android.play.core.assetpacks.bb r11 = r1.c     // Catch:{ FileNotFoundException -> 0x0334 }
            com.google.android.play.core.assetpacks.cn r3 = r6.c     // Catch:{ FileNotFoundException -> 0x0334 }
            java.lang.String r12 = r3.a     // Catch:{ FileNotFoundException -> 0x0334 }
            int r13 = r6.b     // Catch:{ FileNotFoundException -> 0x0334 }
            long r14 = r3.b     // Catch:{ FileNotFoundException -> 0x0334 }
            java.lang.String r3 = r10.a     // Catch:{ FileNotFoundException -> 0x0334 }
            r16 = r3
            java.io.File r3 = r11.e(r12, r13, r14, r16)     // Catch:{ FileNotFoundException -> 0x0334 }
            r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0334 }
            com.google.android.play.core.assetpacks.bv r15 = new com.google.android.play.core.assetpacks.bv     // Catch:{ all -> 0x0433 }
            int r3 = r6.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r4 = r6.c     // Catch:{ all -> 0x0433 }
            java.lang.String r7 = r4.a     // Catch:{ all -> 0x0433 }
            int r6 = r6.b     // Catch:{ all -> 0x0433 }
            long r8 = r4.b     // Catch:{ all -> 0x0433 }
            java.lang.String r10 = r10.a     // Catch:{ all -> 0x0433 }
            long r11 = r4.d     // Catch:{ all -> 0x0433 }
            r26 = 0
            r27 = 0
            r28 = 1
            int r4 = r4.c     // Catch:{ all -> 0x0433 }
            r19 = r15
            r20 = r3
            r21 = r7
            r22 = r6
            r23 = r8
            r25 = r10
            r29 = r11
            r31 = r4
            r32 = r0
            r19.<init>(r20, r21, r22, r23, r25, r26, r27, r28, r29, r31, r32)     // Catch:{ all -> 0x0433 }
            goto L_0x0362
        L_0x0334:
            r0 = move-exception
            com.google.android.play.core.assetpacks.by r2 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x0433 }
            r3 = 3
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0433 }
            int r4 = r6.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0433 }
            r5 = 0
            r3[r5] = r4     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r4 = r6.c     // Catch:{ all -> 0x0433 }
            java.lang.String r4 = r4.a     // Catch:{ all -> 0x0433 }
            r5 = 1
            r3[r5] = r4     // Catch:{ all -> 0x0433 }
            java.lang.String r4 = r10.a     // Catch:{ all -> 0x0433 }
            r5 = 2
            r3[r5] = r4     // Catch:{ all -> 0x0433 }
            java.lang.String r4 = "Error finding patch, session %s packName %s sliceId %s"
            java.lang.String r3 = java.lang.String.format(r4, r3)     // Catch:{ all -> 0x0433 }
            int r4 = r6.a     // Catch:{ all -> 0x0433 }
            r2.<init>(r3, r0, r4)     // Catch:{ all -> 0x0433 }
            throw r2     // Catch:{ all -> 0x0433 }
        L_0x035b:
            r4 = r16
            r7 = 0
            r8 = 3
            goto L_0x01ff
        L_0x0361:
            r15 = 0
        L_0x0362:
            if (r15 != 0) goto L_0x0420
            java.util.Iterator r0 = r2.iterator()     // Catch:{ all -> 0x0433 }
        L_0x0368:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x0433 }
            if (r2 == 0) goto L_0x0415
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.co r2 = (com.google.android.play.core.assetpacks.co) r2     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r3 = r2.c     // Catch:{ all -> 0x0433 }
            int r3 = r3.c     // Catch:{ all -> 0x0433 }
            boolean r3 = com.google.android.play.core.assetpacks.dd.c(r3)     // Catch:{ all -> 0x0433 }
            if (r3 == 0) goto L_0x0368
            com.google.android.play.core.assetpacks.cn r3 = r2.c     // Catch:{ all -> 0x0433 }
            java.util.List<com.google.android.play.core.assetpacks.cp> r3 = r3.e     // Catch:{ all -> 0x0433 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0433 }
        L_0x0386:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0433 }
            if (r4 == 0) goto L_0x0368
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cp r4 = (com.google.android.play.core.assetpacks.cp) r4     // Catch:{ all -> 0x0433 }
            boolean r6 = a(r4)     // Catch:{ all -> 0x0433 }
            if (r6 == 0) goto L_0x0386
            java.util.List<com.google.android.play.core.assetpacks.cm> r6 = r4.d     // Catch:{ all -> 0x0433 }
            r7 = 0
            java.lang.Object r6 = r6.get(r7)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cm r6 = (com.google.android.play.core.assetpacks.cm) r6     // Catch:{ all -> 0x0433 }
            boolean r6 = r6.a     // Catch:{ all -> 0x0433 }
            if (r6 == 0) goto L_0x0386
            boolean r6 = r1.a(r2, r4)     // Catch:{ all -> 0x0433 }
            if (r6 != 0) goto L_0x0386
            com.google.android.play.core.internal.aa r0 = a     // Catch:{ all -> 0x0433 }
            java.lang.Object[] r3 = new java.lang.Object[r5]     // Catch:{ all -> 0x0433 }
            int r5 = r4.f     // Catch:{ all -> 0x0433 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0433 }
            r6 = 0
            r3[r6] = r5     // Catch:{ all -> 0x0433 }
            int r5 = r2.a     // Catch:{ all -> 0x0433 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0433 }
            r6 = 1
            r3[r6] = r5     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r5 = r2.c     // Catch:{ all -> 0x0433 }
            java.lang.String r5 = r5.a     // Catch:{ all -> 0x0433 }
            r7 = 2
            r3[r7] = r5     // Catch:{ all -> 0x0433 }
            java.lang.String r5 = r4.a     // Catch:{ all -> 0x0433 }
            r8 = 3
            r3[r8] = r5     // Catch:{ all -> 0x0433 }
            java.lang.String r5 = "Found patch slice task using patch format %s for session %s, pack %s, slice %s."
            r0.a(r5, r3)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bn r0 = r1.d     // Catch:{ all -> 0x0433 }
            int r3 = r2.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r5 = r2.c     // Catch:{ all -> 0x0433 }
            java.lang.String r5 = r5.a     // Catch:{ all -> 0x0433 }
            java.lang.String r6 = r4.a     // Catch:{ all -> 0x0433 }
            r9 = 0
            java.io.InputStream r23 = r0.a(r3, r5, r6, r9)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.dn r0 = new com.google.android.play.core.assetpacks.dn     // Catch:{ all -> 0x0433 }
            int r11 = r2.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r3 = r2.c     // Catch:{ all -> 0x0433 }
            java.lang.String r12 = r3.a     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bb r3 = r1.c     // Catch:{ all -> 0x0433 }
            int r13 = r3.e(r12)     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.bb r3 = r1.c     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r5 = r2.c     // Catch:{ all -> 0x0433 }
            java.lang.String r5 = r5.a     // Catch:{ all -> 0x0433 }
            long r14 = r3.f(r5)     // Catch:{ all -> 0x0433 }
            int r3 = r2.b     // Catch:{ all -> 0x0433 }
            com.google.android.play.core.assetpacks.cn r2 = r2.c     // Catch:{ all -> 0x0433 }
            long r5 = r2.b     // Catch:{ all -> 0x0433 }
            int r2 = r4.f     // Catch:{ all -> 0x0433 }
            java.lang.String r7 = r4.a     // Catch:{ all -> 0x0433 }
            long r8 = r4.c     // Catch:{ all -> 0x0433 }
            r10 = r0
            r16 = r3
            r17 = r5
            r19 = r2
            r20 = r7
            r21 = r8
            r10.<init>(r11, r12, r13, r14, r16, r17, r19, r20, r21, r23)     // Catch:{ all -> 0x0433 }
            r15 = r0
            goto L_0x0416
        L_0x0415:
            r15 = 0
        L_0x0416:
            com.google.android.play.core.assetpacks.cr r0 = r1.b
            r0.b()
            if (r15 != 0) goto L_0x041f
            r2 = 0
            return r2
        L_0x041f:
            return r15
        L_0x0420:
            com.google.android.play.core.assetpacks.cr r0 = r1.b
            r0.b()
            return r15
        L_0x0426:
            com.google.android.play.core.assetpacks.cr r2 = r1.b
            r2.b()
            return r0
        L_0x042c:
            com.google.android.play.core.assetpacks.cr r0 = r1.b
            r0.b()
            r2 = 0
            return r2
        L_0x0433:
            r0 = move-exception
            com.google.android.play.core.assetpacks.cr r2 = r1.b
            r2.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.cu.a():com.google.android.play.core.assetpacks.ct");
    }
}
