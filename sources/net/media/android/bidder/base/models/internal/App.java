package net.media.android.bidder.base.models.internal;

import mnetinternal.c;

public final class App {
    @c(a = "name")
    private String mAppName;
    @c(a = "package")
    private String mPackageName;
    @c(a = "version")
    private String mVersion;

    public App(String str, String str2, String str3) {
        this.mAppName = str;
        this.mPackageName = str2;
        this.mVersion = str3;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public String getVersion() {
        return this.mVersion;
    }
}
