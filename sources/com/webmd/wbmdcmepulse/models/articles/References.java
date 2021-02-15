package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class References implements Parcelable {
    public static final Parcelable.Creator<References> CREATOR = new Parcelable.Creator<References>() {
        public References createFromParcel(Parcel parcel) {
            return new References(parcel);
        }

        public References[] newArray(int i) {
            return new References[i];
        }
    };
    public static final String DISPLAY_TYPE_NONE = "none";
    public static final String DISPLAY_TYPE_ODERED = "order";
    public List<String> content;
    public String displayType;

    public int describeContents() {
        return 0;
    }

    public References() {
        this.content = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.displayType);
        parcel.writeStringList(this.content);
    }

    protected References(Parcel parcel) {
        this.displayType = parcel.readString();
        this.content = parcel.createStringArrayList();
    }
}
