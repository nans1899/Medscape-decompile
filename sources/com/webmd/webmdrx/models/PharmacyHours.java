package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PharmacyHours implements Parcelable {
    public static final Parcelable.Creator<PharmacyHours> CREATOR = new Parcelable.Creator<PharmacyHours>() {
        public PharmacyHours createFromParcel(Parcel parcel) {
            return new PharmacyHours(parcel);
        }

        public PharmacyHours[] newArray(int i) {
            return new PharmacyHours[i];
        }
    };
    private Day Friday;
    private Day Monday;
    private Day Saturday;
    private Day Sunday;
    private Day Thursday;
    private Day Tuesday;
    private Day Wednesday;

    public int describeContents() {
        return 0;
    }

    public Day getSunday() {
        return this.Sunday;
    }

    public void setSunday(Day day) {
        this.Sunday = day;
    }

    public Day getMonday() {
        return this.Monday;
    }

    public void setMonday(Day day) {
        this.Monday = day;
    }

    public Day getTuesday() {
        return this.Tuesday;
    }

    public void setTuesday(Day day) {
        this.Tuesday = day;
    }

    public Day getWednesday() {
        return this.Wednesday;
    }

    public void setWednesday(Day day) {
        this.Wednesday = day;
    }

    public Day getThursday() {
        return this.Thursday;
    }

    public void setThursday(Day day) {
        this.Thursday = day;
    }

    public Day getFriday() {
        return this.Friday;
    }

    public void setFriday(Day day) {
        this.Friday = day;
    }

    public Day getSaturday() {
        return this.Saturday;
    }

    public void setSaturday(Day day) {
        this.Saturday = day;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.Sunday, i);
        parcel.writeParcelable(this.Monday, i);
        parcel.writeParcelable(this.Tuesday, i);
        parcel.writeParcelable(this.Wednesday, i);
        parcel.writeParcelable(this.Thursday, i);
        parcel.writeParcelable(this.Friday, i);
        parcel.writeParcelable(this.Saturday, i);
    }

    public PharmacyHours() {
    }

    protected PharmacyHours(Parcel parcel) {
        this.Sunday = (Day) parcel.readParcelable(getClass().getClassLoader());
        this.Monday = (Day) parcel.readParcelable(getClass().getClassLoader());
        this.Tuesday = (Day) parcel.readParcelable(getClass().getClassLoader());
        this.Wednesday = (Day) parcel.readParcelable(getClass().getClassLoader());
        this.Thursday = (Day) parcel.readParcelable(getClass().getClassLoader());
        this.Friday = (Day) parcel.readParcelable(getClass().getClassLoader());
        this.Saturday = (Day) parcel.readParcelable(getClass().getClassLoader());
    }
}
