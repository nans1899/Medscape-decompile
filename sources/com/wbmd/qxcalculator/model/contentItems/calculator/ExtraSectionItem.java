package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBExtraSectionItem;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExtraSectionItem implements Parcelable {
    public static final Parcelable.Creator<ExtraSectionItem> CREATOR = new Parcelable.Creator<ExtraSectionItem>() {
        public ExtraSectionItem createFromParcel(Parcel parcel) {
            ExtraSectionItem extraSectionItem = new ExtraSectionItem();
            extraSectionItem.identifier = parcel.readString();
            extraSectionItem.position = (Integer) parcel.readValue(Integer.class.getClassLoader());
            extraSectionItem.title = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.footer = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.type = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.source = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.leftImage = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.rightImage = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.backgroundColor = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.color = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.accentColor = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.trackerId = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.trackerCategory = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.trackerEvent = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.trackerLabel = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.hideWhenNoResults = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            extraSectionItem.conditionFormula = (String) parcel.readValue(String.class.getClassLoader());
            extraSectionItem.conditionFormulaResult = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            extraSectionItem.subSectionItems = parcel.createTypedArrayList(ExtraSectionItem.CREATOR);
            return extraSectionItem;
        }

        public ExtraSectionItem[] newArray(int i) {
            return new ExtraSectionItem[i];
        }
    };
    public static final String EXTRA_SECTION_ITEM_TYPE_DEFAULT = "default";
    public static final String EXTRA_SECTION_ITEM_TYPE_HEADER = "section_header";
    public String accentColor;
    public String backgroundColor;
    public String color;
    public String conditionFormula;
    public Boolean conditionFormulaResult;
    public String footer;
    public Boolean hideWhenNoResults;
    public String identifier;
    public String leftImage;
    public Integer position;
    public String rightImage;
    public String source;
    public List<ExtraSectionItem> subSectionItems;
    public String subTitle;
    public String title;
    public String trackerCategory;
    public String trackerEvent;
    public String trackerId;
    public String trackerLabel;
    public String type;

    public enum ExtraSectionType {
        DEFAULT,
        HEADER
    }

    public int describeContents() {
        return 0;
    }

    public ExtraSectionType getType() {
        String str = this.type;
        if (str == null || !str.equalsIgnoreCase(EXTRA_SECTION_ITEM_TYPE_HEADER)) {
            return ExtraSectionType.DEFAULT;
        }
        return ExtraSectionType.HEADER;
    }

    public static ArrayList<ExtraSectionItem> extraSectionItems(List<DBExtraSectionItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ExtraSectionItem> arrayList = new ArrayList<>(list.size());
        for (DBExtraSectionItem extraSectionItem : list) {
            arrayList.add(new ExtraSectionItem(extraSectionItem));
        }
        return arrayList;
    }

    public ExtraSectionItem(DBExtraSectionItem dBExtraSectionItem) {
        if (dBExtraSectionItem != null) {
            this.identifier = dBExtraSectionItem.getIdentifier();
            this.position = dBExtraSectionItem.getPosition();
            this.title = dBExtraSectionItem.getTitle();
            this.subTitle = dBExtraSectionItem.getSubTitle();
            this.footer = dBExtraSectionItem.getFooter();
            this.type = dBExtraSectionItem.getType();
            this.source = dBExtraSectionItem.getSource();
            this.leftImage = dBExtraSectionItem.getLeftImage();
            this.rightImage = dBExtraSectionItem.getRightImage();
            this.backgroundColor = dBExtraSectionItem.getBackgroundColor();
            this.color = dBExtraSectionItem.getColor();
            this.accentColor = dBExtraSectionItem.getAccentColor();
            this.trackerId = dBExtraSectionItem.getTrackerId();
            this.trackerCategory = dBExtraSectionItem.getTrackerCategory();
            this.trackerEvent = dBExtraSectionItem.getTrackerEvent();
            this.trackerLabel = dBExtraSectionItem.getTrackerLabel();
            this.hideWhenNoResults = dBExtraSectionItem.getHideWhenNoResults();
            this.conditionFormula = dBExtraSectionItem.getConditionFormula();
            this.subSectionItems = extraSectionItems(dBExtraSectionItem.getExtraSectionItems());
        }
    }

    public ExtraSectionItem() {
        this((DBExtraSectionItem) null);
    }

    public void initializeExtraSectionItem(DBUser dBUser) {
        List<ExtraSectionItem> list = this.subSectionItems;
        if (list != null) {
            Collections.sort(list, new Comparator<ExtraSectionItem>() {
                public int compare(ExtraSectionItem extraSectionItem, ExtraSectionItem extraSectionItem2) {
                    return extraSectionItem.position.compareTo(extraSectionItem2.position);
                }
            });
            for (ExtraSectionItem initializeExtraSectionItem : this.subSectionItems) {
                initializeExtraSectionItem.initializeExtraSectionItem(dBUser);
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
        parcel.writeValue(this.conditionFormula);
        parcel.writeValue(this.conditionFormulaResult);
        parcel.writeTypedList(this.subSectionItems);
    }

    public static List<ExtraSectionItem> convertJsonToExtraSectionItems(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToExtraSectionItem(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ExtraSectionItem convertJsonToExtraSectionItem(JsonReader jsonReader) throws IOException, ParseException {
        ExtraSectionItem extraSectionItem = new ExtraSectionItem();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                extraSectionItem.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                extraSectionItem.position = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                extraSectionItem.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                extraSectionItem.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("footer")) {
                extraSectionItem.footer = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                extraSectionItem.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("source")) {
                extraSectionItem.source = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("left_img")) {
                extraSectionItem.leftImage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("right_img")) {
                extraSectionItem.rightImage = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("background_color")) {
                extraSectionItem.backgroundColor = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("color")) {
                extraSectionItem.color = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("accent_color")) {
                extraSectionItem.accentColor = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("src")) {
                extraSectionItem.source = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_id")) {
                extraSectionItem.trackerId = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_category")) {
                extraSectionItem.trackerCategory = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_event")) {
                extraSectionItem.trackerEvent = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("tracker_label")) {
                extraSectionItem.trackerLabel = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("hide_when_no_results")) {
                extraSectionItem.hideWhenNoResults = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("extra_section_items")) {
                extraSectionItem.subSectionItems = convertJsonToExtraSectionItems(jsonReader);
            } else if (nextName.equalsIgnoreCase("condition_formula")) {
                extraSectionItem.conditionFormula = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return extraSectionItem;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExtraSectionItem)) {
            return false;
        }
        String str = this.identifier;
        String str2 = ((ExtraSectionItem) obj).identifier;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "ExtraSectionItem [identifier=" + this.identifier + ", sub_sections=" + this.subSectionItems + "]";
    }
}
