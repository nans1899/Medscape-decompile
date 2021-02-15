package com.medscape.android.drugs.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;

public class DrugMonographSectionIndex implements Parcelable {
    public static final Parcelable.Creator<DrugMonographSectionIndex> CREATOR = new Parcelable.Creator<DrugMonographSectionIndex>() {
        public DrugMonographSectionIndex createFromParcel(Parcel parcel) {
            return new DrugMonographSectionIndex(parcel);
        }

        public DrugMonographSectionIndex[] newArray(int i) {
            return new DrugMonographSectionIndex[i];
        }
    };
    ArrayList<DrugMonographIndexElement> mSectionIndexElements;

    public int describeContents() {
        return 0;
    }

    public DrugMonographSectionIndex(DrugMonograph drugMonograph) {
        this.mSectionIndexElements = new ArrayList<>();
        DrugMonographIndexElement.AD = new DrugMonographIndexElement("", true);
        this.mSectionIndexElements.add(DrugMonographIndexElement.AD);
        if (hasNonEmptySection(drugMonograph, 0) || hasNonEmptySection(drugMonograph, 1) || hasNonEmptySection(drugMonograph, 2)) {
            this.mSectionIndexElements.add(DrugMonographIndexElement.DOSAGE);
        }
        if (hasInteractionsSection(drugMonograph, 3)) {
            this.mSectionIndexElements.add(DrugMonographIndexElement.INTERACTIONS);
        }
        if (hasNonEmptySection(drugMonograph, 4)) {
            this.mSectionIndexElements.add(DrugMonographIndexElement.EFFECTS);
        }
        if (hasNonEmptySection(drugMonograph, 5)) {
            this.mSectionIndexElements.add(DrugMonographIndexElement.WARNINGS);
        }
        if (hasNonEmptySection(drugMonograph, 6)) {
            this.mSectionIndexElements.add(DrugMonographIndexElement.PREGNANCY);
        }
        if (hasNonEmptySection(drugMonograph, 10)) {
            this.mSectionIndexElements.add(DrugMonographIndexElement.PHARMACOLOGY);
        }
        if (hasNonEmptySection(drugMonograph, 11)) {
            this.mSectionIndexElements.add(DrugMonographIndexElement.ADMININSTRATION);
        }
        this.mSectionIndexElements.add(DrugMonographIndexElement.IMAGES);
        DrugMonographIndexElement.FORMULARY.isProgressRequired = false;
        this.mSectionIndexElements.add(DrugMonographIndexElement.FORMULARY);
    }

    public static boolean hasInteractionsSection(DrugMonograph drugMonograph, int i) {
        if (drugMonograph == null || drugMonograph.getSections() == null) {
            return false;
        }
        return drugMonograph.getSections().containsKey(Integer.valueOf(i));
    }

    public static boolean hasNonEmptySection(DrugMonograph drugMonograph, int i) {
        if (drugMonograph == null || drugMonograph.getSections() == null || drugMonograph.getSections().get(Integer.valueOf(i)) == null || !drugMonograph.getSections().containsKey(Integer.valueOf(i)) || drugMonograph.getSections().get(Integer.valueOf(i)).size() <= 0) {
            return false;
        }
        return true;
    }

    public ArrayList<DrugMonographIndexElement> getNavMenuElements() {
        return filterOutFormularyRx(this.mSectionIndexElements);
    }

    private ArrayList<DrugMonographIndexElement> filterOutFormularyRx(ArrayList<DrugMonographIndexElement> arrayList) {
        ArrayList<DrugMonographIndexElement> arrayList2 = new ArrayList<>();
        Iterator<DrugMonographIndexElement> it = arrayList.iterator();
        while (it.hasNext()) {
            DrugMonographIndexElement next = it.next();
            if (!next.equals(DrugMonographIndexElement.FORMULARY) && !next.equals(DrugMonographIndexElement.PRICINGSAVINGS) && !next.equals(DrugMonographIndexElement.DISCLAIMER) && !next.equals(DrugMonographIndexElement.AD)) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    public ArrayList<DrugMonographIndexElement> getIndexElements() {
        return this.mSectionIndexElements;
    }

    public DrugMonographIndexElement getItem(int i) {
        return this.mSectionIndexElements.get(i);
    }

    protected DrugMonographSectionIndex(Parcel parcel) {
        if (parcel.readByte() == 1) {
            ArrayList<DrugMonographIndexElement> arrayList = new ArrayList<>();
            this.mSectionIndexElements = arrayList;
            parcel.readList(arrayList, DrugMonographIndexElement.class.getClassLoader());
            return;
        }
        this.mSectionIndexElements = null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.mSectionIndexElements == null) {
            parcel.writeByte((byte) 0);
            return;
        }
        parcel.writeByte((byte) 1);
        parcel.writeList(this.mSectionIndexElements);
    }
}
