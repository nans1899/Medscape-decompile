package com.webmd.wbmdcmepulse.models.parsers;

import android.content.Context;
import com.facebook.appevents.UserDataStore;
import com.google.android.gms.common.Scopes;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdproffesionalauthentication.model.BasicInfo;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserProfileParser {
    public static UserProfile getUserProfile(Context context, JSONObject jSONObject, boolean z) {
        JSONObject optJSONObject;
        UserProfile userProfile = new UserProfile();
        UserProfession professionProfile = userProfile.getProfessionProfile();
        BasicInfo basicProfile = userProfile.getBasicProfile();
        if (!(jSONObject == null || (optJSONObject = jSONObject.optJSONObject(Scopes.PROFILE)) == null)) {
            storeProfileValue(Constants.LOGIN_PREF_HOMEPAGEID, optJSONObject.optString("hpid"), z, context);
            professionProfile.setOccupationId(storeProfileValue(Constants.LOGIN_PREF_OCCUPATIONKEY, optJSONObject.optString("occid"), z, context));
            professionProfile.setOccupation(storeProfileValue(Constants.LOGIN_PREF_OCCUPATIONSTRINGKEY, optJSONObject.optString("occdesc"), z, context));
            String storeProfileValue = storeProfileValue(Constants.LOGIN_PREF_PROFESSIONKEY, optJSONObject.optString("profid"), z, context);
            professionProfile.setProfessionId(storeProfileValue);
            professionProfile.setProfession(storeProfileValue(Constants.LOGIN_PREF_PROFESSIONSTRINGKEY, optJSONObject.optString("profdesc"), z, context));
            professionProfile.setSpecialityId(storeProfileValue(Constants.LOGIN_PREF_SPECIALTYKEY, optJSONObject.optString("spid"), z, context));
            professionProfile.setSpeciality(storeProfileValue(Constants.LOGIN_PREF_SPECIALTYSTRINGKEY, optJSONObject.optString("spdesc"), z, context));
            String storeProfileValue2 = storeProfileValue("userGuid", optJSONObject.optInt("guid") + "", z, context);
            try {
                long parseLong = Long.parseLong(storeProfileValue2);
                storeProfileValue2 = "" + (parseLong * 27);
            } catch (Exception e) {
                e.printStackTrace();
            }
            basicProfile.setUserId(storeProfileValue2);
            if (z) {
                try {
                    SharedPreferenceProvider.get().saveCryptoEncrypted("userGuid", storeProfileValue2, context);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            basicProfile.setFirstName(storeProfileValue(Constants.LOGIN_PREF_FIRSTNAMEKEY, optJSONObject.optString(UserDataStore.FIRST_NAME), z, context));
            basicProfile.setLastName(storeProfileValue(Constants.LOGIN_PREF_LASTNAMEKEY, optJSONObject.optString(UserDataStore.LAST_NAME), z, context));
            JSONArray optJSONArray = optJSONObject.optJSONArray("contact");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int i = 0;
                while (true) {
                    if (i < optJSONArray.length()) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                        if (optJSONObject2 != null && optJSONObject2.optInt("cntctypeid") == 1) {
                            professionProfile.setCountryCode(storeProfileValue(Constants.LOGIN_PREF_WORKCOUNTRYKEY, optJSONObject2.optString("co"), z, context));
                            professionProfile.setState(storeProfileValue(Constants.LOGIN_PREF_WORKSTATEKEY, optJSONObject2.optString("st"), z, context));
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            }
            if (storeProfileValue.equalsIgnoreCase(UserProfile.PHYSICIAN_ID)) {
                try {
                    JSONArray jSONArray = optJSONObject.getJSONArray("professions");
                    if (jSONArray != null && jSONArray.length() > 0) {
                        professionProfile.setAbimId(storeProfileValue("userMocString", jSONArray.getJSONObject(0).optString("ABIM_ID"), z, context));
                    }
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return userProfile;
    }

    private static String storeProfileValue(String str, String str2, boolean z, Context context) {
        if (!z) {
            return str2;
        }
        if (str2 != null) {
            try {
                if (str.equalsIgnoreCase(Constants.LOGIN_PREF_HOMEPAGEID)) {
                    String simpleCryptoDecryptedString = SharedPreferenceProvider.get().getSimpleCryptoDecryptedString(Constants.LOGIN_PREF_HOMEPAGEID, "", context);
                    if (!Extensions.isStringNullOrEmpty(simpleCryptoDecryptedString)) {
                        if (!simpleCryptoDecryptedString.equalsIgnoreCase(str2)) {
                            SharedPreferenceProvider.get().saveCryptoEncrypted(Constants.LOGIN_PREF_PREVIOUS_HPID, simpleCryptoDecryptedString, context);
                        }
                    }
                    SharedPreferenceProvider.get().saveCryptoEncrypted(Constants.LOGIN_PREF_PREVIOUS_HPID, "", context);
                }
                SharedPreferenceProvider.get().saveCryptoEncrypted(str, str2, context);
                return str2;
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
