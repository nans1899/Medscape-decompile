package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem;
import com.wbmd.qxcalculator.model.db.DBReferenceBookSectionDao;
import com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBReferenceBookSectionItem {
    public static final String TAG = DBReferenceBookSectionItem.class.getSimpleName();
    private String accentColor;
    private String color;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private transient DBReferenceBookSectionItemDao myDao;
    private Long position;
    private Long referenceBookSectionId;
    private Long referenceBookSectionItemId;
    private List<DBReferenceBookSectionItem> referenceBookSubSectionItems;
    private String shareLink;
    private String source;
    private String sourceId;
    private String subTitle;
    private String title;

    public DBReferenceBookSectionItem() {
    }

    public DBReferenceBookSectionItem(Long l) {
        this.id = l;
    }

    public DBReferenceBookSectionItem(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, Long l2, String str8, Long l3, Long l4) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.subTitle = str3;
        this.color = str4;
        this.accentColor = str5;
        this.source = str6;
        this.sourceId = str7;
        this.position = l2;
        this.shareLink = str8;
        this.referenceBookSectionId = l3;
        this.referenceBookSectionItemId = l4;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBReferenceBookSectionItemDao() : null;
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

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(String str) {
        this.sourceId = str;
    }

    public Long getPosition() {
        return this.position;
    }

    public void setPosition(Long l) {
        this.position = l;
    }

    public String getShareLink() {
        return this.shareLink;
    }

    public void setShareLink(String str) {
        this.shareLink = str;
    }

    public Long getReferenceBookSectionId() {
        return this.referenceBookSectionId;
    }

    public void setReferenceBookSectionId(Long l) {
        this.referenceBookSectionId = l;
    }

    public Long getReferenceBookSectionItemId() {
        return this.referenceBookSectionItemId;
    }

    public void setReferenceBookSectionItemId(Long l) {
        this.referenceBookSectionItemId = l;
    }

    public List<DBReferenceBookSectionItem> getReferenceBookSubSectionItems() {
        if (this.referenceBookSubSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBReferenceBookSectionItem> _queryDBReferenceBookSectionItem_ReferenceBookSubSectionItems = daoSession2.getDBReferenceBookSectionItemDao()._queryDBReferenceBookSectionItem_ReferenceBookSubSectionItems(this.id);
                synchronized (this) {
                    if (this.referenceBookSubSectionItems == null) {
                        this.referenceBookSubSectionItems = _queryDBReferenceBookSectionItem_ReferenceBookSubSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.referenceBookSubSectionItems;
    }

    public synchronized void resetReferenceBookSubSectionItems() {
        this.referenceBookSubSectionItems = null;
    }

    public void delete() {
        DBReferenceBookSectionItemDao dBReferenceBookSectionItemDao = this.myDao;
        if (dBReferenceBookSectionItemDao != null) {
            dBReferenceBookSectionItemDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBReferenceBookSectionItemDao dBReferenceBookSectionItemDao = this.myDao;
        if (dBReferenceBookSectionItemDao != null) {
            dBReferenceBookSectionItemDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBReferenceBookSectionItemDao dBReferenceBookSectionItemDao = this.myDao;
        if (dBReferenceBookSectionItemDao != null) {
            dBReferenceBookSectionItemDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedReferenceBookSectionItems(DaoSession daoSession2) {
        List list = daoSession2.getDBReferenceBookSectionItemDao().queryBuilder().where(DBReferenceBookSectionItemDao.Properties.ReferenceBookSectionId.isNull(), DBReferenceBookSectionItemDao.Properties.ReferenceBookSectionItemId.isNull()).list();
        String str = TAG;
        Log.d(str, "Purging DBReferenceBookSectionItem: " + list.size());
        deleteReferenceBookSectionItems(daoSession2, list);
    }

    public static void deleteReferenceBookSectionItems(DaoSession daoSession2, List<DBReferenceBookSectionItem> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (DBReferenceBookSectionItem next : list) {
                arrayList.add(next.getId());
                if (next.getReferenceBookSectionId() != null) {
                    arrayList2.add(next.getReferenceBookSectionId());
                }
                if (next.getReferenceBookSectionItemId() != null) {
                    arrayList3.add(next.getReferenceBookSectionItemId());
                }
            }
            List<DBReferenceBookSectionItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionItemDao(), DBReferenceBookSectionItemDao.Properties.ReferenceBookSectionItemId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBReferenceBookSectionItem referenceBookSectionItemId2 : allWithPropertyInData) {
                    referenceBookSectionItemId2.setReferenceBookSectionItemId((Long) null);
                }
                deleteReferenceBookSectionItems(daoSession2, allWithPropertyInData);
            }
            if (!arrayList2.isEmpty()) {
                List<DBReferenceBookSection> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionDao(), DBReferenceBookSectionDao.Properties.Id, arrayList2);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBReferenceBookSection resetReferenceBookSectionItems : allWithPropertyInData2) {
                        resetReferenceBookSectionItems.resetReferenceBookSectionItems();
                    }
                }
            }
            if (!arrayList3.isEmpty()) {
                List<DBReferenceBookSectionItem> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionItemDao(), DBReferenceBookSectionItemDao.Properties.Id, arrayList3);
                if (!allWithPropertyInData3.isEmpty()) {
                    for (DBReferenceBookSectionItem resetReferenceBookSubSectionItems : allWithPropertyInData3) {
                        resetReferenceBookSubSectionItems.resetReferenceBookSubSectionItems();
                    }
                }
            }
            daoSession2.getDBReferenceBookSectionItemDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem> r0 = com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem.class
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
            com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem r1 = (com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem):com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItem");
    }

    public static synchronized List<DBReferenceBookSectionItem> insertAndRetrieveDbEntities(DaoSession daoSession2, List<ReferenceBookSectionItem> list) {
        synchronized (DBReferenceBookSectionItem.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (ReferenceBookSectionItem next : list) {
                arrayList2.add(next.identifier);
                if (next.subSectionItems != null) {
                    arrayList3.addAll(next.subSectionItems);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionItemDao(), DBReferenceBookSectionItemDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (!arrayList3.isEmpty()) {
                arrayList5 = insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<ReferenceBookSectionItem> it = list.iterator();
            while (true) {
                DBReferenceBookSectionItem dBReferenceBookSectionItem = null;
                if (!it.hasNext()) {
                    break;
                }
                ReferenceBookSectionItem next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBReferenceBookSectionItem = (DBReferenceBookSectionItem) linkedHashMap.get(next2);
                }
                if (dBReferenceBookSectionItem == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBReferenceBookSectionItem dBReferenceBookSectionItem2 = (DBReferenceBookSectionItem) it2.next();
                        if (dBReferenceBookSectionItem2.getIdentifier().equals(next2.identifier)) {
                            dBReferenceBookSectionItem = dBReferenceBookSectionItem2;
                            break;
                        }
                    }
                }
                if (dBReferenceBookSectionItem == null) {
                    dBReferenceBookSectionItem = new DBReferenceBookSectionItem();
                    arrayList4.add(dBReferenceBookSectionItem);
                }
                dBReferenceBookSectionItem.setIdentifier(next2.identifier);
                dBReferenceBookSectionItem.setTitle(next2.title);
                dBReferenceBookSectionItem.setSubTitle(next2.subTitle);
                dBReferenceBookSectionItem.setColor(next2.color);
                dBReferenceBookSectionItem.setAccentColor(next2.accentColor);
                dBReferenceBookSectionItem.setSource(next2.source);
                dBReferenceBookSectionItem.setSourceId(next2.sourceId);
                dBReferenceBookSectionItem.setPosition(next2.position);
                dBReferenceBookSectionItem.setShareLink(next2.shareLink);
                linkedHashMap.put(next2, dBReferenceBookSectionItem);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBReferenceBookSectionItemDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBReferenceBookSectionItem id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBReferenceBookSectionItem> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionItemDao(), DBReferenceBookSectionItemDao.Properties.ReferenceBookSectionItemId, arrayList6);
            for (DBReferenceBookSectionItem referenceBookSectionItemId2 : allWithPropertyInData2) {
                referenceBookSectionItemId2.setReferenceBookSectionItemId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                ReferenceBookSectionItem referenceBookSectionItem = (ReferenceBookSectionItem) entry.getKey();
                DBReferenceBookSectionItem dBReferenceBookSectionItem3 = (DBReferenceBookSectionItem) entry.getValue();
                if (referenceBookSectionItem.subSectionItems != null && !referenceBookSectionItem.subSectionItems.isEmpty()) {
                    for (ReferenceBookSectionItem next3 : referenceBookSectionItem.subSectionItems) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBReferenceBookSectionItem dBReferenceBookSectionItem4 = (DBReferenceBookSectionItem) it3.next();
                            if (dBReferenceBookSectionItem4.getIdentifier().equals(next3.identifier)) {
                                dBReferenceBookSectionItem4.setReferenceBookSectionItemId(dBReferenceBookSectionItem3.getId());
                                arrayList7.add(dBReferenceBookSectionItem4);
                                break;
                            }
                        }
                    }
                }
                dBReferenceBookSectionItem3.resetReferenceBookSubSectionItems();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBReferenceBookSectionItem dBReferenceBookSectionItem5 : allWithPropertyInData2) {
                if (dBReferenceBookSectionItem5.getReferenceBookSectionItemId() == null) {
                    arrayList8.add(dBReferenceBookSectionItem5);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBReferenceBookSectionItemDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBReferenceBookSectionItemDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBReferenceBookSectionItemDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
