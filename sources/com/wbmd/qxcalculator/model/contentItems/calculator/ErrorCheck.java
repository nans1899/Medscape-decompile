package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonWriter;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBErrorCheck;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ErrorCheck implements Parcelable {
    public static final Parcelable.Creator<ErrorCheck> CREATOR = new Parcelable.Creator<ErrorCheck>() {
        public ErrorCheck createFromParcel(Parcel parcel) {
            ErrorCheck errorCheck = new ErrorCheck();
            errorCheck.identifier = parcel.readString();
            errorCheck.orderedId = Long.valueOf(parcel.readLong());
            errorCheck.position = Integer.valueOf(parcel.readInt());
            errorCheck.type = parcel.readString();
            errorCheck.title = (String) parcel.readValue(String.class.getClassLoader());
            errorCheck.answer = (String) parcel.readValue(String.class.getClassLoader());
            errorCheck.answerResult = (String) parcel.readValue(String.class.getClassLoader());
            errorCheck.formula = (String) parcel.readValue(String.class.getClassLoader());
            errorCheck.hasError = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            return errorCheck;
        }

        public ErrorCheck[] newArray(int i) {
            return new ErrorCheck[i];
        }
    };
    public String answer;
    public String answerResult;
    public String formula;
    public Boolean hasError;
    public String identifier;
    public Long orderedId;
    public Integer position;
    public String title;
    public String type;

    public int describeContents() {
        return 0;
    }

    public void initializeErrorCheck(DBUser dBUser) {
    }

    public static ArrayList<ErrorCheck> errorChecks(List<DBErrorCheck> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ErrorCheck> arrayList = new ArrayList<>(list.size());
        for (DBErrorCheck errorCheck : list) {
            arrayList.add(new ErrorCheck(errorCheck));
        }
        return arrayList;
    }

    public ErrorCheck(DBErrorCheck dBErrorCheck) {
        if (dBErrorCheck != null) {
            this.identifier = dBErrorCheck.getIdentifier();
            this.orderedId = dBErrorCheck.getOrderedId();
            this.position = dBErrorCheck.getPosition();
            this.type = dBErrorCheck.getType();
            this.title = dBErrorCheck.getTitle();
            this.answer = dBErrorCheck.getAnswer();
            this.formula = dBErrorCheck.getFormula();
        }
    }

    public ErrorCheck() {
        this((DBErrorCheck) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeLong(this.orderedId.longValue());
        parcel.writeInt(this.position.intValue());
        parcel.writeString(this.type);
        parcel.writeValue(this.title);
        parcel.writeValue(this.answer);
        parcel.writeValue(this.answerResult);
        parcel.writeValue(this.formula);
        parcel.writeValue(this.hasError);
    }

    public static List<ErrorCheck> convertJsonToErrorChecks(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList(30);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToErrorCheck(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ErrorCheck convertJsonToErrorCheck(JsonReader jsonReader) throws IOException, ParseException {
        ErrorCheck errorCheck = new ErrorCheck();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                errorCheck.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("ordered_id")) {
                errorCheck.orderedId = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                errorCheck.position = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                errorCheck.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                errorCheck.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("answer")) {
                errorCheck.answer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("formula")) {
                errorCheck.formula = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return errorCheck;
    }

    public static void convertToJson(JsonWriter jsonWriter, List<ErrorCheck> list) throws IOException {
        jsonWriter.beginArray();
        for (ErrorCheck next : list) {
            jsonWriter.beginObject();
            jsonWriter.name("ordered_id").value(next.identifier);
            jsonWriter.name("position").value(next.position);
            jsonWriter.name("type").value(next.type);
            jsonWriter.name("title").value(next.title);
            jsonWriter.name("answer").value(next.answer);
            jsonWriter.name("formula").value(next.formula);
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    public String toString() {
        return "ErrorCheck [contentSpecificIdentifier=" + this.identifier + ", position=" + this.position + ", type=" + this.type + ", title=" + this.title + ", answer=" + this.answer + ", formula=" + this.formula + "]";
    }
}
