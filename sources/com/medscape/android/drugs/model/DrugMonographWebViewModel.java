package com.medscape.android.drugs.model;

import com.medscape.android.contentviewer.DividerLineItem;

public class DrugMonographWebViewModel {
    DividerLineItem[] dividerLineItems;
    boolean isLoadFromTop;
    boolean isLoadFromUrl;
    boolean isNextPageAvailable;
    boolean isPreviousPageAvaialable;
    boolean progressBarVisibility;
    String uri;

    public boolean isLoadFromUrl() {
        return this.isLoadFromUrl;
    }

    public void setLoadFromUrl(boolean z) {
        this.isLoadFromUrl = z;
    }

    public boolean isLoadFromTop() {
        return this.isLoadFromTop;
    }

    public void setLoadFromTop(boolean z) {
        this.isLoadFromTop = z;
    }

    public DividerLineItem[] getDividerLineItems() {
        return this.dividerLineItems;
    }

    public void setDividerLineItems(DividerLineItem[] dividerLineItemArr) {
        this.dividerLineItems = dividerLineItemArr;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public boolean isProgressBarVisibility() {
        return this.progressBarVisibility;
    }

    public void setProgressBarVisibility(boolean z) {
        this.progressBarVisibility = z;
    }

    public boolean isNextPageAvailable() {
        return this.isNextPageAvailable;
    }

    public void setNextPageAvailable(boolean z) {
        this.isNextPageAvailable = z;
    }

    public boolean isPreviousPageAvaialable() {
        return this.isPreviousPageAvaialable;
    }

    public void setPreviousPageAvaialable(boolean z) {
        this.isPreviousPageAvaialable = z;
    }
}
