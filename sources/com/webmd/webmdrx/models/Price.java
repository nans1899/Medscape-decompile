package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Price implements Parcelable {
    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        public Price createFromParcel(Parcel parcel) {
            return new Price(parcel);
        }

        public Price[] newArray(int i) {
            return new Price[i];
        }
    };
    private DrugPriceInfo DrugPriceInfo;
    private Pharmacy Pharmacy;
    public CouponLocal couponLocal;

    public int describeContents() {
        return 0;
    }

    public Pharmacy getPharmacy() {
        return this.Pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.Pharmacy = pharmacy;
    }

    public DrugPriceInfo getDrugPriceInfo() {
        return this.DrugPriceInfo;
    }

    public void setDrugPriceInfo(DrugPriceInfo drugPriceInfo) {
        this.DrugPriceInfo = drugPriceInfo;
    }

    public Price() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.Pharmacy, i);
        parcel.writeParcelable(this.DrugPriceInfo, i);
    }

    protected Price(Parcel parcel) {
        this.Pharmacy = (Pharmacy) parcel.readParcelable(getClass().getClassLoader());
        this.DrugPriceInfo = (DrugPriceInfo) parcel.readParcelable(getClass().getClassLoader());
    }

    public CouponLocal getCouponLocal() {
        return this.couponLocal;
    }

    public void setCouponLocal(CouponLocal couponLocal2) {
        this.couponLocal = couponLocal2;
    }
}
