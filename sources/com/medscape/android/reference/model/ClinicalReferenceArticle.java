package com.medscape.android.reference.model;

import android.net.Uri;
import android.provider.BaseColumns;
import java.io.Serializable;

public class ClinicalReferenceArticle implements Serializable {
    private static final long serialVersionUID = 7106623112299353728L;
    private String localXMLPath;
    private int mArticleId;
    private ClinicalReferenceContent mContent;
    private boolean mIsSaved;
    private String mTitle;
    private int mType;
    private int mUniqueId;

    public static final class ClinicalReferenceArticles implements BaseColumns {
        public static final String ARTICLE_ID = "articleId";
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.medscape.providers.MedscapeContentProvider/conditionscaches");
        public static final String GUID = "userGuid";
        public static final String ISSAVED = "isSaved";
        public static final String TITLE = "title";
        public static final String TYPE = "type";
        public static final String UNIQUE_ID = "uniqueId";
    }

    public int getUniqueId() {
        return this.mUniqueId;
    }

    public void setUniqueId(int i) {
        this.mUniqueId = i;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public int getArticleId() {
        return this.mArticleId;
    }

    public void setArticleId(int i) {
        this.mArticleId = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public void setSaved(boolean z) {
        this.mIsSaved = z;
    }

    public boolean isSaved() {
        return this.mIsSaved;
    }

    public void setLocalXmlFilePath(String str) {
        this.localXMLPath = str;
    }

    public String getLocalXMLPath() {
        return this.localXMLPath;
    }

    public void setContent(ClinicalReferenceContent clinicalReferenceContent) {
        this.mContent = clinicalReferenceContent;
    }

    public ClinicalReferenceContent getContent() {
        return this.mContent;
    }

    public String toString() {
        return "ClinicalReferenceArticle" + "\nDatabase:" + "\nid:" + this.mUniqueId + "\ntitle:" + this.mTitle + "\nArticleId:" + this.mArticleId + "\nType:" + this.mType + "\nSaved:" + this.mIsSaved;
    }

    public boolean isSinglePageArticle() {
        ClinicalReferenceContent clinicalReferenceContent = this.mContent;
        if (clinicalReferenceContent == null || clinicalReferenceContent.sections == null || this.mContent.sections.size() != 1 || this.mContent.sections.get(0).subsections == null || this.mContent.sections.get(0).subsections.size() != 1) {
            return false;
        }
        return true;
    }
}
