package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONObject;

public class SavedRxDosage implements Parcelable {
    public static final Parcelable.Creator<SavedRxDosage> CREATOR = new Parcelable.Creator<SavedRxDosage>() {
        public SavedRxDosage createFromParcel(Parcel parcel) {
            return new SavedRxDosage(parcel);
        }

        public SavedRxDosage[] newArray(int i) {
            return new SavedRxDosage[i];
        }
    };
    String UpdateDate;
    String asLowAsText;
    String drugName;
    String form;
    String gpi;
    int isGeneric;
    String ndc;
    String otherName;
    double pkg_size;
    int quantity;
    ReminderInfo reminderInfo;
    String strength;

    public int describeContents() {
        return 0;
    }

    public String getGpi() {
        return this.gpi;
    }

    public void setGpi(String str) {
        this.gpi = str;
    }

    public SavedRxDosage() {
        this.ndc = "";
        this.form = "";
        this.strength = "";
        this.drugName = "";
        this.quantity = 0;
        this.pkg_size = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.otherName = "";
        this.isGeneric = 1;
        this.UpdateDate = "";
        this.reminderInfo = new ReminderInfo();
        this.asLowAsText = "";
    }

    public String getNdc() {
        return this.ndc;
    }

    public void setNdc(String str) {
        this.ndc = str;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String str) {
        this.form = str;
    }

    public String getStrength() {
        return this.strength;
    }

    public void setStrength(String str) {
        this.strength = str;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }

    public double getPkg_size() {
        return this.pkg_size;
    }

    public void setPkg_size(double d) {
        this.pkg_size = d;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public void setDrugName(String str) {
        this.drugName = str;
    }

    public String getOtherName() {
        return this.otherName;
    }

    public void setOtherName(String str) {
        this.otherName = str;
    }

    public int getIsGeneric() {
        return this.isGeneric;
    }

    public void setIsGeneric(int i) {
        this.isGeneric = i;
    }

    public String getUpdateDate() {
        return this.UpdateDate;
    }

    public void setUpdateDate(String str) {
        this.UpdateDate = str;
    }

    public ReminderInfo getReminderInfo() {
        return this.reminderInfo;
    }

    public void setReminderInfo(ReminderInfo reminderInfo2) {
        this.reminderInfo = reminderInfo2;
    }

    public String getAsLowAsText() {
        return this.asLowAsText;
    }

    public void setAsLowAsText(String str) {
        this.asLowAsText = str;
    }

    public JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.gpi != null) {
                jSONObject.put("gpi", this.gpi);
            } else {
                jSONObject.put("gpi", JSONObject.NULL);
            }
            if (this.ndc != null) {
                jSONObject.put(Constants.WBMDDrugResponseKeyDrugNDC, this.ndc);
            } else {
                jSONObject.put(Constants.WBMDDrugResponseKeyDrugNDC, "");
            }
            if (this.form != null) {
                jSONObject.put("form", this.form);
            } else {
                jSONObject.put("form", "");
            }
            if (this.strength != null) {
                jSONObject.put("strength", this.strength);
            } else {
                jSONObject.put("strength", "");
            }
            if (this.drugName != null) {
                jSONObject.put(Constants.WBMDDrugResponseKeyDrugName, this.drugName);
            } else {
                jSONObject.put(Constants.WBMDDrugResponseKeyDrugName, "");
            }
            if (this.otherName != null) {
                jSONObject.put("genericName", this.otherName);
            } else {
                jSONObject.put("genericName", "");
            }
            if (this.isGeneric == 0) {
                jSONObject.put("isGeneric", false);
            } else {
                jSONObject.put("isGeneric", true);
            }
            jSONObject.put(FirebaseAnalytics.Param.QUANTITY, this.quantity);
            if (this.pkg_size == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                jSONObject.put("pkg_size", "");
            } else {
                jSONObject.put("pkg_size", this.pkg_size);
            }
            if (this.UpdateDate == null || this.UpdateDate.isEmpty()) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
                    this.UpdateDate = simpleDateFormat.format(new Date());
                } catch (Exception e) {
                    this.UpdateDate = "";
                    e.printStackTrace();
                }
            }
            jSONObject.put("UpdateDate", this.UpdateDate);
            if (this.reminderInfo != null) {
                jSONObject.put(NotificationCompat.CATEGORY_REMINDER, this.reminderInfo.toJsonObject());
            }
            return jSONObject;
        } catch (Exception unused) {
            return new JSONObject();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0007, code lost:
        r2 = r7.ndc;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equal(com.webmd.webmdrx.models.SavedRxDosage r7) {
        /*
            r6 = this;
            r0 = 0
            if (r7 == 0) goto L_0x0053
            java.lang.String r1 = r6.ndc
            if (r1 == 0) goto L_0x0014
            java.lang.String r2 = r7.ndc
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
            java.lang.String r2 = r6.form
            if (r2 == 0) goto L_0x0023
            java.lang.String r3 = r7.form
            if (r3 == 0) goto L_0x0023
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0024
        L_0x0023:
            r1 = 0
        L_0x0024:
            java.lang.String r2 = r6.strength
            if (r2 == 0) goto L_0x0032
            java.lang.String r3 = r7.strength
            if (r3 == 0) goto L_0x0032
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0033
        L_0x0032:
            r1 = 0
        L_0x0033:
            java.lang.String r2 = r6.drugName
            if (r2 == 0) goto L_0x0041
            java.lang.String r3 = r7.drugName
            if (r3 == 0) goto L_0x0041
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0042
        L_0x0041:
            r1 = 0
        L_0x0042:
            int r2 = r6.quantity
            int r3 = r7.quantity
            if (r2 == r3) goto L_0x0049
            r1 = 0
        L_0x0049:
            double r2 = r6.pkg_size
            double r4 = r7.pkg_size
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0052
            goto L_0x0053
        L_0x0052:
            r0 = r1
        L_0x0053:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.models.SavedRxDosage.equal(com.webmd.webmdrx.models.SavedRxDosage):boolean");
    }

    public void fromJSON(JSONObject jSONObject) {
        try {
            if (jSONObject.optString("gpi") != null) {
                this.gpi = jSONObject.optString("gpi");
            } else {
                this.gpi = "";
            }
            if (jSONObject.optString(Constants.WBMDDrugResponseKeyDrugNDC) != null) {
                this.ndc = jSONObject.optString(Constants.WBMDDrugResponseKeyDrugNDC);
            } else {
                this.ndc = "";
            }
            if (jSONObject.optString("form") != null) {
                this.form = jSONObject.optString("form");
            } else {
                this.form = "";
            }
            if (jSONObject.optString("strength") != null) {
                this.strength = jSONObject.optString("strength");
            } else {
                this.strength = "";
            }
            if (jSONObject.optString(Constants.WBMDDrugResponseKeyDrugName) != null) {
                this.drugName = jSONObject.optString(Constants.WBMDDrugResponseKeyDrugName);
            } else {
                this.drugName = "";
            }
            if (jSONObject.optString("genericName") != null) {
                this.otherName = jSONObject.optString("genericName");
            } else {
                this.otherName = "";
            }
            if (jSONObject.optInt("isGeneric") == 0) {
                this.isGeneric = 0;
            } else {
                this.isGeneric = 1;
            }
            this.quantity = jSONObject.optInt(FirebaseAnalytics.Param.QUANTITY);
            if (jSONObject.optDouble("pkg_size") == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.pkg_size = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            } else {
                this.pkg_size = jSONObject.optDouble("pkg_size");
            }
            if (jSONObject.optString("UpdateDate") == null || jSONObject.optString("UpdateDate").isEmpty()) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0"));
                    this.UpdateDate = simpleDateFormat.format(new Date());
                } catch (Exception e) {
                    this.UpdateDate = "";
                    e.printStackTrace();
                }
            }
            if (jSONObject.optString(NotificationCompat.CATEGORY_REMINDER) != null) {
                this.reminderInfo.fromJsonObject(jSONObject.optJSONObject(NotificationCompat.CATEGORY_REMINDER));
            }
        } catch (Exception unused) {
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.gpi);
        parcel.writeString(this.ndc);
        parcel.writeString(this.form);
        parcel.writeString(this.strength);
        parcel.writeString(this.drugName);
        parcel.writeInt(this.quantity);
        parcel.writeDouble(this.pkg_size);
        parcel.writeString(this.UpdateDate);
        parcel.writeString(this.otherName);
        parcel.writeInt(this.isGeneric);
        parcel.writeParcelable(this.reminderInfo, i);
        parcel.writeString(this.asLowAsText);
    }

    protected SavedRxDosage(Parcel parcel) {
        this.gpi = parcel.readString();
        this.ndc = parcel.readString();
        this.form = parcel.readString();
        this.strength = parcel.readString();
        this.drugName = parcel.readString();
        this.quantity = parcel.readInt();
        this.pkg_size = parcel.readDouble();
        this.UpdateDate = parcel.readString();
        this.otherName = parcel.readString();
        this.isGeneric = parcel.readInt();
        this.reminderInfo = (ReminderInfo) parcel.readParcelable(ReminderInfo.class.getClassLoader());
        this.asLowAsText = parcel.readString();
    }
}
