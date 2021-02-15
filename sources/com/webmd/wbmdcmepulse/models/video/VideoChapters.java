package com.webmd.wbmdcmepulse.models.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class VideoChapters implements Parcelable {
    public static final Parcelable.Creator<VideoChapters> CREATOR = new Parcelable.Creator<VideoChapters>() {
        public VideoChapters createFromParcel(Parcel parcel) {
            return new VideoChapters(parcel);
        }

        public VideoChapters[] newArray(int i) {
            return new VideoChapters[i];
        }
    };
    @SerializedName("ccInfo")
    private CcInfo mCcInfo;
    @SerializedName("config")
    private Config mConfig;
    @SerializedName("playlistInfo")
    private List<Object> mPlaylistInfo;
    @SerializedName("slideInfo")
    private List<SlideInfo> mSlideInfo;

    public int describeContents() {
        return 0;
    }

    public CcInfo getCcInfo() {
        return this.mCcInfo;
    }

    public void setCcInfo(CcInfo ccInfo) {
        this.mCcInfo = ccInfo;
    }

    public Config getConfig() {
        return this.mConfig;
    }

    public void setConfig(Config config) {
        this.mConfig = config;
    }

    public List<Object> getPlaylistInfo() {
        return this.mPlaylistInfo;
    }

    public void setPlaylistInfo(List<Object> list) {
        this.mPlaylistInfo = list;
    }

    public List<SlideInfo> getSlideInfo() {
        return this.mSlideInfo;
    }

    public void setSlideInfo(List<SlideInfo> list) {
        this.mSlideInfo = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mCcInfo, i);
        parcel.writeParcelable(this.mConfig, i);
        parcel.writeList(this.mPlaylistInfo);
        parcel.writeTypedList(this.mSlideInfo);
    }

    public VideoChapters() {
    }

    protected VideoChapters(Parcel parcel) {
        this.mCcInfo = (CcInfo) parcel.readParcelable(CcInfo.class.getClassLoader());
        this.mConfig = (Config) parcel.readParcelable(Config.class.getClassLoader());
        ArrayList arrayList = new ArrayList();
        this.mPlaylistInfo = arrayList;
        parcel.readList(arrayList, Object.class.getClassLoader());
        this.mSlideInfo = parcel.createTypedArrayList(SlideInfo.CREATOR);
    }
}
