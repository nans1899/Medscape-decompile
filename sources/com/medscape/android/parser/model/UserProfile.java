package com.medscape.android.parser.model;

import android.content.Context;
import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.R;
import com.medscape.android.util.Util;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = -6357061590670469530L;
    private String abimID;
    private HashMap<String, String> attributes = new HashMap<>();
    private String campaignList;
    private List<String> cookie;
    private String countryId;
    private String degree;
    private String degreeId;
    private String displayName;
    private String email;
    private String firstName;
    private String gradYear;
    private String hasAccessToPhysicianBoards;
    private String homePage;
    private String homePageId;
    private String id;
    private String lastName;
    private String marketTCID;
    private String medSchool;
    private String middleInitial;
    private String occupation;
    private String occupationId;
    private String profession;
    private String professionId;
    private String profileSegvar;
    private String registeredId;
    private String specialty;
    private String specialtyId;
    private String state;
    private String stateId;
    private HashMap<String, String> tidMap = new HashMap<>();
    private String username;

    public String getDisplayName(Context context) {
        String str;
        String firstName2 = getFirstName();
        String lastName2 = getLastName();
        if (firstName2 == null) {
            firstName2 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        if (lastName2 == null) {
            lastName2 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        String str2 = this.professionId;
        if (str2 == null || !str2.equalsIgnoreCase(com.webmd.wbmdproffesionalauthentication.model.UserProfile.PHYSICIAN_ID)) {
            if (firstName2.isEmpty()) {
                str = context.getString(R.string.prifile_display_no_name, new Object[]{lastName2, ""});
            } else {
                str = context.getString(R.string.prifile_display_no_name, new Object[]{Character.valueOf(firstName2.charAt(0)), lastName2});
            }
        } else if (firstName2.isEmpty()) {
            str = context.getString(R.string.profile_display_name, new Object[]{lastName2, ""});
        } else {
            str = context.getString(R.string.profile_display_name, new Object[]{Character.valueOf(firstName2.charAt(0)), lastName2});
        }
        return str.trim();
    }

    public void setDisplayName(String str, String str2, String str3) {
        String str4;
        if (str == null) {
            str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        if (str2 == null) {
            str2 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        if (str3 == null || !str3.equalsIgnoreCase(com.webmd.wbmdproffesionalauthentication.model.UserProfile.PHYSICIAN_ID)) {
            str4 = str.charAt(0) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2;
        } else {
            str4 = "Dr. " + str.charAt(0) + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2;
        }
        this.displayName = str4;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public String getMiddleInitial() {
        return this.middleInitial;
    }

    public void setMiddleInitial(String str) {
        this.middleInitial = str;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String str) {
        this.countryId = str;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getStateId() {
        return this.stateId;
    }

    public void setStateId(String str) {
        this.stateId = str;
    }

    public String getGradYear() {
        return this.gradYear;
    }

    public void setGradYear(String str) {
        this.gradYear = str;
    }

    public String getMedSchool() {
        return this.medSchool;
    }

    public void setMedSchool(String str) {
        this.medSchool = str;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String str) {
        this.profession = str;
    }

    public String getProfessionId() {
        return this.professionId;
    }

    public void setProfessionId(String str) {
        this.professionId = str;
    }

    public String getRegisteredId() {
        return this.registeredId;
    }

    public String getEncryptedRegisteredId() {
        if (this.registeredId == null) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        return "" + (Integer.valueOf(this.registeredId).intValue() * 27);
    }

    public Long getEncryptedLongRegisteredId() {
        String str = this.registeredId;
        return Long.valueOf(str != null ? Util.convertStringToLong(str) * 27 : 0);
    }

    public void setRegisteredId(String str) {
        this.registeredId = str;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public void setSpecialty(String str) {
        this.specialty = str;
    }

    public String getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(String str) {
        this.specialtyId = str;
    }

    public String getHasAccessToPhysicianBoards() {
        return this.hasAccessToPhysicianBoards;
    }

    public void setHasAccessToPhysicianBoards(String str) {
        this.hasAccessToPhysicianBoards = str;
    }

    public String getHomePageId() {
        return this.homePageId;
    }

    public void setHomePageId(String str) {
        this.homePageId = str;
    }

    public String getHomePage() {
        return this.homePage;
    }

    public void setHomePage(String str) {
        this.homePage = str;
    }

    public String getCampaignList() {
        return this.campaignList;
    }

    public void setCampaignList(String str) {
        this.campaignList = str;
    }

    public String getDegreeId() {
        return this.degreeId;
    }

    public void setDegreeId(String str) {
        this.degreeId = str;
    }

    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String str) {
        this.degree = str;
    }

    public String getOccupationId() {
        return this.occupationId;
    }

    public void setOccupationId(String str) {
        this.occupationId = str;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String str) {
        this.occupation = str;
    }

    public void setProfileSegvar(String str) {
        this.profileSegvar = str.replaceAll(";", "&");
    }

    public String getProfileSegvar() {
        return this.profileSegvar;
    }

    public HashMap<String, String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(HashMap<String, String> hashMap) {
        this.attributes = hashMap;
    }

    public HashMap<String, String> getTidMap() {
        return this.tidMap;
    }

    public void setTidMap(HashMap<String, String> hashMap) {
        this.tidMap = hashMap;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public List<String> getCookie() {
        return this.cookie;
    }

    public void setCookie(List<String> list) {
        this.cookie = list;
    }

    public void setMarketTCID(String str) {
        this.marketTCID = str;
    }

    public String getMarketTCID() {
        return this.marketTCID;
    }

    public String getAbimID() {
        return this.abimID;
    }

    public void setAbimID(String str) {
        this.abimID = str;
    }
}
