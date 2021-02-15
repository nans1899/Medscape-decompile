package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
final class zzeis extends zzeiz {
    private final int zzieu;
    private final int zziev;

    zzeis(byte[] bArr, int i, int i2) {
        super(bArr);
        zzi(i, i + i2, bArr.length);
        this.zzieu = i;
        this.zziev = i2;
    }

    public final byte zzfu(int i) {
        zzaa(i, size());
        return this.zzifc[this.zzieu + i];
    }

    /* access modifiers changed from: package-private */
    public final byte zzfv(int i) {
        return this.zzifc[this.zzieu + i];
    }

    public final int size() {
        return this.zziev;
    }

    /* access modifiers changed from: protected */
    public final int zzbem() {
        return this.zzieu;
    }

    /* access modifiers changed from: protected */
    public final void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzifc, zzbem() + i, bArr, i2, i3);
    }
}
