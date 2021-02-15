package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public enum zzlk {
    DOUBLE(zzlr.DOUBLE, 1),
    FLOAT(zzlr.FLOAT, 5),
    INT64(zzlr.LONG, 0),
    UINT64(zzlr.LONG, 0),
    INT32(zzlr.INT, 0),
    FIXED64(zzlr.LONG, 1),
    FIXED32(zzlr.INT, 5),
    BOOL(zzlr.BOOLEAN, 0),
    STRING(zzlr.STRING, 2),
    GROUP(zzlr.MESSAGE, 3),
    MESSAGE(zzlr.MESSAGE, 2),
    BYTES(zzlr.BYTE_STRING, 2),
    UINT32(zzlr.INT, 0),
    ENUM(zzlr.ENUM, 0),
    SFIXED32(zzlr.INT, 5),
    SFIXED64(zzlr.LONG, 1),
    SINT32(zzlr.INT, 0),
    SINT64(zzlr.LONG, 0);
    
    private final zzlr zzadx;
    private final int zzady;

    private zzlk(zzlr zzlr, int i) {
        this.zzadx = zzlr;
        this.zzady = i;
    }

    public final zzlr zzjk() {
        return this.zzadx;
    }

    public final int zzjl() {
        return this.zzady;
    }
}
