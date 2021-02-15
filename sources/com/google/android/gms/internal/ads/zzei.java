package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzei {
    private static Cipher zzye;
    private static final Object zzyf = new Object();
    private static final Object zzyg = new Object();
    private final SecureRandom zzyd = null;

    public zzei(SecureRandom secureRandom) {
    }

    public final byte[] zzap(String str) throws zzeh {
        try {
            byte[] zzb = zzcv.zzb(str, false);
            if (zzb.length == 32) {
                byte[] bArr = new byte[16];
                ByteBuffer.wrap(zzb, 4, 16).get(bArr);
                for (int i = 0; i < 16; i++) {
                    bArr[i] = (byte) (bArr[i] ^ 68);
                }
                return bArr;
            }
            throw new zzeh(this);
        } catch (IllegalArgumentException e) {
            throw new zzeh(this, e);
        }
    }

    public final String zzb(byte[] bArr, byte[] bArr2) throws zzeh {
        byte[] doFinal;
        byte[] iv;
        if (bArr.length == 16) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                synchronized (zzyf) {
                    getCipher().init(1, secretKeySpec, (SecureRandom) null);
                    doFinal = getCipher().doFinal(bArr2);
                    iv = getCipher().getIV();
                }
                int length = doFinal.length + iv.length;
                ByteBuffer allocate = ByteBuffer.allocate(length);
                allocate.put(iv).put(doFinal);
                allocate.flip();
                byte[] bArr3 = new byte[length];
                allocate.get(bArr3);
                return zzcv.zza(bArr3, false);
            } catch (NoSuchAlgorithmException e) {
                throw new zzeh(this, e);
            } catch (InvalidKeyException e2) {
                throw new zzeh(this, e2);
            } catch (IllegalBlockSizeException e3) {
                throw new zzeh(this, e3);
            } catch (NoSuchPaddingException e4) {
                throw new zzeh(this, e4);
            } catch (BadPaddingException e5) {
                throw new zzeh(this, e5);
            }
        } else {
            throw new zzeh(this);
        }
    }

    public final byte[] zza(byte[] bArr, String str) throws zzeh {
        byte[] doFinal;
        if (bArr.length == 16) {
            try {
                byte[] zzb = zzcv.zzb(str, false);
                if (zzb.length > 16) {
                    ByteBuffer allocate = ByteBuffer.allocate(zzb.length);
                    allocate.put(zzb);
                    allocate.flip();
                    byte[] bArr2 = new byte[16];
                    byte[] bArr3 = new byte[(zzb.length - 16)];
                    allocate.get(bArr2);
                    allocate.get(bArr3);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                    synchronized (zzyf) {
                        getCipher().init(2, secretKeySpec, new IvParameterSpec(bArr2));
                        doFinal = getCipher().doFinal(bArr3);
                    }
                    return doFinal;
                }
                throw new zzeh(this);
            } catch (NoSuchAlgorithmException e) {
                throw new zzeh(this, e);
            } catch (InvalidKeyException e2) {
                throw new zzeh(this, e2);
            } catch (IllegalBlockSizeException e3) {
                throw new zzeh(this, e3);
            } catch (NoSuchPaddingException e4) {
                throw new zzeh(this, e4);
            } catch (BadPaddingException e5) {
                throw new zzeh(this, e5);
            } catch (InvalidAlgorithmParameterException e6) {
                throw new zzeh(this, e6);
            } catch (IllegalArgumentException e7) {
                throw new zzeh(this, e7);
            }
        } else {
            throw new zzeh(this);
        }
    }

    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher;
        synchronized (zzyg) {
            if (zzye == null) {
                zzye = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            cipher = zzye;
        }
        return cipher;
    }
}
