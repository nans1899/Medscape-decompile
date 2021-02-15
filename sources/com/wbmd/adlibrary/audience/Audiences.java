package com.wbmd.adlibrary.audience;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Audiences implements Parcelable {
    public static final Parcelable.Creator<Audiences> CREATOR = new Parcelable.Creator<Audiences>() {
        public Audiences createFromParcel(Parcel parcel) {
            return new Audiences(parcel);
        }

        public Audiences[] newArray(int i) {
            return new Audiences[i];
        }
    };
    @SerializedName("Audience")
    private List<Audience> mAudience;

    public int describeContents() {
        return 0;
    }

    public List<Audience> getAudience() {
        return this.mAudience;
    }

    public void setAudience(List<Audience> list) {
        this.mAudience = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mAudience);
    }

    public Audiences() {
    }

    protected Audiences(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.mAudience = arrayList;
        parcel.readList(arrayList, Audience.class.getClassLoader());
    }
}
