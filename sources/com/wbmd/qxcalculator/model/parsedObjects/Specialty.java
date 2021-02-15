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
import com.github.jasminb.jsonapi.annotations.Type;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBSpecialty;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Type("specialty")
public class Specialty implements Parcelable {
    public static final Parcelable.Creator<Specialty> CREATOR = new Parcelable.Creator<Specialty>() {
        public Specialty createFromParcel(Parcel parcel) {
            Specialty specialty = new Specialty();
            specialty.identifier = (Long) parcel.readValue(Long.class.getClassLoader());
            specialty.name = (String) parcel.readValue(String.class.getClassLoader());
            specialty.categoryIdentifier = (String) parcel.readValue(String.class.getClassLoader());
            specialty.contributingAuthor = (String) parcel.readValue(String.class.getClassLoader());
            return specialty;
        }

        public Specialty[] newArray(int i) {
            return new Specialty[i];
        }
    };
    public String categoryIdentifier;
    public String contributingAuthor;
    @Id(LongIdHandler.class)
    public Long identifier;
    @JsonProperty("name")
    public String name;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Specialty> specialties(List<DBSpecialty> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Specialty> arrayList = new ArrayList<>(list.size());
        for (DBSpecialty specialty : list) {
            arrayList.add(new Specialty(specialty));
        }
        return arrayList;
    }

    public Specialty(DBSpecialty dBSpecialty) {
        if (dBSpecialty == null) {
            this.identifier = 0L;
            this.name = "None Indicated";
            return;
        }
        this.identifier = dBSpecialty.getIdentifier();
        this.name = dBSpecialty.getName();
        this.categoryIdentifier = dBSpecialty.getCategoryIdentifier();
        this.contributingAuthor = dBSpecialty.getContributingAuthor();
    }

    public Specialty() {
        this((DBSpecialty) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.name);
        parcel.writeValue(this.categoryIdentifier);
        parcel.writeValue(this.contributingAuthor);
    }

    public static List<Specialty> convertJsonToSpecialties(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToSpecialty(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Specialty convertJsonToSpecialty(JsonReader jsonReader) throws IOException, ParseException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.skipValue();
            return null;
        }
        Specialty specialty = new Specialty();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                specialty.identifier = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                specialty.name = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("category_id")) {
                specialty.categoryIdentifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("contributor")) {
                specialty.contributingAuthor = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return specialty;
    }

    public static ArrayList<Specialty> convertJsonInputStreamToSpecialties(InputStream inputStream) {
        Class<Specialty> cls = Specialty.class;
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
        if (!(obj instanceof Specialty)) {
            return false;
        }
        Long l = this.identifier;
        Long l2 = ((Specialty) obj).identifier;
        if (l != null) {
            return l.equals(l2);
        }
        if (l2 == null) {
            return true;
        }
        return false;
    }
}
