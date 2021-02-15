package com.wbmd.adlibrary.audience;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Profile implements Parcelable {
    public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
        public Profile createFromParcel(Parcel parcel) {
            return new Profile(parcel);
        }

        public Profile[] newArray(int i) {
            return new Profile[i];
        }
    };
    @SerializedName("Audiences")
    private Audiences mAudiences;
    @SerializedName("pid")
    private String mPid;
    @SerializedName("tpid")
    private String mTpid;

    public int describeContents() {
        return 0;
    }

    public Audiences getAudiences() {
        return this.mAudiences;
    }

    public void setAudiences(Audiences audiences) {
        this.mAudiences = audiences;
    }

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String str) {
        this.mPid = str;
    }

    public String getTpid() {
        return this.mTpid;
    }

    public void setTpid(String str) {
        this.mTpid = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAudiences, i);
        parcel.writeString(this.mPid);
        parcel.writeString(this.mTpid);
    }

    public Profile() {
    }

    protected Profile(Parcel parcel) {
        this.mAudiences = (Audiences) parcel.readParcelable(Audiences.class.getClassLoader());
        this.mPid = parcel.readString();
        this.mTpid = parcel.readString();
    }
}
