package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.commonfile.CommonFile;
import com.wbmd.qxcalculator.model.db.DBCommonFileDao;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBCommonFile {
    public static final String TAG = DBCommonFile.class.getSimpleName();
    private Long id;
    private String identifier;

    public DBCommonFile() {
    }

    public DBCommonFile(Long l) {
        this.id = l;
    }

    public DBCommonFile(Long l, String str) {
        this.id = l;
        this.identifier = str;
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

    public static void deleteUnusedCommonFiles(DaoSession daoSession) {
        List<DBContentItem> loadAll = daoSession.getDBContentItemDao().loadAll();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem dBContentItem : loadAll) {
            if (dBContentItem.getCommonFileId() != null) {
                arrayList.add(dBContentItem.getCommonFileId());
            }
        }
        List allWithPropertyNotInData = DatabaseHelper.getAllWithPropertyNotInData(daoSession.getDBCommonFileDao(), DBCommonFileDao.Properties.Id, arrayList);
        String str = TAG;
        Log.d(str, "Purging DBCommonFile: " + allWithPropertyNotInData.size());
        deleteCommonFiles(daoSession, allWithPropertyNotInData);
    }

    public static void deleteCommonFiles(DaoSession daoSession, List<DBCommonFile> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            for (DBCommonFile id2 : list) {
                arrayList.add(id2.getId());
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBContentItemDao(), DBContentItemDao.Properties.CommonFileId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBContentItem commonFileId : allWithPropertyInData) {
                    commonFileId.setCommonFileId((Long) null);
                }
                daoSession.getDBContentItemDao().updateInTx(allWithPropertyInData);
            }
            daoSession.getDBCommonFileDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBCommonFile insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.commonfile.CommonFile r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBCommonFile> r0 = com.wbmd.qxcalculator.model.db.DBCommonFile.class
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
            com.wbmd.qxcalculator.model.db.DBCommonFile r1 = (com.wbmd.qxcalculator.model.db.DBCommonFile) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBCommonFile.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.commonfile.CommonFile):com.wbmd.qxcalculator.model.db.DBCommonFile");
    }

    public static synchronized List<DBCommonFile> insertAndRetrieveDbEntities(DaoSession daoSession, List<CommonFile> list) {
        synchronized (DBCommonFile.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (CommonFile commonFile : list) {
                arrayList2.add(commonFile.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBCommonFileDao(), DBCommonFileDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (CommonFile next : list) {
                DBCommonFile dBCommonFile = linkedHashMap.containsKey(next) ? (DBCommonFile) linkedHashMap.get(next) : null;
                if (dBCommonFile == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBCommonFile dBCommonFile2 = (DBCommonFile) it.next();
                        if (dBCommonFile2.getIdentifier().equals(next.identifier)) {
                            dBCommonFile = dBCommonFile2;
                            break;
                        }
                    }
                }
                if (dBCommonFile == null) {
                    dBCommonFile = new DBCommonFile();
                    arrayList3.add(dBCommonFile);
                }
                dBCommonFile.setIdentifier(next.identifier);
                linkedHashMap.put(next, dBCommonFile);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBCommonFileDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBCommonFileDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
