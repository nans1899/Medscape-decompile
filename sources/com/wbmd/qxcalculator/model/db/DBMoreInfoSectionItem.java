package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSectionItem;
import com.wbmd.qxcalculator.model.db.DBMoreInfoSectionDao;
import com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBMoreInfoSectionItem {
    public static final String TAG = DBMoreInfoSectionItem.class.getSimpleName();
    private String accentColor;
    private String backgroundColor;
    private String color;
    private transient DaoSession daoSession;
    private String footer;
    private Long id;
    private String identifier;
    private String leftImage;
    private Long moreInfoSectionId;
    private Long moreInfoSectionItemId;
    private List<DBMoreInfoSectionItem> moreInfoSectionItems;
    private transient DBMoreInfoSectionItemDao myDao;
    private Integer position;
    private String rightImage;
    private String source;
    private String subTitle;
    private String title;
    private String trackerCategory;
    private String trackerEvent;
    private String trackerId;
    private String trackerLabel;
    private String type;

    public DBMoreInfoSectionItem() {
    }

    public DBMoreInfoSectionItem(Long l) {
        this.id = l;
    }

    public DBMoreInfoSectionItem(Long l, String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.position = num;
        this.title = str2;
        this.subTitle = str3;
        this.footer = str4;
        this.type = str5;
        this.source = str6;
        this.leftImage = str7;
        this.rightImage = str8;
        this.backgroundColor = str9;
        this.color = str10;
        this.accentColor = str11;
        this.trackerId = str12;
        this.trackerCategory = str13;
        this.trackerEvent = str14;
        this.trackerLabel = str15;
        this.moreInfoSectionId = l2;
        this.moreInfoSectionItemId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBMoreInfoSectionItemDao() : null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getLeftImage() {
        return this.leftImage;
    }

    public void setLeftImage(String str) {
        this.leftImage = str;
    }

    public String getRightImage() {
        return this.rightImage;
    }

    public void setRightImage(String str) {
        this.rightImage = str;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getAccentColor() {
        return this.accentColor;
    }

    public void setAccentColor(String str) {
        this.accentColor = str;
    }

    public String getTrackerId() {
        return this.trackerId;
    }

    public void setTrackerId(String str) {
        this.trackerId = str;
    }

    public String getTrackerCategory() {
        return this.trackerCategory;
    }

    public void setTrackerCategory(String str) {
        this.trackerCategory = str;
    }

    public String getTrackerEvent() {
        return this.trackerEvent;
    }

    public void setTrackerEvent(String str) {
        this.trackerEvent = str;
    }

    public String getTrackerLabel() {
        return this.trackerLabel;
    }

    public void setTrackerLabel(String str) {
        this.trackerLabel = str;
    }

    public Long getMoreInfoSectionId() {
        return this.moreInfoSectionId;
    }

    public void setMoreInfoSectionId(Long l) {
        this.moreInfoSectionId = l;
    }

    public Long getMoreInfoSectionItemId() {
        return this.moreInfoSectionItemId;
    }

    public void setMoreInfoSectionItemId(Long l) {
        this.moreInfoSectionItemId = l;
    }

    public List<DBMoreInfoSectionItem> getMoreInfoSectionItems() {
        if (this.moreInfoSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBMoreInfoSectionItem> _queryDBMoreInfoSectionItem_MoreInfoSectionItems = daoSession2.getDBMoreInfoSectionItemDao()._queryDBMoreInfoSectionItem_MoreInfoSectionItems(this.id);
                synchronized (this) {
                    if (this.moreInfoSectionItems == null) {
                        this.moreInfoSectionItems = _queryDBMoreInfoSectionItem_MoreInfoSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.moreInfoSectionItems;
    }

    public synchronized void resetMoreInfoSectionItems() {
        this.moreInfoSectionItems = null;
    }

    public void delete() {
        DBMoreInfoSectionItemDao dBMoreInfoSectionItemDao = this.myDao;
        if (dBMoreInfoSectionItemDao != null) {
            dBMoreInfoSectionItemDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBMoreInfoSectionItemDao dBMoreInfoSectionItemDao = this.myDao;
        if (dBMoreInfoSectionItemDao != null) {
            dBMoreInfoSectionItemDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBMoreInfoSectionItemDao dBMoreInfoSectionItemDao = this.myDao;
        if (dBMoreInfoSectionItemDao != null) {
            dBMoreInfoSectionItemDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedMoreInfoSectionItems(DaoSession daoSession2) {
        List list = daoSession2.getDBMoreInfoSectionItemDao().queryBuilder().where(DBMoreInfoSectionItemDao.Properties.MoreInfoSectionId.isNull(), DBMoreInfoSectionItemDao.Properties.MoreInfoSectionItemId.isNull()).list();
        String str = TAG;
        Log.d(str, "Purging DBMoreInfoSectionItem: " + list.size());
        deleteMoreInfoSectionItems(daoSession2, list);
    }

    public static void deleteMoreInfoSectionItems(DaoSession daoSession2, List<DBMoreInfoSectionItem> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (DBMoreInfoSectionItem next : list) {
                arrayList.add(next.getId());
                if (next.getMoreInfoSectionId() != null) {
                    arrayList2.add(next.getMoreInfoSectionId());
                }
                if (next.getMoreInfoSectionItemId() != null) {
                    arrayList3.add(next.getMoreInfoSectionItemId());
                }
            }
            List<DBMoreInfoSectionItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionItemDao(), DBMoreInfoSectionItemDao.Properties.MoreInfoSectionItemId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBMoreInfoSectionItem moreInfoSectionItemId2 : allWithPropertyInData) {
                    moreInfoSectionItemId2.setMoreInfoSectionItemId((Long) null);
                }
                deleteMoreInfoSectionItems(daoSession2, allWithPropertyInData);
            }
            if (!arrayList2.isEmpty()) {
                List<DBMoreInfoSection> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionDao(), DBMoreInfoSectionDao.Properties.Id, arrayList2);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBMoreInfoSection resetMoreInfoSectionItems : allWithPropertyInData2) {
                        resetMoreInfoSectionItems.resetMoreInfoSectionItems();
                    }
                }
            }
            if (!arrayList3.isEmpty()) {
                List<DBMoreInfoSectionItem> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionItemDao(), DBMoreInfoSectionItemDao.Properties.Id, arrayList3);
                if (!allWithPropertyInData3.isEmpty()) {
                    for (DBMoreInfoSectionItem resetMoreInfoSectionItems2 : allWithPropertyInData3) {
                        resetMoreInfoSectionItems2.resetMoreInfoSectionItems();
                    }
                }
            }
            daoSession2.getDBMoreInfoSectionItemDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSectionItem r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem> r0 = com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem.class
            monitor-enter(r0)
            r1 = 0
            if (r3 == 0) goto L_0x0029
            if (r4 != 0) goto L_0x0009
            goto L_0x0029
        L_0x0009:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0026 }
            r2.<init>()     // Catch:{ all -> 0x0026 }
            r2.add(r4)     // Catch:{ all -> 0x0026 }
            java.util.List r3 = insertAndRetrieveDbEntities(r3, r2)     // Catch:{ all -> 0x0026 }
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x0026 }
            r1 = r3
            com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem r1 = (com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem) r1     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r0)
            return r1
        L_0x0026:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x0029:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSectionItem):com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItem");
    }

    public static synchronized List<DBMoreInfoSectionItem> insertAndRetrieveDbEntities(DaoSession daoSession2, List<MoreInfoSectionItem> list) {
        synchronized (DBMoreInfoSectionItem.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (MoreInfoSectionItem next : list) {
                arrayList2.add(next.identifier);
                if (next.subSectionItems != null) {
                    arrayList3.addAll(next.subSectionItems);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionItemDao(), DBMoreInfoSectionItemDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (!arrayList3.isEmpty()) {
                arrayList5 = insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<MoreInfoSectionItem> it = list.iterator();
            while (true) {
                DBMoreInfoSectionItem dBMoreInfoSectionItem = null;
                if (!it.hasNext()) {
                    break;
                }
                MoreInfoSectionItem next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBMoreInfoSectionItem = (DBMoreInfoSectionItem) linkedHashMap.get(next2);
                }
                if (dBMoreInfoSectionItem == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBMoreInfoSectionItem dBMoreInfoSectionItem2 = (DBMoreInfoSectionItem) it2.next();
                        if (dBMoreInfoSectionItem2.getIdentifier().equals(next2.identifier)) {
                            dBMoreInfoSectionItem = dBMoreInfoSectionItem2;
                            break;
                        }
                    }
                }
                if (dBMoreInfoSectionItem == null) {
                    dBMoreInfoSectionItem = new DBMoreInfoSectionItem();
                    arrayList4.add(dBMoreInfoSectionItem);
                }
                dBMoreInfoSectionItem.setIdentifier(next2.identifier);
                dBMoreInfoSectionItem.setPosition(next2.position);
                dBMoreInfoSectionItem.setTitle(next2.title);
                dBMoreInfoSectionItem.setSubTitle(next2.subTitle);
                dBMoreInfoSectionItem.setFooter(next2.footer);
                dBMoreInfoSectionItem.setType(next2.type);
                dBMoreInfoSectionItem.setSource(next2.source);
                dBMoreInfoSectionItem.setLeftImage(next2.leftImage);
                dBMoreInfoSectionItem.setRightImage(next2.rightImage);
                dBMoreInfoSectionItem.setBackgroundColor(next2.backgroundColor);
                dBMoreInfoSectionItem.setColor(next2.color);
                dBMoreInfoSectionItem.setAccentColor(next2.accentColor);
                dBMoreInfoSectionItem.setTrackerId(next2.trackerId);
                dBMoreInfoSectionItem.setTrackerCategory(next2.trackerCategory);
                dBMoreInfoSectionItem.setTrackerEvent(next2.trackerEvent);
                dBMoreInfoSectionItem.setTrackerLabel(next2.trackerLabel);
                linkedHashMap.put(next2, dBMoreInfoSectionItem);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBMoreInfoSectionItemDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBMoreInfoSectionItem id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBMoreInfoSectionItem> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionItemDao(), DBMoreInfoSectionItemDao.Properties.MoreInfoSectionItemId, arrayList6);
            for (DBMoreInfoSectionItem moreInfoSectionItemId2 : allWithPropertyInData2) {
                moreInfoSectionItemId2.setMoreInfoSectionItemId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                MoreInfoSectionItem moreInfoSectionItem = (MoreInfoSectionItem) entry.getKey();
                DBMoreInfoSectionItem dBMoreInfoSectionItem3 = (DBMoreInfoSectionItem) entry.getValue();
                if (moreInfoSectionItem.subSectionItems != null && !moreInfoSectionItem.subSectionItems.isEmpty()) {
                    for (MoreInfoSectionItem next3 : moreInfoSectionItem.subSectionItems) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBMoreInfoSectionItem dBMoreInfoSectionItem4 = (DBMoreInfoSectionItem) it3.next();
                            if (dBMoreInfoSectionItem4.getIdentifier().equals(next3.identifier)) {
                                dBMoreInfoSectionItem4.setMoreInfoSectionItemId(dBMoreInfoSectionItem3.getId());
                                arrayList7.add(dBMoreInfoSectionItem4);
                                break;
                            }
                        }
                    }
                }
                dBMoreInfoSectionItem3.resetMoreInfoSectionItems();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBMoreInfoSectionItem dBMoreInfoSectionItem5 : allWithPropertyInData2) {
                if (dBMoreInfoSectionItem5.getMoreInfoSectionItemId() == null) {
                    arrayList8.add(dBMoreInfoSectionItem5);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBMoreInfoSectionItemDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBMoreInfoSectionItemDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBMoreInfoSectionItemDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
