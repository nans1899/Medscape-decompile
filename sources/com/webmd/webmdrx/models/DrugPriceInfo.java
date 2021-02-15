package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class DrugPriceInfo implements Parcelable {
    public static final Parcelable.Creator<DrugPriceInfo> CREATOR = new Parcelable.Creator<DrugPriceInfo>() {
        public DrugPriceInfo createFromParcel(Parcel parcel) {
            return new DrugPriceInfo(parcel);
        }

        public DrugPriceInfo[] newArray(int i) {
            return new DrugPriceInfo[i];
        }
    };
    private double DiscountPricing;
    private RegularPricing RegularPricing;

    public int describeContents() {
        return 0;
    }

    public double getDiscountPricing() {
        return this.DiscountPricing;
    }

    public void setDiscountPricing(double d) {
        this.DiscountPricing = d;
    }

    public RegularPricing getRegularPricing() {
        return this.RegularPricing;
    }

    public void setRegularPricing(RegularPricing regularPricing) {
        this.RegularPricing = regularPricing;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.DiscountPricing);
        parcel.writeParcelable(this.RegularPricing, i);
    }

    protected DrugPriceInfo(Parcel parcel) {
        this.DiscountPricing = parcel.readDouble();
        this.RegularPricing = (RegularPricing) parcel.readParcelable(getClass().getClassLoader());
    }

    public String toStringDiscountPricing() {
        return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.DiscountPricing)});
    }

    public DrugPriceInfo() {
    }
}
