package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzdh extends zzdf {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzgt;
    private int zzgu;
    private int zzgv;
    private int zzgw;

    private zzdh(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzgw = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzgv = i;
        this.zzgt = z;
    }

    public final int zzn(int i) throws zzeh {
        if (i >= 0) {
            int zzat = i + zzat();
            int i2 = this.zzgw;
            if (zzat <= i2) {
                this.zzgw = zzat;
                int i3 = this.limit + this.zzgu;
                this.limit = i3;
                int i4 = i3 - this.zzgv;
                if (i4 > zzat) {
                    int i5 = i4 - zzat;
                    this.zzgu = i5;
                    this.limit = i3 - i5;
                } else {
                    this.zzgu = 0;
                }
                return i2;
            }
            throw new zzeh("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new zzeh("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public final int zzat() {
        return this.pos - this.zzgv;
    }
}
