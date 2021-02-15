package com.google.android.gms.internal.ads;

import java.security.interfaces.ECPublicKey;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzegw {
    private ECPublicKey zzicl;

    public zzegw(ECPublicKey eCPublicKey) {
        this.zzicl = eCPublicKey;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzegv zza(java.lang.String r11, byte[] r12, byte[] r13, int r14, com.google.android.gms.internal.ads.zzegz r15) throws java.security.GeneralSecurityException {
        /*
            r10 = this;
            java.security.interfaces.ECPublicKey r0 = r10.zzicl
            java.security.spec.ECParameterSpec r0 = r0.getParams()
            java.security.KeyPair r0 = com.google.android.gms.internal.ads.zzegy.zza((java.security.spec.ECParameterSpec) r0)
            java.security.PublicKey r1 = r0.getPublic()
            java.security.interfaces.ECPublicKey r1 = (java.security.interfaces.ECPublicKey) r1
            java.security.PrivateKey r0 = r0.getPrivate()
            java.security.interfaces.ECPrivateKey r0 = (java.security.interfaces.ECPrivateKey) r0
            java.security.interfaces.ECPublicKey r2 = r10.zzicl
            java.security.spec.ECParameterSpec r3 = r2.getParams()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            java.security.spec.ECParameterSpec r4 = r0.getParams()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            java.security.spec.EllipticCurve r5 = r3.getCurve()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            java.security.spec.EllipticCurve r6 = r4.getCurve()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            boolean r5 = r5.equals(r6)     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            r6 = 1
            r7 = 0
            if (r5 == 0) goto L_0x0058
            java.security.spec.ECPoint r5 = r3.getGenerator()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            java.security.spec.ECPoint r8 = r4.getGenerator()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            boolean r5 = r5.equals(r8)     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            if (r5 == 0) goto L_0x0058
            java.math.BigInteger r5 = r3.getOrder()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            java.math.BigInteger r8 = r4.getOrder()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            boolean r5 = r5.equals(r8)     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            if (r5 == 0) goto L_0x0058
            int r3 = r3.getCofactor()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            int r4 = r4.getCofactor()     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            if (r3 != r4) goto L_0x0058
            r3 = 1
            goto L_0x0059
        L_0x0058:
            r3 = 0
        L_0x0059:
            if (r3 == 0) goto L_0x01ab
            java.security.spec.ECPoint r2 = r2.getW()
            byte[] r0 = com.google.android.gms.internal.ads.zzegy.zza((java.security.interfaces.ECPrivateKey) r0, (java.security.spec.ECPoint) r2)
            java.security.spec.ECParameterSpec r2 = r1.getParams()
            java.security.spec.EllipticCurve r2 = r2.getCurve()
            java.security.spec.ECPoint r1 = r1.getW()
            com.google.android.gms.internal.ads.zzegy.zza((java.security.spec.ECPoint) r1, (java.security.spec.EllipticCurve) r2)
            java.math.BigInteger r2 = com.google.android.gms.internal.ads.zzegy.zza((java.security.spec.EllipticCurve) r2)
            java.math.BigInteger r3 = java.math.BigInteger.ONE
            java.math.BigInteger r2 = r2.subtract(r3)
            int r2 = r2.bitLength()
            int r2 = r2 + 7
            int r2 = r2 / 8
            int[] r3 = com.google.android.gms.internal.ads.zzegx.zzicm
            int r4 = r15.ordinal()
            r3 = r3[r4]
            r4 = 2
            if (r3 == r6) goto L_0x0110
            if (r3 == r4) goto L_0x00db
            r5 = 3
            if (r3 != r5) goto L_0x00b6
            int r2 = r2 + r6
            byte[] r15 = new byte[r2]
            java.math.BigInteger r3 = r1.getAffineX()
            byte[] r3 = r3.toByteArray()
            int r8 = r3.length
            int r2 = r2 - r8
            int r8 = r3.length
            java.lang.System.arraycopy(r3, r7, r15, r2, r8)
            java.math.BigInteger r1 = r1.getAffineY()
            boolean r1 = r1.testBit(r7)
            if (r1 == 0) goto L_0x00b0
            goto L_0x00b1
        L_0x00b0:
            r5 = 2
        L_0x00b1:
            byte r1 = (byte) r5
            r15[r7] = r1
            goto L_0x0136
        L_0x00b6:
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException
            java.lang.String r12 = java.lang.String.valueOf(r15)
            java.lang.String r13 = java.lang.String.valueOf(r12)
            int r13 = r13.length()
            int r13 = r13 + 15
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>(r13)
            java.lang.String r13 = "invalid format:"
            r14.append(r13)
            r14.append(r12)
            java.lang.String r12 = r14.toString()
            r11.<init>(r12)
            throw r11
        L_0x00db:
            int r15 = r2 * 2
            byte[] r3 = new byte[r15]
            java.math.BigInteger r5 = r1.getAffineX()
            byte[] r5 = r5.toByteArray()
            int r8 = r5.length
            if (r8 <= r2) goto L_0x00f1
            int r8 = r5.length
            int r8 = r8 - r2
            int r9 = r5.length
            byte[] r5 = java.util.Arrays.copyOfRange(r5, r8, r9)
        L_0x00f1:
            java.math.BigInteger r1 = r1.getAffineY()
            byte[] r1 = r1.toByteArray()
            int r8 = r1.length
            if (r8 <= r2) goto L_0x0103
            int r8 = r1.length
            int r8 = r8 - r2
            int r9 = r1.length
            byte[] r1 = java.util.Arrays.copyOfRange(r1, r8, r9)
        L_0x0103:
            int r8 = r1.length
            int r15 = r15 - r8
            int r8 = r1.length
            java.lang.System.arraycopy(r1, r7, r3, r15, r8)
            int r15 = r5.length
            int r2 = r2 - r15
            int r15 = r5.length
            java.lang.System.arraycopy(r5, r7, r3, r2, r15)
            goto L_0x0135
        L_0x0110:
            int r15 = r2 * 2
            int r15 = r15 + r6
            byte[] r3 = new byte[r15]
            java.math.BigInteger r5 = r1.getAffineX()
            byte[] r5 = r5.toByteArray()
            java.math.BigInteger r1 = r1.getAffineY()
            byte[] r1 = r1.toByteArray()
            int r8 = r1.length
            int r15 = r15 - r8
            int r8 = r1.length
            java.lang.System.arraycopy(r1, r7, r3, r15, r8)
            int r2 = r2 + r6
            int r15 = r5.length
            int r2 = r2 - r15
            int r15 = r5.length
            java.lang.System.arraycopy(r5, r7, r3, r2, r15)
            r15 = 4
            r3[r7] = r15
        L_0x0135:
            r15 = r3
        L_0x0136:
            byte[][] r1 = new byte[r4][]
            r1[r7] = r15
            r1[r6] = r0
            byte[] r0 = com.google.android.gms.internal.ads.zzegl.zza(r1)
            com.google.android.gms.internal.ads.zzehb<com.google.android.gms.internal.ads.zzehh, javax.crypto.Mac> r1 = com.google.android.gms.internal.ads.zzehb.zzicy
            java.lang.Object r1 = r1.zzhs(r11)
            javax.crypto.Mac r1 = (javax.crypto.Mac) r1
            int r2 = r1.getMacLength()
            int r2 = r2 * 255
            if (r14 > r2) goto L_0x01a3
            if (r12 == 0) goto L_0x015f
            int r2 = r12.length
            if (r2 != 0) goto L_0x0156
            goto L_0x015f
        L_0x0156:
            javax.crypto.spec.SecretKeySpec r2 = new javax.crypto.spec.SecretKeySpec
            r2.<init>(r12, r11)
            r1.init(r2)
            goto L_0x016d
        L_0x015f:
            javax.crypto.spec.SecretKeySpec r12 = new javax.crypto.spec.SecretKeySpec
            int r2 = r1.getMacLength()
            byte[] r2 = new byte[r2]
            r12.<init>(r2, r11)
            r1.init(r12)
        L_0x016d:
            byte[] r12 = r1.doFinal(r0)
            byte[] r0 = new byte[r14]
            javax.crypto.spec.SecretKeySpec r2 = new javax.crypto.spec.SecretKeySpec
            r2.<init>(r12, r11)
            r1.init(r2)
            byte[] r11 = new byte[r7]
            r12 = 0
        L_0x017e:
            r1.update(r11)
            r1.update(r13)
            byte r11 = (byte) r6
            r1.update(r11)
            byte[] r11 = r1.doFinal()
            int r2 = r11.length
            int r2 = r2 + r12
            if (r2 >= r14) goto L_0x0199
            int r2 = r11.length
            java.lang.System.arraycopy(r11, r7, r0, r12, r2)
            int r2 = r11.length
            int r12 = r12 + r2
            int r6 = r6 + 1
            goto L_0x017e
        L_0x0199:
            int r14 = r14 - r12
            java.lang.System.arraycopy(r11, r7, r0, r12, r14)
            com.google.android.gms.internal.ads.zzegv r11 = new com.google.android.gms.internal.ads.zzegv
            r11.<init>(r15, r0)
            return r11
        L_0x01a3:
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException
            java.lang.String r12 = "size too large"
            r11.<init>(r12)
            throw r11
        L_0x01ab:
            java.security.GeneralSecurityException r11 = new java.security.GeneralSecurityException     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            java.lang.String r12 = "invalid public key spec"
            r11.<init>(r12)     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
            throw r11     // Catch:{ IllegalArgumentException -> 0x01b5, NullPointerException -> 0x01b3 }
        L_0x01b3:
            r11 = move-exception
            goto L_0x01b6
        L_0x01b5:
            r11 = move-exception
        L_0x01b6:
            java.security.GeneralSecurityException r12 = new java.security.GeneralSecurityException
            java.lang.String r11 = r11.toString()
            r12.<init>(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzegw.zza(java.lang.String, byte[], byte[], int, com.google.android.gms.internal.ads.zzegz):com.google.android.gms.internal.ads.zzegv");
    }
}
