package com.google.android.gms.internal.ads;

import org.jetbrains.anko.DimensionsKt;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzkz implements zzky {
    private final zzpk zzawi;
    private final int zzaxc = this.zzawi.zzjf();
    private final int zzaxd = (this.zzawi.zzjf() & 255);
    private int zzaxe;
    private int zzaxf;

    public zzkz(zzkt zzkt) {
        zzpk zzpk = zzkt.zzawi;
        this.zzawi = zzpk;
        zzpk.zzbo(12);
    }

    public final boolean zzha() {
        return false;
    }

    public final int zzgy() {
        return this.zzaxc;
    }

    public final int zzgz() {
        int i = this.zzaxd;
        if (i == 8) {
            return this.zzawi.readUnsignedByte();
        }
        if (i == 16) {
            return this.zzawi.readUnsignedShort();
        }
        int i2 = this.zzaxe;
        this.zzaxe = i2 + 1;
        if (i2 % 2 != 0) {
            return this.zzaxf & 15;
        }
        int readUnsignedByte = this.zzawi.readUnsignedByte();
        this.zzaxf = readUnsignedByte;
        return (readUnsignedByte & DimensionsKt.HDPI) >> 4;
    }
}
