package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MoreInfoSectionItem implements Parcelable {
    public static final Parcelable.Creator<MoreInfoSectionItem> CREATOR = new Parcelable.Creator<MoreInfoSectionItem>() {
        public MoreInfoSectionItem createFromParcel(Parcel parcel) {
            MoreInfoSectionItem moreInfoSectionItem = new MoreInfoSectionItem();
            moreInfoSectionItem.identifier = parcel.readString();
            moreInfoSectionItem.position = (Integer) parcel.readValue(Integer.class.getClassLoader());
            moreInfoSectionItem.title = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.footer = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.type = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.source = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.leftImage = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.rightImage = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.backgroundColor = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.color = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.accentColor = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.trackerId = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.trackerCategory = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.trackerEvent = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.trackerLabel = (String) parcel.readValue(String.class.getClassLoader());
            moreInfoSectionItem.hideWhenNoResults = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            moreInfoSectionItem.subSectionItems = parcel.createTypedArrayList(MoreInfoSectionItem.CREATOR);
            return moreInfoSectionItem;
        }

        public MoreInfoSectionItem[] newArray(int i) {
            return new MoreInfoSectionItem[i];
        }
    };
    public String accentColor;
    public String backgroundColor;
    public String color;
    public String footer;
    public Boolean hideWhenNoResults;
    public String identifier;
    public String leftImage;
    public Integer position;
    public String rightImage;
    public String source;
    public List<MoreInfoSectionItem> subSectionItems;
    public String subTitle;
    public String title;
    public String trackerCategory;
    public String trackerEvent;
    public String trackerId;
    public String trackerLabel;
    public String type;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<MoreInfoSectionItem> moreInfoSectionItems(List<DBMoreInfoSectionItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList<MoreInfoSectionItem> arrayList = new ArrayList<>(list.size());
        for (DBMoreInfoSectionItem moreInfoSectionItem : list) {
            arrayList.add(new MoreInfoSectionItem(moreInfoSectionItem));
        }
        return arrayList;
    }

    public MoreInfoSectionItem(DBMoreInfoSectionItem dBMoreInfoSectionItem) {
        if (dBMoreInfoSectionItem != null) {
            this.identifier = dBMoreInfoSectionItem.getIdentifier();
            this.position = dBMoreInfoSectionItem.getPosition();
            this.title = dBMoreInfoSectionItem.getTitle();
            this.subTitle = dBMoreInfoSectionItem.getSubTitle();
            this.footer = dBMoreInfoSectionItem.getFooter();
            this.type = dBMoreInfoSectionItem.getType();
            this.source = dBMoreInfoSectionItem.getSource();
            this.leftImage = dBMoreInfoSectionItem.getLeftImage();
            this.rightImage = dBMoreInfoSectionItem.getRightImage();
            this.backgroundColor = dBMoreInfoSectionItem.getBackgroundColor();
            this.color = dBMoreInfoSectionItem.getColor();
            this.accentColor = dBMoreInfoSectionItem.getAccentColor();
            this.trackerId = dBMoreInfoSectionItem.getTrackerId();
            this.trackerCategory = dBMoreInfoSectionItem.getTrackerCategory();
            this.trackerEvent = dBMoreInfoSectionItem.getTrackerEvent();
            this.trackerLabel = dBMoreInfoSectionItem.getTrackerLabel();
            this.subSectionItems = moreInfoSectionItems(dBMoreInfoSectionItem.getMoreInfoSectionItems());
        }
    }

    public MoreInfoSectionItem() {
        this((DBMoreInfoSectionItem) null);
    }

    public void initializeMoreInfoSectionItem(DBUser dBUser) {
        List<MoreInfoSectionItem> list = this.subSectionItems;
        if (list != null) {
            Collections.sort(list, new Comparator<MoreInfoSectionItem>() {
                public int compare(MoreInfoSectionItem moreInfoSectionItem, MoreInfoSectionItem moreInfoSectionItem2) {
                    return moreInfoSectionItem.position.compareTo(moreInfoSectionItem2.position);
                }
            });
            for (MoreInfoSectionItem initializeMoreInfoSectionItem : this.subSectionItems) {
                initializeMoreInfoSectionItem.initializeMoreInfoSectionItem(dBUser);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.position);
        parcel.writeValue(this.title);
        parcel.writeValue(this.subTitle);
        parcel.writeValue(this.footer);
        parcel.writeValue(this.type);
        parcel.writeValue(this.source);
        parcel.writeValue(this.leftImage);
        parcel.writeValue(this.rightImage);
        parcel.writeValue(this.backgroundColor);
        parcel.writeValue(this.color);
        parcel.writeValue(this.accentColor);
        parcel.writeValue(this.trackerId);
        parcel.writeValue(this.trackerCategory);
        parcel.writeValue(this.trackerEvent);
        parcel.writeValue(this.trackerLabel);
        parcel.writeValue(this.hideWhenNoResults);
        parcel.writeTypedList(this.subSectionItems);
    }

    public static List<MoreInfoSectionItem> convertJsonToMoreInfoSectionItems(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToMoreInfoSectionItem(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static MoreInfoSectionItem convertJsonToMoreInfoSectionItem(JsonReader jsonReader) throws IOException, ParseException {
        MoreInfoSectionItem moreInfoSectionItem = new MoreInfoSectionItem();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                moreInfoSectionItem.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                moreInfoSectionItem.position = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                moreInfoSectionItem.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                moreInfoSectionItem.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("footer")) {
                moreInfoSectionItem.footer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                moreInfoSectionItem.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("source")) {
                moreInfoSectionItem.source = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("left_img")) {
                moreInfoSectionItem.leftImage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("right_img")) {
                moreInfoSectionItem.rightImage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("background_color")) {
                moreInfoSectionItem.backgroundColor = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("color")) {
                moreInfoSectionItem.color = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("accent_color")) {
                moreInfoSectionItem.accentColor = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("src")) {
                moreInfoSectionItem.source = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_id")) {
                moreInfoSectionItem.trackerId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_category")) {
                moreInfoSectionItem.trackerCategory = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_event")) {
                moreInfoSectionItem.trackerEvent = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_label")) {
                moreInfoSectionItem.trackerLabel = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("hide_when_no_results")) {
                moreInfoSectionItem.hideWhenNoResults = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("more_info_section_items")) {
                moreInfoSectionItem.subSectionItems = convertJsonToMoreInfoSectionItems(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return moreInfoSectionItem;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MoreInfoSectionItem)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((MoreInfoSectionItem) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "MoreInfoSectionItem [identifier=" + this.identifier + ", sub_sections=" + this.subSectionItems + "]";
    }
}
