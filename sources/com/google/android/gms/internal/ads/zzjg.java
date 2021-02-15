package com.google.android.gms.internal.ads;

import android.media.MediaCodec;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzjg {
    public byte[] iv;
    private byte[] key;
    private int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    private int numSubSamples;
    private int zzanv;
    private int zzanw;
    private final MediaCodec.CryptoInfo zzanx;
    private final zzji zzany;

    public zzjg() {
        zzji zzji = null;
        this.zzanx = zzpq.SDK_INT >= 16 ? new MediaCodec.CryptoInfo() : null;
        this.zzany = zzpq.SDK_INT >= 24 ? new zzji(this.zzanx) : zzji;
    }

    public final void set(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2) {
        this.numSubSamples = i;
        this.numBytesOfClearData = iArr;
        this.numBytesOfEncryptedData = iArr2;
        this.key = bArr;
        this.iv = bArr2;
        this.mode = i2;
        this.zzanv = 0;
        this.zzanw = 0;
        if (zzpq.SDK_INT >= 16) {
            this.zzanx.numSubSamples = this.numSubSamples;
            this.zzanx.numBytesOfClearData = this.numBytesOfClearData;
            this.zzanx.numBytesOfEncryptedData = this.numBytesOfEncryptedData;
            this.zzanx.key = this.key;
            this.zzanx.iv = this.iv;
            this.zzanx.mode = this.mode;
            if (zzpq.SDK_INT >= 24) {
                this.zzany.set(0, 0);
            }
        }
    }

    public final MediaCodec.CryptoInfo zzgj() {
        return this.zzanx;
    }
}
