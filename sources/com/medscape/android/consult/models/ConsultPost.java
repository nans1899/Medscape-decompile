package com.medscape.android.consult.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConsultPost extends ConsultFeedItem {
    public static final Parcelable.Creator<ConsultPost> CREATOR = new Parcelable.Creator<ConsultPost>() {
        public ConsultPost createFromParcel(Parcel parcel) {
            return new ConsultPost(parcel);
        }

        public ConsultPost[] newArray(int i) {
            return new ConsultPost[i];
        }
    };
    private List<BodyUpdates> bodyUpdates;
    private boolean isCaseResolved;
    private boolean isDownVoteProgress = false;
    private boolean isFromAD = false;
    private boolean isUpVoteProgress = false;
    private List<ConsultFeedItem> mApprovedAnswers;
    private CMEInfo mCMEInfo;
    private int mCommentCount;
    private String mContentId;
    private String mContentTypeId;
    private int mDownVoteCount;
    private int mFollowState;
    private boolean mIsDownVoted;
    private boolean mIsHeader = false;
    private boolean mIsHiddenPost;
    private boolean mIsImageRefreshNotRequired = false;
    private boolean mIsLowQualityPost = false;
    private boolean mIsLowQualityPostShown = false;
    private boolean mIsPendingApproval;
    private boolean mIsRepliesByUser = false;
    private boolean mIsStub;
    private boolean mIsUpVoted;
    private int mMaxAssetHeight = -1;
    private List<String> mMediaAssetURLs;
    private List<ConsultAsset> mMediaAssets;
    private String mPostBody;
    private String mPostId;
    private ConsultUser mPoster;
    private List<ConsultFeedItem> mReplies;
    private ConsultUser mReviewer;
    private String mSubject;
    private List<String> mTags;
    private Calendar mTimestamp;
    private int mUpVoteCount;

    public int describeContents() {
        return 0;
    }

    public void setDownVoteProgress(boolean z) {
        this.isDownVoteProgress = z;
    }

    public boolean isLowQualityPost() {
        return this.mIsLowQualityPost;
    }

    public void setIsLowQualityPost(boolean z) {
        this.mIsLowQualityPost = z;
    }

    public boolean isFromAD() {
        return this.isFromAD;
    }

    public void setFromAD(boolean z) {
        this.isFromAD = z;
    }

    public boolean isLowQualityPostShown() {
        return this.mIsLowQualityPostShown;
    }

    public void setIsLowQualityPostShown(boolean z) {
        this.mIsLowQualityPostShown = z;
    }

    public void setPostId(String str) {
        this.mPostId = str;
    }

    public String getPostId() {
        return this.mPostId;
    }

    public void setContentTypeId(String str) {
        this.mContentTypeId = str;
    }

    public String getContentTypeId() {
        return this.mContentTypeId;
    }

    public void setContentId(String str) {
        this.mContentId = str;
    }

    public String getContentId() {
        return this.mContentId;
    }

    public void setConsultAssets(List<ConsultAsset> list) {
        this.mMediaAssets = list;
    }

    public List<ConsultAsset> getConsultAssets() {
        return this.mMediaAssets;
    }

    public void addConsultAssetUrl(String str) {
        if (this.mMediaAssetURLs == null) {
            this.mMediaAssetURLs = new ArrayList();
        }
        this.mMediaAssetURLs.add(str);
    }

    public List<String> getConsultAssetUrls() {
        return this.mMediaAssetURLs;
    }

    public void setPoster(ConsultUser consultUser) {
        this.mPoster = consultUser;
    }

    public ConsultUser getPoster() {
        return this.mPoster;
    }

    public void setReviewer(ConsultUser consultUser) {
        this.mReviewer = consultUser;
    }

    public ConsultUser getReviewer() {
        return this.mReviewer;
    }

    public void setTimestamp(Calendar calendar) {
        this.mTimestamp = calendar;
    }

    public Calendar getTimestamp() {
        return this.mTimestamp;
    }

    public void setSubject(String str) {
        this.mSubject = str;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public void setPostBody(String str) {
        this.mPostBody = str;
    }

    public String getPostBody() {
        return this.mPostBody;
    }

    public void setTags(List<String> list) {
        this.mTags = list;
    }

    public List<String> getTags() {
        return this.mTags;
    }

    public void setUpVoteCount(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mUpVoteCount = i;
    }

    public int getUpVoteCount() {
        return this.mUpVoteCount;
    }

    public void setDownVoteCount(int i) {
        if (i < 0) {
            i = 0;
        }
        this.mDownVoteCount = i;
    }

    public int getDownVoteCount() {
        return this.mDownVoteCount;
    }

    public void setCommentCount(int i) {
        this.mCommentCount = i;
    }

    public int getCommentCount() {
        return this.mCommentCount;
    }

    public void setIsHidden(boolean z) {
        this.mIsHiddenPost = z;
    }

    public boolean isHidden() {
        return this.mIsHiddenPost;
    }

    public void setIsUpVoted(boolean z) {
        this.mIsUpVoted = z;
    }

    public boolean isUpVoted() {
        return this.mIsUpVoted;
    }

    public void setIsDownVoted(boolean z) {
        this.mIsDownVoted = z;
    }

    public boolean isDownVoted() {
        return this.mIsDownVoted;
    }

    public void setApprovedAnswers(List<ConsultFeedItem> list) {
        this.mApprovedAnswers = list;
    }

    public List<ConsultFeedItem> getApprovedAnswers() {
        return this.mApprovedAnswers;
    }

    public List<ConsultFeedItem> getReplies() {
        return this.mReplies;
    }

    public void setReplies(List<ConsultFeedItem> list) {
        this.mReplies = list;
    }

    public void setFollowState(int i) {
        this.mFollowState = i;
    }

    public int getFollowState() {
        return this.mFollowState;
    }

    public void setIsHeader(boolean z) {
        this.mIsHeader = z;
    }

    public boolean isHeader() {
        return this.mIsHeader;
    }

    public boolean isRepliesByUser() {
        return this.mIsRepliesByUser;
    }

    public void setIsRepliesByUser(boolean z) {
        this.mIsRepliesByUser = z;
    }

    public boolean ismIsImageRefreshNotRequired() {
        return this.mIsImageRefreshNotRequired;
    }

    public void setmIsImageRefreshNotRequired(boolean z) {
        this.mIsImageRefreshNotRequired = z;
    }

    public void setCMEInfo(CMEInfo cMEInfo) {
        this.mCMEInfo = cMEInfo;
    }

    public CMEInfo getCMEInfo() {
        return this.mCMEInfo;
    }

    public boolean isUpVoteProgress() {
        return this.isUpVoteProgress;
    }

    public void setUpVoteProgress(boolean z) {
        this.isUpVoteProgress = z;
    }

    public boolean isDownVoteProgress() {
        return this.isDownVoteProgress;
    }

    public boolean isShowSponsored() {
        return ConsultUtils.isshowSponsoredLabel(this.mPoster);
    }

    public int getMaxAssetHeight(Context context) {
        int imageViewHeight;
        int i = this.mMaxAssetHeight;
        if (i > 0) {
            return i;
        }
        List<ConsultAsset> list = this.mMediaAssets;
        if (list != null && list.size() > 0) {
            for (ConsultAsset next : this.mMediaAssets) {
                if (next != null && (imageViewHeight = (int) ConsultUtils.getImageViewHeight(context, ConsultUtils.getImageSizeOfAsset(next.getAssetUrl()))) > this.mMaxAssetHeight) {
                    this.mMaxAssetHeight = imageViewHeight;
                }
            }
        }
        return this.mMaxAssetHeight;
    }

    public List<BodyUpdates> getConsultBodyUpdateList() {
        return this.bodyUpdates;
    }

    public void setBodyUpdates(List<BodyUpdates> list) {
        this.bodyUpdates = list;
    }

    public boolean isCaseResolved() {
        return this.isCaseResolved;
    }

    public void setCaseResolved(boolean z) {
        this.isCaseResolved = z;
    }

    public boolean isCMEEligible() {
        CMEInfo cMEInfo = this.mCMEInfo;
        if (cMEInfo == null || !StringUtil.isNotEmpty(cMEInfo.getCMEArticleId()) || this.mCMEInfo.getCMECreditCount() <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mPostId);
        parcel.writeString(this.mContentTypeId);
        parcel.writeString(this.mContentId);
        parcel.writeList(this.mMediaAssets);
        parcel.writeStringList(this.mMediaAssetURLs);
        parcel.writeParcelable(this.mReviewer, i);
        parcel.writeParcelable(this.mPoster, i);
        parcel.writeSerializable(this.mTimestamp);
        parcel.writeString(this.mSubject);
        parcel.writeString(this.mPostBody);
        parcel.writeStringList(this.mTags);
        parcel.writeInt(this.mUpVoteCount);
        parcel.writeInt(this.mDownVoteCount);
        parcel.writeInt(this.mCommentCount);
        parcel.writeByte(this.mIsHiddenPost ? (byte) 1 : 0);
        parcel.writeByte(this.mIsPendingApproval ? (byte) 1 : 0);
        parcel.writeByte(this.mIsStub ? (byte) 1 : 0);
        parcel.writeByte(this.mIsUpVoted ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDownVoted ? (byte) 1 : 0);
        parcel.writeParcelable(this.mCMEInfo, i);
        parcel.writeInt(this.mFollowState);
        parcel.writeList(this.mApprovedAnswers);
        parcel.writeList(this.mReplies);
        parcel.writeByte(this.mIsHeader ? (byte) 1 : 0);
        parcel.writeByte(this.mIsRepliesByUser ? (byte) 1 : 0);
        parcel.writeByte(this.mIsImageRefreshNotRequired ? (byte) 1 : 0);
        parcel.writeByte(this.mIsLowQualityPost ? (byte) 1 : 0);
        parcel.writeByte(this.mIsLowQualityPostShown ? (byte) 1 : 0);
        parcel.writeInt(this.mMaxAssetHeight);
        parcel.writeList(this.bodyUpdates);
        parcel.writeByte(this.isCaseResolved ? (byte) 1 : 0);
        parcel.writeByte(this.isUpVoteProgress ? (byte) 1 : 0);
        parcel.writeByte(this.isFromAD ? (byte) 1 : 0);
    }

    public ConsultPost() {
    }

    protected ConsultPost(Parcel parcel) {
        super(parcel);
        boolean z = false;
        this.mPostId = parcel.readString();
        this.mContentTypeId = parcel.readString();
        this.mContentId = parcel.readString();
        ArrayList arrayList = new ArrayList();
        this.mMediaAssets = arrayList;
        parcel.readList(arrayList, ConsultAsset.class.getClassLoader());
        this.mMediaAssetURLs = parcel.createStringArrayList();
        this.mReviewer = (ConsultUser) parcel.readParcelable(ConsultUser.class.getClassLoader());
        this.mPoster = (ConsultUser) parcel.readParcelable(ConsultUser.class.getClassLoader());
        this.mTimestamp = (Calendar) parcel.readSerializable();
        this.mSubject = parcel.readString();
        this.mPostBody = parcel.readString();
        this.mTags = parcel.createStringArrayList();
        this.mUpVoteCount = parcel.readInt();
        this.mDownVoteCount = parcel.readInt();
        this.mCommentCount = parcel.readInt();
        this.mIsHiddenPost = parcel.readByte() != 0;
        this.mIsPendingApproval = parcel.readByte() != 0;
        this.mIsStub = parcel.readByte() != 0;
        this.mIsUpVoted = parcel.readByte() != 0;
        this.mIsDownVoted = parcel.readByte() != 0;
        this.mCMEInfo = (CMEInfo) parcel.readParcelable(CMEInfo.class.getClassLoader());
        this.mFollowState = parcel.readInt();
        ArrayList arrayList2 = new ArrayList();
        this.mApprovedAnswers = arrayList2;
        parcel.readList(arrayList2, ConsultFeedItem.class.getClassLoader());
        ArrayList arrayList3 = new ArrayList();
        this.mReplies = arrayList3;
        parcel.readList(arrayList3, ConsultFeedItem.class.getClassLoader());
        this.mIsHeader = parcel.readByte() != 0;
        this.mIsRepliesByUser = parcel.readByte() != 0;
        this.mIsImageRefreshNotRequired = parcel.readByte() != 0;
        this.mIsLowQualityPost = parcel.readByte() != 0;
        this.mIsLowQualityPostShown = parcel.readByte() != 0;
        this.mMaxAssetHeight = parcel.readInt();
        ArrayList arrayList4 = new ArrayList();
        this.bodyUpdates = arrayList4;
        parcel.readList(arrayList4, BodyUpdates.class.getClassLoader());
        this.isCaseResolved = parcel.readByte() != 0;
        this.isUpVoteProgress = parcel.readByte() != 0;
        this.isFromAD = parcel.readByte() != 0 ? true : z;
    }
}
