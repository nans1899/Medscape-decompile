package com.webmd.webmdrx.tasks;

import android.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TaskRequestHelper {
    public static String getClientSecretHashForTimestamp(long j, String str, String str2) {
        return hashString(String.format("{{timestamp:%s,client_id:%s}}", new Object[]{Long.valueOf(j), str2}), "HmacSHA256", str);
    }

    private static String hashString(String str, String str2, String str3) {
        try {
            Mac instance = Mac.getInstance(str2);
            instance.init(new SecretKeySpec(str3.getBytes(), str2));
            return Base64.encodeToString(instance.doFinal(str.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }
}
