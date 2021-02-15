package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzgz extends zzhc {
    private final int zztx;
    private final int zzty;

    zzgz(byte[] bArr, int i, int i2) {
        super(bArr);
        zze(i, i + i2, bArr.length);
        this.zztx = i;
        this.zzty = i2;
    }

    public final byte zzau(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzua[this.zztx + i];
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
    public final byte zzav(int i) {
        return this.zzua[this.zztx + i];
    }

    public final int size() {
        return this.zzty;
    }

    /* access modifiers changed from: protected */
    public final int zzfo() {
        return this.zztx;
    }

    /* access modifiers changed from: protected */
    public final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzua, zzfo(), bArr, 0, i3);
    }
}
