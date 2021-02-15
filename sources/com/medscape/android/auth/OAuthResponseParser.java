package com.medscape.android.auth;

import android.app.Activity;
import android.content.Context;
import com.facebook.appevents.UserDataStore;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.google.android.gms.common.Scopes;
import com.medscape.android.Constants;
import com.medscape.android.Settings;
import com.medscape.android.activity.login.LoginManager;
import com.medscape.android.analytics.NotificationAnalyticsHandler;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.consult.util.ConsultConstants;
import com.medscape.android.drugs.UpdateManufacturer;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.parser.model.UserProfile;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OAuthResponseParser {
    static String TAG = OAuthResponseParser.class.getSimpleName();

    public static OAuthResponse parseOAuthResponse(JSONObject jSONObject) {
        OAuthResponse oAuthResponse = null;
        if (jSONObject != null) {
            try {
                OAuthResponse oAuthResponse2 = new OAuthResponse();
                try {
                    int optInt = jSONObject.optInt("status");
                    if (optInt == 0) {
                        int optInt2 = jSONObject.optInt("errorCode");
                        String optString = jSONObject.optString("errorMessage");
                        if (!StringUtil.isNotEmpty(optString)) {
                            optString = "Unknown Error";
                        }
                        oAuthResponse2.setErrorCode(optInt2);
                        oAuthResponse2.setErrorString(optString);
                        return oAuthResponse2;
                    }
                    oAuthResponse2.setStatus(optInt);
                    JSONArray optJSONArray = jSONObject.optJSONArray("cookiename");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            if (StringUtil.isNotEmpty(optJSONArray.getString(i))) {
                                arrayList.add(optJSONArray.getString(i));
                            }
                        }
                        oAuthResponse2.setCookieNames(arrayList);
                    }
                    oAuthResponse = setPatientInstructionStatus(jSONObject, oAuthResponse2);
                    oAuthResponse.setUserProfile(getUserProfile(jSONObject));
                    oAuthResponse.setAccessToken(jSONObject.optString(Constants.OAUTH_ACCESS_TOKEN));
                    oAuthResponse.setRefreshToken(jSONObject.optString(Constants.OAUTH_REFRESH_TOKEN));
                    oAuthResponse.setMaskedGuid(jSONObject.optString("maskedGuid"));
                    return oAuthResponse;
                } catch (Exception unused) {
                    return oAuthResponse2;
                }
            } catch (Exception unused2) {
                return oAuthResponse;
            }
        } else {
            OAuthResponse oAuthResponse3 = new OAuthResponse();
            try {
                oAuthResponse3.setErrorCode(0);
                oAuthResponse3.setErrorString("Unable to parse login response");
            } catch (Exception unused3) {
            }
            return oAuthResponse3;
        }
    }

    private static OAuthResponse setPatientInstructionStatus(JSONObject jSONObject, OAuthResponse oAuthResponse) {
        if (jSONObject != null) {
            String optString = jSONObject.optString("patientInstructionsStatus");
            if (StringUtil.isNotEmpty(optString)) {
                if (optString.equalsIgnoreCase("disabled")) {
                    oAuthResponse.setPatientInstructionStatus(Constants.PATIENT_INSTRUCTION_STATE_DISABLED);
                } else if (optString.equalsIgnoreCase("enabled")) {
                    oAuthResponse.setPatientInstructionStatus(Constants.PATIENT_INSTRUCTION_STATE_ENABLED);
                }
            }
        }
        return oAuthResponse;
    }

    private static UserProfile getUserProfile(JSONObject jSONObject) {
        JSONArray jSONArray;
        UserProfile userProfile = null;
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(Scopes.PROFILE);
        if (optJSONObject != null) {
            userProfile = new UserProfile();
            userProfile.setGradYear(optJSONObject.optString("gradyr"));
            userProfile.setMarketTCID(optJSONObject.optString("marketcd"));
            int optInt = optJSONObject.optInt("guid");
            userProfile.setRegisteredId("" + optInt);
            userProfile.setFirstName(optJSONObject.optString(UserDataStore.FIRST_NAME));
            userProfile.setLastName(optJSONObject.optString(UserDataStore.LAST_NAME));
            userProfile.setHomePageId(optJSONObject.optString("hpid"));
            userProfile.setHomePage(optJSONObject.optString("hpdesc"));
            userProfile.setOccupationId(optJSONObject.optString("occid"));
            userProfile.setProfessionId(optJSONObject.optString("profid"));
            userProfile.setProfession(optJSONObject.optString("profdesc"));
            userProfile.setSpecialtyId(optJSONObject.optString("spid"));
            userProfile.setSpecialty(optJSONObject.optString("spdesc"));
            userProfile.setEmail(optJSONObject.optString("email"));
            userProfile.setAttributes(parseAttributesArray(optJSONObject.optJSONArray(JSONAPISpecConstants.ATTRIBUTES)));
            JSONArray optJSONArray = optJSONObject.optJSONArray("contact");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int i = 0;
                while (true) {
                    if (i < optJSONArray.length()) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null && optJSONObject2.optInt("cntctypeid") == 1) {
                            userProfile.setCountryId(optJSONObject2.optString("co"));
                            userProfile.setStateId(optJSONObject2.optString("st"));
                            break;
                        }
                        i++;
                    }
                }
            }
            try {
                if (userProfile.getProfessionId().equalsIgnoreCase(com.webmd.wbmdproffesionalauthentication.model.UserProfile.PHYSICIAN_ID) && (jSONArray = optJSONObject.getJSONArray("professions")) != null && jSONArray.length() > 0) {
                    userProfile.setAbimID(jSONArray.getJSONObject(0).optString("ABIM_ID"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return setAdsProperties(userProfile, jSONObject);
    }

    public static HashMap<String, String> parseAttributesArray(JSONArray jSONArray) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null && !optJSONObject.isNull("attrid") && !optJSONObject.isNull("val")) {
                    String optString = optJSONObject.optString("attrid");
                    String optString2 = optJSONObject.optString("val");
                    if (!(optString == null || optString.isEmpty() || optString2 == null)) {
                        hashMap.put(optString, optString2);
                    }
                }
            }
        }
        return hashMap;
    }

    private static UserProfile setAdsProperties(UserProfile userProfile, JSONObject jSONObject) {
        if (userProfile != null) {
            userProfile.setProfileSegvar(jSONObject.optString("profilesegvardfp"));
            JSONArray optJSONArray = jSONObject.optJSONArray("adTargetingDfp");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        String optString = optJSONObject.optString("siteOn");
                        String optString2 = optJSONObject.optString("tid");
                        if (StringUtil.isNotEmpty(optString) && StringUtil.isNotEmpty(optString2)) {
                            userProfile.getTidMap().put(optString, optString2);
                        }
                    }
                }
            }
        }
        return userProfile;
    }

    public static int parseResponse(Context context, Object obj, Activity activity) {
        List<String> cookieNames;
        if (obj == null || !(obj instanceof JSONObject)) {
            return -1;
        }
        JSONObject jSONObject = (JSONObject) obj;
        OAuthResponse parseOAuthResponse = parseOAuthResponse(jSONObject);
        if (parseOAuthResponse == null) {
            return 3009;
        }
        if (parseOAuthResponse.getErrorCode() == 200 || !StringUtil.isNotEmpty(parseOAuthResponse.getErrorString())) {
            String responseCookies = getResponseCookies(jSONObject);
            String[] strArr = null;
            if (responseCookies != null && !responseCookies.isEmpty()) {
                strArr = responseCookies.split(";");
            }
            if (strArr != null && strArr.length > 0 && (cookieNames = parseOAuthResponse.getCookieNames()) != null && cookieNames.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (String next : cookieNames) {
                    for (String str : strArr) {
                        if (str.contains(next)) {
                            sb.append(str);
                            sb.append("; ");
                        }
                    }
                }
                if (!StringUtil.isNotEmpty(sb.toString())) {
                    return 3008;
                }
                Settings.singleton(context).saveSetting(Constants.PREF_COOKIE_STRING, sb.toString());
            }
            AuthenticationManager instance = AuthenticationManager.getInstance(context);
            if (instance != null) {
                instance.setAuthenticationToken(parseOAuthResponse.getAccessToken());
                instance.setRefreshToken(parseOAuthResponse.getRefreshToken());
                instance.setMaskedGuid(parseOAuthResponse.getMaskedGuid());
                LoginManager.storeGuid(context, parseOAuthResponse.getMaskedGuid());
            }
            UserProfile userProfile = parseOAuthResponse.getUserProfile();
            UserProfileProvider.INSTANCE.setUserProfile(userProfile, context);
            if (userProfile != null) {
                Settings.singleton(context).saveSetting(Constants.REGISTERED_ID, (Long.parseLong(userProfile.getRegisteredId()) * 27) + "");
                Settings.singleton(context).saveSetting(Constants.USER_STATE_ID, userProfile.getStateId());
                Settings.singleton(context).saveSetting(Constants.HOME_PAGE_ID, userProfile.getHomePageId());
                Settings.singleton(context).saveSetting("wbmd.medscape.specialty.id", userProfile.getSpecialtyId());
                Settings.singleton(context).saveSetting(Constants.USER_DISPLAYNAME, userProfile.getDisplayName(context));
                Settings.singleton(context).saveSetting("wbmd_professionId", userProfile.getProfessionId());
                Settings.singleton(context).saveSetting(Constants.PROFESSION, userProfile.getProfession());
                Settings.singleton(context).saveSetting(Constants.OCCUPATION_ID, userProfile.getOccupationId());
                Settings.singleton(context).saveSetting(Constants.USER_COUNTRY_CODE, userProfile.getCountryId());
                Settings.singleton(context).saveSetting(Constants.USER_EMAIL, userProfile.getEmail());
                Settings.singleton(context).saveSetting(ConsultConstants.MED_STUDENTS_FILTER_SHOW, userProfile.getAttributes().get(ConsultConstants.MED_STUDENTS_FILTER_SHOW));
                Settings.singleton(context).saveSetting(ConsultConstants.MED_STUDENTS_FILTER_NOTIFICATION, userProfile.getAttributes().get(ConsultConstants.MED_STUDENTS_FILTER_NOTIFICATION));
                Settings.singleton(context).saveSetting(ConsultConstants.GLOBAL_POSTS_SHOW, userProfile.getAttributes().get(ConsultConstants.GLOBAL_POSTS_SHOW));
                Settings.singleton(context).saveSetting(ConsultConstants.GLOBAL_POSTS_SHOW, userProfile.getAttributes().get(ConsultConstants.GLOBAL_POSTS_SHOW));
            }
            new UpdateManufacturer(context).getAllManufacturer();
            if (activity != null) {
                NotificationAnalyticsHandler.INSTANCE.tagLoggedInUserAttributes(context, activity);
            }
            CapabilitiesManager.getInstance(context).updateCapabilitiesIfNecessary();
            return Constants.AUTHENTICATION_STATUS_ACCEPTED;
        } else if (parseOAuthResponse.getErrorCode() == 101) {
            return Constants.AUTHENTICATION_STATUS_INVALID;
        } else {
            return 3009;
        }
    }

    private static String getResponseCookies(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("headers");
            if (jSONObject2 == null) {
                return "";
            }
            return "" + jSONObject2.optString("Set-Cookie");
        } catch (JSONException unused) {
            return "";
        }
    }
}
