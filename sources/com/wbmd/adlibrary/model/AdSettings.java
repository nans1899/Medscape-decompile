package com.wbmd.adlibrary.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.wbmd.adlibrary.audience.Audience;
import com.wbmd.adlibrary.audience.AudienceInfoResponse;
import java.util.ArrayList;
import java.util.Iterator;

public class AdSettings implements Parcelable {
    public static final Parcelable.Creator<AdSettings> CREATOR = new Parcelable.Creator<AdSettings>() {
        public AdSettings createFromParcel(Parcel parcel) {
            return new AdSettings(parcel);
        }

        public AdSettings[] newArray(int i) {
            return new AdSettings[i];
        }
    };
    private int mAdSession;
    private int mAdsEnvironment;
    private String mAppVersion;
    private String mArticleId;
    private AudienceInfoResponse mAudienceInfoResponse;
    private String mConditionId;
    private String mContentUrl;
    private String mDrugId;
    private ArrayList<String> mExclusionCategories;
    private String mGender;
    private boolean mIsPhone;
    private String mIsSponsored;
    private String mOs;
    private String mPos;
    private String mPrimaryId;
    private String mPug;
    private ArrayList<String> mSecondaryIds;
    private String mSectionId;
    private String mTug;

    public int describeContents() {
        return 0;
    }

    public AdSettings(int i, String str, String str2, boolean z, int i2, String str3, String str4, String str5) {
        this.mAdsEnvironment = i;
        this.mAppVersion = str;
        this.mGender = str2;
        this.mIsPhone = z;
        this.mAdSession = i2;
        this.mOs = str3;
        this.mSectionId = str4;
        this.mPos = str5;
        this.mDrugId = null;
    }

    public AdSettings(int i, String str, String str2, boolean z, int i2, String str3, String str4, String str5, String str6) {
        this.mAdsEnvironment = i;
        this.mAppVersion = str;
        this.mGender = str2;
        this.mIsPhone = z;
        this.mAdSession = i2;
        this.mOs = str3;
        this.mSectionId = str4;
        this.mPos = str5;
        this.mDrugId = str6;
    }

    public AdSettings(int i, String str, String str2, boolean z, int i2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.mAdsEnvironment = i;
        this.mAppVersion = str;
        this.mGender = str2;
        this.mIsPhone = z;
        this.mAdSession = i2;
        this.mOs = str3;
        this.mSectionId = str4;
        this.mPos = str5;
        this.mDrugId = str6;
        this.mPrimaryId = str7;
        this.mTug = str9;
        this.mPug = str8;
    }

    public AdSettings(int i, String str, String str2, boolean z, int i2, String str3, String str4, String str5, String str6, String str7, ArrayList<String> arrayList, ArrayList<String> arrayList2, String str8) {
        this.mAdsEnvironment = i;
        this.mAppVersion = str;
        this.mGender = str2;
        this.mIsPhone = z;
        this.mAdSession = i2;
        this.mOs = str3;
        this.mSectionId = str4;
        this.mPos = str5;
        this.mArticleId = str6;
        this.mPrimaryId = str7;
        this.mSecondaryIds = arrayList;
        this.mExclusionCategories = arrayList2;
        this.mConditionId = str8;
    }

    public String getContentUrl() {
        return this.mContentUrl;
    }

    public void setContentUrl(String str) {
        this.mContentUrl = str;
    }

    public int getAdsEnv() {
        return this.mAdsEnvironment;
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getGender() {
        return this.mGender;
    }

    public boolean getIsPhone() {
        return this.mIsPhone;
    }

    public int getAdSession() {
        return this.mAdSession;
    }

    public String getOs() {
        return this.mOs;
    }

    public String getSectionId() {
        return this.mSectionId;
    }

    public String getPos() {
        return this.mPos;
    }

    public String getDrugId() {
        return this.mDrugId;
    }

    public String getTug() {
        return this.mTug;
    }

    public String getPug() {
        return this.mPug;
    }

    public String getArticleId() {
        return this.mArticleId;
    }

    public void setArticleId(String str) {
        this.mArticleId = str;
    }

    public String getPrimaryId() {
        return this.mPrimaryId;
    }

    public void setPrimaryId(String str) {
        this.mPrimaryId = str;
    }

    public ArrayList<String> getSecondaryIds() {
        return this.mSecondaryIds;
    }

    public void setSecondaryIds(ArrayList<String> arrayList) {
        this.mSecondaryIds = arrayList;
    }

    public ArrayList<String> getExclusionCategories() {
        return this.mExclusionCategories;
    }

    public void setExclusionCategories(ArrayList<String> arrayList) {
        this.mExclusionCategories = arrayList;
    }

    public String getConditionId() {
        return this.mConditionId;
    }

    public void setConditionId(String str) {
        this.mConditionId = str;
    }

    public String getIsSponsored() {
        return this.mIsSponsored;
    }

    public void setIsSponsored(String str) {
        this.mIsSponsored = str;
    }

    public AudienceInfoResponse getAudienceInfoResponse() {
        return this.mAudienceInfoResponse;
    }

    public void setAudienceInfoResponse(AudienceInfoResponse audienceInfoResponse) {
        this.mAudienceInfoResponse = audienceInfoResponse;
    }

    public String getLotameAttributesCSV() {
        AudienceInfoResponse audienceInfoResponse = this.mAudienceInfoResponse;
        if (audienceInfoResponse == null || audienceInfoResponse.getProfile() == null || this.mAudienceInfoResponse.getProfile().getAudiences() == null || this.mAudienceInfoResponse.getProfile().getAudiences().getAudience() == null || this.mAudienceInfoResponse.getProfile().getAudiences().getAudience().size() <= 0) {
            return null;
        }
        int size = this.mAudienceInfoResponse.getProfile().getAudiences().getAudience().size();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Audience abbr : this.mAudienceInfoResponse.getProfile().getAudiences().getAudience()) {
            sb.append(abbr.getAbbr());
            i++;
            if (i < size) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String getSecondaryIdsCommaDelimeted() {
        StringBuilder sb = new StringBuilder();
        if (getSecondaryIds() != null) {
            int size = getSecondaryIds().size();
            int i = 0;
            Iterator<String> it = getSecondaryIds().iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                i++;
                if (i < size) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mAdsEnvironment);
        parcel.writeString(this.mAppVersion);
        parcel.writeString(this.mGender);
        parcel.writeByte(this.mIsPhone ? (byte) 1 : 0);
        parcel.writeInt(this.mAdSession);
        parcel.writeString(this.mOs);
        parcel.writeString(this.mSectionId);
        parcel.writeString(this.mPos);
        parcel.writeString(this.mDrugId);
        parcel.writeString(this.mArticleId);
        parcel.writeString(this.mPrimaryId);
        parcel.writeStringList(this.mSecondaryIds);
        parcel.writeStringList(this.mExclusionCategories);
        parcel.writeString(this.mConditionId);
        parcel.writeString(this.mTug);
        parcel.writeString(this.mPug);
        parcel.writeString(this.mIsSponsored);
        parcel.writeString(this.mContentUrl);
        parcel.writeParcelable(this.mAudienceInfoResponse, i);
    }

    protected AdSettings(Parcel parcel) {
        this.mAdsEnvironment = parcel.readInt();
        this.mAppVersion = parcel.readString();
        this.mGender = parcel.readString();
        this.mIsPhone = parcel.readByte() != 0;
        this.mAdSession = parcel.readInt();
        this.mOs = parcel.readString();
        this.mSectionId = parcel.readString();
        this.mPos = parcel.readString();
        this.mDrugId = parcel.readString();
        this.mArticleId = parcel.readString();
        this.mPrimaryId = parcel.readString();
        this.mSecondaryIds = parcel.createStringArrayList();
        this.mExclusionCategories = parcel.createStringArrayList();
        this.mConditionId = parcel.readString();
        this.mTug = parcel.readString();
        this.mPug = parcel.readString();
        this.mIsSponsored = parcel.readString();
        this.mContentUrl = parcel.readString();
        this.mAudienceInfoResponse = (AudienceInfoResponse) parcel.readParcelable(AudienceInfoResponse.class.getClassLoader());
    }
}
