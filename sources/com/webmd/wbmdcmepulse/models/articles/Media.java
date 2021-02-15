package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;

public class Media implements Parcelable {
    public static final Parcelable.Creator<Media> CREATOR = new Parcelable.Creator<Media>() {
        public Media createFromParcel(Parcel parcel) {
            return new Media(parcel);
        }

        public Media[] newArray(int i) {
            return new Media[i];
        }
    };
    public int audioBytesDownloaded;
    public String audioRssLocation;
    public boolean autoPlay;
    public String baseFolder;
    public String configPath;
    public float duration;
    public String format;
    public int height;
    public String location;
    public String swfLocation;
    public String title;
    public int videoBytesDownloaded;
    public String videoRssLocation;
    public int width;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.format);
        parcel.writeString(this.location);
        parcel.writeString(this.videoRssLocation);
        parcel.writeString(this.audioRssLocation);
        parcel.writeFloat(this.duration);
        parcel.writeInt(this.audioBytesDownloaded);
        parcel.writeInt(this.videoBytesDownloaded);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeString(this.baseFolder);
        parcel.writeByte(this.autoPlay ? (byte) 1 : 0);
        parcel.writeString(this.swfLocation);
        parcel.writeString(this.configPath);
    }

    public Media() {
    }

    protected Media(Parcel parcel) {
        this.title = parcel.readString();
        this.format = parcel.readString();
        this.location = parcel.readString();
        this.videoRssLocation = parcel.readString();
        this.audioRssLocation = parcel.readString();
        this.duration = parcel.readFloat();
        this.audioBytesDownloaded = parcel.readInt();
        this.videoBytesDownloaded = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.baseFolder = parcel.readString();
        this.autoPlay = parcel.readByte() != 0;
        this.swfLocation = parcel.readString();
        this.configPath = parcel.readString();
    }
}
