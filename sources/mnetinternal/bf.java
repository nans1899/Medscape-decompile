package mnetinternal;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class bf {
    public static String a(Activity activity) {
        return new bj(activity.getIntent()).b();
    }

    public static String a(Activity activity, List<bk> list) {
        bj bjVar = new bj(activity.getIntent());
        Uri.Builder appendPath = new Uri.Builder().scheme("http").authority(c(activity)).appendPath(String.valueOf(a(activity.getBaseContext()))).appendPath(String.valueOf(bw.a(activity)));
        if (bjVar.b() != null) {
            appendPath.appendQueryParameter("uri", bjVar.b());
        } else {
            appendPath.appendQueryParameter("intent", bjVar.a());
        }
        a(appendPath, list);
        return appendPath.build().toString();
    }

    private static void a(Uri.Builder builder, List<bk> list) {
        if (list != null && !list.isEmpty()) {
            bk.a(list);
            int i = 0;
            for (bk next : list) {
                builder.appendQueryParameter(next.a(i), next.a());
                builder.appendQueryParameter(next.b(i), String.valueOf(next.c()));
                builder.appendQueryParameter(next.c(i), String.valueOf(next.b()));
                i++;
            }
        }
    }

    private static String c(Context context) {
        String packageName;
        if (context == null || (packageName = context.getApplicationContext().getPackageName()) == null) {
            return "";
        }
        List asList = Arrays.asList(packageName.split("\\."));
        Collections.reverse(asList);
        return TextUtils.join(".", asList) + "." + "amnapp";
    }

    public static int a(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            bi.a("##LinkGenerator", (Throwable) e);
            return -1;
        }
    }

    public static String b(Context context) {
        return new Uri.Builder().scheme("http").authority(c(context)).appendPath(String.valueOf(a(context))).build().toString();
    }
}
