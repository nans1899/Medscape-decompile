package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class Accreditor implements Parcelable {
    public static final Parcelable.Creator<Accreditor> CREATOR = new Parcelable.Creator<Accreditor>() {
        public Accreditor createFromParcel(Parcel parcel) {
            return new Accreditor(parcel);
        }

        public Accreditor[] newArray(int i) {
            return new Accreditor[i];
        }
    };
    public String creditDescription;
    public String eligbleAudience;
    public String id;
    public String rank;

    public int describeContents() {
        return 0;
    }

    public Accreditor() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.eligbleAudience);
        parcel.writeString(this.creditDescription);
        parcel.writeString(this.rank);
    }

    protected Accreditor(Parcel parcel) {
        this.id = parcel.readString();
        this.eligbleAudience = parcel.readString();
        this.creditDescription = parcel.readString();
        this.rank = parcel.readString();
    }
}
