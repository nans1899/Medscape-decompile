package com.wbmd.adlibrary.utilities;

public class PreloadedAdIndexer {
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0013, code lost:
        r7 = r7 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.HashSet<java.lang.Integer> getAdIndexes(int r4, int r5, int r6, int r7) {
        /*
            r3 = this;
            int r5 = r3.getNumberOfAds(r5, r6, r7)
            java.util.HashSet r6 = new java.util.HashSet
            r6.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            r6.add(r0)
            if (r5 > 0) goto L_0x0013
            return r6
        L_0x0013:
            r0 = 1
            int r7 = r7 + r0
            int r5 = r7 / r5
            if (r5 > r0) goto L_0x001a
            return r6
        L_0x001a:
            r0 = 0
            r1 = 0
        L_0x001c:
            if (r0 >= r7) goto L_0x0032
            int r2 = r5 + r1
            if (r0 != r2) goto L_0x002f
            int r1 = r5 - r4
            int r1 = r1 + r4
            if (r0 == r1) goto L_0x002e
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            r6.add(r1)
        L_0x002e:
            r1 = r0
        L_0x002f:
            int r0 = r0 + 1
            goto L_0x001c
        L_0x0032:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.adlibrary.utilities.PreloadedAdIndexer.getAdIndexes(int, int, int, int):java.util.HashSet");
    }

    private int getNumberOfAds(int i, int i2, int i3) {
        return (i2 * i3) / i;
    }
}
