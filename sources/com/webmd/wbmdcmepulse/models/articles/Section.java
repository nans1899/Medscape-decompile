package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Section implements Parcelable {
    public static final Parcelable.Creator<Section> CREATOR = new Parcelable.Creator<Section>() {
        public Section createFromParcel(Parcel parcel) {
            return new Section(parcel);
        }

        public Section[] newArray(int i) {
            return new Section[i];
        }
    };
    public List<SubSection> subsections;
    public String title;

    public int describeContents() {
        return 0;
    }

    public Section() {
        this.subsections = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeTypedList(this.subsections);
    }

    protected Section(Parcel parcel) {
        this.title = parcel.readString();
        this.subsections = parcel.createTypedArrayList(SubSection.CREATOR);
    }
}
