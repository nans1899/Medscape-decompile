package com.medscape.android.consult.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ConsultUser extends ConsultFeedItem {
    public static final Parcelable.Creator<ConsultUser> CREATOR = new Parcelable.Creator<ConsultUser>() {
        public ConsultUser createFromParcel(Parcel parcel) {
            return new ConsultUser(parcel);
        }

        public ConsultUser[] newArray(int i) {
            return new ConsultUser[i];
        }
    };
    private int mAllTimeRank;
    private String mBio;
    private boolean mCanShowSponsoredLabel;
    private String mDisplayName;
    private String mEducation;
    private String mErrorFromServer;
    private int mFollowState;
    private int mFollowerCount = -1;
    private int mFollowingCount = -1;
    private String mInstitution;
    private ConsultAsset mInstitutionLogo;
    private boolean mIsStub;
    private String mMentionToken;
    private int mPostCount = -1;
    private String mProfessionID;
    private String mProfessionString;
    private String mProfessionalTitle;
    private String mProfessionalURL;
    private ConsultAsset mProfileImageAsset;
    private int mResponsesCount = -1;
    private String mSpecialty;
    private String mUserId;
    private String mUserName;
    private String mUserRole;
    private String mUserType;

    public int describeContents() {
        return 0;
    }

    public String getInstitution() {
        return this.mInstitution;
    }

    public void setInstitution(String str) {
        this.mInstitution = str;
    }

    public ConsultAsset getInstitutionLogo() {
        return this.mInstitutionLogo;
    }

    public void setInstitutionLogo(ConsultAsset consultAsset) {
        this.mInstitutionLogo = consultAsset;
    }

    public String getProfessionalTitle() {
        return this.mProfessionalTitle;
    }

    public void setProfessionalTitle(String str) {
        this.mProfessionalTitle = str;
    }

    public String getProfessionalURL() {
        return this.mProfessionalURL;
    }

    public void setProfessionalURL(String str) {
        this.mProfessionalURL = str;
    }

    public String getUserRole() {
        return this.mUserRole;
    }

    public void setUserRole(String str) {
        this.mUserRole = str;
    }

    public String getUserType() {
        return this.mUserType;
    }

    public void setUserType(String str) {
        this.mUserType = str;
    }

    public void setUserId(String str) {
        this.mUserId = str;
    }

    public String getUserId() {
        return this.mUserId;
    }

    public void setUserName(String str) {
        this.mUserName = str;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public String getBio() {
        return this.mBio;
    }

    public void setBio(String str) {
        this.mBio = str;
    }

    public String getSpecialty() {
        return this.mSpecialty;
    }

    public void setSpecialty(String str) {
        this.mSpecialty = str;
    }

    public String getEducation() {
        return this.mEducation;
    }

    public void setEducation(String str) {
        this.mEducation = str;
    }

    public ConsultAsset getProfileImageAsset() {
        return this.mProfileImageAsset;
    }

    public void setProfileImageAsset(ConsultAsset consultAsset) {
        this.mProfileImageAsset = consultAsset;
    }

    public int getPostCount() {
        if (this.mPostCount < 0) {
            this.mPostCount = 0;
        }
        return this.mPostCount;
    }

    public int getResponsesCount() {
        if (this.mResponsesCount < 0) {
            this.mResponsesCount = 0;
        }
        return this.mResponsesCount;
    }

    public void setResponsesCount(int i) {
        this.mResponsesCount = i;
    }

    public void setPostCount(int i) {
        this.mPostCount = i;
    }

    public int getFollowerCount() {
        if (this.mFollowerCount < 0) {
            this.mFollowerCount = 0;
        }
        return this.mFollowerCount;
    }

    public void setFollowerCount(int i) {
        this.mFollowerCount = i;
    }

    public int getFollowingCount() {
        if (this.mFollowingCount < 0) {
            this.mFollowingCount = 0;
        }
        return this.mFollowingCount;
    }

    public void setFollowingCount(int i) {
        this.mFollowingCount = i;
    }

    public String getMentionToken() {
        return this.mMentionToken;
    }

    public void setMentionToken(String str) {
        this.mMentionToken = str;
    }

    public int getAllTimeRank() {
        return this.mAllTimeRank;
    }

    public void setAllTimeRank(int i) {
        this.mAllTimeRank = i;
    }

    public boolean isIsStub() {
        return this.mIsStub;
    }

    public void setIsStub(boolean z) {
        this.mIsStub = z;
    }

    public int getFollowState() {
        return this.mFollowState;
    }

    public void setFollowState(int i) {
        this.mFollowState = i;
    }

    public void setErrorFromServer(String str) {
        this.mErrorFromServer = str;
    }

    public String getErrorFromServer() {
        return this.mErrorFromServer;
    }

    public String getProfessionString() {
        return this.mProfessionString;
    }

    public void setProfessionString(String str) {
        this.mProfessionString = str;
    }

    public String getProfessionID() {
        return this.mProfessionID;
    }

    public void setProfessionID(String str) {
        this.mProfessionID = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mUserName);
        parcel.writeString(this.mDisplayName);
        parcel.writeString(this.mBio);
        parcel.writeString(this.mSpecialty);
        parcel.writeString(this.mEducation);
        parcel.writeParcelable(this.mProfileImageAsset, 0);
        parcel.writeInt(this.mPostCount);
        parcel.writeInt(this.mFollowerCount);
        parcel.writeInt(this.mFollowingCount);
        parcel.writeString(this.mMentionToken);
        parcel.writeInt(this.mAllTimeRank);
        parcel.writeByte(this.mIsStub ? (byte) 1 : 0);
        parcel.writeByte(this.mCanShowSponsoredLabel ? (byte) 1 : 0);
        parcel.writeInt(this.mFollowState);
        parcel.writeString(this.mErrorFromServer);
        parcel.writeString(this.mInstitution);
        parcel.writeParcelable(this.mInstitutionLogo, 0);
        parcel.writeString(this.mProfessionalTitle);
        parcel.writeString(this.mProfessionString);
        parcel.writeString(this.mProfessionID);
        parcel.writeString(this.mProfessionalURL);
        parcel.writeString(this.mUserRole);
        parcel.writeString(this.mUserType);
    }

    public ConsultUser() {
    }

    protected ConsultUser(Parcel parcel) {
        super(parcel);
        this.mUserId = parcel.readString();
        this.mUserName = parcel.readString();
        this.mDisplayName = parcel.readString();
        this.mBio = parcel.readString();
        this.mSpecialty = parcel.readString();
        this.mEducation = parcel.readString();
        this.mProfileImageAsset = (ConsultAsset) parcel.readParcelable(ConsultAsset.class.getClassLoader());
        this.mPostCount = parcel.readInt();
        this.mFollowerCount = parcel.readInt();
        this.mFollowingCount = parcel.readInt();
        this.mMentionToken = parcel.readString();
        this.mAllTimeRank = parcel.readInt();
        boolean z = true;
        this.mIsStub = parcel.readByte() != 0;
        this.mCanShowSponsoredLabel = parcel.readByte() == 0 ? false : z;
        this.mFollowState = parcel.readInt();
        this.mErrorFromServer = parcel.readString();
        this.mInstitution = parcel.readString();
        this.mInstitutionLogo = (ConsultAsset) parcel.readParcelable(ConsultAsset.class.getClassLoader());
        this.mProfessionalTitle = parcel.readString();
        this.mProfessionString = parcel.readString();
        this.mProfessionID = parcel.readString();
        this.mProfessionalURL = parcel.readString();
        this.mUserRole = parcel.readString();
        this.mUserType = parcel.readString();
    }

    public boolean canShowSponsoredLabel() {
        return this.mCanShowSponsoredLabel;
    }

    public void setCanShowSponsoredLabel(boolean z) {
        this.mCanShowSponsoredLabel = z;
    }
}
