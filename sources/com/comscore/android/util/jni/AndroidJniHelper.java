package com.comscore.android.util.jni;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.comscore.BuildConfig;
import com.comscore.android.CommonUtils;
import com.comscore.android.HostApplicationState;
import com.comscore.android.id.CrossPublisherId;
import com.comscore.android.id.DeviceId;
import com.comscore.android.id.IdHelperAndroid;
import com.comscore.android.util.AndroidTcfDataLoader;
import com.comscore.android.util.BackgroundManager;
import com.comscore.android.util.Permissions;
import com.comscore.android.util.update.UpdateFrom5_4_x;
import com.comscore.util.CrossPublisherIdUtil;
import com.comscore.util.TcfDataLoader;
import com.comscore.util.crashreport.CrashReportDecorator;
import com.comscore.util.jni.JniComScoreHelper;
import com.comscore.util.log.Logger;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AndroidJniHelper extends JniComScoreHelper {
    public static final String KEY_PACKAGE_MANAGER = "packageManager";
    private Context a;
    private BackgroundManager b;
    private UpdateFrom5_4_x c;
    private int d = -1;
    private int e = -1;
    private AndroidTcfDataLoader f = new AndroidTcfDataLoader();

    private int a(int i) {
        if (i != 1) {
            return i != 2 ? 0 : 1;
        }
        return 2;
    }

    /* access modifiers changed from: protected */
    public CrashReportDecorator createCrashReportDecorator() {
        return new AndroidCrashReportDecorator(this);
    }

    public String getAppDataDir() {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return context.getFilesDir().getAbsolutePath();
    }

    public String getApplicationId() {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return context.getPackageName();
    }

    public String getApplicationName() {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        return CommonUtils.getApplicationName(context);
    }

    public String getApplicationVersion() {
        return CommonUtils.getApplicationVersion(this.a);
    }

    public String getArchitecture() {
        return CommonUtils.getDeviceArchitecture();
    }

    /* access modifiers changed from: protected */
    public int getConnectivityType() {
        Context context = this.a;
        if (context == null) {
            return 40000;
        }
        return CommonUtils.getConnectivityType(context);
    }

    public Context getContext() {
        return this.a;
    }

    /* access modifiers changed from: protected */
    public String getCrossPublisherUniqueDeviceId() {
        Context context = this.a;
        if (context == null) {
            return null;
        }
        CrossPublisherId crossPublisherDeviceId = IdHelperAndroid.getCrossPublisherDeviceId(context);
        StringBuilder sb = new StringBuilder();
        String str = crossPublisherDeviceId.crossPublisherId;
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(crossPublisherDeviceId.source);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public int getCurrentHostApplicationState() {
        int state = HostApplicationState.getState(this.a);
        int i = 1;
        if (state == -1) {
            state = 1;
        }
        if (this.d == -1) {
            if (state != 0) {
                i = 2;
            }
            this.d = i;
        }
        return state;
    }

    public String[] getDeviceIds() {
        DeviceId deviceId = IdHelperAndroid.getDeviceId(this.a);
        if (deviceId == null || !IdHelperAndroid.checkAndroidSerial(deviceId.getId())) {
            return null;
        }
        return new String[]{deviceId.getId() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + deviceId.getCommonness() + "" + deviceId.getPersistency() + " true " + deviceId.getSource() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a(deviceId.getSource())};
    }

    public String getDeviceModel() {
        return CommonUtils.getDeviceModel();
    }

    public int getFirstHostState() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public String[] getInvalidIds() {
        return IdHelperAndroid.INVALID_ID_VALUES;
    }

    /* access modifiers changed from: protected */
    public String getLanguage() {
        return CommonUtils.getLanguage();
    }

    public String getOsName() {
        return "android";
    }

    public String getOsVersion() {
        return CommonUtils.getOsVersion();
    }

    public String getPackageManagerName() {
        Context context = this.a;
        if (context == null) {
            return "unknown";
        }
        String installerPackageName = context.getPackageManager().getInstallerPackageName(this.a.getPackageName());
        return !TextUtils.isEmpty(installerPackageName) ? installerPackageName : "unknown";
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getPlatformLabels(Map<String, String> map) {
        if (this.a == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        int i = IdHelperAndroid.getCrossPublisherDeviceId(this.a).source;
        int source = IdHelperAndroid.getDeviceId(this.a).getSource();
        boolean booleanValue = Permissions.check(this.a, "android.permission.INTERNET").booleanValue();
        boolean booleanValue2 = Permissions.check(this.a, "android.permission.ACCESS_NETWORK_STATE").booleanValue();
        boolean booleanValue3 = Permissions.check(this.a, "android.permission.ACCESS_WIFI_STATE").booleanValue();
        hashMap.put("ns_ap_env", String.format(Locale.getDefault(), "%d-%d-%d%d%d-%d-%d", new Object[]{Integer.valueOf(i), Integer.valueOf(source), Integer.valueOf(booleanValue ? 1 : 0), Integer.valueOf(booleanValue2 ? 1 : 0), Integer.valueOf(booleanValue3 ? 1 : 0), Integer.valueOf(isLibraryInstalledUsingMaven() ? 1 : 0), Integer.valueOf(this.d)}));
        hashMap.put("ns_ap_res", CommonUtils.getApplicationResolution(this.a));
        if (i == 1 && !TextUtils.isEmpty(map.get("ns_ak"))) {
            String mD5CrossPublisherDeviceId = CrossPublisherIdUtil.getMD5CrossPublisherDeviceId();
            if (!TextUtils.isEmpty(mD5CrossPublisherDeviceId)) {
                hashMap.put("ns_ap_i3", mD5CrossPublisherDeviceId);
            }
        }
        if (TextUtils.isEmpty(map.get("ns_ap_an"))) {
            hashMap.put("ns_ap_an", getApplicationName());
        }
        if (TextUtils.isEmpty(map.get("ns_ap_ver"))) {
            hashMap.put("ns_ap_ver", getApplicationVersion());
        }
        if (TextUtils.isEmpty(map.get("ns_ap_bi"))) {
            hashMap.put("ns_ap_bi", getApplicationId());
        }
        hashMap.put("ns_ap_ais", getPackageManagerName());
        return hashMap;
    }

    public String getRuntimeName() {
        return "android";
    }

    public String getRuntimeVersion() {
        return getOsVersion();
    }

    /* access modifiers changed from: protected */
    public int getScreenHeight() {
        Context context = this.a;
        if (context == null) {
            return 0;
        }
        return CommonUtils.getDisplaySize(context).y;
    }

    /* access modifiers changed from: protected */
    public int getScreenWidth() {
        Context context = this.a;
        if (context == null) {
            return 0;
        }
        return CommonUtils.getDisplaySize(context).x;
    }

    /* access modifiers changed from: protected */
    public TcfDataLoader getTcfDataLoader() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public boolean isJailBroken() {
        return CommonUtils.isDeviceRooted();
    }

    public boolean isLibraryInstalledUsingMaven() {
        if (this.e == -1) {
            try {
                this.e = ((String) Class.forName("com.comscore.BuildConfig").getField("FLAVOR").get((Object) null)).startsWith(BuildConfig.FLAVOR) ? 1 : 0;
            } catch (Exception unused) {
                this.e = 0;
            }
        }
        return this.e == 1;
    }

    /* access modifiers changed from: protected */
    public boolean libraryPostUpdate(String str, String str2, Map<String, String> map, ArrayList<String> arrayList) {
        UpdateFrom5_4_x updateFrom5_4_x = this.c;
        if (updateFrom5_4_x == null) {
            return false;
        }
        updateFrom5_4_x.libraryPostUpdate(map, arrayList);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean libraryUpdate(String str, String str2, Map<String, String> map) {
        if ((str != null && str.length() != 0) || map.containsKey("updated_from_versions")) {
            return false;
        }
        UpdateFrom5_4_x updateFrom5_4_x = new UpdateFrom5_4_x(this.a);
        this.c = updateFrom5_4_x;
        updateFrom5_4_x.libraryUpdate(map);
        return true;
    }

    public void setContext(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            this.a = applicationContext;
            if (applicationContext != null) {
                this.f.setContext(applicationContext);
                return;
            }
            throw new NullPointerException("The Context provided by context.getApplicationContext() cannot be null.");
        }
        throw new NullPointerException("The Context parameter provided cannot be null.");
    }

    public boolean subscribeToForegroundNotification() {
        Context context = this.a;
        if (!(context instanceof Application) || Build.VERSION.SDK_INT < 14) {
            Logger.w("Unable to detect automatically if the application is in the foreground or in the background.");
            return false;
        }
        BackgroundManager backgroundManager = new BackgroundManager((Application) context);
        this.b = backgroundManager;
        backgroundManager.register();
        return true;
    }

    public boolean unsubscribeFromForegroundNotification() {
        BackgroundManager backgroundManager = this.b;
        if (backgroundManager == null) {
            return false;
        }
        backgroundManager.unregister();
        return true;
    }
}
