package com.webmd.wbmdcmepulse.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.webmd.wbmdproffesionalauthentication.encryption.EncryptionHelper;

public class SharedPreferencesSettings {
    private static SharedPreferencesSettings mInstance;

    private SharedPreferencesSettings() {
    }

    public static SharedPreferencesSettings get() {
        if (mInstance == null) {
            synchronized (SharedPreferencesSettings.class) {
                mInstance = new SharedPreferencesSettings();
            }
        }
        return mInstance;
    }

    public void save(String str, String str2, Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        try {
            edit.putString(str, EncryptionHelper.encrypt(str2));
        } catch (Exception unused) {
            edit.putString(str, "");
        }
        edit.apply();
    }

    public String getString(String str, String str2, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0013, code lost:
        r4 = android.preference.PreferenceManager.getDefaultSharedPreferences(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getMedscapeSettings(java.lang.String r2, java.lang.String r3, android.content.Context r4) {
        /*
            r1 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            java.lang.String r2 = "txt"
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            if (r4 == 0) goto L_0x0024
            android.content.SharedPreferences r4 = android.preference.PreferenceManager.getDefaultSharedPreferences(r4)
            if (r4 == 0) goto L_0x0024
            boolean r0 = r4.contains(r2)
            if (r0 == 0) goto L_0x0024
            java.lang.String r2 = r4.getString(r2, r3)
            return r2
        L_0x0024:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.providers.SharedPreferencesSettings.getMedscapeSettings(java.lang.String, java.lang.String, android.content.Context):java.lang.String");
    }
}
