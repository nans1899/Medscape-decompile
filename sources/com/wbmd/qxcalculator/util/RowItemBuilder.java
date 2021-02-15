package com.wbmd.qxcalculator.util;

import android.content.Context;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.calcList.CalcListType;
import com.wbmd.qxcalculator.model.calcList.CalcListUtils;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBCategory;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAd;
import com.wbmd.qxcalculator.model.db.DBFilter;
import com.wbmd.qxcalculator.model.db.DBPlatform;
import com.wbmd.qxcalculator.model.db.DBPromotion;
import com.wbmd.qxcalculator.model.db.DBSpecialty;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.rowItems.FeaturedContentRowItem;
import com.wbmd.qxcalculator.model.rowItems.GroupRowItem;
import com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RowItemBuilder {
    private static final int K_MAX_RECENTS_COUNT = 15;
    private static RowItemBuilder mInstance;
    private List<QxRecyclerViewRowItem> allCalcRowItems;
    private OnRowItemsChangedListener allCalcRowItemsChangedListener;
    private CalcListType calcListTypeForFeaturedContentAd;
    private Context context;
    private OnRowItemsChangedListener favoriteCalcRowItemsChangedListener;
    private FeaturedContentRowItem featuredContentRowItem;
    /* access modifiers changed from: private */
    public QxRecyclerViewRowItem generalGroupedCalcRowItems;
    /* access modifiers changed from: private */
    public List<QxRecyclerViewRowItem> groupedCalcRowItems;
    private OnRowItemsChangedListener groupedCalcRowItemsChangedListener;
    private OnRowItemsChangedListener recentCalcRowItemsChangedListener;
    private boolean rowItemCacheBuilt;
    private boolean showFeaturedContentRowItem = true;
    /* access modifiers changed from: private */
    public QxRecyclerViewRowItem usersGroupedCalcRowItems;

    public interface OnRowItemsChangedListener {
        QxRecyclerViewAdapter getAdapter();
    }

    public static RowItemBuilder getInstance() {
        if (mInstance == null) {
            mInstance = new RowItemBuilder();
        }
        return mInstance;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public void setGroupedCalcRowItemsChangedListener(OnRowItemsChangedListener onRowItemsChangedListener) {
        this.groupedCalcRowItemsChangedListener = onRowItemsChangedListener;
    }

    public OnRowItemsChangedListener getGroupedCalcRowItemsChangedListener() {
        return this.groupedCalcRowItemsChangedListener;
    }

    public void setAllCalcRowItemsChangedListener(OnRowItemsChangedListener onRowItemsChangedListener) {
        this.allCalcRowItemsChangedListener = onRowItemsChangedListener;
    }

    public void setRecentCalcRowItemsChangedListener(OnRowItemsChangedListener onRowItemsChangedListener) {
        this.recentCalcRowItemsChangedListener = onRowItemsChangedListener;
    }

    public void setFavoriteCalcRowItemsChangedListener(OnRowItemsChangedListener onRowItemsChangedListener) {
        this.favoriteCalcRowItemsChangedListener = onRowItemsChangedListener;
    }

    public OnRowItemsChangedListener getAllCalcRowItemsChangedListener() {
        return this.allCalcRowItemsChangedListener;
    }

    public OnRowItemsChangedListener getRecentCalcRowItemsChangedListener() {
        return this.recentCalcRowItemsChangedListener;
    }

    public OnRowItemsChangedListener getFavoriteCalcRowItemsChangedListener() {
        return this.favoriteCalcRowItemsChangedListener;
    }

    public void setCalcListTypeForFeaturedContentAd(CalcListType calcListType) {
        this.calcListTypeForFeaturedContentAd = calcListType;
    }

    public CalcListType getCalcListTypeForFeaturedContentAd() {
        if (this.calcListTypeForFeaturedContentAd == null) {
            this.calcListTypeForFeaturedContentAd = CalcListUtils.getCalcListTypeForTabPosition(CalcListUtils.getTabIndexForTabId(UserManager.getInstance().getDbUser().getLastUsedTabId()));
        }
        return this.calcListTypeForFeaturedContentAd;
    }

    public synchronized FeaturedContentRowItem getFeaturedContentRowItem() {
        DBFeaturedContentAd dBFeaturedContentAd;
        if (this.featuredContentRowItem == null) {
            DBUser dbUser = UserManager.getInstance().getDbUser();
            if (!dbUser.getIsPersonalizationEnabled()) {
                return null;
            }
            List<DBContentItem> contentItemsAndFetchRelations = dbUser.getContentItemsAndFetchRelations();
            ArrayList<DBContentItem> arrayList = new ArrayList<>(contentItemsAndFetchRelations.size());
            for (DBContentItem next : contentItemsAndFetchRelations) {
                if (next.getFeaturedContentAds() != null && !next.getFeaturedContentAds().isEmpty() && DBContentItem.shouldIncludeItem(next)) {
                    if ((next.getRequiresUpdate() == null || !next.getRequiresUpdate().booleanValue()) && (next.getResourcesRequireUpdate() == null || !next.getResourcesRequireUpdate().booleanValue())) {
                        arrayList.add(next);
                    }
                }
            }
            ArrayList<DBFeaturedContentAd> arrayList2 = new ArrayList<>(arrayList.size());
            for (DBContentItem dBContentItem : arrayList) {
                if (dBContentItem.getFeaturedContentAds() != null) {
                    for (DBFeaturedContentAd next2 : dBContentItem.getFeaturedContentAds()) {
                        if (next2 != null && DBPlatform.shouldIncludePlatforms(next2.getPlatforms()) && DBFilter.shouldIncludeFilters(next2.getFilters())) {
                            arrayList2.add(next2);
                        }
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                double nextDouble = new Random().nextDouble() * 100.0d;
                double d = 0.0d;
                double d2 = 0.0d;
                for (DBFeaturedContentAd dBFeaturedContentAd2 : arrayList2) {
                    if (dBFeaturedContentAd2.getDisplayFrequency() != null) {
                        if (dBFeaturedContentAd2.getDisplayFrequency().doubleValue() != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                            d += dBFeaturedContentAd2.getDisplayFrequency().doubleValue();
                        }
                    }
                    d2 += 1.0d;
                }
                double d3 = d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? ((d + d2) - d) / d2 : 0.0d;
                Iterator it = arrayList2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        dBFeaturedContentAd = null;
                        break;
                    }
                    dBFeaturedContentAd = (DBFeaturedContentAd) it.next();
                    nextDouble = (dBFeaturedContentAd.getDisplayFrequency() == null || dBFeaturedContentAd.getDisplayFrequency().doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) ? nextDouble - d3 : nextDouble - dBFeaturedContentAd.getDisplayFrequency().doubleValue();
                    if (nextDouble < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                        break;
                    }
                }
                if (dBFeaturedContentAd != null) {
                    DBContentItem contentItem = dBFeaturedContentAd.getContentItem();
                    if (contentItem != null && !ContentDataManager.getInstance().findAndMarkMissingFilesForContentItem(contentItem)) {
                        FeaturedContentRowItem featuredContentRowItem2 = new FeaturedContentRowItem(dBFeaturedContentAd, this.context);
                        this.featuredContentRowItem = featuredContentRowItem2;
                        if (featuredContentRowItem2.errorRetrievingDrawable) {
                            this.featuredContentRowItem = null;
                        }
                    }
                    if (this.featuredContentRowItem != null) {
                        if (dBFeaturedContentAd.getPromotion() != null) {
                            if (DBFilter.shouldIncludeFilters(dBFeaturedContentAd.getPromotion().getFilters()) && DBPlatform.shouldIncludePlatforms(dBFeaturedContentAd.getPromotion().getPlatforms())) {
                                dBFeaturedContentAd.promotionToUse = dBFeaturedContentAd.getPromotion();
                            }
                        } else if (contentItem.getPromotions() != null && !contentItem.getPromotions().isEmpty()) {
                            Iterator<DBPromotion> it2 = contentItem.getPromotions().iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                DBPromotion next3 = it2.next();
                                if (DBFilter.shouldIncludeFilters(next3.getFilters()) && DBPlatform.shouldIncludePlatforms(next3.getPlatforms())) {
                                    dBFeaturedContentAd.promotionToUse = next3;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        return this.featuredContentRowItem;
    }

    public boolean getRowItemCacheBuilt() {
        return this.rowItemCacheBuilt;
    }

    public synchronized List<QxRecyclerViewRowItem> getGroupedCalcRowItems() {
        if (!this.rowItemCacheBuilt) {
            rebuildCache();
        }
        if (isShowFeaturedContentRowItem()) {
            return this.groupedCalcRowItems;
        }
        if (this.groupedCalcRowItems == null || this.groupedCalcRowItems.isEmpty() || !(this.groupedCalcRowItems.get(0) instanceof FeaturedContentRowItem)) {
            rebuildCache();
        } else {
            this.groupedCalcRowItems.remove(0);
        }
        return this.groupedCalcRowItems;
    }

    public synchronized List<QxRecyclerViewRowItem> getAllCalcRowItems() {
        if (!this.rowItemCacheBuilt) {
            rebuildCache();
        }
        return this.allCalcRowItems;
    }

    public synchronized List<QxRecyclerViewRowItem> getRecentRowItems() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (DBContentItem next : UserManager.getInstance().getDbUser().getRecentItems()) {
            if (next.getListType() == ContentItem.ContentItemListType.DEFAULT && DBContentItem.shouldIncludeItem(next)) {
                arrayList.add(new LeafItemRowItem(next, this.context));
            }
            if (arrayList.size() == 15) {
                break;
            }
        }
        return arrayList;
    }

    public synchronized List<QxRecyclerViewRowItem> getFavoriteRowItems() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (DBContentItem next : UserManager.getInstance().getDbUser().getFavoriteItems()) {
            if (DBContentItem.shouldIncludeItem(next)) {
                arrayList.add(new LeafItemRowItem(next, this.context));
            }
        }
        return arrayList;
    }

    public synchronized void updateContentItems(List<DBContentItem> list) {
        List<QxRecyclerViewRowItem> list2;
        int i;
        QxRecyclerViewAdapter adapter;
        QxRecyclerViewAdapter adapter2;
        int positionForRowItem;
        QxRecyclerViewAdapter qxRecyclerViewAdapter = null;
        QxRecyclerViewAdapter adapter3 = getGroupedCalcRowItemsChangedListener() != null ? getGroupedCalcRowItemsChangedListener().getAdapter() : null;
        if (adapter3 != null) {
            list2 = adapter3.getRowItems();
        } else {
            list2 = this.groupedCalcRowItems;
        }
        Iterator<DBContentItem> it = list.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            DBContentItem next = it.next();
            next.updatePromotionToUse();
            int i2 = 0;
            while (true) {
                if (i2 >= list2.size()) {
                    break;
                }
                QxRecyclerViewRowItem qxRecyclerViewRowItem = list2.get(i2);
                if (!(qxRecyclerViewRowItem instanceof LeafItemRowItem) || !((LeafItemRowItem) qxRecyclerViewRowItem).contentItem.getIdentifier().equals(next.getIdentifier())) {
                    i2++;
                } else {
                    ((LeafItemRowItem) qxRecyclerViewRowItem).resetItem();
                    if (adapter3 != null && (positionForRowItem = adapter3.getPositionForRowItem(qxRecyclerViewRowItem)) >= 0) {
                        adapter3.notifyItemChanged(positionForRowItem);
                    }
                    i = 1;
                }
            }
            if (i == 0) {
                updateRowItemWithContentItem(this.groupedCalcRowItems, next);
            }
        }
        if (getAllCalcRowItemsChangedListener() != null) {
            qxRecyclerViewAdapter = getAllCalcRowItemsChangedListener().getAdapter();
        }
        for (int i3 = 0; i3 < this.allCalcRowItems.size(); i3++) {
            QxRecyclerViewRowItem qxRecyclerViewRowItem2 = this.allCalcRowItems.get(i3);
            Iterator<DBContentItem> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                } else if (((LeafItemRowItem) qxRecyclerViewRowItem2).contentItem.getIdentifier().equals(it2.next().getIdentifier())) {
                    ((LeafItemRowItem) qxRecyclerViewRowItem2).resetItem();
                    if (qxRecyclerViewAdapter != null) {
                        qxRecyclerViewAdapter.notifyItemChanged(qxRecyclerViewAdapter.getPositionForRowItem(qxRecyclerViewRowItem2));
                    }
                }
            }
        }
        if (!(getRecentCalcRowItemsChangedListener() == null || (adapter2 = getRecentCalcRowItemsChangedListener().getAdapter()) == null)) {
            List<QxRecyclerViewRowItem> rowItems = adapter2.getRowItems();
            for (int i4 = 0; i4 < rowItems.size(); i4++) {
                QxRecyclerViewRowItem qxRecyclerViewRowItem3 = rowItems.get(i4);
                if (qxRecyclerViewRowItem3 instanceof LeafItemRowItem) {
                    Iterator<DBContentItem> it3 = list.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (((LeafItemRowItem) qxRecyclerViewRowItem3).contentItem.getIdentifier().equals(it3.next().getIdentifier())) {
                                ((LeafItemRowItem) qxRecyclerViewRowItem3).resetItem();
                                adapter2.notifyItemChanged(adapter2.getPositionForRowItem(qxRecyclerViewRowItem3));
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (!(getFavoriteCalcRowItemsChangedListener() == null || (adapter = getFavoriteCalcRowItemsChangedListener().getAdapter()) == null)) {
            List<QxRecyclerViewRowItem> rowItems2 = adapter.getRowItems();
            while (i < rowItems2.size()) {
                QxRecyclerViewRowItem qxRecyclerViewRowItem4 = rowItems2.get(i);
                if (qxRecyclerViewRowItem4 instanceof LeafItemRowItem) {
                    Iterator<DBContentItem> it4 = list.iterator();
                    while (true) {
                        if (it4.hasNext()) {
                            if (((LeafItemRowItem) qxRecyclerViewRowItem4).contentItem.getIdentifier().equals(it4.next().getIdentifier())) {
                                ((LeafItemRowItem) qxRecyclerViewRowItem4).resetItem();
                                adapter.notifyItemChanged(adapter.getPositionForRowItem(qxRecyclerViewRowItem4));
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                i++;
            }
        }
    }

    private boolean updateRowItemWithContentItem(List<QxRecyclerViewRowItem> list, DBContentItem dBContentItem) {
        for (int i = 0; i < list.size(); i++) {
            QxRecyclerViewRowItem qxRecyclerViewRowItem = list.get(i);
            if (qxRecyclerViewRowItem instanceof LeafItemRowItem) {
                LeafItemRowItem leafItemRowItem = (LeafItemRowItem) qxRecyclerViewRowItem;
                if (leafItemRowItem.contentItem.getIdentifier().equals(dBContentItem.getIdentifier())) {
                    leafItemRowItem.resetItem();
                    return true;
                }
            } else if (qxRecyclerViewRowItem.hasChildren() && updateRowItemWithContentItem(qxRecyclerViewRowItem.children, dBContentItem)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void insertContentItems(List<DBContentItem> list) {
        if (list != null) {
            for (DBContentItem updatePromotionToUse : list) {
                updatePromotionToUse.updatePromotionToUse();
            }
        }
        QxRecyclerViewAdapter qxRecyclerViewAdapter = null;
        QxRecyclerViewAdapter adapter = getGroupedCalcRowItemsChangedListener() != null ? getGroupedCalcRowItemsChangedListener().getAdapter() : null;
        if (getAllCalcRowItemsChangedListener() != null) {
            qxRecyclerViewAdapter = getAllCalcRowItemsChangedListener().getAdapter();
        }
        insertContentItemsToGroupedCalcList(list, adapter);
        insertContentItemsToAllCalcList(list, qxRecyclerViewAdapter);
    }

    public synchronized void insertContentItemsToAllCalcList(List<DBContentItem> list, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        int i;
        for (DBContentItem next : list) {
            if (next.getListType() == ContentItem.ContentItemListType.DEFAULT) {
                LeafItemRowItem leafItemRowItem = new LeafItemRowItem(next, this.context);
                int binarySearch = Collections.binarySearch(this.allCalcRowItems, leafItemRowItem, new Comparator<QxRecyclerViewRowItem>() {
                    public int compare(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewRowItem qxRecyclerViewRowItem2) {
                        return qxRecyclerViewRowItem.getTitle().compareToIgnoreCase(qxRecyclerViewRowItem2.getTitle());
                    }
                });
                if (binarySearch < 0) {
                    binarySearch = (binarySearch * -1) - 1;
                }
                if (qxRecyclerViewAdapter != null) {
                    if (binarySearch < this.allCalcRowItems.size()) {
                        i = qxRecyclerViewAdapter.getPositionForRowItem(this.allCalcRowItems.get(binarySearch));
                    } else {
                        i = qxRecyclerViewAdapter.getPositionForRowItem(this.allCalcRowItems.get(binarySearch - 1)) + 1;
                    }
                    this.allCalcRowItems.add(binarySearch, leafItemRowItem);
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(leafItemRowItem);
                    qxRecyclerViewAdapter.insertRowsAtIndex(arrayList, i, 0);
                } else {
                    this.allCalcRowItems.add(binarySearch, leafItemRowItem);
                }
            }
        }
    }

    public synchronized void insertContentItemsToGroupedCalcList(List<DBContentItem> list, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ArrayList<QxRecyclerViewRowItem> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        for (DBContentItem next : list) {
            if (next.getCategories() != null && !next.getCategories().isEmpty()) {
                for (DBCategory next2 : next.getCategories()) {
                    if (!next2.isMenuItem()) {
                        if (next2.getName().equalsIgnoreCase("my_specialty")) {
                            arrayList2.add(next);
                        } else {
                            addDbContentItemToGroupedRowItems(next, next2, (QxRecyclerViewRowItem) null, arrayList);
                        }
                    }
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            addMySpecialtyContentItemsToRowItems(arrayList2, arrayList);
        }
        for (QxRecyclerViewRowItem qxRecyclerViewRowItem : arrayList) {
            if (qxRecyclerViewRowItem.hasChildren()) {
                sortRowItems(qxRecyclerViewRowItem.children);
            }
        }
        List<QxRecyclerViewRowItem> list2 = this.groupedCalcRowItems;
        for (QxRecyclerViewRowItem insertRowItem : arrayList) {
            insertRowItem(insertRowItem, (QxRecyclerViewRowItem) null, list2, qxRecyclerViewAdapter);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void insertRowItem(com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r7, com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r8, java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r9, com.qxmd.qxrecyclerview.QxRecyclerViewAdapter r10) {
        /*
            r6 = this;
            boolean r0 = r7.hasChildren()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0049
            java.util.Iterator r0 = r9.iterator()
        L_0x000c:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0049
            java.lang.Object r3 = r0.next()
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r3 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r3
            boolean r4 = r3 instanceof com.wbmd.qxcalculator.model.rowItems.CategoryRowItem
            if (r4 == 0) goto L_0x000c
            java.lang.String r4 = r3.getTitle()
            java.lang.String r5 = r7.getTitle()
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 == 0) goto L_0x000c
            boolean r0 = r3.isExpanded
            if (r0 != 0) goto L_0x002f
            r10 = 0
        L_0x002f:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r0 = r7.children
            java.util.Iterator r0 = r0.iterator()
        L_0x0035:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x0047
            java.lang.Object r4 = r0.next()
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r4 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r4
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r5 = r3.children
            r6.insertRowItem(r4, r3, r5, r10)
            goto L_0x0035
        L_0x0047:
            r0 = 1
            goto L_0x004a
        L_0x0049:
            r0 = 0
        L_0x004a:
            if (r0 != 0) goto L_0x00b3
            if (r8 == 0) goto L_0x0086
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r9 = r8.children
            int r9 = r6.findIndexForInsertion(r9, r7)
            boolean r0 = r8.isExpanded
            if (r0 == 0) goto L_0x0082
            if (r10 == 0) goto L_0x0082
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r0 = r8.children
            int r0 = r0.size()
            if (r9 >= r0) goto L_0x006f
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r0 = r8.children
            java.lang.Object r0 = r0.get(r9)
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r0
            int r0 = r10.getPositionForRowItem(r0)
            goto L_0x0073
        L_0x006f:
            int r0 = r10.getPositionOfNextRowItemAtSameIndent(r8)
        L_0x0073:
            r8.insertChild(r9, r7)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>(r1)
            r8.add(r7)
            r10.insertRowsAtIndex(r8, r0, r2)
            goto L_0x00b3
        L_0x0082:
            r8.insertChild(r9, r7)
            goto L_0x00b3
        L_0x0086:
            int r8 = r6.findIndexForInsertion(r9, r7)
            if (r10 == 0) goto L_0x00b0
            int r0 = r9.size()
            if (r8 >= r0) goto L_0x009d
            java.lang.Object r0 = r9.get(r8)
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r0
            int r0 = r10.getPositionForRowItem(r0)
            goto L_0x00a1
        L_0x009d:
            int r0 = r10.getItemCount()
        L_0x00a1:
            r9.add(r8, r7)
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>(r1)
            r8.add(r7)
            r10.insertRowsAtIndex(r8, r0, r2)
            goto L_0x00b3
        L_0x00b0:
            r9.add(r8, r7)
        L_0x00b3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.util.RowItemBuilder.insertRowItem(com.qxmd.qxrecyclerview.QxRecyclerViewRowItem, com.qxmd.qxrecyclerview.QxRecyclerViewRowItem, java.util.List, com.qxmd.qxrecyclerview.QxRecyclerViewAdapter):void");
    }

    private int findIndexForInsertion(final List<QxRecyclerViewRowItem> list, QxRecyclerViewRowItem qxRecyclerViewRowItem) {
        int binarySearch = Collections.binarySearch(list, qxRecyclerViewRowItem, new Comparator<QxRecyclerViewRowItem>() {
            public int compare(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewRowItem qxRecyclerViewRowItem2) {
                if (list == RowItemBuilder.this.groupedCalcRowItems) {
                    if (RowItemBuilder.this.generalGroupedCalcRowItems != null && qxRecyclerViewRowItem == RowItemBuilder.this.generalGroupedCalcRowItems) {
                        return -1;
                    }
                    if (RowItemBuilder.this.generalGroupedCalcRowItems != null && qxRecyclerViewRowItem2 == RowItemBuilder.this.generalGroupedCalcRowItems) {
                        return 1;
                    }
                    if (RowItemBuilder.this.usersGroupedCalcRowItems != null && qxRecyclerViewRowItem == RowItemBuilder.this.usersGroupedCalcRowItems) {
                        return -1;
                    }
                    if (RowItemBuilder.this.usersGroupedCalcRowItems != null && qxRecyclerViewRowItem2 == RowItemBuilder.this.usersGroupedCalcRowItems) {
                        return 1;
                    }
                    if (qxRecyclerViewRowItem instanceof FeaturedContentRowItem) {
                        return -1;
                    }
                    if (qxRecyclerViewRowItem2 instanceof FeaturedContentRowItem) {
                        return 1;
                    }
                }
                if (qxRecyclerViewRowItem.sortingWeight != qxRecyclerViewRowItem2.sortingWeight) {
                    return qxRecyclerViewRowItem2.sortingWeight - qxRecyclerViewRowItem.sortingWeight;
                }
                return qxRecyclerViewRowItem.getTitle().compareToIgnoreCase(qxRecyclerViewRowItem2.getTitle());
            }
        });
        return binarySearch < 0 ? (binarySearch * -1) - 1 : binarySearch;
    }

    public synchronized void deleteContentItems(List<DBContentItem> list) {
        QxRecyclerViewAdapter qxRecyclerViewAdapter = null;
        QxRecyclerViewAdapter adapter = getGroupedCalcRowItemsChangedListener() != null ? getGroupedCalcRowItemsChangedListener().getAdapter() : null;
        if (getAllCalcRowItemsChangedListener() != null) {
            qxRecyclerViewAdapter = getAllCalcRowItemsChangedListener().getAdapter();
        }
        deleteContentItemsFromGroupedCalcList(list, adapter);
        deleteContentItemsFromAllCalcList(list, qxRecyclerViewAdapter);
    }

    public synchronized void deleteContentItemsFromAllCalcList(List<DBContentItem> list, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        for (DBContentItem next : list) {
            Iterator<QxRecyclerViewRowItem> it = this.allCalcRowItems.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                QxRecyclerViewRowItem next2 = it.next();
                if (((LeafItemRowItem) next2).contentItem.getIdentifier().equals(next.getIdentifier())) {
                    it.remove();
                    if (qxRecyclerViewAdapter != null) {
                        qxRecyclerViewAdapter.deleteRowsAtIndex(qxRecyclerViewAdapter.getPositionForRowItem(next2), 1, 0);
                    }
                }
            }
        }
    }

    public synchronized void deleteContentItemsFromGroupedCalcList(List<DBContentItem> list, QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        ArrayList<QxRecyclerViewRowItem> arrayList = new ArrayList<>();
        for (DBContentItem next : list) {
            if (next.getCategories() != null && !next.getCategories().isEmpty()) {
                for (DBCategory addDbContentItemToGroupedRowItems : next.getCategories()) {
                    addDbContentItemToGroupedRowItems(next, addDbContentItemToGroupedRowItems, (QxRecyclerViewRowItem) null, arrayList);
                }
            }
        }
        List<QxRecyclerViewRowItem> list2 = this.groupedCalcRowItems;
        for (QxRecyclerViewRowItem deleteRowItem : arrayList) {
            deleteRowItem(deleteRowItem, (QxRecyclerViewRowItem) null, list2, qxRecyclerViewAdapter);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void deleteRowItem(com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r7, com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r8, java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r9, com.qxmd.qxrecyclerview.QxRecyclerViewAdapter r10) {
        /*
            r6 = this;
            boolean r0 = r7.hasChildren()
            r1 = 1
            r2 = 0
            r3 = 0
            if (r0 == 0) goto L_0x004c
            java.util.Iterator r9 = r9.iterator()
        L_0x000d:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x004c
            java.lang.Object r0 = r9.next()
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r0
            boolean r4 = r0 instanceof com.wbmd.qxcalculator.model.rowItems.CategoryRowItem
            if (r4 == 0) goto L_0x000d
            com.wbmd.qxcalculator.model.rowItems.CategoryRowItem r0 = (com.wbmd.qxcalculator.model.rowItems.CategoryRowItem) r0
            java.lang.String r4 = r0.getTitle()
            java.lang.String r5 = r7.getTitle()
            boolean r4 = r4.equalsIgnoreCase(r5)
            if (r4 == 0) goto L_0x000d
            boolean r9 = r0.isExpanded
            if (r9 != 0) goto L_0x0032
            r10 = r2
        L_0x0032:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r9 = r7.children
            java.util.Iterator r9 = r9.iterator()
        L_0x0038:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x004a
            java.lang.Object r4 = r9.next()
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r4 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r4
            java.util.List r5 = r0.children
            r6.deleteRowItem(r4, r0, r5, r10)
            goto L_0x0038
        L_0x004a:
            r9 = 1
            goto L_0x004d
        L_0x004c:
            r9 = 0
        L_0x004d:
            if (r9 != 0) goto L_0x00ba
            boolean r9 = r7 instanceof com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem
            if (r9 == 0) goto L_0x0099
            if (r8 == 0) goto L_0x00ba
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r9 = r8.children
            java.util.Iterator r9 = r9.iterator()
        L_0x005b:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto L_0x0084
            java.lang.Object r0 = r9.next()
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r0
            boolean r4 = r0 instanceof com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem
            if (r4 == 0) goto L_0x005b
            r4 = r0
            com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem r4 = (com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem) r4
            r5 = r7
            com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem r5 = (com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem) r5
            com.wbmd.qxcalculator.model.db.DBContentItem r5 = r5.contentItem
            java.lang.String r5 = r5.getIdentifier()
            com.wbmd.qxcalculator.model.db.DBContentItem r4 = r4.contentItem
            java.lang.String r4 = r4.getIdentifier()
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x005b
            r2 = r0
        L_0x0084:
            if (r2 == 0) goto L_0x0089
            r8.deleteChild(r2)
        L_0x0089:
            boolean r7 = r8.isExpanded
            if (r7 == 0) goto L_0x00ba
            if (r2 == 0) goto L_0x00ba
            if (r10 == 0) goto L_0x00ba
            int r7 = r10.getPositionForRowItem(r2)
            r10.deleteRowsAtIndex(r7, r1, r3)
            goto L_0x00ba
        L_0x0099:
            java.lang.Exception r8 = new java.lang.Exception
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "trying to delete sub-group: "
            r9.append(r10)
            java.lang.String r7 = r7.getTitle()
            r9.append(r7)
            java.lang.String r7 = r9.toString()
            r8.<init>(r7)
            com.wbmd.qxcalculator.util.CrashLogger r7 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()
            r7.logHandledException(r8)
        L_0x00ba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.util.RowItemBuilder.deleteRowItem(com.qxmd.qxrecyclerview.QxRecyclerViewRowItem, com.qxmd.qxrecyclerview.QxRecyclerViewRowItem, java.util.List, com.qxmd.qxrecyclerview.QxRecyclerViewAdapter):void");
    }

    public synchronized void clearCache() {
        this.groupedCalcRowItems = null;
        this.allCalcRowItems = null;
        this.featuredContentRowItem = null;
        this.rowItemCacheBuilt = false;
    }

    private synchronized boolean isShowFeaturedContentRowItem() {
        return this.showFeaturedContentRowItem;
    }

    public synchronized void setShowFeaturedContentRowItem(boolean z) {
        this.showFeaturedContentRowItem = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0214, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void rebuildCache() {
        /*
            r13 = this;
            monitor-enter(r13)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0215 }
            r1 = 50
            r0.<init>(r1)     // Catch:{ all -> 0x0215 }
            r13.groupedCalcRowItems = r0     // Catch:{ all -> 0x0215 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0215 }
            r1 = 500(0x1f4, float:7.0E-43)
            r0.<init>(r1)     // Catch:{ all -> 0x0215 }
            r13.allCalcRowItems = r0     // Catch:{ all -> 0x0215 }
            r0 = 0
            r13.featuredContentRowItem = r0     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.managers.UserManager r1 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.db.DBUser r1 = r1.getDbUser()     // Catch:{ all -> 0x0215 }
            if (r1 != 0) goto L_0x0022
            monitor-exit(r13)
            return
        L_0x0022:
            java.util.List r2 = r1.getContentItemsAndFetchRelations()     // Catch:{ all -> 0x0215 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0215 }
            int r4 = r2.size()     // Catch:{ all -> 0x0215 }
            r3.<init>(r4)     // Catch:{ all -> 0x0215 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0215 }
            r4.<init>()     // Catch:{ all -> 0x0215 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0215 }
        L_0x0038:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x0215 }
            if (r5 == 0) goto L_0x0094
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.db.DBContentItem r5 = (com.wbmd.qxcalculator.model.db.DBContentItem) r5     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.CalculateModule$ModuleOwner r6 = com.wbmd.qxcalculator.CalculateModule.moduleOwner     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.CalculateModule$ModuleOwner r7 = com.wbmd.qxcalculator.CalculateModule.ModuleOwner.QxMD     // Catch:{ all -> 0x0215 }
            if (r6 != r7) goto L_0x007e
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem$ContentItemListType r6 = r5.getListType()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem$ContentItemListType r7 = com.wbmd.qxcalculator.model.contentItems.common.ContentItem.ContentItemListType.DEFAULT     // Catch:{ all -> 0x0215 }
            if (r6 != r7) goto L_0x005c
            boolean r6 = com.wbmd.qxcalculator.model.db.DBContentItem.shouldIncludeItem(r5)     // Catch:{ all -> 0x0215 }
            if (r6 == 0) goto L_0x005c
            r3.add(r5)     // Catch:{ all -> 0x0215 }
            goto L_0x0038
        L_0x005c:
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem$ContentItemListType r6 = r5.getListType()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem$ContentItemListType r7 = com.wbmd.qxcalculator.model.contentItems.common.ContentItem.ContentItemListType.SPLASH_PAGE     // Catch:{ all -> 0x0215 }
            if (r6 != r7) goto L_0x0038
            boolean r6 = com.wbmd.qxcalculator.model.db.DBContentItem.shouldIncludeItem(r5)     // Catch:{ all -> 0x0215 }
            if (r6 == 0) goto L_0x0038
            com.wbmd.qxcalculator.model.db.DBSplashPage r6 = r5.getSplashPage()     // Catch:{ all -> 0x0215 }
            java.lang.String r6 = r6.getType()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage$SplashPageType r6 = com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage.getSplashPageType(r6)     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage$SplashPageType r7 = com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage.SplashPageType.CENTER_INSIDE     // Catch:{ all -> 0x0215 }
            if (r6 != r7) goto L_0x0038
            r4.add(r5)     // Catch:{ all -> 0x0215 }
            goto L_0x0038
        L_0x007e:
            com.wbmd.qxcalculator.CalculateModule$ModuleOwner r6 = com.wbmd.qxcalculator.CalculateModule.moduleOwner     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.CalculateModule$ModuleOwner r7 = com.wbmd.qxcalculator.CalculateModule.ModuleOwner.WebMD     // Catch:{ all -> 0x0215 }
            if (r6 != r7) goto L_0x0038
            java.lang.String r6 = r5.getIdentifier()     // Catch:{ all -> 0x0215 }
            if (r6 == 0) goto L_0x0038
            boolean r6 = com.wbmd.qxcalculator.model.db.DBContentItem.shouldIncludeItem(r5)     // Catch:{ all -> 0x0215 }
            if (r6 == 0) goto L_0x0038
            r3.add(r5)     // Catch:{ all -> 0x0215 }
            goto L_0x0038
        L_0x0094:
            boolean r2 = r4.isEmpty()     // Catch:{ all -> 0x0215 }
            if (r2 != 0) goto L_0x00ae
            java.util.Random r2 = new java.util.Random     // Catch:{ all -> 0x0215 }
            r2.<init>()     // Catch:{ all -> 0x0215 }
            int r5 = r4.size()     // Catch:{ all -> 0x0215 }
            int r2 = r2.nextInt(r5)     // Catch:{ all -> 0x0215 }
            java.lang.Object r2 = r4.get(r2)     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.db.DBContentItem r2 = (com.wbmd.qxcalculator.model.db.DBContentItem) r2     // Catch:{ all -> 0x0215 }
            goto L_0x00af
        L_0x00ae:
            r2 = r0
        L_0x00af:
            com.wbmd.qxcalculator.managers.DataManager r4 = com.wbmd.qxcalculator.managers.DataManager.getInstance()     // Catch:{ all -> 0x0215 }
            r4.setSplashPageSponsorImage(r2)     // Catch:{ all -> 0x0215 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0215 }
            r2.<init>()     // Catch:{ all -> 0x0215 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0215 }
        L_0x00bf:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x0215 }
            if (r4 == 0) goto L_0x010c
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.db.DBContentItem r4 = (com.wbmd.qxcalculator.model.db.DBContentItem) r4     // Catch:{ all -> 0x0215 }
            r4.updatePromotionToUse()     // Catch:{ all -> 0x0215 }
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r5 = r13.allCalcRowItems     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem r6 = new com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem     // Catch:{ all -> 0x0215 }
            android.content.Context r7 = r13.context     // Catch:{ all -> 0x0215 }
            r6.<init>(r4, r7)     // Catch:{ all -> 0x0215 }
            r5.add(r6)     // Catch:{ all -> 0x0215 }
            java.util.List r5 = r4.getCategories()     // Catch:{ all -> 0x0215 }
            if (r5 == 0) goto L_0x00bf
            java.util.List r5 = r4.getCategories()     // Catch:{ all -> 0x0215 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0215 }
        L_0x00e8:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0215 }
            if (r6 == 0) goto L_0x00bf
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.db.DBCategory r6 = (com.wbmd.qxcalculator.model.db.DBCategory) r6     // Catch:{ all -> 0x0215 }
            if (r6 == 0) goto L_0x00e8
            java.lang.String r7 = r6.getName()     // Catch:{ all -> 0x0215 }
            java.lang.String r8 = "my_specialty"
            boolean r7 = r7.equalsIgnoreCase(r8)     // Catch:{ all -> 0x0215 }
            if (r7 == 0) goto L_0x0106
            r2.add(r4)     // Catch:{ all -> 0x0215 }
            goto L_0x00e8
        L_0x0106:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r7 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            r13.addDbContentItemToGroupedRowItems(r4, r6, r0, r7)     // Catch:{ all -> 0x0215 }
            goto L_0x00e8
        L_0x010c:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r3 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            r13.sortRowItemsAlphabetically(r3)     // Catch:{ all -> 0x0215 }
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r3 = r13.allCalcRowItems     // Catch:{ all -> 0x0215 }
            r13.sortRowItemsAlphabetically(r3)     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.db.DBSpecialty r3 = r1.getSpecialty()     // Catch:{ all -> 0x0215 }
            r4 = -1
            r5 = 0
            r8 = r0
            r6 = 0
            r7 = -1
        L_0x011f:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r9 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            int r9 = r9.size()     // Catch:{ all -> 0x0215 }
            r10 = 1
            if (r6 >= r9) goto L_0x0153
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r9 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            java.lang.Object r9 = r9.get(r6)     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.rowItems.GroupRowItem r9 = (com.wbmd.qxcalculator.model.rowItems.GroupRowItem) r9     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.db.DBCategory r11 = r9.category     // Catch:{ all -> 0x0215 }
            java.lang.String r11 = r11.getIdentifier()     // Catch:{ all -> 0x0215 }
            java.lang.String r12 = "194"
            boolean r11 = r11.equalsIgnoreCase(r12)     // Catch:{ all -> 0x0215 }
            if (r11 == 0) goto L_0x013f
            r7 = r6
        L_0x013f:
            com.wbmd.qxcalculator.model.db.DBCategory r11 = r9.category     // Catch:{ all -> 0x0215 }
            java.lang.String r11 = r11.getIdentifier()     // Catch:{ all -> 0x0215 }
            java.lang.String r12 = "381"
            boolean r11 = r11.equalsIgnoreCase(r12)     // Catch:{ all -> 0x0215 }
            if (r11 == 0) goto L_0x0150
            r9.highlightGroup = r10     // Catch:{ all -> 0x0215 }
            r8 = r9
        L_0x0150:
            int r6 = r6 + 1
            goto L_0x011f
        L_0x0153:
            if (r7 < 0) goto L_0x0165
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r6 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            java.lang.Object r6 = r6.remove(r7)     // Catch:{ all -> 0x0215 }
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r6 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r6     // Catch:{ all -> 0x0215 }
            r13.generalGroupedCalcRowItems = r6     // Catch:{ all -> 0x0215 }
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r7 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            r7.add(r5, r6)     // Catch:{ all -> 0x0215 }
            goto L_0x0167
        L_0x0165:
            r13.generalGroupedCalcRowItems = r0     // Catch:{ all -> 0x0215 }
        L_0x0167:
            if (r8 == 0) goto L_0x0179
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r6 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r7 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            int r7 = r7.indexOf(r8)     // Catch:{ all -> 0x0215 }
            r6.remove(r7)     // Catch:{ all -> 0x0215 }
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r6 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            r6.add(r5, r8)     // Catch:{ all -> 0x0215 }
        L_0x0179:
            r6 = 0
        L_0x017a:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r7 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            int r7 = r7.size()     // Catch:{ all -> 0x0215 }
            if (r6 >= r7) goto L_0x01a1
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r7 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            java.lang.Object r7 = r7.get(r6)     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.rowItems.GroupRowItem r7 = (com.wbmd.qxcalculator.model.rowItems.GroupRowItem) r7     // Catch:{ all -> 0x0215 }
            if (r3 == 0) goto L_0x019e
            com.wbmd.qxcalculator.model.db.DBCategory r7 = r7.category     // Catch:{ all -> 0x0215 }
            java.lang.String r7 = r7.getIdentifier()     // Catch:{ all -> 0x0215 }
            java.lang.String r9 = r3.getCategoryIdentifier()     // Catch:{ all -> 0x0215 }
            boolean r7 = r7.equalsIgnoreCase(r9)     // Catch:{ all -> 0x0215 }
            if (r7 == 0) goto L_0x019e
            r4 = r6
            goto L_0x01a1
        L_0x019e:
            int r6 = r6 + 1
            goto L_0x017a
        L_0x01a1:
            if (r4 < 0) goto L_0x01c9
            boolean r0 = r1.getIsPersonalizationEnabled()     // Catch:{ all -> 0x0215 }
            if (r0 == 0) goto L_0x01be
            if (r8 == 0) goto L_0x01ad
            r0 = 2
            goto L_0x01ae
        L_0x01ad:
            r0 = 1
        L_0x01ae:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r1 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            java.lang.Object r1 = r1.remove(r4)     // Catch:{ all -> 0x0215 }
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r1 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r1     // Catch:{ all -> 0x0215 }
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r3 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            r3.add(r0, r1)     // Catch:{ all -> 0x0215 }
            r13.usersGroupedCalcRowItems = r1     // Catch:{ all -> 0x0215 }
            goto L_0x01cb
        L_0x01be:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r0 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0215 }
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r0 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r0     // Catch:{ all -> 0x0215 }
            r13.usersGroupedCalcRowItems = r0     // Catch:{ all -> 0x0215 }
            goto L_0x01cb
        L_0x01c9:
            r13.usersGroupedCalcRowItems = r0     // Catch:{ all -> 0x0215 }
        L_0x01cb:
            boolean r0 = r2.isEmpty()     // Catch:{ all -> 0x0215 }
            if (r0 != 0) goto L_0x01d6
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r0 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            r13.addMySpecialtyContentItemsToRowItems(r2, r0)     // Catch:{ all -> 0x0215 }
        L_0x01d6:
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r0 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0215 }
        L_0x01dc:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0215 }
            if (r1 == 0) goto L_0x01f4
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0215 }
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r1 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r1     // Catch:{ all -> 0x0215 }
            boolean r2 = r1.hasChildren()     // Catch:{ all -> 0x0215 }
            if (r2 == 0) goto L_0x01dc
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r1 = r1.children     // Catch:{ all -> 0x0215 }
            r13.sortRowItems(r1)     // Catch:{ all -> 0x0215 }
            goto L_0x01dc
        L_0x01f4:
            r13.rowItemCacheBuilt = r10     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.calcList.CalcListType r0 = r13.getCalcListTypeForFeaturedContentAd()     // Catch:{ all -> 0x0215 }
            com.wbmd.qxcalculator.model.calcList.CalcListType r1 = com.wbmd.qxcalculator.model.calcList.CalcListType.GROUPED     // Catch:{ all -> 0x0215 }
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x0215 }
            if (r0 == 0) goto L_0x0213
            boolean r0 = r13.isShowFeaturedContentRowItem()     // Catch:{ all -> 0x0215 }
            if (r0 == 0) goto L_0x0213
            com.wbmd.qxcalculator.model.rowItems.FeaturedContentRowItem r0 = r13.getFeaturedContentRowItem()     // Catch:{ all -> 0x0215 }
            if (r0 == 0) goto L_0x0213
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r1 = r13.groupedCalcRowItems     // Catch:{ all -> 0x0215 }
            r1.add(r5, r0)     // Catch:{ all -> 0x0215 }
        L_0x0213:
            monitor-exit(r13)
            return
        L_0x0215:
            r0 = move-exception
            monitor-exit(r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.util.RowItemBuilder.rebuildCache():void");
    }

    public void addMySpecialtyContentItemsToRowItems(List<DBContentItem> list, List<QxRecyclerViewRowItem> list2) {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        for (DBContentItem next : list) {
            if (next.getName().contains("$my_specialty")) {
                if (dbUser.getSpecialty() == null) {
                    next.overrideName = next.getName().replace("$my_specialty", "General Medicine");
                } else {
                    next.overrideName = next.getName().replace("$my_specialty", dbUser.getSpecialty().getName());
                }
            }
            for (DBCategory next2 : next.getCategories()) {
                if (next2.getName().equalsIgnoreCase("my_specialty")) {
                    QxRecyclerViewRowItem qxRecyclerViewRowItem = this.usersGroupedCalcRowItems;
                    if (qxRecyclerViewRowItem == null) {
                        qxRecyclerViewRowItem = this.generalGroupedCalcRowItems;
                    }
                    next2.overrideName = qxRecyclerViewRowItem.getTitle();
                    addDbContentItemToGroupedRowItems(next, next2, (QxRecyclerViewRowItem) null, list2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addDbContentItemToGroupedRowItems(com.wbmd.qxcalculator.model.db.DBContentItem r5, com.wbmd.qxcalculator.model.db.DBCategory r6, com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r7, java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r8) {
        /*
            r4 = this;
            if (r6 == 0) goto L_0x00a0
            java.lang.String r0 = r6.overrideName
            if (r0 == 0) goto L_0x0009
            java.lang.String r0 = r6.overrideName
            goto L_0x000d
        L_0x0009:
            java.lang.String r0 = r6.getName()
        L_0x000d:
            if (r8 == 0) goto L_0x002e
            java.util.Iterator r1 = r8.iterator()
        L_0x0013:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x002e
            java.lang.Object r2 = r1.next()
            com.qxmd.qxrecyclerview.QxRecyclerViewRowItem r2 = (com.qxmd.qxrecyclerview.QxRecyclerViewRowItem) r2
            boolean r3 = r2 instanceof com.wbmd.qxcalculator.model.rowItems.CategoryRowItem
            if (r3 == 0) goto L_0x0013
            java.lang.String r3 = r2.getTitle()
            boolean r3 = r3.equalsIgnoreCase(r0)
            if (r3 == 0) goto L_0x0013
            goto L_0x002f
        L_0x002e:
            r2 = 0
        L_0x002f:
            if (r2 != 0) goto L_0x005c
            boolean r0 = r6.hasParent()
            if (r0 == 0) goto L_0x0052
            com.wbmd.qxcalculator.model.rowItems.SubGroupRowItem r2 = new com.wbmd.qxcalculator.model.rowItems.SubGroupRowItem
            android.content.Context r8 = r4.context
            r2.<init>(r6, r8)
            java.lang.Integer r8 = r6.getWeight()
            if (r8 == 0) goto L_0x004e
            java.lang.Integer r8 = r6.getWeight()
            short r8 = r8.shortValue()
            r2.sortingWeight = r8
        L_0x004e:
            r7.addChild(r2)
            goto L_0x005c
        L_0x0052:
            com.wbmd.qxcalculator.model.rowItems.GroupRowItem r2 = new com.wbmd.qxcalculator.model.rowItems.GroupRowItem
            android.content.Context r7 = r4.context
            r2.<init>(r6, r7)
            r8.add(r2)
        L_0x005c:
            java.util.List r7 = r6.getSubCategories()
            if (r7 == 0) goto L_0x0086
            java.util.List r7 = r6.getSubCategories()
            boolean r7 = r7.isEmpty()
            if (r7 != 0) goto L_0x0086
            java.util.List r6 = r6.getSubCategories()
            java.util.Iterator r6 = r6.iterator()
        L_0x0074:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00a0
            java.lang.Object r7 = r6.next()
            com.wbmd.qxcalculator.model.db.DBCategory r7 = (com.wbmd.qxcalculator.model.db.DBCategory) r7
            java.util.List<com.qxmd.qxrecyclerview.QxRecyclerViewRowItem> r8 = r2.children
            r4.addDbContentItemToGroupedRowItems(r5, r7, r2, r8)
            goto L_0x0074
        L_0x0086:
            com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem r7 = new com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem
            android.content.Context r8 = r4.context
            r7.<init>(r5, r8)
            java.lang.Integer r5 = r6.getItemWeight()
            if (r5 == 0) goto L_0x009d
            java.lang.Integer r5 = r6.getItemWeight()
            short r5 = r5.shortValue()
            r7.sortingWeight = r5
        L_0x009d:
            r2.addChild(r7)
        L_0x00a0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.util.RowItemBuilder.addDbContentItemToGroupedRowItems(com.wbmd.qxcalculator.model.db.DBContentItem, com.wbmd.qxcalculator.model.db.DBCategory, com.qxmd.qxrecyclerview.QxRecyclerViewRowItem, java.util.List):void");
    }

    public QxRecyclerViewRowItem getRowItemUsersGroup() {
        DBSpecialty specialty = UserManager.getInstance().getDbUser().getSpecialty();
        if (specialty == null) {
            return null;
        }
        for (int i = 0; i < this.groupedCalcRowItems.size(); i++) {
            if (this.groupedCalcRowItems.get(i) instanceof GroupRowItem) {
                GroupRowItem groupRowItem = (GroupRowItem) this.groupedCalcRowItems.get(i);
                if (groupRowItem.category.getIdentifier().equalsIgnoreCase(specialty.getCategoryIdentifier())) {
                    return groupRowItem;
                }
            }
        }
        return null;
    }

    private void sortRowItemsAlphabetically(List<QxRecyclerViewRowItem> list) {
        Collections.sort(list, new Comparator<QxRecyclerViewRowItem>() {
            public int compare(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewRowItem qxRecyclerViewRowItem2) {
                return qxRecyclerViewRowItem.getTitle().compareToIgnoreCase(qxRecyclerViewRowItem2.getTitle());
            }
        });
    }

    private void sortRowItems(List<QxRecyclerViewRowItem> list) {
        Collections.sort(list, new Comparator<QxRecyclerViewRowItem>() {
            public int compare(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewRowItem qxRecyclerViewRowItem2) {
                if (qxRecyclerViewRowItem.sortingWeight != qxRecyclerViewRowItem2.sortingWeight) {
                    return qxRecyclerViewRowItem2.sortingWeight - qxRecyclerViewRowItem.sortingWeight;
                }
                return qxRecyclerViewRowItem.getTitle().compareToIgnoreCase(qxRecyclerViewRowItem2.getTitle());
            }
        });
        for (QxRecyclerViewRowItem next : list) {
            if (next.hasChildren()) {
                sortRowItems(next.children);
            }
        }
    }
}
