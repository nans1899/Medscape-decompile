package com.wbmd.adlibrary.audience;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Audience implements Parcelable {
    public static final Parcelable.Creator<Audience> CREATOR = new Parcelable.Creator<Audience>() {
        public Audience createFromParcel(Parcel parcel) {
            return new Audience(parcel);
        }

        public Audience[] newArray(int i) {
            return new Audience[i];
        }
    };
    @SerializedName("abbr")
    private String mAbbr;
    @SerializedName("id")
    private String mId;

    public int describeContents() {
        return 0;
    }

    public String getAbbr() {
        return this.mAbbr;
    }

    public void setAbbr(String str) {
        this.mAbbr = str;
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public Audience() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAbbr);
        parcel.writeString(this.mId);
    }

    protected Audience(Parcel parcel) {
        this.mAbbr = parcel.readString();
        this.mId = parcel.readString();
    }
}
