package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public enum zzhu {
    DOUBLE(0, zzhw.SCALAR, zzij.DOUBLE),
    FLOAT(1, zzhw.SCALAR, zzij.FLOAT),
    INT64(2, zzhw.SCALAR, zzij.LONG),
    UINT64(3, zzhw.SCALAR, zzij.LONG),
    INT32(4, zzhw.SCALAR, zzij.INT),
    FIXED64(5, zzhw.SCALAR, zzij.LONG),
    FIXED32(6, zzhw.SCALAR, zzij.INT),
    BOOL(7, zzhw.SCALAR, zzij.BOOLEAN),
    STRING(8, zzhw.SCALAR, zzij.STRING),
    MESSAGE(9, zzhw.SCALAR, zzij.MESSAGE),
    BYTES(10, zzhw.SCALAR, zzij.BYTE_STRING),
    UINT32(11, zzhw.SCALAR, zzij.INT),
    ENUM(12, zzhw.SCALAR, zzij.ENUM),
    SFIXED32(13, zzhw.SCALAR, zzij.INT),
    SFIXED64(14, zzhw.SCALAR, zzij.LONG),
    SINT32(15, zzhw.SCALAR, zzij.INT),
    SINT64(16, zzhw.SCALAR, zzij.LONG),
    GROUP(17, zzhw.SCALAR, zzij.MESSAGE),
    DOUBLE_LIST(18, zzhw.VECTOR, zzij.DOUBLE),
    FLOAT_LIST(19, zzhw.VECTOR, zzij.FLOAT),
    INT64_LIST(20, zzhw.VECTOR, zzij.LONG),
    UINT64_LIST(21, zzhw.VECTOR, zzij.LONG),
    INT32_LIST(22, zzhw.VECTOR, zzij.INT),
    FIXED64_LIST(23, zzhw.VECTOR, zzij.LONG),
    FIXED32_LIST(24, zzhw.VECTOR, zzij.INT),
    BOOL_LIST(25, zzhw.VECTOR, zzij.BOOLEAN),
    STRING_LIST(26, zzhw.VECTOR, zzij.STRING),
    MESSAGE_LIST(27, zzhw.VECTOR, zzij.MESSAGE),
    BYTES_LIST(28, zzhw.VECTOR, zzij.BYTE_STRING),
    UINT32_LIST(29, zzhw.VECTOR, zzij.INT),
    ENUM_LIST(30, zzhw.VECTOR, zzij.ENUM),
    SFIXED32_LIST(31, zzhw.VECTOR, zzij.INT),
    SFIXED64_LIST(32, zzhw.VECTOR, zzij.LONG),
    SINT32_LIST(33, zzhw.VECTOR, zzij.INT),
    SINT64_LIST(34, zzhw.VECTOR, zzij.LONG),
    DOUBLE_LIST_PACKED(35, zzhw.PACKED_VECTOR, zzij.DOUBLE),
    FLOAT_LIST_PACKED(36, zzhw.PACKED_VECTOR, zzij.FLOAT),
    INT64_LIST_PACKED(37, zzhw.PACKED_VECTOR, zzij.LONG),
    UINT64_LIST_PACKED(38, zzhw.PACKED_VECTOR, zzij.LONG),
    INT32_LIST_PACKED(39, zzhw.PACKED_VECTOR, zzij.INT),
    FIXED64_LIST_PACKED(40, zzhw.PACKED_VECTOR, zzij.LONG),
    FIXED32_LIST_PACKED(41, zzhw.PACKED_VECTOR, zzij.INT),
    BOOL_LIST_PACKED(42, zzhw.PACKED_VECTOR, zzij.BOOLEAN),
    UINT32_LIST_PACKED(43, zzhw.PACKED_VECTOR, zzij.INT),
    ENUM_LIST_PACKED(44, zzhw.PACKED_VECTOR, zzij.ENUM),
    SFIXED32_LIST_PACKED(45, zzhw.PACKED_VECTOR, zzij.INT),
    SFIXED64_LIST_PACKED(46, zzhw.PACKED_VECTOR, zzij.LONG),
    SINT32_LIST_PACKED(47, zzhw.PACKED_VECTOR, zzij.INT),
    SINT64_LIST_PACKED(48, zzhw.PACKED_VECTOR, zzij.LONG),
    GROUP_LIST(49, zzhw.VECTOR, zzij.MESSAGE),
    MAP(50, zzhw.MAP, zzij.VOID);
    
    private static final zzhu[] zzbe = null;
    private static final Type[] zzbf = null;
    private final zzij zzaz;
    private final int zzba;
    private final zzhw zzbb;
    private final Class<?> zzbc;
    private final boolean zzbd;

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r5 = com.google.android.gms.internal.measurement.zzht.zzb[r6.ordinal()];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzhu(int r4, com.google.android.gms.internal.measurement.zzhw r5, com.google.android.gms.internal.measurement.zzij r6) {
        /*
            r1 = this;
            r1.<init>(r2, r3)
            r1.zzba = r4
            r1.zzbb = r5
            r1.zzaz = r6
            int[] r2 = com.google.android.gms.internal.measurement.zzht.zza
            int r3 = r5.ordinal()
            r2 = r2[r3]
            r3 = 2
            r4 = 1
            if (r2 == r4) goto L_0x0022
            if (r2 == r3) goto L_0x001b
            r2 = 0
            r1.zzbc = r2
            goto L_0x0028
        L_0x001b:
            java.lang.Class r2 = r6.zza()
            r1.zzbc = r2
            goto L_0x0028
        L_0x0022:
            java.lang.Class r2 = r6.zza()
            r1.zzbc = r2
        L_0x0028:
            r2 = 0
            com.google.android.gms.internal.measurement.zzhw r0 = com.google.android.gms.internal.measurement.zzhw.SCALAR
            if (r5 != r0) goto L_0x003d
            int[] r5 = com.google.android.gms.internal.measurement.zzht.zzb
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
            r1.zzbd = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhu.<init>(java.lang.String, int, int, com.google.android.gms.internal.measurement.zzhw, com.google.android.gms.internal.measurement.zzij):void");
    }

    public final int zza() {
        return this.zzba;
    }

    static {
        int i;
        zzbf = new Type[0];
        zzhu[] values = values();
        zzbe = new zzhu[values.length];
        for (zzhu zzhu : values) {
            zzbe[zzhu.zzba] = zzhu;
        }
    }
}
