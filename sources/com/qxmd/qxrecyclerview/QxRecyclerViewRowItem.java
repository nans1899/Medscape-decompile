package com.qxmd.qxrecyclerview;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class QxRecyclerViewRowItem {
    public short childIndex;
    public List<QxRecyclerViewRowItem> children;
    public short indentLevel;
    public String internalTag;
    public boolean isEditModeSelected = false;
    public boolean isEditable = false;
    public boolean isExpanded;
    public boolean isSelected = false;
    public QxRecyclerViewRowItem parentItem;
    public short sortingWeight;
    public String tag;

    public enum RowPosition {
        SINGLE,
        TOP,
        MIDDLE,
        BOTTOM
    }

    public static int getMaxIndentLevel() {
        return -1;
    }

    public String getId() {
        return "";
    }

    public String getTitle() {
        return null;
    }

    public Class<? extends QxRecyclerRowItemViewHolder> getViewHolderClass() {
        return null;
    }

    public void insertChildren(int i, List<QxRecyclerViewRowItem> list) {
    }

    public void onBindData(RecyclerView.ViewHolder viewHolder, int i, QxIndexPath qxIndexPath, RowPosition rowPosition, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
    }

    public boolean hasChildren() {
        List<QxRecyclerViewRowItem> list = this.children;
        return list != null && !list.isEmpty();
    }

    public long getItemId() {
        return (long) super.hashCode();
    }

    public void addChild(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        if (this.children == null) {
            this.children = new ArrayList(25);
        }
        while (qxRecyclerViewRowItem.indentLevel <= this.indentLevel) {
            qxRecyclerViewRowItem.increaseIndent();
        }
        qxRecyclerViewRowItem.childIndex = (short) this.children.size();
        this.children.add(qxRecyclerViewRowItem);
        qxRecyclerViewRowItem.parentItem = this;
    }

    public void insertChild(int i, QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        if (this.children == null) {
            this.children = new ArrayList(20);
        }
        while (qxRecyclerViewRowItem.indentLevel <= this.indentLevel) {
            qxRecyclerViewRowItem.increaseIndent();
        }
        qxRecyclerViewRowItem.childIndex = (short) i;
        if (i < this.children.size()) {
            for (int i2 = i; i2 < this.children.size(); i2++) {
                QxRecyclerViewRowItem qxRecyclerViewRowItem2 = this.children.get(i2);
                qxRecyclerViewRowItem2.childIndex = (short) (qxRecyclerViewRowItem2.childIndex + 1);
            }
        }
        this.children.add(i, qxRecyclerViewRowItem);
        qxRecyclerViewRowItem.parentItem = this;
    }

    public void deleteChild(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        this.children.remove(qxRecyclerViewRowItem);
        for (int indexOf = this.children.indexOf(qxRecyclerViewRowItem); indexOf < this.children.size(); indexOf++) {
            QxRecyclerViewRowItem qxRecyclerViewRowItem2 = this.children.get(indexOf);
            qxRecyclerViewRowItem2.childIndex = (short) (qxRecyclerViewRowItem2.childIndex - 1);
        }
    }

    public void increaseIndent() {
        this.indentLevel = (short) (this.indentLevel + 1);
        List<QxRecyclerViewRowItem> list = this.children;
        if (list != null) {
            for (QxRecyclerViewRowItem increaseIndent : list) {
                increaseIndent.increaseIndent();
            }
        }
    }

    public void replaceChildAtIndex(int i, QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        List<QxRecyclerViewRowItem> list = this.children;
        if (list != null && i < list.size()) {
            QxRecyclerViewRowItem qxRecyclerViewRowItem2 = this.children.get(i);
            qxRecyclerViewRowItem.indentLevel = qxRecyclerViewRowItem2.indentLevel;
            qxRecyclerViewRowItem.childIndex = qxRecyclerViewRowItem2.childIndex;
            this.children.set(i, qxRecyclerViewRowItem);
        }
    }

    public int getResourceId() {
        return R.layout.list_item_default;
    }
}
