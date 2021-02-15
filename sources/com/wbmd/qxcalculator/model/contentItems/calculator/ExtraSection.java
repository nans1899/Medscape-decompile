package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBExtraSection;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExtraSection implements Parcelable {
    public static final Parcelable.Creator<ExtraSection> CREATOR = new Parcelable.Creator<ExtraSection>() {
        public ExtraSection createFromParcel(Parcel parcel) {
            ExtraSection extraSection = new ExtraSection();
            extraSection.identifier = parcel.readString();
            extraSection.title = (String) parcel.readValue(String.class.getClassLoader());
            extraSection.subTitle = (String) parcel.readValue(String.class.getClassLoader());
            extraSection.sectionIndex = (Integer) parcel.readValue(Integer.class.getClassLoader());
            extraSection.showSeparators = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            extraSection.hideWhenNoResults = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            extraSection.conditionFormula = (String) parcel.readValue(String.class.getClassLoader());
            extraSection.conditionFormulaResult = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            extraSection.sectionItems = parcel.createTypedArrayList(ExtraSectionItem.CREATOR);
            return extraSection;
        }

        public ExtraSection[] newArray(int i) {
            return new ExtraSection[i];
        }
    };
    public String conditionFormula;
    public Boolean conditionFormulaResult;
    public Boolean hideWhenNoResults;
    public String identifier;
    public Integer sectionIndex;
    public List<ExtraSectionItem> sectionItems;
    public Boolean showSeparators;
    public String subTitle;
    public String title;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ExtraSection> extraSections(List<DBExtraSection> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ExtraSection> arrayList = new ArrayList<>(list.size());
        for (DBExtraSection extraSection : list) {
            arrayList.add(new ExtraSection(extraSection));
        }
        return arrayList;
    }

    public ExtraSection(DBExtraSection dBExtraSection) {
        if (dBExtraSection != null) {
            this.identifier = dBExtraSection.getIdentifier();
            this.title = dBExtraSection.getTitle();
            this.subTitle = dBExtraSection.getSubTitle();
            this.sectionIndex = dBExtraSection.getSectionIndex();
            this.showSeparators = dBExtraSection.getShowSeparators();
            this.hideWhenNoResults = dBExtraSection.getHideWhenNoResults();
            this.conditionFormula = dBExtraSection.getConditionFormula();
            this.sectionItems = ExtraSectionItem.extraSectionItems(dBExtraSection.getExtraSectionItems());
        }
    }

    public ExtraSection() {
        this((DBExtraSection) null);
    }

    public void initializeExtraSection(DBUser dBUser) {
        List<ExtraSectionItem> list = this.sectionItems;
        if (list != null) {
            Collections.sort(list, new Comparator<ExtraSectionItem>() {
                public int compare(ExtraSectionItem extraSectionItem, ExtraSectionItem extraSectionItem2) {
                    return extraSectionItem.position.compareTo(extraSectionItem2.position);
                }
            });
            for (ExtraSectionItem initializeExtraSectionItem : this.sectionItems) {
                initializeExtraSectionItem.initializeExtraSectionItem(dBUser);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.title);
        parcel.writeValue(this.subTitle);
        parcel.writeValue(this.sectionIndex);
        parcel.writeValue(this.showSeparators);
        parcel.writeValue(this.hideWhenNoResults);
        parcel.writeValue(this.conditionFormula);
        parcel.writeValue(this.conditionFormulaResult);
        parcel.writeTypedList(this.sectionItems);
    }

    public static List<ExtraSection> convertJsonToExtraSections(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToExtraSection(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static ExtraSection convertJsonToExtraSection(JsonReader jsonReader) throws IOException, ParseException {
        ExtraSection extraSection = new ExtraSection();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                extraSection.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                extraSection.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("sub_title")) {
                extraSection.subTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("section_index")) {
                extraSection.sectionIndex = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("show_separators")) {
                extraSection.showSeparators = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("hide_when_no_results")) {
                extraSection.hideWhenNoResults = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("extra_section_items")) {
                extraSection.sectionItems = ExtraSectionItem.convertJsonToExtraSectionItems(jsonReader);
            } else if (nextName.equalsIgnoreCase("condition_formula")) {
                extraSection.conditionFormula = APIParser.readString(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return extraSection;
    }

    public String toString() {
        return "ExtraSection [identifier=" + this.identifier + ", sections=" + this.sectionItems + "]";
    }
}
