package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Recent implements Parcelable {
    public static final Parcelable.Creator<Recent> CREATOR = new Parcelable.Creator<Recent>() {
        public Recent createFromParcel(Parcel parcel) {
            Recent recent = new Recent();
            recent.identifier = (String) parcel.readValue(String.class.getClassLoader());
            recent.lastUsedDate = (Long) parcel.readValue(Long.class.getClassLoader());
            return recent;
        }

        public Recent[] newArray(int i) {
            return new Recent[i];
        }
    };
    public String identifier;
    public Long lastUsedDate;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.lastUsedDate);
    }

    public static List<Recent> convertJsonToRecents(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToRecent(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Recent convertJsonToRecent(JsonReader jsonReader) throws IOException, ParseException {
        Recent recent = new Recent();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("friendly_id")) {
                recent.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("last_used")) {
                recent.lastUsedDate = APIParser.readLong(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return recent;
    }
}
