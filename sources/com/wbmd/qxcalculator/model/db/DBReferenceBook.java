package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSection;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBReferenceBookDao;
import com.wbmd.qxcalculator.model.db.DBReferenceBookSectionDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBReferenceBook {
    public static final String TAG = DBReferenceBook.class.getSimpleName();
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private transient DBReferenceBookDao myDao;
    private List<DBReferenceBookSection> referenceBookSections;
    private String titlePageBackgroundColor;
    private String titlePageSource;

    public DBReferenceBook() {
    }

    public DBReferenceBook(Long l) {
        this.id = l;
    }

    public DBReferenceBook(Long l, String str, String str2, String str3) {
        this.id = l;
        this.identifier = str;
        this.titlePageSource = str2;
        this.titlePageBackgroundColor = str3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBReferenceBookDao() : null;
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

    public String getTitlePageSource() {
        return this.titlePageSource;
    }

    public void setTitlePageSource(String str) {
        this.titlePageSource = str;
    }

    public String getTitlePageBackgroundColor() {
        return this.titlePageBackgroundColor;
    }

    public void setTitlePageBackgroundColor(String str) {
        this.titlePageBackgroundColor = str;
    }

    public List<DBReferenceBookSection> getReferenceBookSections() {
        if (this.referenceBookSections == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBReferenceBookSection> _queryDBReferenceBook_ReferenceBookSections = daoSession2.getDBReferenceBookSectionDao()._queryDBReferenceBook_ReferenceBookSections(this.id);
                synchronized (this) {
                    if (this.referenceBookSections == null) {
                        this.referenceBookSections = _queryDBReferenceBook_ReferenceBookSections;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.referenceBookSections;
    }

    public synchronized void resetReferenceBookSections() {
        this.referenceBookSections = null;
    }

    public void delete() {
        DBReferenceBookDao dBReferenceBookDao = this.myDao;
        if (dBReferenceBookDao != null) {
            dBReferenceBookDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBReferenceBookDao dBReferenceBookDao = this.myDao;
        if (dBReferenceBookDao != null) {
            dBReferenceBookDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBReferenceBookDao dBReferenceBookDao = this.myDao;
        if (dBReferenceBookDao != null) {
            dBReferenceBookDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedReferenceBooks(DaoSession daoSession2) {
        List<DBContentItem> loadAll = daoSession2.getDBContentItemDao().loadAll();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem dBContentItem : loadAll) {
            if (dBContentItem.getReferenceBookId() != null) {
                arrayList.add(dBContentItem.getReferenceBookId());
            }
        }
        List allWithPropertyNotInData = DatabaseHelper.getAllWithPropertyNotInData(daoSession2.getDBReferenceBookDao(), DBReferenceBookDao.Properties.Id, arrayList);
        String str = TAG;
        Log.d(str, "Purging DBReferenceBooks: " + allWithPropertyNotInData.size());
        deleteReferenceBooks(daoSession2, allWithPropertyNotInData);
    }

    public static void deleteReferenceBooks(DaoSession daoSession2, List<DBReferenceBook> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            for (DBReferenceBook next : list) {
                arrayList.add(next.getId());
                next.resetReferenceBookSections();
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.ReferenceBookId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBContentItem referenceBookId : allWithPropertyInData) {
                    referenceBookId.setReferenceBookId((Long) null);
                }
                daoSession2.getDBContentItemDao().updateInTx(allWithPropertyInData);
            }
            List<DBReferenceBookSection> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionDao(), DBReferenceBookSectionDao.Properties.ReferenceBookId, arrayList);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBReferenceBookSection referenceBookId2 : allWithPropertyInData2) {
                    referenceBookId2.setReferenceBookId((Long) null);
                }
                DBReferenceBookSection.deleteReferenceBookSections(daoSession2, allWithPropertyInData2);
            }
            daoSession2.getDBReferenceBookDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBReferenceBook insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBReferenceBook> r0 = com.wbmd.qxcalculator.model.db.DBReferenceBook.class
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
            com.wbmd.qxcalculator.model.db.DBReferenceBook r1 = (com.wbmd.qxcalculator.model.db.DBReferenceBook) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBReferenceBook.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook):com.wbmd.qxcalculator.model.db.DBReferenceBook");
    }

    public static synchronized List<DBReferenceBook> insertAndRetrieveDbEntities(DaoSession daoSession2, List<ReferenceBook> list) {
        synchronized (DBReferenceBook.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (ReferenceBook next : list) {
                arrayList2.add(next.identifier);
                if (next.sections != null) {
                    arrayList3.addAll(next.sections);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookDao(), DBReferenceBookDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (arrayList3.size() > 0) {
                arrayList5 = DBReferenceBookSection.insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<ReferenceBook> it = list.iterator();
            while (true) {
                DBReferenceBook dBReferenceBook = null;
                if (!it.hasNext()) {
                    break;
                }
                ReferenceBook next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBReferenceBook = (DBReferenceBook) linkedHashMap.get(next2);
                }
                if (dBReferenceBook == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBReferenceBook dBReferenceBook2 = (DBReferenceBook) it2.next();
                        if (dBReferenceBook2.getIdentifier().equals(next2.identifier)) {
                            dBReferenceBook = dBReferenceBook2;
                            break;
                        }
                    }
                }
                if (dBReferenceBook == null) {
                    dBReferenceBook = new DBReferenceBook();
                    arrayList4.add(dBReferenceBook);
                }
                dBReferenceBook.setIdentifier(next2.identifier);
                dBReferenceBook.setTitlePageSource(next2.titlePageSource);
                dBReferenceBook.setTitlePageBackgroundColor(next2.titlePageBackgroundColor);
                linkedHashMap.put(next2, dBReferenceBook);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBReferenceBookDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBReferenceBook id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBReferenceBookSection> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBReferenceBookSectionDao(), DBReferenceBookSectionDao.Properties.ReferenceBookId, arrayList6);
            for (DBReferenceBookSection referenceBookId : allWithPropertyInData2) {
                referenceBookId.setReferenceBookId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                ReferenceBook referenceBook = (ReferenceBook) entry.getKey();
                DBReferenceBook dBReferenceBook3 = (DBReferenceBook) entry.getValue();
                if (referenceBook.sections != null && !referenceBook.sections.isEmpty()) {
                    for (ReferenceBookSection next3 : referenceBook.sections) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBReferenceBookSection dBReferenceBookSection = (DBReferenceBookSection) it3.next();
                            if (dBReferenceBookSection.getIdentifier().equals(next3.identifier)) {
                                dBReferenceBookSection.setReferenceBookId(dBReferenceBook3.getId());
                                arrayList7.add(dBReferenceBookSection);
                                break;
                            }
                        }
                    }
                }
                dBReferenceBook3.resetReferenceBookSections();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBReferenceBookSection dBReferenceBookSection2 : allWithPropertyInData2) {
                if (dBReferenceBookSection2.getReferenceBookId() == null) {
                    arrayList8.add(dBReferenceBookSection2);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBReferenceBookSectionDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBReferenceBookSectionDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBReferenceBookDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
