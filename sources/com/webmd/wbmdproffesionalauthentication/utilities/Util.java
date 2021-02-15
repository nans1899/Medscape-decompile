package com.webmd.wbmdproffesionalauthentication.utilities;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        r1 = (r1 = (android.net.ConnectivityManager) r1.getSystemService("connectivity")).getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isOnline(android.content.Context r1) {
        /*
            if (r1 == 0) goto L_0x001a
            java.lang.String r0 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r0)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            if (r1 == 0) goto L_0x001a
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x001a
            boolean r1 = r1.isConnectedOrConnecting()
            if (r1 == 0) goto L_0x001a
            r1 = 1
            return r1
        L_0x001a:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdproffesionalauthentication.utilities.Util.isOnline(android.content.Context):boolean");
    }

    public static void hideKeyboard(Activity activity) {
        Object systemService;
        InputMethodManager inputMethodManager;
        if (activity != null && (systemService = activity.getSystemService("input_method")) != null && (systemService instanceof InputMethodManager) && (inputMethodManager = (InputMethodManager) systemService) != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void showKeyboard(Context context) {
        Object systemService;
        InputMethodManager inputMethodManager;
        if (context != null && context.getApplicationContext() != null && (systemService = context.getApplicationContext().getApplicationContext().getSystemService("input_method")) != null && (systemService instanceof InputMethodManager) && (inputMethodManager = (InputMethodManager) systemService) != null) {
            inputMethodManager.toggleSoftInput(2, 0);
        }
    }

    public static String getApplicationVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void showSnackBar(View view, String str) {
        Snackbar.make(view, (CharSequence) str, -1).show();
    }

    public static String getAppNameForUrl(Context context) {
        String packageName;
        if (!(context == null || (packageName = context.getPackageName()) == null)) {
            if (packageName.contains("cmepulse")) {
                return "cmepulseapp-android";
            }
            if (packageName.contains("medpulse")) {
                return "medpulseapp-android";
            }
        }
        return "medscapeapp-android";
    }

    public static JSONObject getJsonObjectFromResponse(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isValidEmail(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }
}
