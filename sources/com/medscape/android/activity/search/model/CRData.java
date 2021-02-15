package com.medscape.android.activity.search.model;

import com.wbmd.qxcalculator.model.db.DBContentItem;

public class CRData {
    private String calcId;
    private int cid;
    private int comboId;
    private DBContentItem contentItem;
    private int crType;
    private int drugId;
    private boolean externalSearchDriver = false;
    private int genericId;
    private boolean isInlineAD = false;
    private boolean saved;
    private SearchSuggestionMsg searchSuggestionMsg = null;
    private int source;
    private String title;
    private String type;

    public SearchSuggestionMsg getSearchSuggestionMsg() {
        return this.searchSuggestionMsg;
    }

    public void setSearchSuggestionMsg(SearchSuggestionMsg searchSuggestionMsg2) {
        this.searchSuggestionMsg = searchSuggestionMsg2;
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int i) {
        this.cid = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isSaved() {
        return this.saved;
    }

    public void setSaved(boolean z) {
        this.saved = z;
    }

    public void setComboId(int i) {
        this.comboId = i;
    }

    public int getComboId() {
        return this.comboId;
    }

    public void setDrugId(int i) {
        this.drugId = i;
    }

    public int getDrugId() {
        return this.drugId;
    }

    public void setGenericId(int i) {
        this.genericId = i;
    }

    public int getGenericId() {
        return this.genericId;
    }

    public void setCrType(int i) {
        this.crType = i;
    }

    public int getCrType() {
        return this.crType;
    }

    public String toString() {
        return "cid = " + this.cid + " type= " + this.type + " title = " + this.title;
    }

    public String getCalcId() {
        return this.calcId;
    }

    public void setCalcId(String str) {
        this.calcId = str;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int i) {
        this.source = i;
    }

    public boolean isExternalSearchDriver() {
        return this.externalSearchDriver;
    }

    public void setExternalSearchDriver(boolean z) {
        this.externalSearchDriver = z;
    }

    public boolean isInlineAD() {
        return this.isInlineAD;
    }

    public void setInlineAD(boolean z) {
        this.isInlineAD = z;
    }

    public DBContentItem getContentItem() {
        return this.contentItem;
    }

    public void setContentItem(DBContentItem dBContentItem) {
        this.contentItem = dBContentItem;
    }
}
