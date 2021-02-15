package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RxProfileDrugDetailSection implements Parcelable {
    public static final Parcelable.Creator<RxProfileDrugDetailSection> CREATOR = new Parcelable.Creator<RxProfileDrugDetailSection>() {
        public RxProfileDrugDetailSection createFromParcel(Parcel parcel) {
            return new RxProfileDrugDetailSection(parcel);
        }

        public RxProfileDrugDetailSection[] newArray(int i) {
            return new RxProfileDrugDetailSection[i];
        }
    };
    private SavedRxDosage dosage;
    private String fdbId;
    private String headerName;
    private boolean isHeader;
    private String lowestPriceAtLocation;
    private String monoId;
    private boolean showLowestPriceInTheHeader;

    public int describeContents() {
        return 0;
    }

    public RxProfileDrugDetailSection() {
        this.isHeader = false;
        this.headerName = "";
        this.dosage = null;
        this.showLowestPriceInTheHeader = true;
        this.fdbId = "";
        this.monoId = "";
        this.lowestPriceAtLocation = "";
    }

    public RxProfileDrugDetailSection(boolean z, String str, String str2, String str3) {
        this.isHeader = z;
        this.headerName = str;
        this.fdbId = str2;
        this.monoId = str3;
    }

    public RxProfileDrugDetailSection(SavedRxDosage savedRxDosage, String str, String str2, String str3) {
        this.isHeader = false;
        this.headerName = "";
        this.dosage = savedRxDosage;
        this.fdbId = str;
        this.monoId = str2;
    }

    public boolean isShowLowestPriceInTheHeader() {
        return this.showLowestPriceInTheHeader;
    }

    public void setShowLowestPriceInTheHeader(boolean z) {
        this.showLowestPriceInTheHeader = z;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public void setHeader(boolean z) {
        this.isHeader = z;
    }

    public String getHeaderName() {
        return this.headerName;
    }

    public void setHeaderName(String str) {
        this.headerName = str;
    }

    public SavedRxDosage getDosage() {
        return this.dosage;
    }

    public void setDosage(SavedRxDosage savedRxDosage) {
        this.dosage = savedRxDosage;
    }

    public String getFdbId() {
        return this.fdbId;
    }

    public void setFdbId(String str) {
        this.fdbId = str;
    }

    public String getMonoId() {
        return this.monoId;
    }

    public void setMonoId(String str) {
        this.monoId = str;
    }

    public SavedDrugData convertToSavedDrugData() {
        SavedDrugData savedDrugData = new SavedDrugData();
        savedDrugData.setType("drugs");
        savedDrugData.setDeleted(0);
        savedDrugData.setItem_id(getFdbId());
        savedDrugData.setSecondaryID(getMonoId());
        SavedRxDosage savedRxDosage = this.dosage;
        if (savedRxDosage != null) {
            savedDrugData.setTitle(savedRxDosage.getDrugName());
            savedDrugData.setDrugDetail(new RxData());
            savedDrugData.getDrugDetail().addToDosagesList(this.dosage);
        }
        return savedDrugData;
    }

    public String getLowestPriceAtLocation() {
        return this.lowestPriceAtLocation;
    }

    public void setLowestPriceAtLocation(String str) {
        this.lowestPriceAtLocation = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isHeader ? (byte) 1 : 0);
        parcel.writeByte(this.showLowestPriceInTheHeader ? (byte) 1 : 0);
        parcel.writeString(this.headerName);
        parcel.writeString(this.fdbId);
        parcel.writeString(this.monoId);
        parcel.writeParcelable(this.dosage, i);
        parcel.writeString(this.lowestPriceAtLocation);
    }

    protected RxProfileDrugDetailSection(Parcel parcel) {
        boolean z = true;
        this.isHeader = parcel.readByte() != 0;
        this.showLowestPriceInTheHeader = parcel.readByte() == 0 ? false : z;
        this.headerName = parcel.readString();
        this.fdbId = parcel.readString();
        this.monoId = parcel.readString();
        this.dosage = (SavedRxDosage) parcel.readParcelable(SavedRxDosage.class.getClassLoader());
        this.lowestPriceAtLocation = parcel.readString();
    }
}
