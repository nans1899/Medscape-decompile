package com.wbmd.qxcalculator.model.parsedObjects;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Error implements Parcelable {
    public static final Parcelable.Creator<Error> CREATOR = new Parcelable.Creator<Error>() {
        public Error createFromParcel(Parcel parcel) {
            Error error = new Error();
            error.identifier = (Long) parcel.readValue(Long.class.getClassLoader());
            error.status = (String) parcel.readValue(Long.class.getClassLoader());
            error.title = (String) parcel.readValue(String.class.getClassLoader());
            error.detail = (String) parcel.readValue(String.class.getClassLoader());
            return error;
        }

        public Error[] newArray(int i) {
            return new Error[i];
        }
    };
    public String detail;
    public Long identifier;
    public String status;
    public String title;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.status);
        parcel.writeValue(this.title);
        parcel.writeValue(this.detail);
    }

    public static List<Error> convertJsonToErrors(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToError(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Error convertJsonToError(JsonReader jsonReader) throws IOException, ParseException {
        Error error = new Error();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                error.identifier = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("status")) {
                error.status = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                error.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("detail")) {
                error.detail = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return error;
    }
}
