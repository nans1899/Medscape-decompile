package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSection;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem;
import com.wbmd.qxcalculator.model.db.DBReferenceBookDao;
import com.wbmd.qxcalculator.model.db.DBReferenceBookSectionDao;
import com.wbmd.qxcalculator.model.db.DBReferenceBookSectionItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBReferenceBookSection {
    public static final String TAG = DBReferenceBookSection.class.getSimpleName();
    private transient DaoSession daoSession;
    private String footer;
    private Long id;
    private String identifier;
    private transient DBReferenceBookSectionDao myDao;
    private Long position;
    private Long referenceBookId;
    private List<DBReferenceBookSectionItem> referenceBookSectionItems;
    private String subTitle;
    private String title;

    public DBReferenceBookSection() {
    }

    public DBReferenceBookSection(Long l) {
        this.id = l;
    }

    public DBReferenceBookSection(Long l, String str, String str2, String str3, String str4, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.subTitle = str3;
        this.footer = str4;
        this.position = l2;
        this.referenceBookId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBReferenceBookSectionDao() : null;
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

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public Long getPosition() {
        return this.position;
    }

    public void setPosition(Long l) {
        this.position = l;
    }

    public Long getReferenceBookId() {
        return this.referenceBookId;
    }

    public void setReferenceBookId(Long l) {
        this.referenceBookId = l;
    }

    public List<DBReferenceBookSectionItem> getReferenceBookSectionItems() {
        if (this.referenceBookSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBReferenceBookSectionItem> _queryDBReferenceBookSection_ReferenceBookSectionItems = daoSession2.getDBReferenceBookSectionItemDao()._queryDBReferenceBookSection_ReferenceBookSectionItems(this.id);
                synchronized (this) {
                    if (this.referenceBookSectionItems == null) {
                        this.referenceBookSectionItems = _queryDBReferenceBookSection_ReferenceBookSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.referenceBookSectionItems;
    }

    public synchronized void resetReferenceBookSectionItems() {
        this.referenceBookSectionItems = null;
    }

    public void delete() {
        DBReferenceBookSectionDao dBReferenceBookSectionDao = this.myDao;
        if (dBReferenceBookSectionDao != null) {
            dBReferenceBookSectionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBReferenceBookSectionDao dBReferenceBookSectionDao = this.myDao;
        if (dBReferenceBookSectionDao != null) {
            dBReferenceBookSectionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBReferenceBookSectionDao dBReferenceBookSectionDao = this.myDao;
        if (dBReferenceBookSectionDao != null) {
            dBReferenceBookSectionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedReferenceBookSections(DaoSession daoSession2) {
        List list = daoSession2.getDBReferenceBookSectionDao().queryBuilder().where(DBReferenceBookSectionDao.Properties.ReferenceBookId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBReferenceBookSection: " + list.size());
        deleteReferenceBookSections(daoSession2, list);
    }

    public static void deleteReferenceBookSections(DaoSession daoSession2, List<DBReferenceBookSection> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            for (DBReferenceBookSection next : list) {
                arrayList2.add(next.getId());
                next.resetReferenceBookSectionItems();
                if (next.getReferenceBookId() != null) {
                    arrayList.add(next.getReferenceBookId());
                }
            }
            List<DBReferenceBookSectionItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionItemDao(), DBReferenceBookSectionItemDao.Properties.ReferenceBookSectionId, arrayList2);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBReferenceBookSectionItem referenceBookSectionId : allWithPropertyInData) {
                    referenceBookSectionId.setReferenceBookSectionId((Long) null);
                }
                DBReferenceBookSectionItem.deleteReferenceBookSectionItems(daoSession2, allWithPropertyInData);
            }
            if (!arrayList.isEmpty()) {
                List<DBReferenceBook> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookDao(), DBReferenceBookDao.Properties.Id, arrayList);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBReferenceBook resetReferenceBookSections : allWithPropertyInData2) {
                        resetReferenceBookSections.resetReferenceBookSections();
                    }
                }
            }
            daoSession2.getDBReferenceBookSectionDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBReferenceBookSection insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSection r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBReferenceBookSection> r0 = com.wbmd.qxcalculator.model.db.DBReferenceBookSection.class
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
            com.wbmd.qxcalculator.model.db.DBReferenceBookSection r1 = (com.wbmd.qxcalculator.model.db.DBReferenceBookSection) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBReferenceBookSection.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSection):com.wbmd.qxcalculator.model.db.DBReferenceBookSection");
    }

    public static synchronized List<DBReferenceBookSection> insertAndRetrieveDbEntities(DaoSession daoSession2, List<ReferenceBookSection> list) {
        synchronized (DBReferenceBookSection.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (ReferenceBookSection next : list) {
                arrayList2.add(next.identifier);
                if (next.sectionItems != null) {
                    arrayList3.addAll(next.sectionItems);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionDao(), DBReferenceBookSectionDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (arrayList3.size() > 0) {
                arrayList5 = DBReferenceBookSectionItem.insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<ReferenceBookSection> it = list.iterator();
            while (true) {
                DBReferenceBookSection dBReferenceBookSection = null;
                if (!it.hasNext()) {
                    break;
                }
                ReferenceBookSection next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBReferenceBookSection = (DBReferenceBookSection) linkedHashMap.get(next2);
                }
                if (dBReferenceBookSection == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBReferenceBookSection dBReferenceBookSection2 = (DBReferenceBookSection) it2.next();
                        if (dBReferenceBookSection2.getIdentifier().equals(next2.identifier)) {
                            dBReferenceBookSection = dBReferenceBookSection2;
                            break;
                        }
                    }
                }
                if (dBReferenceBookSection == null) {
                    dBReferenceBookSection = new DBReferenceBookSection();
                    arrayList4.add(dBReferenceBookSection);
                }
                dBReferenceBookSection.setIdentifier(next2.identifier);
                dBReferenceBookSection.setTitle(next2.title);
                dBReferenceBookSection.setSubTitle(next2.subTitle);
                dBReferenceBookSection.setFooter(next2.footer);
                dBReferenceBookSection.setPosition(next2.position);
                linkedHashMap.put(next2, dBReferenceBookSection);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBReferenceBookSectionDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBReferenceBookSection id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBReferenceBookSectionItem> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionItemDao(), DBReferenceBookSectionItemDao.Properties.ReferenceBookSectionId, arrayList6);
            for (DBReferenceBookSectionItem referenceBookSectionId : allWithPropertyInData2) {
                referenceBookSectionId.setReferenceBookSectionId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                ReferenceBookSection referenceBookSection = (ReferenceBookSection) entry.getKey();
                DBReferenceBookSection dBReferenceBookSection3 = (DBReferenceBookSection) entry.getValue();
                if (referenceBookSection.sectionItems != null && !referenceBookSection.sectionItems.isEmpty()) {
                    for (ReferenceBookSectionItem next3 : referenceBookSection.sectionItems) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBReferenceBookSectionItem dBReferenceBookSectionItem = (DBReferenceBookSectionItem) it3.next();
                            if (dBReferenceBookSectionItem.getIdentifier().equals(next3.identifier)) {
                                dBReferenceBookSectionItem.setReferenceBookSectionId(dBReferenceBookSection3.getId());
                                arrayList7.add(dBReferenceBookSectionItem);
                                break;
                            }
                        }
                    }
                }
                dBReferenceBookSection3.resetReferenceBookSectionItems();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBReferenceBookSectionItem dBReferenceBookSectionItem2 : allWithPropertyInData2) {
                if (dBReferenceBookSectionItem2.getReferenceBookSectionId() == null) {
                    arrayList8.add(dBReferenceBookSectionItem2);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBReferenceBookSectionItemDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBReferenceBookSectionItemDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBReferenceBookSectionDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
