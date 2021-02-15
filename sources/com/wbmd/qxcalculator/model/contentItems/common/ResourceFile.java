package com.wbmd.qxcalculator.model.contentItems.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBResourceFile;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ResourceFile implements Parcelable {
    public static final Parcelable.Creator<ResourceFile> CREATOR = new Parcelable.Creator<ResourceFile>() {
        public ResourceFile createFromParcel(Parcel parcel) {
            ResourceFile resourceFile = new ResourceFile();
            resourceFile.identifier = parcel.readString();
            resourceFile.name = (String) parcel.readValue(String.class.getClassLoader());
            resourceFile.lastModifiedDate = (Long) parcel.readValue(Long.class.getClassLoader());
            resourceFile.size = (Long) parcel.readValue(Long.class.getClassLoader());
            resourceFile.srcId = (String) parcel.readValue(String.class.getClassLoader());
            resourceFile.type = (String) parcel.readValue(String.class.getClassLoader());
            resourceFile.dipWidth = (Double) parcel.readValue(Double.class.getClassLoader());
            resourceFile.dipHeight = (Double) parcel.readValue(Double.class.getClassLoader());
            resourceFile.baseName = (String) parcel.readValue(String.class.getClassLoader());
            resourceFile.deviceType = (String) parcel.readValue(String.class.getClassLoader());
            resourceFile.scaleFactor = (Double) parcel.readValue(Double.class.getClassLoader());
            resourceFile.aspectRatio = (Double) parcel.readValue(Double.class.getClassLoader());
            resourceFile.content = (String) parcel.readValue(String.class.getClassLoader());
            resourceFile.contentItemIdentifier = (String) parcel.readValue(String.class.getClassLoader());
            return resourceFile;
        }

        public ResourceFile[] newArray(int i) {
            return new ResourceFile[i];
        }
    };
    public Double aspectRatio;
    public String baseName;
    public String content;
    public String contentItemIdentifier;
    public String deviceType;
    public Double dipHeight;
    public Double dipWidth;
    public String identifier;
    public Long lastModifiedDate;
    public String name;
    public Double scaleFactor;
    public Long size;
    public String srcId;
    public String type;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ResourceFile> resourceFiles(List<DBResourceFile> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ResourceFile> arrayList = new ArrayList<>(list.size());
        for (DBResourceFile resourceFile : list) {
            arrayList.add(new ResourceFile(resourceFile));
        }
        return arrayList;
    }

    public ResourceFile(DBResourceFile dBResourceFile) {
        if (dBResourceFile != null) {
            this.identifier = dBResourceFile.getIdentifier();
            this.name = dBResourceFile.getName();
            this.lastModifiedDate = dBResourceFile.getLastModifiedDate();
            this.size = dBResourceFile.getSize();
            this.srcId = dBResourceFile.getSrc();
            this.type = dBResourceFile.getType();
            this.dipWidth = dBResourceFile.getDipWidth();
            this.dipHeight = dBResourceFile.getDipHeight();
            this.baseName = dBResourceFile.getBaseName();
            this.deviceType = dBResourceFile.getDeviceType();
            this.scaleFactor = dBResourceFile.getScaleFactor();
            this.aspectRatio = dBResourceFile.getAspectRatio();
            this.content = dBResourceFile.getContent();
            this.contentItemIdentifier = dBResourceFile.getContentItemIdentifier();
        }
    }

    public ResourceFile() {
        this((DBResourceFile) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.name);
        parcel.writeValue(this.lastModifiedDate);
        parcel.writeValue(this.size);
        parcel.writeValue(this.srcId);
        parcel.writeValue(this.type);
        parcel.writeValue(this.dipWidth);
        parcel.writeValue(this.dipHeight);
        parcel.writeValue(this.baseName);
        parcel.writeValue(this.deviceType);
        parcel.writeValue(this.scaleFactor);
        parcel.writeValue(this.aspectRatio);
        parcel.writeValue(this.content);
        parcel.writeValue(this.contentItemIdentifier);
    }

    public static List<ResourceFile> convertJsonToResourceFiles(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToResourceFile(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ResourceFile convertJsonToResourceFile(JsonReader jsonReader) throws IOException, ParseException {
        ResourceFile resourceFile = new ResourceFile();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                resourceFile.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("name")) {
                resourceFile.name = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("last_modified")) {
                Long readUnixTimestampAndConvertToMs = APIParser.readUnixTimestampAndConvertToMs(jsonReader);
                resourceFile.lastModifiedDate = readUnixTimestampAndConvertToMs;
                if (readUnixTimestampAndConvertToMs == null) {
                    resourceFile.lastModifiedDate = 0L;
                }
            } else if (nextName.equalsIgnoreCase("size")) {
                resourceFile.size = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("src")) {
                resourceFile.srcId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                resourceFile.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("dip_width")) {
                resourceFile.dipWidth = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("dip_height")) {
                resourceFile.dipHeight = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("base_name")) {
                resourceFile.baseName = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("device_type")) {
                resourceFile.deviceType = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("scale_factor")) {
                resourceFile.scaleFactor = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("aspect_ratio")) {
                resourceFile.aspectRatio = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("content")) {
                resourceFile.content = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return resourceFile;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResourceFile)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((ResourceFile) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "ResourceFile: {contentSpecificIdentifier: " + this.identifier + "; name: " + this.name + "; size: " + this.size + "; lastModDate: " + this.lastModifiedDate + "; srcId: " + this.srcId + ";};";
    }
}
