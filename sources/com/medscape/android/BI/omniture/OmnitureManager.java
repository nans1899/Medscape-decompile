package com.medscape.android.BI.omniture;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.adobe.mobile.Analytics;
import com.adobe.mobile.Config;
import com.adobe.mobile.MobilePrivacyStatus;
import com.adobe.mobile.Visitor;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.activity.calc.helper.CalcOmnitureHelper;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.parser.model.MetricsUserProfile;
import com.medscape.android.parser.model.UserProfile;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.wbmd.ads.model.AdContentData;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.Extensions;
import com.webmd.wbmdomnituremanager.ProfessionalOmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureUtil;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OmnitureManager {
    public static final String ACTION = "action";
    public static final String APP_APPID = "app-msp";
    public static final String APP_NAME = "wapp.site";
    public static final String ASSET_ID = "wapp.asset";
    public static final String BU = "wapp.bu";
    public static final String CHANNEL = "wapp.chn";
    public static final String CONTENT_TYPE_ID = "wapp.contypeid";
    private static final String DEFAULT_MODULE_NAME = "entry-module-default";
    public static final String DESTINATION_URL = "wapp.desurl";
    public static final String EXIT_URL = "wapp.exiturl";
    public static final String HEIRARCHY = "wapp.hier1";
    public static final String HOME_SCREEN_PAGE = "medscape.com/app/home-screen/view";
    public static final String LEAD_CONCEPT_ID = "wapp.ldcncptid";
    public static final String LEAD_SPECIALITY_ID = "wapp.ldspecid";
    public static final String MAID_ID = "wapp.maid";
    public static final String MCONTYPEID = "wapp.mcontypeid";
    public static final String MLDCNCPTID = "wapp.mldcncptid";
    public static final String MOBILE_BUILD = "wapp.build";
    public static final String MPG_TITLE = "wapp.mpgtitle";
    public static final String MPVID = "wapp.mpvid";
    public static final String OTT_TEST_VAR = "wapp.test";
    public static final String PAGENAME = "pageName";
    public static final String PG_TITLE = "wapp.pgtitle";
    private static final String PREFIX = "medscape.com/app/";
    public static final String PUBLICATION_ID = "wapp.pubid";
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
    public static final String USER_OCCUPATION_ID = "wapp.doccptnid";
    public static final String USER_PROFESSION = "wapp.dprofsn";
    public static final String USER_PROFESSION_ID = "wapp.dprofsnid";
    public static final String USER_SPECIALTY = "wapp.dspclty";
    public static final String USER_SPECIALTY_ID = "wapp.dspcltyid";
    public static final String VISITOR_VALUE = "wapp.vapi";
    private static final OmnitureManager mOmnitureManager = new OmnitureManager();
    public static Map<String, Object> sReferringData = new HashMap();
    private Map<String, Object> mCurrentModuleData = new HashMap();
    private String mCurrentPageName = null;
    private Map<String, Object> mData;
    private Map<String, Object> mSavedPVModuleData = new HashMap();
    public String mSearchChannel = "other";

    private OmnitureManager() {
        HashMap hashMap = new HashMap();
        this.mData = hashMap;
        fillAppData(hashMap);
    }

    public static OmnitureManager get() {
        return mOmnitureManager;
    }

    private boolean isOmnitureDebugDipslayEnabled() {
        return MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEBUG_OMNITURE_TAG_TOGGLE, false);
    }

    private void displayOmnitureTag(Context context, String str) {
        if (context != null) {
            try {
                if (!Extensions.IsNullOrEmpty(str) && isOmnitureDebugDipslayEnabled()) {
                    Toast.makeText(context, str, 0).show();
                }
            } catch (Exception e) {
                Trace.e(TAG, e.getMessage());
            }
        }
    }

    public String trackPageView(Context context, String str, String str2, String str3, String str4, String str5, Map<String, Object> map, boolean z, String str6) {
        fillContextData(context, this.mData);
        fillUserData(this.mData, context);
        if (str6 == null || str6.isEmpty()) {
            str6 = generatePVID();
        }
        String buildPageName = buildPageName(str2, str3, str4, str5);
        if (!z) {
            this.mCurrentPageName = buildPageName;
        }
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.mData);
        hashMap.putAll(this.mSavedPVModuleData);
        hashMap.putAll(this.mCurrentModuleData);
        hashMap.put("wapp.pvid", str6);
        hashMap.put("wapp.chn", str);
        if (map != null) {
            hashMap.putAll(map);
        }
        Analytics.trackState(buildPageName, hashMap);
        this.mCurrentModuleData.clear();
        this.mSavedPVModuleData.clear();
        displayOmnitureTag(context, String.format("Page View: %s", new Object[]{buildPageName}));
        return str6;
    }

    public String trackPageView(Context context, String str, String str2, String str3, String str4, String str5, Map<String, Object> map) {
        return trackPageView(context, str, str2, str3, str4, str5, map, false, (String) null);
    }

    public String trackPageViewWithPrevious(Context context, String str, String str2) {
        fillContextData(context, this.mData);
        fillUserData(this.mData, context);
        String generatePVID = generatePVID();
        StringBuilder sb = new StringBuilder();
        sb.append(this.mCurrentPageName);
        sb.append(str);
        HashMap hashMap = new HashMap();
        hashMap.putAll(this.mData);
        hashMap.putAll(this.mCurrentModuleData);
        hashMap.put("wapp.pvid", generatePVID);
        if (str2 != null && !str2.isEmpty()) {
            hashMap.put("wapp.asset", str2);
        }
        Analytics.trackState(sb.toString(), hashMap);
        displayOmnitureTag(context, String.format("Page View: %s", new Object[]{sb}));
        return generatePVID;
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
        displayOmnitureTag(context, String.format("Action: %s", new Object[]{buildActionName}));
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
        displayOmnitureTag(context, String.format("Action: %s", new Object[]{buildActionNameAbsolute}));
        this.mCurrentModuleData.clear();
    }

    public void markModule(boolean z, String str, String str2, Map<String, Object> map) {
        if (str != null) {
            if (z) {
                this.mCurrentPageName = buildPageName(DEFAULT_MODULE_NAME, (String) null, (String) null, (String) null);
            }
            this.mCurrentModuleData.put("wapp.mmodule", str);
            if (StringUtil.isNotEmpty(str2)) {
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

    public void markModule(String str, String str2, Map<String, Object> map, boolean z) {
        markModule(false, str, str2, map);
        if (z) {
            this.mSavedPVModuleData.clear();
            this.mSavedPVModuleData.putAll(this.mCurrentModuleData);
        }
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
            if (str == null || !str.equals("drug")) {
                sb.append("/");
                sb.append(str4);
            } else {
                sb.append(str4.replaceAll("[0-9]", ""));
            }
        }
        return sb.toString();
    }

    private void fillUserData(Map<String, Object> map, Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.regid", "000000000");
        hashMap.put("wapp.dcntry", AdError.UNDEFINED_DOMAIN);
        hashMap.put("wapp.dprofsn", AdError.UNDEFINED_DOMAIN);
        hashMap.put("wapp.dspclty", AdError.UNDEFINED_DOMAIN);
        UserProfile userProfile = UserProfileProvider.INSTANCE.getUserProfile();
        boolean isUserLoggedIn = AccountProvider.isUserLoggedIn(context);
        if (userProfile != null) {
            hashMap.put("wapp.regid", userProfile.getEncryptedRegisteredId());
            hashMap.put("wapp.dcntry", userProfile.getCountryId());
            if (StringExtensions.isNotEmpty(userProfile.getOccupationId())) {
                hashMap.put("wapp.doccptnid", userProfile.getOccupationId());
            }
            String profession = userProfile.getProfession();
            if (profession != null) {
                hashMap.put("wapp.dprofsn", profession.toLowerCase());
            }
            String professionId = userProfile.getProfessionId();
            if (professionId != null) {
                hashMap.put("wapp.dprofsnid", professionId);
            }
            String specialty = userProfile.getSpecialty();
            if (specialty != null) {
                hashMap.put("wapp.dspclty", specialty.toLowerCase());
            }
            String specialtyId = userProfile.getSpecialtyId();
            if (specialtyId != null) {
                hashMap.put("wapp.dspcltyid", specialtyId);
            }
        } else if (userProfile == null && isUserLoggedIn) {
            MetricsUserProfile metricsUserProfile = UserProfileProvider.INSTANCE.getMetricsUserProfile(context);
            if (metricsUserProfile != null) {
                hashMap.put("wapp.regid", metricsUserProfile.getEncryptedRegisteredId());
                hashMap.put("wapp.dcntry", metricsUserProfile.getCountryId());
                String profession2 = metricsUserProfile.getProfession();
                if (profession2 != null) {
                    hashMap.put("wapp.dprofsn", profession2.toLowerCase());
                }
                String professionID = metricsUserProfile.getProfessionID();
                if (professionID != null) {
                    hashMap.put("wapp.dprofsnid", professionID);
                }
                String specialty2 = metricsUserProfile.getSpecialty();
                if (specialty2 != null) {
                    hashMap.put("wapp.dspclty", specialty2.toLowerCase());
                }
                String specialtyID = metricsUserProfile.getSpecialtyID();
                if (specialtyID != null) {
                    hashMap.put("wapp.dspcltyid", specialtyID);
                }
            }
            String setting = Settings.singleton(context).getSetting(Constants.OCCUPATION_ID, "");
            if (StringExtensions.isNotEmpty(setting)) {
                hashMap.put("wapp.doccptnid", setting);
            }
        }
        ProfessionalOmnitureData.mAdditonalDefaultData = hashMap;
        CalcOmnitureHelper.saveUserProfileData(hashMap);
        map.putAll(hashMap);
    }

    private String buildActionName(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        } else {
            sb.append(buildPageName(DEFAULT_MODULE_NAME, (String) null, (String) null, (String) null));
        }
        if (StringUtil.isNotEmpty(str)) {
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
        if (StringUtil.isNotEmpty(str2)) {
            sb.append("_");
            sb.append(str2);
        }
        if (StringUtil.isNotEmpty(str3)) {
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
        hashMap.put(VISITOR_VALUE, StringUtil.isNotEmpty(Visitor.getMarketingCloudId()) ? "VisitorAPI Present" : "VisitorAPI Missing");
        map.putAll(hashMap);
    }

    private void fillContextData(Context context, Map<String, Object> map) {
        map.put("wapp.build", "app-msp_" + Util.getApplicationVersion(context) + "_android");
    }

    public String generatePVID() {
        return WBMDOmnitureUtil.generateNewPvid();
    }

    public void setmCurrentPageName(String str) {
        this.mCurrentPageName = str;
    }

    public String getmCurrentPageName() {
        String str = this.mCurrentPageName;
        return str == null ? "" : str;
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

    public HashMap<String, Object> getContentBasedOmnitureData(HashMap<String, String> hashMap, int i) {
        HashMap<String, Object> hashMap2 = new HashMap<>();
        if (i > 0) {
            hashMap2.put("wapp.asset", String.valueOf(i));
        }
        if (hashMap != null && hashMap.size() > 0) {
            if (StringExtensions.isNotEmpty(hashMap.get(AdContentData.LEAD_CONCEPT))) {
                hashMap2.put("wapp.ldcncptid", hashMap.get(AdContentData.LEAD_CONCEPT) + "p");
            }
            if (StringExtensions.isNotEmpty(hashMap.get(AdContentData.LEAD_SPECIALITY))) {
                hashMap2.put("wapp.ldspecid", hashMap.get(AdContentData.LEAD_SPECIALITY));
            }
            if (StringExtensions.isNotEmpty(hashMap.get("pub"))) {
                hashMap2.put(PUBLICATION_ID, hashMap.get("pub"));
            }
            if (StringExtensions.isNotEmpty(hashMap.get("cg"))) {
                hashMap2.put("wapp.contypeid", hashMap.get("cg"));
            }
        }
        return hashMap2;
    }

    public void setReferringData(HashMap<String, String> hashMap, String str, String str2) {
        if (hashMap != null && hashMap.size() > 0) {
            if (StringExtensions.isNotEmpty(hashMap.get(AdContentData.LEAD_CONCEPT))) {
                Map<String, Object> map = sReferringData;
                map.put(MLDCNCPTID, hashMap.get(AdContentData.LEAD_CONCEPT) + "p");
            }
            if (StringExtensions.isNotEmpty(hashMap.get("cg"))) {
                sReferringData.put(MCONTYPEID, hashMap.get("cg"));
            }
        }
        sReferringData.put(MPVID, str);
        sReferringData.put(MPG_TITLE, str2);
    }

    public void setOmniturePrivacyStatus(boolean z, Context context) {
        setOmniturePrivacyOnly(z);
        collectOmnitureLifeCycleData(context);
    }

    public void setOmniturePrivacyOnly(boolean z) {
        if (z) {
            Config.setPrivacyStatus(MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN);
        } else {
            Config.setPrivacyStatus(MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT);
        }
    }

    public void collectOmnitureLifeCycleData(Context context) {
        new Thread(new Runnable(context) {
            public final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                OmnitureManager.lambda$collectOmnitureLifeCycleData$0(this.f$0);
            }
        }).start();
    }

    static /* synthetic */ void lambda$collectOmnitureLifeCycleData$0(Context context) {
        String str = "00000000-0000-0000-0000-000000000000";
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (!advertisingIdInfo.isLimitAdTrackingEnabled() && Config.getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
                str = advertisingIdInfo.getId();
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
        }
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.maid", str);
        if (StringUtil.isNotEmpty(str)) {
            hashMap.put("wapp.maid", str);
        }
        if (context instanceof Activity) {
            Config.collectLifecycleData((Activity) context, hashMap);
        }
    }
}
