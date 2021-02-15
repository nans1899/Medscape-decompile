package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzeic {
    private final byte[] zzieb = new byte[256];
    private int zziec;
    private int zzied;

    public zzeic(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.zzieb[i] = (byte) i;
        }
        byte b = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            byte[] bArr2 = this.zzieb;
            b = (b + bArr2[i2] + bArr[i2 % bArr.length]) & 255;
            byte b2 = bArr2[i2];
            bArr2[i2] = bArr2[b];
            bArr2[b] = b2;
        }
        this.zziec = 0;
        this.zzied = 0;
    }

    public final void zzt(byte[] bArr) {
        int i = this.zziec;
        int i2 = this.zzied;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            byte[] bArr2 = this.zzieb;
            i2 = (i2 + bArr2[i]) & 255;
            byte b = bArr2[i];
            bArr2[i] = bArr2[i2];
            bArr2[i2] = b;
            bArr[i3] = (byte) (bArr2[(bArr2[i] + bArr2[i2]) & 255] ^ bArr[i3]);
        }
        this.zziec = i;
        this.zzied = i2;
    }
}
