package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzehp implements zzeam {
    private final zzecx zzido;
    private final int zzidp;

    public zzehp(zzecx zzecx, int i) throws GeneralSecurityException {
        this.zzido = zzecx;
        this.zzidp = i;
        if (i >= 10) {
            zzecx.zzd(new byte[0], i);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    public final byte[] zzm(byte[] bArr) throws GeneralSecurityException {
        return this.zzido.zzd(bArr, this.zzidp);
    }
}
