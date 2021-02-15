package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBMoreInfoSection;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MoreInfoSection implements Parcelable {
    public static final Parcelable.Creator<MoreInfoSection> CREATOR = new Parcelable.Creator<MoreInfoSection>() {
        public MoreInfoSection createFromParcel(Parcel parcel) {
            MoreInfoSection moreInfoSection = new MoreInfoSection();
            moreInfoSection.identifier = parcel.readString();
            moreInfoSection.title = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSection.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSection.position = (Integer) parcel.readValue(Integer.class.getClassLoader());
            moreInfoSection.sectionItems = parcel.createTypedArrayList(MoreInfoSectionItem.CREATOR);
            return moreInfoSection;
        }

        public MoreInfoSection[] newArray(int i) {
            return new MoreInfoSection[i];
        }
    };
    public String identifier;
    public Integer position;
    public List<MoreInfoSectionItem> sectionItems;
    public String subTitle;
    public String title;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<MoreInfoSection> moreInfoSections(List<DBMoreInfoSection> list) {
        if (list == null) {
            return null;
        }
        ArrayList<MoreInfoSection> arrayList = new ArrayList<>(list.size());
        for (DBMoreInfoSection moreInfoSection : list) {
            arrayList.add(new MoreInfoSection(moreInfoSection));
        }
        return arrayList;
    }

    public MoreInfoSection(DBMoreInfoSection dBMoreInfoSection) {
        if (dBMoreInfoSection != null) {
            this.identifier = dBMoreInfoSection.getIdentifier();
            this.title = dBMoreInfoSection.getTitle();
            this.subTitle = dBMoreInfoSection.getSubTitle();
            this.position = dBMoreInfoSection.getPosition();
            this.sectionItems = MoreInfoSectionItem.moreInfoSectionItems(dBMoreInfoSection.getMoreInfoSectionItems());
        }
    }

    public MoreInfoSection() {
        this((DBMoreInfoSection) null);
    }

    public void initializeMoreInfoSection(DBUser dBUser) {
        List<MoreInfoSectionItem> list = this.sectionItems;
        if (list != null) {
            Collections.sort(list, new Comparator<MoreInfoSectionItem>() {
                public int compare(MoreInfoSectionItem moreInfoSectionItem, MoreInfoSectionItem moreInfoSectionItem2) {
                    return moreInfoSectionItem.position.compareTo(moreInfoSectionItem2.position);
                }
            });
            for (MoreInfoSectionItem initializeMoreInfoSectionItem : this.sectionItems) {
                initializeMoreInfoSectionItem.initializeMoreInfoSectionItem(dBUser);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.title);
        parcel.writeValue(this.subTitle);
        parcel.writeValue(this.position);
        parcel.writeTypedList(this.sectionItems);
    }

    public static List<MoreInfoSection> convertJsonToMoreInfoSections(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToMoreInfoSection(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static MoreInfoSection convertJsonToMoreInfoSection(JsonReader jsonReader) throws IOException, ParseException {
        MoreInfoSection moreInfoSection = new MoreInfoSection();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                moreInfoSection.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                moreInfoSection.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                moreInfoSection.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                moreInfoSection.position = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("more_info_section_items")) {
                moreInfoSection.sectionItems = MoreInfoSectionItem.convertJsonToMoreInfoSectionItems(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return moreInfoSection;
    }

    public String toString() {
        return "MoreInfoSection [identifier=" + this.identifier + ", sections=" + this.sectionItems + "]";
    }
}
