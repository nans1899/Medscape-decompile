package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.List;

public class CouponLocal implements Parcelable {
    public static final Parcelable.Creator<CouponLocal> CREATOR = new Parcelable.Creator<CouponLocal>() {
        public CouponLocal createFromParcel(Parcel parcel) {
            return new CouponLocal(parcel);
        }

        public CouponLocal[] newArray(int i) {
            return new CouponLocal[i];
        }
    };
    String savingValue;
    String shortText;
    String url;
    List<String> values;

    public int describeContents() {
        return 0;
    }

    public CouponLocal() {
    }

    public CouponLocal(String str, String str2, String str3) {
        this.url = str;
        this.savingValue = str2;
        this.shortText = str3;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getSavingValue() {
        return this.savingValue;
    }

    public void setSavingValue(String str) {
        this.savingValue = str;
    }

    public String getShortText() {
        return this.shortText;
    }

    public void setShortText(String str) {
        this.shortText = str;
    }

    public List<String> getValues() {
        return this.values;
    }

    public void setValues(List<String> list) {
        String str;
        this.values = list;
        String replace = list.get(0).replace("{", "").replace("}", "").replace("\"", "");
        HashMap hashMap = new HashMap();
        for (String split : replace.split(" *, *")) {
            String[] split2 = split.split(" *: *", 2);
            String str2 = split2[0];
            if (split2.length == 1) {
                str = "";
            } else {
                str = split2[1];
            }
            hashMap.put(str2, str);
        }
        this.url = (String) hashMap.get("URL1");
        this.savingValue = (String) hashMap.get("LinkTitle1");
        this.shortText = (String) hashMap.get("ShortText1");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.savingValue);
        parcel.writeString(this.shortText);
        parcel.writeStringList(this.values);
    }

    protected CouponLocal(Parcel parcel) {
        this.url = parcel.readString();
        this.savingValue = parcel.readString();
        this.shortText = parcel.readString();
        this.values = parcel.createStringArrayList();
    }
}
