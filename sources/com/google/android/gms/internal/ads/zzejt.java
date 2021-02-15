package com.google.android.gms.internal.ads;

import java.lang.reflect.Type;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public enum zzejt {
    DOUBLE(0, zzejv.SCALAR, zzekl.DOUBLE),
    FLOAT(1, zzejv.SCALAR, zzekl.FLOAT),
    INT64(2, zzejv.SCALAR, zzekl.LONG),
    UINT64(3, zzejv.SCALAR, zzekl.LONG),
    INT32(4, zzejv.SCALAR, zzekl.INT),
    FIXED64(5, zzejv.SCALAR, zzekl.LONG),
    FIXED32(6, zzejv.SCALAR, zzekl.INT),
    BOOL(7, zzejv.SCALAR, zzekl.BOOLEAN),
    STRING(8, zzejv.SCALAR, zzekl.STRING),
    MESSAGE(9, zzejv.SCALAR, zzekl.MESSAGE),
    BYTES(10, zzejv.SCALAR, zzekl.BYTE_STRING),
    UINT32(11, zzejv.SCALAR, zzekl.INT),
    ENUM(12, zzejv.SCALAR, zzekl.ENUM),
    SFIXED32(13, zzejv.SCALAR, zzekl.INT),
    SFIXED64(14, zzejv.SCALAR, zzekl.LONG),
    SINT32(15, zzejv.SCALAR, zzekl.INT),
    SINT64(16, zzejv.SCALAR, zzekl.LONG),
    GROUP(17, zzejv.SCALAR, zzekl.MESSAGE),
    DOUBLE_LIST(18, zzejv.VECTOR, zzekl.DOUBLE),
    FLOAT_LIST(19, zzejv.VECTOR, zzekl.FLOAT),
    INT64_LIST(20, zzejv.VECTOR, zzekl.LONG),
    UINT64_LIST(21, zzejv.VECTOR, zzekl.LONG),
    INT32_LIST(22, zzejv.VECTOR, zzekl.INT),
    FIXED64_LIST(23, zzejv.VECTOR, zzekl.LONG),
    FIXED32_LIST(24, zzejv.VECTOR, zzekl.INT),
    BOOL_LIST(25, zzejv.VECTOR, zzekl.BOOLEAN),
    STRING_LIST(26, zzejv.VECTOR, zzekl.STRING),
    MESSAGE_LIST(27, zzejv.VECTOR, zzekl.MESSAGE),
    BYTES_LIST(28, zzejv.VECTOR, zzekl.BYTE_STRING),
    UINT32_LIST(29, zzejv.VECTOR, zzekl.INT),
    ENUM_LIST(30, zzejv.VECTOR, zzekl.ENUM),
    SFIXED32_LIST(31, zzejv.VECTOR, zzekl.INT),
    SFIXED64_LIST(32, zzejv.VECTOR, zzekl.LONG),
    SINT32_LIST(33, zzejv.VECTOR, zzekl.INT),
    SINT64_LIST(34, zzejv.VECTOR, zzekl.LONG),
    DOUBLE_LIST_PACKED(35, zzejv.PACKED_VECTOR, zzekl.DOUBLE),
    FLOAT_LIST_PACKED(36, zzejv.PACKED_VECTOR, zzekl.FLOAT),
    INT64_LIST_PACKED(37, zzejv.PACKED_VECTOR, zzekl.LONG),
    UINT64_LIST_PACKED(38, zzejv.PACKED_VECTOR, zzekl.LONG),
    INT32_LIST_PACKED(39, zzejv.PACKED_VECTOR, zzekl.INT),
    FIXED64_LIST_PACKED(40, zzejv.PACKED_VECTOR, zzekl.LONG),
    FIXED32_LIST_PACKED(41, zzejv.PACKED_VECTOR, zzekl.INT),
    BOOL_LIST_PACKED(42, zzejv.PACKED_VECTOR, zzekl.BOOLEAN),
    UINT32_LIST_PACKED(43, zzejv.PACKED_VECTOR, zzekl.INT),
    ENUM_LIST_PACKED(44, zzejv.PACKED_VECTOR, zzekl.ENUM),
    SFIXED32_LIST_PACKED(45, zzejv.PACKED_VECTOR, zzekl.INT),
    SFIXED64_LIST_PACKED(46, zzejv.PACKED_VECTOR, zzekl.LONG),
    SINT32_LIST_PACKED(47, zzejv.PACKED_VECTOR, zzekl.INT),
    SINT64_LIST_PACKED(48, zzejv.PACKED_VECTOR, zzekl.LONG),
    GROUP_LIST(49, zzejv.VECTOR, zzekl.MESSAGE),
    MAP(50, zzejv.MAP, zzekl.VOID);
    
    private static final zzejt[] zziio = null;
    private static final Type[] zziip = null;
    private final int id;
    private final zzekl zziik;
    private final zzejv zziil;
    private final Class<?> zziim;
    private final boolean zziin;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r5 = com.google.android.gms.internal.ads.zzejw.zziiy[r6.ordinal()];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzejt(int r4, com.google.android.gms.internal.ads.zzejv r5, com.google.android.gms.internal.ads.zzekl r6) {
        /*
            r1 = this;
            r1.<init>(r2, r3)
            r1.id = r4
            r1.zziil = r5
            r1.zziik = r6
            int[] r2 = com.google.android.gms.internal.ads.zzejw.zziix
            int r3 = r5.ordinal()
            r2 = r2[r3]
            r3 = 2
            r4 = 1
            if (r2 == r4) goto L_0x0022
            if (r2 == r3) goto L_0x001b
            r2 = 0
            r1.zziim = r2
            goto L_0x0028
        L_0x001b:
            java.lang.Class r2 = r6.zzbhg()
            r1.zziim = r2
            goto L_0x0028
        L_0x0022:
            java.lang.Class r2 = r6.zzbhg()
            r1.zziim = r2
        L_0x0028:
            r2 = 0
            com.google.android.gms.internal.ads.zzejv r0 = com.google.android.gms.internal.ads.zzejv.SCALAR
            if (r5 != r0) goto L_0x003d
            int[] r5 = com.google.android.gms.internal.ads.zzejw.zziiy
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
            r1.zziin = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzejt.<init>(java.lang.String, int, int, com.google.android.gms.internal.ads.zzejv, com.google.android.gms.internal.ads.zzekl):void");
    }

    public final int id() {
        return this.id;
    }

    static {
        int i;
        zziip = new Type[0];
        zzejt[] values = values();
        zziio = new zzejt[values.length];
        for (zzejt zzejt : values) {
            zziio[zzejt.id] = zzejt;
        }
    }
}
