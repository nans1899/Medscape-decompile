package com.webmd.webmdrx.util;

import android.app.ActivityManager;
import android.content.Context;
import java.util.Iterator;
import java.util.List;

public class AppBackgroundHelper {
    public static boolean isAppInBackground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        Boolean bool = false;
        if (runningAppProcesses != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (context.getPackageName().equals(next.processName) && next.importance != 100) {
                    bool = true;
                    break;
                }
            }
        }
        return bool.booleanValue();
    }
}
