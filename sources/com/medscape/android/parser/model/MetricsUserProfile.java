package com.medscape.android.parser.model;

import com.facebook.appevents.AppEventsConstants;
import java.io.Serializable;

public class MetricsUserProfile implements Serializable {
    private static final long serialVersionUID = -6595147624254771747L;
    private String abimID;
    private String countryId;
    private String occupation;
    private String occupationID;
    private String profession;
    private String professionID;
    private String registeredId;
    private String specialty;
    private String specialtyID;

    public MetricsUserProfile(UserProfile userProfile) {
        if (userProfile != null) {
            this.countryId = userProfile.getCountryId();
            this.profession = userProfile.getProfession();
            this.registeredId = userProfile.getRegisteredId();
            this.specialty = userProfile.getSpecialty();
            this.professionID = userProfile.getProfessionId();
            this.specialtyID = userProfile.getSpecialtyId();
            this.occupationID = userProfile.getOccupationId();
            this.occupation = userProfile.getOccupation();
            this.abimID = userProfile.getAbimID();
        }
    }

    public String getCountryId() {
        return this.countryId;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getEncryptedRegisteredId() {
        if (this.registeredId == null) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        return "" + (Integer.valueOf(this.registeredId).intValue() * 27);
    }

    public String getRegisteredId() {
        return this.registeredId;
    }

    public String getSpecialty() {
        return this.specialty;
    }

    public String getProfessionID() {
        return this.professionID;
    }

    public String getSpecialtyID() {
        return this.specialtyID;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public String getOccupationID() {
        return this.occupationID;
    }

    public String getAbimID() {
        return this.abimID;
    }
}
