package com.webmd.wbmdproffesionalauthentication.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.AppEventsConstants;

public class BasicInfo implements Parcelable {
    public static final Parcelable.Creator<BasicInfo> CREATOR = new Parcelable.Creator<BasicInfo>() {
        public BasicInfo createFromParcel(Parcel parcel) {
            return new BasicInfo(parcel);
        }

        public BasicInfo[] newArray(int i) {
            return new BasicInfo[i];
        }
    };
    private String city;
    private String mEmailAddress;
    private String mFirstName;
    private String mLastName;
    private String mPassword;
    private String mUserId;
    private String mZipCode;
    private String workPhone;

    public int describeContents() {
        return 0;
    }

    public BasicInfo() {
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String str) {
        this.mPassword = str;
    }

    public String getEmailAddress() {
        return this.mEmailAddress;
    }

    public void setEmailAddress(String str) {
        this.mEmailAddress = str;
    }

    public String getZipCode() {
        return this.mZipCode;
    }

    public void setZipCode(String str) {
        this.mZipCode = str;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public void setFirstName(String str) {
        this.mFirstName = str;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public void setLastName(String str) {
        this.mLastName = str;
    }

    public String getEncryptedRegisteredId() {
        if (this.mUserId == null) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        return "" + (Integer.valueOf(this.mUserId).intValue() * 27);
    }

    public String getWorkPhone() {
        return this.workPhone;
    }

    public void setWorkPhone(String str) {
        this.workPhone = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    protected BasicInfo(Parcel parcel) {
        this.mEmailAddress = parcel.readString();
        this.mPassword = parcel.readString();
        this.mZipCode = parcel.readString();
        this.mUserId = parcel.readString();
        this.mFirstName = parcel.readString();
        this.mLastName = parcel.readString();
        this.workPhone = parcel.readString();
        this.city = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEmailAddress);
        parcel.writeString(this.mPassword);
        parcel.writeString(this.mZipCode);
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mFirstName);
        parcel.writeString(this.mLastName);
        parcel.writeString(this.workPhone);
        parcel.writeString(this.city);
    }
}
