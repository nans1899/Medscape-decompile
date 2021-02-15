package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.common.ItemFileZip;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBItemFileZipDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBItemFileZip {
    public static final String TAG = DBItemFileZip.class.getSimpleName();
    private Long id;
    private String identifier;
    private Long size;
    private String url;

    public DBItemFileZip() {
    }

    public DBItemFileZip(Long l) {
        this.id = l;
    }

    public DBItemFileZip(Long l, String str, String str2, Long l2) {
        this.id = l;
        this.identifier = str;
        this.url = str2;
        this.size = l2;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long l) {
        this.size = l;
    }

    public static void deleteUnusedItemFileZips(DaoSession daoSession) {
        List<DBContentItem> loadAll = daoSession.getDBContentItemDao().loadAll();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem dBContentItem : loadAll) {
            if (dBContentItem.getItemFileZipId() != null) {
                arrayList.add(dBContentItem.getItemFileZipId());
            }
        }
        List allWithPropertyNotInData = DatabaseHelper.getAllWithPropertyNotInData(daoSession.getDBItemFileZipDao(), DBItemFileZipDao.Properties.Id, arrayList);
        String str = TAG;
        Log.d(str, "Purging DBItemFileZip: " + allWithPropertyNotInData.size());
        deleteItemFileZips(daoSession, allWithPropertyNotInData);
    }

    public static void deleteItemFileZips(DaoSession daoSession, List<DBItemFileZip> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBItemFileZip id2 : list) {
                arrayList.add(id2.getId());
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBContentItemDao(), DBContentItemDao.Properties.ItemFileZipId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBContentItem itemFileZipId : allWithPropertyInData) {
                    itemFileZipId.setItemFileZipId((Long) null);
                }
                daoSession.getDBContentItemDao().updateInTx(allWithPropertyInData);
            }
            daoSession.getDBItemFileZipDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBItemFileZip insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.common.ItemFileZip r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBItemFileZip> r0 = com.wbmd.qxcalculator.model.db.DBItemFileZip.class
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
            com.wbmd.qxcalculator.model.db.DBItemFileZip r1 = (com.wbmd.qxcalculator.model.db.DBItemFileZip) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBItemFileZip.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.common.ItemFileZip):com.wbmd.qxcalculator.model.db.DBItemFileZip");
    }

    public static synchronized List<DBItemFileZip> insertAndRetrieveDbEntities(DaoSession daoSession, List<ItemFileZip> list) {
        synchronized (DBItemFileZip.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (ItemFileZip itemFileZip : list) {
                arrayList2.add(itemFileZip.url);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBItemFileZipDao(), DBItemFileZipDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (ItemFileZip next : list) {
                DBItemFileZip dBItemFileZip = linkedHashMap.containsKey(next) ? (DBItemFileZip) linkedHashMap.get(next) : null;
                if (dBItemFileZip == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBItemFileZip dBItemFileZip2 = (DBItemFileZip) it.next();
                        if (dBItemFileZip2.getIdentifier().equals(next.url)) {
                            dBItemFileZip = dBItemFileZip2;
                            break;
                        }
                    }
                }
                if (dBItemFileZip == null) {
                    dBItemFileZip = new DBItemFileZip();
                    arrayList3.add(dBItemFileZip);
                }
                dBItemFileZip.setIdentifier(next.url);
                dBItemFileZip.setUrl(next.url);
                dBItemFileZip.setSize(next.size);
                linkedHashMap.put(next, dBItemFileZip);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBItemFileZipDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBItemFileZipDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
