package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public enum zzli {
    DOUBLE(zzll.DOUBLE, 1),
    FLOAT(zzll.FLOAT, 5),
    INT64(zzll.LONG, 0),
    UINT64(zzll.LONG, 0),
    INT32(zzll.INT, 0),
    FIXED64(zzll.LONG, 1),
    FIXED32(zzll.INT, 5),
    BOOL(zzll.BOOLEAN, 0),
    STRING(zzll.STRING, 2),
    GROUP(zzll.MESSAGE, 3),
    MESSAGE(zzll.MESSAGE, 2),
    BYTES(zzll.BYTE_STRING, 2),
    UINT32(zzll.INT, 0),
    ENUM(zzll.ENUM, 0),
    SFIXED32(zzll.INT, 5),
    SFIXED64(zzll.LONG, 1),
    SINT32(zzll.INT, 0),
    SINT64(zzll.LONG, 0);
    
    private final zzll zzs;
    private final int zzt;

    private zzli(zzll zzll, int i) {
        this.zzs = zzll;
        this.zzt = i;
    }

    public final zzll zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }
}
