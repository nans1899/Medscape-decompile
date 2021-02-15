package bo.app;

import com.appboy.support.AppboyLogger;
import org.json.JSONException;

public class b implements c {
    private static final String a = AppboyLogger.getAppboyLogTag(b.class);

    public bz a(String str) {
        try {
            return cf.c(str);
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to create feed card impression event for card: " + str, e);
            return null;
        }
    }

    public bz e(String str) {
        try {
            return cf.d(str);
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to create feed card click event for card: " + str, e);
            return null;
        }
    }

    public bz c(String str) {
        String str2 = a;
        AppboyLogger.w(str2, "Cannot create card dismissed event for Feed card. Returning null. Card id: " + str);
        return null;
    }

    public bz d(String str) {
        String str2 = a;
        AppboyLogger.w(str2, "Cannot create card control event for Feed card. Returning null. Card id: " + str);
        return null;
    }
}
