package com.webmd.wbmdcmepulse.models.articles;

import java.util.ArrayList;
import java.util.List;

public class HtmlTableRow {
    public static int ROW_NUMBER_HEADER = 0;
    public static final String ROW_TYPE_TABLE_HEADER = "th";
    public static final String Row_TYPE_TABLE_DATA = "td";
    private List<HtmlRowItem> mRowItems = new ArrayList();
    private int mRowNumber;
    private String mRowType;

    public HtmlTableRow(int i, String str) {
        if (i == ROW_NUMBER_HEADER || !str.equals(ROW_TYPE_TABLE_HEADER)) {
            this.mRowNumber = i;
            this.mRowType = str;
            return;
        }
        throw new UnsupportedOperationException("rows of type header must have a row number of zero");
    }

    public String getRowType() {
        return this.mRowType;
    }

    public int getRowNumber() {
        if (this.mRowType.equals(ROW_TYPE_TABLE_HEADER)) {
            return ROW_NUMBER_HEADER;
        }
        return this.mRowNumber;
    }

    public void addRowItem(String str) {
        HtmlRowItem htmlRowItem = new HtmlRowItem();
        htmlRowItem.column = this.mRowItems.size();
        htmlRowItem.row = this.mRowNumber;
        htmlRowItem.value = str;
        this.mRowItems.add(htmlRowItem);
    }

    public List<HtmlRowItem> getRowItems() {
        return this.mRowItems;
    }
}
