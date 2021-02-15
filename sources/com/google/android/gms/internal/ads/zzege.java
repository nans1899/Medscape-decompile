package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzege implements zzeam {
    private final SecretKey zzibo;
    private final int zzibp;
    private byte[] zzibq;
    private byte[] zzibr;

    private static Cipher zzbdl() throws GeneralSecurityException {
        return zzehb.zzicx.zzhs("AES/ECB/NoPadding");
    }

    public zzege(byte[] bArr, int i) throws GeneralSecurityException {
        zzeht.zzfp(bArr.length);
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, min is 10 bytes");
        } else if (i <= 16) {
            this.zzibo = new SecretKeySpec(bArr, "AES");
            this.zzibp = i;
            Cipher zzbdl = zzbdl();
            zzbdl.init(1, this.zzibo);
            byte[] zzq = zzegm.zzq(zzbdl.doFinal(new byte[16]));
            this.zzibq = zzq;
            this.zzibr = zzegm.zzq(zzq);
        } else {
            throw new InvalidAlgorithmParameterException("tag size too large, max is 16 bytes");
        }
    }

    public final byte[] zzm(byte[] bArr) throws GeneralSecurityException {
        byte[] bArr2;
        Cipher zzbdl = zzbdl();
        zzbdl.init(1, this.zzibo);
        int max = Math.max(1, (int) Math.ceil(((double) bArr.length) / 16.0d));
        if ((max << 4) == bArr.length) {
            bArr2 = zzegl.zza(bArr, (max - 1) << 4, this.zzibq, 0, 16);
        } else {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, (max - 1) << 4, bArr.length);
            if (copyOfRange.length < 16) {
                byte[] copyOf = Arrays.copyOf(copyOfRange, 16);
                copyOf[copyOfRange.length] = Byte.MIN_VALUE;
                bArr2 = zzegl.zzd(copyOf, this.zzibr);
            } else {
                throw new IllegalArgumentException("x must be smaller than a block.");
            }
        }
        byte[] bArr3 = new byte[16];
        for (int i = 0; i < max - 1; i++) {
            bArr3 = zzbdl.doFinal(zzegl.zza(bArr3, 0, bArr, i << 4, 16));
        }
        byte[] zzd = zzegl.zzd(bArr2, bArr3);
        byte[] bArr4 = new byte[this.zzibp];
        System.arraycopy(zzbdl.doFinal(zzd), 0, bArr4, 0, this.zzibp);
        return bArr4;
    }
}
