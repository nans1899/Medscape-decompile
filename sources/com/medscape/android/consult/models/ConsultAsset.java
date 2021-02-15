package com.medscape.android.consult.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ConsultAsset implements Parcelable {
    public static final Parcelable.Creator<ConsultAsset> CREATOR = new Parcelable.Creator<ConsultAsset>() {
        public ConsultAsset createFromParcel(Parcel parcel) {
            return new ConsultAsset(parcel);
        }

        public ConsultAsset[] newArray(int i) {
            return new ConsultAsset[i];
        }
    };
    private String mAssertUrl;
    private Bitmap mBitmap;

    private void getSizeOfAsset() {
    }

    private boolean isDataValid() {
        return false;
    }

    private boolean populateAssetWithData() {
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public void setAssetUrl(String str) {
        this.mAssertUrl = str;
    }

    public String getAssetUrl() {
        return this.mAssertUrl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mBitmap, 0);
        parcel.writeString(this.mAssertUrl);
    }

    public ConsultAsset() {
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof ConsultAsset)) {
            ConsultAsset consultAsset = (ConsultAsset) obj;
            if (consultAsset.mAssertUrl != null && getAssetUrl() != null) {
                return consultAsset.mAssertUrl.equals(getAssetUrl());
            }
            if (consultAsset.mAssertUrl == null && getAssetUrl() == null) {
                return true;
            }
        }
        return super.equals(obj);
    }

    protected ConsultAsset(Parcel parcel) {
        this.mBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.mAssertUrl = parcel.readString();
    }
}
