package com.wbmd.qxcalculator.model.contentItems.filesource;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBFileSource;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FileSource implements Parcelable {
    public static final Parcelable.Creator<FileSource> CREATOR = new Parcelable.Creator<FileSource>() {
        public FileSource createFromParcel(Parcel parcel) {
            FileSource fileSource = new FileSource();
            fileSource.identifier = parcel.readString();
            fileSource.source = (String) parcel.readValue(String.class.getClassLoader());
            fileSource.type = (String) parcel.readValue(String.class.getClassLoader());
            return fileSource;
        }

        public FileSource[] newArray(int i) {
            return new FileSource[i];
        }
    };
    public static final String HTML_EXT = "HTML_EXT";
    public static final String HTML_EXT_BROWSER = "HTML_EXT_BROWSER";
    public static final String HTML_INT = "HTML_INT";
    public static final String PDF_EXT = "PDF_EXT";
    public static final String PDF_INT = "PDF_INT";
    public String identifier;
    public String source;
    public String type;

    public enum FileSourceType {
        HTML_EXT,
        HTML_EXT_BROWSER,
        HTML_INT,
        PDF_EXT,
        PDF_INT,
        NOT_SET
    }

    public int describeContents() {
        return 0;
    }

    public static ArrayList<FileSource> fileSources(List<DBFileSource> list) {
        if (list == null) {
            return null;
        }
        ArrayList<FileSource> arrayList = new ArrayList<>(list.size());
        for (DBFileSource fileSource : list) {
            arrayList.add(new FileSource(fileSource));
        }
        return arrayList;
    }

    public FileSource(DBFileSource dBFileSource) {
        if (dBFileSource != null) {
            this.identifier = dBFileSource.getIdentifier();
            this.source = dBFileSource.getSource();
            this.type = dBFileSource.getType();
        }
    }

    public FileSource() {
        this((DBFileSource) null);
    }

    public static FileSourceType getFileSourceType(String str) {
        if (str == null) {
            return FileSourceType.NOT_SET;
        }
        if (str.equalsIgnoreCase(HTML_EXT)) {
            return FileSourceType.HTML_EXT;
        }
        if (str.equalsIgnoreCase(HTML_EXT_BROWSER)) {
            return FileSourceType.HTML_EXT_BROWSER;
        }
        if (str.equalsIgnoreCase(HTML_INT)) {
            return FileSourceType.HTML_INT;
        }
        if (str.equalsIgnoreCase(PDF_EXT)) {
            return FileSourceType.PDF_EXT;
        }
        if (str.equalsIgnoreCase(PDF_INT)) {
            return FileSourceType.PDF_INT;
        }
        return FileSourceType.NOT_SET;
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
        return "HtmlSource [identifier=" + this.identifier + ", source=" + this.source + ", type=" + this.type + "]";
    }
}
