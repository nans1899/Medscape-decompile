package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.support.AppboyLogger;

public class dp {
    private static final String a = AppboyLogger.getAppboyLogTag(dp.class);
    private final SharedPreferences b;

    public dp(Context context) {
        this.b = context.getSharedPreferences("persistent.com.appboy.storage.sdk_enabled_cache", 0);
    }

    public boolean a() {
        return this.b.getBoolean("appboy_sdk_disabled", false);
    }

    public void a(boolean z) {
        String str = a;
        AppboyLogger.i(str, "Setting Appboy SDK disabled to: " + z);
        this.b.edit().putBoolean("appboy_sdk_disabled", z).apply();
    }
}
