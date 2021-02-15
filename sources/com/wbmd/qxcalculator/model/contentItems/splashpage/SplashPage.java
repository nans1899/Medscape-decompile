package com.wbmd.qxcalculator.model.contentItems.splashpage;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBSplashPage;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SplashPage implements Parcelable {
    public static final String CENTER_INSIDE = "CENTER_INSIDE";
    public static final Parcelable.Creator<SplashPage> CREATOR = new Parcelable.Creator<SplashPage>() {
        public SplashPage createFromParcel(Parcel parcel) {
            SplashPage splashPage = new SplashPage();
            splashPage.identifier = parcel.readString();
            splashPage.source = (String) parcel.readValue(String.class.getClassLoader());
            splashPage.type = (String) parcel.readValue(String.class.getClassLoader());
            return splashPage;
        }

        public SplashPage[] newArray(int i) {
            return new SplashPage[i];
        }
    };
    public String identifier;
    public String source;
    public String type;

    public enum SplashPageType {
        CENTER_INSIDE,
        NOT_SET
    }

    public int describeContents() {
        return 0;
    }

    public static ArrayList<SplashPage> splashPages(List<DBSplashPage> list) {
        if (list == null) {
            return null;
        }
        ArrayList<SplashPage> arrayList = new ArrayList<>(list.size());
        for (DBSplashPage splashPage : list) {
            arrayList.add(new SplashPage(splashPage));
        }
        return arrayList;
    }

    public SplashPage(DBSplashPage dBSplashPage) {
        if (dBSplashPage != null) {
            this.identifier = dBSplashPage.getIdentifier();
            this.source = dBSplashPage.getSource();
            this.type = dBSplashPage.getType();
        }
    }

    public SplashPage() {
        this((DBSplashPage) null);
    }

    public static SplashPageType getSplashPageType(String str) {
        if (str == null) {
            return SplashPageType.NOT_SET;
        }
        if (str.equalsIgnoreCase(CENTER_INSIDE)) {
            return SplashPageType.CENTER_INSIDE;
        }
        return SplashPageType.NOT_SET;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.source);
        parcel.writeValue(this.type);
    }

    public boolean readJsonTag(JsonReader jsonReader, String str) throws IOException, ParseException {
        if (!str.equalsIgnoreCase("resources")) {
            return false;
        }
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (nextName.equalsIgnoreCase("id")) {
                    this.identifier = APIParser.readString(jsonReader);
                } else if (nextName.equalsIgnoreCase("src")) {
                    this.source = APIParser.readString(jsonReader);
                } else if (nextName.equalsIgnoreCase("type")) {
                    this.type = APIParser.readString(jsonReader);
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        }
        jsonReader.endArray();
        return true;
    }

    public String toString() {
        return "SplashPage [identifier=" + this.identifier + ", source=" + this.source + ", type=" + this.type + "]";
    }
}
