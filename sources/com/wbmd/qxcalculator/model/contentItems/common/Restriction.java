package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.facebook.share.internal.ShareConstants;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBRestriction;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Restriction implements Parcelable {
    public static final Parcelable.Creator<Restriction> CREATOR = new Parcelable.Creator<Restriction>() {
        public Restriction createFromParcel(Parcel parcel) {
            Restriction restriction = new Restriction();
            restriction.identifier = (String) parcel.readValue(String.class.getClassLoader());
            restriction.alertTitle = (String) parcel.readValue(String.class.getClassLoader());
            restriction.alertMessage = (String) parcel.readValue(String.class.getClassLoader());
            restriction.alternateUrl = (String) parcel.readValue(String.class.getClassLoader());
            restriction.filters = parcel.createTypedArrayList(Filter.CREATOR);
            restriction.platforms = parcel.createTypedArrayList(Platform.CREATOR);
            return restriction;
        }

        public Restriction[] newArray(int i) {
            return new Restriction[i];
        }
    };
    public String alertMessage;
    public String alertTitle;
    public String alternateUrl;
    public List<Filter> filters;
    public String identifier;
    public List<Platform> platforms;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Restriction> restrictions(List<DBRestriction> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Restriction> arrayList = new ArrayList<>(list.size());
        for (DBRestriction restriction : list) {
            arrayList.add(new Restriction(restriction));
        }
        return arrayList;
    }

    public Restriction(DBRestriction dBRestriction) {
        if (dBRestriction != null) {
            this.identifier = dBRestriction.getIdentifier();
            this.alertTitle = dBRestriction.getAlertTitle();
            this.alertMessage = dBRestriction.getAlertMessage();
            this.alternateUrl = dBRestriction.getAlternateUrl();
            this.filters = Filter.filters(dBRestriction.getFilters());
            this.platforms = Platform.platforms(dBRestriction.getPlatforms());
        }
    }

    public Restriction() {
        this((DBRestriction) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.alertTitle);
        parcel.writeValue(this.alertMessage);
        parcel.writeValue(this.alternateUrl);
        parcel.writeTypedList(this.filters);
        parcel.writeTypedList(this.platforms);
    }

    public static List<Restriction> convertJsonToFeaturedRestrictions(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToFeaturedRestriction(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Restriction convertJsonToFeaturedRestriction(JsonReader jsonReader) throws IOException, ParseException {
        Restriction restriction = new Restriction();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                restriction.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("alert_title")) {
                restriction.alertTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("alert_message")) {
                restriction.alertMessage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("alternate_url")) {
                restriction.alternateUrl = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(ShareConstants.WEB_DIALOG_PARAM_FILTERS)) {
                restriction.filters = Filter.convertJsonToFilters(jsonReader);
            } else if (nextName.equalsIgnoreCase("platforms")) {
                restriction.platforms = Platform.convertJsonToPlatforms(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return restriction;
    }
}
