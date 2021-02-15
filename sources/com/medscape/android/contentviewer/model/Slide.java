package com.medscape.android.contentviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Slide implements Parcelable {
    public static final Parcelable.Creator<Slide> CREATOR = new Parcelable.Creator<Slide>() {
        public Slide createFromParcel(Parcel parcel) {
            return new Slide(parcel);
        }

        public Slide[] newArray(int i) {
            return new Slide[i];
        }
    };
    public String altText;
    public String caption;
    public String graphicUrl;
    public String label;
    public String longDescription;
    public String objectId;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.label);
        parcel.writeString(this.graphicUrl);
        parcel.writeString(this.caption);
        parcel.writeString(this.longDescription);
        parcel.writeString(this.altText);
        parcel.writeString(this.objectId);
    }

    public Slide() {
    }

    protected Slide(Parcel parcel) {
        this.label = parcel.readString();
        this.graphicUrl = parcel.readString();
        this.caption = parcel.readString();
        this.longDescription = parcel.readString();
        this.altText = parcel.readString();
        this.objectId = parcel.readString();
    }
}
