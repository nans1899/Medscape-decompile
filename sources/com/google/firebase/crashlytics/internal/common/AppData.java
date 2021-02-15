package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

class AppData {
    public final String buildId;
    public final String googleAppId;
    public final String installerPackageName;
    public final String packageName;
    public final String versionCode;
    public final String versionName;

    public static AppData create(Context context, IdManager idManager, String str, String str2) throws PackageManager.NameNotFoundException {
        String packageName2 = context.getPackageName();
        String installerPackageName2 = idManager.getInstallerPackageName();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName2, 0);
        return new AppData(str, str2, installerPackageName2, packageName2, Integer.toString(packageInfo.versionCode), packageInfo.versionName == null ? IdManager.DEFAULT_VERSION_NAME : packageInfo.versionName);
    }

    public AppData(String str, String str2, String str3, String str4, String str5, String str6) {
        this.googleAppId = str;
        this.buildId = str2;
        this.installerPackageName = str3;
        this.packageName = str4;
        this.versionCode = str5;
        this.versionName = str6;
    }
}
