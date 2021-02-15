package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import java.io.IOException;

public class ContentTags implements Parcelable {
    public static final Parcelable.Creator<ContentTags> CREATOR = new Parcelable.Creator<ContentTags>() {
        public ContentTags createFromParcel(Parcel parcel) {
            ContentTags contentTags = new ContentTags();
            contentTags.id = ((Integer) parcel.readValue(Integer.class.getClassLoader())).intValue();
            contentTags.calculatorId = ((Integer) parcel.readValue(String.class.getClassLoader())).intValue();
            contentTags.keywords = (String) parcel.readValue(String.class.getClassLoader());
            contentTags.leadSpecialty = (String) parcel.readValue(String.class.getClassLoader());
            contentTags.leadConcept = (String) parcel.readValue(String.class.getClassLoader());
            contentTags.allSpecialty = (String) parcel.readValue(String.class.getClassLoader());
            contentTags.allConcept = (String) parcel.readValue(String.class.getClassLoader());
            contentTags.medscapeId = (String) parcel.readValue(String.class.getClassLoader());
            return contentTags;
        }

        public ContentTags[] newArray(int i) {
            return new ContentTags[i];
        }
    };
    public String allConcept;
    public String allSpecialty;
    public int calculatorId;
    public int id;
    public String keywords;
    public String leadConcept;
    public String leadSpecialty;
    public String medscapeId;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(Integer.valueOf(this.id));
        parcel.writeValue(Integer.valueOf(this.calculatorId));
        parcel.writeValue(this.keywords);
        parcel.writeValue(this.leadSpecialty);
        parcel.writeValue(this.leadConcept);
        parcel.writeValue(this.allSpecialty);
        parcel.writeValue(this.allConcept);
        parcel.writeValue(this.medscapeId);
    }

    public static ContentTags convertJsonToContentTag(JsonReader jsonReader) throws IOException {
        ContentTags contentTags = new ContentTags();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                contentTags.id = APIParser.readInteger(jsonReader).intValue();
            } else if (nextName.equalsIgnoreCase("calculator_id")) {
                contentTags.calculatorId = APIParser.readInteger(jsonReader).intValue();
            } else if (nextName.equalsIgnoreCase("keywords")) {
                contentTags.keywords = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("lead_specialty")) {
                contentTags.leadSpecialty = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("lead_concept")) {
                contentTags.leadConcept = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("all_specialty")) {
                contentTags.allSpecialty = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("all_concept")) {
                contentTags.allConcept = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("medscape_id")) {
                contentTags.medscapeId = APIParser.readString(jsonReader);
            }
        }
        jsonReader.endObject();
        return contentTags;
    }
}
