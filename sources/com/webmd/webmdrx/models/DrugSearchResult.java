package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class DrugSearchResult implements Parcelable {
    public static final Parcelable.Creator<DrugSearchResult> CREATOR = new Parcelable.Creator<DrugSearchResult>() {
        public DrugSearchResult createFromParcel(Parcel parcel) {
            return new DrugSearchResult(parcel);
        }

        public DrugSearchResult[] newArray(int i) {
            return new DrugSearchResult[i];
        }
    };
    private List<Drug> drugsDosages = new ArrayList();
    private String mDrugName;
    private String mId;
    private boolean mIsGeneric;
    private List<DrugSearchResult> mOtherNames = new ArrayList();
    private String mProfessionalContentID;

    public int describeContents() {
        return 0;
    }

    public void setDrugId(String str) {
        this.mId = str;
    }

    public String getDrugId() {
        return this.mId;
    }

    public void setDrugName(String str) {
        this.mDrugName = str;
    }

    public String getDrugName() {
        return this.mDrugName;
    }

    public void setIsGeneric(boolean z) {
        this.mIsGeneric = z;
    }

    public boolean isGeneric() {
        return this.mIsGeneric;
    }

    public void setOtherNames(List<DrugSearchResult> list) {
        this.mOtherNames = list;
    }

    public List<DrugSearchResult> getOtherNames() {
        return this.mOtherNames;
    }

    public List<Drug> getDrugsDosages() {
        return this.drugsDosages;
    }

    public void setDrugDosages(List<Drug> list) {
        this.drugsDosages = list;
    }

    public String getprofessionalContentID() {
        return this.mProfessionalContentID;
    }

    public void setprofessionalContentID(String str) {
        this.mProfessionalContentID = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mDrugName);
        parcel.writeString(this.mProfessionalContentID);
        parcel.writeByte(this.mIsGeneric ? (byte) 1 : 0);
        parcel.writeList(this.mOtherNames);
        parcel.writeList(this.drugsDosages);
    }

    public DrugSearchResult() {
    }

    protected DrugSearchResult(Parcel parcel) {
        this.mId = parcel.readString();
        this.mDrugName = parcel.readString();
        this.mProfessionalContentID = parcel.readString();
        this.mIsGeneric = parcel.readByte() != 0;
        ArrayList arrayList = new ArrayList();
        this.mOtherNames = arrayList;
        parcel.readList(arrayList, DrugSearchResult.class.getClassLoader());
        ArrayList arrayList2 = new ArrayList();
        this.drugsDosages = arrayList2;
        parcel.readList(arrayList2, Drug.class.getClassLoader());
    }
}
