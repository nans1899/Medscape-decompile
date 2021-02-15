package com.appboy.ui.support;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.appboy.support.AppboyLogger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UriUtils {
    private static final String TAG = AppboyLogger.getAppboyLogTag(UriUtils.class);

    public static Map<String, String> getQueryParameters(Uri uri) {
        if (uri.isOpaque()) {
            AppboyLogger.d(TAG, "URI is not hierarchical. There are no query parameters to parse.");
            return Collections.emptyMap();
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        int i = 0;
        do {
            int indexOf = encodedQuery.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = encodedQuery.length();
            }
            int indexOf2 = encodedQuery.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            if (indexOf > i) {
                hashMap.put(Uri.decode(encodedQuery.substring(i, indexOf2)), Uri.decode(encodedQuery.substring(indexOf2 + 1, indexOf)));
            }
            i = indexOf + 1;
        } while (i < encodedQuery.length());
        return Collections.unmodifiableMap(hashMap);
    }

    public static Intent getMainActivityIntent(Context context, Bundle bundle) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.setFlags(872415232);
        if (bundle != null) {
            launchIntentForPackage.putExtras(bundle);
        }
        return launchIntentForPackage;
    }

    public static boolean isActivityRegisteredInManifest(Context context, String str) {
        try {
            return context.getPackageManager().getActivityInfo(new ComponentName(context, str), 0) != null;
        } catch (PackageManager.NameNotFoundException e) {
            String str2 = TAG;
            AppboyLogger.w(str2, "Could not find activity info for class with name: " + str, e);
            return false;
        }
    }
}
