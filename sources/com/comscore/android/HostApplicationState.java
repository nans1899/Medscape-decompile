package com.comscore.android;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import java.util.List;

public class HostApplicationState {
    public static final int BACKGROUND = 1;
    public static final int FOREGROUND = 0;
    public static final int UNKNOWN = -1;

    public static int getState(Context context) {
        if (context != null && Build.VERSION.SDK_INT > 20) {
            try {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
                if (runningAppProcesses == null) {
                    return -1;
                }
                for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                    if (next.importance == 100) {
                        for (String equals : next.pkgList) {
                            if (equals.equals(context.getPackageName())) {
                                return 0;
                            }
                        }
                        continue;
                    }
                }
                return 1;
            } catch (Exception unused) {
            }
        }
        return -1;
    }
}
