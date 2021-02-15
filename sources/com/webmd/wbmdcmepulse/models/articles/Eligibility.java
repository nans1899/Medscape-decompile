package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Eligibility implements Parcelable {
    public static final Parcelable.Creator<Eligibility> CREATOR = new Parcelable.Creator<Eligibility>() {
        public Eligibility createFromParcel(Parcel parcel) {
            return new Eligibility(parcel);
        }

        public Eligibility[] newArray(int i) {
            return new Eligibility[i];
        }
    };
    public String AccreditationStatement;
    public List<Accreditor> Accreditors;
    public String Acpe;
    public String CmeUs;
    public String CreditAllocation;
    public String CreditStatement;
    public float MaxCredits;
    public String Name;
    public List<Provider> Providers;
    public boolean isEvalRequired;
    public double passingScore;
    public int type;

    public int describeContents() {
        return 0;
    }

    public Eligibility() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.MaxCredits);
        parcel.writeTypedList(this.Providers);
        parcel.writeTypedList(this.Accreditors);
        parcel.writeString(this.CmeUs);
        parcel.writeString(this.CreditAllocation);
        parcel.writeString(this.Acpe);
        parcel.writeString(this.CreditStatement);
        parcel.writeString(this.AccreditationStatement);
        parcel.writeString(this.Name);
        parcel.writeByte(this.isEvalRequired ? (byte) 1 : 0);
        parcel.writeInt(this.type);
        parcel.writeDouble(this.passingScore);
    }

    protected Eligibility(Parcel parcel) {
        this.MaxCredits = parcel.readFloat();
        this.Providers = parcel.createTypedArrayList(Provider.CREATOR);
        this.Accreditors = parcel.createTypedArrayList(Accreditor.CREATOR);
        this.CmeUs = parcel.readString();
        this.CreditAllocation = parcel.readString();
        this.Acpe = parcel.readString();
        this.CreditStatement = parcel.readString();
        this.AccreditationStatement = parcel.readString();
        this.Name = parcel.readString();
        this.isEvalRequired = parcel.readByte() != 0;
        this.type = parcel.readInt();
        this.passingScore = parcel.readDouble();
    }
}
