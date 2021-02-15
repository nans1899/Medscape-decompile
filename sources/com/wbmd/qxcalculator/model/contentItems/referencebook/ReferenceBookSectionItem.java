package com.wbmd.qxcalculator.model.contentItems.referencebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReferenceBookSectionItem implements Parcelable {
    public static final Parcelable.Creator<ReferenceBookSectionItem> CREATOR = new Parcelable.Creator<ReferenceBookSectionItem>() {
        public ReferenceBookSectionItem createFromParcel(Parcel parcel) {
            ReferenceBookSectionItem referenceBookSectionItem = new ReferenceBookSectionItem();
            referenceBookSectionItem.identifier = parcel.readString();
            referenceBookSectionItem.title = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.color = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.accentColor = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.source = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.sourceId = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.position = (Long) parcel.readValue(Long.class.getClassLoader());
            referenceBookSectionItem.shareLink = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.topLevelItemName = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.breadcrumbPath = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSectionItem.subSectionItems = parcel.createTypedArrayList(ReferenceBookSectionItem.CREATOR);
            for (ReferenceBookSectionItem referenceBookSectionItem2 : referenceBookSectionItem.subSectionItems) {
                referenceBookSectionItem2.parentItem = referenceBookSectionItem;
            }
            return referenceBookSectionItem;
        }

        public ReferenceBookSectionItem[] newArray(int i) {
            return new ReferenceBookSectionItem[i];
        }
    };
    public String accentColor;
    public String breadcrumbPath;
    public String color;
    public String identifier;
    public ReferenceBookSectionItem parentItem;
    public Long position;
    public String shareLink;
    public String source;
    public String sourceId;
    public List<ReferenceBookSectionItem> subSectionItems;
    public String subTitle;
    public String title;
    public String topLevelItemName;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ReferenceBookSectionItem> referenceBookSectionItems(List<DBReferenceBookSectionItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ReferenceBookSectionItem> arrayList = new ArrayList<>(list.size());
        for (DBReferenceBookSectionItem referenceBookSectionItem : list) {
            arrayList.add(new ReferenceBookSectionItem(referenceBookSectionItem));
        }
        return arrayList;
    }

    public ReferenceBookSectionItem(DBReferenceBookSectionItem dBReferenceBookSectionItem) {
        if (dBReferenceBookSectionItem != null) {
            this.identifier = dBReferenceBookSectionItem.getIdentifier();
            this.title = dBReferenceBookSectionItem.getTitle();
            this.subTitle = dBReferenceBookSectionItem.getSubTitle();
            this.color = dBReferenceBookSectionItem.getColor();
            this.accentColor = dBReferenceBookSectionItem.getAccentColor();
            this.source = dBReferenceBookSectionItem.getSource();
            this.sourceId = dBReferenceBookSectionItem.getSourceId();
            this.position = dBReferenceBookSectionItem.getPosition();
            this.shareLink = dBReferenceBookSectionItem.getShareLink();
            ArrayList<ReferenceBookSectionItem> referenceBookSectionItems = referenceBookSectionItems(dBReferenceBookSectionItem.getReferenceBookSubSectionItems());
            this.subSectionItems = referenceBookSectionItems;
            for (ReferenceBookSectionItem referenceBookSectionItem : referenceBookSectionItems) {
                referenceBookSectionItem.parentItem = this;
            }
        }
    }

    public ReferenceBookSectionItem() {
        this((DBReferenceBookSectionItem) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.title);
        parcel.writeValue(this.subTitle);
        parcel.writeValue(this.color);
        parcel.writeValue(this.accentColor);
        parcel.writeValue(this.source);
        parcel.writeValue(this.sourceId);
        parcel.writeValue(this.position);
        parcel.writeValue(this.shareLink);
        parcel.writeValue(this.topLevelItemName);
        parcel.writeValue(this.breadcrumbPath);
        parcel.writeTypedList(this.subSectionItems);
    }

    public static List<ReferenceBookSectionItem> convertJsonToReferenceBookSectionItems(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList(100);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToReferenceBookSectionItem(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ReferenceBookSectionItem convertJsonToReferenceBookSectionItem(JsonReader jsonReader) throws IOException, ParseException {
        ReferenceBookSectionItem referenceBookSectionItem = new ReferenceBookSectionItem();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                referenceBookSectionItem.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                referenceBookSectionItem.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                referenceBookSectionItem.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("color")) {
                referenceBookSectionItem.color = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("accent_color")) {
                referenceBookSectionItem.accentColor = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("src")) {
                referenceBookSectionItem.source = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("friendly_src_id")) {
                referenceBookSectionItem.sourceId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                referenceBookSectionItem.position = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("share_link")) {
                referenceBookSectionItem.shareLink = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("section_items")) {
                referenceBookSectionItem.subSectionItems = convertJsonToReferenceBookSectionItems(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return referenceBookSectionItem;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReferenceBookSectionItem)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((ReferenceBookSectionItem) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "ReferenceBookSectionItem [identifier=" + this.identifier + ", sub_sections=" + this.subSectionItems + "]";
    }
}
