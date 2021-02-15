package com.webmd.wbmdproffesionalauthentication.encryption;

import android.util.Base64;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHelper {
    private static final String ANDROID_KEY_STORE = "webmd.consumerauthentication.keystore";
    private static final int ITERATION_COUNT = 1000;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 32;
    private static final String STRING_TO_GENERATE_KEY = "pUZ!$h_ze-u#uprEzah=3H7RefUqetH6";
    private static byte[] iv;
    private static byte[] salt;

    public static SecretKey generateKey(boolean z) throws Exception {
        if (z) {
            generateRandomSalt();
        }
        return new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(STRING_TO_GENERATE_KEY.toCharArray(), salt, 1000, 256)).getEncoded(), "AES");
    }

    private static void generateRandomSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[32];
        salt = bArr;
        secureRandom.nextBytes(bArr);
    }

    public static String encrypt(String str) throws Exception {
        if (str == null || str.isEmpty()) {
            return "";
        }
        Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
        instance.init(1, generateKey(true));
        iv = instance.getIV();
        String encodeToString = Base64.encodeToString(instance.doFinal(str.getBytes("UTF-8")), 0);
        return encodeToString + "]" + Base64.encodeToString(iv, 0) + "]" + Base64.encodeToString(salt, 0);
    }

    public static String decrypt(String str) throws Exception {
        if (str == null || str.isEmpty()) {
            return "";
        }
        String[] split = str.split("]");
        if (split.length == 3) {
            byte[] decode = Base64.decode(split[0], 0);
            byte[] decode2 = Base64.decode(split[1], 0);
            salt = Base64.decode(split[2], 0);
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(2, generateKey(false), new IvParameterSpec(decode2));
            return new String(instance.doFinal(decode), "UTF-8");
        }
        throw new IllegalArgumentException("Invalid encrypted message format");
    }
}
