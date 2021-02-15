package com.medscape.android.activity.interactions.models;

public class InteractionsMenuOptions {
    private boolean isCloseVisible = false;
    private boolean isOpenMenuBlack = false;
    private boolean isOpenVisible = true;
    private boolean isSaveBlack = false;
    private boolean isSaveVisible = true;
    private String openMenuTitle = "Open Saved List";

    public boolean isCloseVisible() {
        return this.isCloseVisible;
    }

    public void setCloseVisible(boolean z) {
        this.isCloseVisible = z;
    }

    public boolean isOpenVisible() {
        return this.isOpenVisible;
    }

    public void setOpenVisible(boolean z) {
        this.isOpenVisible = z;
    }

    public boolean isSaveVisible() {
        return this.isSaveVisible;
    }

    public void setSaveVisible(boolean z) {
        this.isSaveVisible = z;
    }

    public String getOpenMenuTitle() {
        return this.openMenuTitle;
    }

    public void setOpenMenuTitle(String str) {
        this.openMenuTitle = str;
    }

    public boolean isSaveBlack() {
        return this.isSaveBlack;
    }

    public void setSaveBlack(boolean z) {
        this.isSaveBlack = z;
    }

    public boolean isOpenMenuBlack() {
        return this.isOpenMenuBlack;
    }

    public void setOpenMenuBlack(boolean z) {
        this.isOpenMenuBlack = z;
    }
}
