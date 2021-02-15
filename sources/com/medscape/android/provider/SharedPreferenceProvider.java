package com.medscape.android.provider;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.medscape.android.MedscapeApplication;

public class SharedPreferenceProvider {
    private static SharedPreferenceProvider mInstance;

    private SharedPreferenceProvider() {
    }

    public static SharedPreferenceProvider get() {
        if (mInstance == null) {
            synchronized (SharedPreferenceProvider.class) {
                mInstance = new SharedPreferenceProvider();
            }
        }
        return mInstance;
    }

    public void save(String str, Long l) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).edit();
        edit.putLong(str, l.longValue());
        edit.apply();
    }

    public void save(String str, int i) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public void save(String str, String str2) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public void save(String str, boolean z) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public long get(String str, long j) {
        return MedscapeApplication.get().getApplicationContext() != null ? PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).getLong(str, j) : j;
    }

    public boolean get(String str, boolean z) {
        return MedscapeApplication.get().getApplicationContext() != null ? PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).getBoolean(str, z) : z;
    }

    public int get(String str, int i) {
        return MedscapeApplication.get().getApplicationContext() != null ? PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).getInt(str, i) : i;
    }

    public String get(String str, String str2) {
        return MedscapeApplication.get().getApplicationContext() != null ? PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).getString(str, str2) : str2;
    }

    public void remove(String str) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(MedscapeApplication.get().getApplicationContext()).edit();
        edit.remove(str);
        edit.apply();
    }
}
