package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class PackageSize implements Parcelable {
    public static final Parcelable.Creator<PackageSize> CREATOR = new Parcelable.Creator<PackageSize>() {
        public PackageSize createFromParcel(Parcel parcel) {
            return new PackageSize(parcel);
        }

        public PackageSize[] newArray(int i) {
            return new PackageSize[i];
        }
    };
    private String mDisplay;
    private String mPackageSize;
    private List<Quantity> quantityList = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public PackageSize() {
    }

    public String getPackageSize() {
        return this.mPackageSize;
    }

    public void setPackageSize(String str) {
        this.mPackageSize = str;
    }

    public String getDisplay() {
        return this.mDisplay;
    }

    public void setDisplay(String str) {
        this.mDisplay = str;
    }

    public List<Quantity> getQuantityList() {
        return this.quantityList;
    }

    public void setQuantityList(List<Quantity> list) {
        if (list != null && !list.isEmpty()) {
            if (this.quantityList == null) {
                this.quantityList = new ArrayList();
            }
            this.quantityList.clear();
            for (Quantity add : list) {
                this.quantityList.add(add);
            }
        }
    }

    public void addToQuantityList(Quantity quantity) {
        if (quantity != null) {
            if (this.quantityList == null) {
                this.quantityList = new ArrayList();
            }
            this.quantityList.add(quantity);
        }
    }

    protected PackageSize(Parcel parcel) {
        this.mPackageSize = parcel.readString();
        this.mDisplay = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.quantityList = arrayList;
        parcel.readList(arrayList, Quantity.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPackageSize);
        parcel.writeString(this.mDisplay);
        parcel.writeList(this.quantityList);
    }
}
