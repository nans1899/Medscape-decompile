package com.medscape.android.consult.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ConsultFeedItem implements Parcelable {
    public static final Parcelable.Creator<ConsultFeedItem> CREATOR = new Parcelable.Creator<ConsultFeedItem>() {
        public ConsultFeedItem createFromParcel(Parcel parcel) {
            return new ConsultFeedItem(parcel);
        }

        public ConsultFeedItem[] newArray(int i) {
            return new ConsultFeedItem[i];
        }
    };
    private boolean mIsPromo;
    private String mUniqueIdentifier;

    public int describeContents() {
        return 0;
    }

    public void setUniqueIdentifier(String str) {
        this.mUniqueIdentifier = str;
    }

    public String getUniqueIdentifier() {
        return this.mUniqueIdentifier;
    }

    public void setIsPromo(boolean z) {
        this.mIsPromo = z;
    }

    public boolean isPromo() {
        return this.mIsPromo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUniqueIdentifier);
        parcel.writeByte(this.mIsPromo ? (byte) 1 : 0);
    }

    public ConsultFeedItem() {
    }

    protected ConsultFeedItem(Parcel parcel) {
        this.mUniqueIdentifier = parcel.readString();
        this.mIsPromo = parcel.readByte() != 0;
    }
}
