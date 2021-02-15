package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Quantity implements Parcelable {
    public static final Parcelable.Creator<Quantity> CREATOR = new Parcelable.Creator<Quantity>() {
        public Quantity createFromParcel(Parcel parcel) {
            return new Quantity(parcel);
        }

        public Quantity[] newArray(int i) {
            return new Quantity[i];
        }
    };
    private String mDisplay;
    private String mPackageSize;
    private String mPackageUnit;
    private int mQuantity;
    private int mRank;
    private Double priceQuantity;

    public int describeContents() {
        return 0;
    }

    public Double getPriceQuantity() {
        return this.priceQuantity;
    }

    public void setPriceQuantity(Double d) {
        this.priceQuantity = d;
    }

    public void setQuantity(int i) {
        this.mQuantity = i;
    }

    public int getQuantity() {
        return this.mQuantity;
    }

    public void setRank(int i) {
        this.mRank = i;
    }

    public int getRank() {
        return this.mRank;
    }

    public void setDisplay(String str) {
        this.mDisplay = str;
    }

    public String getDisplay() {
        return this.mDisplay;
    }

    public void setPackageSize(String str) {
        this.mPackageSize = str;
    }

    public String getPackageSize() {
        return this.mPackageSize;
    }

    public void setPackageUnit(String str) {
        this.mPackageUnit = str;
    }

    public String getPackageUnit() {
        return this.mPackageUnit;
    }

    public Quantity() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mQuantity);
        parcel.writeInt(this.mRank);
        parcel.writeString(this.mDisplay);
        parcel.writeString(this.mPackageSize);
        parcel.writeString(this.mPackageUnit);
        parcel.writeValue(this.priceQuantity);
    }

    protected Quantity(Parcel parcel) {
        this.mQuantity = parcel.readInt();
        this.mRank = parcel.readInt();
        this.mDisplay = parcel.readString();
        this.mPackageSize = parcel.readString();
        this.mPackageUnit = parcel.readString();
        this.priceQuantity = (Double) parcel.readValue(Double.class.getClassLoader());
    }
}
