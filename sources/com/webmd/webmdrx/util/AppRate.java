package com.webmd.webmdrx.util;

import android.app.Activity;
import android.content.SharedPreferences;
import java.lang.ref.WeakReference;

public class AppRate {
    private static final int DAYS_TILL_MESSAGE = 7;
    private static final int DRUGS_SEARCHED_TILL_MESSAGE = 4;
    private static final int DRUGS_SEARCHED_TILL_MESSAGE_NEW = 7;
    private static final int LAUNCHES_TILL_MESSAGE = 50;
    private static final int MY_MEDS_VIEWED_TILL_MESSAGE = 3;
    private static final int MY_MEDS_VIEWED_TILL_MESSAGE_NEW = 5;

    public static void toShowRateDialogDrugSearch(WeakReference<Activity> weakReference) {
        Activity activity;
        if (weakReference != null && (activity = (Activity) weakReference.get()) != null) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("rate_app_counter", 0);
            if (!sharedPreferences.getBoolean("rate_app_never", false)) {
                long j = sharedPreferences.getLong("searches_made", 1);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                int i = (j > 7 ? 1 : (j == 7 ? 0 : -1));
                if (i == 0) {
                    showAppRateDialog(weakReference);
                }
                if (i >= 0) {
                    long j2 = j - 7;
                    if (j2 > 0 && j2 % 4 == 0) {
                        showAppRateDialog(weakReference);
                    }
                }
                edit.putLong("searches_made", j + 1);
                edit.apply();
            }
        }
    }

    public static void toShowRateDialogMyMedsView(WeakReference<Activity> weakReference) {
        Activity activity;
        if (weakReference != null && (activity = (Activity) weakReference.get()) != null) {
            SharedPreferences sharedPreferences = activity.getSharedPreferences("rate_app_counter", 0);
            if (!sharedPreferences.getBoolean("rate_app_never", false)) {
                long j = sharedPreferences.getLong("my_med_views", 1);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                int i = (j > 5 ? 1 : (j == 5 ? 0 : -1));
                if (i == 0) {
                    showAppRateDialog(weakReference);
                }
                if (i >= 0) {
                    long j2 = j - 5;
                    if (j2 > 0 && j2 % 3 == 0) {
                        showAppRateDialog(weakReference);
                    }
                }
                edit.putLong("my_med_views", j + 1);
                edit.apply();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r4 = (android.app.Activity) r4.get();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void showAppRateDialog(java.lang.ref.WeakReference<android.app.Activity> r4) {
        /*
            if (r4 == 0) goto L_0x0045
            java.lang.Object r4 = r4.get()
            android.app.Activity r4 = (android.app.Activity) r4
            if (r4 == 0) goto L_0x0045
            androidx.appcompat.app.AlertDialog$Builder r0 = new androidx.appcompat.app.AlertDialog$Builder
            r0.<init>(r4)
            r1 = 0
            java.lang.String r2 = "rate_app_counter"
            android.content.SharedPreferences r1 = r4.getSharedPreferences(r2, r1)
            android.content.SharedPreferences$Editor r1 = r1.edit()
            int r2 = com.webmd.webmdrx.R.string.app_rate_title
            r0.setTitle((int) r2)
            int r2 = com.webmd.webmdrx.R.string.rate_app_dialog_message
            r0.setMessage((int) r2)
            int r2 = com.webmd.webmdrx.R.string.app_rate_button_positive
            com.webmd.webmdrx.util.AppRate$1 r3 = new com.webmd.webmdrx.util.AppRate$1
            r3.<init>(r1, r4)
            r0.setPositiveButton((int) r2, (android.content.DialogInterface.OnClickListener) r3)
            int r4 = com.webmd.webmdrx.R.string.app_rate_button_negative
            com.webmd.webmdrx.util.AppRate$2 r2 = new com.webmd.webmdrx.util.AppRate$2
            r2.<init>(r1)
            r0.setNegativeButton((int) r4, (android.content.DialogInterface.OnClickListener) r2)
            int r4 = com.webmd.webmdrx.R.string.app_rate_button_neutral
            com.webmd.webmdrx.util.AppRate$3 r1 = new com.webmd.webmdrx.util.AppRate$3
            r1.<init>()
            r0.setNeutralButton((int) r4, (android.content.DialogInterface.OnClickListener) r1)
            r0.show()
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.util.AppRate.showAppRateDialog(java.lang.ref.WeakReference):void");
    }
}
