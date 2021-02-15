package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.wbmd.wbmdcommons.logging.Trace;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONObject;

public class SavedDrugData implements Comparable<SavedDrugData>, Parcelable, Cloneable {
    public static final Parcelable.Creator<SavedDrugData> CREATOR = new Parcelable.Creator<SavedDrugData>() {
        public SavedDrugData createFromParcel(Parcel parcel) {
            return new SavedDrugData(parcel);
        }

        public SavedDrugData[] newArray(int i) {
            return new SavedDrugData[i];
        }
    };
    String _id;
    String createDate;
    int deleted;
    RxData drugDetails;
    String item_id;
    String lowestPriceInArea;
    String secondaryID;
    String summary;
    String thumbnailURLString;
    String title;
    String type;
    String updateDate;

    public int describeContents() {
        return 0;
    }

    public SavedDrugData() {
        this.type = "";
        this.deleted = 0;
        this._id = "";
        this.item_id = "";
        this.title = "";
        this.summary = "";
        this.thumbnailURLString = "";
        this.secondaryID = "";
        this.createDate = "";
        this.updateDate = "";
        this.drugDetails = null;
        this.lowestPriceInArea = "";
    }

    public SavedDrugData(SavedDrugData savedDrugData) {
        if (savedDrugData != null) {
            this.type = savedDrugData.getType();
            this.deleted = savedDrugData.getDeleted();
            this._id = savedDrugData.get_id();
            this.item_id = savedDrugData.getItem_id();
            this.title = savedDrugData.getTitle();
            this.summary = savedDrugData.getSummary();
            this.thumbnailURLString = savedDrugData.getThumbnailURLString();
            this.secondaryID = savedDrugData.getSecondaryID();
            this.createDate = savedDrugData.getCreateDate();
            this.updateDate = savedDrugData.getUpdateDate();
            if (savedDrugData.getDrugDetail() != null) {
                this.drugDetails = new RxData(savedDrugData.getDrugDetail());
            }
        }
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.type != null) {
                jSONObject.put("type", this.type);
            } else {
                jSONObject.put("type", "");
            }
            jSONObject.put("deleted", this.deleted);
            if (this._id != null) {
                jSONObject.put("_id", this._id);
            } else {
                jSONObject.put("_id", "");
            }
            if (this.item_id != null) {
                jSONObject.put(FirebaseAnalytics.Param.ITEM_ID, this.item_id);
            } else {
                jSONObject.put(FirebaseAnalytics.Param.ITEM_ID, "");
            }
            if (this.title != null) {
                jSONObject.put("title", this.title);
            } else {
                jSONObject.put("title", "");
            }
            if (this.summary != null) {
                jSONObject.put("summary", this.summary);
            } else {
                jSONObject.put("summary", "");
            }
            if (this.thumbnailURLString != null) {
                jSONObject.put("thumbnailURLString", this.thumbnailURLString);
            } else {
                jSONObject.put("thumbnailURLString", "");
            }
            if (this.secondaryID != null) {
                jSONObject.put("secondaryID", this.secondaryID);
            } else {
                jSONObject.put("secondaryID", "");
            }
            if (this.drugDetails != null) {
                jSONObject.put("RxData", this.drugDetails.toJsonObject());
            } else {
                jSONObject.put("RxData", JSONObject.NULL);
            }
            return jSONObject;
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    public boolean isValidToSave() {
        String str = this.type;
        boolean z = false;
        boolean z2 = str != null && !str.isEmpty();
        String str2 = this.item_id;
        if (str2 == null || str2.isEmpty()) {
            z2 = false;
        }
        String str3 = this.title;
        if (str3 == null || str3.isEmpty()) {
            z2 = false;
        }
        String str4 = this.secondaryID;
        if (str4 != null && !str4.isEmpty()) {
            z = z2;
        }
        Trace.e("_TAG", "is valid to save: " + this.title + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + String.valueOf(z));
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0007, code lost:
        r2 = r5.item_id;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equal(com.webmd.webmdrx.models.SavedDrugData r5) {
        /*
            r4 = this;
            r0 = 0
            if (r5 == 0) goto L_0x0034
            java.lang.String r1 = r4.item_id
            if (r1 == 0) goto L_0x0014
            java.lang.String r2 = r5.item_id
            if (r2 == 0) goto L_0x0014
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r1 = 1
            goto L_0x0015
        L_0x0014:
            r1 = 0
        L_0x0015:
            java.lang.String r2 = r4.secondaryID
            if (r2 == 0) goto L_0x0023
            java.lang.String r3 = r5.secondaryID
            if (r3 == 0) goto L_0x0023
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            java.lang.String r2 = r4.type
            if (r2 == 0) goto L_0x0034
            java.lang.String r5 = r5.type
            if (r5 == 0) goto L_0x0034
            boolean r5 = r2.equals(r5)
            if (r5 != 0) goto L_0x0033
            goto L_0x0034
        L_0x0033:
            r0 = r1
        L_0x0034:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.models.SavedDrugData.equal(com.webmd.webmdrx.models.SavedDrugData):boolean");
    }

    public boolean hasSameDosage(SavedDrugData savedDrugData) {
        if (!(savedDrugData == null || this.drugDetails == null || savedDrugData.getDrugDetail() == null || this.drugDetails.getDeleted() == 1 || savedDrugData.drugDetails.getDeleted() == 1 || savedDrugData.drugDetails.dosages.size() <= 0)) {
            SavedRxDosage savedRxDosage = savedDrugData.drugDetails.dosages.get(0);
            List<SavedRxDosage> list = this.drugDetails.dosages;
            if (list.size() > 0) {
                for (SavedRxDosage equal : list) {
                    if (equal.equal(savedRxDosage)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int compareTo(SavedDrugData savedDrugData) {
        new Date();
        new Date();
        if (!(this.updateDate == null || savedDrugData.updateDate == null)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                return simpleDateFormat.parse(this.updateDate).compareTo(simpleDateFormat.parse(savedDrugData.updateDate));
            } catch (ParseException unused) {
            }
        }
        return 0;
    }

    public String getStrippedFdbId() {
        String str = this.item_id;
        if (str == null || str.isEmpty()) {
            return "";
        }
        if (!this.item_id.contains("FDB_")) {
            return this.item_id;
        }
        String str2 = this.item_id;
        return str2.substring(str2.indexOf("FDB_") + 4);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getDeleted() {
        return this.deleted;
    }

    public void setDeleted(int i) {
        this.deleted = i;
    }

    public String getItem_id() {
        return this.item_id;
    }

    public void setItem_id(String str) {
        this.item_id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String str) {
        this.summary = str;
    }

    public String getThumbnailURLString() {
        return this.thumbnailURLString;
    }

    public void setThumbnailURLString(String str) {
        this.thumbnailURLString = str;
    }

    public String getSecondaryID() {
        return this.secondaryID;
    }

    public void setSecondaryID(String str) {
        this.secondaryID = str;
    }

    public RxData getDrugDetail() {
        return this.drugDetails;
    }

    public void setDrugDetail(RxData rxData) {
        this.drugDetails = rxData;
    }

    public String get_id() {
        return this._id;
    }

    public void set_id(String str) {
        this._id = str;
    }

    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(String str) {
        this.createDate = str;
    }

    public String getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(String str) {
        this.updateDate = str;
    }

    public String getLowestPriceInArea() {
        return this.lowestPriceInArea;
    }

    public void setLowestPriceInArea(String str) {
        this.lowestPriceInArea = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeInt(this.deleted);
        parcel.writeString(this._id);
        parcel.writeString(this.item_id);
        parcel.writeString(this.title);
        parcel.writeString(this.summary);
        parcel.writeString(this.thumbnailURLString);
        parcel.writeString(this.secondaryID);
        parcel.writeString(this.createDate);
        parcel.writeString(this.updateDate);
        parcel.writeParcelable(this.drugDetails, i);
        parcel.writeString(this.lowestPriceInArea);
    }

    protected SavedDrugData(Parcel parcel) {
        this.type = parcel.readString();
        this.deleted = parcel.readInt();
        this._id = parcel.readString();
        this.item_id = parcel.readString();
        this.title = parcel.readString();
        this.summary = parcel.readString();
        this.thumbnailURLString = parcel.readString();
        this.secondaryID = parcel.readString();
        this.createDate = parcel.readString();
        this.updateDate = parcel.readString();
        this.drugDetails = (RxData) parcel.readParcelable(RxData.class.getClassLoader());
        this.lowestPriceInArea = parcel.readString();
    }

    public SavedDrugData clone() {
        try {
            return (SavedDrugData) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
