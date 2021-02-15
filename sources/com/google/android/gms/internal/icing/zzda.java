package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzda extends zzdd {
    private final int zzgm;
    private final int zzgn;

    zzda(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzgm = i;
        this.zzgn = i2;
    }

    public final byte zzk(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzgp[this.zzgm + i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    /* access modifiers changed from: package-private */
    public final byte zzl(int i) {
        return this.zzgp[this.zzgm + i];
    }

    public final int size() {
        return this.zzgn;
    }

    /* access modifiers changed from: protected */
    public final int zzaq() {
        return this.zzgm;
    }
}
