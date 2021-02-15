package com.medscape.android.activity.interactions.models;

import android.os.Parcel;
import android.os.Parcelable;

public class DrugList implements Parcelable {
    public static final Parcelable.Creator<DrugList> CREATOR = new Parcelable.Creator<DrugList>() {
        public DrugList createFromParcel(Parcel parcel) {
            return new DrugList(parcel);
        }

        public DrugList[] newArray(int i) {
            return new DrugList[i];
        }
    };
    private long id;
    private String listName;

    public int describeContents() {
        return 0;
    }

    public DrugList() {
        this.id = -1;
        this.listName = "";
    }

    public DrugList(long j, String str) {
        this.id = j;
        this.listName = str;
    }

    protected DrugList(Parcel parcel) {
        this.id = parcel.readLong();
        this.listName = parcel.readString();
    }

    public Long getId() {
        return Long.valueOf(this.id);
    }

    public void setId(Long l) {
        this.id = l.longValue();
    }

    public String getListName() {
        return this.listName;
    }

    public void setListName(String str) {
        this.listName = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeString(this.listName);
    }
}
