package org.unbescape.properties;

final class PropertiesUnescapeUtil {
    private static final char ESCAPE_PREFIX = '\\';
    private static final char ESCAPE_UHEXA_PREFIX2 = 'u';
    private static char[] HEXA_CHARS_LOWER = "0123456789abcdef".toCharArray();
    private static char[] HEXA_CHARS_UPPER = "0123456789ABCDEF".toCharArray();

    private PropertiesUnescapeUtil() {
    }

    static int parseIntFromReference(String str, int i, int i2, int i3) {
        int i4 = 0;
        while (i < i2) {
            char charAt = str.charAt(i);
            int i5 = -1;
            int i6 = 0;
            while (true) {
                char[] cArr = HEXA_CHARS_UPPER;
                if (i6 >= cArr.length) {
                    break;
                } else if (charAt == cArr[i6] || charAt == HEXA_CHARS_LOWER[i6]) {
                    i5 = i6;
                } else {
                    i6++;
                }
            }
            i4 = (i4 * i3) + i5;
            i++;
        }
        return i4;
    }

    static int parseIntFromReference(char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i < i2) {
            char c = cArr[i];
            int i5 = -1;
            int i6 = 0;
            while (true) {
                char[] cArr2 = HEXA_CHARS_UPPER;
                if (i6 >= cArr2.length) {
                    break;
                } else if (c == cArr2[i6] || c == HEXA_CHARS_LOWER[i6]) {
                    i5 = i6;
                } else {
                    i6++;
                }
            }
            i5 = i6;
            i4 = (i4 * i3) + i5;
            i++;
        }
        return i4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String unescape(java.lang.String r11) {
        /*
            r0 = 0
            if (r11 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r11.length()
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000b:
            if (r2 >= r1) goto L_0x00ac
            char r5 = r11.charAt(r2)
            r6 = 92
            if (r5 != r6) goto L_0x00a8
            int r7 = r2 + 1
            if (r7 < r1) goto L_0x001b
            goto L_0x00a8
        L_0x001b:
            r8 = -1
            if (r5 != r6) goto L_0x0083
            char r5 = r11.charAt(r7)
            r9 = 102(0x66, float:1.43E-43)
            if (r5 == r6) goto L_0x0041
            if (r5 == r9) goto L_0x003f
            r6 = 110(0x6e, float:1.54E-43)
            if (r5 == r6) goto L_0x003c
            r6 = 114(0x72, float:1.6E-43)
            if (r5 == r6) goto L_0x0039
            r6 = 116(0x74, float:1.63E-43)
            if (r5 == r6) goto L_0x0036
            r6 = -1
            goto L_0x0042
        L_0x0036:
            r6 = 9
            goto L_0x0041
        L_0x0039:
            r6 = 13
            goto L_0x0041
        L_0x003c:
            r6 = 10
            goto L_0x0041
        L_0x003f:
            r6 = 12
        L_0x0041:
            r4 = r7
        L_0x0042:
            if (r6 != r8) goto L_0x0082
            r6 = 117(0x75, float:1.64E-43)
            if (r5 != r6) goto L_0x007f
            int r5 = r2 + 2
            r6 = r5
        L_0x004b:
            int r8 = r2 + 6
            if (r6 >= r8) goto L_0x006f
            if (r6 >= r1) goto L_0x006f
            char r8 = r11.charAt(r6)
            r10 = 48
            if (r8 < r10) goto L_0x005d
            r10 = 57
            if (r8 <= r10) goto L_0x006c
        L_0x005d:
            r10 = 65
            if (r8 < r10) goto L_0x0065
            r10 = 70
            if (r8 <= r10) goto L_0x006c
        L_0x0065:
            r10 = 97
            if (r8 < r10) goto L_0x006f
            if (r8 <= r9) goto L_0x006c
            goto L_0x006f
        L_0x006c:
            int r6 = r6 + 1
            goto L_0x004b
        L_0x006f:
            int r8 = r6 - r5
            r9 = 4
            if (r8 >= r9) goto L_0x0076
            r2 = r7
            goto L_0x00a8
        L_0x0076:
            r4 = 16
            int r8 = parseIntFromReference((java.lang.String) r11, (int) r5, (int) r6, (int) r4)
            int r4 = r6 + -1
            goto L_0x0083
        L_0x007f:
            r8 = r5
            r4 = r7
            goto L_0x0083
        L_0x0082:
            r8 = r6
        L_0x0083:
            if (r0 != 0) goto L_0x008c
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r5 = r1 + 5
            r0.<init>(r5)
        L_0x008c:
            int r5 = r2 - r3
            if (r5 <= 0) goto L_0x0093
            r0.append(r11, r3, r2)
        L_0x0093:
            int r2 = r4 + 1
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r8 <= r3) goto L_0x00a2
            char[] r3 = java.lang.Character.toChars(r8)
            r0.append(r3)
            goto L_0x00a6
        L_0x00a2:
            char r3 = (char) r8
            r0.append(r3)
        L_0x00a6:
            r3 = r2
            r2 = r4
        L_0x00a8:
            int r2 = r2 + 1
            goto L_0x000b
        L_0x00ac:
            if (r0 != 0) goto L_0x00af
            return r11
        L_0x00af:
            int r2 = r1 - r3
            if (r2 <= 0) goto L_0x00b6
            r0.append(r11, r3, r1)
        L_0x00b6:
            java.lang.String r11 = r0.toString()
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.properties.PropertiesUnescapeUtil.unescape(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void unescape(java.io.Reader r10, java.io.Writer r11) throws java.io.IOException {
        /*
            if (r10 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 4
            char[] r1 = new char[r0]
            int r2 = r10.read()
        L_0x000a:
            if (r2 < 0) goto L_0x00c7
            int r3 = r10.read()
            r4 = 92
            if (r2 != r4) goto L_0x00c1
            if (r3 >= 0) goto L_0x0018
            goto L_0x00c1
        L_0x0018:
            r5 = -1
            if (r2 != r4) goto L_0x00ac
            r6 = 102(0x66, float:1.43E-43)
            if (r3 == r4) goto L_0x004b
            if (r3 == r6) goto L_0x0044
            r4 = 110(0x6e, float:1.54E-43)
            if (r3 == r4) goto L_0x003d
            r4 = 114(0x72, float:1.6E-43)
            if (r3 == r4) goto L_0x0036
            r4 = 116(0x74, float:1.63E-43)
            if (r3 == r4) goto L_0x002f
            r4 = -1
            goto L_0x0052
        L_0x002f:
            r4 = 9
            int r2 = r10.read()
            goto L_0x004f
        L_0x0036:
            r4 = 13
            int r2 = r10.read()
            goto L_0x004f
        L_0x003d:
            r4 = 10
            int r2 = r10.read()
            goto L_0x004f
        L_0x0044:
            r4 = 12
            int r2 = r10.read()
            goto L_0x004f
        L_0x004b:
            int r2 = r10.read()
        L_0x004f:
            r9 = r3
            r3 = r2
            r2 = r9
        L_0x0052:
            if (r4 != r5) goto L_0x00a9
            r4 = 117(0x75, float:1.64E-43)
            if (r3 != r4) goto L_0x00a3
            int r4 = r10.read()
            r5 = 0
            r7 = 0
        L_0x005e:
            if (r4 < 0) goto L_0x0083
            if (r7 >= r0) goto L_0x0083
            r8 = 48
            if (r4 < r8) goto L_0x006a
            r8 = 57
            if (r4 <= r8) goto L_0x0079
        L_0x006a:
            r8 = 65
            if (r4 < r8) goto L_0x0072
            r8 = 70
            if (r4 <= r8) goto L_0x0079
        L_0x0072:
            r8 = 97
            if (r4 < r8) goto L_0x0083
            if (r4 <= r6) goto L_0x0079
            goto L_0x0083
        L_0x0079:
            char r4 = (char) r4
            r1[r7] = r4
            int r4 = r10.read()
            int r7 = r7 + 1
            goto L_0x005e
        L_0x0083:
            if (r7 >= r0) goto L_0x0098
            r11.write(r2)
            r11.write(r3)
        L_0x008b:
            if (r5 >= r7) goto L_0x0095
            char r2 = r1[r5]
            r11.write(r2)
            int r5 = r5 + 1
            goto L_0x008b
        L_0x0095:
            r2 = r4
            goto L_0x000a
        L_0x0098:
            r2 = 3
            char r2 = r1[r2]
            r2 = 16
            int r5 = parseIntFromReference((char[]) r1, (int) r5, (int) r0, (int) r2)
            r2 = r4
            goto L_0x00ad
        L_0x00a3:
            int r2 = r10.read()
            r5 = r3
            goto L_0x00ad
        L_0x00a9:
            r2 = r3
            r5 = r4
            goto L_0x00ad
        L_0x00ac:
            r2 = r3
        L_0x00ad:
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r5 <= r3) goto L_0x00bb
            char[] r3 = java.lang.Character.toChars(r5)
            r11.write(r3)
            goto L_0x000a
        L_0x00bb:
            char r3 = (char) r5
            r11.write(r3)
            goto L_0x000a
        L_0x00c1:
            r11.write(r2)
            r2 = r3
            goto L_0x000a
        L_0x00c7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.properties.PropertiesUnescapeUtil.unescape(java.io.Reader, java.io.Writer):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void unescape(char[] r8, int r9, int r10, java.io.Writer r11) throws java.io.IOException {
        /*
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            int r10 = r10 + r9
            r0 = r9
            r1 = r0
        L_0x0006:
            if (r9 >= r10) goto L_0x0097
            char r2 = r8[r9]
            r3 = 92
            if (r2 != r3) goto L_0x0093
            int r4 = r9 + 1
            if (r4 < r10) goto L_0x0014
            goto L_0x0093
        L_0x0014:
            r5 = -1
            if (r2 != r3) goto L_0x0078
            char r2 = r8[r4]
            r6 = 102(0x66, float:1.43E-43)
            if (r2 == r3) goto L_0x0038
            if (r2 == r6) goto L_0x0036
            r3 = 110(0x6e, float:1.54E-43)
            if (r2 == r3) goto L_0x0033
            r3 = 114(0x72, float:1.6E-43)
            if (r2 == r3) goto L_0x0030
            r3 = 116(0x74, float:1.63E-43)
            if (r2 == r3) goto L_0x002d
            r3 = -1
            goto L_0x0039
        L_0x002d:
            r3 = 9
            goto L_0x0038
        L_0x0030:
            r3 = 13
            goto L_0x0038
        L_0x0033:
            r3 = 10
            goto L_0x0038
        L_0x0036:
            r3 = 12
        L_0x0038:
            r1 = r4
        L_0x0039:
            if (r3 != r5) goto L_0x0077
            r3 = 117(0x75, float:1.64E-43)
            if (r2 != r3) goto L_0x0074
            int r2 = r9 + 2
            r3 = r2
        L_0x0042:
            int r5 = r9 + 6
            if (r3 >= r5) goto L_0x0064
            if (r3 >= r10) goto L_0x0064
            char r5 = r8[r3]
            r7 = 48
            if (r5 < r7) goto L_0x0052
            r7 = 57
            if (r5 <= r7) goto L_0x0061
        L_0x0052:
            r7 = 65
            if (r5 < r7) goto L_0x005a
            r7 = 70
            if (r5 <= r7) goto L_0x0061
        L_0x005a:
            r7 = 97
            if (r5 < r7) goto L_0x0064
            if (r5 <= r6) goto L_0x0061
            goto L_0x0064
        L_0x0061:
            int r3 = r3 + 1
            goto L_0x0042
        L_0x0064:
            int r5 = r3 - r2
            r6 = 4
            if (r5 >= r6) goto L_0x006b
            r9 = r4
            goto L_0x0093
        L_0x006b:
            r1 = 16
            int r5 = parseIntFromReference((char[]) r8, (int) r2, (int) r3, (int) r1)
            int r1 = r3 + -1
            goto L_0x0078
        L_0x0074:
            r5 = r2
            r1 = r4
            goto L_0x0078
        L_0x0077:
            r5 = r3
        L_0x0078:
            int r9 = r9 - r0
            if (r9 <= 0) goto L_0x007e
            r11.write(r8, r0, r9)
        L_0x007e:
            int r9 = r1 + 1
            r0 = 65535(0xffff, float:9.1834E-41)
            if (r5 <= r0) goto L_0x008d
            char[] r0 = java.lang.Character.toChars(r5)
            r11.write(r0)
            goto L_0x0091
        L_0x008d:
            char r0 = (char) r5
            r11.write(r0)
        L_0x0091:
            r0 = r9
            r9 = r1
        L_0x0093:
            int r9 = r9 + 1
            goto L_0x0006
        L_0x0097:
            int r10 = r10 - r0
            if (r10 <= 0) goto L_0x009d
            r11.write(r8, r0, r10)
        L_0x009d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.unbescape.properties.PropertiesUnescapeUtil.unescape(char[], int, int, java.io.Writer):void");
    }
}
