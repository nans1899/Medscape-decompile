package com.medscape.android.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.provider.Telephony;
import com.medscape.android.R;
import java.util.ArrayList;
import java.util.List;

public class ShareUtil {
    public static Intent createChooser(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        PackageManager packageManager = context.getPackageManager();
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType("text/plain");
        String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(context);
        Intent createChooser = Intent.createChooser(intent, context.getResources().getString(R.string.share_dialog_title));
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent2, 0);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            ResolveInfo resolveInfo = queryIntentActivities.get(i);
            String str3 = resolveInfo.activityInfo.packageName;
            Intent intent3 = new Intent();
            intent3.setComponent(new ComponentName(str3, resolveInfo.activityInfo.name));
            intent3.setAction("android.intent.action.SEND");
            intent3.setType("text/plain");
            intent3.putExtra("android.intent.extra.TEXT", str);
            if (!str3.contains(defaultSmsPackage)) {
                intent3.putExtra("android.intent.extra.SUBJECT", str2);
            }
            arrayList.add(new LabeledIntent(intent3, str3, resolveInfo.loadLabel(packageManager), resolveInfo.icon));
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[]) arrayList.toArray(new LabeledIntent[arrayList.size()]));
        return createChooser;
    }
}
