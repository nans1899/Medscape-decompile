package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzehm {
    private final byte[] data;

    public static zzehm zzs(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new zzehm(bArr, 0, bArr.length);
    }

    public final byte[] getBytes() {
        byte[] bArr = this.data;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    private zzehm(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.data = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i2);
    }
}
