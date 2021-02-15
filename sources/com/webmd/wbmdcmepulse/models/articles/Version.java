package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Version implements Parcelable {
    public static final Parcelable.Creator<Version> CREATOR = new Parcelable.Creator<Version>() {
        public Version createFromParcel(Parcel parcel) {
            return new Version(parcel);
        }

        public Version[] newArray(int i) {
            return new Version[i];
        }
    };
    public List<String> buttonUrls;
    public String description;
    public String id;
    public boolean isPrimary;
    public Media media;
    public String title;

    public int describeContents() {
        return 0;
    }

    public Version() {
        this.buttonUrls = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeByte(this.isPrimary ? (byte) 1 : 0);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeParcelable(this.media, i);
        parcel.writeStringList(this.buttonUrls);
    }

    protected Version(Parcel parcel) {
        this.id = parcel.readString();
        this.isPrimary = parcel.readByte() != 0;
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.media = (Media) parcel.readParcelable(Media.class.getClassLoader());
        this.buttonUrls = parcel.createStringArrayList();
    }
}
