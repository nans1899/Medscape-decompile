package bo.app;

import com.appboy.support.AppboyLogger;
import org.json.JSONException;

public class a implements c {
    private static final String a = AppboyLogger.getAppboyLogTag(a.class);

    public bz a(String str) {
        try {
            return cf.e(str);
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to create Content Cards impression event for card: " + str, e);
            return null;
        }
    }

    /* renamed from: b */
    public cf e(String str) {
        try {
            return cf.g(str);
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to create Content Cards click event for card: " + str, e);
            return null;
        }
    }

    public bz c(String str) {
        try {
            return cf.h(str);
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to create Content Cards dismissed event for card: " + str, e);
            return null;
        }
    }

    public bz d(String str) {
        try {
            return cf.f(str);
        } catch (JSONException e) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to create Content Cards control impression event for card: " + str, e);
            return null;
        }
    }
}
