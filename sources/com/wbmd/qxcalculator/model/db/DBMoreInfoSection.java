package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection;
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSectionItem;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBMoreInfoSectionDao;
import com.wbmd.qxcalculator.model.db.DBMoreInfoSectionItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBMoreInfoSection {
    public static final String TAG = DBMoreInfoSection.class.getSimpleName();
    private Long contentItemId;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private List<DBMoreInfoSectionItem> moreInfoSectionItems;
    private transient DBMoreInfoSectionDao myDao;
    private Integer position;
    private String subTitle;
    private String title;

    public DBMoreInfoSection() {
    }

    public DBMoreInfoSection(Long l) {
        this.id = l;
    }

    public DBMoreInfoSection(Long l, String str, String str2, String str3, Integer num, Long l2) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.subTitle = str3;
        this.position = num;
        this.contentItemId = l2;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBMoreInfoSectionDao() : null;
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

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public List<DBMoreInfoSectionItem> getMoreInfoSectionItems() {
        if (this.moreInfoSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBMoreInfoSectionItem> _queryDBMoreInfoSection_MoreInfoSectionItems = daoSession2.getDBMoreInfoSectionItemDao()._queryDBMoreInfoSection_MoreInfoSectionItems(this.id);
                synchronized (this) {
                    if (this.moreInfoSectionItems == null) {
                        this.moreInfoSectionItems = _queryDBMoreInfoSection_MoreInfoSectionItems;
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
        DBMoreInfoSectionDao dBMoreInfoSectionDao = this.myDao;
        if (dBMoreInfoSectionDao != null) {
            dBMoreInfoSectionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBMoreInfoSectionDao dBMoreInfoSectionDao = this.myDao;
        if (dBMoreInfoSectionDao != null) {
            dBMoreInfoSectionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBMoreInfoSectionDao dBMoreInfoSectionDao = this.myDao;
        if (dBMoreInfoSectionDao != null) {
            dBMoreInfoSectionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedMoreInfoSections(DaoSession daoSession2) {
        List list = daoSession2.getDBMoreInfoSectionDao().queryBuilder().where(DBMoreInfoSectionDao.Properties.ContentItemId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBMoreInfoSection: " + list.size());
        deleteMoreInfoSections(daoSession2, list);
    }

    public static void deleteMoreInfoSections(DaoSession daoSession2, List<DBMoreInfoSection> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            for (DBMoreInfoSection next : list) {
                arrayList2.add(next.getId());
                next.resetMoreInfoSectionItems();
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                }
            }
            List<DBMoreInfoSectionItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionItemDao(), DBMoreInfoSectionItemDao.Properties.MoreInfoSectionId, arrayList2);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBMoreInfoSectionItem moreInfoSectionId : allWithPropertyInData) {
                    moreInfoSectionId.setMoreInfoSectionId((Long) null);
                }
                DBMoreInfoSectionItem.deleteMoreInfoSectionItems(daoSession2, allWithPropertyInData);
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBContentItem resetMoreInfoSections : allWithPropertyInData2) {
                        resetMoreInfoSections.resetMoreInfoSections();
                    }
                }
            }
            daoSession2.getDBMoreInfoSectionDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBMoreInfoSection insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBMoreInfoSection> r0 = com.wbmd.qxcalculator.model.db.DBMoreInfoSection.class
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
            com.wbmd.qxcalculator.model.db.DBMoreInfoSection r1 = (com.wbmd.qxcalculator.model.db.DBMoreInfoSection) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBMoreInfoSection.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection):com.wbmd.qxcalculator.model.db.DBMoreInfoSection");
    }

    public static synchronized List<DBMoreInfoSection> insertAndRetrieveDbEntities(DaoSession daoSession2, List<MoreInfoSection> list) {
        synchronized (DBMoreInfoSection.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (MoreInfoSection next : list) {
                arrayList2.add(next.identifier);
                if (next.sectionItems != null) {
                    arrayList3.addAll(next.sectionItems);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionDao(), DBMoreInfoSectionDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (arrayList3.size() > 0) {
                arrayList5 = DBMoreInfoSectionItem.insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<MoreInfoSection> it = list.iterator();
            while (true) {
                DBMoreInfoSection dBMoreInfoSection = null;
                if (!it.hasNext()) {
                    break;
                }
                MoreInfoSection next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBMoreInfoSection = (DBMoreInfoSection) linkedHashMap.get(next2);
                }
                if (dBMoreInfoSection == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBMoreInfoSection dBMoreInfoSection2 = (DBMoreInfoSection) it2.next();
                        if (dBMoreInfoSection2.getIdentifier().equals(next2.identifier)) {
                            dBMoreInfoSection = dBMoreInfoSection2;
                            break;
                        }
                    }
                }
                if (dBMoreInfoSection == null) {
                    dBMoreInfoSection = new DBMoreInfoSection();
                    arrayList4.add(dBMoreInfoSection);
                }
                dBMoreInfoSection.setIdentifier(next2.identifier);
                dBMoreInfoSection.setTitle(next2.title);
                dBMoreInfoSection.setSubTitle(next2.subTitle);
                dBMoreInfoSection.setPosition(next2.position);
                linkedHashMap.put(next2, dBMoreInfoSection);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBMoreInfoSectionDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBMoreInfoSection id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBMoreInfoSectionItem> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionItemDao(), DBMoreInfoSectionItemDao.Properties.MoreInfoSectionId, arrayList6);
            for (DBMoreInfoSectionItem moreInfoSectionId : allWithPropertyInData2) {
                moreInfoSectionId.setMoreInfoSectionId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                MoreInfoSection moreInfoSection = (MoreInfoSection) entry.getKey();
                DBMoreInfoSection dBMoreInfoSection3 = (DBMoreInfoSection) entry.getValue();
                if (moreInfoSection.sectionItems != null && !moreInfoSection.sectionItems.isEmpty()) {
                    for (MoreInfoSectionItem next3 : moreInfoSection.sectionItems) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBMoreInfoSectionItem dBMoreInfoSectionItem = (DBMoreInfoSectionItem) it3.next();
                            if (dBMoreInfoSectionItem.getIdentifier().equals(next3.identifier)) {
                                dBMoreInfoSectionItem.setMoreInfoSectionId(dBMoreInfoSection3.getId());
                                arrayList7.add(dBMoreInfoSectionItem);
                                break;
                            }
                        }
                    }
                }
                dBMoreInfoSection3.resetMoreInfoSectionItems();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBMoreInfoSectionItem dBMoreInfoSectionItem2 : allWithPropertyInData2) {
                if (dBMoreInfoSectionItem2.getMoreInfoSectionId() == null) {
                    arrayList8.add(dBMoreInfoSectionItem2);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBMoreInfoSectionItemDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBMoreInfoSectionItemDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBMoreInfoSectionDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
