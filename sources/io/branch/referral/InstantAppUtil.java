package io.branch.referral;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class InstantAppUtil {
    private static Boolean isInstantApp;
    private static Context lastApplicationContext;
    private static PackageManagerWrapper packageManagerWrapper;

    InstantAppUtil() {
    }

    static boolean isInstantApp(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            return false;
        }
        if (isInstantApp != null && applicationContext.equals(lastApplicationContext)) {
            return isInstantApp.booleanValue();
        }
        Boolean bool = null;
        isInstantApp = null;
        if (isAtLeastO()) {
            if (packageManagerWrapper == null || !applicationContext.equals(lastApplicationContext)) {
                packageManagerWrapper = new PackageManagerWrapper(applicationContext.getPackageManager());
            }
            bool = packageManagerWrapper.isInstantApp();
        }
        lastApplicationContext = applicationContext;
        if (bool != null) {
            isInstantApp = bool;
        } else {
            try {
                applicationContext.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                isInstantApp = Boolean.TRUE;
            } catch (ClassNotFoundException unused) {
                isInstantApp = Boolean.FALSE;
            }
        }
        return isInstantApp.booleanValue();
    }

    /* access modifiers changed from: private */
    public static boolean isAtLeastO() {
        return Build.VERSION.SDK_INT > 25 || isPreReleaseOBuild();
    }

    private static boolean isPreReleaseOBuild() {
        return !"REL".equals(Build.VERSION.CODENAME) && ("O".equals(Build.VERSION.CODENAME) || Build.VERSION.CODENAME.startsWith("OMR"));
    }

    static boolean doShowInstallPrompt(Activity activity, int i, String str) {
        if (activity == null) {
            if (Branch.isLogging_ != null && Branch.isLogging_.booleanValue()) {
                Log.e("BranchSDK", "Unable to show install prompt. Activity is null");
            }
            return false;
        } else if (!isInstantApp(activity)) {
            if (Branch.isLogging_ != null && Branch.isLogging_.booleanValue()) {
                Log.e("BranchSDK", "Unable to show install prompt. Application is not an instant app");
            }
            return false;
        } else {
            Intent putExtra = new Intent("android.intent.action.VIEW").setPackage("com.android.vending").addCategory("android.intent.category.DEFAULT").putExtra("callerId", activity.getPackageName()).putExtra("overlay", true);
            Uri.Builder appendQueryParameter = new Uri.Builder().scheme("market").authority("details").appendQueryParameter("id", activity.getPackageName());
            if (!TextUtils.isEmpty(str)) {
                appendQueryParameter.appendQueryParameter("referrer", str);
            }
            putExtra.setData(appendQueryParameter.build());
            activity.startActivityForResult(putExtra, i);
            return true;
        }
    }

    private static class PackageManagerWrapper {
        private static Method isInstantAppMethod;
        private final PackageManager packageManager;

        PackageManagerWrapper(PackageManager packageManager2) {
            this.packageManager = packageManager2;
        }

        /* access modifiers changed from: package-private */
        public Boolean isInstantApp() {
            if (!InstantAppUtil.isAtLeastO()) {
                return null;
            }
            if (isInstantAppMethod == null) {
                try {
                    isInstantAppMethod = PackageManager.class.getDeclaredMethod("isInstantApp", new Class[0]);
                } catch (NoSuchMethodException unused) {
                    return null;
                }
            }
            try {
                return (Boolean) isInstantAppMethod.invoke(this.packageManager, new Object[0]);
            } catch (IllegalAccessException | InvocationTargetException unused2) {
                return null;
            }
        }
    }
}
