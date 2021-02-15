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
import com.wbmd.qxcalculator.model.db.DBConsentLocation;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Type("consent_location")
public class ConsentLocation implements Parcelable {
    public static final Parcelable.Creator<ConsentLocation> CREATOR = new Parcelable.Creator<ConsentLocation>() {
        public ConsentLocation createFromParcel(Parcel parcel) {
            ConsentLocation consentLocation = new ConsentLocation();
            consentLocation.identifier = (String) parcel.readValue(String.class.getClassLoader());
            consentLocation.label = (String) parcel.readValue(String.class.getClassLoader());
            consentLocation.consentId = (String) parcel.readValue(String.class.getClassLoader());
            consentLocation.title = (String) parcel.readValue(String.class.getClassLoader());
            consentLocation.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            consentLocation.shortName = (String) parcel.readValue(String.class.getClassLoader());
            consentLocation.explicitRequired = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            consentLocation.optInRequired = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            consentLocation.isEditable = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            consentLocation.defaultOptIn = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            return consentLocation;
        }

        public ConsentLocation[] newArray(int i) {
            return new ConsentLocation[i];
        }
    };
    @JsonProperty("consent_id")
    public String consentId;
    @JsonProperty("default_opt_in")
    public Boolean defaultOptIn;
    @JsonProperty("explicit_required")
    public Boolean explicitRequired;
    @Id(StringIdHandler.class)
    public String identifier;
    @JsonProperty("is_editable")
    public Boolean isEditable;
    @JsonProperty("label")
    public String label;
    @JsonProperty("opt_in_required")
    public Boolean optInRequired;
    @JsonProperty("short_name")
    public String shortName;
    @JsonProperty("sub_title")
    public String subTitle;
    @JsonProperty("title")
    public String title;

    public int describeContents() {
        return 0;
    }

    /* renamed from: com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType[] r0 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType = r0
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType r1 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.TERMS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType r1 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.PRIVACY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType r1 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.COOKIES     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType r1 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.EMAIL_LIVE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType r1 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.PERSONALIZATION     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType r1 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.PERSONALIZATION_LIVE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation$ConsentType r1 = com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.ConsentType.ANALYTICS     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation.AnonymousClass2.<clinit>():void");
        }
    }

    public enum ConsentType {
        TERMS,
        PRIVACY,
        COOKIES,
        EMAIL_LIVE,
        PERSONALIZATION,
        PERSONALIZATION_LIVE,
        ANALYTICS;

        public String toString() {
            switch (AnonymousClass2.$SwitchMap$com$wbmd$qxcalculator$model$parsedObjects$ConsentLocation$ConsentType[ordinal()]) {
                case 1:
                    return Constants.TERMSOFUSE_VERSION;
                case 2:
                    return "privacy";
                case 3:
                    return "cookie";
                case 4:
                    return "email_live";
                case 5:
                    return "personalization_registration";
                case 6:
                    return "personalization_live";
                case 7:
                    return "analytics";
                default:
                    return null;
            }
        }

        public static ConsentType fromLabel(String str) {
            if (str.equalsIgnoreCase(Constants.TERMSOFUSE_VERSION)) {
                return TERMS;
            }
            if (str.equalsIgnoreCase("privacy")) {
                return PRIVACY;
            }
            if (str.equalsIgnoreCase("cookie")) {
                return COOKIES;
            }
            if (str.equalsIgnoreCase("email_live")) {
                return EMAIL_LIVE;
            }
            if (str.equalsIgnoreCase("personalization_registration")) {
                return PERSONALIZATION;
            }
            if (str.equalsIgnoreCase("personalization_live")) {
                return PERSONALIZATION_LIVE;
            }
            if (str.equalsIgnoreCase("analytics")) {
                return ANALYTICS;
            }
            return null;
        }
    }

    public static ArrayList<ConsentLocation> requiredConsents(List<DBConsentLocation> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ConsentLocation> arrayList = new ArrayList<>(list.size());
        for (DBConsentLocation consentLocation : list) {
            arrayList.add(new ConsentLocation(consentLocation));
        }
        return arrayList;
    }

    public ConsentLocation(DBConsentLocation dBConsentLocation) {
        if (dBConsentLocation != null) {
            this.identifier = dBConsentLocation.getIdentifier();
            this.consentId = dBConsentLocation.getIdentifier();
            this.label = dBConsentLocation.getLabel();
            this.title = dBConsentLocation.getTitle();
            this.subTitle = dBConsentLocation.getSubTitle();
            this.shortName = dBConsentLocation.getShortName();
            this.explicitRequired = dBConsentLocation.getExplicitRequired();
            this.optInRequired = dBConsentLocation.getOptInRequired();
            this.isEditable = dBConsentLocation.getIsEditable();
            this.defaultOptIn = dBConsentLocation.getDefaultOptIn();
        }
    }

    public ConsentLocation(ConsentType consentType) {
        this.label = consentType.toString();
    }

    public ConsentLocation() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.label);
        parcel.writeValue(this.consentId);
        parcel.writeValue(this.title);
        parcel.writeValue(this.subTitle);
        parcel.writeValue(this.shortName);
        parcel.writeValue(this.explicitRequired);
        parcel.writeValue(this.optInRequired);
        parcel.writeValue(this.isEditable);
        parcel.writeValue(this.defaultOptIn);
    }

    public static List<ConsentLocation> convertJsonToRequiredConsents(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToRequiredConsent(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ConsentLocation convertJsonToRequiredConsent(JsonReader jsonReader) throws IOException, ParseException {
        ConsentLocation consentLocation = new ConsentLocation();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                consentLocation.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(Constants.ScionAnalytics.PARAM_LABEL)) {
                consentLocation.label = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                consentLocation.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("consent_id")) {
                consentLocation.consentId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                consentLocation.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("short_name")) {
                consentLocation.shortName = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("explicit_required")) {
                consentLocation.explicitRequired = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("opt_in_required")) {
                consentLocation.optInRequired = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("is_editable")) {
                consentLocation.isEditable = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("default_opt_in")) {
                consentLocation.defaultOptIn = APIParser.readBoolean(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return consentLocation;
    }

    public static ArrayList<ConsentLocation> convertJsonInputStreamToApp(InputStream inputStream) {
        Class<ConsentLocation> cls = ConsentLocation.class;
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
        if (!(obj instanceof ConsentLocation)) {
            return false;
        }
        String str = this.label;
        String str2 = ((ConsentLocation) obj).label;
        if (str != null) {
            return str.equalsIgnoreCase(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }
}
