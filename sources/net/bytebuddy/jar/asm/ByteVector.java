package net.bytebuddy.jar.asm;

public class ByteVector {
    byte[] data;
    int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int i) {
        this.data = new byte[i];
    }

    ByteVector(byte[] bArr) {
        this.data = bArr;
        this.length = bArr.length;
    }

    public ByteVector putByte(int i) {
        int i2 = this.length;
        int i3 = i2 + 1;
        if (i3 > this.data.length) {
            enlarge(1);
        }
        this.data[i2] = (byte) i;
        this.length = i3;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ByteVector put11(int i, int i2) {
        int i3 = this.length;
        if (i3 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        bArr[i4] = (byte) i2;
        this.length = i4 + 1;
        return this;
    }

    public ByteVector putShort(int i) {
        int i2 = this.length;
        if (i2 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i3] = (byte) i;
        this.length = i3 + 1;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ByteVector put12(int i, int i2) {
        int i3 = this.length;
        if (i3 + 3 > this.data.length) {
            enlarge(3);
        }
        byte[] bArr = this.data;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i5] = (byte) i2;
        this.length = i5 + 1;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ByteVector put112(int i, int i2, int i3) {
        int i4 = this.length;
        if (i4 + 4 > this.data.length) {
            enlarge(4);
        }
        byte[] bArr = this.data;
        int i5 = i4 + 1;
        bArr[i4] = (byte) i;
        int i6 = i5 + 1;
        bArr[i5] = (byte) i2;
        int i7 = i6 + 1;
        bArr[i6] = (byte) (i3 >>> 8);
        bArr[i7] = (byte) i3;
        this.length = i7 + 1;
        return this;
    }

    public ByteVector putInt(int i) {
        int i2 = this.length;
        if (i2 + 4 > this.data.length) {
            enlarge(4);
        }
        byte[] bArr = this.data;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i5] = (byte) i;
        this.length = i5 + 1;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final ByteVector put122(int i, int i2, int i3) {
        int i4 = this.length;
        if (i4 + 5 > this.data.length) {
            enlarge(5);
        }
        byte[] bArr = this.data;
        int i5 = i4 + 1;
        bArr[i4] = (byte) i;
        int i6 = i5 + 1;
        bArr[i5] = (byte) (i2 >>> 8);
        int i7 = i6 + 1;
        bArr[i6] = (byte) i2;
        int i8 = i7 + 1;
        bArr[i7] = (byte) (i3 >>> 8);
        bArr[i8] = (byte) i3;
        this.length = i8 + 1;
        return this;
    }

    public ByteVector putLong(long j) {
        int i = this.length;
        if (i + 8 > this.data.length) {
            enlarge(8);
        }
        byte[] bArr = this.data;
        int i2 = (int) (j >>> 32);
        int i3 = i + 1;
        bArr[i] = (byte) (i2 >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i2 >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        int i6 = i5 + 1;
        bArr[i5] = (byte) i2;
        int i7 = (int) j;
        int i8 = i6 + 1;
        bArr[i6] = (byte) (i7 >>> 24);
        int i9 = i8 + 1;
        bArr[i8] = (byte) (i7 >>> 16);
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i7 >>> 8);
        bArr[i10] = (byte) i7;
        this.length = i10 + 1;
        return this;
    }

    public ByteVector putUTF8(String str) {
        int length2 = str.length();
        if (length2 <= 65535) {
            int i = this.length;
            if (i + 2 + length2 > this.data.length) {
                enlarge(length2 + 2);
            }
            byte[] bArr = this.data;
            int i2 = i + 1;
            bArr[i] = (byte) (length2 >>> 8);
            int i3 = i2 + 1;
            bArr[i2] = (byte) length2;
            int i4 = 0;
            while (i4 < length2) {
                char charAt = str.charAt(i4);
                if (charAt < 1 || charAt > 127) {
                    this.length = i3;
                    return encodeUtf8(str, i4, 65535);
                }
                bArr[i3] = (byte) charAt;
                i4++;
                i3++;
            }
            this.length = i3;
            return this;
        }
        throw new IllegalArgumentException("UTF8 string too large");
    }

    /* access modifiers changed from: package-private */
    public final ByteVector encodeUtf8(String str, int i, int i2) {
        int i3;
        int length2 = str.length();
        int i4 = i;
        int i5 = i4;
        while (i4 < length2) {
            char charAt = str.charAt(i4);
            i5 = (charAt < 1 || charAt > 127) ? charAt <= 2047 ? i5 + 2 : i5 + 3 : i5 + 1;
            i4++;
        }
        if (i5 <= i2) {
            int i6 = (this.length - i) - 2;
            if (i6 >= 0) {
                byte[] bArr = this.data;
                bArr[i6] = (byte) (i5 >>> 8);
                bArr[i6 + 1] = (byte) i5;
            }
            if ((this.length + i5) - i > this.data.length) {
                enlarge(i5 - i);
            }
            int i7 = this.length;
            while (i < length2) {
                char charAt2 = str.charAt(i);
                if (charAt2 >= 1 && charAt2 <= 127) {
                    i3 = i7 + 1;
                    this.data[i7] = (byte) charAt2;
                } else if (charAt2 <= 2047) {
                    byte[] bArr2 = this.data;
                    int i8 = i7 + 1;
                    bArr2[i7] = (byte) (((charAt2 >> 6) & 31) | 192);
                    i7 = i8 + 1;
                    bArr2[i8] = (byte) ((charAt2 & '?') | 128);
                    i++;
                } else {
                    byte[] bArr3 = this.data;
                    int i9 = i7 + 1;
                    bArr3[i7] = (byte) (((charAt2 >> 12) & 15) | 224);
                    int i10 = i9 + 1;
                    bArr3[i9] = (byte) (((charAt2 >> 6) & 63) | 128);
                    i3 = i10 + 1;
                    bArr3[i10] = (byte) ((charAt2 & '?') | 128);
                }
                i7 = i3;
                i++;
            }
            this.length = i7;
            return this;
        }
        throw new IllegalArgumentException("UTF8 string too large");
    }

    public ByteVector putByteArray(byte[] bArr, int i, int i2) {
        if (this.length + i2 > this.data.length) {
            enlarge(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.data, this.length, i2);
        }
        this.length += i2;
        return this;
    }

    private void enlarge(int i) {
        int length2 = this.data.length * 2;
        int i2 = this.length + i;
        if (length2 <= i2) {
            length2 = i2;
        }
        byte[] bArr = new byte[length2];
        System.arraycopy(this.data, 0, bArr, 0, this.length);
        this.data = bArr;
    }
}
