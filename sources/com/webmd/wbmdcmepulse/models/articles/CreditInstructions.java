package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class CreditInstructions implements Parcelable {
    public static final Parcelable.Creator<CreditInstructions> CREATOR = new Parcelable.Creator<CreditInstructions>() {
        public CreditInstructions createFromParcel(Parcel parcel) {
            return new CreditInstructions(parcel);
        }

        public CreditInstructions[] newArray(int i) {
            return new CreditInstructions[i];
        }
    };
    public List<String> InstructionList;
    public List<String> Paragraphs;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.Paragraphs);
        parcel.writeStringList(this.InstructionList);
    }

    public CreditInstructions() {
    }

    protected CreditInstructions(Parcel parcel) {
        this.Paragraphs = parcel.createStringArrayList();
        this.InstructionList = parcel.createStringArrayList();
    }
}
