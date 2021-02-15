package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class RxData implements Parcelable {
    public static final Parcelable.Creator<RxData> CREATOR = new Parcelable.Creator<RxData>() {
        public RxData createFromParcel(Parcel parcel) {
            return new RxData(parcel);
        }

        public RxData[] newArray(int i) {
            return new RxData[i];
        }
    };
    int deleted;
    List<SavedRxDosage> dosages;

    public int describeContents() {
        return 0;
    }

    public RxData() {
        this.dosages = new ArrayList();
        this.deleted = 0;
    }

    public RxData(RxData rxData) {
        this.deleted = rxData.getDeleted();
        List<SavedRxDosage> list = this.dosages;
        if (list != null) {
            list.clear();
        } else {
            this.dosages = new ArrayList();
        }
        this.dosages.addAll(rxData.getDosages());
    }

    public List<SavedRxDosage> getDosages() {
        return this.dosages;
    }

    public int getDeleted() {
        return this.deleted;
    }

    public void setDeleted(int i) {
        this.deleted = i;
    }

    public void setDosages(List<SavedRxDosage> list) {
        this.dosages = list;
    }

    public boolean addToDosagesList(SavedRxDosage savedRxDosage) {
        if (savedRxDosage == null) {
            return false;
        }
        List<SavedRxDosage> list = this.dosages;
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            this.dosages = arrayList;
            arrayList.add(savedRxDosage);
            return true;
        }
        list.add(savedRxDosage);
        return true;
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("deleted", this.deleted);
            if (this.dosages != null) {
                if (this.dosages.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (SavedRxDosage jsonObject : this.dosages) {
                        jSONArray.put(jsonObject.toJsonObject());
                    }
                    jSONObject.put("dosages", jSONArray);
                    return jSONObject;
                }
            }
            jSONObject.put("dosages", JSONObject.NULL);
            return jSONObject;
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.dosages);
        parcel.writeInt(this.deleted);
    }

    protected RxData(Parcel parcel) {
        this.dosages = parcel.createTypedArrayList(SavedRxDosage.CREATOR);
        this.deleted = parcel.readInt();
    }
}
