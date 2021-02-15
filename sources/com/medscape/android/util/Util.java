package com.medscape.android.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.net.MailTo;
import androidx.fragment.app.FragmentActivity;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.BuildConfig;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.calc.MedscapeCalculatorActivity;
import com.medscape.android.activity.calc.MedscapeDefinitionActivity;
import com.medscape.android.activity.calc.MedscapeFileSourceHtmlActivity;
import com.medscape.android.activity.calc.MedscapePdfViewerActivity;
import com.medscape.android.activity.calc.MedscapeReferenceBookActivity;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.login.LogoutHandler;
import com.medscape.android.activity.update.UpdateReferenceMainActivity;
import com.medscape.android.analytics.FirebaseConversionEventHandler;
import com.medscape.android.analytics.FirebaseEventsConstants;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.auth.OAuthResponseParser;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.forceup.ForceUpManager;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.parser.model.MetricsUserProfile;
import com.medscape.android.parser.model.UserProfile;
import com.medscape.android.welcome.LoginCompletedCallback;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.filesource.FileSource;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.util.DetectHtml;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmddatacompliance.activities.AcceptActivity;
import com.wbmd.wbmddatacompliance.gdpr.GDPRState;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;
import net.bytebuddy.description.type.TypeDescription;
import net.media.android.bidder.base.ConsentStatus;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.SubjectToGDPR;
import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
    public static final String AD_DEFAULT_NAV_BAR_BUTTON_TITLE = "Close";
    public static final String AD_DEFAULT_NAV_BAR_COLOR = "dcdcdc";
    public static final String AD_DEFAULT_NAV_BAR_TITLE = "Advertisement";
    public static final int HIDE_AD = 52;
    private static final int SCR_LANSCAPE = 1;
    public static final int SCR_PORTRAIT = 0;
    public static final int START_AUTOHIDE_TIMER = 51;
    public static int TIMEOUT = 60000;
    public static final int TYPE_CALCULATOR = 2;
    public static boolean isFullScreenAd = false;

    public static void addZoomControl(WebView webView) {
        if (webView == null) {
        }
    }

    public static boolean isHoneyCombOrHigher() {
        return true;
    }

    public static boolean isLetter(char c) {
        if (c < 'A' || c > 'Z') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    public static boolean isSDCardAvailable() {
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        r1 = (r1 = (android.net.ConnectivityManager) r1.getSystemService("connectivity")).getActiveNetworkInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isOnline(android.content.Context r1) {
        /*
            if (r1 == 0) goto L_0x001a
            java.lang.String r0 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r0)
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1
            if (r1 == 0) goto L_0x001a
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()
            if (r1 == 0) goto L_0x001a
            boolean r1 = r1.isConnectedOrConnecting()
            if (r1 == 0) goto L_0x001a
            r1 = 1
            return r1
        L_0x001a:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.Util.isOnline(android.content.Context):boolean");
    }

    public static boolean isAdBlockerInstalled() {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/etc/hosts")));
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return false;
                }
            } while (!readLine.contains("bi.medscape.com"));
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int getDisplayOrientation(Context context) {
        try {
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            if (defaultDisplay.getWidth() > defaultDisplay.getHeight()) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getDisplayHeight(Context context) {
        try {
            return ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getDisplayWidth(Context context) {
        try {
            return ((Activity) context).getWindowManager().getDefaultDisplay().getWidth();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getDisplayWidthInDp(Context context) {
        try {
            return (int) (((float) ((Activity) context).getWindowManager().getDefaultDisplay().getWidth()) / getScreenDensity(context));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getDisplayHeightInDp(Context context) {
        try {
            return (int) (((float) ((Activity) context).getWindowManager().getDefaultDisplay().getHeight()) / getScreenDensity(context));
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static boolean isPhone() {
        return MedscapeApplication.get().getResources().getBoolean(R.bool.isPhone);
    }

    public static boolean isTestDriveTimeFinished(Context context) {
        return Settings.singleton(context).getSetting(Constants.PREF_TEST_DRIVE_DONE, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    public static boolean isTestDriveTimeSet(Context context) {
        return Settings.singleton(context).getSetting(Constants.PREF_TEST_DRIVE_TIME, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    public static CharSequence getLastUpdateDate(Context context) {
        return Settings.singleton(context).getSetting(Constants.PREF_LAST_UPDATE_DATE, "");
    }

    public static void setLastUpdateTime(Context context) {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(7);
        StringBuilder sb = new StringBuilder();
        switch (i) {
            case 1:
                sb.append("Sun, ");
                break;
            case 2:
                sb.append("Mon, ");
                break;
            case 3:
                sb.append("Tue, ");
                break;
            case 4:
                sb.append("Wed, ");
                break;
            case 5:
                sb.append("Thu, ");
                break;
            case 6:
                sb.append("Fri, ");
                break;
            case 7:
                sb.append("Sat, ");
                break;
        }
        switch (instance.get(2)) {
            case 0:
                sb.append("Jan ");
                break;
            case 1:
                sb.append("Feb ");
                break;
            case 2:
                sb.append("Mar ");
                break;
            case 3:
                sb.append("Apr ");
                break;
            case 4:
                sb.append("May ");
                break;
            case 5:
                sb.append("Jun ");
                break;
            case 6:
                sb.append("Jul ");
                break;
            case 7:
                sb.append("Aug ");
                break;
            case 8:
                sb.append("Sep ");
                break;
            case 9:
                sb.append("Oct ");
                break;
            case 10:
                sb.append("Nov ");
                break;
            case 11:
                sb.append("Dec ");
                break;
        }
        sb.append(instance.get(5));
        sb.append(", ");
        sb.append(instance.get(1));
        Settings.singleton(context).saveSetting(Constants.PREF_LAST_UPDATE_DATE, sb.toString());
    }

    public static int selectRandomNumber(int i) {
        if (i == 1) {
            return 0;
        }
        return new Random().nextInt(i);
    }

    public static String getApplicationCode(Context context) {
        try {
            ComponentName componentName = new ComponentName(context, UpdateReferenceMainActivity.class);
            return context.getPackageManager().getPackageInfo(componentName.getPackageName(), 0).versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getApplicationVersion(Context context) {
        String str = "";
        try {
            str = context.getPackageManager().getPackageInfo(new ComponentName(context, UpdateReferenceMainActivity.class).getPackageName(), 0).versionName;
            com.wbmd.wbmdcommons.utils.Util.setAppApplicationVersion(str);
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String getPhoneModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isEmulator() {
        return Build.MANUFACTURER != null && Build.MANUFACTURER.toLowerCase().contains("genymotion");
    }

    public static String getClinicalRefVersion(Context context) {
        return Settings.singleton(context).getSetting(Constants.PREF_CLINICAL_REFERENCE_VERSION, "-1");
    }

    public static String addUserAgent(WebView webView, Context context) {
        String userAgentString = webView.getSettings().getUserAgentString();
        if (userAgentString.contains("Medscape")) {
            return userAgentString;
        }
        return userAgentString + Constants.USER_AGENT_APP_NAME + getApplicationVersion(context) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    }

    public static String addMobileWebviewUserAgentString(String str) {
        return str + " Mobile Safari";
    }

    public static boolean isEmailConfigured(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static void openExternalApp(final Context context, Uri uri) {
        final ExternalAppParams externalAppParams = new ExternalAppParams();
        externalAppParams.setActionString(uri.getQueryParameter("action"));
        externalAppParams.setTargetUri(uri.getHost());
        externalAppParams.setButtonText(uri.getQueryParameter("button"));
        externalAppParams.setMarketUri(uri.getQueryParameter("link"));
        externalAppParams.setPackagerId(uri.getQueryParameter("packageid"));
        externalAppParams.setMessage(uri.getQueryParameter(ShareConstants.WEB_DIALOG_PARAM_MESSAGE));
        if (externalAppParams.getTargetUri() != null && !externalAppParams.getTargetUri().equals("")) {
            Intent externalAppIntent = externalAppIntent(context, externalAppParams);
            if (isAppExists(context, externalAppIntent)) {
                context.startActivity(externalAppIntent);
            } else if (externalAppParams.getMarketUri() != null && !externalAppParams.getMarketUri().equals("")) {
                String message = externalAppParams.getMessage();
                String buttonText = (externalAppParams.getButtonText() == null || externalAppParams.getButtonText().equals("")) ? "Download" : externalAppParams.getButtonText();
                if (message == null || message.equals("")) {
                    openAppStore(context, externalAppParams.getMarketUri());
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.BlackTextDialog);
                builder.setMessage(message).setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Util.openAppStore(context, externalAppParams.getMarketUri());
                    }
                });
                builder.show();
            }
        }
    }

    public static void openInExternalBrowser(Context context, String str) {
        if (!str.startsWith("http://") && !str.startsWith("https://")) {
            str = "http://" + str;
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    public static void handleMailToWithExternalApp(Context context, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/html");
        if (str != null && str.startsWith(MailTo.MAILTO_SCHEME)) {
            try {
                android.net.MailTo parse = android.net.MailTo.parse(str);
                if (parse != null) {
                    if (parse.getTo() != null) {
                        intent.putExtra("android.intent.extra.EMAIL", new String[]{parse.getTo()});
                    }
                    if (parse.getSubject() != null) {
                        intent.putExtra("android.intent.extra.SUBJECT", parse.getSubject());
                    }
                    if (parse.getCc() != null) {
                        intent.putExtra("android.intent.extra.CC", new String[]{parse.getCc()});
                    }
                    if (parse.getHeaders().get("bcc") != null) {
                        intent.putExtra("android.intent.extra.BCC", new String[]{parse.getHeaders().get("bcc")});
                    }
                    if (parse.getBody() != null) {
                        intent.putExtra("android.intent.extra.TEXT", Html.fromHtml(parse.getBody()));
                    }
                }
            } catch (Exception unused) {
            }
        }
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.alert_email_chooser_message)));
    }

    /* access modifiers changed from: private */
    public static void openAppStore(Context context, String str) {
        if (str != null && !str.equals("")) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    private static boolean isAppExists(Context context, Intent intent) {
        if (intent != null && context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
            return true;
        }
        return false;
    }

    private static Intent externalAppIntent(Context context, ExternalAppParams externalAppParams) {
        String packagerId = externalAppParams.getPackagerId();
        String actionString = externalAppParams.getActionString();
        String targetUri = externalAppParams.getTargetUri();
        if (packagerId != null && !packagerId.equals("")) {
            new Intent("android.intent.action.MAIN");
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packagerId);
            if (launchIntentForPackage == null) {
                return launchIntentForPackage;
            }
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            return launchIntentForPackage;
        } else if (actionString == null || actionString.equals("")) {
            return new Intent("android.intent.action.VIEW", Uri.parse(targetUri));
        } else {
            return new Intent(actionString, Uri.parse(targetUri));
        }
    }

    public static void setCookie(Context context) {
        try {
            CookieSyncManager.createInstance(context);
            CookieManager instance = CookieManager.getInstance();
            String[] strArr = {"domain=medscape.com;", "domain=medscape.org;"};
            String[] strArr2 = {"medscape.com", "medscape.org"};
            for (String str : Settings.singleton(context).getSetting(Constants.PREF_COOKIE_STRING, "").split(";\\s*")) {
                for (int i = 0; i < 2; i++) {
                    instance.setCookie(strArr2[i], str + "; " + strArr[i]);
                }
            }
            CookieSyncManager.getInstance().sync();
        } catch (Throwable unused) {
        }
    }

    public static String attachHttpsToUrl(String str) {
        if (StringUtil.isNullOrEmpty(str)) {
            return str;
        }
        if (str.toLowerCase().startsWith("https://") || str.toLowerCase().startsWith("http://")) {
            return str.startsWith("http://") ? str.replace("http://", "https://") : str;
        }
        return "https://" + str;
    }

    public static float dpToPixel(Context context, int i) {
        return context != null ? TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics()) : (float) i;
    }

    public float dpToPx(Context context, int i) {
        return dpToPixel(context, i);
    }

    public static int pixelToDP(Context context, int i) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) Math.ceil((double) (((float) i) * displayMetrics.density));
    }

    public static double distance(double d, double d2, double d3, double d4) {
        return Math.sqrt(Math.pow(d4 - d2, 2.0d) + Math.pow(d3 - d, 2.0d));
    }

    public static String MD5(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.w("Util", e.getMessage());
            return "";
        }
    }

    public static String getQueryparamString(String str, String str2) {
        StringBuilder sb;
        String str3;
        if (str.contains(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
            sb = new StringBuilder();
            str3 = "&isEditorial=";
        } else {
            sb = new StringBuilder();
            str3 = "?isEditorial=";
        }
        sb.append(str3);
        sb.append(str2);
        return sb.toString();
    }

    public static String getBuildDate() {
        return new Date(BuildConfig.BUILD_TIMESTAMP).toString();
    }

    public static String getInstallDate(Context context) {
        try {
            return SimpleDateFormat.getInstance().format(new Date(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime));
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isNotificationChannelEnabled() {
        return NotificationManagerCompat.from(MedscapeApplication.get()).areNotificationsEnabled();
    }

    public static String getUrlFromUrlWithMap(String str, Map<String, String> map) {
        String str2;
        if (str == null || str.equalsIgnoreCase("")) {
            return null;
        }
        HashMap hashMap = new HashMap();
        String[] split = str.split("\\?");
        if (split.length < 1) {
            return null;
        }
        if (split.length > 1) {
            for (String split2 : split[1].split("&")) {
                String[] split3 = split2.split("=");
                if (split3.length == 2) {
                    hashMap.put(split3[0], split3[1]);
                }
            }
        }
        hashMap.putAll(map);
        String str3 = split[0];
        if (str3 == null) {
            return str3;
        }
        String[] split4 = str3.split("#");
        if (split4.length < 1) {
            return null;
        }
        String str4 = split4[0];
        boolean z = true;
        for (String str5 : hashMap.keySet()) {
            if (z) {
                str2 = str4 + TypeDescription.Generic.OfWildcardType.SYMBOL;
                z = false;
            } else {
                str2 = str4 + "&";
            }
            str4 = str2 + str5 + "=" + ((String) hashMap.get(str5));
        }
        if (split4.length <= 1) {
            return str4;
        }
        return str4 + "#" + split4[1];
    }

    public static void showKeyboard(Context context) {
        Object systemService;
        InputMethodManager inputMethodManager;
        if (context != null && context.getApplicationContext() != null && (systemService = context.getApplicationContext().getApplicationContext().getSystemService("input_method")) != null && (systemService instanceof InputMethodManager) && (inputMethodManager = (InputMethodManager) systemService) != null) {
            inputMethodManager.toggleSoftInput(2, 0);
        }
    }

    public static void hideKeyboard(Activity activity) {
        Object systemService;
        InputMethodManager inputMethodManager;
        if (activity != null && (systemService = MedscapeApplication.get().getSystemService("input_method")) != null && (systemService instanceof InputMethodManager) && (inputMethodManager = (InputMethodManager) systemService) != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static float getScreenDensity(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (defaultDisplay != null) {
            defaultDisplay.getMetrics(displayMetrics);
        }
        return displayMetrics.density;
    }

    public static boolean isUSuser(Context context) {
        MetricsUserProfile metricsUserProfile;
        UserProfile userProfile = UserProfileProvider.INSTANCE.getUserProfile();
        if (userProfile != null && userProfile.getCountryId() != null && userProfile.getCountryId().equalsIgnoreCase("us")) {
            return true;
        }
        if ((userProfile == null || userProfile.getCountryId() == null) && (metricsUserProfile = UserProfileProvider.INSTANCE.getMetricsUserProfile(context)) != null && metricsUserProfile.getCountryId() != null && metricsUserProfile.getCountryId().equalsIgnoreCase("us")) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(CharSequence charSequence) {
        return StringUtil.isNotEmpty((String) charSequence) && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    public static boolean isValidUSMobile(String str) {
        return str.length() == 10 && Patterns.PHONE.matcher(str).matches();
    }

    public static void adjustAlertDialogueButtonSize(final AlertDialog alertDialog) {
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                AlertDialog alertDialog = alertDialog;
                if (alertDialog != null) {
                    Button button = alertDialog.getButton(-1);
                    Button button2 = alertDialog.getButton(-2);
                    if (button != null) {
                        button.setTextSize(1, 14.0f);
                    }
                    if (button2 != null) {
                        button2.setTextSize(1, 14.0f);
                    }
                }
            }
        });
    }

    public static int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static long convertStringToLong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static void saveStringToSharedPreference(Context context, String str, String str2) {
        if (context != null && StringUtil.isNotEmpty(str2)) {
            context.getSharedPreferences(str2, 0).edit().putString(str2, str).apply();
        }
    }

    public static String getStringFromSharedPreference(Context context, String str) {
        if (context == null || StringUtil.isNullOrEmpty(str)) {
            return "";
        }
        return context.getSharedPreferences(str, 0).getString(str, "");
    }

    public static Intent getGDPRRoadBlock(Context context) {
        Intent intent = new Intent(context, AcceptActivity.class);
        intent.putExtra(com.wbmd.wbmddatacompliance.utils.Constants.EXTRA_APPLICATION_TYPE, com.wbmd.wbmddatacompliance.utils.Constants.APPLICATION_TYPE_PROFESSIONAL);
        return intent;
    }

    public static void showViewAnimation(Context context, View view) {
        Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.anim_show);
        view.setVisibility(0);
        view.startAnimation(loadAnimation);
    }

    public static void hideViewAnimation(Context context, View view) {
        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_hide));
        view.setVisibility(8);
    }

    public static void setContainerRule(boolean z, View view, int i) {
        int i2 = z ? 350 : 0;
        if (view != null) {
            view.postDelayed(new Runnable(z, i, view) {
                public final /* synthetic */ boolean f$0;
                public final /* synthetic */ int f$1;
                public final /* synthetic */ View f$2;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    Util.lambda$setContainerRule$0(this.f$0, this.f$1, this.f$2);
                }
            }, (long) i2);
        }
    }

    static /* synthetic */ void lambda$setContainerRule$0(boolean z, int i, View view) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        if (z) {
            layoutParams.addRule(2, i);
        }
        layoutParams.addRule(10);
        view.setLayoutParams(layoutParams);
    }

    public static boolean objectEquals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return Objects.equals(obj, obj2);
        }
        return obj.equals(obj2);
    }

    public static String convertOrAppendHttps(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return str;
        }
        if (str.startsWith("http://")) {
            return str.replace("http://", "https://");
        }
        if (str.startsWith("https://")) {
            return str;
        }
        return "https://" + str;
    }

    public static String getUserSpecialityIDKey(Context context) {
        if (context == null) {
            return "";
        }
        return "wbmd.medscape.user_selected_specialty.id" + AuthenticationManager.getInstance(context).getMaskedGuid();
    }

    public static String getPageNumberFromUrl(String str) {
        if (!StringUtil.isNotEmpty(str) || !str.contains("#")) {
            return null;
        }
        String[] split = str.split("#");
        if (split.length > 1) {
            return split[1];
        }
        return null;
    }

    public static String getUrlWithoutPageNumber(String str) {
        return (!StringUtil.isNotEmpty(str) || !str.contains("#")) ? str : str.split("#")[0];
    }

    public static int[] updateMediaNetGDPRStatus(GDPRState gDPRState) {
        SubjectToGDPR subjectToGDPR = SubjectToGDPR.UNKNOWN;
        ConsentStatus consentStatus = ConsentStatus.UNKNOWN;
        if (gDPRState != null) {
            if (gDPRState.isAccepted()) {
                subjectToGDPR = SubjectToGDPR.ENABLED;
                consentStatus = ConsentStatus.GIVEN;
            } else if (gDPRState.isGeoCodeEU()) {
                subjectToGDPR = SubjectToGDPR.ENABLED;
            } else {
                subjectToGDPR = SubjectToGDPR.DISABLED;
            }
        }
        Log.d("MediaNet", "GDPR set");
        MNet.updateGdprConsent(subjectToGDPR, consentStatus, "");
        return new int[]{subjectToGDPR.value(), consentStatus.value()};
    }

    public static boolean isClinicalAdvancesItem(FeedDataItem feedDataItem) {
        if (feedDataItem == null || feedDataItem.getType() == null) {
            return false;
        }
        return feedDataItem.getType().equalsIgnoreCase(FeedConstants.CLINICAL_ADVANCES_ITEM);
    }

    public static boolean isDataUpdatesItem(FeedDataItem feedDataItem) {
        if (feedDataItem == null || feedDataItem.getType() == null) {
            return false;
        }
        return feedDataItem.getType().equalsIgnoreCase(FeedConstants.DATA_UPDATE_ITEM);
    }

    public static boolean isLiveEventsItem(FeedDataItem feedDataItem) {
        if (feedDataItem == null || feedDataItem.getType() == null) {
            return false;
        }
        return feedDataItem.getType().equalsIgnoreCase(FeedConstants.LIVE_EVENTS_ITEM);
    }

    public static void onLoginSuccess(final Activity activity, String str, int i, LoginCompletedCallback loginCompletedCallback) {
        int parseResponse = StringUtil.isNotEmpty(str) ? OAuthResponseParser.parseResponse(activity, com.webmd.wbmdproffesionalauthentication.utilities.Util.getJsonObjectFromResponse(str), activity) : 3008;
        if (parseResponse == 3010) {
            new LogoutHandler().handleLogout(activity);
            return;
        }
        sendFirebaseEvent(activity, i);
        updateCurrentSpeciality(activity);
        if (activity != null) {
            AuthenticationManager.getInstance(activity).setAuthStatus(parseResponse);
        }
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                new ForceUpManager().initializeForceup((AppCompatActivity) activity);
            }
        }, 2000);
        if (loginCompletedCallback != null) {
            loginCompletedCallback.onLoginComplete();
        }
    }

    private static void sendFirebaseEvent(Activity activity, int i) {
        FirebaseConversionEventHandler.logFirebaseConversionEvent(i, activity);
    }

    public static void updateAuthStatus(Context context, boolean z) {
        int i = z ? Constants.AUTHENTICATION_STATUS_ACCEPTED : 3008;
        if (context != null) {
            AuthenticationManager.getInstance(context).setAuthStatus(i);
        }
    }

    private static void updateCurrentSpeciality(Activity activity) {
        if (activity != null) {
            String userSpecialityIDKey = UserProfileProvider.INSTANCE.getUserSpecialityIDKey(activity);
            if (StringUtil.isNullOrEmpty(Settings.singleton(activity).getSetting(userSpecialityIDKey, ""))) {
                Settings.singleton(activity).saveSetting(userSpecialityIDKey, Settings.singleton(activity).getSetting(Constants.HOME_PAGE_ID, ""));
            }
        }
    }

    public static void resetForceupAndAuthFlags(Activity activity) {
        com.ib.foreceup.util.Util.saveForceupDonePref(activity, false);
        AuthenticationManager.getInstance(activity).setAuthStatus(3008);
    }

    public static int getFolderIdForProcedures(Activity activity) {
        int i = -1;
        if (activity != null) {
            try {
                DatabaseHelper databaseHelper = new DatabaseHelper(activity);
                Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT FolderID FROM tblClinicalReferenceClass  WHERE SegmentName ='Clinical Procedures'", (String[]) null);
                while (rawQuery.moveToNext()) {
                    i = rawQuery.getInt(rawQuery.getColumnIndex("FolderID"));
                }
                rawQuery.close();
                databaseHelper.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static boolean findMatchingQxCalcForMedscapeCalc(Context context, Object obj, LaunchQxCallback launchQxCallback) {
        boolean z;
        String str;
        if (!(context == null || obj == null)) {
            try {
                if (obj instanceof CalcArticle) {
                    str = ((CalcArticle) obj).getCalcId();
                    z = ((CalcArticle) obj).isSaved();
                } else if (!(obj instanceof String)) {
                    return false;
                } else {
                    str = (String) obj;
                    z = false;
                }
                if (str != null && !str.isEmpty()) {
                    if (!str.contains(ContentParser.CALCULATOR) || !isCurrentCalcAQxCalc(str)) {
                        JSONArray jSONArray = getJSONArray(context);
                        if (jSONArray != null) {
                            for (int i = 0; i < jSONArray.length(); i++) {
                                JSONObject jSONObject = jSONArray.getJSONObject(i);
                                String string = jSONObject.getString("medscape_id");
                                String string2 = jSONObject.getJSONObject("qx_calc").getString("id");
                                if (string.equalsIgnoreCase(str)) {
                                    openCalculator(context, string2, launchQxCallback, z);
                                    return true;
                                }
                            }
                        }
                    } else {
                        ContentItemLaunchManager.getInstance().launchContentItem(ContentDataManager.getInstance().getContentItemForIdentifier(str), (FragmentActivity) context, launchQxCallback, (Intent) null);
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static void openCalculator(Context context, String str, LaunchQxCallback launchQxCallback, boolean z) {
        DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(str);
        if (z) {
            contentItemForIdentifier.setIsFavorite(true);
            contentItemForIdentifier.update();
        }
        ContentItemLaunchManager.getInstance().launchContentItem(contentItemForIdentifier, (FragmentActivity) context, launchQxCallback, (Intent) null);
    }

    public static JSONArray getJSONArray(Context context) {
        try {
            return new JSONParser().getJSONFromInputStream(context.getResources().getAssets().open("calculate_medscape_list.json")).getJSONArray("data");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCalculatorId(Context context, String str, boolean z) {
        try {
            JSONArray jSONArray = getJSONArray(context);
            if (jSONArray == null) {
                return "";
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("medscape_id");
                String string2 = jSONObject.getJSONObject("qx_calc").getString("id");
                if (z) {
                    if (str.equalsIgnoreCase(string2)) {
                        return string;
                    }
                } else if (str.equalsIgnoreCase(string)) {
                    return string2;
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean isCurrentCalcAQxCalc(String str) {
        return (str == null || ContentDataManager.getInstance().getContentItemForIdentifier(str) == null) ? false : true;
    }

    public static void showNoCalculatorAvailable(Activity activity, View view) {
        MedscapeException medscapeException = new MedscapeException(activity.getString(R.string.this_calculator_isnt_available_at_this_time));
        medscapeException.showSnackBar(view, -2, activity.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new View.OnClickListener() {
            public final void onClick(View view) {
                MedscapeException.this.dismissSnackBar();
            }
        });
    }

    public static String getMLinkForOmniture(DBContentItem dBContentItem) {
        if (dBContentItem != null) {
            String parentCalcName = dBContentItem.getParentCalcName();
            if (parentCalcName != null) {
                return getFirstTwoLettersOfEachWords(parentCalcName);
            }
            if (dBContentItem.getCategories() != null && dBContentItem.getCategories().size() > 0) {
                return getFirstTwoLettersOfEachWords(dBContentItem.getCategories().get(0).getName());
            }
        }
        return "";
    }

    public static String getCalcPageNameForOmniture(Context context, Object obj) {
        String str;
        String str2 = "";
        if (obj == null) {
            return str2;
        }
        boolean z = false;
        if (obj instanceof DBContentItem) {
            DBContentItem dBContentItem = (DBContentItem) obj;
            str2 = dBContentItem.getIdentifier();
            str = dBContentItem.getName();
            z = true;
        } else if (obj instanceof CalcArticle) {
            CalcArticle calcArticle = (CalcArticle) obj;
            str2 = calcArticle.getCalcId();
            str = calcArticle.getTitle();
        } else {
            if (obj instanceof String) {
                str2 = (String) obj;
            }
            str = str2;
        }
        String calculatorId = getCalculatorId(context, str2, z);
        if (!calculatorId.isEmpty()) {
            str = calculatorId;
        }
        return "calc/view/" + getCalcPageName(str);
    }

    public static String getCalcPageName(String str) {
        if (StringUtil.isNotEmpty(str)) {
            return str.trim().toLowerCase().replaceAll("[^a-zA-z0-9']+", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).trim().replace("'", "").replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-").trim();
        }
        return "";
    }

    public static String getFirstTwoLettersOfEachWords(String str) {
        if (str != null) {
            String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (split.length > 1) {
                if (isValidLength(split[0]) && isValidLength(split[1])) {
                    return getSubStringForOmniture(split[0]) + getSubStringForOmniture(split[1]);
                } else if (isValidLength(split[0]) && !isValidLength(split[1])) {
                    return getSubStringForOmniture(split[0]) + split[1].toLowerCase();
                } else if (isValidLength(split[0]) || !isValidLength(split[1])) {
                    return (split[0] + split[1]).toLowerCase();
                } else {
                    return split[0].toLowerCase() + getSubStringForOmniture(split[1]);
                }
            } else if (split.length == 1) {
                if (isValidLength(split[0])) {
                    return getSubStringForOmniture(split[0]);
                }
                return split[0].toLowerCase();
            }
        }
        return "";
    }

    private static boolean isValidLength(String str) {
        return str.length() >= 2;
    }

    private static String getSubStringForOmniture(String str) {
        return str.substring(0, 2).toLowerCase();
    }

    public static CalcArticle getCalcArticleFromContentItem(Context context, ContentItem contentItem) {
        if (contentItem == null) {
            return null;
        }
        CalcArticle calcArticle = new CalcArticle();
        String calculatorId = getCalculatorId(context, contentItem.identifier, true);
        if (isCalculatorSaved(context, calculatorId)) {
            calcArticle.setCalcId(calculatorId);
        } else {
            calcArticle.setCalcId(contentItem.identifier);
        }
        calcArticle.setTitle(contentItem.name);
        calcArticle.setType(2);
        if (contentItem.isFavorite != null) {
            calcArticle.setSaved(contentItem.isFavorite.booleanValue());
            return calcArticle;
        }
        calcArticle.setSaved(false);
        return calcArticle;
    }

    private static boolean isCalculatorSaved(Context context, String str) {
        Iterator<CalcArticle> it = getOldSavedCalculators(context).iterator();
        while (it.hasNext()) {
            if (it.next().getCalcId().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasHTMLContent(String str) {
        Pattern compile = Pattern.compile("(" + DetectHtml.tagStart + ".*" + DetectHtml.tagEnd + ")|(" + DetectHtml.tagSelfClosing + ")|(" + DetectHtml.htmlEntity + ")", 32);
        if (StringUtil.isNotEmpty(str)) {
            return compile.matcher(str).find();
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005d, code lost:
        if (r1 != null) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0066, code lost:
        if (r1 == null) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0068, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006b, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<com.medscape.android.activity.calc.model.CalcArticle> getOldSavedCalculators(android.content.Context r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            com.medscape.android.MedscapeApplication r2 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x0062 }
            com.medscape.android.auth.AuthenticationManager r2 = com.medscape.android.auth.AuthenticationManager.getInstance(r2)     // Catch:{ Exception -> 0x0062 }
            java.lang.String r2 = r2.getMaskedGuid()     // Catch:{ Exception -> 0x0062 }
            boolean r3 = com.medscape.android.util.StringUtil.isNotEmpty(r2)     // Catch:{ Exception -> 0x0062 }
            if (r3 == 0) goto L_0x005d
            if (r10 == 0) goto L_0x005d
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch:{ Exception -> 0x0062 }
            android.net.Uri r5 = com.medscape.android.activity.calc.model.CalcArticle.CalcArticles.CONTENT_URI     // Catch:{ Exception -> 0x0062 }
            r6 = 0
            java.lang.String r7 = "userGuid='' OR (isSaved=? AND userGuid = ?)"
            r10 = 2
            java.lang.String[] r8 = new java.lang.String[r10]     // Catch:{ Exception -> 0x0062 }
            r3 = 0
            java.lang.String r9 = "1"
            r8[r3] = r9     // Catch:{ Exception -> 0x0062 }
            r3 = 1
            r8[r3] = r2     // Catch:{ Exception -> 0x0062 }
            r9 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0062 }
            if (r1 == 0) goto L_0x005d
        L_0x0035:
            boolean r2 = r1.moveToNext()     // Catch:{ Exception -> 0x0062 }
            if (r2 == 0) goto L_0x005d
            com.medscape.android.activity.calc.model.CalcArticle r2 = new com.medscape.android.activity.calc.model.CalcArticle     // Catch:{ Exception -> 0x0062 }
            r2.<init>()     // Catch:{ Exception -> 0x0062 }
            java.lang.String r4 = r1.getString(r3)     // Catch:{ Exception -> 0x0062 }
            r2.setTitle(r4)     // Catch:{ Exception -> 0x0062 }
            r2.setSaved(r3)     // Catch:{ Exception -> 0x0062 }
            java.lang.String r4 = r1.getString(r10)     // Catch:{ Exception -> 0x0062 }
            r2.setCalcId(r4)     // Catch:{ Exception -> 0x0062 }
            r4 = 3
            int r4 = r1.getInt(r4)     // Catch:{ Exception -> 0x0062 }
            r2.setType(r4)     // Catch:{ Exception -> 0x0062 }
            r0.add(r2)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0035
        L_0x005d:
            if (r1 == 0) goto L_0x006b
            goto L_0x0068
        L_0x0060:
            r10 = move-exception
            goto L_0x006c
        L_0x0062:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x0060 }
            if (r1 == 0) goto L_0x006b
        L_0x0068:
            r1.close()
        L_0x006b:
            return r0
        L_0x006c:
            if (r1 == 0) goto L_0x0071
            r1.close()
        L_0x0071:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.Util.getOldSavedCalculators(android.content.Context):java.util.ArrayList");
    }

    public static void openQxItem(Activity activity, DBContentItem dBContentItem, Bundle bundle) {
        if (activity != null && dBContentItem != null) {
            String type = dBContentItem.getType();
            char c = 65535;
            switch (type.hashCode()) {
                case -1019220188:
                    if (type.equals(ContentItem.CONTENT_ITEM_TYPE_CALCULATOR)) {
                        c = 0;
                        break;
                    }
                    break;
                case -895108747:
                    if (type.equals(ContentItem.CONTENT_ITEM_TYPE_DEFINITION)) {
                        c = 3;
                        break;
                    }
                    break;
                case -276425057:
                    if (type.equals(ContentItem.CONTENT_ITEM_TYPE_REFERENCE_BOOK)) {
                        c = 1;
                        break;
                    }
                    break;
                case -214961060:
                    if (type.equals(ContentItem.CONTENT_ITEM_TYPE_FILE_SOURCE)) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                Intent intent = new Intent(activity, MedscapeCalculatorActivity.class);
                intent.putExtras(bundle);
                activity.startActivity(intent);
            } else if (c == 1) {
                Intent intent2 = new Intent(activity, MedscapeReferenceBookActivity.class);
                intent2.putExtras(bundle);
                activity.startActivity(intent2);
            } else if (c == 2) {
                FileSource.FileSourceType fileSourceType = FileSource.getFileSourceType(dBContentItem.getFileSource().getType());
                if (fileSourceType == FileSource.FileSourceType.PDF_EXT || fileSourceType == FileSource.FileSourceType.PDF_INT) {
                    Intent intent3 = new Intent(activity, MedscapePdfViewerActivity.class);
                    intent3.putExtras(bundle);
                    activity.startActivity(intent3);
                } else if (fileSourceType == FileSource.FileSourceType.HTML_EXT) {
                    Intent intent4 = new Intent(activity, MedscapeFileSourceHtmlActivity.class);
                    intent4.putExtras(bundle);
                    activity.startActivity(intent4);
                }
            } else if (c == 3) {
                Intent intent5 = new Intent(activity, MedscapeDefinitionActivity.class);
                intent5.putExtras(bundle);
                activity.startActivity(intent5);
            }
        }
    }

    public static void sendFirebaseContentInfo(Activity activity, String str, String str2) {
        PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(activity, false, true);
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("type", str);
        }
        if (str2 != null) {
            bundle.putString(OmnitureConstants.OMNITURE_LINK_EVENT_INFO, str2);
        }
        platformRouteDispatcher.routeEvent(FirebaseEventsConstants.CONTENT_INFO, bundle);
    }
}
