package com.wbmd.qxcalculator.model.contentItems.referencebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.db.DBReferenceBook;
import com.wbmd.qxcalculator.model.db.DBUser;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReferenceBook implements Parcelable {
    public static final Parcelable.Creator<ReferenceBook> CREATOR = new Parcelable.Creator<ReferenceBook>() {
        public ReferenceBook createFromParcel(Parcel parcel) {
            ReferenceBook referenceBook = new ReferenceBook();
            referenceBook.identifier = parcel.readString();
            referenceBook.titlePageSource = (String) parcel.readValue(String.class.getClassLoader());
            referenceBook.titlePageBackgroundColor = (String) parcel.readValue(String.class.getClassLoader());
            referenceBook.sections = parcel.createTypedArrayList(ReferenceBookSection.CREATOR);
            referenceBook.flatSectionItems = parcel.createTypedArrayList(ReferenceBookSectionItem.CREATOR);
            return referenceBook;
        }

        public ReferenceBook[] newArray(int i) {
            return new ReferenceBook[i];
        }
    };
    public List<ReferenceBookSectionItem> flatSectionItems;
    public String identifier;
    public List<ReferenceBookSection> sections;
    public String titlePageBackgroundColor;
    public String titlePageSource;

    public int describeContents() {
        return 0;
    }

    public static ArrayList<ReferenceBook> referenceBooks(List<DBReferenceBook> list) {
        if (list == null) {
            return null;
        }
        ArrayList<ReferenceBook> arrayList = new ArrayList<>(list.size());
        for (DBReferenceBook referenceBook : list) {
            arrayList.add(new ReferenceBook(referenceBook));
        }
        return arrayList;
    }

    public ReferenceBook(DBReferenceBook dBReferenceBook) {
        if (dBReferenceBook != null) {
            this.identifier = dBReferenceBook.getIdentifier();
            this.titlePageSource = dBReferenceBook.getTitlePageSource();
            this.titlePageBackgroundColor = dBReferenceBook.getTitlePageBackgroundColor();
            this.sections = ReferenceBookSection.referenceBookSections(dBReferenceBook.getReferenceBookSections());
        }
    }

    public ReferenceBook() {
        this((DBReferenceBook) null);
    }

    public void initializeReferenceBook(DBUser dBUser) {
        Collections.sort(this.sections, new Comparator<ReferenceBookSection>() {
            public int compare(ReferenceBookSection referenceBookSection, ReferenceBookSection referenceBookSection2) {
                return referenceBookSection.position.compareTo(referenceBookSection2.position);
            }
        });
        for (ReferenceBookSection referenceBookSection : this.sections) {
            sortSection(referenceBookSection.sectionItems);
        }
        this.flatSectionItems = new ArrayList();
        for (ReferenceBookSection referenceBookSection2 : this.sections) {
            flattenSectionItems(referenceBookSection2.sectionItems, this.flatSectionItems);
        }
    }

    private void sortSection(List<ReferenceBookSectionItem> list) {
        if (list != null) {
            Collections.sort(list, new Comparator<ReferenceBookSectionItem>() {
                public int compare(ReferenceBookSectionItem referenceBookSectionItem, ReferenceBookSectionItem referenceBookSectionItem2) {
                    return referenceBookSectionItem.position.compareTo(referenceBookSectionItem2.position);
                }
            });
            for (ReferenceBookSectionItem referenceBookSectionItem : list) {
                sortSection(referenceBookSectionItem.subSectionItems);
            }
        }
    }

    private void flattenSectionItems(List<ReferenceBookSectionItem> list, List<ReferenceBookSectionItem> list2) {
        for (ReferenceBookSectionItem next : list) {
            if (next.subSectionItems != null && !next.subSectionItems.isEmpty()) {
                flattenSectionItems(next.subSectionItems, list2);
            } else if (next.sourceId == null || next.sourceId.isEmpty()) {
                list2.add(next);
            }
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeValue(this.titlePageSource);
        parcel.writeValue(this.titlePageBackgroundColor);
        parcel.writeTypedList(this.sections);
        parcel.writeTypedList(this.flatSectionItems);
    }

    public boolean readJsonTag(JsonReader jsonReader, String str) throws IOException, ParseException {
        if (str.equalsIgnoreCase("title_page_src")) {
            this.titlePageSource = APIParser.readString(jsonReader);
            return true;
        } else if (str.equalsIgnoreCase("title_page_background_color")) {
            this.titlePageBackgroundColor = APIParser.readString(jsonReader);
            return true;
        } else if (!str.equalsIgnoreCase("sections")) {
            return false;
        } else {
            this.sections = ReferenceBookSection.convertJsonToReferenceBookSections(jsonReader);
            return true;
        }
    }

    public String toString() {
        return "ReferenceBook [identifier=" + this.identifier + ", sections=" + this.sections + "]";
    }
}
