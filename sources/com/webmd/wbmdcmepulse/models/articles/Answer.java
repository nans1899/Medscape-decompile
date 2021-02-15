package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    public static final Parcelable.Creator<Answer> CREATOR = new Parcelable.Creator<Answer>() {
        public Answer createFromParcel(Parcel parcel) {
            return new Answer(parcel);
        }

        public Answer[] newArray(int i) {
            return new Answer[i];
        }
    };
    public String choiceExplination;
    public int id;
    public boolean isCorrect;
    public boolean isSelected;
    public int order;
    public String text;
    public int totalResponse;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.order);
        parcel.writeString(this.text);
        parcel.writeString(this.choiceExplination);
        parcel.writeByte(this.isCorrect ? (byte) 1 : 0);
        parcel.writeInt(this.totalResponse);
        parcel.writeByte(this.isSelected ? (byte) 1 : 0);
    }

    public Answer() {
    }

    protected Answer(Parcel parcel) {
        this.id = parcel.readInt();
        this.order = parcel.readInt();
        this.text = parcel.readString();
        this.choiceExplination = parcel.readString();
        boolean z = true;
        this.isCorrect = parcel.readByte() != 0;
        this.totalResponse = parcel.readInt();
        this.isSelected = parcel.readByte() == 0 ? false : z;
    }
}
