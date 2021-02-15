package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBFilter;
import com.wbmd.qxcalculator.model.parsedObjects.Location;
import com.wbmd.qxcalculator.model.parsedObjects.Profession;
import com.wbmd.qxcalculator.model.parsedObjects.Specialty;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Filter implements Parcelable {
    public static final Parcelable.Creator<Filter> CREATOR = new Parcelable.Creator<Filter>() {
        public Filter createFromParcel(Parcel parcel) {
            Filter filter = new Filter();
            filter.identifier = (String) parcel.readValue(String.class.getClassLoader());
            filter.type = (String) parcel.readValue(String.class.getClassLoader());
            filter.professions = parcel.createTypedArrayList(Profession.CREATOR);
            filter.specialties = parcel.createTypedArrayList(Specialty.CREATOR);
            filter.locations = parcel.createTypedArrayList(Location.CREATOR);
            return filter;
        }

        public Filter[] newArray(int i) {
            return new Filter[i];
        }
    };
    public static final String K_EXCLUSION_MATCH_ALL = "exclude_match_all";
    public static final String K_EXCLUSION_MATCH_ANY = "exclude_match_any";
    public static final String K_INCLUSION_MATCH_ALL = "inclusion_match_all";
    public static final String K_INCLUSION_MATCH_ANY = "inclusion_match_any";
    public String identifier;
    public List<Location> locations;
    public List<Profession> professions;
    public List<Specialty> specialties;
    public String type;

    public enum FilterType {
        INCLUDE_MATCH_ALL,
        INCLUDE_MATCH_ANY,
        EXCLUDE_MATCH_ALL,
        EXCLUDE_MATCH_ANY
    }

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Filter> filters(List<DBFilter> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Filter> arrayList = new ArrayList<>(list.size());
        for (DBFilter filter : list) {
            arrayList.add(new Filter(filter));
        }
        return arrayList;
    }

    public Filter(DBFilter dBFilter) {
        if (dBFilter != null) {
            this.identifier = dBFilter.getIdentifier();
            this.type = dBFilter.getType();
            this.professions = Profession.professions(dBFilter.getProfessions());
            this.specialties = Specialty.specialties(dBFilter.getSpecialties());
            this.locations = Location.locations(dBFilter.getLocations());
        }
    }

    public Filter() {
        this((DBFilter) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.type);
        parcel.writeTypedList(this.professions);
        parcel.writeTypedList(this.specialties);
        parcel.writeTypedList(this.locations);
    }

    public static List<Filter> convertJsonToFilters(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToFilter(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Filter convertJsonToFilter(JsonReader jsonReader) throws IOException, ParseException {
        Filter filter = new Filter();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                filter.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                filter.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("professions")) {
                filter.professions = Profession.convertJsonToProfessions(jsonReader);
            } else if (nextName.equalsIgnoreCase("specialties")) {
                filter.specialties = Specialty.convertJsonToSpecialties(jsonReader);
            } else if (nextName.equalsIgnoreCase("locations")) {
                filter.locations = Location.convertJsonToLocations(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return filter;
    }
}
