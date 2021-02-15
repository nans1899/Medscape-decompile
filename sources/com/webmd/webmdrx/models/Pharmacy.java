package com.webmd.webmdrx.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.places.model.PlaceFields;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.settings.Settings;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

public class Pharmacy implements Parcelable, Comparable<Pharmacy> {
    public static final Parcelable.Creator<Pharmacy> CREATOR = new Parcelable.Creator<Pharmacy>() {
        public Pharmacy createFromParcel(Parcel parcel) {
            return new Pharmacy(parcel);
        }

        public Pharmacy[] newArray(int i) {
            return new Pharmacy[i];
        }
    };
    private String Address1;
    private String Address2;
    private ArrayList<Address> Addresses;
    private String City;
    private double Distance;
    private boolean HasDeliveryService;
    private boolean HasDriveUp;
    private String Hours;
    private String Image;
    private boolean IsOpen24Hours;
    private double Latitude;
    private double Longitude;
    private String Name;
    private int OtherLocationCount;
    private String PharmID;
    private String PharmacyGroup;
    private double PharmacyGroupMaxPrice;
    private double PharmacyGroupMinPrice;
    private PharmacyHours[] PharmacyHours;
    private String Phone;
    private String PostalCode;
    private String RxPharmID;
    private String State;
    private String createDate;
    private Bitmap imageBitmap;
    private String rewardsID;
    private String updateDate;

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0056, code lost:
        if (r5.updateDate.isEmpty() != false) goto L_0x0058;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject toJsonObject(boolean r6) {
        /*
            r5 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "type"
            java.lang.String r2 = "rx_pharmacy"
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r1 = "deleted"
            if (r6 == 0) goto L_0x0015
            r6 = 1
            r0.put(r1, r6)     // Catch:{ JSONException -> 0x00c2 }
            goto L_0x0019
        L_0x0015:
            r6 = 0
            r0.put(r1, r6)     // Catch:{ JSONException -> 0x00c2 }
        L_0x0019:
            java.lang.String r6 = r5.RxPharmID     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r1 = "item_id"
            java.lang.String r2 = ""
            if (r6 == 0) goto L_0x0027
            java.lang.String r6 = r5.RxPharmID     // Catch:{ JSONException -> 0x00c2 }
            r0.put(r1, r6)     // Catch:{ JSONException -> 0x00c2 }
            goto L_0x002a
        L_0x0027:
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00c2 }
        L_0x002a:
            java.lang.String r6 = r5.Name     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r1 = "title"
            if (r6 == 0) goto L_0x0036
            java.lang.String r6 = r5.Name     // Catch:{ JSONException -> 0x00c2 }
            r0.put(r1, r6)     // Catch:{ JSONException -> 0x00c2 }
            goto L_0x0039
        L_0x0036:
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00c2 }
        L_0x0039:
            java.lang.String r6 = r5.Image     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r1 = "thumbnailURLString"
            if (r6 == 0) goto L_0x0045
            java.lang.String r6 = r5.Image     // Catch:{ JSONException -> 0x00c2 }
            r0.put(r1, r6)     // Catch:{ JSONException -> 0x00c2 }
            goto L_0x0048
        L_0x0045:
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00c2 }
        L_0x0048:
            java.lang.String r6 = r5.updateDate     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r1 = "GMT+0"
            java.lang.String r3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            if (r6 == 0) goto L_0x0058
            java.lang.String r6 = r5.updateDate     // Catch:{ JSONException -> 0x00c2 }
            boolean r6 = r6.isEmpty()     // Catch:{ JSONException -> 0x00c2 }
            if (r6 == 0) goto L_0x0076
        L_0x0058:
            java.text.SimpleDateFormat r6 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x0070 }
            r6.<init>(r3)     // Catch:{ Exception -> 0x0070 }
            java.util.TimeZone r4 = java.util.TimeZone.getTimeZone(r1)     // Catch:{ Exception -> 0x0070 }
            r6.setTimeZone(r4)     // Catch:{ Exception -> 0x0070 }
            java.util.Date r4 = new java.util.Date     // Catch:{ Exception -> 0x0070 }
            r4.<init>()     // Catch:{ Exception -> 0x0070 }
            java.lang.String r6 = r6.format(r4)     // Catch:{ Exception -> 0x0070 }
            r5.updateDate = r6     // Catch:{ Exception -> 0x0070 }
            goto L_0x0076
        L_0x0070:
            r6 = move-exception
            r5.updateDate = r2     // Catch:{ JSONException -> 0x00c2 }
            r6.printStackTrace()     // Catch:{ JSONException -> 0x00c2 }
        L_0x0076:
            java.lang.String r6 = "UpdateDate"
            java.lang.String r4 = r5.updateDate     // Catch:{ JSONException -> 0x00c2 }
            r0.put(r6, r4)     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r6 = r5.createDate     // Catch:{ JSONException -> 0x00c2 }
            if (r6 == 0) goto L_0x0089
            java.lang.String r6 = r5.createDate     // Catch:{ JSONException -> 0x00c2 }
            boolean r6 = r6.isEmpty()     // Catch:{ JSONException -> 0x00c2 }
            if (r6 == 0) goto L_0x00a7
        L_0x0089:
            java.text.SimpleDateFormat r6 = new java.text.SimpleDateFormat     // Catch:{ Exception -> 0x00a1 }
            r6.<init>(r3)     // Catch:{ Exception -> 0x00a1 }
            java.util.TimeZone r1 = java.util.TimeZone.getTimeZone(r1)     // Catch:{ Exception -> 0x00a1 }
            r6.setTimeZone(r1)     // Catch:{ Exception -> 0x00a1 }
            java.util.Date r1 = new java.util.Date     // Catch:{ Exception -> 0x00a1 }
            r1.<init>()     // Catch:{ Exception -> 0x00a1 }
            java.lang.String r6 = r6.format(r1)     // Catch:{ Exception -> 0x00a1 }
            r5.createDate = r6     // Catch:{ Exception -> 0x00a1 }
            goto L_0x00a7
        L_0x00a1:
            r6 = move-exception
            r5.createDate = r2     // Catch:{ JSONException -> 0x00c2 }
            r6.printStackTrace()     // Catch:{ JSONException -> 0x00c2 }
        L_0x00a7:
            java.lang.String r6 = "CreatedDate"
            java.lang.String r1 = r5.createDate     // Catch:{ JSONException -> 0x00c2 }
            r0.put(r6, r1)     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r6 = "summary"
            r0.put(r6, r2)     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r6 = "secondaryID"
            r0.put(r6, r2)     // Catch:{ JSONException -> 0x00c2 }
            java.lang.String r6 = "RxPharmacyData"
            org.json.JSONObject r1 = r5.getRxPharmacyDataJson()     // Catch:{ JSONException -> 0x00c2 }
            r0.put(r6, r1)     // Catch:{ JSONException -> 0x00c2 }
            return r0
        L_0x00c2:
            org.json.JSONObject r6 = new org.json.JSONObject
            r6.<init>()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.models.Pharmacy.toJsonObject(boolean):org.json.JSONObject");
    }

    public int compareTo(Pharmacy pharmacy) {
        new Date();
        new Date();
        if (!(pharmacy == null || this.createDate == null || pharmacy.createDate == null)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                return simpleDateFormat.parse(this.createDate).compareTo(simpleDateFormat.parse(pharmacy.createDate));
            } catch (ParseException unused) {
            }
        }
        return 0;
    }

    public JSONObject getAddressJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.Address1 != null) {
                jSONObject.put("street", this.Address1);
            } else {
                jSONObject.put("street", "");
            }
            if (this.City != null) {
                jSONObject.put(Settings.CITY, this.City);
            } else {
                jSONObject.put(Settings.CITY, "");
            }
            if (this.State != null) {
                jSONObject.put("state", this.State);
            } else {
                jSONObject.put("state", "");
            }
            if (this.PostalCode != null) {
                jSONObject.put(Settings.ZIP, this.PostalCode);
            } else {
                jSONObject.put(Settings.ZIP, "");
            }
            return jSONObject;
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    public JSONObject getLocationJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Settings.LATITUDE_KEY, this.Latitude);
            jSONObject.put(Settings.LONGITUDE_KEY, this.Longitude);
            return jSONObject;
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    public JSONObject getRxPharmacyDataJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("address", getAddressJsonObject());
            jSONObject.put("location", getLocationJsonObject());
            if (this.Phone != null) {
                jSONObject.put(PlaceFields.PHONE, this.Phone);
            } else {
                jSONObject.put(PlaceFields.PHONE, "");
            }
            jSONObject.put("ncpdp", "");
            jSONObject.put("npi", "");
            if (this.RxPharmID != null) {
                jSONObject.put("RxCutPharmacyID", this.RxPharmID);
            } else {
                jSONObject.put("RxCutPharmacyID", "");
            }
            if (this.rewardsID != null) {
                jSONObject.put("rewardsID", this.rewardsID);
            } else {
                jSONObject.put("rewardsID", "");
            }
            return jSONObject;
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    public boolean isValidToSave() {
        String str = this.RxPharmID;
        boolean z = str != null && !str.isEmpty();
        String str2 = this.Name;
        if (str2 == null || str2.isEmpty()) {
            z = false;
        }
        String str3 = this.Address1;
        if (str3 == null || str3.isEmpty()) {
            z = false;
        }
        String str4 = this.City;
        if (str4 == null || str4.isEmpty()) {
            z = false;
        }
        String str5 = this.State;
        if (str5 == null || str5.isEmpty()) {
            z = false;
        }
        String str6 = this.PostalCode;
        if (str6 == null || str6.isEmpty()) {
            return false;
        }
        return z;
    }

    public ArrayList<Address> getAddresses() {
        return this.Addresses;
    }

    public String getRxPharmID() {
        return this.RxPharmID;
    }

    public void setRxPharmID(String str) {
        this.RxPharmID = str;
    }

    public Bitmap getImageBitmap() {
        return this.imageBitmap;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.imageBitmap = bitmap;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String str) {
        this.Name = str;
    }

    public String getPharmacyGroup() {
        return this.PharmacyGroup;
    }

    public void setPharmacyGroup(String str) {
        this.PharmacyGroup = str;
    }

    public int getOtherLocationCount() {
        return this.OtherLocationCount;
    }

    public void setOtherLocationCount(int i) {
        this.OtherLocationCount = i;
    }

    public double getPharmacyGroupMinPrice() {
        return this.PharmacyGroupMinPrice;
    }

    public void setPharmacyGroupMinPrice(double d) {
        this.PharmacyGroupMinPrice = d;
    }

    public double getPharmacyGroupMaxPrice() {
        return this.PharmacyGroupMaxPrice;
    }

    public void setPharmacyGroupMaxPrice(double d) {
        this.PharmacyGroupMaxPrice = d;
    }

    public String getImage() {
        return this.Image;
    }

    public void setImage(String str) {
        this.Image = str;
    }

    public double getDistance() {
        return this.Distance;
    }

    public void setDistance(double d) {
        this.Distance = d;
    }

    public Double getLatitude() {
        return Double.valueOf(this.Latitude);
    }

    public void setLatitude(Double d) {
        this.Latitude = d.doubleValue();
    }

    public Double getLongitude() {
        return Double.valueOf(this.Longitude);
    }

    public void setLongitude(Double d) {
        this.Longitude = d.doubleValue();
    }

    public String getAddress1() {
        return this.Address1;
    }

    public void setAddress1(String str) {
        this.Address1 = str;
    }

    public String getAddress2() {
        return this.Address2;
    }

    public void setAddress2(String str) {
        this.Address2 = str;
    }

    public String getCity() {
        return this.City;
    }

    public void setCity(String str) {
        this.City = str;
    }

    public String getHours() {
        return this.Hours;
    }

    public void setHours(String str) {
        this.Hours = str;
    }

    public PharmacyHours[] getPharmacyHours() {
        return this.PharmacyHours;
    }

    public void setPharmacyHours(PharmacyHours[] pharmacyHoursArr) {
        this.PharmacyHours = pharmacyHoursArr;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String str) {
        this.Phone = str;
    }

    public String getPostalCode() {
        return this.PostalCode;
    }

    public void setPostalCode(String str) {
        this.PostalCode = str;
    }

    public String getState() {
        return this.State;
    }

    public void setState(String str) {
        this.State = str;
    }

    public boolean isOpen24Hours() {
        return this.IsOpen24Hours;
    }

    public void setOpen24Hours(boolean z) {
        this.IsOpen24Hours = z;
    }

    public boolean isHasDriveUp() {
        return this.HasDriveUp;
    }

    public void setHasDriveUp(boolean z) {
        this.HasDriveUp = z;
    }

    public boolean isHasDeliveryService() {
        return this.HasDeliveryService;
    }

    public void setHasDeliveryService(boolean z) {
        this.HasDeliveryService = z;
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

    public String getRewardsID() {
        return this.rewardsID;
    }

    public void setRewardsID(String str) {
        this.rewardsID = str;
    }

    public String getFullAddress() {
        String str;
        String str2;
        String str3 = this.Address1;
        if (str3 == null || str3.isEmpty() || (str = this.City) == null || str.isEmpty() || (str2 = this.State) == null || str2.isEmpty()) {
            return "";
        }
        String str4 = "" + this.Address1 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.City + ", " + this.State;
        String str5 = this.PostalCode;
        if (str5 == null || str5.isEmpty()) {
            return str4;
        }
        return str4 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.PostalCode;
    }

    public String getStreetAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(formatCapital(this.Address1));
        String str = this.Address2;
        if (str != null && !str.isEmpty()) {
            sb.append(10);
            sb.append(formatCapital(this.Address2));
        }
        return sb.toString();
    }

    public String getCityStateZip() {
        return formatCapital(this.City) + ", " + this.State + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.PostalCode.substring(0, 5);
    }

    private String formatCapital(String str) {
        StringBuilder sb = new StringBuilder(str);
        boolean z = true;
        for (int i = 0; i < sb.length(); i++) {
            char charAt = sb.charAt(i);
            if (!z) {
                sb.setCharAt(i, Character.toLowerCase(charAt));
            }
            z = Character.isWhitespace(charAt);
        }
        return sb.toString();
    }

    public Pharmacy() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Name);
        parcel.writeString(this.PharmacyGroup);
        parcel.writeInt(this.OtherLocationCount);
        parcel.writeDouble(this.PharmacyGroupMinPrice);
        parcel.writeDouble(this.PharmacyGroupMaxPrice);
        parcel.writeString(this.Image);
        parcel.writeDouble(this.Distance);
        parcel.writeDouble(this.Latitude);
        parcel.writeDouble(this.Longitude);
        parcel.writeString(this.Address1);
        parcel.writeString(this.Address2);
        parcel.writeString(this.City);
        parcel.writeString(this.Hours);
        parcel.writeTypedArray(this.PharmacyHours, i);
        parcel.writeString(this.Phone);
        parcel.writeString(this.PostalCode);
        parcel.writeString(this.State);
        parcel.writeByte(this.IsOpen24Hours ? (byte) 1 : 0);
        parcel.writeByte(this.HasDriveUp ? (byte) 1 : 0);
        parcel.writeByte(this.HasDeliveryService ? (byte) 1 : 0);
        parcel.writeString(this.RxPharmID);
        parcel.writeString(this.createDate);
        parcel.writeString(this.updateDate);
        parcel.writeString(this.rewardsID);
        parcel.writeString(this.PharmID);
        parcel.writeTypedList(this.Addresses);
        parcel.writeParcelable(this.imageBitmap, i);
    }

    protected Pharmacy(Parcel parcel) {
        this.Name = parcel.readString();
        this.PharmacyGroup = parcel.readString();
        this.OtherLocationCount = parcel.readInt();
        this.PharmacyGroupMinPrice = parcel.readDouble();
        this.PharmacyGroupMaxPrice = parcel.readDouble();
        this.Image = parcel.readString();
        this.Distance = parcel.readDouble();
        this.Latitude = parcel.readDouble();
        this.Longitude = parcel.readDouble();
        this.Address1 = parcel.readString();
        this.Address2 = parcel.readString();
        this.City = parcel.readString();
        this.Hours = parcel.readString();
        this.PharmacyHours = (PharmacyHours[]) parcel.createTypedArray(PharmacyHours.CREATOR);
        this.Phone = parcel.readString();
        this.PostalCode = parcel.readString();
        this.State = parcel.readString();
        boolean z = true;
        this.IsOpen24Hours = parcel.readByte() != 0;
        this.HasDriveUp = parcel.readByte() != 0;
        this.HasDeliveryService = parcel.readByte() == 0 ? false : z;
        this.RxPharmID = parcel.readString();
        this.createDate = parcel.readString();
        this.updateDate = parcel.readString();
        this.rewardsID = parcel.readString();
        this.PharmID = parcel.readString();
        this.Addresses = parcel.createTypedArrayList(Address.CREATOR);
        this.imageBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
    }

    public String getPharmID() {
        return this.PharmID;
    }
}
