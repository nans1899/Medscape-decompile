package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzbeu implements zzol {
    private final byte[] zzdtu;
    private final zzol zzelb;

    zzbeu(zzol zzol, byte[] bArr) {
        this.zzelb = zzol;
        this.zzdtu = bArr;
    }

    public final zzom zzip() {
        zzol zzol = this.zzelb;
        byte[] bArr = this.zzdtu;
        return new zzbfb(new zzoj(bArr), bArr.length, zzol.zzip());
    }
}
