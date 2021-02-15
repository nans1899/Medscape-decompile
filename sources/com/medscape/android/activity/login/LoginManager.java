package com.medscape.android.activity.login;

import android.content.Context;
import android.util.Log;
import com.medscape.android.security.KeychainManager;
import com.medscape.android.security.SimpleCrypto;

public class LoginManager {
    public static String getStoredGUID(Context context) {
        try {
            String findValueInKeychain = KeychainManager.findValueInKeychain("pref_guid", context);
            if (findValueInKeychain != null) {
                return SimpleCrypto.decrypt(findValueInKeychain);
            }
            return "";
        } catch (Exception unused) {
            Log.w("loginmanager", "Error decrypting the Stored GUID");
            return "";
        }
    }

    public static void resetGUID(Context context) {
        KeychainManager.removeValueFromKeychain(context, "pref_guid");
    }

    public static void storeGuid(Context context, String str) {
        try {
            KeychainManager.saveValueToKeychain(context, "pref_guid", SimpleCrypto.encrypt(str));
        } catch (Exception unused) {
            Log.w("loginmanager", "Error while encrypting credentials");
        }
    }
}
