package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBPlatform;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Platform implements Parcelable {
    public static final Parcelable.Creator<Platform> CREATOR = new Parcelable.Creator<Platform>() {
        public Platform createFromParcel(Parcel parcel) {
            Platform platform = new Platform();
            platform.identifier = (String) parcel.readValue(String.class.getClassLoader());
            platform.os = (String) parcel.readValue(String.class.getClassLoader());
            platform.deviceType = (String) parcel.readValue(String.class.getClassLoader());
            platform.minVersion = (String) parcel.readValue(String.class.getClassLoader());
            platform.maxVersion = (String) parcel.readValue(String.class.getClassLoader());
            return platform;
        }

        public Platform[] newArray(int i) {
            return new Platform[i];
        }
    };
    public static final String K_OS_ANDROID = "android";
    public String deviceType;
    public String identifier;
    public String maxVersion;
    public String minVersion;
    public String os;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Platform> platforms(List<DBPlatform> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Platform> arrayList = new ArrayList<>(list.size());
        for (DBPlatform platform : list) {
            arrayList.add(new Platform(platform));
        }
        return arrayList;
    }

    public Platform(DBPlatform dBPlatform) {
        if (dBPlatform != null) {
            this.identifier = dBPlatform.getIdentifier();
            this.os = dBPlatform.getOs();
            this.deviceType = dBPlatform.getDeviceType();
            this.minVersion = dBPlatform.getMinVersion();
            this.maxVersion = dBPlatform.getMaxVersion();
        }
    }

    public Platform() {
        this((DBPlatform) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.identifier);
        parcel.writeValue(this.os);
        parcel.writeValue(this.deviceType);
        parcel.writeValue(this.minVersion);
        parcel.writeValue(this.maxVersion);
    }

    public static List<Platform> convertJsonToPlatforms(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToPlatform(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Platform convertJsonToPlatform(JsonReader jsonReader) throws IOException, ParseException {
        Platform platform = new Platform();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                platform.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase(AdParameterKeys.OS)) {
                platform.os = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("device_type")) {
                platform.deviceType = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("min_version")) {
                platform.minVersion = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("max_version")) {
                platform.maxVersion = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return platform;
    }
}
