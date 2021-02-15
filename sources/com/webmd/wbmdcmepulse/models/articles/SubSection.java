package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class SubSection implements Parcelable {
    public static final Parcelable.Creator<SubSection> CREATOR = new Parcelable.Creator<SubSection>() {
        public SubSection createFromParcel(Parcel parcel) {
            return new SubSection(parcel);
        }

        public SubSection[] newArray(int i) {
            return new SubSection[i];
        }
    };
    public List<HtmlObject> content;

    public int describeContents() {
        return 0;
    }

    public SubSection() {
        this.content = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.content);
    }

    protected SubSection(Parcel parcel) {
        this.content = parcel.createTypedArrayList(HtmlObject.CREATOR);
    }
}
