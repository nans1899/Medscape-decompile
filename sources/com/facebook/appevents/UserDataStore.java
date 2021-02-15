package com.facebook.appevents;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import com.facebook.FacebookSdk;
import com.facebook.internal.Utility;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class UserDataStore {
    public static final String CITY = "ct";
    public static final String COUNTRY = "country";
    public static final String DATE_OF_BIRTH = "db";
    public static final String EMAIL = "em";
    public static final String FIRST_NAME = "fn";
    public static final String GENDER = "ge";
    public static final String LAST_NAME = "ln";
    public static final String PHONE = "ph";
    public static final String STATE = "st";
    /* access modifiers changed from: private */
    public static final String TAG = UserDataStore.class.getSimpleName();
    private static final String USER_DATA_KEY = "com.facebook.appevents.UserDataStore.userData";
    public static final String ZIP = "zp";
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<String, String> hashedUserData;
    /* access modifiers changed from: private */
    public static AtomicBoolean initialized = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static SharedPreferences sharedPreferences;

    static void initStore() {
        if (!initialized.get()) {
            initAndWait();
        }
    }

    static void setUserDataAndHash(final Bundle bundle) {
        InternalAppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                if (!UserDataStore.initialized.get()) {
                    Log.w(UserDataStore.TAG, "initStore should have been called before calling setUserData");
                    UserDataStore.initAndWait();
                }
                UserDataStore.updateHashUserData(bundle);
                UserDataStore.sharedPreferences.edit().putString(UserDataStore.USER_DATA_KEY, UserDataStore.mapToJsonStr(UserDataStore.hashedUserData)).apply();
            }
        });
    }

    static void setUserDataAndHash(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString(EMAIL, str);
        }
        if (str2 != null) {
            bundle.putString(FIRST_NAME, str2);
        }
        if (str3 != null) {
            bundle.putString(LAST_NAME, str3);
        }
        if (str4 != null) {
            bundle.putString(PHONE, str4);
        }
        if (str5 != null) {
            bundle.putString(DATE_OF_BIRTH, str5);
        }
        if (str6 != null) {
            bundle.putString(GENDER, str6);
        }
        if (str7 != null) {
            bundle.putString("ct", str7);
        }
        if (str8 != null) {
            bundle.putString("st", str8);
        }
        if (str9 != null) {
            bundle.putString(ZIP, str9);
        }
        if (str10 != null) {
            bundle.putString(COUNTRY, str10);
        }
        setUserDataAndHash(bundle);
    }

    static void clear() {
        InternalAppEventsLogger.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                if (!UserDataStore.initialized.get()) {
                    Log.w(UserDataStore.TAG, "initStore should have been called before calling setUserData");
                    UserDataStore.initAndWait();
                }
                UserDataStore.hashedUserData.clear();
                UserDataStore.sharedPreferences.edit().putString(UserDataStore.USER_DATA_KEY, (String) null).apply();
            }
        });
    }

    static String getHashedUserData() {
        if (!initialized.get()) {
            Log.w(TAG, "initStore should have been called before calling setUserID");
            initAndWait();
        }
        return mapToJsonStr(hashedUserData);
    }

    /* access modifiers changed from: private */
    public static synchronized void initAndWait() {
        synchronized (UserDataStore.class) {
            if (!initialized.get()) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
                sharedPreferences = defaultSharedPreferences;
                hashedUserData = new ConcurrentHashMap<>(JsonStrToMap(defaultSharedPreferences.getString(USER_DATA_KEY, "")));
                initialized.set(true);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void updateHashUserData(Bundle bundle) {
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                if (obj != null) {
                    String obj2 = obj.toString();
                    if (maybeSHA256Hashed(obj2)) {
                        hashedUserData.put(str, obj2.toLowerCase());
                    } else {
                        String sha256hash = Utility.sha256hash(normalizeData(str, obj2));
                        if (sha256hash != null) {
                            hashedUserData.put(str, sha256hash);
                        }
                    }
                }
            }
        }
    }

    private static String normalizeData(String str, String str2) {
        String str3;
        String lowerCase = str2.trim().toLowerCase();
        if (EMAIL.equals(str)) {
            if (Patterns.EMAIL_ADDRESS.matcher(lowerCase).matches()) {
                return lowerCase;
            }
            Log.e(TAG, "Setting email failure: this is not a valid email address");
            return "";
        } else if (PHONE.equals(str)) {
            return lowerCase.replaceAll("[^0-9]", "");
        } else {
            if (!GENDER.equals(str)) {
                return lowerCase;
            }
            if (lowerCase.length() > 0) {
                str3 = lowerCase.substring(0, 1);
            } else {
                str3 = "";
            }
            if ("f".equals(str3) || "m".equals(str3)) {
                return str3;
            }
            Log.e(TAG, "Setting gender failure: the supported value for gender is f or m");
            return "";
        }
    }

    private static boolean maybeSHA256Hashed(String str) {
        return str.matches("[A-Fa-f0-9]{64}");
    }

    /* access modifiers changed from: private */
    public static String mapToJsonStr(Map<String, String> map) {
        if (map.isEmpty()) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (String next : map.keySet()) {
                jSONObject.put(next, map.get(next));
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    private static Map<String, String> JsonStrToMap(String str) {
        if (str.isEmpty()) {
            return new HashMap();
        }
        try {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            return hashMap;
        } catch (JSONException unused) {
            return new HashMap();
        }
    }
}
