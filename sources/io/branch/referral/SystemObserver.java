package io.branch.referral;

import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class SystemObserver {
    public static final String BLANK = "bnc_no_value";
    static String GAIDString_ = null;
    private static final int GAID_FETCH_TIME_OUT = 1500;
    int LATVal_ = 0;
    private Context context_;
    private boolean isRealHardwareId;

    interface GAdsParamsFetchEvents {
        void onGAdsFetchFinished();
    }

    /* access modifiers changed from: package-private */
    public String getOS() {
        return "Android";
    }

    SystemObserver(Context context) {
        this.context_ = context;
        this.isRealHardwareId = true;
    }

    /* access modifiers changed from: package-private */
    public String getUniqueID(boolean z) {
        if (this.context_ == null) {
            return "bnc_no_value";
        }
        String str = null;
        if (!z && !Branch.isSimulatingInstalls_) {
            str = Settings.Secure.getString(this.context_.getContentResolver(), "android_id");
        }
        if (str != null) {
            return str;
        }
        String uuid = UUID.randomUUID().toString();
        this.isRealHardwareId = false;
        return uuid;
    }

    /* access modifiers changed from: package-private */
    public boolean hasRealHardwareId() {
        return this.isRealHardwareId;
    }

    /* access modifiers changed from: package-private */
    public String getPackageName() {
        try {
            return this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public String getAppVersion() {
        try {
            PackageInfo packageInfo = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 0);
            if (packageInfo.versionName != null) {
                return packageInfo.versionName;
            }
            return "bnc_no_value";
        } catch (PackageManager.NameNotFoundException unused) {
            return "bnc_no_value";
        }
    }

    /* access modifiers changed from: package-private */
    public String getPhoneBrand() {
        return Build.MANUFACTURER;
    }

    /* access modifiers changed from: package-private */
    public String getPhoneModel() {
        return Build.MODEL;
    }

    /* access modifiers changed from: package-private */
    public String getISO2CountryCode() {
        return Locale.getDefault() != null ? Locale.getDefault().getCountry() : "";
    }

    /* access modifiers changed from: package-private */
    public String getISO2LanguageCode() {
        return Locale.getDefault() != null ? Locale.getDefault().getLanguage() : "";
    }

    /* access modifiers changed from: package-private */
    public int getOSVersion() {
        return Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: package-private */
    public DisplayMetrics getScreenDisplay() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context_.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public boolean getWifiConnected() {
        NetworkInfo networkInfo;
        if (this.context_.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0 || (networkInfo = ((ConnectivityManager) this.context_.getSystemService("connectivity")).getNetworkInfo(1)) == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public Object getAdInfoObject() {
        try {
            return Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{this.context_});
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public String getAdvertisingId(Object obj) {
        try {
            GAIDString_ = (String) obj.getClass().getMethod("getId", new Class[0]).invoke(obj, new Object[0]);
        } catch (Exception unused) {
        }
        return GAIDString_;
    }

    /* access modifiers changed from: private */
    public int getLATValue(Object obj) {
        try {
            int i = 0;
            if (((Boolean) obj.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(obj, new Object[0])).booleanValue()) {
                i = 1;
            }
            this.LATVal_ = i;
        } catch (Exception unused) {
        }
        return this.LATVal_;
    }

    /* access modifiers changed from: package-private */
    public boolean prefetchGAdsParams(GAdsParamsFetchEvents gAdsParamsFetchEvents) {
        if (!TextUtils.isEmpty(GAIDString_)) {
            return false;
        }
        new GAdsPrefetchTask(gAdsParamsFetchEvents).executeTask(new Void[0]);
        return true;
    }

    private class GAdsPrefetchTask extends BranchAsyncTask<Void, Void, Void> {
        private final GAdsParamsFetchEvents callback_;

        public GAdsPrefetchTask(GAdsParamsFetchEvents gAdsParamsFetchEvents) {
            this.callback_ = gAdsParamsFetchEvents;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Thread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(-19);
                    Object access$000 = SystemObserver.this.getAdInfoObject();
                    String unused = SystemObserver.this.getAdvertisingId(access$000);
                    int unused2 = SystemObserver.this.getLATValue(access$000);
                    countDownLatch.countDown();
                }
            }).start();
            try {
                countDownLatch.await(1500, TimeUnit.MILLISECONDS);
                return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            GAdsParamsFetchEvents gAdsParamsFetchEvents = this.callback_;
            if (gAdsParamsFetchEvents != null) {
                gAdsParamsFetchEvents.onGAdsFetchFinished();
            }
        }
    }

    static String getLocalIPAddress() {
        String str = "";
        try {
            for (T inetAddresses : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                Iterator<T> it = Collections.list(inetAddresses.getInetAddresses()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InetAddress inetAddress = (InetAddress) it.next();
                    if (!inetAddress.isLoopbackAddress()) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (hostAddress.indexOf(58) < 0) {
                            str = hostAddress;
                            break;
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public String getUIMode() {
        switch (((UiModeManager) this.context_.getSystemService("uimode")).getCurrentModeType()) {
            case 1:
                return "UI_MODE_TYPE_NORMAL";
            case 2:
                return "UI_MODE_TYPE_DESK";
            case 3:
                return "UI_MODE_TYPE_CAR";
            case 4:
                return "UI_MODE_TYPE_TELEVISION";
            case 5:
                return "UI_MODE_TYPE_APPLIANCE";
            case 6:
                return "UI_MODE_TYPE_WATCH";
            default:
                return "UI_MODE_TYPE_UNDEFINED";
        }
    }
}
