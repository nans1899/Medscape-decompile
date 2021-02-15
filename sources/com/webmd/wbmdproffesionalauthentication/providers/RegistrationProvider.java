package com.webmd.wbmdproffesionalauthentication.providers;

import android.content.Context;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.model.EnvironmentData;
import com.webmd.wbmdproffesionalauthentication.model.Speciality;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.utilities.EnvironmentUtil;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class RegistrationProvider {
    private static RegistrationProvider instance = new RegistrationProvider();
    /* access modifiers changed from: private */
    public static Context mContext;
    JSONObject profRegData;
    Map<String, Speciality> specialities;

    private RegistrationProvider() {
    }

    public static RegistrationProvider getInstance(Context context) {
        mContext = context;
        return instance;
    }

    public void loadRegistrationData(final ICallbackEvent<JSONObject, String> iCallbackEvent) {
        VolleyRestClient.getInstance(mContext).get(EnvironmentUtil.Companion.getUrl(mContext, EnvironmentData.REGISTRATION_DATA_URL) + "?src=" + Util.getAppNameForUrl(mContext), 1, new ICallbackEvent<Object, Exception>() {
            public void onError(Exception exc) {
                ICallbackEvent iCallbackEvent = iCallbackEvent;
                if (iCallbackEvent != null) {
                    iCallbackEvent.onError(VolleyErrorConverter.exceptionToMessage(RegistrationProvider.mContext, exc));
                }
            }

            public void onCompleted(Object obj) {
                if (obj instanceof JSONObject) {
                    JSONObject jSONObject = (JSONObject) obj;
                    if (jSONObject == null || !jSONObject.has("localeCountryMap") || jSONObject.optJSONObject("localeCountryMap") == null || jSONObject.optJSONObject("localeCountryMap").optJSONArray("en_us") == null || jSONObject.optJSONObject("localeCountryMap").optJSONArray("en_us").length() <= 0) {
                        ICallbackEvent iCallbackEvent = iCallbackEvent;
                        if (iCallbackEvent != null) {
                            iCallbackEvent.onError(RegistrationProvider.mContext.getString(R.string.error_service_unavailable));
                            return;
                        }
                        return;
                    }
                    RegistrationProvider.this.setProfRegData(jSONObject);
                    iCallbackEvent.onCompleted(jSONObject);
                }
            }
        });
    }

    public void setProfRegData(JSONObject jSONObject) {
        this.profRegData = jSONObject;
        Map<String, Speciality> map = this.specialities;
        if (map != null) {
            map.clear();
        }
    }

    public Map<String, String> getSpeciality(String str) {
        this.specialities = new LinkedHashMap();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            JSONObject selectedProfessionJSON = getSelectedProfessionJSON("us", str);
            if (selectedProfessionJSON != null) {
                this.specialities = getSpecialities(selectedProfessionJSON);
            }
        } catch (Exception unused) {
        }
        Map<String, Speciality> map = this.specialities;
        if (map != null) {
            for (String next : map.keySet()) {
                linkedHashMap.put(next, this.specialities.get(next).getId());
            }
        }
        return linkedHashMap;
    }

    public Map<String, String> getSubSpeciality(String str, String str2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            if (this.specialities == null || this.specialities.size() == 0) {
                getSpeciality(str);
            }
            return (this.specialities == null || !this.specialities.containsKey(str2)) ? linkedHashMap : this.specialities.get(str2).getSubSpecialities();
        } catch (Exception unused) {
            return linkedHashMap;
        }
    }

    public Map<String, String> getOccupations(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            JSONObject selectedProfessionJSON = getSelectedProfessionJSON("us", str);
            return selectedProfessionJSON != null ? getNameIdMap(selectedProfessionJSON, "occupations", "name", "id") : linkedHashMap;
        } catch (Exception unused) {
            return linkedHashMap;
        }
    }

    public Map<String, String> getCountries(String str) {
        Map<String, String> map;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            if (this.profRegData == null || !this.profRegData.has("localeCountryMap")) {
                return linkedHashMap;
            }
            JSONObject jSONObject = this.profRegData.getJSONObject("localeCountryMap");
            if (jSONObject.has(str)) {
                map = getNameIdMap(jSONObject, str.toString(), "name", "abbreviation");
            } else {
                map = getNameIdMap(jSONObject, "en_us", "name", "abbreviation");
            }
            return map;
        } catch (Exception unused) {
            return linkedHashMap;
        }
    }

    public Map<String, String> getAvailableProfessions(String str) {
        JSONObject jSONObject;
        Map<String, String> map;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            if (this.profRegData == null || !this.profRegData.has("localeCountryMap") || (jSONObject = this.profRegData.getJSONObject("localeProfessionalMapping")) == null) {
                return linkedHashMap;
            }
            if (str.contains("us")) {
                map = getProfessionNameIdMap(jSONObject, "en_us", "en", "id");
            } else {
                map = getProfessionNameIdMap(jSONObject, Constants.COUNTRY_CODE_NON_US, "en", "id");
            }
            return map;
        } catch (Exception unused) {
            return linkedHashMap;
        }
    }

    public Map<String, String> getStates() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            return (this.profRegData == null || !this.profRegData.has("medSchoolLocationList")) ? linkedHashMap : getMedschoolStateList(this.profRegData);
        } catch (Exception unused) {
            return linkedHashMap;
        }
    }

    public Map<String, String> getSchools(String str) {
        JSONArray optJSONArray;
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            if (this.profRegData != null && this.profRegData.has("medSchoolLocationList") && (optJSONArray = this.profRegData.optJSONArray("medSchoolLocationList")) != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject = optJSONArray.getJSONObject(i);
                    if (jSONObject.optString("stateAbbreviation", "").equalsIgnoreCase(str)) {
                        linkedHashMap = getNameIdMap(jSONObject, "medschoolList", "name", "id");
                    }
                }
            }
        } catch (Exception unused) {
        }
        return linkedHashMap;
    }

    public Map<String, String> getNonUSASchools(String str) {
        JSONArray optJSONArray;
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        try {
            if (this.profRegData != null && this.profRegData.has("medSchoolLocationList") && (optJSONArray = this.profRegData.optJSONArray("medSchoolLocationList")) != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject = optJSONArray.getJSONObject(i);
                    if (jSONObject.optString("countryAbbreviation", "").equalsIgnoreCase(str)) {
                        linkedHashMap = getNameIdMap(jSONObject, "medschoolList", "name", "id");
                    }
                }
            }
        } catch (Exception unused) {
        }
        return linkedHashMap;
    }

    public boolean registerUser(final Context context, final UserProfile userProfile, final ICallbackEvent<Boolean, String> iCallbackEvent) {
        String str = EnvironmentUtil.Companion.getUrl(context, EnvironmentData.REGISTER_URL) + "?src=" + Util.getAppNameForUrl(context);
        HashMap hashMap = new HashMap();
        hashMap.put("isEncrypted", "false");
        if (context == null || userProfile == null) {
            iCallbackEvent.onError(context.getString(R.string.error_oauth_request_failed));
            return false;
        }
        try {
            VolleyRestClient.getInstance(mContext).post(str, userProfile.toJSON(context), (Map<String, String>) hashMap, (ICallbackEvent) new ICallbackEvent() {
                /* JADX WARNING: Removed duplicated region for block: B:40:0x0141  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onError(java.lang.Object r5) {
                    /*
                        r4 = this;
                        com.wbmd.wbmdcommons.callbacks.ICallbackEvent r0 = r8
                        if (r0 == 0) goto L_0x0150
                        android.content.Context r0 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext
                        android.content.res.Resources r0 = r0.getResources()
                        int r1 = com.webmd.wbmdproffesionalauthentication.R.string.error_register_failed
                        java.lang.String r0 = r0.getString(r1)
                        java.lang.Exception r1 = new java.lang.Exception
                        r1.<init>(r0)
                        boolean r2 = r5 instanceof java.lang.Exception
                        if (r2 == 0) goto L_0x0024
                        r1 = r5
                        java.lang.Exception r1 = (java.lang.Exception) r1
                        android.content.Context r5 = r6
                        java.lang.String r0 = com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter.exceptionToMessage(r5, r1)
                    L_0x0024:
                        org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x012d }
                        r5.<init>(r0)     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = "errorDescription"
                        java.lang.String r5 = r5.optString(r2)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.isEmpty()     // Catch:{ JSONException -> 0x012d }
                        if (r2 != 0) goto L_0x0131
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_email_address     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x0051
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_please_enter_valid_email_address     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x0051:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_username     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x006d
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_please_enter_valid_username     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x006d:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_password     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x0089
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_please_enter_valid_password_min_characters     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x0089:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_firstname     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x00a5
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_please_enter_valid_firstname     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x00a5:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_lastname     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x00c1
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_please_enter_valid_lastname     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x00c1:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_password_empty     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x00dc
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_please_enter_valid_password     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x00dc:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_email_address_already_exists     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x00f7
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_email_address_entered_already_in_use     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x00f7:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_username_already_exists     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x0112
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_username_entered_already_in_use     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x0112:
                        android.content.Context r2 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r3 = com.webmd.wbmdproffesionalauthentication.R.string.error_wrong_value_of_password_invalid     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r2 = r2.getString(r3)     // Catch:{ JSONException -> 0x012d }
                        boolean r2 = r5.equalsIgnoreCase(r2)     // Catch:{ JSONException -> 0x012d }
                        if (r2 == 0) goto L_0x0132
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext     // Catch:{ JSONException -> 0x012d }
                        int r2 = com.webmd.wbmdproffesionalauthentication.R.string.error_please_enter_valid_password     // Catch:{ JSONException -> 0x012d }
                        java.lang.String r5 = r5.getString(r2)     // Catch:{ JSONException -> 0x012d }
                        goto L_0x0132
                    L_0x012d:
                        r5 = move-exception
                        r5.printStackTrace()
                    L_0x0131:
                        r5 = r0
                    L_0x0132:
                        com.wbmd.wbmdcommons.callbacks.ICallbackEvent r2 = r8
                        r2.onError(r5)
                        java.lang.String r5 = r1.getMessage()
                        boolean r5 = com.wbmd.wbmdcommons.utils.Extensions.IsNullOrEmpty(r5)
                        if (r5 == 0) goto L_0x0146
                        java.lang.Exception r1 = new java.lang.Exception
                        r1.<init>(r0)
                    L_0x0146:
                        android.content.Context r5 = com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.mContext
                        r2 = 0
                        java.lang.String r3 = "reg_error"
                        com.webmd.wbmdproffesionalauthentication.providers.AccountProvider.logCrashlyticEvent(r5, r1, r3, r0, r2)
                    L_0x0150:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider.AnonymousClass2.onError(java.lang.Object):void");
                }

                public void onCompleted(Object obj) {
                    AccountProvider.signInUser(context, userProfile.getBasicProfile().getEmailAddress(), userProfile.getBasicProfile().getPassword(), 2, new ICallbackEvent<Object, Exception>() {
                        public void onError(Exception exc) {
                            iCallbackEvent.onError(context.getString(R.string.error_oauth_failed));
                        }

                        public void onCompleted(Object obj) {
                            String parseLoginError = LoginErrorParser.parseLoginError(context, (JSONObject) obj);
                            if (StringExtensions.isNullOrEmpty(parseLoginError)) {
                                iCallbackEvent.onCompleted(true);
                            } else {
                                iCallbackEvent.onError(parseLoginError);
                            }
                        }
                    });
                }
            });
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Map<String, String> getProfessionNameIdMap(JSONObject jSONObject, String str, String str2, String str3) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String optString = jSONObject2.getJSONObject("translatedValues").optString(str2, "");
                String optString2 = jSONObject2.optString(str3, "");
                if (!linkedHashMap.containsKey(optString) && StringExtensions.isNotEmpty(optString) && StringExtensions.isNotEmpty(optString2)) {
                    linkedHashMap.put(optString, optString2);
                }
            }
        }
        return linkedHashMap;
    }

    private Map<String, String> getNameIdMap(JSONObject jSONObject, String str, String str2, String str3) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String optString = jSONObject2.optString(str2, "");
                String optString2 = jSONObject2.optString(str3, "");
                if (!linkedHashMap.containsKey(optString) && StringExtensions.isNotEmpty(optString) && StringExtensions.isNotEmpty(optString2)) {
                    linkedHashMap.put(optString, optString2);
                }
            }
        }
        return linkedHashMap;
    }

    private JSONObject getSelectedProfessionJSON(String str, String str2) throws Exception {
        JSONObject jSONObject;
        JSONArray jSONArray;
        JSONObject jSONObject2 = this.profRegData;
        if (!(jSONObject2 == null || !jSONObject2.has("localeCountryMap") || (jSONObject = this.profRegData.getJSONObject("localeProfessionalMapping")) == null)) {
            if (str.equalsIgnoreCase("us")) {
                jSONArray = jSONObject.getJSONArray("en_us");
            } else {
                jSONArray = jSONObject.getJSONArray(Constants.COUNTRY_CODE_NON_US);
            }
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                    if (jSONObject3.optString("id", "").equalsIgnoreCase(str2)) {
                        return jSONObject3;
                    }
                }
            }
        }
        return null;
    }

    private Map<String, Speciality> getSpecialities(JSONObject jSONObject) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONArray optJSONArray = jSONObject.optJSONArray("specialties");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                Speciality speciality = new Speciality();
                String optString = jSONObject2.optString("name", "");
                speciality.setId(jSONObject2.optString("id", ""));
                speciality.setName(optString);
                speciality.setSubSpecialities(getNameIdMap(jSONObject2, "subSpecialities", "name", "id"));
                if (!linkedHashMap.containsKey(optString)) {
                    linkedHashMap.put(optString, speciality);
                }
            }
        }
        return linkedHashMap;
    }

    private Map<String, String> getMedschoolStateList(JSONObject jSONObject) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        JSONArray optJSONArray = jSONObject.optJSONArray("medSchoolLocationList");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String optString = jSONObject2.optString("stateName", "");
                String optString2 = jSONObject2.optString("stateAbbreviation", "");
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("medschoolList");
                if (optJSONArray2 != null && optJSONArray2.length() > 0 && StringExtensions.isNotEmpty(optString) && StringExtensions.isNotEmpty(optString2) && !linkedHashMap.containsKey(optString)) {
                    linkedHashMap.put(optString, optString2);
                }
            }
        }
        return linkedHashMap;
    }
}
