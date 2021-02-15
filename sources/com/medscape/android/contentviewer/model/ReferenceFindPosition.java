package com.medscape.android.contentviewer.model;

public class ReferenceFindPosition {
    public int contentIndex = -1;
    public int contentRow = -1;
    public int contentTableColumn = -1;
    public boolean isDetailText = false;

    public int getContentRow() {
        return this.contentRow;
    }

    public void setContentRow(int i) {
        this.contentRow = i;
    }

    public int getContentIndex() {
        return this.contentIndex;
    }

    public void setContentIndex(int i) {
        this.contentIndex = i;
    }

    public int getContentTableColumn() {
        return this.contentTableColumn;
    }

    public void setContentTableColumn(int i) {
        this.contentTableColumn = i;
    }

    public boolean isDetailText() {
        return this.isDetailText;
    }

    public void setDetailText(boolean z) {
        this.isDetailText = z;
    }
}
