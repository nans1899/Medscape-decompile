package com.google.android.gms.internal.vision;

import java.lang.reflect.Type;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public enum zzhy {
    DOUBLE(0, zzia.SCALAR, zzip.DOUBLE),
    FLOAT(1, zzia.SCALAR, zzip.FLOAT),
    INT64(2, zzia.SCALAR, zzip.LONG),
    UINT64(3, zzia.SCALAR, zzip.LONG),
    INT32(4, zzia.SCALAR, zzip.INT),
    FIXED64(5, zzia.SCALAR, zzip.LONG),
    FIXED32(6, zzia.SCALAR, zzip.INT),
    BOOL(7, zzia.SCALAR, zzip.BOOLEAN),
    STRING(8, zzia.SCALAR, zzip.STRING),
    MESSAGE(9, zzia.SCALAR, zzip.MESSAGE),
    BYTES(10, zzia.SCALAR, zzip.BYTE_STRING),
    UINT32(11, zzia.SCALAR, zzip.INT),
    ENUM(12, zzia.SCALAR, zzip.ENUM),
    SFIXED32(13, zzia.SCALAR, zzip.INT),
    SFIXED64(14, zzia.SCALAR, zzip.LONG),
    SINT32(15, zzia.SCALAR, zzip.INT),
    SINT64(16, zzia.SCALAR, zzip.LONG),
    GROUP(17, zzia.SCALAR, zzip.MESSAGE),
    DOUBLE_LIST(18, zzia.VECTOR, zzip.DOUBLE),
    FLOAT_LIST(19, zzia.VECTOR, zzip.FLOAT),
    INT64_LIST(20, zzia.VECTOR, zzip.LONG),
    UINT64_LIST(21, zzia.VECTOR, zzip.LONG),
    INT32_LIST(22, zzia.VECTOR, zzip.INT),
    FIXED64_LIST(23, zzia.VECTOR, zzip.LONG),
    FIXED32_LIST(24, zzia.VECTOR, zzip.INT),
    BOOL_LIST(25, zzia.VECTOR, zzip.BOOLEAN),
    STRING_LIST(26, zzia.VECTOR, zzip.STRING),
    MESSAGE_LIST(27, zzia.VECTOR, zzip.MESSAGE),
    BYTES_LIST(28, zzia.VECTOR, zzip.BYTE_STRING),
    UINT32_LIST(29, zzia.VECTOR, zzip.INT),
    ENUM_LIST(30, zzia.VECTOR, zzip.ENUM),
    SFIXED32_LIST(31, zzia.VECTOR, zzip.INT),
    SFIXED64_LIST(32, zzia.VECTOR, zzip.LONG),
    SINT32_LIST(33, zzia.VECTOR, zzip.INT),
    SINT64_LIST(34, zzia.VECTOR, zzip.LONG),
    DOUBLE_LIST_PACKED(35, zzia.PACKED_VECTOR, zzip.DOUBLE),
    FLOAT_LIST_PACKED(36, zzia.PACKED_VECTOR, zzip.FLOAT),
    INT64_LIST_PACKED(37, zzia.PACKED_VECTOR, zzip.LONG),
    UINT64_LIST_PACKED(38, zzia.PACKED_VECTOR, zzip.LONG),
    INT32_LIST_PACKED(39, zzia.PACKED_VECTOR, zzip.INT),
    FIXED64_LIST_PACKED(40, zzia.PACKED_VECTOR, zzip.LONG),
    FIXED32_LIST_PACKED(41, zzia.PACKED_VECTOR, zzip.INT),
    BOOL_LIST_PACKED(42, zzia.PACKED_VECTOR, zzip.BOOLEAN),
    UINT32_LIST_PACKED(43, zzia.PACKED_VECTOR, zzip.INT),
    ENUM_LIST_PACKED(44, zzia.PACKED_VECTOR, zzip.ENUM),
    SFIXED32_LIST_PACKED(45, zzia.PACKED_VECTOR, zzip.INT),
    SFIXED64_LIST_PACKED(46, zzia.PACKED_VECTOR, zzip.LONG),
    SINT32_LIST_PACKED(47, zzia.PACKED_VECTOR, zzip.INT),
    SINT64_LIST_PACKED(48, zzia.PACKED_VECTOR, zzip.LONG),
    GROUP_LIST(49, zzia.VECTOR, zzip.MESSAGE),
    MAP(50, zzia.MAP, zzip.VOID);
    
    private static final zzhy[] zzxj = null;
    private static final Type[] zzxk = null;
    private final int id;
    private final zzip zzxf;
    private final zzia zzxg;
    private final Class<?> zzxh;
    private final boolean zzxi;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r5 = com.google.android.gms.internal.vision.zzhx.zzvf[r6.ordinal()];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzhy(int r4, com.google.android.gms.internal.vision.zzia r5, com.google.android.gms.internal.vision.zzip r6) {
        /*
            r1 = this;
            r1.<init>(r2, r3)
            r1.id = r4
            r1.zzxg = r5
            r1.zzxf = r6
            int[] r2 = com.google.android.gms.internal.vision.zzhx.zzve
            int r3 = r5.ordinal()
            r2 = r2[r3]
            r3 = 2
            r4 = 1
            if (r2 == r4) goto L_0x0022
            if (r2 == r3) goto L_0x001b
            r2 = 0
            r1.zzxh = r2
            goto L_0x0028
        L_0x001b:
            java.lang.Class r2 = r6.zzhq()
            r1.zzxh = r2
            goto L_0x0028
        L_0x0022:
            java.lang.Class r2 = r6.zzhq()
            r1.zzxh = r2
        L_0x0028:
            r2 = 0
            com.google.android.gms.internal.vision.zzia r0 = com.google.android.gms.internal.vision.zzia.SCALAR
            if (r5 != r0) goto L_0x003d
            int[] r5 = com.google.android.gms.internal.vision.zzhx.zzvf
            int r6 = r6.ordinal()
            r5 = r5[r6]
            if (r5 == r4) goto L_0x003d
            if (r5 == r3) goto L_0x003d
            r3 = 3
            if (r5 == r3) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r4 = 0
        L_0x003e:
            r1.zzxi = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhy.<init>(java.lang.String, int, int, com.google.android.gms.internal.vision.zzia, com.google.android.gms.internal.vision.zzip):void");
    }

    public final int id() {
        return this.id;
    }

    static {
        int i;
        zzxk = new Type[0];
        zzhy[] values = values();
        zzxj = new zzhy[values.length];
        for (zzhy zzhy : values) {
            zzxj[zzhy.id] = zzhy;
        }
    }
}
