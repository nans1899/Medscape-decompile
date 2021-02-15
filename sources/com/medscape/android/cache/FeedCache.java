package com.medscape.android.cache;

import android.net.Uri;
import android.provider.BaseColumns;

public class FeedCache {
    public static final Integer CME = 1;
    public static final Integer IWA = 2;
    public static final Integer NEWS = 0;
    private String feedCategory;
    private String feedDate;
    private String feedTitle;
    private String feedUrl;
    private String imageUrl;
    private boolean isSpecial;
    private Integer specialtyId;
    private Integer type;
    private String url;

    public static final class FeedCaches implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.medscape.providers.MedscapeContentProvider/feedscaches");
        public static final String FEED_DATE = "feedDate";
        public static final String FEED_TITLE = "feedTitle";
        public static final String FFED_CATEGORY = "feedCategory";
        public static final String FFED_IMAGE_URL = "feedImageUrl";
        public static final String FFED_URL = "feedUrl";
        public static final String IS_SPECIAL = "isSpecial";
        public static final String SPECIALITY_ID = "specialtyId";
        public static final String TYPE = "type";
        public static final String URL = "articleUrl";

        private FeedCaches() {
        }
    }

    public String getFeedTitle() {
        return this.feedTitle;
    }

    public void setFeedTitle(String str) {
        this.feedTitle = str;
    }

    public Integer getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(Integer num) {
        this.specialtyId = num;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getFeedDate() {
        return this.feedDate;
    }

    public void setFeedDate(String str) {
        this.feedDate = str;
    }

    public void setSpecial(boolean z) {
        this.isSpecial = z;
    }

    public boolean isSpecial() {
        return this.isSpecial;
    }

    public void setFeedCategory(String str) {
        this.feedCategory = str;
    }

    public String getFeedCategory() {
        return this.feedCategory;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
