package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzegd implements zzehl {
    private static final ThreadLocal<Cipher> zzibk = new zzegg();
    private final SecretKeySpec zzibl;
    private final int zzibm;
    private final int zzibn;

    public zzegd(byte[] bArr, int i) throws GeneralSecurityException {
        zzeht.zzfp(bArr.length);
        this.zzibl = new SecretKeySpec(bArr, "AES");
        int blockSize = zzibk.get().getBlockSize();
        this.zzibn = blockSize;
        if (i < 12 || i > blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.zzibm = i;
    }

    public final byte[] zzo(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        int i = this.zzibm;
        if (length <= Integer.MAX_VALUE - i) {
            byte[] bArr2 = new byte[(bArr.length + i)];
            byte[] zzfo = zzehs.zzfo(i);
            System.arraycopy(zzfo, 0, bArr2, 0, this.zzibm);
            int length2 = bArr.length;
            int i2 = this.zzibm;
            Cipher cipher = zzibk.get();
            byte[] bArr3 = new byte[this.zzibn];
            System.arraycopy(zzfo, 0, bArr3, 0, this.zzibm);
            cipher.init(1, this.zzibl, new IvParameterSpec(bArr3));
            if (cipher.doFinal(bArr, 0, length2, bArr2, i2) == length2) {
                return bArr2;
            }
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("plaintext length can not exceed ");
        sb.append(Integer.MAX_VALUE - this.zzibm);
        throw new GeneralSecurityException(sb.toString());
    }
}
