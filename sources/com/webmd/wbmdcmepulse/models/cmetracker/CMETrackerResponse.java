package com.webmd.wbmdcmepulse.models.cmetracker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;

public class CMETrackerResponse implements Parcelable {
    public static final Parcelable.Creator<CMETrackerResponse> CREATOR = new Parcelable.Creator<CMETrackerResponse>() {
        public CMETrackerResponse createFromParcel(Parcel parcel) {
            return new CMETrackerResponse(parcel);
        }

        public CMETrackerResponse[] newArray(int i) {
            return new CMETrackerResponse[i];
        }
    };
    public ArrayList<CMEItem> completedCmeItems;
    public HashMap creditMap;
    public ArrayList<CMEItem> inProgressCmeItems;
    public ArrayList<CMEItem> locCmeItmes;
    public ArrayList<CMEItem> mocCmeItmes;

    public int describeContents() {
        return 0;
    }

    public CMETrackerResponse() {
        this.completedCmeItems = new ArrayList<>();
        this.inProgressCmeItems = new ArrayList<>();
        this.mocCmeItmes = new ArrayList<>();
        this.locCmeItmes = new ArrayList<>();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.completedCmeItems);
        parcel.writeList(this.inProgressCmeItems);
        parcel.writeList(this.mocCmeItmes);
        parcel.writeList(this.locCmeItmes);
    }

    protected CMETrackerResponse(Parcel parcel) {
        ArrayList<CMEItem> arrayList = new ArrayList<>();
        this.completedCmeItems = arrayList;
        parcel.readList(arrayList, CMEItem.class.getClassLoader());
        ArrayList<CMEItem> arrayList2 = new ArrayList<>();
        this.inProgressCmeItems = arrayList2;
        parcel.readList(arrayList2, CMEItem.class.getClassLoader());
        ArrayList<CMEItem> arrayList3 = new ArrayList<>();
        this.mocCmeItmes = arrayList3;
        parcel.readList(arrayList3, CMEItem.class.getClassLoader());
        ArrayList<CMEItem> arrayList4 = new ArrayList<>();
        this.locCmeItmes = arrayList4;
        parcel.readList(arrayList4, CMEItem.class.getClassLoader());
    }
}
