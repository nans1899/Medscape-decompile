package bo.app;

import android.content.Context;
import com.appboy.Appboy;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import java.lang.reflect.Method;

public class bp {
    private static final String a = AppboyLogger.getAppboyLogTag(bp.class);
    private static final String[] b = {"com.google.firebase.iid.FirebaseInstanceId"};
    private final Context c;

    public bp(Context context) {
        this.c = context;
    }

    public void a(String str) {
        String b2 = b(str);
        if (!StringUtils.isNullOrEmpty(b2)) {
            Appboy.getInstance(this.c).registerAppboyPushMessages(b2);
        } else {
            AppboyLogger.w(a, "Obtained an empty or null Firebase Cloud Messaging registration token. Not registering token.");
        }
    }

    public static boolean a(Context context, AppboyConfigurationProvider appboyConfigurationProvider) {
        if (StringUtils.isNullOrEmpty(appboyConfigurationProvider.getFirebaseCloudMessagingSenderIdKey())) {
            AppboyLogger.w(a, "Firebase Cloud Messaging requires a non-null and non-empty sender ID.");
            return false;
        } else if (!dz.b(context)) {
            AppboyLogger.w(a, "Firebase Cloud Messaging requires the Google Play Store to be installed.");
            return false;
        } else {
            try {
                ClassLoader classLoader = bp.class.getClassLoader();
                for (String str : b) {
                    if (Class.forName(str, false, classLoader) == null) {
                        AppboyLogger.w(a, "Automatic registration for Firebase Cloud Messaging requires the following class to be present: " + str);
                        return false;
                    }
                }
                return true;
            } catch (Exception e) {
                AppboyLogger.e(a, "Caught error while checking for required classes for Firebase Cloud Messaging.", e);
                return false;
            }
        }
    }

    private static String b(String str) {
        try {
            Method a2 = eg.a("com.google.firebase.iid.FirebaseInstanceId", "getInstance", (Class<?>[]) new Class[0]);
            if (a2 == null) {
                AppboyLogger.d(a, "Firebase Cloud Messaging 'getInstance' method could not obtained. Not registering for Firebase Cloud Messaging.");
                return null;
            }
            Object a3 = eg.a((Object) null, a2, new Object[0]);
            if (a3 == null) {
                AppboyLogger.d(a, "Firebase Cloud Messaging 'InstanceId' object could not invoked. Not registering for Firebase Cloud Messaging.");
                return null;
            }
            Method a4 = eg.a(a3.getClass(), "getToken", (Class<?>[]) new Class[]{String.class, String.class});
            if (a4 == null) {
                AppboyLogger.d(a, "Firebase Cloud Messaging 'FirebaseInstanceId.getInstance().getToken()' method could not obtained. Not registering for Firebase Cloud Messaging.");
                return null;
            }
            Object a5 = eg.a(a3, a4, str, FirebaseMessaging.INSTANCE_ID_SCOPE);
            if (a5 == null || !(a5 instanceof String)) {
                return null;
            }
            return (String) a5;
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to register for Firebase Cloud Messaging", e);
            return null;
        }
    }
}
