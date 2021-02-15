package com.google.android.gms.internal.icing;

import java.lang.reflect.Type;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public enum zzdt {
    DOUBLE(0, zzdv.SCALAR, zzej.DOUBLE),
    FLOAT(1, zzdv.SCALAR, zzej.FLOAT),
    INT64(2, zzdv.SCALAR, zzej.LONG),
    UINT64(3, zzdv.SCALAR, zzej.LONG),
    INT32(4, zzdv.SCALAR, zzej.INT),
    FIXED64(5, zzdv.SCALAR, zzej.LONG),
    FIXED32(6, zzdv.SCALAR, zzej.INT),
    BOOL(7, zzdv.SCALAR, zzej.BOOLEAN),
    STRING(8, zzdv.SCALAR, zzej.STRING),
    MESSAGE(9, zzdv.SCALAR, zzej.MESSAGE),
    BYTES(10, zzdv.SCALAR, zzej.BYTE_STRING),
    UINT32(11, zzdv.SCALAR, zzej.INT),
    ENUM(12, zzdv.SCALAR, zzej.ENUM),
    SFIXED32(13, zzdv.SCALAR, zzej.INT),
    SFIXED64(14, zzdv.SCALAR, zzej.LONG),
    SINT32(15, zzdv.SCALAR, zzej.INT),
    SINT64(16, zzdv.SCALAR, zzej.LONG),
    GROUP(17, zzdv.SCALAR, zzej.MESSAGE),
    DOUBLE_LIST(18, zzdv.VECTOR, zzej.DOUBLE),
    FLOAT_LIST(19, zzdv.VECTOR, zzej.FLOAT),
    INT64_LIST(20, zzdv.VECTOR, zzej.LONG),
    UINT64_LIST(21, zzdv.VECTOR, zzej.LONG),
    INT32_LIST(22, zzdv.VECTOR, zzej.INT),
    FIXED64_LIST(23, zzdv.VECTOR, zzej.LONG),
    FIXED32_LIST(24, zzdv.VECTOR, zzej.INT),
    BOOL_LIST(25, zzdv.VECTOR, zzej.BOOLEAN),
    STRING_LIST(26, zzdv.VECTOR, zzej.STRING),
    MESSAGE_LIST(27, zzdv.VECTOR, zzej.MESSAGE),
    BYTES_LIST(28, zzdv.VECTOR, zzej.BYTE_STRING),
    UINT32_LIST(29, zzdv.VECTOR, zzej.INT),
    ENUM_LIST(30, zzdv.VECTOR, zzej.ENUM),
    SFIXED32_LIST(31, zzdv.VECTOR, zzej.INT),
    SFIXED64_LIST(32, zzdv.VECTOR, zzej.LONG),
    SINT32_LIST(33, zzdv.VECTOR, zzej.INT),
    SINT64_LIST(34, zzdv.VECTOR, zzej.LONG),
    DOUBLE_LIST_PACKED(35, zzdv.PACKED_VECTOR, zzej.DOUBLE),
    FLOAT_LIST_PACKED(36, zzdv.PACKED_VECTOR, zzej.FLOAT),
    INT64_LIST_PACKED(37, zzdv.PACKED_VECTOR, zzej.LONG),
    UINT64_LIST_PACKED(38, zzdv.PACKED_VECTOR, zzej.LONG),
    INT32_LIST_PACKED(39, zzdv.PACKED_VECTOR, zzej.INT),
    FIXED64_LIST_PACKED(40, zzdv.PACKED_VECTOR, zzej.LONG),
    FIXED32_LIST_PACKED(41, zzdv.PACKED_VECTOR, zzej.INT),
    BOOL_LIST_PACKED(42, zzdv.PACKED_VECTOR, zzej.BOOLEAN),
    UINT32_LIST_PACKED(43, zzdv.PACKED_VECTOR, zzej.INT),
    ENUM_LIST_PACKED(44, zzdv.PACKED_VECTOR, zzej.ENUM),
    SFIXED32_LIST_PACKED(45, zzdv.PACKED_VECTOR, zzej.INT),
    SFIXED64_LIST_PACKED(46, zzdv.PACKED_VECTOR, zzej.LONG),
    SINT32_LIST_PACKED(47, zzdv.PACKED_VECTOR, zzej.INT),
    SINT64_LIST_PACKED(48, zzdv.PACKED_VECTOR, zzej.LONG),
    GROUP_LIST(49, zzdv.VECTOR, zzej.MESSAGE),
    MAP(50, zzdv.MAP, zzej.VOID);
    
    private static final zzdt[] zzjr = null;
    private static final Type[] zzjs = null;
    private final int id;
    private final zzej zzjn;
    private final zzdv zzjo;
    private final Class<?> zzjp;
    private final boolean zzjq;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r5 = com.google.android.gms.internal.icing.zzdw.zzkb[r6.ordinal()];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzdt(int r4, com.google.android.gms.internal.icing.zzdv r5, com.google.android.gms.internal.icing.zzej r6) {
        /*
            r1 = this;
            r1.<init>(r2, r3)
            r1.id = r4
            r1.zzjo = r5
            r1.zzjn = r6
            int[] r2 = com.google.android.gms.internal.icing.zzdw.zzka
            int r3 = r5.ordinal()
            r2 = r2[r3]
            r3 = 2
            r4 = 1
            if (r2 == r4) goto L_0x0022
            if (r2 == r3) goto L_0x001b
            r2 = 0
            r1.zzjp = r2
            goto L_0x0028
        L_0x001b:
            java.lang.Class r2 = r6.zzcb()
            r1.zzjp = r2
            goto L_0x0028
        L_0x0022:
            java.lang.Class r2 = r6.zzcb()
            r1.zzjp = r2
        L_0x0028:
            r2 = 0
            com.google.android.gms.internal.icing.zzdv r0 = com.google.android.gms.internal.icing.zzdv.SCALAR
            if (r5 != r0) goto L_0x003d
            int[] r5 = com.google.android.gms.internal.icing.zzdw.zzkb
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
            r1.zzjq = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzdt.<init>(java.lang.String, int, int, com.google.android.gms.internal.icing.zzdv, com.google.android.gms.internal.icing.zzej):void");
    }

    public final int id() {
        return this.id;
    }

    static {
        int i;
        zzjs = new Type[0];
        zzdt[] values = values();
        zzjr = new zzdt[values.length];
        for (zzdt zzdt : values) {
            zzjr[zzdt.id] = zzdt;
        }
    }
}
