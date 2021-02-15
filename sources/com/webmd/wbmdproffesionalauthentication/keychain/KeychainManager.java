package com.webmd.wbmdproffesionalauthentication.keychain;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.encryption.EncryptionHelper;
import com.webmd.wbmdproffesionalauthentication.encryption.SimpleCrypto;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;
import java.util.HashMap;
import java.util.Map;

public class KeychainManager {
    private static int contextMode = 1;

    public static Map<String, String> getKeychain(Context context) {
        HashMap hashMap = new HashMap();
        if (context != null) {
            String[] stringArray = context.getResources().getStringArray(R.array.wbmdprofessionalauthentication_other_consumer_apps);
            if (stringArray.length > 0) {
                int i = 0;
                while (true) {
                    if (i >= stringArray.length) {
                        break;
                    }
                    Uri parse = Uri.parse("content://" + stringArray[i] + "/global");
                    ContentProviderClient acquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(parse);
                    if (acquireContentProviderClient != null) {
                        try {
                            Cursor query = acquireContentProviderClient.query(parse, (String[]) null, (String) null, (String[]) null, (String) null);
                            if (query != null && query.moveToNext()) {
                                String string = query.getString(query.getColumnIndex("username"));
                                String string2 = query.getString(query.getColumnIndex("password"));
                                if (!string.isEmpty() && !string2.isEmpty()) {
                                    try {
                                        hashMap.put("username", EncryptionHelper.decrypt(string));
                                        hashMap.put("password", EncryptionHelper.decrypt(string2));
                                        break;
                                    } catch (Exception unused) {
                                        Log.i("_TAG", "getKeychain: ");
                                        break;
                                    }
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        acquireContentProviderClient.release();
                    }
                    i++;
                }
            }
        }
        if (hashMap.isEmpty()) {
            String findValueInKeychain = findValueInKeychain("pref_username", context);
            String findValueInKeychain2 = findValueInKeychain("pref_password", context);
            if (!StringExtensions.isNullOrEmpty(findValueInKeychain) && !StringExtensions.isNullOrEmpty(findValueInKeychain2)) {
                try {
                    hashMap.put("username", SimpleCrypto.decrypt(findValueInKeychain));
                    hashMap.put("password", SimpleCrypto.decrypt(findValueInKeychain2));
                } catch (Exception unused2) {
                    Log.w("loginmanager", "Error decrypting");
                }
            }
        }
        return hashMap;
    }

    public static String findValueInKeychain(String str, Context context) {
        if (context == null) {
            return null;
        }
        String findValueInKeychainByApp = findValueInKeychainByApp(str, context, (String) null, "com.medscape.android", "medscape.keychain");
        if (findValueInKeychainByApp == null) {
            findValueInKeychainByApp = findValueInKeychainByApp(str, context, findValueInKeychainByApp, "com.medscape.medpulse", "medpulse.keychain");
        }
        return findValueInKeychainByApp == null ? findValueInKeychainByApp(str, context, findValueInKeychainByApp, "com.medscape.cmepulse", "cmepulse.keychain") : findValueInKeychainByApp;
    }

    private static String findValueInKeychainByApp(String str, Context context, String str2, String str3, String str4) {
        try {
            Context createPackageContext = context.getApplicationContext().createPackageContext(str3, 2);
            if (createPackageContext != null) {
                context = createPackageContext;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(str4, contextMode);
            if (sharedPreferences != null) {
                return sharedPreferences.getString(str, (String) null);
            }
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }

    public static void saveToKeychain(Context context, String str, String str2) {
        SharedPreferenceProvider.get().saveString(context, str, str2);
    }

    public static String getValueFromKeychain(Context context, String str) {
        if (context == null) {
            return null;
        }
        String[] stringArray = context.getResources().getStringArray(R.array.wbmdprofessionalauthentication_other_consumer_apps);
        if (stringArray.length <= 0) {
            return null;
        }
        for (int i = 0; i < stringArray.length; i++) {
            Uri parse = Uri.parse("content://" + stringArray[i] + "/global");
            ContentProviderClient acquireContentProviderClient = context.getContentResolver().acquireContentProviderClient(parse);
            if (acquireContentProviderClient != null) {
                try {
                    Cursor query = acquireContentProviderClient.query(parse, new String[]{str}, (String) null, (String[]) null, (String) null);
                    if (query != null && query.moveToNext() && query.getColumnIndex(str) > -1) {
                        String string = query.getString(query.getColumnIndex(str));
                        if (!string.isEmpty()) {
                            return string;
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                acquireContentProviderClient.release();
            }
        }
        return null;
    }
}
