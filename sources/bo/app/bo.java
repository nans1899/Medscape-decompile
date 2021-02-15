package bo.app;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.facebook.places.model.PlaceFields;
import com.google.firebase.messaging.Constants;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TimeZone;

public class bo implements br {
    private static final String a = AppboyLogger.getAppboyLogTag(bo.class);
    private final Context b;
    private final bs c;
    private final dh d;
    private String e;
    private final AppboyConfigurationProvider f;

    public bo(Context context, AppboyConfigurationProvider appboyConfigurationProvider, bs bsVar, dh dhVar) {
        if (context != null) {
            this.b = context;
            this.f = appboyConfigurationProvider;
            this.c = bsVar;
            this.d = dhVar;
            return;
        }
        throw null;
    }

    public ch a() {
        return new ch(this.f, f(), g(), h(), a(i()), j().getID(), a(k(), l()), Boolean.valueOf(d()));
    }

    public ch b() {
        this.d.a(a());
        return (ch) this.d.b();
    }

    public String c() {
        String a2 = this.c.a();
        if (a2 == null) {
            AppboyLogger.e(a, "Error reading deviceId, received a null value.");
        }
        return a2;
    }

    private String f() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        Object a2;
        Method a3;
        Object a4;
        if (Build.VERSION.SDK_INT >= 24) {
            NotificationManager notificationManager = (NotificationManager) this.b.getSystemService("notification");
            if (notificationManager != null) {
                return notificationManager.areNotificationsEnabled();
            }
            return true;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method a5 = eg.a("androidx.core.app.NotificationManagerCompat", Constants.MessagePayloadKeys.FROM, (Class<?>[]) new Class[]{Context.class});
                if ((a5 == null && (a5 = eg.a("androidx.core.app.NotificationManagerCompat", (String) Constants.MessagePayloadKeys.FROM, (Class<?>[]) new Class[]{Context.class})) == null) || (a2 = eg.a((Object) null, a5, this.b)) == null || (a3 = eg.a(a2.getClass(), "areNotificationsEnabled", (Class<?>[]) new Class[0])) == null || (a4 = eg.a(a2, a3, new Object[0])) == null || !(a4 instanceof Boolean)) {
                    return true;
                }
                return ((Boolean) a4).booleanValue();
            } catch (Exception e2) {
                AppboyLogger.e(a, "Failed to read notifications enabled state from NotificationManagerCompat.", e2);
            }
        }
        return true;
    }

    private String g() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.b.getSystemService(PlaceFields.PHONE);
            int phoneType = telephonyManager.getPhoneType();
            if (phoneType == 0) {
                return null;
            }
            if (phoneType == 1 || phoneType == 2) {
                return telephonyManager.getNetworkOperatorName();
            }
            AppboyLogger.w(a, "Unknown phone type");
            return null;
        } catch (Resources.NotFoundException e2) {
            AppboyLogger.e(a, "Caught resources not found exception while reading the phone carrier name.", e2);
            return null;
        } catch (SecurityException e3) {
            AppboyLogger.e(a, "Caught security exception while reading the phone carrier name.", e3);
            return null;
        }
    }

    private String h() {
        return Build.MODEL;
    }

    private Locale i() {
        return Locale.getDefault();
    }

    static String a(Locale locale) {
        return locale.toString();
    }

    private TimeZone j() {
        return TimeZone.getDefault();
    }

    private DisplayMetrics k() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private boolean l() {
        int rotation = ((WindowManager) this.b.getSystemService("window")).getDefaultDisplay().getRotation();
        return rotation == 1 || rotation == 3;
    }

    static String a(DisplayMetrics displayMetrics, boolean z) {
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (z) {
            return i2 + "x" + i;
        }
        return i + "x" + i2;
    }

    public String e() {
        PackageInfo packageInfo;
        String str = this.e;
        if (str != null) {
            return str;
        }
        String packageName = this.b.getPackageName();
        try {
            packageInfo = this.b.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            String str2 = a;
            AppboyLogger.e(str2, "Unable to inspect package [" + packageName + "]", e2);
            packageInfo = null;
        }
        if (packageInfo == null) {
            packageInfo = this.b.getPackageManager().getPackageArchiveInfo(this.b.getApplicationInfo().sourceDir, 0);
        }
        if (packageInfo != null) {
            String str3 = packageInfo.versionName;
            this.e = str3;
            return str3;
        }
        AppboyLogger.d(a, "App version could not be read. Returning null");
        return null;
    }
}
