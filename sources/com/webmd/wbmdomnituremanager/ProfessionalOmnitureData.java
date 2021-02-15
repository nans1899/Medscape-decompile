package com.webmd.wbmdomnituremanager;

import android.content.ComponentName;
import android.content.Context;
import com.adobe.mobile.Config;
import com.google.android.gms.ads.AdError;
import com.medscape.android.BI.omniture.OmnitureManager;
import java.util.HashMap;
import java.util.Map;

public class ProfessionalOmnitureData extends WBMDOmnitureData {
    private static final String CME_PACKAGE_NAME = "com.medscape.cmepulse";
    private static final String MEDPULSE_PACKAGE_NAME = "com.medscape.medpulse";
    private static final String MEDSCAPE_PACKAGE_NAME = "com.medscape.android";
    public static Map<String, String> mAdditonalDefaultData;
    private final String KEY_BUILD = "wapp.build";
    private final String KEY_CHANNEL = "wapp.chn";
    private final String KEY_REGISTERED_USERID = "wapp.regid";
    private final String KEY_SERVER = "wapp.server";
    private final String KEY_SITE = "wapp.site";
    private final String KEY_TEST = "wapp.test";
    private final String KEY_USER_COUNTRY = "wapp.dcntry";
    private final String KEY_USER_OCCUPATION_ID = "wapp.doccptnid";
    private final String KEY_USER_PROFESSION_ID = "wapp.dprofsnid";
    private final String KEY_USER_SPECIALTY_ID = "wapp.dspcltyid";
    private final String KEY_VAPI = OmnitureManager.VISITOR_VALUE;
    private Context mContext;

    public ProfessionalOmnitureData(Context context) {
        this.mContext = context;
        WBMDOmnitureManager.noExtraSlash = true;
    }

    public Map<String, String> addPageNames() {
        return new HashMap();
    }

    public Map<String, String> addPageNameExceptions() {
        return new HashMap();
    }

    public Map<String, String> addModuleNames() {
        return new HashMap();
    }

    public Map<String, String> addModuleNameExceptions() {
        return new HashMap();
    }

    public Map<String, String> addDefaultData() {
        return getDefaultData();
    }

    public String addAppName() {
        return getAppOmnitureName();
    }

    private Map<String, String> getDefaultData() {
        HashMap hashMap = new HashMap();
        WBMDOmnitureManager.mPageNamePrefix = "medscape.com/app/";
        Context context = this.mContext;
        if (context != null) {
            String packageName = context.getPackageName();
            if (packageName.contains("com.medscape.cmepulse")) {
                hashMap.put("wapp.site", "app-edu");
                hashMap.put("wapp.chn", "education");
                hashMap.put("wapp.build", "app-edu_" + getVersion() + "_android");
                StringBuilder sb = new StringBuilder();
                sb.append("edu|mobileappsdk|");
                sb.append(Config.getVersion());
                hashMap.put("wapp.server", sb.toString());
                hashMap.put(OmnitureManager.VISITOR_VALUE, "VisitorAPI Present");
            } else if (packageName.contains("com.medscape.android")) {
                hashMap.put("wapp.site", "app-msp");
                hashMap.put("wapp.chn", "ntc");
                hashMap.put("wapp.test", (Object) null);
                hashMap.put("wapp.build", "app-msp_" + getVersion() + "_android");
                hashMap.put("wapp.regid", "000000000");
                hashMap.put("wapp.dcntry", AdError.UNDEFINED_DOMAIN);
                hashMap.put("wapp.dprofsnid", AdError.UNDEFINED_DOMAIN);
                hashMap.put("wapp.dspcltyid", AdError.UNDEFINED_DOMAIN);
                hashMap.put("wapp.doccptnid", AdError.UNDEFINED_DOMAIN);
            } else if (packageName.contains("com.medscape.medpulse")) {
                hashMap.put("wapp.site", "app-mp");
                hashMap.put("wapp.chn", "news");
                hashMap.put("wapp.test", (Object) null);
                hashMap.put("wapp.build", "app-mp_" + getVersion() + "_android");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("mp|mobileappsdk|");
                sb2.append(Config.getVersion());
                hashMap.put("wapp.server", sb2.toString());
                hashMap.put("wapp.regid", (Object) null);
                hashMap.put("wapp.dcntry", (Object) null);
                hashMap.put("wapp.dprofsnid", (Object) null);
                hashMap.put("wapp.dspcltyid", (Object) null);
                hashMap.put(OmnitureManager.VISITOR_VALUE, "VisitorAPI Present");
            }
        }
        Map<String, String> map = mAdditonalDefaultData;
        if (map != null && map.size() > 0) {
            hashMap.putAll(mAdditonalDefaultData);
        }
        return hashMap;
    }

    private String getAppOmnitureName() {
        return getAppNamePrefix(this.mContext) + "-app";
    }

    private String getVersion() {
        if (this.mContext != null) {
            try {
                return this.mContext.getPackageManager().getPackageInfo(new ComponentName(this.mContext, ProfessionalOmnitureData.class).getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String buildPage(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder(str);
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

    public static String getAppNamePrefix(Context context) {
        if (context == null) {
            return "msp";
        }
        String packageName = context.getPackageName();
        if (packageName.contains("com.medscape.cmepulse")) {
            return "edu";
        }
        if (!packageName.contains("com.medscape.android") && packageName.contains("com.medscape.medpulse")) {
            return "mp";
        }
        return "msp";
    }

    public static String appendAppPrefix(Context context, String str, boolean z) {
        if (z) {
            return str + getAppNamePrefix(context);
        }
        return getAppNamePrefix(context) + str;
    }
}
