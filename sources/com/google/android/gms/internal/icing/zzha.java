package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public enum zzha {
    DOUBLE(zzhh.DOUBLE, 1),
    FLOAT(zzhh.FLOAT, 5),
    INT64(zzhh.LONG, 0),
    UINT64(zzhh.LONG, 0),
    INT32(zzhh.INT, 0),
    FIXED64(zzhh.LONG, 1),
    FIXED32(zzhh.INT, 5),
    BOOL(zzhh.BOOLEAN, 0),
    STRING(zzhh.STRING, 2),
    GROUP(zzhh.MESSAGE, 3),
    MESSAGE(zzhh.MESSAGE, 2),
    BYTES(zzhh.BYTE_STRING, 2),
    UINT32(zzhh.INT, 0),
    ENUM(zzhh.ENUM, 0),
    SFIXED32(zzhh.INT, 5),
    SFIXED64(zzhh.LONG, 1),
    SINT32(zzhh.INT, 0),
    SINT64(zzhh.LONG, 0);
    
    private final zzhh zzqa;
    private final int zzqb;

    private zzha(zzhh zzhh, int i) {
        this.zzqa = zzhh;
        this.zzqb = i;
    }

    public final zzhh zzdt() {
        return this.zzqa;
    }

    public final int zzdu() {
        return this.zzqb;
    }
}
