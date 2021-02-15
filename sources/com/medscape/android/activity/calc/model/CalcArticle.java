package com.medscape.android.activity.calc.model;

import android.net.Uri;
import android.provider.BaseColumns;
import java.io.Serializable;

public class CalcArticle implements Serializable {
    private String calcId;
    private boolean isSaved;
    private String title;
    private int type;

    public static final class CalcArticles implements BaseColumns {
        public static final String CALC_ID = "calcId";
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.medscape.providers.MedscapeContentProvider/calculatorcaches");
        public static final String GUID = "userGuid";
        public static final String ISSAVED = "isSaved";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final String UNIQUE_ID = "uniqueId";

        private CalcArticles() {
        }
    }

    public void setCalcId(String str) {
        this.calcId = str;
    }

    public String getCalcId() {
        return this.calcId;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public void setSaved(boolean z) {
        this.isSaved = z;
    }
}
