package com.webmd.wbmdcmepulse.models.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class SlideInfo implements Parcelable {
    public static final Parcelable.Creator<SlideInfo> CREATOR = new Parcelable.Creator<SlideInfo>() {
        public SlideInfo createFromParcel(Parcel parcel) {
            return new SlideInfo(parcel);
        }

        public SlideInfo[] newArray(int i) {
            return new SlideInfo[i];
        }
    };
    @SerializedName("assetUrl")
    private String mAssetUrl;
    @SerializedName("chapterTitle")
    private String mChapterTitle;
    @SerializedName("endTime")
    private String mEndTime;
    @SerializedName("qaidFormPage")
    private String mQaidFormPage;
    @SerializedName("slideNumber")
    private String mSlideNumber;
    @SerializedName("slideTitledescriptionNotesOptional")
    private String mSlideTitledescriptionNotesOptional;
    @SerializedName("startTime")
    private String mStartTime;
    @SerializedName("thumbnailOverride")
    private String mThumbnailOverride;
    @SerializedName("type")
    private String mType;
    @SerializedName("verbalCueOptional")
    private String mVerbalCueOptional;

    public int describeContents() {
        return 0;
    }

    public String getAssetUrl() {
        return this.mAssetUrl;
    }

    public void setAssetUrl(String str) {
        this.mAssetUrl = str;
    }

    public String getChapterTitle() {
        return this.mChapterTitle;
    }

    public void setChapterTitle(String str) {
        this.mChapterTitle = str;
    }

    public String getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(String str) {
        this.mEndTime = str;
    }

    public String getQaidFormPage() {
        return this.mQaidFormPage;
    }

    public void setQaidFormPage(String str) {
        this.mQaidFormPage = str;
    }

    public String getSlideNumber() {
        return this.mSlideNumber;
    }

    public void setSlideNumber(String str) {
        this.mSlideNumber = str;
    }

    public String getSlideTitledescriptionNotesOptional() {
        return this.mSlideTitledescriptionNotesOptional;
    }

    public void setSlideTitledescriptionNotesOptional(String str) {
        this.mSlideTitledescriptionNotesOptional = str;
    }

    public String getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(String str) {
        this.mStartTime = str;
    }

    public String getThumbnailOverride() {
        return this.mThumbnailOverride;
    }

    public void setThumbnailOverride(String str) {
        this.mThumbnailOverride = str;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public String getVerbalCueOptional() {
        return this.mVerbalCueOptional;
    }

    public void setVerbalCueOptional(String str) {
        this.mVerbalCueOptional = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAssetUrl);
        parcel.writeString(this.mChapterTitle);
        parcel.writeString(this.mEndTime);
        parcel.writeString(this.mQaidFormPage);
        parcel.writeString(this.mSlideNumber);
        parcel.writeString(this.mSlideTitledescriptionNotesOptional);
        parcel.writeString(this.mStartTime);
        parcel.writeString(this.mThumbnailOverride);
        parcel.writeString(this.mType);
        parcel.writeString(this.mVerbalCueOptional);
    }

    public SlideInfo() {
    }

    protected SlideInfo(Parcel parcel) {
        this.mAssetUrl = parcel.readString();
        this.mChapterTitle = parcel.readString();
        this.mEndTime = parcel.readString();
        this.mQaidFormPage = parcel.readString();
        this.mSlideNumber = parcel.readString();
        this.mSlideTitledescriptionNotesOptional = parcel.readString();
        this.mStartTime = parcel.readString();
        this.mThumbnailOverride = parcel.readString();
        this.mType = parcel.readString();
        this.mVerbalCueOptional = parcel.readString();
    }
}
