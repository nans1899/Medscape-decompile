package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {
    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel parcel) {
            return new Address(parcel);
        }

        public Address[] newArray(int i) {
            return new Address[i];
        }
    };
    @SerializedName("Address1")
    private String mAddress1;
    @SerializedName("Address2")
    private String mAddress2;
    @SerializedName("City")
    private String mCity;
    @SerializedName("Distance")
    private Double mDistance;
    @SerializedName("Latitude")
    private Double mLatitude;
    @SerializedName("Longitude")
    private Double mLongitude;
    @SerializedName("PharmID")
    private String mPharmID;
    @SerializedName("PostalCode")
    private String mPostalCode;
    @SerializedName("State")
    private String mState;

    public int describeContents() {
        return 0;
    }

    public String getAddress1() {
        return this.mAddress1;
    }

    public void setAddress1(String str) {
        this.mAddress1 = str;
    }

    public String getAddress2() {
        return this.mAddress2;
    }

    public void setAddress2(String str) {
        this.mAddress2 = str;
    }

    public String getCity() {
        return this.mCity;
    }

    public void setCity(String str) {
        this.mCity = str;
    }

    public Double getDistance() {
        return this.mDistance;
    }

    public void setDistance(Double d) {
        this.mDistance = d;
    }

    public Double getLatitude() {
        return this.mLatitude;
    }

    public void setLatitude(Double d) {
        this.mLatitude = d;
    }

    public Double getLongitude() {
        return this.mLongitude;
    }

    public void setLongitude(Double d) {
        this.mLongitude = d;
    }

    public String getPharmID() {
        return this.mPharmID;
    }

    public void setPharmID(String str) {
        this.mPharmID = str;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public void setPostalCode(String str) {
        this.mPostalCode = str;
    }

    public String getState() {
        return this.mState;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAddress1);
        parcel.writeString(this.mAddress2);
        parcel.writeString(this.mCity);
        parcel.writeValue(this.mDistance);
        parcel.writeValue(this.mLatitude);
        parcel.writeValue(this.mLongitude);
        parcel.writeString(this.mPharmID);
        parcel.writeString(this.mPostalCode);
        parcel.writeString(this.mState);
    }

    public Address() {
    }

    protected Address(Parcel parcel) {
        this.mAddress1 = parcel.readString();
        this.mAddress2 = parcel.readString();
        this.mCity = parcel.readString();
        this.mDistance = (Double) parcel.readValue(Double.class.getClassLoader());
        this.mLatitude = (Double) parcel.readValue(Double.class.getClassLoader());
        this.mLongitude = (Double) parcel.readValue(Double.class.getClassLoader());
        this.mPharmID = parcel.readString();
        this.mPostalCode = parcel.readString();
        this.mState = parcel.readString();
    }
}
