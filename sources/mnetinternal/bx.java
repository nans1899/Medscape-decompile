package mnetinternal;

import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class bx {
    public static String a(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes("UTF-8"));
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                StringBuilder sb2 = new StringBuilder(Integer.toHexString(b & 255));
                while (sb2.length() < 2) {
                    sb2.insert(0, AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                sb.append(sb2);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            bi.a("##Encryption", (Throwable) e);
            return "";
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
