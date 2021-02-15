package com.tapstream.sdk;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tapstream.sdk.landers.Lander;
import com.tapstream.sdk.wordofmouth.Reward;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;

class AndroidPlatform implements Platform {
    public static final String FIRED_EVENTS_KEY = "TapstreamSDKFiredEvents";
    public static final String IN_APP_LANDERS_KEY = "TapstreamInAppLanders";
    public static final String UUID_KEY = "TapstreamSDKUUID";
    public static final String WOM_REWARDS_KEY = "TapstreamWOMRewards";
    private final Application app;

    public AndroidPlatform(Application application) {
        this.app = application;
    }

    public synchronized String loadSessionId() {
        String string;
        SharedPreferences sharedPreferences = this.app.getApplicationContext().getSharedPreferences(UUID_KEY, 0);
        string = sharedPreferences.getString("uuid", (String) null);
        if (string == null) {
            string = UUID.randomUUID().toString();
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("uuid", string);
            edit.apply();
        }
        return string;
    }

    public synchronized Set<String> loadFiredEvents() {
        return new HashSet(this.app.getApplicationContext().getSharedPreferences(FIRED_EVENTS_KEY, 0).getAll().keySet());
    }

    public synchronized void saveFiredEvents(Set<String> set) {
        SharedPreferences.Editor edit = this.app.getApplicationContext().getSharedPreferences(FIRED_EVENTS_KEY, 0).edit();
        edit.clear();
        for (String putString : set) {
            edit.putString(putString, "");
        }
        edit.apply();
    }

    public String getResolution() {
        Display defaultDisplay = ((WindowManager) this.app.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return String.format(Locale.US, "%dx%d", new Object[]{Integer.valueOf(displayMetrics.widthPixels), Integer.valueOf(displayMetrics.heightPixels)});
    }

    public String getManufacturer() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception unused) {
            return null;
        }
    }

    public String getModel() {
        return Build.MODEL;
    }

    public String getOs() {
        return String.format(Locale.US, "Android %s", new Object[]{Build.VERSION.RELEASE});
    }

    public String getLocale() {
        return Locale.getDefault().toString();
    }

    public String getAppName() {
        PackageManager packageManager = this.app.getPackageManager();
        try {
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.app.getPackageName(), 0)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            return this.app.getPackageName();
        }
    }

    public String getAppVersion() {
        try {
            return this.app.getPackageManager().getPackageInfo(this.app.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public String getPackageName() {
        return this.app.getPackageName();
    }

    public String getReferrer() {
        return this.app.getApplicationContext().getSharedPreferences(UUID_KEY, 0).getString("referrer", (String) null);
    }

    public synchronized Integer getCountForReward(Reward reward) {
        return Integer.valueOf(this.app.getApplicationContext().getSharedPreferences(WOM_REWARDS_KEY, 0).getInt(Integer.toString(reward.getOfferId()), 0));
    }

    public synchronized boolean isConsumed(Reward reward) {
        return getCountForReward(reward).intValue() >= reward.getInstallCount() / reward.getMinimumInstalls();
    }

    public synchronized void consumeReward(Reward reward) {
        String num = Integer.toString(reward.getOfferId());
        SharedPreferences sharedPreferences = this.app.getApplicationContext().getSharedPreferences(WOM_REWARDS_KEY, 0);
        sharedPreferences.edit().putInt(num, sharedPreferences.getInt(num, 0) + 1).apply();
    }

    public Callable<AdvertisingID> getAdIdFetcher() {
        return new AdvertisingIdFetcher(this.app);
    }

    public ActivityEventSource getActivityEventSource() {
        try {
            return (ActivityEventSource) Class.forName("com.tapstream.sdk.api14.ActivityCallbacks").getConstructor(new Class[]{Application.class}).newInstance(new Object[]{this.app});
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean hasShown(Lander lander) {
        return this.app.getApplicationContext().getSharedPreferences(IN_APP_LANDERS_KEY, 0).getBoolean(Integer.toString(lander.getId()), false);
    }

    public void registerLanderShown(Lander lander) {
        SharedPreferences sharedPreferences = this.app.getApplicationContext().getSharedPreferences(IN_APP_LANDERS_KEY, 0);
        sharedPreferences.edit().putBoolean(Integer.toString(lander.getId()), true).apply();
    }
}
