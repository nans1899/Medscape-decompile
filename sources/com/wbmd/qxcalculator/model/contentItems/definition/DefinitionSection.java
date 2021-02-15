package com.wbmd.qxcalculator.model.contentItems.definition;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBDefinitionSection;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class DefinitionSection implements Parcelable {
    public static final Parcelable.Creator<DefinitionSection> CREATOR = new Parcelable.Creator<DefinitionSection>() {
        public DefinitionSection createFromParcel(Parcel parcel) {
            DefinitionSection definitionSection = new DefinitionSection();
            definitionSection.identifier = parcel.readString();
            definitionSection.title = (String) parcel.readValue(String.class.getClassLoader());
            definitionSection.body = (String) parcel.readValue(String.class.getClassLoader());
            definitionSection.position = (Integer) parcel.readValue(Integer.class.getClassLoader());
            return definitionSection;
        }

        public DefinitionSection[] newArray(int i) {
            return new DefinitionSection[i];
        }
    };
    public String body;
    public String identifier;
    public Integer position;
    public String title;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<DefinitionSection> definitionSections(List<DBDefinitionSection> list) {
        if (list == null) {
            return null;
        }
        ArrayList<DefinitionSection> arrayList = new ArrayList<>(list.size());
        for (DBDefinitionSection definitionSection : list) {
            arrayList.add(new DefinitionSection(definitionSection));
        }
        return arrayList;
    }

    public DefinitionSection(DBDefinitionSection dBDefinitionSection) {
        if (dBDefinitionSection != null) {
            this.identifier = dBDefinitionSection.getIdentifier();
            this.title = dBDefinitionSection.getTitle();
            this.body = dBDefinitionSection.getBody();
            this.position = dBDefinitionSection.getPosition();
        }
    }

    public DefinitionSection() {
        this((DBDefinitionSection) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.title);
        parcel.writeValue(this.body);
        parcel.writeValue(this.position);
    }

    public static List<DefinitionSection> convertJsonToDefinitionSections(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToDefinitionSection(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static DefinitionSection convertJsonToDefinitionSection(JsonReader jsonReader) throws IOException, ParseException {
        DefinitionSection definitionSection = new DefinitionSection();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                definitionSection.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                definitionSection.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("definition")) {
                definitionSection.body = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                definitionSection.position = APIParser.readInteger(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return definitionSection;
    }

    public String toString() {
        return "DefinitionSection [identifier=" + this.identifier + ", title=" + this.title + ", body=" + this.body + ", position=" + this.position + "]";
    }
}
