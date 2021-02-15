package bo.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.appboy.Constants;
import com.appboy.models.AppboyGeofence;
import com.appboy.receivers.AppboyActionReceiver;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public final class dx {
    private static final String a = AppboyLogger.getAppboyLogTag(dx.class);

    public static PendingIntent a(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent(Constants.APPBOY_ACTION_RECEIVER_GEOFENCE_UPDATE_INTENT_ACTION).setClass(context, AppboyActionReceiver.class), 134217728);
    }

    public static PendingIntent b(Context context) {
        return PendingIntent.getBroadcast(context, 0, new Intent(Constants.APPBOY_ACTION_RECEIVER_GEOFENCE_LOCATION_UPDATE_INTENT_ACTION).setClass(context, AppboyActionReceiver.class), 134217728);
    }

    public static boolean a(dq dqVar) {
        if (!dqVar.a()) {
            AppboyLogger.i(a, "Geofences implicitly disabled via server configuration.");
            return false;
        } else if (dqVar.b()) {
            AppboyLogger.i(a, "Geofences enabled in server configuration.");
            return true;
        } else {
            AppboyLogger.i(a, "Geofences explicitly disabled via server configuration.");
            return false;
        }
    }

    public static int b(dq dqVar) {
        if (dqVar.e() > 0) {
            return dqVar.e();
        }
        return 20;
    }

    public static List<AppboyGeofence> a(SharedPreferences sharedPreferences) {
        ArrayList arrayList = new ArrayList();
        Map<String, ?> all = sharedPreferences.getAll();
        if (all == null || all.size() == 0) {
            AppboyLogger.d(a, "Did not find stored geofences.");
            return arrayList;
        }
        Set<String> keySet = all.keySet();
        if (keySet == null || keySet.size() == 0) {
            AppboyLogger.w(a, "Failed to find stored geofence keys.");
            return arrayList;
        }
        for (String next : keySet) {
            String string = sharedPreferences.getString(next, (String) null);
            try {
                if (StringUtils.isNullOrBlank(string)) {
                    String str = a;
                    AppboyLogger.w(str, "Received null or blank serialized  geofence string for geofence id " + next + " from shared preferences. Not parsing.");
                } else {
                    arrayList.add(new AppboyGeofence(new JSONObject(string)));
                }
            } catch (JSONException e) {
                String str2 = a;
                AppboyLogger.e(str2, "Encountered Json exception while parsing stored geofence: " + string, e);
            } catch (Exception e2) {
                String str3 = a;
                AppboyLogger.e(str3, "Encountered unexpected exception while parsing stored geofence: " + string, e2);
            }
        }
        return arrayList;
    }
}
