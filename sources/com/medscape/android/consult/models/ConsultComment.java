package com.medscape.android.consult.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Calendar;

public class ConsultComment extends ConsultFeedItem {
    public static final Parcelable.Creator<ConsultComment> CREATOR = new Parcelable.Creator<ConsultComment>() {
        public ConsultComment createFromParcel(Parcel parcel) {
            return new ConsultComment(parcel);
        }

        public ConsultComment[] newArray(int i) {
            return new ConsultComment[i];
        }
    };
    private String mCommentBody;
    private String mCommentId;
    private int mCommentType;
    private String mContentId;
    private String mContentTypeId;
    private int mDownVoteCount;
    private boolean mIsCommentOnHiddenPost;
    private boolean mIsDownVoted;
    private boolean mIsHeader;
    private boolean mIsLoadMorePlaceHolder;
    private boolean mIsLowQualityPost = false;
    private boolean mIsLowQualityPostShown = false;
    private boolean mIsMedscapeApprovedAnswer;
    private boolean mIsParentHidden = false;
    private boolean mIsUpVoted;
    private ConsultUser mLastReplyUser;
    private String mParentCommentId;
    private String mParentThreadId;
    private ConsultUser mPoster;
    private ConsultFeed mRepliesFeed;
    private int mReplyCount;
    private ConsultUser mReviewer;
    private Calendar mTimestamp;
    private int mUpVoteCount;

    public int describeContents() {
        return 0;
    }

    public boolean isParentHidden() {
        return this.mIsParentHidden;
    }

    public void setIsParentHidden(boolean z) {
        this.mIsParentHidden = z;
    }

    public boolean isLowQualityPost() {
        return this.mIsLowQualityPost;
    }

    public void setIsLowQualityPost(boolean z) {
        this.mIsLowQualityPost = z;
    }

    public boolean isLowQualityPostShown() {
        return this.mIsLowQualityPostShown;
    }

    public void setIsLowQualityPostShown(boolean z) {
        this.mIsLowQualityPostShown = z;
    }

    public void setIsLoadMorePlaceHolder(boolean z) {
        this.mIsLoadMorePlaceHolder = z;
    }

    public boolean isLoadMorePlaceHolder() {
        return this.mIsLoadMorePlaceHolder;
    }

    public void setIsHeader(boolean z) {
        this.mIsHeader = z;
    }

    public boolean isHeader() {
        return this.mIsHeader;
    }

    public void setCommentId(String str) {
        this.mCommentId = str;
    }

    public String getCommentId() {
        return this.mCommentId;
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

    public void setReviewer(ConsultUser consultUser) {
        this.mReviewer = consultUser;
    }

    public ConsultUser getReviewer() {
        return this.mReviewer;
    }

    public void setPoster(ConsultUser consultUser) {
        this.mPoster = consultUser;
    }

    public ConsultUser getPoster() {
        return this.mPoster;
    }

    public void setCommentBody(String str) {
        this.mCommentBody = str;
    }

    public String getCommentBody() {
        return this.mCommentBody;
    }

    public void setUpVoteCount(int i) {
        this.mUpVoteCount = i;
    }

    public int getUpVoteCount() {
        return this.mUpVoteCount;
    }

    public void setDownVoteCount(int i) {
        this.mDownVoteCount = i;
    }

    public int getDownVoteCount() {
        return this.mDownVoteCount;
    }

    public void setLastReplyUser(ConsultUser consultUser) {
        this.mLastReplyUser = consultUser;
    }

    public ConsultUser getLastReplyUser() {
        return this.mLastReplyUser;
    }

    public void setReplyCount(int i) {
        this.mReplyCount = i;
    }

    public int getReplyCount() {
        return this.mReplyCount;
    }

    public void setTimestamp(Calendar calendar) {
        this.mTimestamp = calendar;
    }

    public Calendar getTimestamp() {
        return this.mTimestamp;
    }

    public void setParentThreadId(String str) {
        this.mParentThreadId = str;
    }

    public String getParentThreadId() {
        return this.mParentThreadId;
    }

    public void setParentCommentId(String str) {
        this.mParentCommentId = str;
    }

    public String getParentCommentId() {
        return this.mParentCommentId;
    }

    public void setIsCommentOnHiddenPost(boolean z) {
        this.mIsCommentOnHiddenPost = z;
    }

    public boolean isCommentOnHiddenPost() {
        return this.mIsCommentOnHiddenPost;
    }

    public void setIsMedscapeApprovedAnswer(boolean z) {
        this.mIsMedscapeApprovedAnswer = z;
    }

    public boolean isMedscapeApprovedAnswer() {
        return this.mIsMedscapeApprovedAnswer;
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

    public void setCommentType(int i) {
        this.mCommentType = i;
    }

    public int getCommentType() {
        return this.mCommentType;
    }

    public void setRepliesFeed(ConsultFeed consultFeed) {
        this.mRepliesFeed = consultFeed;
    }

    public ConsultFeed getRepliesFeed() {
        return this.mRepliesFeed;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mCommentId);
        parcel.writeString(this.mContentTypeId);
        parcel.writeString(this.mContentId);
        parcel.writeParcelable(this.mPoster, i);
        parcel.writeParcelable(this.mReviewer, i);
        parcel.writeString(this.mCommentBody);
        parcel.writeInt(this.mUpVoteCount);
        parcel.writeInt(this.mDownVoteCount);
        parcel.writeParcelable(this.mLastReplyUser, i);
        parcel.writeInt(this.mReplyCount);
        parcel.writeSerializable(this.mTimestamp);
        parcel.writeString(this.mParentThreadId);
        parcel.writeString(this.mParentCommentId);
        parcel.writeByte(this.mIsHeader ? (byte) 1 : 0);
        parcel.writeByte(this.mIsCommentOnHiddenPost ? (byte) 1 : 0);
        parcel.writeByte(this.mIsMedscapeApprovedAnswer ? (byte) 1 : 0);
        parcel.writeByte(this.mIsUpVoted ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDownVoted ? (byte) 1 : 0);
        parcel.writeByte(this.mIsLoadMorePlaceHolder ? (byte) 1 : 0);
        parcel.writeInt(this.mCommentType);
        parcel.writeParcelable(this.mRepliesFeed, i);
        parcel.writeByte(this.mIsLowQualityPost ? (byte) 1 : 0);
        parcel.writeByte(this.mIsLowQualityPostShown ? (byte) 1 : 0);
        parcel.writeByte(this.mIsParentHidden ? (byte) 1 : 0);
    }

    public ConsultComment() {
    }

    protected ConsultComment(Parcel parcel) {
        super(parcel);
        boolean z = false;
        this.mCommentId = parcel.readString();
        this.mContentTypeId = parcel.readString();
        this.mContentId = parcel.readString();
        this.mPoster = (ConsultUser) parcel.readParcelable(ConsultUser.class.getClassLoader());
        this.mReviewer = (ConsultUser) parcel.readParcelable(ConsultUser.class.getClassLoader());
        this.mCommentBody = parcel.readString();
        this.mUpVoteCount = parcel.readInt();
        this.mDownVoteCount = parcel.readInt();
        this.mLastReplyUser = (ConsultUser) parcel.readParcelable(ConsultUser.class.getClassLoader());
        this.mReplyCount = parcel.readInt();
        this.mTimestamp = (Calendar) parcel.readSerializable();
        this.mParentThreadId = parcel.readString();
        this.mParentCommentId = parcel.readString();
        this.mIsHeader = parcel.readByte() != 0;
        this.mIsCommentOnHiddenPost = parcel.readByte() != 0;
        this.mIsMedscapeApprovedAnswer = parcel.readByte() != 0;
        this.mIsUpVoted = parcel.readByte() != 0;
        this.mIsDownVoted = parcel.readByte() != 0;
        this.mIsLoadMorePlaceHolder = parcel.readByte() != 0;
        this.mCommentType = parcel.readInt();
        this.mRepliesFeed = (ConsultFeed) parcel.readParcelable(ConsultFeed.class.getClassLoader());
        this.mIsLowQualityPost = parcel.readByte() != 0;
        this.mIsLowQualityPostShown = parcel.readByte() != 0;
        this.mIsParentHidden = parcel.readByte() != 0 ? true : z;
    }
}
