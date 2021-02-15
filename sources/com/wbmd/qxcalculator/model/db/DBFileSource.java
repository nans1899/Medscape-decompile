package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.filesource.FileSource;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBFileSourceDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBFileSource {
    public static final String TAG = DBFileSource.class.getSimpleName();
    private Long id;
    private String identifier;
    private String source;
    private String type;

    public DBFileSource() {
    }

    public DBFileSource(Long l) {
        this.id = l;
    }

    public DBFileSource(Long l, String str, String str2, String str3) {
        this.id = l;
        this.identifier = str;
        this.source = str2;
        this.type = str3;
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

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public static void deleteUnusedFileSources(DaoSession daoSession) {
        List<DBContentItem> loadAll = daoSession.getDBContentItemDao().loadAll();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem dBContentItem : loadAll) {
            if (dBContentItem.getFileSourceId() != null) {
                arrayList.add(dBContentItem.getFileSourceId());
            }
        }
        List allWithPropertyNotInData = DatabaseHelper.getAllWithPropertyNotInData(daoSession.getDBFileSourceDao(), DBFileSourceDao.Properties.Id, arrayList);
        String str = TAG;
        Log.d(str, "Purging DBFileSource: " + allWithPropertyNotInData.size());
        deleteFileSources(daoSession, allWithPropertyNotInData);
    }

    public static void deleteFileSources(DaoSession daoSession, List<DBFileSource> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            for (DBFileSource id2 : list) {
                arrayList.add(id2.getId());
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBContentItemDao(), DBContentItemDao.Properties.FileSourceId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBContentItem fileSourceId : allWithPropertyInData) {
                    fileSourceId.setFileSourceId((Long) null);
                }
                daoSession.getDBContentItemDao().updateInTx(allWithPropertyInData);
            }
            daoSession.getDBFileSourceDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBFileSource insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.filesource.FileSource r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBFileSource> r0 = com.wbmd.qxcalculator.model.db.DBFileSource.class
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
            com.wbmd.qxcalculator.model.db.DBFileSource r1 = (com.wbmd.qxcalculator.model.db.DBFileSource) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBFileSource.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.filesource.FileSource):com.wbmd.qxcalculator.model.db.DBFileSource");
    }

    public static synchronized List<DBFileSource> insertAndRetrieveDbEntities(DaoSession daoSession, List<FileSource> list) {
        synchronized (DBFileSource.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (FileSource fileSource : list) {
                arrayList2.add(fileSource.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBFileSourceDao(), DBFileSourceDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (FileSource next : list) {
                DBFileSource dBFileSource = linkedHashMap.containsKey(next) ? (DBFileSource) linkedHashMap.get(next) : null;
                if (dBFileSource == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBFileSource dBFileSource2 = (DBFileSource) it.next();
                        if (dBFileSource2.getIdentifier().equals(next.identifier)) {
                            dBFileSource = dBFileSource2;
                            break;
                        }
                    }
                }
                if (dBFileSource == null) {
                    dBFileSource = new DBFileSource();
                    arrayList3.add(dBFileSource);
                }
                dBFileSource.setIdentifier(next.identifier);
                dBFileSource.setSource(next.source);
                dBFileSource.setType(next.type);
                linkedHashMap.put(next, dBFileSource);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBFileSourceDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBFileSourceDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
