package com.wbmd.wbmdcommons.utils;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.tapstream.sdk.http.RequestBuilders;
import com.wbmd.wbmdcommons.R;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.bytebuddy.description.type.TypeDescription;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u001a\u0010\u0010\r\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u001a\u0010\u0010\u000e\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u001a\u0010\u0010\u000f\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006\u001a\u0006\u0010\u0010\u001a\u00020\u0006\u001a,\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u001a\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b\u001a\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0002\u001a\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020\u0006\u001a>\u0010!\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\"\u001a\u0004\u0018\u00010\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u00062\b\u0010#\u001a\u0004\u0018\u00010\u00062\b\u0010$\u001a\u0004\u0018\u00010%\u001a\u000e\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u0002\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006("}, d2 = {"RX_SESSIONS", "Ljava/util/ArrayList;", "", "getRX_SESSIONS", "()Ljava/util/ArrayList;", "applicationVersion", "", "getApplicationVersion", "()Ljava/lang/String;", "setApplicationVersion", "(Ljava/lang/String;)V", "addBasicQueryParams", "url", "attachAndrodSrcTagToUrl", "attachShareParameters", "attachSrcTagToUrl", "getPhoneOSVersion", "getSendIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "title", "subject", "handleRxDeepLink", "", "Landroid/app/Activity;", "deepLinkUri", "Landroid/net/Uri;", "isRxCardToBeShown", "", "session", "setAppApplicationVersion", "version", "share", "link", "subsection", "pendingIntent", "Landroid/app/PendingIntent;", "sleep", "milliseconds", "wbmdcommons_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: Util.kt */
public final class Util {
    private static final ArrayList<Integer> RX_SESSIONS = new Util$RX_SESSIONS$1(4);
    private static String applicationVersion = "";

    public static final ArrayList<Integer> getRX_SESSIONS() {
        return RX_SESSIONS;
    }

    public static final String getApplicationVersion() {
        return applicationVersion;
    }

    public static final void setApplicationVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        applicationVersion = str;
    }

    public static final void handleRxDeepLink(Activity activity, Uri uri) {
        Intrinsics.checkNotNullParameter(activity, "context");
        Intrinsics.checkNotNullParameter(uri, "deepLinkUri");
        Intent intent = new Intent();
        Context context = activity;
        intent.setClassName(context, "com.webmd.android.activity.home.HomeActivity");
        if ((Intrinsics.areEqual((Object) uri.getScheme(), (Object) "wbmd") && Intrinsics.areEqual((Object) uri.getHost(), (Object) "rx")) || (Intrinsics.areEqual((Object) uri.getScheme(), (Object) RequestBuilders.DEFAULT_SCHEME) && Intrinsics.areEqual((Object) uri.getHost(), (Object) "www.webmd.com"))) {
            if ((uri.getLastPathSegment() == null || !Intrinsics.areEqual((Object) uri.getLastPathSegment(), (Object) "rx")) && uri.getLastPathSegment() != null) {
                String lastPathSegment = uri.getLastPathSegment();
                Intent intent2 = new Intent();
                intent2.setClassName(context, "com.webmd.webmdrx.activities.PrescriptionDetailsActivity");
                intent2.putExtra(Constants.EXTRA_DRUG_ID, lastPathSegment);
                intent2.putExtra(Constants.EXTRA_IS_DEEP_LINK, true);
                activity.startActivities(new Intent[]{intent, intent2});
                return;
            }
            Intent intent3 = new Intent();
            intent3.setClassName(context, "com.webmd.webmdrx.activities.search.RxSearchActivity");
            intent3.putExtra(Constants.EXTRA_IS_DEEP_LINK, true);
            activity.startActivities(new Intent[]{intent, intent3});
        }
    }

    public static final boolean isRxCardToBeShown(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        return !SharedPreferenceManager.INSTANCE.isUserVisitedTheRxPricingScreen(context) && RX_SESSIONS.contains(Integer.valueOf(i)) && !SharedPreferenceManager.INSTANCE.isUserClosedTheRxCard(context);
    }

    public static final void share(Context context, String str, String str2, String str3, String str4, PendingIntent pendingIntent) {
        Intent intent;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str3, "subject");
        if (str != null) {
            boolean z = false;
            if (StringsKt.startsWith$default(str, "http://", false, 2, (Object) null)) {
                str = StringsKt.replace$default(str, "http://", "https://", false, 4, (Object) null);
            }
            str = Intrinsics.stringPlus(str, attachShareParameters(str));
            if (str4 != null) {
                if (str4.length() > 0) {
                    z = true;
                }
                if (z) {
                    str = Intrinsics.stringPlus(str, str4);
                }
            }
        }
        if (StringExtensions.isNullOrEmpty(str3)) {
            str3 = context.getString(R.string.email_title);
            Intrinsics.checkNotNullExpressionValue(str3, "context.getString(R.string.email_title)");
        }
        Intent sendIntent = getSendIntent(context, str2, str, str3);
        if (pendingIntent == null) {
            intent = Intent.createChooser(sendIntent, (CharSequence) null);
            Intrinsics.checkNotNullExpressionValue(intent, "Intent.createChooser(sendIntent, null)");
        } else {
            if (Build.VERSION.SDK_INT >= 22) {
                intent = Intent.createChooser(sendIntent, (CharSequence) null, pendingIntent.getIntentSender());
            } else {
                intent = Intent.createChooser(sendIntent, (CharSequence) null);
            }
            Intrinsics.checkNotNullExpressionValue(intent, "if (Build.VERSION.SDK_IN…ndIntent, null)\n        }");
        }
        context.startActivity(intent);
    }

    public static final String attachShareParameters(String str) {
        if (str != null) {
            return StringsKt.contains$default((CharSequence) str, (CharSequence) TypeDescription.Generic.OfWildcardType.SYMBOL, false, 2, (Object) null) ? "&src=mbl_msp_android&ref=share" : "?src=mbl_msp_android&ref=share";
        }
        return "";
    }

    public static final String attachAndrodSrcTagToUrl(String str) {
        if (str == null) {
            return "";
        }
        CharSequence charSequence = str;
        if (StringsKt.contains$default(charSequence, (CharSequence) "src=android", false, 2, (Object) null)) {
            return "";
        }
        return StringsKt.contains$default(charSequence, (CharSequence) TypeDescription.Generic.OfWildcardType.SYMBOL, false, 2, (Object) null) ? "&src=android" : "?src=android";
    }

    public static final String attachSrcTagToUrl(String str) {
        if (str == null) {
            return "";
        }
        CharSequence charSequence = str;
        if (StringsKt.contains$default(charSequence, (CharSequence) "src=medscapeapp-android", false, 2, (Object) null)) {
            return "";
        }
        return StringsKt.contains$default(charSequence, (CharSequence) TypeDescription.Generic.OfWildcardType.SYMBOL, false, 2, (Object) null) ? "&src=medscapeapp-android" : "?src=medscapeapp-android";
    }

    public static final String addBasicQueryParams(String str) {
        String str2 = "";
        if (str == null) {
            return str2;
        }
        CharSequence charSequence = str;
        if (!StringsKt.contains$default(charSequence, (CharSequence) TypeDescription.Generic.OfWildcardType.SYMBOL, false, 2, (Object) null)) {
            str2 = str2 + TypeDescription.Generic.OfWildcardType.SYMBOL;
        } else if (StringsKt.contains$default(charSequence, (CharSequence) "=", false, 2, (Object) null)) {
            str2 = str2 + "&";
        }
        if (!StringsKt.contains$default(charSequence, (CharSequence) "devicetype=", false, 2, (Object) null)) {
            str2 = str2 + "devicetype=android&";
        }
        if (!StringsKt.contains$default(charSequence, (CharSequence) "osversion=", false, 2, (Object) null)) {
            str2 = str2 + "osversion=" + getPhoneOSVersion() + "&";
        }
        if (StringsKt.contains$default(charSequence, (CharSequence) "appversion=", false, 2, (Object) null)) {
            return str2;
        }
        return str2 + "appversion=" + applicationVersion;
    }

    public static final String getPhoneOSVersion() {
        try {
            String str = Build.VERSION.RELEASE;
            Intrinsics.checkNotNullExpressionValue(str, "Build.VERSION.RELEASE");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static final Intent getSendIntent(Context context, String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", str3);
        intent.putExtra("android.intent.extra.TEXT", context.getString(R.string.email_body, new Object[]{str, str2}));
        intent.setType("text/plain");
        return intent;
    }

    public static final void setAppApplicationVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "version");
        applicationVersion = str;
    }

    public static final void sleep(int i) {
        try {
            Thread.sleep((long) i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
