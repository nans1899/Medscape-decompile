package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzpj {
    private byte[] data;
    private int zzbjy;
    private int zzbjz = 0;
    private int zzbka;

    public zzpj(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.zzbjy = i;
        this.zzbka = i2;
        zziz();
    }

    public final void zzbl(int i) {
        int i2 = this.zzbjy;
        int i3 = (i / 8) + i2;
        this.zzbjy = i3;
        int i4 = this.zzbjz + (i % 8);
        this.zzbjz = i4;
        if (i4 > 7) {
            this.zzbjy = i3 + 1;
            this.zzbjz = i4 - 8;
        }
        while (true) {
            i2++;
            if (i2 > this.zzbjy) {
                zziz();
                return;
            } else if (zzbm(i2)) {
                this.zzbjy++;
                i2 += 2;
            }
        }
    }

    public final boolean zziv() {
        return zzbk(1) == 1;
    }

    public final int zzbk(int i) {
        byte b;
        byte b2;
        if (i == 0) {
            return 0;
        }
        int i2 = i / 8;
        byte b3 = 0;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = zzbm(this.zzbjy + 1) ? this.zzbjy + 2 : this.zzbjy + 1;
            int i5 = this.zzbjz;
            if (i5 != 0) {
                byte[] bArr = this.data;
                b2 = ((bArr[i4] & 255) >>> (8 - i5)) | ((bArr[this.zzbjy] & 255) << i5);
            } else {
                b2 = this.data[this.zzbjy];
            }
            i -= 8;
            b3 |= (255 & b2) << i;
            this.zzbjy = i4;
        }
        if (i > 0) {
            int i6 = this.zzbjz + i;
            byte b4 = (byte) (255 >> (8 - i));
            int i7 = zzbm(this.zzbjy + 1) ? this.zzbjy + 2 : this.zzbjy + 1;
            if (i6 > 8) {
                byte[] bArr2 = this.data;
                b = (b4 & (((255 & bArr2[i7]) >> (16 - i6)) | ((bArr2[this.zzbjy] & 255) << (i6 - 8)))) | b3;
                this.zzbjy = i7;
            } else {
                b = (b4 & ((255 & this.data[this.zzbjy]) >> (8 - i6))) | b3;
                if (i6 == 8) {
                    this.zzbjy = i7;
                }
            }
            b3 = b;
            this.zzbjz = i6 % 8;
        }
        zziz();
        return b3;
    }

    public final int zziw() {
        return zziy();
    }

    public final int zzix() {
        int zziy = zziy();
        return (zziy % 2 == 0 ? -1 : 1) * ((zziy + 1) / 2);
    }

    private final int zziy() {
        int i = 0;
        int i2 = 0;
        while (!zziv()) {
            i2++;
        }
        int i3 = (1 << i2) - 1;
        if (i2 > 0) {
            i = zzbk(i2);
        }
        return i3 + i;
    }

    private final boolean zzbm(int i) {
        if (2 > i || i >= this.zzbka) {
            return false;
        }
        byte[] bArr = this.data;
        return bArr[i] == 3 && bArr[i + -2] == 0 && bArr[i - 1] == 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r1 = r3.zzbjz;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000c, code lost:
        r2 = r3.zzbka;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zziz() {
        /*
            r3 = this;
            int r0 = r3.zzbjy
            if (r0 < 0) goto L_0x0016
            int r1 = r3.zzbjz
            if (r1 < 0) goto L_0x0016
            r2 = 8
            if (r1 >= r2) goto L_0x0016
            int r2 = r3.zzbka
            if (r0 < r2) goto L_0x0014
            if (r0 != r2) goto L_0x0016
            if (r1 != 0) goto L_0x0016
        L_0x0014:
            r0 = 1
            goto L_0x0017
        L_0x0016:
            r0 = 0
        L_0x0017:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpj.zziz():void");
    }
}
