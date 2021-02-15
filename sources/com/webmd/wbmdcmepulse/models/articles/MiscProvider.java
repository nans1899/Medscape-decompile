package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class MiscProvider implements Parcelable {
    public static final Parcelable.Creator<MiscProvider> CREATOR = new Parcelable.Creator<MiscProvider>() {
        public MiscProvider createFromParcel(Parcel parcel) {
            return new MiscProvider(parcel);
        }

        public MiscProvider[] newArray(int i) {
            return new MiscProvider[i];
        }
    };
    public String LogoUri;
    public String Statement;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.LogoUri);
        parcel.writeString(this.Statement);
    }

    public MiscProvider() {
    }

    protected MiscProvider(Parcel parcel) {
        this.LogoUri = parcel.readString();
        this.Statement = parcel.readString();
    }
}
