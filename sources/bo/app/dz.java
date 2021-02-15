package bo.app;

import android.content.Context;
import android.content.pm.PackageManager;
import com.appboy.support.AppboyLogger;
import com.google.android.gms.common.GoogleApiAvailability;

public final class dz {
    private static final String a = AppboyLogger.getAppboyLogTag(dz.class);

    public static boolean a(Context context) {
        try {
            Class.forName("com.google.android.gms.common.GoogleApiAvailability");
            int isGooglePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
            if (isGooglePlayServicesAvailable == 0) {
                AppboyLogger.d(a, "Google Play Services is available.");
                return true;
            }
            String str = a;
            AppboyLogger.i(str, "Google Play Services is unavailable. Connection result: " + isGooglePlayServicesAvailable);
            return false;
        } catch (Exception e) {
            AppboyLogger.i(a, "Google Play Services Availability API not found. Google Play Services not enabled.", (Throwable) e);
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.google.android.gsf", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        } catch (Exception unused2) {
            AppboyLogger.e(a, "Unexpected exception while checking for com.google.android.gsf");
            return false;
        }
    }
}
