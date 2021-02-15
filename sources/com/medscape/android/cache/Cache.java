package com.medscape.android.cache;

import android.net.Uri;
import android.provider.BaseColumns;

public class Cache {
    public static int CLINICAL_PROCEDURE = 0;
    public static int CME = 3;
    public static int DRUGS = 1;
    public static int NEWS = 2;
    private String byline;
    private String content;
    private int id;
    private String imageUrl;
    private boolean isSaved;
    private String time;
    private String title;
    private int type;
    private String url;
    private String userGUID;

    public static final class Caches implements BaseColumns {
        public static final String BYLINE = "publicationName";
        public static final String CONDITION_ID = "conditionId";
        public static final String CONTENT = "content";
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.medscape.providers.MedscapeContentProvider/conditions");
        public static final String IMAGE = "imageUrl";
        public static final String ISSAVED = "isSaved";
        public static final String TIME = "time";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final String USERGUID = "userGUID";

        private Caches() {
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setSaved(boolean z) {
        this.isSaved = z;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setUserGUID(String str) {
        this.userGUID = str;
    }

    public String getUserGUID() {
        return this.userGUID;
    }

    public String getByline() {
        return this.byline;
    }

    public void setByline(String str) {
        this.byline = str;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }
}
