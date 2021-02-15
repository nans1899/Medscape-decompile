package com.ib.foreceup.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RemoteUpdateMessaging implements Parcelable {
    public static final Parcelable.Creator<RemoteUpdateMessaging> CREATOR = new Parcelable.Creator<RemoteUpdateMessaging>() {
        public RemoteUpdateMessaging createFromParcel(Parcel parcel) {
            return new RemoteUpdateMessaging(parcel);
        }

        public RemoteUpdateMessaging[] newArray(int i) {
            return new RemoteUpdateMessaging[i];
        }
    };
    private String dimissButtonText;
    private boolean forceGoToPlayStore;
    private String message;
    private String nextTargetVersionMax;
    private String skipButtonText;
    private String title;
    private String updateButtonText;

    public int describeContents() {
        return 0;
    }

    public RemoteUpdateMessaging() {
    }

    private RemoteUpdateMessaging(Parcel parcel) {
        this.title = parcel.readString();
        this.message = parcel.readString();
        this.updateButtonText = parcel.readString();
        this.dimissButtonText = parcel.readString();
        this.skipButtonText = parcel.readString();
        this.nextTargetVersionMax = parcel.readString();
        this.forceGoToPlayStore = parcel.readByte() != 0;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getUpdateButtonText() {
        return this.updateButtonText;
    }

    public void setUpdateButtonText(String str) {
        this.updateButtonText = str;
    }

    public String getDimissButtonText() {
        return this.dimissButtonText;
    }

    public void setDimissButtonText(String str) {
        this.dimissButtonText = str;
    }

    public String getSkipButtonText() {
        return this.skipButtonText;
    }

    public void setSkipButtonText(String str) {
        this.skipButtonText = str;
    }

    public String getNextTargetVersionMax() {
        return this.nextTargetVersionMax;
    }

    public void setNextTargetVersionMax(String str) {
        this.nextTargetVersionMax = str;
    }

    public boolean isForceGoToPlayStore() {
        return this.forceGoToPlayStore;
    }

    public void setForceGoToPlayStore(boolean z) {
        this.forceGoToPlayStore = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.message);
        parcel.writeString(this.updateButtonText);
        parcel.writeString(this.dimissButtonText);
        parcel.writeString(this.skipButtonText);
        parcel.writeString(this.nextTargetVersionMax);
        parcel.writeByte(this.forceGoToPlayStore ? (byte) 1 : 0);
    }
}
