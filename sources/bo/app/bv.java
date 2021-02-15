package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;

public class bv implements bu {
    private static final String b = AppboyLogger.getAppboyLogTag(bv.class);
    final SharedPreferences a;
    private final AppboyConfigurationProvider c;

    public bv(Context context, AppboyConfigurationProvider appboyConfigurationProvider) {
        this.c = appboyConfigurationProvider;
        this.a = context.getSharedPreferences("com.appboy.push_registration", 0);
    }

    public synchronized String a() {
        if (b() && this.a.contains("version_code") && this.c.getVersionCode() != this.a.getInt("version_code", Integer.MIN_VALUE)) {
            return null;
        }
        if (this.a.contains("device_identifier")) {
            if (!bg.b().equals(this.a.getString("device_identifier", ""))) {
                AppboyLogger.i(b, "Device identifier differs from saved device identifier. Returning null token.");
                return null;
            }
        }
        return this.a.getString("registration_id", (String) null);
    }

    public synchronized void a(String str) {
        if (str != null) {
            SharedPreferences.Editor edit = this.a.edit();
            edit.putString("registration_id", str);
            edit.putInt("version_code", this.c.getVersionCode());
            edit.putString("device_identifier", bg.b());
            edit.apply();
        } else {
            throw new NullPointerException();
        }
    }

    private boolean b() {
        return this.c.isAdmMessagingRegistrationEnabled() || this.c.isFirebaseCloudMessagingRegistrationEnabled();
    }
}
