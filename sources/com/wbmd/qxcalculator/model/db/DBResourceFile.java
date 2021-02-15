package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.common.ResourceFile;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBResourceFileDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBResourceFile {
    public static final String TAG = DBResourceFile.class.getSimpleName();
    private Double aspectRatio;
    private String baseName;
    private String content;
    private Long contentItemId;
    private String contentItemIdentifier;
    private String deviceType;
    private Double dipHeight;
    private Double dipWidth;
    private Long id;
    private String identifier;
    private Long lastModifiedDate;
    private String name;
    private Double scaleFactor;
    private Long size;
    private String src;
    private String type;

    public DBResourceFile() {
    }

    public DBResourceFile(Long l) {
        this.id = l;
    }

    public DBResourceFile(Long l, String str, String str2, Long l2, Long l3, String str3, String str4, Double d, Double d2, String str5, String str6, Double d3, Double d4, String str7, String str8, Long l4) {
        this.id = l;
        this.identifier = str;
        this.name = str2;
        this.lastModifiedDate = l2;
        this.size = l3;
        this.src = str3;
        this.type = str4;
        this.dipWidth = d;
        this.dipHeight = d2;
        this.baseName = str5;
        this.deviceType = str6;
        this.scaleFactor = d3;
        this.aspectRatio = d4;
        this.contentItemIdentifier = str7;
        this.content = str8;
        this.contentItemId = l4;
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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Long getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Long l) {
        this.lastModifiedDate = l;
    }

    public Long getSize() {
        return this.size;
    }

    public void setSize(Long l) {
        this.size = l;
    }

    public String getSrc() {
        return this.src;
    }

    public void setSrc(String str) {
        this.src = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Double getDipWidth() {
        return this.dipWidth;
    }

    public void setDipWidth(Double d) {
        this.dipWidth = d;
    }

    public Double getDipHeight() {
        return this.dipHeight;
    }

    public void setDipHeight(Double d) {
        this.dipHeight = d;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public void setBaseName(String str) {
        this.baseName = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public Double getScaleFactor() {
        return this.scaleFactor;
    }

    public void setScaleFactor(Double d) {
        this.scaleFactor = d;
    }

    public Double getAspectRatio() {
        return this.aspectRatio;
    }

    public void setAspectRatio(Double d) {
        this.aspectRatio = d;
    }

    public String getContentItemIdentifier() {
        return this.contentItemIdentifier;
    }

    public void setContentItemIdentifier(String str) {
        this.contentItemIdentifier = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public static void deleteUnusedResourceFiles(DaoSession daoSession) {
        List list = daoSession.getDBResourceFileDao().queryBuilder().where(DBResourceFileDao.Properties.ContentItemId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBResourceFile: " + list.size());
        deleteResourceFiles(daoSession, list);
    }

    public static void deleteResourceFiles(DaoSession daoSession, List<DBResourceFile> list) {
        DBContentItem dBContentItem;
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBResourceFile next : list) {
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                    next.setContentItemId((Long) null);
                }
            }
            List<DBContentItem> arrayList2 = new ArrayList<>();
            if (!arrayList.isEmpty()) {
                arrayList2 = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!arrayList2.isEmpty()) {
                    for (DBContentItem resetResourceFiles : arrayList2) {
                        resetResourceFiles.resetResourceFiles();
                    }
                }
            }
            HashMap hashMap = new HashMap();
            for (DBContentItem dBContentItem2 : arrayList2) {
                ArrayList arrayList3 = new ArrayList(dBContentItem2.getResourceFiles().size());
                for (DBResourceFile name2 : dBContentItem2.getResourceFiles()) {
                    arrayList3.add(name2.getName());
                }
                hashMap.put(dBContentItem2, arrayList3);
            }
            for (DBResourceFile next2 : list) {
                Iterator it = arrayList2.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        dBContentItem = null;
                        break;
                    }
                    dBContentItem = (DBContentItem) it.next();
                    if (dBContentItem.getId().equals(next2.getContentItemId())) {
                        break;
                    }
                }
                if (dBContentItem == null || hashMap.get(dBContentItem) == null) {
                    FileHelper.getInstance().deleteFileForResourceFile(next2);
                } else if (!((List) hashMap.get(dBContentItem)).contains(next2.getName())) {
                    FileHelper.getInstance().deleteFileForResourceFile(next2);
                }
            }
            daoSession.getDBResourceFileDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBResourceFile insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.common.ResourceFile r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBResourceFile> r0 = com.wbmd.qxcalculator.model.db.DBResourceFile.class
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
            com.wbmd.qxcalculator.model.db.DBResourceFile r1 = (com.wbmd.qxcalculator.model.db.DBResourceFile) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBResourceFile.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.common.ResourceFile):com.wbmd.qxcalculator.model.db.DBResourceFile");
    }

    public static synchronized List<DBResourceFile> insertAndRetrieveDbEntities(DaoSession daoSession, List<ResourceFile> list) {
        synchronized (DBResourceFile.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (ResourceFile resourceFile : list) {
                arrayList2.add(resourceFile.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBResourceFileDao(), DBResourceFileDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (ResourceFile next : list) {
                DBResourceFile dBResourceFile = linkedHashMap.containsKey(next) ? (DBResourceFile) linkedHashMap.get(next) : null;
                if (dBResourceFile == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBResourceFile dBResourceFile2 = (DBResourceFile) it.next();
                        if (dBResourceFile2.getIdentifier().equals(next.identifier)) {
                            dBResourceFile = dBResourceFile2;
                            break;
                        }
                    }
                }
                if (dBResourceFile == null) {
                    dBResourceFile = new DBResourceFile();
                    arrayList3.add(dBResourceFile);
                }
                dBResourceFile.setIdentifier(next.identifier);
                dBResourceFile.setName(next.name);
                dBResourceFile.setLastModifiedDate(next.lastModifiedDate);
                dBResourceFile.setSize(next.size);
                dBResourceFile.setSrc(next.srcId);
                dBResourceFile.setType(next.type);
                dBResourceFile.setDipWidth(next.dipWidth);
                dBResourceFile.setDipHeight(next.dipHeight);
                dBResourceFile.setBaseName(next.baseName);
                dBResourceFile.setDeviceType(next.deviceType);
                dBResourceFile.setScaleFactor(next.scaleFactor);
                dBResourceFile.setAspectRatio(next.aspectRatio);
                dBResourceFile.setContent(next.content);
                dBResourceFile.setContentItemIdentifier(next.contentItemIdentifier);
                linkedHashMap.put(next, dBResourceFile);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBResourceFileDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBResourceFileDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
