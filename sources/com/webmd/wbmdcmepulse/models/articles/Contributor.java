package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Contributor implements Parcelable {
    public static final Parcelable.Creator<Contributor> CREATOR = new Parcelable.Creator<Contributor>() {
        public Contributor createFromParcel(Parcel parcel) {
            return new Contributor(parcel);
        }

        public Contributor[] newArray(int i) {
            return new Contributor[i];
        }
    };
    public static String ROLE_AUTHOR = "Author(s)";
    public static String ROLE_AUTHOR_CME = "CME Author(s)";
    public static String ROLE_CME_REVIEW_NURSE_PLANNER = "CME Reviewer/Nurse Planner";
    public List<ContributorComment> comments;
    public String contribType;
    public String image;
    public String name;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.contribType);
        parcel.writeTypedList(this.comments);
        parcel.writeString(this.image);
    }

    public Contributor() {
    }

    protected Contributor(Parcel parcel) {
        this.name = parcel.readString();
        this.contribType = parcel.readString();
        this.comments = parcel.createTypedArrayList(ContributorComment.CREATOR);
        this.image = parcel.readString();
    }
}
