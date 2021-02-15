package com.medscape.android.security;

import android.content.Context;
import android.content.SharedPreferences;

public class KeychainManager {
    static int contextMode;

    public static boolean saveValueToKeychain(Context context, String str, String str2) {
        if (context == null) {
            return false;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("medscape.keychain", contextMode).edit();
        edit.putString(str, str2);
        return edit.commit();
    }

    public static boolean removeValueFromKeychain(Context context, String str) {
        if (context != null) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("medscape.keychain", contextMode).edit();
                edit.remove(str);
                return edit.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String findValueInKeychain(String str, Context context) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getSharedPreferences("medscape.keychain", contextMode)) == null) {
            return null;
        }
        return sharedPreferences.getString(str, (String) null);
    }

    public static String getValueFromSharedKeychain(String str, Context context) {
        String valueFromApp = getValueFromApp(str, context, (String) null, "com.medscape.medpulse", "medpulse.keychain");
        return valueFromApp == null ? getValueFromApp(str, context, valueFromApp, "com.medscape.cmepulse", "cmepulse.keychain") : valueFromApp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        r3 = r3.getSharedPreferences(r6, contextMode);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getValueFromApp(java.lang.String r2, android.content.Context r3, java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            if (r3 == 0) goto L_0x0020
            r0 = 0
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ Exception -> 0x000d }
            r1 = 2
            android.content.Context r3 = r3.createPackageContext(r5, r1)     // Catch:{ Exception -> 0x000d }
            goto L_0x0012
        L_0x000d:
            r3 = move-exception
            r3.printStackTrace()
            r3 = r0
        L_0x0012:
            if (r3 == 0) goto L_0x0020
            int r5 = contextMode
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r6, r5)
            if (r3 == 0) goto L_0x0020
            java.lang.String r4 = r3.getString(r2, r0)
        L_0x0020:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.security.KeychainManager.getValueFromApp(java.lang.String, android.content.Context, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }
}
