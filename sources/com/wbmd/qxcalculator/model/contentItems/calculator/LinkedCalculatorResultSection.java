package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class LinkedCalculatorResultSection implements Parcelable {
    public static final Parcelable.Creator<LinkedCalculatorResultSection> CREATOR = new Parcelable.Creator<LinkedCalculatorResultSection>() {
        public LinkedCalculatorResultSection createFromParcel(Parcel parcel) {
            LinkedCalculatorResultSection linkedCalculatorResultSection = new LinkedCalculatorResultSection();
            linkedCalculatorResultSection.title = (String) parcel.readValue(String.class.getClassLoader());
            linkedCalculatorResultSection.answer = (String) parcel.readValue(String.class.getClassLoader());
            return linkedCalculatorResultSection;
        }

        public LinkedCalculatorResultSection[] newArray(int i) {
            return new LinkedCalculatorResultSection[i];
        }
    };
    public String answer;
    public String title;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.title);
        parcel.writeValue(this.answer);
    }
}
