package com.qxmd.qxrecyclerview;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QxRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private static final String KEY_LOADING_MORE_TAG = "KEY_LOADING_MORE_TAG";
    public boolean handleExpandCollapse = true;
    private boolean hasBeenInitialized = false;
    private List<QxRecyclerViewHeaderItem> headerItems = new ArrayList();
    private boolean isEditing;
    private boolean isLoadingMore;
    private QxRecyclerViewRowItem loadingMoreRowItem;
    private OnRecyclerViewRowItemAccessoryViewClickedListener onRecyclerViewRowItemAccessoryViewClickedListener;
    private OnRecyclerViewRowItemChildItemClickedListener onRecyclerViewRowItemChildItemClickedListener;
    /* access modifiers changed from: private */
    public OnRecyclerViewRowItemClickedListener onRecyclerViewRowItemClickedListener;
    private OnRecyclerViewRowItemExpandCollapseListener onRecyclerViewRowItemExpandCollapseListener;
    protected OnRecyclerViewRowItemLongClickedListener onRecyclerViewRowItemLongClickedListener;
    public RecyclerView recyclerView;
    private List<Integer> sectionsCounts = new ArrayList();
    public boolean shouldDelaySelection = true;
    protected Map<Integer, Class<? extends QxRecyclerRowItemViewHolder>> viewTypeMap = new HashMap();
    protected List<QxRecyclerViewRowItemWrapper> wrappedRowItems = new ArrayList();

    public interface OnRecyclerViewRowItemAccessoryViewClickedListener {
        void onRecyclerViewRowItemAccessoryViewClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i);
    }

    public interface OnRecyclerViewRowItemChildItemClickedListener {
        void onRecyclerViewRowItemChildItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i, int i2, int i3);
    }

    public interface OnRecyclerViewRowItemClickedListener {
        void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i);
    }

    public interface OnRecyclerViewRowItemExpandCollapseListener {
        void onRecyclerViewRowItemCollapsed(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i);

        void onRecyclerViewRowItemExpanded(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i);
    }

    public interface OnRecyclerViewRowItemLongClickedListener {
        void onRecyclerViewRowItemLongClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
    }

    public void removeItems(int i, int i2) {
    }

    public void replaceRowItems(int i, int i2, List<? extends QxRecyclerViewRowItem> list) {
    }

    public QxRecyclerViewAdapter() {
        super.setHasStableIds(true);
    }

    public void setOnClickListener(OnRecyclerViewRowItemClickedListener onRecyclerViewRowItemClickedListener2) {
        this.onRecyclerViewRowItemClickedListener = onRecyclerViewRowItemClickedListener2;
    }

    public void setOnLongClickListener(OnRecyclerViewRowItemLongClickedListener onRecyclerViewRowItemLongClickedListener2) {
        this.onRecyclerViewRowItemLongClickedListener = onRecyclerViewRowItemLongClickedListener2;
    }

    public void setOnAccessoryViewClickListener(OnRecyclerViewRowItemAccessoryViewClickedListener onRecyclerViewRowItemAccessoryViewClickedListener2) {
        this.onRecyclerViewRowItemAccessoryViewClickedListener = onRecyclerViewRowItemAccessoryViewClickedListener2;
    }

    public void setOnChildClickListener(OnRecyclerViewRowItemChildItemClickedListener onRecyclerViewRowItemChildItemClickedListener2) {
        this.onRecyclerViewRowItemChildItemClickedListener = onRecyclerViewRowItemChildItemClickedListener2;
    }

    public void setOnExpandCollapseListener(OnRecyclerViewRowItemExpandCollapseListener onRecyclerViewRowItemExpandCollapseListener2) {
        this.onRecyclerViewRowItemExpandCollapseListener = onRecyclerViewRowItemExpandCollapseListener2;
    }

    public void setAdapterData(Map<String, List<? extends QxRecyclerViewRowItem>> map) {
        this.hasBeenInitialized = true;
        this.wrappedRowItems = new ArrayList();
        this.headerItems = new ArrayList();
        this.sectionsCounts = new ArrayList();
        this.viewTypeMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            addSectionWithTitle((String) next.getKey(), (List) next.getValue());
        }
    }

    public void setAdapterDataWithCustomHeaders(Map<QxRecyclerViewRowItem, List<? extends QxRecyclerViewRowItem>> map) {
        this.hasBeenInitialized = true;
        this.wrappedRowItems = new ArrayList();
        this.headerItems = new ArrayList();
        this.sectionsCounts = new ArrayList();
        this.viewTypeMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            addSectionWithHeaderItem((QxRecyclerViewRowItem) next.getKey(), (List) next.getValue());
        }
    }

    public void reset() {
        this.hasBeenInitialized = false;
        this.isLoadingMore = false;
        this.wrappedRowItems = new ArrayList();
        this.headerItems = new ArrayList();
        this.sectionsCounts = new ArrayList();
        this.viewTypeMap = new HashMap();
    }

    public void setIsEditing(boolean z) {
        if (z != this.isEditing) {
            this.isEditing = z;
            if (!z) {
                for (QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper : this.wrappedRowItems) {
                    qxRecyclerViewRowItemWrapper.rowItem.isEditModeSelected = false;
                }
                notifyDataSetChanged();
            }
        }
    }

    public boolean getIsEditing() {
        return this.isEditing;
    }

    public void setLoadingMoreRowItem(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        this.loadingMoreRowItem = qxRecyclerViewRowItem;
        qxRecyclerViewRowItem.internalTag = KEY_LOADING_MORE_TAG;
    }

    public QxRecyclerViewRowItem getLoadingMoreRowItem() {
        return this.loadingMoreRowItem;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
        r4 = getPositionOfLastRowItem();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setIsLoadingMore(boolean r4) {
        /*
            r3 = this;
            boolean r0 = r3.isLoadingMore
            if (r4 != r0) goto L_0x0005
            return
        L_0x0005:
            r3.isLoadingMore = r4
            boolean r0 = r3.hasBeenInitialized
            if (r0 == 0) goto L_0x005a
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = r3.loadingMoreRowItem
            if (r0 == 0) goto L_0x005a
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItemWrapper> r0 = r3.wrappedRowItems
            if (r0 == 0) goto L_0x005a
            r1 = 1
            if (r4 == 0) goto L_0x0035
            boolean r4 = r0.isEmpty()
            if (r4 == 0) goto L_0x001e
            r4 = 0
            goto L_0x0029
        L_0x001e:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItemWrapper> r4 = r3.wrappedRowItems
            int r4 = r4.size()
            int r4 = r4 - r1
            int r4 = r3.getSectionForRowItemAtPosition(r4)
        L_0x0029:
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = r3.loadingMoreRowItem
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItemWrapper> r1 = r3.wrappedRowItems
            int r1 = r1.size()
            r3.insertRowAtIndex(r0, r1, r4)
            goto L_0x005a
        L_0x0035:
            boolean r4 = r0.isEmpty()
            if (r4 != 0) goto L_0x005a
            int r4 = r3.getPositionOfLastRowItem()
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = r3.getItem(r4)
            if (r0 == 0) goto L_0x005a
            java.lang.String r2 = r0.internalTag
            if (r2 == 0) goto L_0x005a
            java.lang.String r0 = r0.internalTag
            java.lang.String r2 = "KEY_LOADING_MORE_TAG"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x005a
            int r0 = r3.getSectionForRowItemAtPosition(r4)
            r3.deleteRowsAtIndex(r4, r1, r0)
        L_0x005a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qxmd.qxrecyclerview.QxRecyclerViewAdapter.setIsLoadingMore(boolean):void");
    }

    public boolean getIsLoadingMore() {
        return this.isLoadingMore;
    }

    public boolean getHasBeenInitialized() {
        return this.hasBeenInitialized;
    }

    public void addSectionWithTitle(String str, List<? extends QxRecyclerViewRowItem> list) {
        addSectionWithHeaderItem(new QxRecyclerViewHeaderItem(str), list);
    }

    public List<QxRecyclerViewRowItem> getRowItems() {
        ArrayList arrayList = new ArrayList();
        for (QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper : this.wrappedRowItems) {
            arrayList.add(qxRecyclerViewRowItemWrapper.rowItem);
        }
        return arrayList;
    }

    public List<QxRecyclerViewRowItem> getTopLevelRowItems() {
        ArrayList arrayList = new ArrayList();
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (!(next.rowItem instanceof QxRecyclerViewHeaderItem) && next.rowItem.indentLevel == 0) {
                arrayList.add(next.rowItem);
            }
        }
        return arrayList;
    }

    public int getSectionCount() {
        return this.sectionsCounts.size();
    }

    public void removeDataFromSection(int i) {
        int i2 = i + 1;
        int itemCountForSection = getItemCountForSection(i);
        int i3 = 0;
        while (true) {
            if (i3 >= this.wrappedRowItems.size()) {
                i3 = 0;
                break;
            } else if (this.wrappedRowItems.get(i3).indexPath.section == i) {
                break;
            } else {
                i3++;
            }
        }
        for (int i4 = 0; i4 < itemCountForSection; i4++) {
            this.wrappedRowItems.remove(i3);
        }
        this.sectionsCounts.remove(i);
        if (i2 < this.sectionsCounts.size()) {
            while (i3 < this.wrappedRowItems.size()) {
                QxIndexPath qxIndexPath = this.wrappedRowItems.get(i3).indexPath;
                qxIndexPath.section--;
                i3++;
            }
        }
    }

    public int getItemCountForSection(int i) {
        return this.sectionsCounts.get(i).intValue() + 1;
    }

    public void addSectionWithHeaderItem(QxRecyclerViewRowItem qxRecyclerViewRowItem, List<? extends QxRecyclerViewRowItem> list) {
        this.hasBeenInitialized = true;
        ArrayList arrayList = new ArrayList(list.size());
        QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper = new QxRecyclerViewRowItemWrapper(qxRecyclerViewRowItem);
        qxRecyclerViewRowItemWrapper.indexPath.section = this.sectionsCounts.size();
        qxRecyclerViewRowItemWrapper.indexPath.row = -1;
        this.wrappedRowItems.add(qxRecyclerViewRowItemWrapper);
        arrayList.add(qxRecyclerViewRowItemWrapper);
        int resourceId = qxRecyclerViewRowItem.getResourceId();
        if (!this.viewTypeMap.containsKey(Integer.valueOf(resourceId))) {
            this.viewTypeMap.put(Integer.valueOf(resourceId), qxRecyclerViewRowItem.getViewHolderClass());
        }
        int i = 0;
        for (QxRecyclerViewRowItem qxRecyclerViewRowItem2 : list) {
            QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper2 = new QxRecyclerViewRowItemWrapper(qxRecyclerViewRowItem2);
            qxRecyclerViewRowItemWrapper2.indexPath.section = this.sectionsCounts.size();
            qxRecyclerViewRowItemWrapper2.indexPath.row = i;
            this.wrappedRowItems.add(qxRecyclerViewRowItemWrapper2);
            arrayList.add(qxRecyclerViewRowItemWrapper2);
            int resourceId2 = qxRecyclerViewRowItem2.getResourceId();
            if (!this.viewTypeMap.containsKey(Integer.valueOf(resourceId2))) {
                this.viewTypeMap.put(Integer.valueOf(resourceId2), qxRecyclerViewRowItem2.getViewHolderClass());
            }
            i++;
        }
        updateRowPositionsForSectionItems(arrayList);
        this.sectionsCounts.add(Integer.valueOf(list.size()));
    }

    private void updateRowPositionsForSectionItems(List<QxRecyclerViewRowItemWrapper> list) {
        int i = 0;
        for (QxRecyclerViewRowItemWrapper next : list) {
            if (list.size() == 2) {
                next.rowPosition = QxRecyclerViewRowItem.RowPosition.SINGLE;
            } else if (i == list.size() - 1) {
                next.rowPosition = QxRecyclerViewRowItem.RowPosition.BOTTOM;
            } else if (i == 1) {
                next.rowPosition = QxRecyclerViewRowItem.RowPosition.TOP;
            } else {
                next.rowPosition = QxRecyclerViewRowItem.RowPosition.MIDDLE;
            }
            i++;
        }
    }

    public List<QxRecyclerViewRowItemWrapper> insertRowAtIndex(QxRecyclerViewRowItem qxRecyclerViewRowItem, int i, int i2) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(qxRecyclerViewRowItem);
        return insertRowsAtIndex(arrayList, i, i2);
    }

    public List<QxRecyclerViewRowItemWrapper> insertSectionAtIndex(QxRecyclerViewRowItem qxRecyclerViewRowItem, List<QxRecyclerViewRowItem> list, int i, int i2) {
        if (i < 0) {
            return null;
        }
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (next.indexPath.section >= i2) {
                next.indexPath.section++;
            }
        }
        ArrayList arrayList = new ArrayList(list.size());
        QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper = new QxRecyclerViewRowItemWrapper(qxRecyclerViewRowItem);
        qxRecyclerViewRowItemWrapper.indexPath.section = i2;
        qxRecyclerViewRowItemWrapper.indexPath.row = -1;
        this.wrappedRowItems.add(qxRecyclerViewRowItemWrapper);
        arrayList.add(qxRecyclerViewRowItemWrapper);
        int resourceId = qxRecyclerViewRowItem.getResourceId();
        if (!this.viewTypeMap.containsKey(Integer.valueOf(resourceId))) {
            this.viewTypeMap.put(Integer.valueOf(resourceId), qxRecyclerViewRowItem.getViewHolderClass());
        }
        this.sectionsCounts.add(i2, 0);
        notifyItemRangeInserted(i, 1);
        return insertRowsAtIndex(list, i + 1, i2);
    }

    public int getPositionOfLastRowItem() {
        return this.wrappedRowItems.size() - 1;
    }

    public List<QxRecyclerViewRowItemWrapper> appendRows(List<? extends QxRecyclerViewRowItem> list) {
        int size = this.wrappedRowItems.size();
        return insertRowsAtIndex(list, size, getSectionForRowItemAtPosition(size - 1));
    }

    public List<QxRecyclerViewRowItemWrapper> insertRowsAtIndex(List<? extends QxRecyclerViewRowItem> list, int i, int i2) {
        if (i < 0) {
            return null;
        }
        if (i < this.wrappedRowItems.size()) {
            int i3 = this.wrappedRowItems.get(i).indexPath.section;
            if (i3 != i2) {
                if (i > 0) {
                    int i4 = this.wrappedRowItems.get(i - 1).indexPath.section;
                    if (i2 >= i3 || i2 < i4) {
                        throw new IllegalArgumentException("Incorrect section in insertRowsAtIndex");
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect section in insertRowsAtIndex");
                }
            }
        } else if (i != this.wrappedRowItems.size()) {
            throw new IllegalArgumentException("Insertion index " + i + " cannot be greater than list size " + this.wrappedRowItems.size());
        } else if (i2 > this.sectionsCounts.size()) {
            throw new IllegalArgumentException("Incorrect section in insertRowsAtIndex: cannot insert to section beyond section count");
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (QxRecyclerViewRowItem qxRecyclerViewRowItem : list) {
            QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper = new QxRecyclerViewRowItemWrapper(qxRecyclerViewRowItem);
            qxRecyclerViewRowItemWrapper.indexPath.section = i2;
            int resourceId = qxRecyclerViewRowItem.getResourceId();
            if (!this.viewTypeMap.containsKey(Integer.valueOf(resourceId))) {
                this.viewTypeMap.put(Integer.valueOf(resourceId), qxRecyclerViewRowItem.getViewHolderClass());
            }
            arrayList.add(qxRecyclerViewRowItemWrapper);
        }
        int intValue = (i2 == this.sectionsCounts.size() ? 0 : this.sectionsCounts.get(i2).intValue()) + list.size();
        this.wrappedRowItems.addAll(i, arrayList);
        if (i2 == this.sectionsCounts.size()) {
            this.sectionsCounts.add(Integer.valueOf(intValue));
        } else {
            this.sectionsCounts.set(i2, Integer.valueOf(intValue));
        }
        updateRowPositionsForSectionItems(getWrappedRowItemsForSection(i2));
        notifyItemRangeInserted(i, arrayList.size());
        return arrayList;
    }

    public void deleteSectionAtIndex(int i, int i2) {
        deleteRowsAtIndex(i, this.sectionsCounts.get(i2).intValue() + 1, i2);
        this.sectionsCounts.remove(i2);
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (next.indexPath.section >= i2) {
                QxIndexPath qxIndexPath = next.indexPath;
                qxIndexPath.section--;
            }
        }
    }

    public void deleteRowsAtIndex(int i, int i2, int i3) {
        int i4 = i + i2;
        try {
            if (i4 <= this.wrappedRowItems.size()) {
                if (this.wrappedRowItems.get(i).indexPath.section == this.wrappedRowItems.get(i4 - 1).indexPath.section) {
                    for (int i5 = 0; i5 < i2; i5++) {
                        this.wrappedRowItems.remove(i);
                    }
                    this.sectionsCounts.set(i3, Integer.valueOf(this.sectionsCounts.get(i3).intValue() - i2));
                    updateRowPositionsForSectionItems(getWrappedRowItemsForSection(i3));
                    notifyItemRangeRemoved(i, i2);
                    return;
                }
                throw new IllegalArgumentException("Can't delete rows across multiple sections");
            }
            throw new IllegalArgumentException("length of items to delete exceeds rowItems size");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void expandChildrenContainedInExpandedIds(List<QxRecyclerViewRowItemWrapper> list, List<String> list2) {
        if (list2 != null && !list2.isEmpty()) {
            if (list == null) {
                list = new ArrayList<>(this.wrappedRowItems);
            }
            for (QxRecyclerViewRowItemWrapper next : list) {
                if (list2.contains(next.rowItem.getId())) {
                    if (next.rowItem.isExpanded) {
                        next.rowItem.isExpanded = false;
                        expandRowItemAtPosition(this.wrappedRowItems.indexOf(next));
                    } else {
                        List<QxRecyclerViewRowItemWrapper> expandRowItemAtPosition = expandRowItemAtPosition(this.wrappedRowItems.indexOf(next));
                        if (expandRowItemAtPosition != null) {
                            expandChildrenContainedInExpandedIds(expandRowItemAtPosition, list2);
                        }
                    }
                } else if (next.rowItem.isExpanded) {
                    next.rowItem.isExpanded = false;
                    collapseRowItemAtPosition(this.wrappedRowItems.indexOf(next));
                }
            }
        }
    }

    public List<QxRecyclerViewRowItemWrapper> expandRowItemAtPosition(int i) {
        QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper = this.wrappedRowItems.get(i);
        List<QxRecyclerViewRowItemWrapper> list = null;
        if (qxRecyclerViewRowItemWrapper.rowItem.isExpanded) {
            return null;
        }
        List<QxRecyclerViewRowItem> findRowsToBeInsertedForExpandingRowItem = findRowsToBeInsertedForExpandingRowItem(qxRecyclerViewRowItemWrapper.rowItem);
        qxRecyclerViewRowItemWrapper.rowItem.isExpanded = true;
        if (findRowsToBeInsertedForExpandingRowItem != null && !findRowsToBeInsertedForExpandingRowItem.isEmpty()) {
            list = insertRowsAtIndex(findRowsToBeInsertedForExpandingRowItem, i + 1, qxRecyclerViewRowItemWrapper.indexPath.section);
        }
        notifyItemChanged(i);
        return list;
    }

    public List<QxRecyclerViewRowItem> findRowsToBeInsertedForExpandingRowItem(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        if (qxRecyclerViewRowItem.children == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < qxRecyclerViewRowItem.children.size(); i++) {
            QxRecyclerViewRowItem qxRecyclerViewRowItem2 = qxRecyclerViewRowItem.children.get(i);
            arrayList.add(qxRecyclerViewRowItem2);
            if (qxRecyclerViewRowItem2.hasChildren() && qxRecyclerViewRowItem2.isExpanded) {
                arrayList.addAll(findRowsToBeInsertedForExpandingRowItem(qxRecyclerViewRowItem2));
            }
        }
        return arrayList;
    }

    public void collapseRowItemAtPosition(int i) {
        QxRecyclerViewRowItem item = getItem(i);
        if (item.isExpanded) {
            int calculateNumberOfRowsToBeDeletedForCollapsingRowItem = calculateNumberOfRowsToBeDeletedForCollapsingRowItem(item);
            item.isExpanded = false;
            if (calculateNumberOfRowsToBeDeletedForCollapsingRowItem != 0) {
                deleteRowsAtIndex(i + 1, calculateNumberOfRowsToBeDeletedForCollapsingRowItem, getSectionForRowItemAtPosition(i));
            }
            notifyItemChanged(i);
        }
    }

    public int calculateNumberOfRowsToBeDeletedForCollapsingRowItem(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        if (qxRecyclerViewRowItem.children == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < qxRecyclerViewRowItem.children.size(); i2++) {
            i++;
            QxRecyclerViewRowItem qxRecyclerViewRowItem2 = qxRecyclerViewRowItem.children.get(i2);
            if (qxRecyclerViewRowItem2.hasChildren() && qxRecyclerViewRowItem2.isExpanded) {
                i += calculateNumberOfRowsToBeDeletedForCollapsingRowItem(qxRecyclerViewRowItem2);
            }
        }
        return i;
    }

    public int getPositionOfNextRowItemAtSameIndent(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        short s = qxRecyclerViewRowItem.indentLevel;
        int positionForRowItem = getPositionForRowItem(qxRecyclerViewRowItem);
        while (true) {
            positionForRowItem++;
            if (positionForRowItem >= this.wrappedRowItems.size()) {
                positionForRowItem = -1;
                break;
            }
            QxRecyclerViewRowItem qxRecyclerViewRowItem2 = this.wrappedRowItems.get(positionForRowItem).rowItem;
            if (!(qxRecyclerViewRowItem2 instanceof QxRecyclerViewHeaderItem) && qxRecyclerViewRowItem2.indentLevel == s) {
                break;
            }
        }
        return positionForRowItem == -1 ? getItemCount() : positionForRowItem;
    }

    public int getPositionForRowItem(QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        if (qxRecyclerViewRowItem == null) {
            return -1;
        }
        int i = 0;
        for (QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper : this.wrappedRowItems) {
            if (qxRecyclerViewRowItemWrapper.rowItem == qxRecyclerViewRowItem) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getSectionForRowItemAtPosition(int i) {
        return this.wrappedRowItems.get(i).indexPath.section;
    }

    public int getSectionRowIndexForRowItemAtPosition(int i) {
        return this.wrappedRowItems.get(i).indexPath.row;
    }

    public List<QxRecyclerViewRowItem> getRowItemsInSection(int i) {
        ArrayList arrayList = new ArrayList();
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (next.indexPath.section == i) {
                arrayList.add(next.rowItem);
            }
        }
        return arrayList;
    }

    public QxRecyclerViewRowItem getItem(int i) {
        if (i < this.wrappedRowItems.size()) {
            return this.wrappedRowItems.get(i).rowItem;
        }
        return null;
    }

    public int getEditModeSelectionCount() {
        int i = 0;
        for (QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper : this.wrappedRowItems) {
            if (qxRecyclerViewRowItemWrapper.rowItem.isEditModeSelected) {
                i++;
            }
        }
        return i;
    }

    public List<QxRecyclerViewRowItem> getEditModeSelectedRowItems() {
        ArrayList arrayList = new ArrayList();
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (next.rowItem.isEditModeSelected) {
                arrayList.add(next.rowItem);
            }
        }
        return arrayList;
    }

    public List<QxRecyclerViewRowItem> getSelectedRowItems() {
        ArrayList arrayList = new ArrayList();
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (next.rowItem.isSelected) {
                arrayList.add(next.rowItem);
            }
        }
        return arrayList;
    }

    public void markAllItemsSelected() {
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (!(next.rowItem instanceof QxRecyclerViewHeaderItem)) {
                next.rowItem.isSelected = true;
            }
        }
    }

    private List<QxRecyclerViewRowItemWrapper> getWrappedRowItemsForSection(int i) {
        ArrayList arrayList = new ArrayList(this.wrappedRowItems.size());
        for (QxRecyclerViewRowItemWrapper next : this.wrappedRowItems) {
            if (next.indexPath.section == i) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        inflate.setOnClickListener(this);
        if (this.onRecyclerViewRowItemLongClickedListener != null) {
            inflate.setOnLongClickListener(this);
        }
        return constructViewHolderClass(inflate, i);
    }

    public QxRecyclerRowItemViewHolder constructViewHolderClass(View view, int i) {
        try {
            return (QxRecyclerRowItemViewHolder) this.viewTypeMap.get(Integer.valueOf(i)).getConstructor(new Class[]{View.class}).newInstance(new Object[]{view});
        } catch (Exception unused) {
            throw new IllegalArgumentException("Can't find ViewHolder class ");
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper = this.wrappedRowItems.get(i);
        qxRecyclerViewRowItemWrapper.rowItem.onBindData(viewHolder, i, qxRecyclerViewRowItemWrapper.indexPath, qxRecyclerViewRowItemWrapper.rowPosition, this);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView2) {
        super.onAttachedToRecyclerView(recyclerView2);
        this.recyclerView = recyclerView2;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView2) {
        super.onDetachedFromRecyclerView(recyclerView2);
        this.recyclerView = null;
    }

    public void onChildItemClicked(View view, int i, int i2, int i3) {
        if (this.onRecyclerViewRowItemChildItemClickedListener != null) {
            this.onRecyclerViewRowItemChildItemClickedListener.onRecyclerViewRowItemChildItemClicked(getItem(i), this, view, i, i2, i3);
        }
    }

    public void onAccessoryViewClicked(View view, int i) {
        if (this.onRecyclerViewRowItemAccessoryViewClickedListener != null) {
            this.onRecyclerViewRowItemAccessoryViewClickedListener.onRecyclerViewRowItemAccessoryViewClicked(getItem(i), this, view, i);
        }
    }

    public void onClick(final View view) {
        final int childAdapterPosition = this.recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition != -1) {
            final QxRecyclerViewRowItem item = getItem(childAdapterPosition);
            if (!(item instanceof QxRecyclerViewHeaderItem)) {
                if (item.children == null || item.children.isEmpty() || !this.handleExpandCollapse) {
                    OnRecyclerViewRowItemClickedListener onRecyclerViewRowItemClickedListener2 = this.onRecyclerViewRowItemClickedListener;
                    if (onRecyclerViewRowItemClickedListener2 == null) {
                        return;
                    }
                    if (this.shouldDelaySelection) {
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                QxRecyclerViewAdapter.this.onRecyclerViewRowItemClickedListener.onRecyclerViewRowItemClicked(item, QxRecyclerViewAdapter.this, view, childAdapterPosition);
                            }
                        }, 150);
                    } else {
                        onRecyclerViewRowItemClickedListener2.onRecyclerViewRowItemClicked(item, this, view, childAdapterPosition);
                    }
                } else if (item.isExpanded) {
                    collapseRowItemAtPosition(childAdapterPosition);
                    OnRecyclerViewRowItemExpandCollapseListener onRecyclerViewRowItemExpandCollapseListener2 = this.onRecyclerViewRowItemExpandCollapseListener;
                    if (onRecyclerViewRowItemExpandCollapseListener2 != null) {
                        onRecyclerViewRowItemExpandCollapseListener2.onRecyclerViewRowItemCollapsed(item, this, view, childAdapterPosition);
                    }
                } else {
                    expandRowItemAtPosition(childAdapterPosition);
                    if (view.getTop() > Math.round(((float) this.recyclerView.getHeight()) / 2.0f)) {
                        this.recyclerView.smoothScrollBy(0, view.getTop() - Math.round(((float) this.recyclerView.getHeight()) / 2.0f));
                    }
                    OnRecyclerViewRowItemExpandCollapseListener onRecyclerViewRowItemExpandCollapseListener3 = this.onRecyclerViewRowItemExpandCollapseListener;
                    if (onRecyclerViewRowItemExpandCollapseListener3 != null) {
                        onRecyclerViewRowItemExpandCollapseListener3.onRecyclerViewRowItemExpanded(item, this, view, childAdapterPosition);
                    }
                }
            }
        }
    }

    public boolean onLongClick(View view) {
        int childAdapterPosition = this.recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == -1) {
            return false;
        }
        QxRecyclerViewRowItem item = getItem(childAdapterPosition);
        if (item instanceof QxRecyclerViewHeaderItem) {
            return false;
        }
        this.onRecyclerViewRowItemLongClickedListener.onRecyclerViewRowItemLongClicked(item, this, view, childAdapterPosition);
        return true;
    }

    public long getItemId(int i) {
        List<QxRecyclerViewRowItemWrapper> list = this.wrappedRowItems;
        if (list == null) {
            return 0;
        }
        return list.get(i).rowItem.getItemId();
    }

    public int getItemViewType(int i) {
        return this.wrappedRowItems.get(i).rowItem.getResourceId();
    }

    public int getItemCount() {
        List<QxRecyclerViewRowItemWrapper> list = this.wrappedRowItems;
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
