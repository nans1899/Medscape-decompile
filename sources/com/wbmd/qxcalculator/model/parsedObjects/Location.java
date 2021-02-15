package com.wbmd.qxcalculator.model.parsedObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonToken;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.LongIdHandler;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBLocation;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Type("location")
public class Location implements Parcelable {
    public static final Parcelable.Creator<Location> CREATOR = new Parcelable.Creator<Location>() {
        public Location createFromParcel(Parcel parcel) {
            Location location = new Location();
            location.identifier = (Long) parcel.readValue(Long.class.getClassLoader());
            location.name = (String) parcel.readValue(String.class.getClassLoader());
            location.consents = parcel.createTypedArrayList(ConsentLocation.CREATOR);
            return location;
        }

        public Location[] newArray(int i) {
            return new Location[i];
        }
    };
    @Relationship("consents")
    public List<ConsentLocation> consents;
    @Id(LongIdHandler.class)
    public Long identifier;
    @JsonProperty("name")
    public String name;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Location> locations(List<DBLocation> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Location> arrayList = new ArrayList<>(list.size());
        for (DBLocation location : list) {
            arrayList.add(new Location(location));
        }
        return arrayList;
    }

    public Location(DBLocation dBLocation) {
        if (dBLocation == null) {
            this.identifier = 0L;
            this.name = "None Indicated";
            return;
        }
        this.identifier = dBLocation.getIdentifier();
        this.name = dBLocation.getName();
    }

    public Location() {
        this((DBLocation) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.name);
        parcel.writeTypedList(this.consents);
    }

    public static List<Location> convertJsonToLocations(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToLocation(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Location convertJsonToLocation(JsonReader jsonReader) throws IOException, ParseException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.skipValue();
            return null;
        }
        Location location = new Location();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                location.identifier = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                location.name = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return location;
    }

    public static ArrayList<Location> convertJsonInputStreamToLocations(InputStream inputStream) {
        Class<Location> cls = Location.class;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setConfig(objectMapper.getDeserializationConfig().without(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
        ResourceConverter resourceConverter = new ResourceConverter(objectMapper, (Class<?>[]) new Class[]{cls, ConsentLocation.class});
        resourceConverter.enableDeserializationOption(com.github.jasminb.jsonapi.DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS);
        return new ArrayList<>(resourceConverter.readDocumentCollection(inputStream, cls).get());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Long l = this.identifier;
        Long l2 = ((Location) obj).identifier;
        if (l != null) {
            return l.equals(l2);
        }
        if (l2 == null) {
            return true;
        }
        return false;
    }
}
