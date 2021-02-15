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
import com.wbmd.qxcalculator.model.db.DBProfession;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Type("profession")
public class Profession implements Parcelable {
    public static final Parcelable.Creator<Profession> CREATOR = new Parcelable.Creator<Profession>() {
        public Profession createFromParcel(Parcel parcel) {
            Profession profession = new Profession();
            profession.identifier = (Long) parcel.readValue(Long.class.getClassLoader());
            profession.name = (String) parcel.readValue(String.class.getClassLoader());
            return profession;
        }

        public Profession[] newArray(int i) {
            return new Profession[i];
        }
    };
    @Id(LongIdHandler.class)
    public Long identifier;
    @JsonProperty("name")
    public String name;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Profession> professions(List<DBProfession> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Profession> arrayList = new ArrayList<>(list.size());
        for (DBProfession profession : list) {
            arrayList.add(new Profession(profession));
        }
        return arrayList;
    }

    public Profession(DBProfession dBProfession) {
        if (dBProfession == null) {
            this.identifier = 0L;
            this.name = "None Indicated";
            return;
        }
        this.identifier = dBProfession.getIdentifier();
        this.name = dBProfession.getName();
    }

    public Profession() {
        this((DBProfession) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.name);
    }

    public static List<Profession> convertJsonToProfessions(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToProfession(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Profession convertJsonToProfession(JsonReader jsonReader) throws IOException, ParseException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.skipValue();
            return null;
        }
        Profession profession = new Profession();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                profession.identifier = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                profession.name = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return profession;
    }

    public static ArrayList<Profession> convertJsonInputStreamToProfession(InputStream inputStream) {
        Class<Profession> cls = Profession.class;
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
        if (!(obj instanceof Profession)) {
            return false;
        }
        Long l = this.identifier;
        Long l2 = ((Profession) obj).identifier;
        if (l != null) {
            return l.equals(l2);
        }
        if (l2 == null) {
            return true;
        }
        return false;
    }
}
