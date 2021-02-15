package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionPageMapItem implements Parcelable {
    public static final Parcelable.Creator<QuestionPageMapItem> CREATOR = new Parcelable.Creator<QuestionPageMapItem>() {
        public QuestionPageMapItem createFromParcel(Parcel parcel) {
            return new QuestionPageMapItem(parcel);
        }

        public QuestionPageMapItem[] newArray(int i) {
            return new QuestionPageMapItem[i];
        }
    };
    public String pageNumber;
    public String testId;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.testId);
        parcel.writeString(this.pageNumber);
    }

    public QuestionPageMapItem() {
    }

    protected QuestionPageMapItem(Parcel parcel) {
        this.testId = parcel.readString();
        this.pageNumber = parcel.readString();
    }
}
