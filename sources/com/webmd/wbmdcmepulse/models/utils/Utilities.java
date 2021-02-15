package com.webmd.wbmdcmepulse.models.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.material.snackbar.Snackbar;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.providers.SharedPreferencesSettings;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import net.bytebuddy.description.type.TypeDescription;
import org.apache.commons.io.IOUtils;
import org.unbescape.html.HtmlEscape;

public class Utilities {
    private static final String HTML_PATTERN = "<(\"[^\"]*\"|'[^']*'|[^'\">])*>";
    private static final String TAG = "Utilities";

    public static boolean isPhone() {
        return true;
    }

    public static Spanned getFormattedText(String str) {
        Pattern compile = Pattern.compile(HTML_PATTERN);
        String unescapeHtml = HtmlEscape.unescapeHtml(str);
        if (compile.matcher(unescapeHtml).find()) {
            return Html.fromHtml(unescapeHtml);
        }
        return new SpannableString(unescapeHtml);
    }

    public static void saveTopicKeysToSharedPreferences(List<String> list, String str, Context context) {
        StringBuilder sb = new StringBuilder();
        for (String str2 : list) {
            sb.append(str2 + ",");
        }
        try {
            SharedPreferenceProvider.get().saveCryptoEncrypted(str, sb.toString().substring(0, sb.toString().length() - 1), context);
        } catch (Exception e) {
            Trace.w(TAG, e.getLocalizedMessage());
        }
    }

    public static List<String> getSharedPreferecesValues(String str, String str2, Context context) {
        List arrayList = new ArrayList();
        try {
            String simpleCryptoDecryptedString = SharedPreferenceProvider.get().getSimpleCryptoDecryptedString(str, "", context);
            if (!Extensions.isStringNullOrEmpty(simpleCryptoDecryptedString)) {
                arrayList = Arrays.asList(simpleCryptoDecryptedString.split(str2));
            }
        } catch (Exception e) {
            Trace.e(TAG, "Error getting list from shared prefs" + e.getMessage());
        }
        return new ArrayList(arrayList);
    }

    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object deserialize(byte[] bArr) {
        try {
            return new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (Exception unused) {
            return null;
        }
    }

    public static void disableMenuButtons(Menu menu) {
        if (menu != null) {
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                try {
                    menu.getItem(i).setEnabled(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        Object systemService = context.getSystemService("connectivity");
        if (!(systemService instanceof ConnectivityManager)) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return false;
        }
        return true;
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
        if (activity != null && (systemService = activity.getSystemService("input_method")) != null && (systemService instanceof InputMethodManager) && (inputMethodManager = (InputMethodManager) systemService) != null && activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static String readRawTextFile(Context context, int i) {
        Resources resources;
        if (context == null || (resources = context.getResources()) == null) {
            return "";
        }
        try {
            InputStream openRawResource = resources.openRawResource(i);
            if (openRawResource == null) {
                return "";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource));
            StringBuilder sb = new StringBuilder();
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        return sb.toString();
                    }
                    sb.append(readLine);
                } catch (IOException unused) {
                    return "";
                }
            }
        } catch (Exception unused2) {
            Trace.w("readRawTextFile", "Failed to read text file: " + i);
            return "";
        }
    }

    public static Snackbar buildSnackBar(View view, int i, String str, String str2, View.OnClickListener onClickListener) {
        if (view == null || view.getContext() == null) {
            return null;
        }
        Snackbar make = Snackbar.make(view, (CharSequence) str, i);
        if (!Extensions.isStringNullOrEmpty(str2) && onClickListener != null) {
            make.setAction((CharSequence) str2, onClickListener);
        }
        View view2 = make.getView();
        ((TextView) view2.findViewById(R.id.snackbar_text)).setTextColor(-1);
        view2.setBackgroundColor(view.getResources().getColor(R.color.app_accent_color));
        return make;
    }

    public static int getStatusBarHeight(Resources resources) {
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static String getThumbnailResizedUrl(String str, String str2) {
        String str3;
        if (Extensions.isStringNullOrEmpty(str)) {
            return str;
        }
        if (str.indexOf(TypeDescription.Generic.OfWildcardType.SYMBOL) < 0) {
            str3 = str + TypeDescription.Generic.OfWildcardType.SYMBOL;
        } else {
            str3 = str + "&";
        }
        if (str3.indexOf("interpolation") < 0) {
            str3 = str3 + "interpolation=lanczos-none&";
        }
        if (str3.indexOf("resize") >= 0) {
            return str3;
        }
        return str3 + "resize=" + str2;
    }

    public static String getFormattedDateForFeed(String str) {
        try {
            return getFormattedDateForCustomFeed(Long.parseLong(str));
        } catch (Exception unused) {
            try {
                Date parse = new SimpleDateFormat("dd/MMMM/yyyy", Locale.US).parse(str);
                if (parse != null) {
                    return new SimpleDateFormat("MM/dd/yy", Locale.US).format(parse);
                }
                return "";
            } catch (Exception unused2) {
                Trace.w(TAG, "Failed to parse date");
                return "";
            }
        }
    }

    public static String getFormattedDateForCustomFeed(long j) {
        try {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j);
            return new SimpleDateFormat("MM/dd/yyyy").format(instance.getTime());
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to parse date");
            return "";
        }
    }

    public static String getFormattedDateForSearch(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return new SimpleDateFormat("MM/dd/yy").format(instance.getTime());
    }

    public static int getYearFromLong(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(1);
    }

    public static boolean isUserMocEligible(Context context, UserProfile userProfile) {
        UserProfession professionProfile;
        if (userProfile == null || (professionProfile = userProfile.getProfessionProfile()) == null) {
            return false;
        }
        return isUserMocEligibilie(context, professionProfile.getProfessionId(), professionProfile.getSpecialityId());
    }

    public static boolean isUserMocEligibilie(Context context, String str, String str2) {
        Context context2 = context;
        String medscapeSettings = str == null ? SharedPreferencesSettings.get().getMedscapeSettings("wbmd_professionId", (String) null, context2) : str;
        String medscapeSettings2 = str2 == null ? SharedPreferencesSettings.get().getMedscapeSettings("wbmd.medscape.specialty.id", (String) null, context2) : str2;
        if (medscapeSettings == null || !medscapeSettings.equals(UserProfile.PHYSICIAN_ID) || !new HashSet(Arrays.asList(new String[]{"342", "59", "332", "32", "285", "333", "344", "111", AppEventsConstants.EVENT_PARAM_VALUE_YES, ExifInterface.GPS_MEASUREMENT_2D, "39", "40", "80", "121", "76", "41", "122", "42", "43", "204", "213", "203", "51", "334", "298", "306", "65", "288", "38"})).contains(medscapeSettings2)) {
            return false;
        }
        return true;
    }

    public static int getCreditTypeFromProfessionId(String str, String str2) {
        if (str2 == null || str == null) {
            str2 = "";
        }
        if (str.equals(UserProfile.PHYSICIAN_ID)) {
            return 1;
        }
        if (str.equals(UserProfile.PHYS_ASST_ID)) {
            return 8;
        }
        if (str.equals(UserProfile.NURSE_ID) && str2.equals(UserProfile.NURSE_PRACTITIONER_ID)) {
            return 7;
        }
        if (str.equals(UserProfile.NURSE_ID) && !str2.equals(UserProfile.STUDENT_ID) && !str2.equals(UserProfile.MIDWIFE_ID)) {
            return 2;
        }
        if (str.equals(UserProfile.PHARMACIST_ID)) {
            return 3;
        }
        if (str2.equals(UserProfile.PSYCHOLOGIST_ID)) {
            return 4;
        }
        return str2.equals(UserProfile.MEDICAL_LABORATORY_ID) ? 5 : 6;
    }

    public static String getTrackerLabel(Context context) {
        return getString(R.string.activity_tracker_title, context);
    }

    public static String getString(int i, Context context) {
        return context == null ? "" : context.getString(i);
    }

    public static boolean goToHomeScreen(Context context) {
        String string = SharedPreferenceProvider.get().getString(Constants.PREF_KEY_APP_PACKAGE_NAME, "", context);
        String string2 = SharedPreferenceProvider.get().getString(Constants.PREF_KEY_HOMEACTIVITY_NAME, "", context);
        if (Extensions.isStringNullOrEmpty(string) || Extensions.isStringNullOrEmpty(string2)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(string, string2));
        intent.putExtra("feedtype", "CME");
        intent.putExtra(Constants.BUNDLE_KEY_GO_BACK_HOME, true);
        context.startActivity(intent);
        return true;
    }

    public static boolean goToLandingPAge(Context context) {
        String string = SharedPreferenceProvider.get().getString(Constants.PREF_KEY_APP_PACKAGE_NAME, "", context);
        String string2 = SharedPreferenceProvider.get().getString(Constants.PREF_KEY_LANDINGACTIVITY_NAME, "", context);
        if (Extensions.isStringNullOrEmpty(string) || Extensions.isStringNullOrEmpty(string2)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(string, string2));
        context.startActivity(intent);
        return true;
    }

    public static boolean goToActivityTracker(Context context) {
        String string = SharedPreferenceProvider.get().getString(Constants.PREF_KEY_APP_PACKAGE_NAME, "", context);
        String string2 = SharedPreferenceProvider.get().getString(Constants.PREF_KEY_CMETRACKERACTIVITY_NAME, "", context);
        if (Extensions.isStringNullOrEmpty(string) || Extensions.isStringNullOrEmpty(string2)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(string, string2));
        intent.putExtra(Constants.RETURN_ACTIVITY, Constants.HOME_ACTIVITY_NAME);
        intent.putExtra(Constants.BUNDLE_KEY_GO_BACK_HOME, true);
        intent.addFlags(67141632);
        context.startActivity(intent);
        return true;
    }

    public static String getCookieString(Context context) {
        String str = "";
        try {
            String simpleCryptoDecryptedString = SharedPreferenceProvider.get().getSimpleCryptoDecryptedString(Constants.PREF_COOKIE, str, context);
            try {
                if (Extensions.isStringNullOrEmpty(simpleCryptoDecryptedString)) {
                    return SharedPreferenceProvider.get().getString(Constants.PREF_COOKIE_STRING, str, context);
                }
                return simpleCryptoDecryptedString;
            } catch (Throwable th) {
                th = th;
                str = simpleCryptoDecryptedString;
                th.printStackTrace();
                return str;
            }
        } catch (Throwable th2) {
            th = th2;
            th.printStackTrace();
            return str;
        }
    }

    public static boolean isUserAutoRotationTurnedOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "accelerometer_rotation", 0) == 1;
    }

    public static String removeJsonFromCreditType(String str) {
        String[] split = str.substring(1, str.length() - 1).replaceAll("\"", "").split(",");
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            sb.append(str2 + ", ");
        }
        return sb.subSequence(0, sb.lastIndexOf(",")).toString();
    }

    public static String getImageUrl(String str) {
        String replace = str.replaceAll(IOUtils.LINE_SEPARATOR_UNIX, "").replace("/webmd/professional_assets/medscape/images", "https://img.medscape.com");
        return replace.startsWith("/webmd") ? replace.replace("/webmd", "") : replace;
    }

    public static String getCMEUrl(Context context, String str) {
        return generateEnvironment(context, "https://www%s.medscape.org/viewarticle/") + str;
    }

    public static String generateEnvironment(Context context, String str) {
        return String.format(str, new Object[]{""});
    }

    public static void downloadFile(Context context, View view, String str) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        String valueOf = String.valueOf(System.currentTimeMillis());
        if (str.contains("/") && !str.endsWith("/")) {
            valueOf = str.substring(str.lastIndexOf("/") + 1);
        }
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(1);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, valueOf);
        Snackbar.make(view, R.string.web_view_downloading_file, -1).show();
        ((DownloadManager) context.getSystemService("download")).enqueue(request);
    }
}
