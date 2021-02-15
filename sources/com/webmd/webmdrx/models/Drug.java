package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class Drug implements Parcelable, Cloneable {
    public static final Parcelable.Creator<Drug> CREATOR = new Parcelable.Creator<Drug>() {
        public Drug createFromParcel(Parcel parcel) {
            return new Drug(parcel);
        }

        public Drug[] newArray(int i) {
            return new Drug[i];
        }
    };
    private String NDC;
    private String fdbId;
    private boolean mAutoPackageSelect;
    private String mDeaClassCode;
    private String mForm;
    private String mGPI;
    private boolean mIsGeneric;
    private List<String> mNDCList = new ArrayList();
    private String mName;
    private String mPackageDescription;
    private String mPackageSize;
    private String mPackageUnit;
    private String mQuantity;
    private List<Quantity> mQuantityList = new ArrayList();
    private String mStrength;
    private String monoId;
    private String otherName;
    private List<PackageSize> packageSizeList = new ArrayList();
    private String title;

    public int describeContents() {
        return 0;
    }

    public String getDeaClassCode() {
        return this.mDeaClassCode;
    }

    public void setDeaClassCode(String str) {
        this.mDeaClassCode = str;
    }

    public String getOtherName() {
        return this.otherName;
    }

    public void setOtherName(String str) {
        this.otherName = str;
    }

    public String getDrugId() {
        return this.NDC;
    }

    public void setDrugId(String str) {
        this.NDC = str;
    }

    public String getValue() {
        return this.mName;
    }

    public void setValue(String str) {
        this.mName = str;
    }

    public String getForm() {
        return this.mForm;
    }

    public void setForm(String str) {
        this.mForm = str;
    }

    public String getStrength() {
        return this.mStrength;
    }

    public void setStrength(String str) {
        this.mStrength = str;
    }

    public void setIsGeneric(boolean z) {
        this.mIsGeneric = z;
    }

    public boolean isGeneric() {
        return this.mIsGeneric;
    }

    public void setIsAutoPackageSelect(boolean z) {
        this.mAutoPackageSelect = z;
    }

    public boolean isAutoPackageSelect() {
        return this.mAutoPackageSelect;
    }

    public String getGPI() {
        return this.mGPI;
    }

    public void setGPI(String str) {
        this.mGPI = str;
    }

    public String getPackageDescription() {
        return this.mPackageDescription;
    }

    public String getmQuantity() {
        return this.mQuantity;
    }

    public void setmQuantity(String str) {
        this.mQuantity = str;
    }

    public void setPackageDescription(String str) {
        this.mPackageDescription = str;
    }

    public String getPackageUnit() {
        return this.mPackageUnit;
    }

    public void setPackageUnit(String str) {
        this.mPackageUnit = str;
    }

    public String getPackageSize() {
        return this.mPackageSize;
    }

    public void setPackageSize(String str) {
        this.mPackageSize = str;
    }

    public List<Quantity> getQuantityList() {
        return this.mQuantityList;
    }

    public void setQuantityList(List<Quantity> list) {
        this.mQuantityList = list;
    }

    public void clearQuantityList() {
        this.mQuantityList.clear();
    }

    public void addQuantiyToList(Quantity quantity) {
        if (quantity != null) {
            if (this.mQuantityList == null) {
                this.mQuantityList = new ArrayList();
            }
            this.mQuantityList.add(quantity);
        }
    }

    public List<String> getNDCList() {
        return this.mNDCList;
    }

    public void addToNDCList(String str) {
        if (str != null) {
            if (this.mNDCList == null) {
                this.mNDCList = new ArrayList();
            }
            this.mNDCList.add(str);
        }
    }

    public void setPackageSizeList(List<PackageSize> list) {
        if (list != null) {
            if (this.packageSizeList == null) {
                this.packageSizeList = new ArrayList();
            }
            this.packageSizeList.clear();
            for (PackageSize add : list) {
                this.packageSizeList.add(add);
            }
        }
    }

    public List<PackageSize> getPackageSizeList() {
        return this.packageSizeList;
    }

    public int getSelectedPackageSize(String str) {
        if (this.packageSizeList == null) {
            return -1;
        }
        for (int i = 0; i < this.packageSizeList.size(); i++) {
            if (this.packageSizeList.get(i).getDisplay().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    public String getFdbId() {
        return this.fdbId;
    }

    public void setFdbId(String str) {
        this.fdbId = str;
    }

    public String getMonoId() {
        return this.monoId;
    }

    public void setMonoId(String str) {
        this.monoId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public Drug() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.NDC);
        parcel.writeString(this.fdbId);
        parcel.writeString(this.monoId);
        parcel.writeString(this.title);
        parcel.writeString(this.mName);
        parcel.writeString(this.mForm);
        parcel.writeString(this.mStrength);
        parcel.writeByte(this.mIsGeneric ? (byte) 1 : 0);
        parcel.writeByte(this.mAutoPackageSelect ? (byte) 1 : 0);
        parcel.writeString(this.mGPI);
        parcel.writeString(this.mPackageDescription);
        parcel.writeString(this.mPackageUnit);
        parcel.writeString(this.mPackageSize);
        parcel.writeString(this.mQuantity);
        parcel.writeTypedList(this.mQuantityList);
        parcel.writeStringList(this.mNDCList);
        parcel.writeTypedList(this.packageSizeList);
        parcel.writeString(this.otherName);
        parcel.writeString(this.mDeaClassCode);
    }

    protected Drug(Parcel parcel) {
        this.NDC = parcel.readString();
        this.fdbId = parcel.readString();
        this.monoId = parcel.readString();
        this.title = parcel.readString();
        this.mName = parcel.readString();
        this.mForm = parcel.readString();
        this.mStrength = parcel.readString();
        boolean z = true;
        this.mIsGeneric = parcel.readByte() != 0;
        this.mAutoPackageSelect = parcel.readByte() == 0 ? false : z;
        this.mGPI = parcel.readString();
        this.mPackageDescription = parcel.readString();
        this.mPackageUnit = parcel.readString();
        this.mPackageSize = parcel.readString();
        this.mQuantity = parcel.readString();
        this.mQuantityList = parcel.createTypedArrayList(Quantity.CREATOR);
        this.mNDCList = parcel.createStringArrayList();
        this.packageSizeList = parcel.createTypedArrayList(PackageSize.CREATOR);
        this.otherName = parcel.readString();
        this.mDeaClassCode = parcel.readString();
    }

    public Drug clone() throws CloneNotSupportedException {
        return (Drug) super.clone();
    }

    public void setFields(RxProfileDrugDetailSection rxProfileDrugDetailSection) {
        this.fdbId = rxProfileDrugDetailSection.getFdbId();
        this.NDC = rxProfileDrugDetailSection.getDosage().getNdc();
        this.monoId = rxProfileDrugDetailSection.getMonoId();
        this.mName = rxProfileDrugDetailSection.getDosage().getDrugName();
        this.mForm = rxProfileDrugDetailSection.getDosage().getForm();
        this.mStrength = rxProfileDrugDetailSection.getDosage().getStrength();
        if (rxProfileDrugDetailSection.getDosage().getIsGeneric() == 1) {
            this.mIsGeneric = true;
        } else {
            this.mIsGeneric = false;
        }
        this.mGPI = rxProfileDrugDetailSection.getDosage().getGpi();
        this.mQuantity = String.valueOf(rxProfileDrugDetailSection.getDosage().getQuantity());
    }
}
