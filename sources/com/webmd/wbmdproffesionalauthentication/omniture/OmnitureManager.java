package com.webmd.wbmdproffesionalauthentication.omniture;

import android.content.Context;
import com.adobe.mobile.Analytics;
import com.google.android.gms.ads.AdError;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OmnitureManager {
    public static final String ACTION = "action";
    public static final String APP_APPID = "app-msp";
    public static final String APP_NAME = "wapp.site";
    public static final String BU = "wapp.bu";
    public static final String CHANNEL = "wapp.chn";
    private static final String DEFAULT_MODULE_NAME = "entry-module-default";
    public static final String DESTINATION_URL = "wapp.desurl";
    public static final String HEIRARCHY = "wapp.hier1";
    public static final String HOME_SCREEN_PAGE = "medscape.com/app/home-screen/view";
    public static final String MOBILE_BUILD = "wapp.build";
    public static final String OTT_TEST_VAR = "wapp.test";
    public static final String PAGENAME = "pageName";
    private static final String PREFIX = "medscape.com/app/";
    public static final String PVID = "wapp.pvid";
    public static final String REFERRING_LINK = "wapp.mlink";
    public static final String REFERRING_MODULE = "wapp.mmodule";
    public static final String REFERRING_PAGE = "wapp.mpage";
    public static final String REFERRING_QUERY_TEXT = "wapp.querytext";
    public static final String REGISTERED_USERID = "wapp.regid";
    public static final String SEARCH_FILTER = "wapp.filter";
    public static final String SEARCH_RESULTS_COUNT = "wapp.results";
    public static final String SEARCH_TERMS = "wapp.query";
    public static final String SITE_CLASS = "wapp.class";
    private static final String TAG = OmnitureManager.class.getSimpleName();
    public static final String USER_COUNTRY = "wapp.dcntry";
    public static final String USER_PROFESSION = "wapp.dprofsn";
    public static final String USER_SPECIALTY = "wapp.dspclty";
    private static final OmnitureManager mOmnitureManager = new OmnitureManager();
    private Map<String, Object> mCurrentModuleData = new HashMap();
    private String mCurrentPageName = null;
    private Map<String, Object> mData;
    public String mSearchChannel = "other";

    private OmnitureManager() {
        HashMap hashMap = new HashMap();
        this.mData = hashMap;
        fillAppData(hashMap);
    }

    public static OmnitureManager get() {
        return mOmnitureManager;
    }

    public String trackPageView(Context context, String str, String str2, String str3, String str4, String str5, Map<String, Object> map, boolean z) {
        fillContextData(context, this.mData);
        fillUserData(this.mData, context);
        String str6 = System.currentTimeMillis() + "" + ((int) ((new Random().nextFloat() * 900000.0f) + 100000.0f));
        String buildPageName = buildPageName(str2, str3, str4, str5);
        if (!z) {
            this.mCurrentPageName = buildPageName;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.mData);
        hashMap.putAll(this.mCurrentModuleData);
        hashMap.put("wapp.pvid", str6);
        hashMap.put("wapp.chn", str);
        if (map != null) {
            hashMap.putAll(map);
        }
        Analytics.trackState(buildPageName, hashMap);
        this.mCurrentModuleData.clear();
        return str6;
    }

    public String trackPageView(Context context, String str, String str2, String str3, String str4, String str5, Map<String, Object> map) {
        return trackPageView(context, str, str2, str3, str4, str5, map, false);
    }

    public void trackModule(Context context, String str, String str2, String str3, Map<String, Object> map) {
        markModule(this.mCurrentPageName == null, str2, str3, map);
        fillContextData(context, this.mData);
        fillUserData(this.mData, context);
        String buildActionName = buildActionName(this.mCurrentPageName, str2, str3);
        HashMap hashMap = new HashMap();
        Map<String, Object> map2 = this.mData;
        if (map2 != null) {
            hashMap.putAll(map2);
        }
        Map<String, Object> map3 = this.mCurrentModuleData;
        if (map3 != null) {
            hashMap.putAll(map3);
        }
        hashMap.put("wapp.chn", str);
        if (map != null) {
            hashMap.putAll(map);
        }
        Analytics.trackAction(buildActionName, hashMap);
        this.mCurrentModuleData.clear();
    }

    public void trackModuleAbsolute(Context context, String str, String str2, String str3, Map<String, Object> map) {
        markModuleAbsolute(str2, str3, map);
        fillContextData(context, this.mData);
        fillUserData(this.mData, context);
        String buildActionNameAbsolute = buildActionNameAbsolute(this.mCurrentPageName, str2, str3);
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.mData);
        hashMap.putAll(this.mCurrentModuleData);
        hashMap.put("wapp.chn", str);
        if (map != null) {
            hashMap.putAll(map);
        }
        Analytics.trackAction(buildActionNameAbsolute, hashMap);
        this.mCurrentModuleData.clear();
    }

    public void markModule(boolean z, String str, String str2, Map<String, Object> map) {
        if (str != null) {
            if (z) {
                this.mCurrentPageName = buildPageName(DEFAULT_MODULE_NAME, (String) null, (String) null, (String) null);
            }
            this.mCurrentModuleData.put("wapp.mmodule", str);
            if (StringExtensions.isNotEmpty(str2)) {
                this.mCurrentModuleData.put("wapp.mlink", str2);
            }
            String str3 = this.mCurrentPageName;
            if (str3 != null && !str3.equals("")) {
                this.mCurrentModuleData.put("wapp.mpage", this.mCurrentPageName);
            } else if (this.mCurrentModuleData.containsKey("wapp.mpage")) {
                this.mCurrentModuleData.remove("wapp.mpage");
            }
            if (map != null) {
                this.mCurrentModuleData.putAll(map);
            }
        }
    }

    public void markModule(String str, String str2, Map<String, Object> map) {
        markModule(false, str, str2, map);
    }

    public void markModuleAbsolute(String str, String str2, Map<String, Object> map) {
        if (str != null) {
            this.mCurrentModuleData.put("wapp.mmodule", str);
            this.mCurrentModuleData.put("wapp.mlink", str2);
            this.mCurrentModuleData.put("wapp.mpage", this.mCurrentPageName);
            if (map != null) {
                this.mCurrentModuleData.putAll(map);
            }
        }
    }

    public String buildPageName(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder(PREFIX);
        sb.append(str);
        if (str2 != null) {
            sb.append("/");
            sb.append(str2);
        }
        if (str3 != null) {
            sb.append("/");
            sb.append(str3);
        }
        if (str4 != null) {
            sb.append("/");
            sb.append(str4);
        }
        return sb.toString();
    }

    private void fillUserData(Map<String, Object> map, Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.regid", "000000000");
        hashMap.put("wapp.dcntry", AdError.UNDEFINED_DOMAIN);
        hashMap.put("wapp.dprofsn", AdError.UNDEFINED_DOMAIN);
        hashMap.put("wapp.dspclty", AdError.UNDEFINED_DOMAIN);
        UserProfile instance = UserProfile.getInstance();
        boolean isUserLoggedIn = AccountProvider.isUserLoggedIn(context);
        if (instance != null && isUserLoggedIn) {
            hashMap.put("wapp.regid", instance.getBasicProfile().getEncryptedRegisteredId());
            hashMap.put("wapp.dcntry", instance.getProfessionProfile().getCountryCode());
            String profession = instance.getProfessionProfile().getProfession();
            if (profession != null) {
                hashMap.put("wapp.dprofsn", profession.toLowerCase());
            }
            String speciality = instance.getProfessionProfile().getSpeciality();
            if (speciality != null) {
                hashMap.put("wapp.dspclty", speciality.toLowerCase());
            }
        }
        map.putAll(hashMap);
    }

    private String buildActionName(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        } else {
            sb.append(buildPageName(DEFAULT_MODULE_NAME, (String) null, (String) null, (String) null));
        }
        if (StringExtensions.isNotEmpty(str)) {
            sb.append("_");
        }
        sb.append(str2);
        sb.append("_");
        sb.append(str3);
        return sb.toString();
    }

    private String buildActionNameAbsolute(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (str == null || str.isEmpty()) {
            sb.append(buildPageName(DEFAULT_MODULE_NAME, (String) null, (String) null, (String) null));
        } else {
            sb.append(str);
        }
        if (StringExtensions.isNotEmpty(str2)) {
            sb.append("_");
            sb.append(str2);
        }
        if (StringExtensions.isNotEmpty(str3)) {
            sb.append("_");
            sb.append(str3);
        }
        return sb.toString();
    }

    private void fillAppData(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.site", "app-msp");
        hashMap.put("wapp.chn", "ntc");
        hashMap.put("wapp.test", (Object) null);
        map.putAll(hashMap);
    }

    private void fillContextData(Context context, Map<String, Object> map) {
        map.put("wapp.build", "app-msp_" + Util.getApplicationVersion(context) + "_android");
    }

    public void generatePVID() {
        System.currentTimeMillis();
        new Random().nextFloat();
    }

    public void setmCurrentPageName(String str) {
        this.mCurrentPageName = str;
    }

    public String getmCurrentPageName() {
        return this.mCurrentPageName;
    }

    public boolean isFromHomeScreen() {
        String str = this.mCurrentPageName;
        if (str != null) {
            return str.equalsIgnoreCase("medscape.com/app/home-screen/view");
        }
        return false;
    }

    public void clearCurrentModuleData() {
        this.mCurrentModuleData.clear();
    }
}
