package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBTag;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Tag implements Parcelable {
    public static final Parcelable.Creator<Tag> CREATOR = new Parcelable.Creator<Tag>() {
        public Tag createFromParcel(Parcel parcel) {
            Tag tag = new Tag();
            tag.name = parcel.readString();
            return tag;
        }

        public Tag[] newArray(int i) {
            return new Tag[i];
        }
    };
    public String identifier;
    public String name;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Tag> tags(List<DBTag> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Tag> arrayList = new ArrayList<>(list.size());
        for (DBTag tag : list) {
            arrayList.add(new Tag(tag));
        }
        return arrayList;
    }

    public Tag(DBTag dBTag) {
        if (dBTag != null) {
            this.identifier = dBTag.getIdentifier();
            this.name = dBTag.getName();
        }
    }

    public Tag() {
        this((DBTag) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
    }

    public static List<Tag> convertJsonToTags(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToTag(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Tag convertJsonToTag(JsonReader jsonReader) throws IOException, ParseException {
        Tag tag = new Tag();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("contentSpecificIdentifier")) {
                tag.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                tag.name = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return tag;
    }

    public static void convertToJson(JsonWriter jsonWriter, List<Tag> list) throws IOException {
        jsonWriter.beginArray();
        for (Tag next : list) {
            jsonWriter.beginObject();
            jsonWriter.name("contentSpecificIdentifier").value(next.name);
            jsonWriter.name("name").value(next.name);
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    public String toString() {
        return "Tag: {name: " + this.name + ";};";
    }
}
