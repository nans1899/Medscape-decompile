package io.branch.referral;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import io.branch.referral.Defines;
import org.json.JSONException;
import org.json.JSONObject;

class DeviceInfo {
    private static DeviceInfo thisInstance_;
    private final String UIMode_;
    private final String appVersion_;
    private final String brandName_;
    private final String countryCode_;
    private final String hardwareID_;
    private final boolean isHardwareIDReal_;
    private final boolean isWifiConnected_;
    private final String languageCode_;
    private final String localIpAddr_;
    private final String modelName_;
    private final String osName_;
    private final int osVersion_;
    private final String packageName_;
    private final int screenDensity_;
    private final int screenHeight_;
    private final int screenWidth_;

    public static DeviceInfo getInstance(boolean z, SystemObserver systemObserver, boolean z2) {
        if (thisInstance_ == null) {
            thisInstance_ = new DeviceInfo(z, systemObserver, z2);
        }
        return thisInstance_;
    }

    public static DeviceInfo getInstance() {
        return thisInstance_;
    }

    private DeviceInfo(boolean z, SystemObserver systemObserver, boolean z2) {
        if (z2) {
            this.hardwareID_ = systemObserver.getUniqueID(true);
        } else {
            this.hardwareID_ = systemObserver.getUniqueID(z);
        }
        this.isHardwareIDReal_ = systemObserver.hasRealHardwareId();
        this.brandName_ = systemObserver.getPhoneBrand();
        this.modelName_ = systemObserver.getPhoneModel();
        DisplayMetrics screenDisplay = systemObserver.getScreenDisplay();
        this.screenDensity_ = screenDisplay.densityDpi;
        this.screenHeight_ = screenDisplay.heightPixels;
        this.screenWidth_ = screenDisplay.widthPixels;
        this.isWifiConnected_ = systemObserver.getWifiConnected();
        this.localIpAddr_ = SystemObserver.getLocalIPAddress();
        this.osName_ = systemObserver.getOS();
        this.osVersion_ = systemObserver.getOSVersion();
        this.packageName_ = systemObserver.getPackageName();
        this.appVersion_ = systemObserver.getAppVersion();
        this.countryCode_ = systemObserver.getISO2CountryCode();
        this.languageCode_ = systemObserver.getISO2LanguageCode();
        this.UIMode_ = systemObserver.getUIMode();
    }

    public void updateRequestWithDeviceParams(JSONObject jSONObject) {
        try {
            if (!this.hardwareID_.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.HardwareID.getKey(), this.hardwareID_);
                jSONObject.put(Defines.Jsonkey.IsHardwareIDReal.getKey(), this.isHardwareIDReal_);
            }
            if (!this.brandName_.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.Brand.getKey(), this.brandName_);
            }
            if (!this.modelName_.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.Model.getKey(), this.modelName_);
            }
            jSONObject.put(Defines.Jsonkey.ScreenDpi.getKey(), this.screenDensity_);
            jSONObject.put(Defines.Jsonkey.ScreenHeight.getKey(), this.screenHeight_);
            jSONObject.put(Defines.Jsonkey.ScreenWidth.getKey(), this.screenWidth_);
            jSONObject.put(Defines.Jsonkey.WiFi.getKey(), this.isWifiConnected_);
            jSONObject.put(Defines.Jsonkey.UIMode.getKey(), this.UIMode_);
            if (!this.osName_.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.OS.getKey(), this.osName_);
            }
            jSONObject.put(Defines.Jsonkey.OSVersion.getKey(), this.osVersion_);
            if (!TextUtils.isEmpty(this.countryCode_)) {
                jSONObject.put(Defines.Jsonkey.Country.getKey(), this.countryCode_);
            }
            if (!TextUtils.isEmpty(this.languageCode_)) {
                jSONObject.put(Defines.Jsonkey.Language.getKey(), this.languageCode_);
            }
            if (!TextUtils.isEmpty(this.localIpAddr_)) {
                jSONObject.put(Defines.Jsonkey.LocalIP.getKey(), this.localIpAddr_);
            }
        } catch (JSONException unused) {
        }
    }

    public void updateRequestWithUserData(Context context, PrefHelper prefHelper, JSONObject jSONObject) {
        try {
            if (this.hardwareID_.equals("bnc_no_value") || !this.isHardwareIDReal_) {
                jSONObject.put(Defines.Jsonkey.UnidentifiedDevice.getKey(), true);
            } else {
                jSONObject.put(Defines.Jsonkey.AndroidID.getKey(), this.hardwareID_);
            }
            if (!this.brandName_.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.Brand.getKey(), this.brandName_);
            }
            if (!this.modelName_.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.Model.getKey(), this.modelName_);
            }
            jSONObject.put(Defines.Jsonkey.ScreenDpi.getKey(), this.screenDensity_);
            jSONObject.put(Defines.Jsonkey.ScreenHeight.getKey(), this.screenHeight_);
            jSONObject.put(Defines.Jsonkey.ScreenWidth.getKey(), this.screenWidth_);
            if (!this.osName_.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.OS.getKey(), this.osName_);
            }
            jSONObject.put(Defines.Jsonkey.OSVersion.getKey(), this.osVersion_);
            if (!TextUtils.isEmpty(this.countryCode_)) {
                jSONObject.put(Defines.Jsonkey.Country.getKey(), this.countryCode_);
            }
            if (!TextUtils.isEmpty(this.languageCode_)) {
                jSONObject.put(Defines.Jsonkey.Language.getKey(), this.languageCode_);
            }
            if (!TextUtils.isEmpty(this.localIpAddr_)) {
                jSONObject.put(Defines.Jsonkey.LocalIP.getKey(), this.localIpAddr_);
            }
            if (prefHelper != null && !prefHelper.getDeviceFingerPrintID().equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), prefHelper.getDeviceFingerPrintID());
            }
            String identity = prefHelper.getIdentity();
            if (identity != null && !identity.equals("bnc_no_value")) {
                jSONObject.put(Defines.Jsonkey.DeveloperIdentity.getKey(), prefHelper.getIdentity());
            }
            jSONObject.put(Defines.Jsonkey.AppVersion.getKey(), getInstance().getAppVersion());
            jSONObject.put(Defines.Jsonkey.SDK.getKey(), "android");
            jSONObject.put(Defines.Jsonkey.SdkVersion.getKey(), BuildConfig.VERSION_NAME);
            jSONObject.put(Defines.Jsonkey.UserAgent.getKey(), getDefaultBrowserAgent(context));
        } catch (JSONException unused) {
        }
    }

    public String getPackageName() {
        return this.packageName_;
    }

    public String getAppVersion() {
        return this.appVersion_;
    }

    public boolean isHardwareIDReal() {
        return this.isHardwareIDReal_;
    }

    public String getHardwareID() {
        if (this.hardwareID_.equals("bnc_no_value")) {
            return null;
        }
        return this.hardwareID_;
    }

    public String getOsName() {
        return this.osName_;
    }

    private String getDefaultBrowserAgent(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                return WebSettings.getDefaultUserAgent(context);
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
