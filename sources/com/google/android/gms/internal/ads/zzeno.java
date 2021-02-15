package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public enum zzeno {
    DOUBLE(zzenr.DOUBLE, 1),
    FLOAT(zzenr.FLOAT, 5),
    INT64(zzenr.LONG, 0),
    UINT64(zzenr.LONG, 0),
    INT32(zzenr.INT, 0),
    FIXED64(zzenr.LONG, 1),
    FIXED32(zzenr.INT, 5),
    BOOL(zzenr.BOOLEAN, 0),
    STRING(zzenr.STRING, 2),
    GROUP(zzenr.MESSAGE, 3),
    MESSAGE(zzenr.MESSAGE, 2),
    BYTES(zzenr.BYTE_STRING, 2),
    UINT32(zzenr.INT, 0),
    ENUM(zzenr.ENUM, 0),
    SFIXED32(zzenr.INT, 5),
    SFIXED64(zzenr.LONG, 1),
    SINT32(zzenr.INT, 0),
    SINT64(zzenr.LONG, 0);
    
    private final zzenr zzipo;
    private final int zzipp;

    private zzeno(zzenr zzenr, int i) {
        this.zzipo = zzenr;
        this.zzipp = i;
    }

    public final zzenr zzbje() {
        return this.zzipo;
    }

    public final int zzbjf() {
        return this.zzipp;
    }
}
