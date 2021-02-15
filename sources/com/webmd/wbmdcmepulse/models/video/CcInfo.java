package com.webmd.wbmdcmepulse.models.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class CcInfo implements Parcelable {
    public static final Parcelable.Creator<CcInfo> CREATOR = new Parcelable.Creator<CcInfo>() {
        public CcInfo createFromParcel(Parcel parcel) {
            return new CcInfo(parcel);
        }

        public CcInfo[] newArray(int i) {
            return new CcInfo[i];
        }
    };
    @SerializedName("en")
    private String mEn;
    @SerializedName("thisTabForStudioUseOnly")
    private String mThisTabForStudioUseOnly;

    public int describeContents() {
        return 0;
    }

    public String getEn() {
        return this.mEn;
    }

    public void setEn(String str) {
        this.mEn = str;
    }

    public String getThisTabForStudioUseOnly() {
        return this.mThisTabForStudioUseOnly;
    }

    public void setThisTabForStudioUseOnly(String str) {
        this.mThisTabForStudioUseOnly = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEn);
        parcel.writeString(this.mThisTabForStudioUseOnly);
    }

    public CcInfo() {
    }

    protected CcInfo(Parcel parcel) {
        this.mEn = parcel.readString();
        this.mThisTabForStudioUseOnly = parcel.readString();
    }
}
