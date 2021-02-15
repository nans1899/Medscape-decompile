package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class RxForm implements Parcelable {
    public static final Parcelable.Creator<RxForm> CREATOR = new Parcelable.Creator<RxForm>() {
        public RxForm createFromParcel(Parcel parcel) {
            return new RxForm(parcel);
        }

        public RxForm[] newArray(int i) {
            return new RxForm[i];
        }
    };
    private List<Drug> mDrugs = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public List<Drug> getDrugs() {
        return this.mDrugs;
    }

    public void setDrugsList(List<Drug> list) {
        this.mDrugs = list;
    }

    public void addDrugToList(Drug drug) {
        if (drug != null) {
            if (this.mDrugs == null) {
                this.mDrugs = new ArrayList();
            }
            this.mDrugs.add(drug);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mDrugs);
    }

    public RxForm() {
    }

    protected RxForm(Parcel parcel) {
        this.mDrugs = parcel.createTypedArrayList(Drug.CREATOR);
    }
}
