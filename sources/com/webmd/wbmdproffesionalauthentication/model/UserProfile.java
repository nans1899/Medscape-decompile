package com.webmd.wbmdproffesionalauthentication.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.cache.FeedCache;
import com.medscape.android.settings.Settings;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.SpecialCountriesRegistrationData;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserProfile implements Parcelable {
    public static final Parcelable.Creator<UserProfile> CREATOR = new Parcelable.Creator<UserProfile>() {
        public UserProfile createFromParcel(Parcel parcel) {
            return new UserProfile(parcel);
        }

        public UserProfile[] newArray(int i) {
            return new UserProfile[i];
        }
    };
    public static final String MEDICAL_LABORATORY_ID = "16";
    public static final String MIDWIFE_ID = "21";
    public static final String NURSE_ID = "12";
    public static final String NURSE_PRACTITIONER_ID = "5";
    public static final String OHCP_ID = "15";
    public static final String PHARMACIST_ID = "8";
    public static final String PHYSICIAN_ID = "10";
    public static final String PHYS_ASST_ID = "11";
    public static final String PSYCHOLOGIST_ID = "18";
    public static final String STUDENT_ID = "34";
    private static UserProfile userProfile = new UserProfile();
    private boolean addPromoFlag;
    private BasicInfo mBasicProfile;
    private UserEducation mEducationProfile;
    private UserProfession mProfessionProfile;
    private boolean promoEmailOptOut;

    public void clearPracticeInfo() {
    }

    public int describeContents() {
        return 0;
    }

    public static UserProfile getInstance() {
        if (userProfile == null) {
            userProfile = new UserProfile();
        }
        return userProfile;
    }

    public UserProfile() {
        this.promoEmailOptOut = true;
        this.addPromoFlag = false;
        this.mProfessionProfile = new UserProfession();
        this.mBasicProfile = new BasicInfo();
        this.mEducationProfile = new UserEducation();
    }

    public JSONObject toJSON(Context context) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", AdParameterKeys.SECTION_ID);
        jSONObject.put("registeredOn", "en_us");
        jSONObject.put("siteId", "APP");
        jSONObject.put("userName", this.mBasicProfile.getEmailAddress());
        jSONObject.put("password", this.mBasicProfile.getPassword());
        if (this.addPromoFlag) {
            jSONObject.put("promoEmailOptOut", this.promoEmailOptOut);
        }
        JSONObject addAddressToJsonObject = addAddressToJsonObject(addProfessionToJsonObject(jSONObject));
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("en_us");
        addAddressToJsonObject.put("memberDomains", jSONArray);
        return addMarketDetailsToJsonObject(context, addPersonDetailsToJsonObject(addAddressToJsonObject));
    }

    public JSONObject addProfessionToJsonObject(JSONObject jSONObject) throws Exception {
        String str;
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        UserProfession userProfession = this.mProfessionProfile;
        if (userProfession != null) {
            str = userProfession.getCountryCode();
            jSONObject3.put("locale", "en_us");
            jSONObject3.put("professionId", this.mProfessionProfile.getProfessionId());
            if (StringExtensions.isNotEmpty(this.mProfessionProfile.getSpecialityId())) {
                jSONObject3.put(FeedCache.FeedCaches.SPECIALITY_ID, this.mProfessionProfile.getSpecialityId());
                if (StringExtensions.isNotEmpty(this.mProfessionProfile.getSubSpecialityId())) {
                    jSONObject3.put("subSpecialtyId", this.mProfessionProfile.getSubSpecialityId());
                }
            }
            if (StringExtensions.isNotEmpty(this.mProfessionProfile.getOccupationId())) {
                jSONObject3.put("occupationId", this.mProfessionProfile.getOccupationId());
            }
        } else {
            str = null;
        }
        UserEducation userEducation = this.mEducationProfile;
        if (userEducation != null) {
            if (StringExtensions.isNotEmpty(userEducation.getSchoolId())) {
                jSONObject3.put("medSchoolId", this.mEducationProfile.getSchoolId());
            }
            if (StringExtensions.isNotEmpty(this.mEducationProfile.getGraduationYear())) {
                jSONObject3.put("gradYear", this.mEducationProfile.getGraduationYear());
            }
            if (StringExtensions.isNotEmpty(this.mEducationProfile.getBirthYear())) {
                jSONObject3.put("dob", this.mEducationProfile.getBirthYear() + "-01-01");
            }
            if (str == null || !str.equalsIgnoreCase("us")) {
                jSONObject = addProfileAttributesDetailsToJsonObject(jSONObject);
            } else {
                jSONObject3.put("npi", this.mEducationProfile.getLicenseId());
            }
        }
        jSONObject2.put("en_us", jSONObject3);
        if (jSONObject != null) {
            jSONObject.put("professions", jSONObject2);
            return jSONObject;
        }
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("professions", jSONObject2);
        return jSONObject4;
    }

    private JSONObject addProfileAttributesDetailsToJsonObject(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        UserEducation userEducation = this.mEducationProfile;
        if (userEducation != null && StringExtensions.isNotEmpty(userEducation.getLicenseId())) {
            UserProfession userProfession = this.mProfessionProfile;
            String str = "";
            if (userProfession != null) {
                String countryCode = userProfession.getCountryCode();
                jSONObject3.put("attributeId", str);
                jSONObject3.put("attributeType", "license_" + this.mProfessionProfile.getCountryCode().toLowerCase());
                str = countryCode;
            }
            UserEducation userEducation2 = this.mEducationProfile;
            if (userEducation2 != null) {
                jSONObject3.put("attributeValue", userEducation2.getLicenseId());
            }
            if (str.isEmpty()) {
                jSONObject2.put("license", jSONObject3);
            } else {
                jSONObject2.put("license_" + str.toLowerCase(), jSONObject3);
            }
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
        }
        jSONObject.put("profileAttributes", jSONObject2);
        return jSONObject;
    }

    public JSONObject addAddressToJsonObject(JSONObject jSONObject) throws Exception {
        if (this.mProfessionProfile == null || this.mBasicProfile == null) {
            return jSONObject;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("contactType", 1);
        if (this.mProfessionProfile.getCountryCode() != null) {
            jSONObject2.put("countryAbbreviation", this.mProfessionProfile.getCountryCode());
        } else {
            jSONObject2.put("countryAbbreviation", "");
        }
        if (this.mBasicProfile.getZipCode() != null) {
            jSONObject2.put("zipCode", this.mBasicProfile.getZipCode());
        } else {
            jSONObject2.put("zipCode", "");
        }
        if (this.mBasicProfile.getWorkPhone() != null) {
            jSONObject2.put("phoneNumber", this.mBasicProfile.getWorkPhone());
        } else {
            jSONObject2.put("phoneNumber", "");
        }
        if (this.mBasicProfile.getCity() != null) {
            jSONObject2.put(Settings.CITY, this.mBasicProfile.getCity());
        } else {
            jSONObject2.put(Settings.CITY, "");
        }
        jSONArray.put(jSONObject2);
        if (jSONObject != null) {
            jSONObject.put("address", jSONArray);
            return jSONObject;
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("address", jSONArray);
        return jSONObject3;
    }

    public JSONObject addMarketDetailsToJsonObject(Context context, JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("marketingId", Util.getAppNameForUrl(context));
        jSONObject2.put("isUpdated", true);
        if (jSONObject != null) {
            jSONObject.put("marketInfo", jSONObject2);
            return jSONObject;
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("marketInfo", jSONObject2);
        return jSONObject3;
    }

    public JSONObject addPersonDetailsToJsonObject(JSONObject jSONObject) throws Exception {
        JSONObject jSONObject2 = new JSONObject();
        if (this.mBasicProfile.getLastName() != null) {
            jSONObject2.put(Constants.LOGIN_PREF_LASTNAMEKEY, this.mBasicProfile.getLastName());
        } else {
            jSONObject2.put(Constants.LOGIN_PREF_LASTNAMEKEY, "");
        }
        if (this.mBasicProfile.getFirstName() != null) {
            jSONObject2.put(Constants.LOGIN_PREF_FIRSTNAMEKEY, this.mBasicProfile.getFirstName());
        } else {
            jSONObject2.put(Constants.LOGIN_PREF_LASTNAMEKEY, "");
        }
        if (this.mBasicProfile.getEmailAddress() != null) {
            jSONObject2.put("email", new JSONObject().put("address", this.mBasicProfile.getEmailAddress()));
        } else {
            jSONObject2.put("email", new JSONObject().put("address", ""));
        }
        if (jSONObject != null) {
            jSONObject.put("person", jSONObject2);
            return jSONObject;
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("person", jSONObject2);
        return jSONObject3;
    }

    public UserProfession getProfessionProfile() {
        if (this.mProfessionProfile == null) {
            this.mProfessionProfile = new UserProfession();
        }
        return this.mProfessionProfile;
    }

    public void setProfessionProfile(UserProfession userProfession) {
        this.mProfessionProfile = userProfession;
    }

    public BasicInfo getBasicProfile() {
        return this.mBasicProfile;
    }

    public void setBasicProfile(BasicInfo basicInfo) {
        this.mBasicProfile = basicInfo;
    }

    public UserEducation getEducationProfile() {
        return this.mEducationProfile;
    }

    public void setEducationProfile(UserEducation userEducation) {
        this.mEducationProfile = userEducation;
    }

    public void clearEducation() {
        this.mEducationProfile = new UserEducation();
    }

    public void clearBasicInfo() {
        this.mBasicProfile = new BasicInfo();
    }

    public void clearProfessionProfile() {
        this.mProfessionProfile = new UserProfession();
    }

    public void clearAll() {
        this.mEducationProfile = new UserEducation();
        this.mBasicProfile = new BasicInfo();
        this.mProfessionProfile = new UserProfession();
    }

    public int getSteps() {
        String professionId;
        UserProfession userProfession = this.mProfessionProfile;
        if (userProfession == null || (professionId = userProfession.getProfessionId()) == null) {
            return 0;
        }
        return (PHYSICIAN_ID.equalsIgnoreCase(professionId) || MEDICAL_LABORATORY_ID.equalsIgnoreCase(professionId)) ? 3 : 2;
    }

    public boolean isExtraRegistrationStepCountry(String str) {
        SpecialCountriesRegistrationData specialCountriesRegistrationData = new SpecialCountriesRegistrationData();
        if (str != null) {
            return specialCountriesRegistrationData.getCountryPhoneCodes().containsKey(str);
        }
        return false;
    }

    protected UserProfile(Parcel parcel) {
        this.promoEmailOptOut = true;
        this.addPromoFlag = false;
        this.mProfessionProfile = (UserProfession) parcel.readValue(UserProfession.class.getClassLoader());
        this.mBasicProfile = (BasicInfo) parcel.readValue(BasicInfo.class.getClassLoader());
        this.mEducationProfile = (UserEducation) parcel.readValue(UserEducation.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.mProfessionProfile);
        parcel.writeValue(this.mBasicProfile);
        parcel.writeValue(this.mEducationProfile);
    }

    public String getFullName() {
        String firstName = getBasicProfile().getFirstName();
        String lastName = getBasicProfile().getLastName();
        String str = "";
        if (StringExtensions.isNullOrEmpty(firstName) || StringExtensions.isNullOrEmpty(lastName)) {
            return str;
        }
        String substring = firstName.substring(0);
        UserProfession professionProfile = getProfessionProfile();
        if (professionProfile != null) {
            String professionId = professionProfile.getProfessionId();
            if (!StringExtensions.isNullOrEmpty(professionId) && professionId.equalsIgnoreCase(PHYSICIAN_ID)) {
                str = "Dr. ";
            }
        }
        return str + substring + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + lastName;
    }

    public void setPromoEmailOptOut(boolean z) {
        this.promoEmailOptOut = z;
    }

    public void setAddPromoFlag(boolean z) {
        this.addPromoFlag = z;
    }

    public boolean isPromoEmailOptOut() {
        return this.promoEmailOptOut;
    }

    public boolean isAddPromoFlag() {
        return this.addPromoFlag;
    }
}
