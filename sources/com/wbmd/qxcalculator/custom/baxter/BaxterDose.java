package com.wbmd.qxcalculator.custom.baxter;

import android.os.Parcel;
import android.os.Parcelable;

public class BaxterDose implements Parcelable {
    public static final Parcelable.Creator<BaxterDose> CREATOR = new Parcelable.Creator<BaxterDose>() {
        public BaxterDose createFromParcel(Parcel parcel) {
            BaxterDose baxterDose = new BaxterDose();
            baxterDose.dayTitle = (String) parcel.readValue(String.class.getClassLoader());
            baxterDose.dayType = (String) parcel.readValue(String.class.getClassLoader());
            baxterDose.nightTitle = (String) parcel.readValue(String.class.getClassLoader());
            baxterDose.nightType = (String) parcel.readValue(String.class.getClassLoader());
            return baxterDose;
        }

        public BaxterDose[] newArray(int i) {
            return new BaxterDose[i];
        }
    };
    public String dayTitle;
    public String dayType;
    public String nightTitle;
    public String nightType;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.dayTitle);
        parcel.writeValue(this.dayType);
        parcel.writeValue(this.nightTitle);
        parcel.writeValue(this.nightType);
    }
}
