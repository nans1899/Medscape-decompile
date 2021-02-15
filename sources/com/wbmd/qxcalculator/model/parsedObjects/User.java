package com.wbmd.qxcalculator.model.parsedObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.Log;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.LongIdHandler;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Type("user")
public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel parcel) {
            User user = new User();
            user.identifier = (Long) parcel.readValue(Long.class.getClassLoader());
            user.nameFirst = (String) parcel.readValue(String.class.getClassLoader());
            user.nameLast = (String) parcel.readValue(String.class.getClassLoader());
            user.userDescription = (String) parcel.readValue(String.class.getClassLoader());
            user.email = (String) parcel.readValue(String.class.getClassLoader());
            user.password = (String) parcel.readValue(String.class.getClassLoader());
            user.facebookAccessToken = (String) parcel.readValue(String.class.getClassLoader());
            user.authToken = (String) parcel.readValue(String.class.getClassLoader());
            user.npi = (String) parcel.readValue(String.class.getClassLoader());
            user.zip = (String) parcel.readValue(String.class.getClassLoader());
            user.cmeTracking = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            user.defaultUnitsString = (String) parcel.readValue(String.class.getClassLoader());
            user.showItemDescriptions = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            user.autoOpenFirstQuestion = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            user.pnEnabled = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            user.shouldVerify = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            user.lastUpdated = (String) parcel.readValue(String.class.getClassLoader());
            user.specialty = (Specialty) parcel.readParcelable(Specialty.class.getClassLoader());
            user.location = (Location) parcel.readParcelable(Location.class.getClassLoader());
            user.profession = (Profession) parcel.readParcelable(Profession.class.getClassLoader());
            user.app = (App) parcel.readParcelable(App.class.getClassLoader());
            user.device = (Device) parcel.readParcelable(Device.class.getClassLoader());
            user.currentConsents = parcel.createTypedArrayList(ConsentUser.CREATOR);
            user.consentsToRequest = parcel.createTypedArrayList(ConsentLocation.CREATOR);
            user.consentsRequired = parcel.createTypedArrayList(ConsentLocation.CREATOR);
            return user;
        }

        public User[] newArray(int i) {
            return new User[i];
        }
    };
    public Long accessPrivileges;
    @Relationship("app")
    public App app;
    @JsonProperty("token")
    public String authToken;
    public Boolean autoOpenFirstQuestion;
    @JsonProperty("cme_tracking")
    public Boolean cmeTracking;
    @Relationship("consents")
    public List<ConsentUser> consents;
    @Relationship("required_consents")
    public List<ConsentLocation> consentsRequired;
    @Relationship("consents_to_request")
    public List<ConsentLocation> consentsToRequest;
    @Relationship("current_consents")
    public List<ConsentUser> currentConsents;
    public String debugGroups;
    public String defaultUnitsString;
    @Relationship("device")
    public Device device;
    @JsonProperty("email")
    public String email;
    @JsonProperty("fb_access_token")
    public String facebookAccessToken;
    @JsonProperty("facebook_id")
    public String facebookId;
    @Id(LongIdHandler.class)
    public Long identifier;
    @JsonProperty("lang")
    public String language = Locale.getDefault().toString().replace("_", "-");
    @JsonProperty("updated_at")
    public String lastUpdated;
    @Relationship("location")
    public Location location;
    @JsonProperty("first_name")
    public String nameFirst;
    @JsonProperty("last_name")
    public String nameLast;
    @JsonProperty("license_npi")
    public String npi;
    @JsonProperty("password")
    public String password;
    public Boolean pnEnabled;
    @Relationship("profession")
    public Profession profession;
    @JsonProperty("session_id")
    public String sessionId;
    @JsonProperty("should_verify_profile")
    public Boolean shouldVerify;
    public Boolean showItemDescriptions;
    @Relationship("specialty")
    public Specialty specialty;
    @JsonProperty("tz_name")
    public String timezone = TimeZone.getDefault().getID();
    @JsonIgnore
    public Unit.UnitType unitType = Unit.UnitType.US_UNITS;
    @JsonProperty("description")
    public String userDescription;
    @JsonProperty("zip_code")
    public String zip;

    public int describeContents() {
        return 0;
    }

    public User() {
    }

    public User(DBUser dBUser) {
        copyFromDbUser(dBUser);
    }

    public void copyFromDbUser(DBUser dBUser) {
        if (dBUser != null) {
            try {
                this.identifier = Long.valueOf(Long.parseLong(dBUser.getIdentifier()));
            } catch (Exception unused) {
                this.identifier = -1L;
            }
            this.nameFirst = dBUser.getFirstName();
            this.nameLast = dBUser.getLastName();
            this.userDescription = dBUser.getDescription();
            this.zip = dBUser.getZipCode();
            this.npi = dBUser.getNpi();
            this.email = UserManager.getInstance().getUserEmail();
            this.unitType = Unit.UnitType.convertStringToType(dBUser.getDefaultUnits());
            this.defaultUnitsString = dBUser.getDefaultUnits();
            this.showItemDescriptions = dBUser.getShowItemDescription();
            this.autoOpenFirstQuestion = dBUser.getAutoEnterFirstQuestion();
            this.pnEnabled = dBUser.getPnEnabled();
            this.specialty = new Specialty(dBUser.getSpecialty());
            this.location = new Location(dBUser.getLocation());
            this.profession = new Profession(dBUser.getProfession());
            this.currentConsents = ConsentUser.consents(dBUser.getCurrentConsents());
            this.consentsToRequest = ConsentLocation.requiredConsents(dBUser.getConsentLocationsToRequest());
            this.consentsRequired = ConsentLocation.requiredConsents(dBUser.getConsentLocationsRequired());
        }
    }

    public static String getStringValueForDate(Long l) {
        return Long.valueOf(l.longValue() / 1000).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.nameFirst);
        parcel.writeValue(this.nameLast);
        parcel.writeValue(this.userDescription);
        parcel.writeValue(this.email);
        parcel.writeValue(this.password);
        parcel.writeValue(this.facebookAccessToken);
        parcel.writeValue(this.authToken);
        parcel.writeValue(this.npi);
        parcel.writeValue(this.zip);
        parcel.writeValue(this.cmeTracking);
        parcel.writeValue(this.defaultUnitsString);
        parcel.writeValue(this.showItemDescriptions);
        parcel.writeValue(this.autoOpenFirstQuestion);
        parcel.writeValue(this.pnEnabled);
        parcel.writeValue(this.shouldVerify);
        parcel.writeValue(this.lastUpdated);
        parcel.writeParcelable(this.specialty, i);
        parcel.writeParcelable(this.location, i);
        parcel.writeParcelable(this.profession, i);
        parcel.writeParcelable(this.app, i);
        parcel.writeParcelable(this.device, i);
        parcel.writeTypedList(this.currentConsents);
        parcel.writeTypedList(this.consentsToRequest);
        parcel.writeTypedList(this.consentsRequired);
    }

    public boolean isUsUser() {
        Location location2 = this.location;
        if (location2 == null || location2.identifier == null) {
            return false;
        }
        return this.location.identifier.equals(1L);
    }

    public void acceptTermsPrivacyCookiesPolicies() {
        List<ConsentLocation> list = this.location.consents;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (ConsentLocation next : list) {
                if (next.label.equals(ConsentLocation.ConsentType.TERMS.toString()) || next.label.equals(ConsentLocation.ConsentType.PRIVACY.toString()) || next.label.equals(ConsentLocation.ConsentType.COOKIES.toString())) {
                    ConsentUser consentUser = new ConsentUser();
                    consentUser.label = next.label;
                    consentUser.identifier = next.consentId;
                    consentUser.isOptIn = true;
                    consentUser.isExplicit = true;
                    arrayList.add(consentUser);
                }
            }
        }
        this.consents = arrayList;
    }

    public List<ConsentLocation> requiredConsentsNotIncludingTermsPrivacyCookies() {
        List<ConsentLocation> list = this.location.consents;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (ConsentLocation next : list) {
                if (!next.label.equals(ConsentLocation.ConsentType.TERMS.toString()) && !next.label.equals(ConsentLocation.ConsentType.PRIVACY.toString()) && !next.label.equals(ConsentLocation.ConsentType.COOKIES.toString()) && next.explicitRequired != null && next.explicitRequired.booleanValue()) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    public void clearConsentsNotIncludingTermsPrivacyCookies() {
        ArrayList arrayList = new ArrayList();
        for (ConsentUser next : this.consents) {
            if (next.label.equals(ConsentLocation.ConsentType.TERMS.toString()) || next.label.equals(ConsentLocation.ConsentType.PRIVACY.toString()) || next.label.equals(ConsentLocation.ConsentType.COOKIES.toString())) {
                arrayList.add(next);
            }
        }
        this.consents = arrayList;
    }

    public static User convertJsonToUser(JsonReader jsonReader) throws IOException, ParseException {
        User user = new User();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("email")) {
                user.email = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("first_name")) {
                user.nameFirst = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("last_name")) {
                user.nameLast = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("description")) {
                user.userDescription = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("zip_code")) {
                user.zip = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("license_npi")) {
                user.npi = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("cme_tracking")) {
                user.cmeTracking = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("default_units")) {
                user.defaultUnitsString = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("show_item_descriptions")) {
                user.showItemDescriptions = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("auto_open_first_question")) {
                user.autoOpenFirstQuestion = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("pn_enabled")) {
                user.pnEnabled = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("should_verify_profile")) {
                user.shouldVerify = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("access_privileges")) {
                user.accessPrivileges = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("debug_groups")) {
                user.debugGroups = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("profession")) {
                user.profession = Profession.convertJsonToProfession(jsonReader);
            } else if (nextName.equalsIgnoreCase(OmnitureConstants.OMNITURE_FILTER_SPECIALTY)) {
                user.specialty = Specialty.convertJsonToSpecialty(jsonReader);
            } else if (nextName.equalsIgnoreCase("location")) {
                user.location = Location.convertJsonToLocation(jsonReader);
            } else if (nextName.equalsIgnoreCase("current_consents")) {
                user.currentConsents = ConsentUser.convertJsonToConsents(jsonReader);
            } else if (nextName.equalsIgnoreCase("required_consents")) {
                user.consentsRequired = ConsentLocation.convertJsonToRequiredConsents(jsonReader);
            } else if (nextName.equalsIgnoreCase("consents_to_request")) {
                user.consentsToRequest = ConsentLocation.convertJsonToRequiredConsents(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return user;
    }

    public String convertToJsonApiString() {
        ResourceConverter resourceConverter = new ResourceConverter(new ObjectMapper().configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false).configure(SerializationFeature.INDENT_OUTPUT, true).setSerializationInclusion(JsonInclude.Include.NON_NULL), (Class<?>[]) new Class[]{User.class, Profession.class, Specialty.class, Location.class, App.class, Device.class, ConsentUser.class, ConsentLocation.class});
        resourceConverter.enableSerializationOption(com.github.jasminb.jsonapi.SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
        try {
            return new String(resourceConverter.writeDocument(new JSONAPIDocument(this)));
        } catch (Exception e) {
            Log.d("chan", "error " + e);
            return null;
        }
    }

    public static User convertJsonInputStreamToUser(InputStream inputStream) {
        Class<User> cls = User.class;
        ResourceConverter resourceConverter = new ResourceConverter(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false), (Class<?>[]) new Class[]{cls, Profession.class, Specialty.class, Location.class, App.class, Device.class, ConsentUser.class, ConsentLocation.class});
        resourceConverter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        resourceConverter.disableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.REQUIRE_RESOURCE_ID);
        return resourceConverter.readDocument(inputStream, cls).get();
    }
}
