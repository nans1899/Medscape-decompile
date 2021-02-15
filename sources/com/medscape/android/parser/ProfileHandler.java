package com.medscape.android.parser;

import com.facebook.appevents.UserDataStore;
import com.medscape.android.cache.FeedCache;
import com.medscape.android.parser.model.UserProfile;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProfileHandler extends DefaultHandler {
    private StringBuilder builder;
    private UserProfile currentProfile;
    private String mContactType;
    private String mCountryId;
    private String mStateDesc;
    private String mStateId;

    public UserProfile getProfile() {
        UserProfile userProfile = this.currentProfile;
        userProfile.setDisplayName(userProfile.getFirstName(), this.currentProfile.getLastName(), this.currentProfile.getProfessionId());
        return this.currentProfile;
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        this.builder.append(cArr, i, i2);
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        if (this.currentProfile != null) {
            if (str2.equalsIgnoreCase(UserDataStore.FIRST_NAME)) {
                this.currentProfile.setFirstName(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("middleinitial")) {
                this.currentProfile.setMiddleInitial(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase(UserDataStore.LAST_NAME)) {
                this.currentProfile.setLastName(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("username")) {
                this.currentProfile.setUsername(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("email")) {
                this.currentProfile.setEmail(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase(UserDataStore.COUNTRY) || str2.equalsIgnoreCase("co")) {
                this.mCountryId = this.builder.toString().trim();
            } else if (str2.equalsIgnoreCase("state") || str2.equalsIgnoreCase("st")) {
                this.mStateId = this.builder.toString().trim();
            } else if (str2.equalsIgnoreCase("stdesc")) {
                this.mStateDesc = this.builder.toString().trim();
            } else if (str2.equalsIgnoreCase("cntctypeid")) {
                this.mContactType = this.builder.toString().trim();
            } else if (str2.equalsIgnoreCase("gradyear")) {
                this.currentProfile.setGradYear(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("medschool")) {
                this.currentProfile.setMedSchool(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("profession") || str2.equalsIgnoreCase("profdesc")) {
                this.currentProfile.setProfession(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("professionId") || str2.equalsIgnoreCase("profid")) {
                this.currentProfile.setProfessionId(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("registeredId") || str2.equalsIgnoreCase("guid")) {
                this.currentProfile.setRegisteredId(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("spdesc")) {
                this.currentProfile.setSpecialty(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase(FeedCache.FeedCaches.SPECIALITY_ID) || str2.equalsIgnoreCase("spid")) {
                this.currentProfile.setSpecialtyId(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("hasAccessToPhysicianBoards")) {
                this.currentProfile.setHasAccessToPhysicianBoards(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("homePageId") || str2.equalsIgnoreCase("hpid")) {
                this.currentProfile.setHomePageId(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("hpdesc")) {
                this.currentProfile.setHomePage(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("campaignList")) {
                this.currentProfile.setCampaignList(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("degreeId")) {
                this.currentProfile.setDegreeId(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("degree")) {
                this.currentProfile.setDegree(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("occupationId") || str2.equalsIgnoreCase("occid")) {
                this.currentProfile.setOccupationId(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("occupation")) {
                this.currentProfile.setOccupation(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("cookiename")) {
                this.currentProfile.getCookie().add(this.builder.toString().trim());
            } else if (str2.equalsIgnoreCase("profilesegvardfp")) {
                this.currentProfile.setProfileSegvar(this.builder.toString().trim());
            } else if (str2.startsWith("tid")) {
                this.currentProfile.getTidMap().put(this.currentProfile.getId(), this.builder.toString().trim());
            } else if (str2.startsWith("contact")) {
                try {
                    if (Integer.parseInt(this.mContactType) == 1) {
                        this.currentProfile.setCountryId(this.mCountryId);
                        this.currentProfile.setState(this.mStateDesc);
                        this.currentProfile.setStateId(this.mStateId);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        this.builder.setLength(0);
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        this.currentProfile = new UserProfile();
        this.builder = new StringBuilder();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        UserProfile userProfile;
        super.startElement(str, str2, str3, attributes);
        if (str3.equalsIgnoreCase("siteOn")) {
            this.currentProfile.setId(attributes.getValue("id"));
        } else if (str3.equalsIgnoreCase("cookies") && (userProfile = this.currentProfile) != null) {
            userProfile.setCookie(new ArrayList());
        }
    }
}
