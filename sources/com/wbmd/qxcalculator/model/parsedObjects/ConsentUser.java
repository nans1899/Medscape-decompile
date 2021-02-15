package com.wbmd.qxcalculator.model.parsedObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.StringIdHandler;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;
import com.google.firebase.messaging.Constants;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBConsentUser;
import com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Type("consent_user")
public class ConsentUser implements Parcelable {
    public static final Parcelable.Creator<ConsentUser> CREATOR = new Parcelable.Creator<ConsentUser>() {
        public ConsentUser createFromParcel(Parcel parcel) {
            ConsentUser consentUser = new ConsentUser();
            consentUser.identifier = (String) parcel.readValue(String.class.getClassLoader());
            consentUser.label = (String) parcel.readValue(String.class.getClassLoader());
            consentUser.consentId = (String) parcel.readValue(String.class.getClassLoader());
            consentUser.isOptIn = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            consentUser.isExplicit = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            return consentUser;
        }

        public ConsentUser[] newArray(int i) {
            return new ConsentUser[i];
        }
    };
    @JsonProperty("consent_id")
    public String consentId;
    @Id(StringIdHandler.class)
    public String identifier;
    @JsonProperty("is_explicit")
    public Boolean isExplicit;
    @JsonProperty("is_opt_in")
    public Boolean isOptIn;
    @JsonProperty("label")
    public String label;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ConsentUser> consents(List<DBConsentUser> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ConsentUser> arrayList = new ArrayList<>(list.size());
        for (DBConsentUser consentUser : list) {
            arrayList.add(new ConsentUser(consentUser));
        }
        return arrayList;
    }

    public ConsentUser(DBConsentUser dBConsentUser) {
        if (dBConsentUser != null) {
            this.identifier = dBConsentUser.getIdentifier();
            this.label = dBConsentUser.getLabel();
            this.consentId = dBConsentUser.getIdentifier();
            this.isOptIn = dBConsentUser.getIsOptIn();
            this.isExplicit = dBConsentUser.getIsExplicit();
        }
    }

    public ConsentUser(ConsentLocation.ConsentType consentType) {
        this.label = consentType.toString();
    }

    public ConsentUser() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.label);
        parcel.writeValue(this.consentId);
        parcel.writeValue(this.isOptIn);
        parcel.writeValue(this.isExplicit);
    }

    public static List<ConsentUser> convertJsonToConsents(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToConsent(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ConsentUser convertJsonToConsent(JsonReader jsonReader) throws IOException, ParseException {
        ConsentUser consentUser = new ConsentUser();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                consentUser.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(Constants.ScionAnalytics.PARAM_LABEL)) {
                consentUser.label = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("consent_id")) {
                consentUser.consentId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("is_opt_in")) {
                consentUser.isOptIn = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("is_explicit")) {
                consentUser.isExplicit = APIParser.readBoolean(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return consentUser;
    }

    public static ArrayList<ConsentUser> convertJsonInputStreamToApp(InputStream inputStream) {
        Class<ConsentUser> cls = ConsentUser.class;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setConfig(objectMapper.getDeserializationConfig().without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, (Class<?>[]) new Class[]{cls});
        resourceConverter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        return new ArrayList<>(resourceConverter.readDocumentCollection(inputStream, cls).get());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ConsentUser)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((ConsentUser) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }
}
