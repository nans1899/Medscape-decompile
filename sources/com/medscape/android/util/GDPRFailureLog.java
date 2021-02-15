package com.medscape.android.util;

import android.app.Activity;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.medscape.android.analytics.FirebaseEventsConstants;
import com.wbmd.wbmddatacompliance.callbacks.IGDPRFailLoggingCallback;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0007J&\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/util/GDPRFailureLog;", "Lcom/wbmd/wbmddatacompliance/callbacks/IGDPRFailLoggingCallback;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "checkString", "", "str", "sendErrorLog", "", "msg", "cause", "exception", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: GDPRFailureLog.kt */
public final class GDPRFailureLog implements IGDPRFailLoggingCallback {
    private final Activity activity;

    public GDPRFailureLog(Activity activity2) {
        Intrinsics.checkNotNullParameter(activity2, "activity");
        this.activity = activity2;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public void sendErrorLog(String str, String str2, Throwable th) {
        String checkString;
        if (th != null) {
            try {
                FirebaseCrashlytics.getInstance().recordException(th);
            } catch (Throwable th2) {
                th2.printStackTrace();
                return;
            }
        }
        PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(this.activity, false, true);
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString(NotificationCompat.CATEGORY_MESSAGE, checkString(str));
        } else {
            GDPRFailureLog gDPRFailureLog = this;
            if (!(th == null || (checkString = checkString(th.getMessage())) == null)) {
                bundle.putString(NotificationCompat.CATEGORY_MESSAGE, checkString);
            }
        }
        if (str2 != null) {
            bundle.putString("cause", checkString(str2));
        }
        platformRouteDispatcher.routeEvent(FirebaseEventsConstants.GDPR_FAIL, bundle);
    }

    public final String checkString(String str) {
        if (str == null || str.length() <= 99) {
            return str;
        }
        String substring = str.substring(0, 99);
        Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return substring;
    }
}
