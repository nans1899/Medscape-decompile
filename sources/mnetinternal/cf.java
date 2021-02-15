package mnetinternal;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public final class cf {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return a(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bArr);
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
    }
}
