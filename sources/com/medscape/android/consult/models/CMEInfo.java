package com.medscape.android.consult.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CMEInfo implements Parcelable {
    public static final Parcelable.Creator<CMEInfo> CREATOR = new Parcelable.Creator<CMEInfo>() {
        public CMEInfo createFromParcel(Parcel parcel) {
            return new CMEInfo(parcel);
        }

        public CMEInfo[] newArray(int i) {
            return new CMEInfo[i];
        }
    };
    private String mCMEArticleId;
    private double mCMECreditCount;

    public int describeContents() {
        return 0;
    }

    public void setCMEArticleId(String str) {
        this.mCMEArticleId = str;
    }

    public String getCMEArticleId() {
        return this.mCMEArticleId;
    }

    public void setCMECreditCount(double d) {
        this.mCMECreditCount = d;
    }

    public double getCMECreditCount() {
        return this.mCMECreditCount;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCMEArticleId);
        parcel.writeDouble(this.mCMECreditCount);
    }

    public CMEInfo() {
    }

    protected CMEInfo(Parcel parcel) {
        this.mCMEArticleId = parcel.readString();
        this.mCMECreditCount = parcel.readDouble();
    }
}
