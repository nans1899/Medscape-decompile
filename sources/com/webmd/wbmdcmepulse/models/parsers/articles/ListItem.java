package com.webmd.wbmdcmepulse.models.parsers.articles;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;

public class ListItem implements Parcelable {
    public static final Parcelable.Creator<ListItem> CREATOR = new Parcelable.Creator<ListItem>() {
        public ListItem createFromParcel(Parcel parcel) {
            return new ListItem(parcel);
        }

        public ListItem[] newArray(int i) {
            return new ListItem[i];
        }
    };
    private int mDepth;
    private int mOrder;
    private SpannableStringBuilder mSpanText;
    private String mText;
    private String mType;

    public int describeContents() {
        return 0;
    }

    public ListItem() {
    }

    public ListItem(String str, String str2, int i, int i2) {
        this.mText = str;
        this.mType = str2;
        this.mDepth = i;
        this.mOrder = i2;
    }

    public ListItem(SpannableStringBuilder spannableStringBuilder, String str, int i, int i2) {
        this.mSpanText = spannableStringBuilder;
        this.mType = str;
        this.mDepth = i;
        this.mOrder = i2;
    }

    public SpannableStringBuilder getSpanText() {
        return this.mSpanText;
    }

    public void setSpanText(SpannableStringBuilder spannableStringBuilder) {
        this.mSpanText = spannableStringBuilder;
    }

    public String getText() {
        return this.mText;
    }

    public void setText(String str) {
        this.mText = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public int getDepth() {
        return this.mDepth;
    }

    public void setDepth(int i) {
        this.mDepth = i;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public void setOrder(int i) {
        this.mOrder = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mText);
        parcel.writeString(this.mType);
        parcel.writeInt(this.mDepth);
        parcel.writeInt(this.mOrder);
    }

    private ListItem(Parcel parcel) {
        this.mText = parcel.readString();
        this.mType = parcel.readString();
        this.mDepth = parcel.readInt();
        this.mOrder = parcel.readInt();
    }
}
