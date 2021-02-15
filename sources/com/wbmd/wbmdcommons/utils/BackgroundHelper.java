package com.wbmd.wbmdcommons.utils;

import android.app.ActivityManager;
import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/wbmd/wbmdcommons/utils/BackgroundHelper;", "", "()V", "isAppInBackground", "", "context", "Landroid/content/Context;", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BackgroundHelper.kt */
public final class BackgroundHelper {
    public static final BackgroundHelper INSTANCE = new BackgroundHelper();

    private BackgroundHelper() {
    }

    public final boolean isAppInBackground(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        if (systemService != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (Intrinsics.areEqual((Object) context.getPackageName(), (Object) next.processName) && next.importance != 100) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
    }
}
