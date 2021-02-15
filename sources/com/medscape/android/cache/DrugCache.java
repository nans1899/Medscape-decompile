package com.medscape.android.cache;

import android.net.Uri;
import android.provider.BaseColumns;

public class DrugCache {
    public static final int DRUGS = 1;
    private int contentId;
    private String drugName;
    private int id;
    private boolean isSaved;
    private int type;
    private String userGUID;

    public static final class DrugCaches implements BaseColumns {
        public static final String CONTENT_ID = "contentId";
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.medscape.providers.MedscapeContentProvider/drugcaches");
        public static final String DRUG_NAME = "drugName";
        public static final String ISSAVED = "isSaved";
        public static final String TYPE = "type";
        public static final String USERGUID = "userGUID";

        private DrugCaches() {
        }
    }

    public void setContentId(int i) {
        this.contentId = i;
    }

    public int getContentId() {
        return this.contentId;
    }

    public void setSaved(boolean z) {
        this.isSaved = z;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public void setDrugName(String str) {
        this.drugName = str;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public void setUserGUID(String str) {
        this.userGUID = str;
    }

    public String getUserGUID() {
        return this.userGUID;
    }
}
