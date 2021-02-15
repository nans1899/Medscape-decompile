package com.wbmd.qxcalculator.model.contentItems.referencebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBReferenceBookSection;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReferenceBookSection implements Parcelable {
    public static final Parcelable.Creator<ReferenceBookSection> CREATOR = new Parcelable.Creator<ReferenceBookSection>() {
        public ReferenceBookSection createFromParcel(Parcel parcel) {
            ReferenceBookSection referenceBookSection = new ReferenceBookSection();
            referenceBookSection.identifier = parcel.readString();
            referenceBookSection.title = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSection.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSection.footer = (String) parcel.readValue(String.class.getClassLoader());
            referenceBookSection.position = (Long) parcel.readValue(Long.class.getClassLoader());
            referenceBookSection.sectionItems = parcel.createTypedArrayList(ReferenceBookSectionItem.CREATOR);
            return referenceBookSection;
        }

        public ReferenceBookSection[] newArray(int i) {
            return new ReferenceBookSection[i];
        }
    };
    public String footer;
    public String identifier;
    public Long position;
    public List<ReferenceBookSectionItem> sectionItems;
    public String subTitle;
    public String title;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ReferenceBookSection> referenceBookSections(List<DBReferenceBookSection> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ReferenceBookSection> arrayList = new ArrayList<>(list.size());
        for (DBReferenceBookSection referenceBookSection : list) {
            arrayList.add(new ReferenceBookSection(referenceBookSection));
        }
        return arrayList;
    }

    public ReferenceBookSection(DBReferenceBookSection dBReferenceBookSection) {
        if (dBReferenceBookSection != null) {
            this.identifier = dBReferenceBookSection.getIdentifier();
            this.title = dBReferenceBookSection.getTitle();
            this.subTitle = dBReferenceBookSection.getSubTitle();
            this.footer = dBReferenceBookSection.getFooter();
            this.position = dBReferenceBookSection.getPosition();
            ArrayList<ReferenceBookSectionItem> referenceBookSectionItems = ReferenceBookSectionItem.referenceBookSectionItems(dBReferenceBookSection.getReferenceBookSectionItems());
            this.sectionItems = referenceBookSectionItems;
            for (ReferenceBookSectionItem next : referenceBookSectionItems) {
                updateBreadcrumbPath(next, next.title, new StringBuilder());
            }
        }
    }

    private void updateBreadcrumbPath(ReferenceBookSectionItem referenceBookSectionItem, String str, StringBuilder sb) {
        if (referenceBookSectionItem.subSectionItems == null || referenceBookSectionItem.subSectionItems.isEmpty()) {
            referenceBookSectionItem.topLevelItemName = str;
            referenceBookSectionItem.breadcrumbPath = sb.toString();
            return;
        }
        for (ReferenceBookSectionItem next : referenceBookSectionItem.subSectionItems) {
            StringBuilder sb2 = new StringBuilder(sb.toString());
            if (sb2.length() != 0) {
                sb2.append(" > ");
            }
            sb2.append(next.title);
            updateBreadcrumbPath(next, str, sb2);
        }
    }

    public ReferenceBookSection() {
        this((DBReferenceBookSection) null);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.title);
        parcel.writeValue(this.subTitle);
        parcel.writeValue(this.footer);
        parcel.writeValue(this.position);
        parcel.writeTypedList(this.sectionItems);
    }

    public static List<ReferenceBookSection> convertJsonToReferenceBookSections(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList(100);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToReferenceBookSection(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ReferenceBookSection convertJsonToReferenceBookSection(JsonReader jsonReader) throws IOException, ParseException {
        ReferenceBookSection referenceBookSection = new ReferenceBookSection();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                referenceBookSection.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                referenceBookSection.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                referenceBookSection.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("footer")) {
                referenceBookSection.footer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                referenceBookSection.position = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("section_items")) {
                referenceBookSection.sectionItems = ReferenceBookSectionItem.convertJsonToReferenceBookSectionItems(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return referenceBookSection;
    }

    public String toString() {
        return "ReferenceBookSection [identifier=" + this.identifier + ", sections=" + this.sectionItems + "]";
    }
}
