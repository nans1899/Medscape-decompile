package com.webmd.wbmdcmepulse.models.utils;

import com.google.common.base.Ascii;
import com.webmd.wbmdcmepulse.models.utils.crypto.InsecureSHA1PRNGKeyDerivator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SimpleCrypto {
    public static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String HEX = "0123456789ABCDEF";
    public static final int KEY_BYTE_LENGTH = 16;
    public static final String PASSWORD = "aBmvzkEbiaduEGse";

    public static String encrypt(String str) throws Exception {
        return toHex(encrypt(getLegacyRawKey("aBmvzkEbiaduEGse".getBytes()), str.getBytes()));
    }

    public static String decrypt(String str) throws Exception {
        try {
            return new String(decrypt(getLegacyRawKey("aBmvzkEbiaduEGse".getBytes()), toByte(str)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static byte[] getLegacyRawKey(byte[] bArr) {
        return new SecretKeySpec(InsecureSHA1PRNGKeyDerivator.deriveInsecureKey(bArr, 16), "AES").getEncoded();
    }

    private static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES");
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr2);
    }

    private static byte[] toByte(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    private static String toHex(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte appendHex : bArr) {
            appendHex(stringBuffer, appendHex);
        }
        return stringBuffer.toString();
    }

    private static void appendHex(StringBuffer stringBuffer, byte b) {
        stringBuffer.append(HEX.charAt((b >> 4) & 15));
        stringBuffer.append(HEX.charAt(b & Ascii.SI));
    }
}
