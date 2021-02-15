package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem;
import com.wbmd.qxcalculator.model.db.DBExtraSectionDao;
import com.wbmd.qxcalculator.model.db.DBExtraSectionItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBExtraSectionItem {
    public static final String TAG = DBExtraSectionItem.class.getSimpleName();
    private String accentColor;
    private String backgroundColor;
    private String color;
    private String conditionFormula;
    private transient DaoSession daoSession;
    private Long extraSectionId;
    private Long extraSectionItemId;
    private List<DBExtraSectionItem> extraSectionItems;
    private String footer;
    private Boolean hideWhenNoResults;
    private Long id;
    private String identifier;
    private String leftImage;
    private transient DBExtraSectionItemDao myDao;
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

    public DBExtraSectionItem() {
    }

    public DBExtraSectionItem(Long l) {
        this.id = l;
    }

    public DBExtraSectionItem(Long l, String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, Boolean bool, String str16, Long l2, Long l3) {
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
        this.hideWhenNoResults = bool;
        this.conditionFormula = str16;
        this.extraSectionId = l2;
        this.extraSectionItemId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBExtraSectionItemDao() : null;
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

    public Boolean getHideWhenNoResults() {
        return this.hideWhenNoResults;
    }

    public void setHideWhenNoResults(Boolean bool) {
        this.hideWhenNoResults = bool;
    }

    public String getConditionFormula() {
        return this.conditionFormula;
    }

    public void setConditionFormula(String str) {
        this.conditionFormula = str;
    }

    public Long getExtraSectionId() {
        return this.extraSectionId;
    }

    public void setExtraSectionId(Long l) {
        this.extraSectionId = l;
    }

    public Long getExtraSectionItemId() {
        return this.extraSectionItemId;
    }

    public void setExtraSectionItemId(Long l) {
        this.extraSectionItemId = l;
    }

    public List<DBExtraSectionItem> getExtraSectionItems() {
        if (this.extraSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBExtraSectionItem> _queryDBExtraSectionItem_ExtraSectionItems = daoSession2.getDBExtraSectionItemDao()._queryDBExtraSectionItem_ExtraSectionItems(this.id);
                synchronized (this) {
                    if (this.extraSectionItems == null) {
                        this.extraSectionItems = _queryDBExtraSectionItem_ExtraSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.extraSectionItems;
    }

    public synchronized void resetExtraSectionItems() {
        this.extraSectionItems = null;
    }

    public void delete() {
        DBExtraSectionItemDao dBExtraSectionItemDao = this.myDao;
        if (dBExtraSectionItemDao != null) {
            dBExtraSectionItemDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBExtraSectionItemDao dBExtraSectionItemDao = this.myDao;
        if (dBExtraSectionItemDao != null) {
            dBExtraSectionItemDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBExtraSectionItemDao dBExtraSectionItemDao = this.myDao;
        if (dBExtraSectionItemDao != null) {
            dBExtraSectionItemDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedExtraSectionItems(DaoSession daoSession2) {
        List list = daoSession2.getDBExtraSectionItemDao().queryBuilder().where(DBExtraSectionItemDao.Properties.ExtraSectionId.isNull(), DBExtraSectionItemDao.Properties.ExtraSectionItemId.isNull()).list();
        String str = TAG;
        Log.d(str, "Purging DBExtraSectionItem: " + list.size());
        deleteExtraSectionItems(daoSession2, list);
    }

    public static void deleteExtraSectionItems(DaoSession daoSession2, List<DBExtraSectionItem> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (DBExtraSectionItem next : list) {
                arrayList.add(next.getId());
                if (next.getExtraSectionId() != null) {
                    arrayList2.add(next.getExtraSectionId());
                }
                if (next.getExtraSectionItemId() != null) {
                    arrayList3.add(next.getExtraSectionItemId());
                }
            }
            List<DBExtraSectionItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionItemDao(), DBExtraSectionItemDao.Properties.ExtraSectionItemId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBExtraSectionItem extraSectionItemId2 : allWithPropertyInData) {
                    extraSectionItemId2.setExtraSectionItemId((Long) null);
                }
                deleteExtraSectionItems(daoSession2, allWithPropertyInData);
            }
            if (!arrayList2.isEmpty()) {
                List<DBExtraSection> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionDao(), DBExtraSectionDao.Properties.Id, arrayList2);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBExtraSection resetExtraSectionItems : allWithPropertyInData2) {
                        resetExtraSectionItems.resetExtraSectionItems();
                    }
                }
            }
            if (!arrayList3.isEmpty()) {
                List<DBExtraSectionItem> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionItemDao(), DBExtraSectionItemDao.Properties.Id, arrayList3);
                if (!allWithPropertyInData3.isEmpty()) {
                    for (DBExtraSectionItem resetExtraSectionItems2 : allWithPropertyInData3) {
                        resetExtraSectionItems2.resetExtraSectionItems();
                    }
                }
            }
            daoSession2.getDBExtraSectionItemDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBExtraSectionItem insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBExtraSectionItem> r0 = com.wbmd.qxcalculator.model.db.DBExtraSectionItem.class
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
            com.wbmd.qxcalculator.model.db.DBExtraSectionItem r1 = (com.wbmd.qxcalculator.model.db.DBExtraSectionItem) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBExtraSectionItem.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem):com.wbmd.qxcalculator.model.db.DBExtraSectionItem");
    }

    public static synchronized List<DBExtraSectionItem> insertAndRetrieveDbEntities(DaoSession daoSession2, List<ExtraSectionItem> list) {
        synchronized (DBExtraSectionItem.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (ExtraSectionItem next : list) {
                arrayList2.add(next.identifier);
                if (next.subSectionItems != null) {
                    arrayList3.addAll(next.subSectionItems);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionItemDao(), DBExtraSectionItemDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (!arrayList3.isEmpty()) {
                arrayList5 = insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<ExtraSectionItem> it = list.iterator();
            while (true) {
                DBExtraSectionItem dBExtraSectionItem = null;
                if (!it.hasNext()) {
                    break;
                }
                ExtraSectionItem next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBExtraSectionItem = (DBExtraSectionItem) linkedHashMap.get(next2);
                }
                if (dBExtraSectionItem == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBExtraSectionItem dBExtraSectionItem2 = (DBExtraSectionItem) it2.next();
                        if (dBExtraSectionItem2.getIdentifier().equals(next2.identifier)) {
                            dBExtraSectionItem = dBExtraSectionItem2;
                            break;
                        }
                    }
                }
                if (dBExtraSectionItem == null) {
                    dBExtraSectionItem = new DBExtraSectionItem();
                    arrayList4.add(dBExtraSectionItem);
                }
                dBExtraSectionItem.setIdentifier(next2.identifier);
                dBExtraSectionItem.setPosition(next2.position);
                dBExtraSectionItem.setTitle(next2.title);
                dBExtraSectionItem.setSubTitle(next2.subTitle);
                dBExtraSectionItem.setFooter(next2.footer);
                dBExtraSectionItem.setType(next2.type);
                dBExtraSectionItem.setSource(next2.source);
                dBExtraSectionItem.setLeftImage(next2.leftImage);
                dBExtraSectionItem.setRightImage(next2.rightImage);
                dBExtraSectionItem.setBackgroundColor(next2.backgroundColor);
                dBExtraSectionItem.setColor(next2.color);
                dBExtraSectionItem.setAccentColor(next2.accentColor);
                dBExtraSectionItem.setTrackerId(next2.trackerId);
                dBExtraSectionItem.setTrackerCategory(next2.trackerCategory);
                dBExtraSectionItem.setTrackerEvent(next2.trackerEvent);
                dBExtraSectionItem.setTrackerLabel(next2.trackerLabel);
                dBExtraSectionItem.setHideWhenNoResults(next2.hideWhenNoResults);
                linkedHashMap.put(next2, dBExtraSectionItem);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBExtraSectionItemDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBExtraSectionItem id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBExtraSectionItem> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionItemDao(), DBExtraSectionItemDao.Properties.ExtraSectionItemId, arrayList6);
            for (DBExtraSectionItem extraSectionItemId2 : allWithPropertyInData2) {
                extraSectionItemId2.setExtraSectionItemId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                ExtraSectionItem extraSectionItem = (ExtraSectionItem) entry.getKey();
                DBExtraSectionItem dBExtraSectionItem3 = (DBExtraSectionItem) entry.getValue();
                if (extraSectionItem.subSectionItems != null && !extraSectionItem.subSectionItems.isEmpty()) {
                    for (ExtraSectionItem next3 : extraSectionItem.subSectionItems) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBExtraSectionItem dBExtraSectionItem4 = (DBExtraSectionItem) it3.next();
                            if (dBExtraSectionItem4.getIdentifier().equals(next3.identifier)) {
                                dBExtraSectionItem4.setExtraSectionItemId(dBExtraSectionItem3.getId());
                                arrayList7.add(dBExtraSectionItem4);
                                break;
                            }
                        }
                    }
                }
                dBExtraSectionItem3.resetExtraSectionItems();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBExtraSectionItem dBExtraSectionItem5 : allWithPropertyInData2) {
                if (dBExtraSectionItem5.getExtraSectionItemId() == null) {
                    arrayList8.add(dBExtraSectionItem5);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBExtraSectionItemDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBExtraSectionItemDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBExtraSectionItemDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
