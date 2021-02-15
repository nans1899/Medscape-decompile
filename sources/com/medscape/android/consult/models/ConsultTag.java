package com.medscape.android.consult.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ConsultTag extends ConsultFeedItem {
    public static final Parcelable.Creator<ConsultTag> CREATOR = new Parcelable.Creator<ConsultTag>() {
        public ConsultTag createFromParcel(Parcel parcel) {
            return new ConsultTag(parcel);
        }

        public ConsultTag[] newArray(int i) {
            return new ConsultTag[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public ConsultTag() {
    }

    protected ConsultTag(Parcel parcel) {
        super(parcel);
    }
}
