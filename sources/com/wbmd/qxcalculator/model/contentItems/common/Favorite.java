package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Favorite implements Parcelable {
    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        public Favorite createFromParcel(Parcel parcel) {
            Favorite favorite = new Favorite();
            favorite.identifier = (String) parcel.readValue(String.class.getClassLoader());
            return favorite;
        }

        public Favorite[] newArray(int i) {
            return new Favorite[i];
        }
    };
    public String identifier;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
    }

    public static List<Favorite> convertJsonToFavorites(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToFavorite(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Favorite convertJsonToFavorite(JsonReader jsonReader) throws IOException, ParseException {
        Favorite favorite = new Favorite();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (jsonReader.nextName().equalsIgnoreCase("friendly_id")) {
                favorite.identifier = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return favorite;
    }
}
