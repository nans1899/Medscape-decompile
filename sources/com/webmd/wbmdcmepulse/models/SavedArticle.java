package com.webmd.wbmdcmepulse.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.webmd.wbmdproffesionalauthentication.model.BasicInfo;

public class SavedArticle implements Parcelable {
    public static final Parcelable.Creator<SavedArticle> CREATOR = new Parcelable.Creator<SavedArticle>() {
        public SavedArticle createFromParcel(Parcel parcel) {
            return new SavedArticle(parcel);
        }

        public SavedArticle[] newArray(int i) {
            return new SavedArticle[i];
        }
    };
    public String articleId;
    public String creditType;
    public String detail;
    public String imageUrl;
    public String maxCredits;
    public String publishDate;
    public String publishType;
    public String title;
    public String userId;

    public int describeContents() {
        return 0;
    }

    public SavedArticle() {
    }

    public SavedArticle(Feed feed, BasicInfo basicInfo) {
        this.userId = basicInfo.getUserId();
        this.articleId = feed.getArticleId();
        this.title = feed.getTitle();
        this.publishType = feed.getPublication();
        this.creditType = feed.getCredit();
        this.detail = feed.getDetails();
        this.imageUrl = feed.getThumbUrl();
        this.maxCredits = feed.getMaxCredits();
        this.publishDate = feed.getDate();
    }

    public SavedArticle(Feed feed, String str) {
        this.userId = str;
        this.articleId = feed.getArticleId();
        this.title = feed.getTitle();
        this.publishType = feed.getPublication();
        this.creditType = feed.getCredit();
        this.detail = feed.getDetails();
        this.imageUrl = feed.getThumbUrl();
        this.maxCredits = feed.getMaxCredits();
        this.publishDate = feed.getDate();
    }

    public Feed toFeed() {
        Feed feed = new Feed();
        feed.setArticleId(this.articleId);
        feed.setTitle(this.title);
        feed.setPublication(this.publishType);
        feed.setCredit(this.creditType);
        feed.setDetails(this.detail);
        feed.setThumbUrl(this.imageUrl);
        feed.setMaxCredits(this.maxCredits);
        feed.setDate(this.publishDate);
        return feed;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.articleId);
        parcel.writeString(this.userId);
        parcel.writeString(this.title);
        parcel.writeString(this.detail);
        parcel.writeString(this.publishType);
        parcel.writeString(this.publishDate);
        parcel.writeString(this.maxCredits);
        parcel.writeString(this.creditType);
        parcel.writeString(this.imageUrl);
    }

    protected SavedArticle(Parcel parcel) {
        this.articleId = parcel.readString();
        this.userId = parcel.readString();
        this.title = parcel.readString();
        this.detail = parcel.readString();
        this.publishType = parcel.readString();
        this.publishDate = parcel.readString();
        this.maxCredits = parcel.readString();
        this.creditType = parcel.readString();
        this.imageUrl = parcel.readString();
    }

    public Feed getFeedFromSavedArticle() {
        Feed feed = new Feed();
        feed.setArticleId(this.articleId);
        feed.setTitle(this.title);
        feed.setPublication(this.publishType);
        feed.setCredit(this.creditType);
        feed.setDetails(this.detail);
        feed.setThumbUrl(this.imageUrl);
        feed.setMaxCredits(this.maxCredits);
        feed.setDate(this.publishDate);
        return feed;
    }
}
