package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzegv {
    private final zzehm zzicj;
    private final zzehm zzick;

    public zzegv(byte[] bArr, byte[] bArr2) {
        this.zzicj = zzehm.zzs(bArr);
        this.zzick = zzehm.zzs(bArr2);
    }

    public final byte[] zzbdo() {
        zzehm zzehm = this.zzicj;
        if (zzehm == null) {
            return null;
        }
        return zzehm.getBytes();
    }

    public final byte[] zzbdp() {
        zzehm zzehm = this.zzick;
        if (zzehm == null) {
            return null;
        }
        return zzehm.getBytes();
    }
}
