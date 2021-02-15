package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wbmd.wbmdcommons.logging.Trace;
import org.json.JSONObject;

public class ReminderInfo implements Parcelable {
    public static final Parcelable.Creator<ReminderInfo> CREATOR = new Parcelable.Creator<ReminderInfo>() {
        public ReminderInfo createFromParcel(Parcel parcel) {
            return new ReminderInfo(parcel);
        }

        public ReminderInfo[] newArray(int i) {
            return new ReminderInfo[i];
        }
    };
    public int alertInterval;
    public String alertIntervalUnit;
    public String alertStartDate;
    public String alertType;
    public int isEnabled;

    public int describeContents() {
        return 0;
    }

    public ReminderInfo() {
        this.isEnabled = 0;
        this.alertInterval = 30;
        this.alertType = "notification";
        this.alertIntervalUnit = "days";
        this.alertStartDate = "";
    }

    public int getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(int i) {
        this.isEnabled = i;
    }

    public String getAlertType() {
        return this.alertType;
    }

    public void setAlertType(String str) {
        this.alertType = str;
    }

    public int getAlertInterval() {
        return this.alertInterval;
    }

    public void setAlertInterval(int i) {
        this.alertInterval = i;
    }

    public String getAlertIntervalUnit() {
        return this.alertIntervalUnit;
    }

    public void setAlertIntervalUnit(String str) {
        this.alertIntervalUnit = str;
    }

    public String getAlertStartDate() {
        return this.alertStartDate;
    }

    public void setAlertStartDate(String str) {
        this.alertStartDate = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.isEnabled);
        parcel.writeString(this.alertType);
        parcel.writeInt(this.alertInterval);
        parcel.writeString(this.alertIntervalUnit);
        parcel.writeString(this.alertStartDate);
    }

    protected ReminderInfo(Parcel parcel) {
        this.isEnabled = parcel.readInt();
        this.alertType = parcel.readString();
        this.alertInterval = parcel.readInt();
        this.alertIntervalUnit = parcel.readString();
        this.alertStartDate = parcel.readString();
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("push_enabled", this.isEnabled);
            if (this.alertType != null) {
                jSONObject.put("alert_type", this.alertType);
            }
            jSONObject.put("frequency_quantity", this.alertInterval);
            if (this.alertIntervalUnit != null) {
                jSONObject.put("frequency_unit", this.alertIntervalUnit);
            }
            if (this.alertStartDate != null) {
                jSONObject.put(FirebaseAnalytics.Param.START_DATE, this.alertStartDate);
            }
        } catch (Exception e) {
            Trace.e("_TAG", " ReminderInfo toJsonObject()" + e.toString());
        }
        return jSONObject;
    }

    public void fromJsonObject(JSONObject jSONObject) {
        try {
            this.isEnabled = jSONObject.optInt("push_enabled", 0);
            this.alertType = jSONObject.optString("alert_type", "notification");
            this.alertInterval = jSONObject.optInt("frequency_quantity", 30);
            this.alertIntervalUnit = jSONObject.optString("frequency_unit", "days");
            this.alertStartDate = jSONObject.optString(FirebaseAnalytics.Param.START_DATE);
        } catch (Exception e) {
            Trace.e("_TAG", "ReminderInfo.java: " + e.toString());
        }
    }
}
