package com.webmd.wbmdproffesionalauthentication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserEducation implements Parcelable {
    public static final Parcelable.Creator<UserEducation> CREATOR = new Parcelable.Creator<UserEducation>() {
        public UserEducation createFromParcel(Parcel parcel) {
            return new UserEducation(parcel);
        }

        public UserEducation[] newArray(int i) {
            return new UserEducation[i];
        }
    };
    private String licenseId = "";
    private String mBirthYear;
    private String mGraduationYear;
    private String mSchool;
    private String mSchoolCountry;
    private String mSchoolCountryId;
    private String mSchoolId;
    private String mSchoolState;
    private String mSchoolStateId;

    public int describeContents() {
        return 0;
    }

    public UserEducation() {
    }

    public String getSchoolState() {
        return this.mSchoolState;
    }

    public void setSchoolState(String str) {
        this.mSchoolState = str;
        setSchoolCountry((String) null);
        setSchoolCountryId((String) null);
    }

    public String getSchoolStateId() {
        return this.mSchoolStateId;
    }

    public void setSchoolStateId(String str) {
        this.mSchoolStateId = str;
    }

    public String getSchoolCountry() {
        return this.mSchoolCountry;
    }

    public void setSchoolCountry(String str) {
        this.mSchoolCountry = str;
        setSchool((String) null);
        setSchoolId((String) null);
    }

    public String getSchoolCountryId() {
        return this.mSchoolCountryId;
    }

    public void setSchoolCountryId(String str) {
        this.mSchoolCountryId = str;
    }

    public String getSchool() {
        return this.mSchool;
    }

    public void setSchool(String str) {
        this.mSchool = str;
    }

    public String getSchoolId() {
        return this.mSchoolId;
    }

    public void setSchoolId(String str) {
        this.mSchoolId = str;
    }

    public String getBirthYear() {
        return this.mBirthYear;
    }

    public void setBirthYear(String str) {
        this.mBirthYear = str;
        setGraduationYear((String) null);
    }

    public String getGraduationYear() {
        return this.mGraduationYear;
    }

    public void setGraduationYear(String str) {
        this.mGraduationYear = str;
    }

    public String getLicenseId() {
        return this.licenseId;
    }

    public void setLicenseId(String str) {
        this.licenseId = str;
    }

    protected UserEducation(Parcel parcel) {
        this.mSchoolState = parcel.readString();
        this.mSchoolStateId = parcel.readString();
        this.mSchoolCountry = parcel.readString();
        this.mSchoolCountryId = parcel.readString();
        this.mSchool = parcel.readString();
        this.mSchoolId = parcel.readString();
        this.mBirthYear = parcel.readString();
        this.mGraduationYear = parcel.readString();
        this.licenseId = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSchoolState);
        parcel.writeString(this.mSchoolStateId);
        parcel.writeString(this.mSchoolCountry);
        parcel.writeString(this.mSchoolCountryId);
        parcel.writeString(this.mSchool);
        parcel.writeString(this.mSchoolId);
        parcel.writeString(this.mBirthYear);
        parcel.writeString(this.mGraduationYear);
        parcel.writeString(this.licenseId);
    }
}
