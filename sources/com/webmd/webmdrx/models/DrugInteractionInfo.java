package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;

public class DrugInteractionInfo implements Comparable<DrugInteractionInfo>, Parcelable {
    public static final Parcelable.Creator<DrugInteractionInfo> CREATOR = new Parcelable.Creator<DrugInteractionInfo>() {
        public DrugInteractionInfo createFromParcel(Parcel parcel) {
            return new DrugInteractionInfo(parcel);
        }

        public DrugInteractionInfo[] newArray(int i) {
            return new DrugInteractionInfo[i];
        }
    };
    private String comment;
    private String drug1Name;
    private String drug2Name;
    private String effect1;
    private String effect2;
    private String mechanism;
    private int severityId;

    public int describeContents() {
        return 0;
    }

    public DrugInteractionInfo() {
        this.drug1Name = "";
        this.drug2Name = "";
        this.severityId = -1;
        this.mechanism = "";
        this.effect1 = "";
        this.effect2 = "";
        this.comment = "";
    }

    protected DrugInteractionInfo(Parcel parcel) {
        this.drug1Name = parcel.readString();
        this.drug2Name = parcel.readString();
        this.severityId = parcel.readInt();
        this.mechanism = parcel.readString();
        this.effect1 = parcel.readString();
        this.effect2 = parcel.readString();
        this.comment = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.drug1Name);
        parcel.writeString(this.drug2Name);
        parcel.writeInt(this.severityId);
        parcel.writeString(this.mechanism);
        parcel.writeString(this.effect1);
        parcel.writeString(this.effect2);
        parcel.writeString(this.comment);
    }

    public String getDrug1Name() {
        return this.drug1Name;
    }

    public void setDrug1Name(String str) {
        this.drug1Name = str;
    }

    public String getDrug2Name() {
        return this.drug2Name;
    }

    public void setDrug2Name(String str) {
        this.drug2Name = str;
    }

    public int getSeverityId() {
        return this.severityId;
    }

    public void setSeverityId(int i) {
        this.severityId = i;
    }

    public String getMechanism() {
        return this.mechanism;
    }

    public void setMechanism(String str) {
        this.mechanism = str;
    }

    public String getEffect1() {
        return this.effect1;
    }

    public void setEffect1(String str) {
        this.effect1 = str;
    }

    public String getEffect2() {
        return this.effect2;
    }

    public void setEffect2(String str) {
        this.effect2 = str;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public boolean isValid() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5 = this.drug1Name;
        if (str5 == null || str5.isEmpty() || (str = this.drug2Name) == null || str.isEmpty() || this.severityId <= -1 || (str2 = this.mechanism) == null || str2.isEmpty() || (str3 = this.effect1) == null || str3.isEmpty() || (str4 = this.effect2) == null || str4.isEmpty()) {
            return false;
        }
        return true;
    }

    public int compareTo(DrugInteractionInfo drugInteractionInfo) {
        if (drugInteractionInfo != null) {
            int i = this.severityId;
            int i2 = drugInteractionInfo.severityId;
            if (i > i2) {
                return -1;
            }
            if (i < i2) {
                return 1;
            }
        }
        return 0;
    }
}
