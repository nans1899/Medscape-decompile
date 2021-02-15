package com.qxmd.qxrecyclerview;

import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;

public class QxRecyclerViewRowItemWrapper {
    public QxIndexPath indexPath = new QxIndexPath();
    public QxRecyclerViewRowItem rowItem;
    public QxRecyclerViewRowItem.RowPosition rowPosition;

    public QxRecyclerViewRowItemWrapper(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        this.rowItem = qxRecyclerViewRowItem;
    }
}
