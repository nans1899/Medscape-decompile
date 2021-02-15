package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class ContributorComment implements Parcelable {
    public static final Parcelable.Creator<ContributorComment> CREATOR = new Parcelable.Creator<ContributorComment>() {
        public ContributorComment createFromParcel(Parcel parcel) {
            return new ContributorComment(parcel);
        }

        public ContributorComment[] newArray(int i) {
            return new ContributorComment[i];
        }
    };
    public String body;
    public String title;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.body);
    }

    public ContributorComment() {
    }

    protected ContributorComment(Parcel parcel) {
        this.title = parcel.readString();
        this.body = parcel.readString();
    }
}
