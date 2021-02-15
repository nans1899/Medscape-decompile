package com.appboy.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import okhttp3.HttpUrl;
import org.json.JSONArray;

public final class StringUtils {
    private static final String a = AppboyLogger.getAppboyLogTag(StringUtils.class);

    public static String checkNotNullOrEmpty(String str) {
        if (str == null) {
            throw new NullPointerException("Provided String must be non-null.");
        } else if (str.length() != 0) {
            return str;
        } else {
            throw new IllegalArgumentException("Provided String must be non-empty.");
        }
    }

    public static String join(Collection<String> collection, String str) {
        return collection == null ? "" : join((String[]) collection.toArray(new String[collection.size()]), str);
    }

    public static String join(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            if (str2 != null) {
                sb.append(str2);
                sb.append(str);
            }
        }
        String sb2 = sb.toString();
        return sb2.endsWith(str) ? sb2.substring(0, sb2.length() - str.length()) : sb2;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getOptionalStringResource(Resources resources, int i, String str) {
        try {
            return resources.getString(i);
        } catch (Resources.NotFoundException unused) {
            return str;
        }
    }

    public static String emptyToNull(String str) {
        if (str.trim().equals("")) {
            return null;
        }
        return str;
    }

    public static int countOccurrences(String str, String str2) {
        return str.split(str2, -1).length - 1;
    }

    public static HashSet<String> stringArrayToHashSet(String[] strArr) {
        return new HashSet<>(Arrays.asList(strArr));
    }

    public static HashSet<String> jsonArrayToHashSet(JSONArray jSONArray) {
        HashSet<String> hashSet = new HashSet<>();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.get(i).toString());
            }
        }
        return hashSet;
    }

    public static String stringArrayToJsonString(String[] strArr) {
        JSONArray jSONArray = new JSONArray();
        if (strArr == null) {
            return null;
        }
        if (strArr.length == 0) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        for (String put : strArr) {
            jSONArray.put(put);
        }
        return jSONArray.toString();
    }

    public static String getCacheFileSuffix(Context context, String str, String str2) {
        if (str == null) {
            str = "null";
        }
        if (str.equals("null")) {
            return a("37a6259cc0c1dae299a7866489dff0bd", str2);
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.appboy.support.stringutils.cachefilesuffix", 0);
        String string = sharedPreferences.getString("user_id_key", (String) null);
        if (string != null && string.equals(str)) {
            String string2 = sharedPreferences.getString("user_id_hash_value", (String) null);
            if (!isNullOrEmpty(string2)) {
                return a(string2, str2);
            }
            AppboyLogger.d(a, "The saved user id hash was null or empty.");
        }
        String str3 = a;
        AppboyLogger.d(str3, "Generating MD5 for user id: " + str + " apiKey: " + str2);
        String md5Hash = getMd5Hash(str);
        if (md5Hash == null) {
            md5Hash = Integer.toString(str.hashCode());
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("user_id_key", str);
        edit.putString("user_id_hash_value", md5Hash);
        edit.apply();
        return a(md5Hash, str2);
    }

    public static String getCacheFileSuffix(Context context, String str) {
        return getCacheFileSuffix(context, str, (String) null);
    }

    private static String a(String str, String str2) {
        if (isNullOrBlank(str2)) {
            return "." + str;
        }
        return "." + str + "." + str2;
    }

    public static String getMd5Hash(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to calculate MD5 hash", e);
            return null;
        }
    }
}
