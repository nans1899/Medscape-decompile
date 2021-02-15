package com.medscape.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {
    private static Settings sInstance;
    private Context mContext;

    private Settings(Context context) {
        this.mContext = context;
    }

    public SharedPreferences.Editor getEditor() {
        Context context = this.mContext;
        if (context != null) {
            return PreferenceManager.getDefaultSharedPreferences(context).edit();
        }
        return null;
    }

    public static Settings singleton(Context context) {
        if (sInstance == null) {
            sInstance = new Settings(context);
        }
        return sInstance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getSetting(java.lang.String r3, java.lang.String r4) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r3 = "txt"
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            android.content.Context r0 = r2.mContext
            if (r0 == 0) goto L_0x0026
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            if (r0 == 0) goto L_0x0026
            boolean r1 = r0.contains(r3)
            if (r1 == 0) goto L_0x0026
            java.lang.String r3 = r0.getString(r3, r4)
            return r3
        L_0x0026:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.Settings.getSetting(java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0015, code lost:
        r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getSetting(java.lang.String r3, boolean r4) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r3 = "txt"
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            android.content.Context r0 = r2.mContext
            if (r0 == 0) goto L_0x0026
            android.content.SharedPreferences r0 = android.preference.PreferenceManager.getDefaultSharedPreferences(r0)
            if (r0 == 0) goto L_0x0026
            boolean r1 = r0.contains(r3)
            if (r1 == 0) goto L_0x0026
            boolean r3 = r0.getBoolean(r3, r4)
            return r3
        L_0x0026:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.Settings.getSetting(java.lang.String, boolean):boolean");
    }

    public boolean saveSetting(String str, String str2) {
        SharedPreferences defaultSharedPreferences;
        String str3 = str + "txt";
        Context context = this.mContext;
        if (context == null || (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) == null) {
            return false;
        }
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        edit.putString(str3, str2);
        return edit.commit();
    }

    public boolean saveSetting(String str, boolean z) {
        SharedPreferences defaultSharedPreferences;
        String str2 = str + "txt";
        Context context = this.mContext;
        if (context == null || (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) == null) {
            return false;
        }
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        edit.putBoolean(str2, z);
        return edit.commit();
    }

    public boolean removeSetting(String str) {
        SharedPreferences defaultSharedPreferences;
        String str2 = str + "txt";
        Context context = this.mContext;
        if (context == null || (defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)) == null) {
            return false;
        }
        SharedPreferences.Editor edit = defaultSharedPreferences.edit();
        edit.remove(str2);
        return edit.commit();
    }
}
