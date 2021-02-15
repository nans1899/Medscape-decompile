package com.medscape.android.drugs.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.medscape.android.drugs.OmnitureValues;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;

public class DrugMonographIndexElement implements Parcelable {
    public static DrugMonographIndexElement AD = new DrugMonographIndexElement("", true);
    public static final DrugMonographIndexElement ADMININSTRATION = new DrugMonographIndexElement("Administration").addIndex(11).withNativeAD();
    public static final Parcelable.Creator<DrugMonographIndexElement> CREATOR = new Parcelable.Creator<DrugMonographIndexElement>() {
        public DrugMonographIndexElement createFromParcel(Parcel parcel) {
            return new DrugMonographIndexElement(parcel);
        }

        public DrugMonographIndexElement[] newArray(int i) {
            return new DrugMonographIndexElement[i];
        }
    };
    public static final DrugMonographIndexElement DISCLAIMER = new DrugMonographIndexElement("");
    public static final DrugMonographIndexElement DOSAGE = new DrugMonographIndexElement("Dosage & Indications").addIndex(0).addIndex(1).addIndex(2).withNativeAD();
    public static final DrugMonographIndexElement EFFECTS = new DrugMonographIndexElement("Adverse Effects").addIndex(4);
    public static final DrugMonographIndexElement FORMULARY = new DrugMonographIndexElement("Formulary");
    public static final DrugMonographIndexElement IMAGES = new DrugMonographIndexElement("Images").addIndex(9);
    public static final DrugMonographIndexElement INTERACTIONS = new DrugMonographIndexElement("Interactions").addIndex(3).withNativeAD();
    public static final DrugMonographIndexElement PHARMACOLOGY = new DrugMonographIndexElement("Pharmacology").addIndex(10).withNativeAD();
    public static final DrugMonographIndexElement PREGNANCY = new DrugMonographIndexElement("Pregnancy").addIndex(6).withNativeAD();
    public static final DrugMonographIndexElement PRICINGSAVINGS = new DrugMonographIndexElement("Pricing & Savings");
    public static final DrugMonographIndexElement WARNINGS = new DrugMonographIndexElement("Warnings").addIndex(5);
    public String deepLink;
    public ArrayList<Integer> indexes;
    public boolean isAD;
    public boolean isProgressRequired = false;
    public String title;
    public boolean wNativeAD;

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.title;
    }

    private DrugMonographIndexElement(String str) {
        this.title = str;
        this.indexes = new ArrayList<>();
        this.isAD = false;
    }

    DrugMonographIndexElement(String str, boolean z) {
        this.title = str;
        this.indexes = new ArrayList<>();
        this.isAD = z;
    }

    private DrugMonographIndexElement addIndex(Integer num) {
        this.indexes.add(num);
        if (StringUtil.isNullOrEmpty(this.deepLink)) {
            this.deepLink = OmnitureValues.getBiSectionNameFromPageNumber(num.intValue());
        }
        return this;
    }

    private DrugMonographIndexElement withNativeAD() {
        this.wNativeAD = true;
        return this;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DrugMonographIndexElement) {
            return this.title.equals(((DrugMonographIndexElement) obj).title);
        }
        return false;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.deepLink);
        parcel.writeList(this.indexes);
        parcel.writeByte(this.isProgressRequired ? (byte) 1 : 0);
        parcel.writeByte(this.isAD ? (byte) 1 : 0);
        parcel.writeByte(this.wNativeAD ? (byte) 1 : 0);
    }

    protected DrugMonographIndexElement(Parcel parcel) {
        boolean z = false;
        this.title = parcel.readString();
        this.deepLink = parcel.readString();
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.indexes = arrayList;
        parcel.readList(arrayList, Integer.class.getClassLoader());
        this.isProgressRequired = parcel.readByte() != 0;
        this.isAD = parcel.readByte() != 0;
        this.wNativeAD = parcel.readByte() != 0 ? true : z;
    }
}
