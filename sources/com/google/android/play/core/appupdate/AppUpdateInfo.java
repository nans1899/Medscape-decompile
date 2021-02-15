package com.google.android.play.core.appupdate;

import android.app.PendingIntent;

public abstract class AppUpdateInfo {
    public static AppUpdateInfo a(String str, int i, int i2, int i3, Integer num, int i4, long j, long j2, long j3, long j4, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3, PendingIntent pendingIntent4) {
        return new l(str, i, i2, i3, num, i4, j, j2, j3, j4, pendingIntent, pendingIntent2, pendingIntent3, pendingIntent4);
    }

    private final boolean b(AppUpdateOptions appUpdateOptions) {
        return appUpdateOptions.a() && a() <= b();
    }

    /* access modifiers changed from: package-private */
    public abstract long a();

    /* access modifiers changed from: package-private */
    public final PendingIntent a(AppUpdateOptions appUpdateOptions) {
        if (appUpdateOptions.appUpdateType() != 0) {
            if (appUpdateOptions.appUpdateType() == 1) {
                if (c() != null) {
                    return c();
                }
                if (b(appUpdateOptions)) {
                    return e();
                }
            }
            return null;
        } else if (d() != null) {
            return d();
        } else {
            if (b(appUpdateOptions)) {
                return f();
            }
            return null;
        }
    }

    public abstract int availableVersionCode();

    /* access modifiers changed from: package-private */
    public abstract long b();

    public abstract long bytesDownloaded();

    /* access modifiers changed from: package-private */
    public abstract PendingIntent c();

    public abstract Integer clientVersionStalenessDays();

    /* access modifiers changed from: package-private */
    public abstract PendingIntent d();

    /* access modifiers changed from: package-private */
    public abstract PendingIntent e();

    /* access modifiers changed from: package-private */
    public abstract PendingIntent f();

    public abstract int installStatus();

    public boolean isUpdateTypeAllowed(int i) {
        return a(AppUpdateOptions.defaultOptions(i)) != null;
    }

    public boolean isUpdateTypeAllowed(AppUpdateOptions appUpdateOptions) {
        return a(appUpdateOptions) != null;
    }

    public abstract String packageName();

    public abstract long totalBytesToDownload();

    public abstract int updateAvailability();

    public abstract int updatePriority();
}
