package com.webmd.wbmdproffesionalauthentication.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.encryption.EncryptionHelper;
import com.webmd.wbmdproffesionalauthentication.encryption.SimpleCrypto;

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

    public void save(String str, String str2, Context context) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        try {
            edit.putString(str, EncryptionHelper.encrypt(str2));
        } catch (Exception unused) {
            edit.putString(str, "");
        }
        edit.commit();
    }

    public void saveString(Context context, String str, String str2) {
        if (context != null) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
            edit.putString(str, str2);
            edit.commit();
        }
    }

    public String getString(String str, String str2, Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
    }

    public String getSimpleCryptoDecryptedString(String str, String str2, Context context) throws Exception {
        String string = getString(SimpleCrypto.encrypt(str), str2, context);
        return !StringExtensions.isNullOrEmpty(string) ? SimpleCrypto.decrypt(string) : string;
    }

    public void saveCryptoEncrypted(String str, String str2, Context context) throws Exception {
        saveString(context, SimpleCrypto.encrypt(str), SimpleCrypto.encrypt(str2));
    }
}
