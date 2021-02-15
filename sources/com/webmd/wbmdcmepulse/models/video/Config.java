package com.webmd.wbmdcmepulse.models.video;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Config implements Parcelable {
    public static final Parcelable.Creator<Config> CREATOR = new Parcelable.Creator<Config>() {
        public Config createFromParcel(Parcel parcel) {
            return new Config(parcel);
        }

        public Config[] newArray(int i) {
            return new Config[i];
        }
    };
    @SerializedName("articleId")
    private String mArticleId;
    @SerializedName("audioSource")
    private String mAudioSource;
    @SerializedName("autoplay")
    private Boolean mAutoplay;
    @SerializedName("bumperImage")
    private String mBumperImage;
    @SerializedName("ccDefaultLang")
    private String mCcDefaultLang;
    @SerializedName("ccFileRoot")
    private String mCcFileRoot;
    @SerializedName("duration")
    private String mDuration;
    @SerializedName("enableCc")
    private Boolean mEnableCc;
    @SerializedName("enableCcAutoDisplay")
    private Boolean mEnableCcAutoDisplay;
    @SerializedName("enableCcLangMenu")
    private Boolean mEnableCcLangMenu;
    @SerializedName("enableFullScreen")
    private Boolean mEnableFullScreen;
    @SerializedName("enableLength")
    private Boolean mEnableLength;
    @SerializedName("enableLooping")
    private Boolean mEnableLooping;
    @SerializedName("enableMute")
    private Boolean mEnableMute;
    @SerializedName("enablePlay")
    private Boolean mEnablePlay;
    @SerializedName("enableProgress")
    private Boolean mEnableProgress;
    @SerializedName("enableScrubbing")
    private Boolean mEnableScrubbing;
    @SerializedName("enableVolume")
    private Boolean mEnableVolume;
    @SerializedName("facultyList")
    private Boolean mFacultyList;
    @SerializedName("poster")
    private String mPoster;
    @SerializedName("publicationYear")
    private String mPublicationYear;
    @SerializedName("shouldShowControlsDesktop")
    private Boolean mShouldShowControlsDesktop;
    @SerializedName("shouldShowNativeControlsMobile")
    private Boolean mShouldShowNativeControlsMobile;
    @SerializedName("shouldShowPlaylist")
    private Boolean mShouldShowPlaylist;
    @SerializedName("slideRoot")
    private String mSlideRoot;
    @SerializedName("source")
    private String mSource;
    @SerializedName("vidChapters")
    private Boolean mVidChapters;
    @SerializedName("watermark")
    private String mWatermark;
    @SerializedName("watermarkPosition")
    private String mWatermarkPosition;

    public int describeContents() {
        return 0;
    }

    public String getArticleId() {
        return this.mArticleId;
    }

    public void setArticleId(String str) {
        this.mArticleId = str;
    }

    public String getAudioSource() {
        return this.mAudioSource;
    }

    public void setAudioSource(String str) {
        this.mAudioSource = str;
    }

    public Boolean getAutoplay() {
        return this.mAutoplay;
    }

    public void setAutoplay(Boolean bool) {
        this.mAutoplay = bool;
    }

    public String getBumperImage() {
        return this.mBumperImage;
    }

    public void setBumperImage(String str) {
        this.mBumperImage = str;
    }

    public String getCcDefaultLang() {
        return this.mCcDefaultLang;
    }

    public void setCcDefaultLang(String str) {
        this.mCcDefaultLang = str;
    }

    public String getCcFileRoot() {
        return this.mCcFileRoot;
    }

    public void setCcFileRoot(String str) {
        this.mCcFileRoot = str;
    }

    public String getDuration() {
        return this.mDuration;
    }

    public void setDuration(String str) {
        this.mDuration = str;
    }

    public Boolean getEnableCc() {
        return this.mEnableCc;
    }

    public void setEnableCc(Boolean bool) {
        this.mEnableCc = bool;
    }

    public Boolean getEnableCcAutoDisplay() {
        return this.mEnableCcAutoDisplay;
    }

    public void setEnableCcAutoDisplay(Boolean bool) {
        this.mEnableCcAutoDisplay = bool;
    }

    public Boolean getEnableCcLangMenu() {
        return this.mEnableCcLangMenu;
    }

    public void setEnableCcLangMenu(Boolean bool) {
        this.mEnableCcLangMenu = bool;
    }

    public Boolean getEnableFullScreen() {
        return this.mEnableFullScreen;
    }

    public void setEnableFullScreen(Boolean bool) {
        this.mEnableFullScreen = bool;
    }

    public Boolean getEnableLength() {
        return this.mEnableLength;
    }

    public void setEnableLength(Boolean bool) {
        this.mEnableLength = bool;
    }

    public Boolean getEnableLooping() {
        return this.mEnableLooping;
    }

    public void setEnableLooping(Boolean bool) {
        this.mEnableLooping = bool;
    }

    public Boolean getEnableMute() {
        return this.mEnableMute;
    }

    public void setEnableMute(Boolean bool) {
        this.mEnableMute = bool;
    }

    public Boolean getEnablePlay() {
        return this.mEnablePlay;
    }

    public void setEnablePlay(Boolean bool) {
        this.mEnablePlay = bool;
    }

    public Boolean getEnableProgress() {
        return this.mEnableProgress;
    }

    public void setEnableProgress(Boolean bool) {
        this.mEnableProgress = bool;
    }

    public Boolean getEnableScrubbing() {
        return this.mEnableScrubbing;
    }

    public void setEnableScrubbing(Boolean bool) {
        this.mEnableScrubbing = bool;
    }

    public Boolean getEnableVolume() {
        return this.mEnableVolume;
    }

    public void setEnableVolume(Boolean bool) {
        this.mEnableVolume = bool;
    }

    public Boolean getFacultyList() {
        return this.mFacultyList;
    }

    public void setFacultyList(Boolean bool) {
        this.mFacultyList = bool;
    }

    public String getPoster() {
        return this.mPoster;
    }

    public void setPoster(String str) {
        this.mPoster = str;
    }

    public String getPublicationYear() {
        return this.mPublicationYear;
    }

    public void setPublicationYear(String str) {
        this.mPublicationYear = str;
    }

    public Boolean getShouldShowControlsDesktop() {
        return this.mShouldShowControlsDesktop;
    }

    public void setShouldShowControlsDesktop(Boolean bool) {
        this.mShouldShowControlsDesktop = bool;
    }

    public Boolean getShouldShowNativeControlsMobile() {
        return this.mShouldShowNativeControlsMobile;
    }

    public void setShouldShowNativeControlsMobile(Boolean bool) {
        this.mShouldShowNativeControlsMobile = bool;
    }

    public Boolean getShouldShowPlaylist() {
        return this.mShouldShowPlaylist;
    }

    public void setShouldShowPlaylist(Boolean bool) {
        this.mShouldShowPlaylist = bool;
    }

    public String getSlideRoot() {
        return this.mSlideRoot;
    }

    public void setSlideRoot(String str) {
        this.mSlideRoot = str;
    }

    public String getSource() {
        return this.mSource;
    }

    public void setSource(String str) {
        this.mSource = str;
    }

    public Boolean getVidChapters() {
        return this.mVidChapters;
    }

    public void setVidChapters(Boolean bool) {
        this.mVidChapters = bool;
    }

    public String getWatermark() {
        return this.mWatermark;
    }

    public void setWatermark(String str) {
        this.mWatermark = str;
    }

    public String getWatermarkPosition() {
        return this.mWatermarkPosition;
    }

    public void setWatermarkPosition(String str) {
        this.mWatermarkPosition = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mArticleId);
        parcel.writeString(this.mAudioSource);
        parcel.writeValue(this.mAutoplay);
        parcel.writeString(this.mBumperImage);
        parcel.writeString(this.mCcDefaultLang);
        parcel.writeString(this.mCcFileRoot);
        parcel.writeString(this.mDuration);
        parcel.writeValue(this.mEnableCc);
        parcel.writeValue(this.mEnableCcAutoDisplay);
        parcel.writeValue(this.mEnableCcLangMenu);
        parcel.writeValue(this.mEnableFullScreen);
        parcel.writeValue(this.mEnableLength);
        parcel.writeValue(this.mEnableLooping);
        parcel.writeValue(this.mEnableMute);
        parcel.writeValue(this.mEnablePlay);
        parcel.writeValue(this.mEnableProgress);
        parcel.writeValue(this.mEnableScrubbing);
        parcel.writeValue(this.mEnableVolume);
        parcel.writeValue(this.mFacultyList);
        parcel.writeString(this.mPoster);
        parcel.writeString(this.mPublicationYear);
        parcel.writeValue(this.mShouldShowControlsDesktop);
        parcel.writeValue(this.mShouldShowNativeControlsMobile);
        parcel.writeValue(this.mShouldShowPlaylist);
        parcel.writeString(this.mSlideRoot);
        parcel.writeString(this.mSource);
        parcel.writeValue(this.mVidChapters);
        parcel.writeString(this.mWatermark);
        parcel.writeString(this.mWatermarkPosition);
    }

    public Config() {
    }

    protected Config(Parcel parcel) {
        this.mArticleId = parcel.readString();
        this.mAudioSource = parcel.readString();
        this.mAutoplay = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mBumperImage = parcel.readString();
        this.mCcDefaultLang = parcel.readString();
        this.mCcFileRoot = parcel.readString();
        this.mDuration = parcel.readString();
        this.mEnableCc = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableCcAutoDisplay = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableCcLangMenu = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableFullScreen = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableLength = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableLooping = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableMute = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnablePlay = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableProgress = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableScrubbing = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mEnableVolume = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mFacultyList = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mPoster = parcel.readString();
        this.mPublicationYear = parcel.readString();
        this.mShouldShowControlsDesktop = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mShouldShowNativeControlsMobile = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mShouldShowPlaylist = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mSlideRoot = parcel.readString();
        this.mSource = parcel.readString();
        this.mVidChapters = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.mWatermark = parcel.readString();
        this.mWatermarkPosition = parcel.readString();
    }
}
