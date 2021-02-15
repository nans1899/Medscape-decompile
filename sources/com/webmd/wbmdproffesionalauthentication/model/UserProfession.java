package com.webmd.wbmdproffesionalauthentication.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;

public class UserProfession implements Parcelable {
    public static final Parcelable.Creator<UserProfession> CREATOR = new Parcelable.Creator<UserProfession>() {
        public UserProfession createFromParcel(Parcel parcel) {
            return new UserProfession(parcel);
        }

        public UserProfession[] newArray(int i) {
            return new UserProfession[i];
        }
    };
    private String mAbimId;
    private String mCountry;
    private String mCountryCode;
    private String mOccupation;
    private String mOccupationId;
    private String mProfession;
    private String mProfessionId;
    private String mSpeciality;
    private String mSpecialityId;
    private String mState;
    private String mSubSpecialityId;
    private String mSubSpecialty;

    public int describeContents() {
        return 0;
    }

    public UserProfession() {
        this.mCountryCode = "us";
        this.mCountry = "United States";
    }

    public String getSubSpecialityId() {
        return this.mSubSpecialityId;
    }

    public void setSubSpecialityId(String str) {
        this.mSubSpecialityId = str;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public void setCountry(String str) {
        this.mCountry = str;
        setProfession((String) null);
        setProfessionId((String) null);
    }

    public String getState() {
        return this.mState;
    }

    public void setState(String str) {
        this.mState = str;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public void setCountryCode(String str) {
        this.mCountryCode = str;
    }

    public String getSpeciality() {
        return this.mSpeciality;
    }

    public void setSpeciality(String str) {
        this.mSpeciality = str;
        setSubSpecialty((String) null);
        setSubSpecialityId((String) null);
    }

    public String getSpecialityId() {
        return this.mSpecialityId;
    }

    public void setSpecialityId(String str) {
        this.mSpecialityId = str;
    }

    public String getProfession() {
        return this.mProfession;
    }

    public void setProfession(String str) {
        this.mProfession = str;
        setOccupation((String) null);
        setOccupationId((String) null);
        setSpeciality((String) null);
        setSpecialityId((String) null);
    }

    public String getProfessionId() {
        return this.mProfessionId;
    }

    public void setProfessionId(String str) {
        this.mProfessionId = str;
    }

    public String getOccupation() {
        return this.mOccupation;
    }

    public void setOccupation(String str) {
        this.mOccupation = str;
    }

    public String getOccupationId() {
        return this.mOccupationId;
    }

    public void setOccupationId(String str) {
        this.mOccupationId = str;
    }

    public String getSubSpecialty() {
        return this.mSubSpecialty;
    }

    public void setSubSpecialty(String str) {
        this.mSubSpecialty = str;
    }

    public String getAbimId(Context context) {
        if (StringExtensions.isNullOrEmpty(this.mAbimId)) {
            try {
                this.mAbimId = SharedPreferenceProvider.get().getSimpleCryptoDecryptedString("userMocString", "", context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.mAbimId;
    }

    public void setAbimId(String str) {
        this.mAbimId = str;
    }

    protected UserProfession(Parcel parcel) {
        this.mCountry = parcel.readString();
        this.mState = parcel.readString();
        this.mCountryCode = parcel.readString();
        this.mSpeciality = parcel.readString();
        this.mSpecialityId = parcel.readString();
        this.mProfession = parcel.readString();
        this.mProfessionId = parcel.readString();
        this.mOccupation = parcel.readString();
        this.mOccupationId = parcel.readString();
        this.mSubSpecialty = parcel.readString();
        this.mSubSpecialityId = parcel.readString();
        this.mAbimId = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mState);
        parcel.writeString(this.mCountryCode);
        parcel.writeString(this.mSpeciality);
        parcel.writeString(this.mSpecialityId);
        parcel.writeString(this.mProfession);
        parcel.writeString(this.mProfessionId);
        parcel.writeString(this.mOccupation);
        parcel.writeString(this.mOccupationId);
        parcel.writeString(this.mSubSpecialty);
        parcel.writeString(this.mSubSpecialityId);
        parcel.writeString(this.mAbimId);
    }
}
