package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

public class Provider implements Parcelable {
    public static final Parcelable.Creator<Provider> CREATOR = new Parcelable.Creator<Provider>() {
        public Provider createFromParcel(Parcel parcel) {
            return new Provider(parcel);
        }

        public Provider[] newArray(int i) {
            return new Provider[i];
        }
    };
    public HashMap<String, String> ContactInfo;
    public int Id;
    public String LogoUri;
    public String Name;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Id);
        parcel.writeString(this.Name);
        parcel.writeString(this.LogoUri);
        parcel.writeSerializable(this.ContactInfo);
    }

    public Provider() {
    }

    protected Provider(Parcel parcel) {
        this.Id = parcel.readInt();
        this.Name = parcel.readString();
        this.LogoUri = parcel.readString();
        this.ContactInfo = (HashMap) parcel.readSerializable();
    }
}
