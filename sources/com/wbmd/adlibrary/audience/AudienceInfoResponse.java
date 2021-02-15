package com.wbmd.adlibrary.audience;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class AudienceInfoResponse implements Parcelable {
    public static final Parcelable.Creator<AudienceInfoResponse> CREATOR = new Parcelable.Creator<AudienceInfoResponse>() {
        public AudienceInfoResponse createFromParcel(Parcel parcel) {
            return new AudienceInfoResponse(parcel);
        }

        public AudienceInfoResponse[] newArray(int i) {
            return new AudienceInfoResponse[i];
        }
    };
    @SerializedName("Profile")
    private Profile mProfile;

    public int describeContents() {
        return 0;
    }

    public Profile getProfile() {
        return this.mProfile;
    }

    public void setProfile(Profile profile) {
        this.mProfile = profile;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mProfile, i);
    }

    public AudienceInfoResponse() {
    }

    protected AudienceInfoResponse(Parcel parcel) {
        this.mProfile = (Profile) parcel.readParcelable(Profile.class.getClassLoader());
    }
}
