package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.AppConfiguration;
import com.wbmd.qxcalculator.model.contentItems.common.Platform;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBPlatformDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import com.wbmd.qxcalculator.util.Version;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBPlatform {
    public static final String TAG = DBPlatform.class.getSimpleName();
    private Long contentItemId;
    private String deviceType;
    private Long featuredContentAdId;
    private Long id;
    private String identifier;
    private String maxVersion;
    private String minVersion;
    private String os;
    private Long promotionId;
    private Long restrictionId;

    public DBPlatform() {
    }

    public DBPlatform(Long l) {
        this.id = l;
    }

    public DBPlatform(Long l, String str, String str2, String str3, String str4, String str5, Long l2, Long l3, Long l4, Long l5) {
        this.id = l;
        this.identifier = str;
        this.os = str2;
        this.deviceType = str3;
        this.minVersion = str4;
        this.maxVersion = str5;
        this.contentItemId = l2;
        this.featuredContentAdId = l3;
        this.restrictionId = l4;
        this.promotionId = l5;
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

    public String getOs() {
        return this.os;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getMinVersion() {
        return this.minVersion;
    }

    public void setMinVersion(String str) {
        this.minVersion = str;
    }

    public String getMaxVersion() {
        return this.maxVersion;
    }

    public void setMaxVersion(String str) {
        this.maxVersion = str;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public Long getFeaturedContentAdId() {
        return this.featuredContentAdId;
    }

    public void setFeaturedContentAdId(Long l) {
        this.featuredContentAdId = l;
    }

    public Long getRestrictionId() {
        return this.restrictionId;
    }

    public void setRestrictionId(Long l) {
        this.restrictionId = l;
    }

    public Long getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(Long l) {
        this.promotionId = l;
    }

    public static void deleteUnusedPlatforms(DaoSession daoSession) {
        List list = daoSession.getDBPlatformDao().queryBuilder().where(DBPlatformDao.Properties.ContentItemId.isNull(), DBPlatformDao.Properties.FeaturedContentAdId.isNull(), DBPlatformDao.Properties.RestrictionId.isNull()).list();
        String str = TAG;
        Log.d(str, "Purging DBPlatform: " + list.size());
        deletePlatforms(daoSession, list);
    }

    public static void deletePlatforms(DaoSession daoSession, List<DBPlatform> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBPlatform next : list) {
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBContentItem resetPlatforms : allWithPropertyInData) {
                        resetPlatforms.resetPlatforms();
                    }
                }
            }
            daoSession.getDBPlatformDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBPlatform insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.common.Platform r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBPlatform> r0 = com.wbmd.qxcalculator.model.db.DBPlatform.class
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
            com.wbmd.qxcalculator.model.db.DBPlatform r1 = (com.wbmd.qxcalculator.model.db.DBPlatform) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBPlatform.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.common.Platform):com.wbmd.qxcalculator.model.db.DBPlatform");
    }

    public static synchronized List<DBPlatform> insertAndRetrieveDbEntities(DaoSession daoSession, List<Platform> list) {
        synchronized (DBPlatform.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (Platform platform : list) {
                arrayList2.add(platform.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBPlatformDao(), DBPlatformDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Platform next : list) {
                DBPlatform dBPlatform = linkedHashMap.containsKey(next) ? (DBPlatform) linkedHashMap.get(next) : null;
                if (dBPlatform == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBPlatform dBPlatform2 = (DBPlatform) it.next();
                        if (dBPlatform2.getIdentifier().equals(next.identifier)) {
                            dBPlatform = dBPlatform2;
                            break;
                        }
                    }
                }
                if (dBPlatform == null) {
                    dBPlatform = new DBPlatform();
                    arrayList3.add(dBPlatform);
                }
                dBPlatform.setIdentifier(next.identifier);
                dBPlatform.setOs(next.os);
                dBPlatform.setDeviceType(next.deviceType);
                dBPlatform.setMinVersion(next.minVersion);
                dBPlatform.setMaxVersion(next.maxVersion);
                linkedHashMap.put(next, dBPlatform);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBPlatformDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBPlatformDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }

    public static boolean shouldIncludePlatforms(List<DBPlatform> list) {
        if (list != null && !list.isEmpty()) {
            try {
                Version version = new Version(AppConfiguration.getInstance().getAppBuildVersion());
                for (DBPlatform next : list) {
                    if (next != null && next.getOs() != null && next.getOs().equalsIgnoreCase(AppConfiguration.getInstance().getPlatformOsForContentItem())) {
                        if (next.getMinVersion() != null && !next.getMinVersion().isEmpty() && version.compareTo(new Version(next.getMinVersion())) < 0) {
                            return false;
                        }
                        if (next.getMaxVersion() == null || next.getMaxVersion().isEmpty() || version.compareTo(new Version(next.getMaxVersion())) <= 0) {
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
