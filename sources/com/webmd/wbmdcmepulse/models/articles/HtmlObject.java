package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class HtmlObject implements Parcelable {
    public static final Parcelable.Creator<HtmlObject> CREATOR = new Parcelable.Creator<HtmlObject>() {
        public HtmlObject createFromParcel(Parcel parcel) {
            return new HtmlObject(parcel);
        }

        public HtmlObject[] newArray(int i) {
            return new HtmlObject[i];
        }
    };
    public String content;
    public String htmlType;

    public static class HtmlMarkUp {
        public static final String CLOSE_BRACKER = ">";
        public static final String ENCODED_BULLET_SYMBOL = "&#8226;";
        public static final String OPEN_BRACKER = "<";
        public static final String ORDERED_LIST = "ol";
        public static final String UNORDERED_LIST = "ul";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.htmlType);
        parcel.writeString(this.content);
    }

    public HtmlObject() {
    }

    protected HtmlObject(Parcel parcel) {
        this.htmlType = parcel.readString();
        this.content = parcel.readString();
    }
}
