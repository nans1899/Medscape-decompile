package mnetinternal;

public class ct {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(byte[] r8) {
        /*
            r0 = 64
            char[] r0 = new char[r0]
            r0 = {81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80} // fill-array
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x000f:
            int r5 = r8.length
            if (r3 >= r5) goto L_0x004f
            byte r5 = r8[r3]
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 << 16
            r6 = 16777215(0xffffff, float:2.3509886E-38)
            r5 = r5 & r6
            int r6 = r3 + 1
            int r7 = r8.length
            if (r6 >= r7) goto L_0x0029
            byte r6 = r8[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 << 8
            r5 = r5 | r6
            goto L_0x002b
        L_0x0029:
            int r4 = r4 + 1
        L_0x002b:
            int r6 = r3 + 2
            int r7 = r8.length
            if (r6 >= r7) goto L_0x0036
            byte r6 = r8[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5 = r5 | r6
            goto L_0x0038
        L_0x0036:
            int r4 = r4 + 1
        L_0x0038:
            r6 = 0
        L_0x0039:
            int r7 = 4 - r4
            if (r6 >= r7) goto L_0x004c
            r7 = 16515072(0xfc0000, float:2.3142545E-38)
            r7 = r7 & r5
            int r7 = r7 >> 18
            char r7 = r0[r7]
            r1.append(r7)
            int r5 = r5 << 6
            int r6 = r6 + 1
            goto L_0x0039
        L_0x004c:
            int r3 = r3 + 3
            goto L_0x000f
        L_0x004f:
            if (r2 >= r4) goto L_0x0059
            java.lang.String r8 = "="
            r1.append(r8)
            int r2 = r2 + 1
            goto L_0x004f
        L_0x0059:
            java.lang.String r8 = r1.toString()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: mnetinternal.ct.a(byte[]):java.lang.String");
    }
}
