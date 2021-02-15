package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzph {
    private byte[] data;
    private int zzbjy;
    private int zzbjz;
    private int zzbka;

    public zzph() {
    }

    public zzph(byte[] bArr) {
        this(bArr, bArr.length);
    }

    private zzph(byte[] bArr, int i) {
        this.data = bArr;
        this.zzbka = i;
    }

    public final int zzbk(int i) {
        int i2;
        int i3;
        byte b;
        byte b2;
        boolean z = false;
        if (i == 0) {
            return 0;
        }
        int i4 = i / 8;
        byte b3 = 0;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = this.zzbjz;
            if (i6 != 0) {
                byte[] bArr = this.data;
                int i7 = this.zzbjy;
                b2 = ((bArr[i7 + 1] & 255) >>> (8 - i6)) | ((bArr[i7] & 255) << i6);
            } else {
                b2 = this.data[this.zzbjy];
            }
            i -= 8;
            b3 |= (255 & b2) << i;
            this.zzbjy++;
        }
        if (i > 0) {
            int i8 = this.zzbjz + i;
            byte b4 = (byte) (255 >> (8 - i));
            if (i8 > 8) {
                byte[] bArr2 = this.data;
                int i9 = this.zzbjy;
                b = (b4 & (((255 & bArr2[i9 + 1]) >> (16 - i8)) | ((bArr2[i9] & 255) << (i8 - 8)))) | b3;
                this.zzbjy = i9 + 1;
            } else {
                byte[] bArr3 = this.data;
                int i10 = this.zzbjy;
                b = (b4 & ((255 & bArr3[i10]) >> (8 - i8))) | b3;
                if (i8 == 8) {
                    this.zzbjy = i10 + 1;
                }
            }
            b3 = b;
            this.zzbjz = i8 % 8;
        }
        int i11 = this.zzbjy;
        if (i11 >= 0 && (i2 = this.zzbjz) >= 0 && i2 < 8 && (i11 < (i3 = this.zzbka) || (i11 == i3 && i2 == 0))) {
            z = true;
        }
        zzoz.checkState(z);
        return b3;
    }
}
