package com.appboy.support;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.appboy.Constants;
import java.util.List;
import java.util.Random;

public final class IntentUtils {
    private static final String a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, IntentUtils.class.getName()});
    private static final Random b = new Random();

    public static int getRequestCode() {
        return b.nextInt();
    }

    public static void addComponentAndSendBroadcast(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers == null) {
            String str = a;
            AppboyLogger.d(str, "No components found for the following intent: " + intent.getAction());
            return;
        }
        for (ResolveInfo next : queryBroadcastReceivers) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(next.activityInfo.applicationInfo.packageName, next.activityInfo.name));
            context.sendBroadcast(intent2);
        }
    }
}
