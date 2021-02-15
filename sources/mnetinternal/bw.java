package mnetinternal;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class bw {
    private static List<String> a = new ArrayList();

    private static void a(Application application) {
        try {
            a.clear();
            ActivityInfo[] activityInfoArr = application.getPackageManager().getPackageInfo(application.getPackageName(), 1).activities;
            if (activityInfoArr != null) {
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if (activityInfo != null) {
                        if (activityInfo.name != null) {
                            a.add(activityInfo.name);
                        }
                    }
                }
                Collections.sort(a);
            }
        } catch (PackageManager.NameNotFoundException e) {
            bi.a("##ActivityIndex", "init: " + e.getMessage());
        }
    }

    public static int a(Activity activity) {
        if (activity == null) {
            return -1;
        }
        if (a.isEmpty()) {
            a(activity.getApplication());
        }
        return a.indexOf(activity.getClass().getCanonicalName());
    }
}
