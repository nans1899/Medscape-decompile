package com.google.firebase.inappmessaging.internal;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.google.firebase.FirebaseApp;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class SharedPreferencesUtils {
    static final String PREFERENCES_PACKAGE_NAME = "com.google.firebase.inappmessaging";
    private final FirebaseApp firebaseApp;

    @Inject
    public SharedPreferencesUtils(FirebaseApp firebaseApp2) {
        this.firebaseApp = firebaseApp2;
    }

    public void setBooleanPreference(String str, boolean z) {
        SharedPreferences.Editor edit = ((Application) this.firebaseApp.getApplicationContext()).getSharedPreferences("com.google.firebase.inappmessaging", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public boolean getAndSetBooleanPreference(String str, boolean z) {
        SharedPreferences sharedPreferences = ((Application) this.firebaseApp.getApplicationContext()).getSharedPreferences("com.google.firebase.inappmessaging", 0);
        if (sharedPreferences.contains(str)) {
            return sharedPreferences.getBoolean(str, z);
        }
        setBooleanPreference(str, z);
        return z;
    }

    public boolean getBooleanPreference(String str, boolean z) {
        SharedPreferences sharedPreferences = ((Application) this.firebaseApp.getApplicationContext()).getSharedPreferences("com.google.firebase.inappmessaging", 0);
        return sharedPreferences.contains(str) ? sharedPreferences.getBoolean(str, z) : z;
    }

    public boolean isPreferenceSet(String str) {
        return ((Application) this.firebaseApp.getApplicationContext()).getSharedPreferences("com.google.firebase.inappmessaging", 0).contains(str);
    }

    public boolean isManifestSet(String str) {
        ApplicationInfo applicationInfo;
        Application application = (Application) this.firebaseApp.getApplicationContext();
        try {
            PackageManager packageManager = application.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(application.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(str)) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public boolean getBooleanManifestValue(String str, boolean z) {
        ApplicationInfo applicationInfo;
        Application application = (Application) this.firebaseApp.getApplicationContext();
        try {
            PackageManager packageManager = application.getPackageManager();
            if (!(packageManager == null || (applicationInfo = packageManager.getApplicationInfo(application.getPackageName(), 128)) == null || applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(str))) {
                return applicationInfo.metaData.getBoolean(str);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return z;
    }
}
