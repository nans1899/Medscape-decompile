package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Day implements Parcelable {
    public static final Parcelable.Creator<Day> CREATOR = new Parcelable.Creator<Day>() {
        public Day createFromParcel(Parcel parcel) {
            return new Day(parcel);
        }

        public Day[] newArray(int i) {
            return new Day[i];
        }
    };
    private String endTime;
    private String name;
    private String number;
    private String startTime;

    public int describeContents() {
        return 0;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.number);
        parcel.writeString(this.name);
        parcel.writeString(this.startTime);
        parcel.writeString(this.endTime);
    }

    protected Day(Parcel parcel) {
        this.number = parcel.readString();
        this.name = parcel.readString();
        this.startTime = parcel.readString();
        this.endTime = parcel.readString();
    }
}
