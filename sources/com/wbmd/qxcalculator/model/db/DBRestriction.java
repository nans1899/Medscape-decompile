package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.common.Filter;
import com.wbmd.qxcalculator.model.contentItems.common.Platform;
import com.wbmd.qxcalculator.model.contentItems.common.Restriction;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBPlatformDao;
import com.wbmd.qxcalculator.model.db.DBRestrictionDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBRestriction {
    public static final String TAG = DBRestriction.class.getSimpleName();
    private String alertMessage;
    private String alertTitle;
    private String alternateUrl;
    private Long contentItemId;
    private transient DaoSession daoSession;
    private List<DBFilter> filters;
    private Long id;
    private String identifier;
    private transient DBRestrictionDao myDao;
    private List<DBPlatform> platforms;

    public DBRestriction() {
    }

    public DBRestriction(Long l) {
        this.id = l;
    }

    public DBRestriction(Long l, String str, String str2, String str3, String str4, Long l2) {
        this.id = l;
        this.identifier = str;
        this.alertTitle = str2;
        this.alertMessage = str3;
        this.alternateUrl = str4;
        this.contentItemId = l2;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBRestrictionDao() : null;
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

    public String getAlertTitle() {
        return this.alertTitle;
    }

    public void setAlertTitle(String str) {
        this.alertTitle = str;
    }

    public String getAlertMessage() {
        return this.alertMessage;
    }

    public void setAlertMessage(String str) {
        this.alertMessage = str;
    }

    public String getAlternateUrl() {
        return this.alternateUrl;
    }

    public void setAlternateUrl(String str) {
        this.alternateUrl = str;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public List<DBPlatform> getPlatforms() {
        if (this.platforms == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBPlatform> _queryDBRestriction_Platforms = daoSession2.getDBPlatformDao()._queryDBRestriction_Platforms(this.id);
                synchronized (this) {
                    if (this.platforms == null) {
                        this.platforms = _queryDBRestriction_Platforms;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.platforms;
    }

    public synchronized void resetPlatforms() {
        this.platforms = null;
    }

    public List<DBFilter> getFilters() {
        if (this.filters == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBFilter> _queryDBRestriction_Filters = daoSession2.getDBFilterDao()._queryDBRestriction_Filters(this.id);
                synchronized (this) {
                    if (this.filters == null) {
                        this.filters = _queryDBRestriction_Filters;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.filters;
    }

    public synchronized void resetFilters() {
        this.filters = null;
    }

    public void delete() {
        DBRestrictionDao dBRestrictionDao = this.myDao;
        if (dBRestrictionDao != null) {
            dBRestrictionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBRestrictionDao dBRestrictionDao = this.myDao;
        if (dBRestrictionDao != null) {
            dBRestrictionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBRestrictionDao dBRestrictionDao = this.myDao;
        if (dBRestrictionDao != null) {
            dBRestrictionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void preloadFilterRelations(DaoSession daoSession2, List<DBRestriction> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.RestrictionId, list2);
        for (DBRestriction next : list) {
            next.filters = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBFilter dBFilter = (DBFilter) it.next();
                if (dBFilter.getRestrictionId().equals(next.getId())) {
                    next.filters.add(dBFilter);
                    it.remove();
                }
            }
        }
        DBFilter.preloadRelations(daoSession2, allWithPropertyInData);
    }

    public static void preloadPlatformRelations(DaoSession daoSession2, List<DBRestriction> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.RestrictionId, list2);
        for (DBRestriction next : list) {
            next.platforms = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBPlatform dBPlatform = (DBPlatform) it.next();
                if (dBPlatform.getRestrictionId().equals(next.getId())) {
                    next.platforms.add(dBPlatform);
                    it.remove();
                }
            }
        }
    }

    public static void deleteUnusedRestrictions(DaoSession daoSession2) {
        List list = daoSession2.getDBRestrictionDao().queryBuilder().where(DBRestrictionDao.Properties.ContentItemId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBRestriction: " + list.size());
        deleteRestrictions(daoSession2, list);
    }

    public static void deleteRestrictions(DaoSession daoSession2, List<DBRestriction> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            for (DBRestriction next : list) {
                arrayList2.add(next.getId());
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                }
            }
            List<DBFilter> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.RestrictionId, arrayList2);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBFilter restrictionId : allWithPropertyInData) {
                    restrictionId.setRestrictionId((Long) null);
                }
                DBFilter.deleteFilters(daoSession2, allWithPropertyInData);
            }
            List<DBPlatform> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.RestrictionId, arrayList2);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBPlatform restrictionId2 : allWithPropertyInData2) {
                    restrictionId2.setRestrictionId((Long) null);
                }
                DBPlatform.deletePlatforms(daoSession2, allWithPropertyInData2);
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData3.isEmpty()) {
                    for (DBContentItem resetRestrictions : allWithPropertyInData3) {
                        resetRestrictions.resetRestrictions();
                    }
                }
            }
            daoSession2.getDBRestrictionDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBRestriction insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.common.Restriction r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBRestriction> r0 = com.wbmd.qxcalculator.model.db.DBRestriction.class
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
            com.wbmd.qxcalculator.model.db.DBRestriction r1 = (com.wbmd.qxcalculator.model.db.DBRestriction) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBRestriction.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.common.Restriction):com.wbmd.qxcalculator.model.db.DBRestriction");
    }

    public static synchronized List<DBRestriction> insertAndRetrieveDbEntities(DaoSession daoSession2, List<Restriction> list) {
        List list2;
        DaoSession daoSession3 = daoSession2;
        synchronized (DBRestriction.class) {
            if (daoSession3 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (Restriction next : list) {
                arrayList2.add(next.identifier);
                if (next.platforms != null) {
                    arrayList3.addAll(next.platforms);
                }
                if (next.filters != null) {
                    arrayList4.addAll(next.filters);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBRestrictionDao(), DBRestrictionDao.Properties.Identifier, arrayList2);
            ArrayList arrayList5 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList6 = new ArrayList();
            if (!arrayList3.isEmpty()) {
                arrayList6 = DBPlatform.insertAndRetrieveDbEntities(daoSession3, arrayList3);
            }
            List arrayList7 = new ArrayList();
            if (!arrayList4.isEmpty()) {
                arrayList7 = DBFilter.insertAndRetrieveDbEntities(daoSession3, arrayList4);
            }
            Iterator<Restriction> it = list.iterator();
            while (true) {
                DBRestriction dBRestriction = null;
                if (!it.hasNext()) {
                    break;
                }
                Restriction next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBRestriction = (DBRestriction) linkedHashMap.get(next2);
                }
                if (dBRestriction == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBRestriction dBRestriction2 = (DBRestriction) it2.next();
                        if (dBRestriction2.getIdentifier().equals(next2.identifier)) {
                            dBRestriction = dBRestriction2;
                            break;
                        }
                    }
                }
                if (dBRestriction == null) {
                    dBRestriction = new DBRestriction();
                    arrayList5.add(dBRestriction);
                }
                dBRestriction.setIdentifier(next2.identifier);
                dBRestriction.setAlertTitle(next2.alertTitle);
                dBRestriction.setAlertMessage(next2.alertMessage);
                dBRestriction.setAlternateUrl(next2.alternateUrl);
                linkedHashMap.put(next2, dBRestriction);
            }
            if (arrayList5.size() > 0) {
                daoSession2.getDBRestrictionDao().insertInTx(arrayList5);
            }
            ArrayList arrayList8 = new ArrayList(linkedHashMap.size());
            for (DBRestriction id2 : linkedHashMap.values()) {
                arrayList8.add(id2.getId());
            }
            List<DBPlatform> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.RestrictionId, arrayList8);
            for (DBPlatform restrictionId : allWithPropertyInData2) {
                restrictionId.setRestrictionId((Long) null);
            }
            ArrayList arrayList9 = new ArrayList();
            List<DBFilter> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.RestrictionId, arrayList8);
            for (DBFilter restrictionId2 : allWithPropertyInData3) {
                restrictionId2.setRestrictionId((Long) null);
            }
            ArrayList arrayList10 = new ArrayList();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Restriction restriction = (Restriction) entry.getKey();
                DBRestriction dBRestriction3 = (DBRestriction) entry.getValue();
                if (restriction.platforms != null && !restriction.platforms.isEmpty()) {
                    for (Platform next3 : restriction.platforms) {
                        Iterator it3 = arrayList6.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                list2 = arrayList6;
                                break;
                            }
                            DBPlatform dBPlatform = (DBPlatform) it3.next();
                            list2 = arrayList6;
                            if (dBPlatform.getIdentifier().equals(next3.identifier)) {
                                dBPlatform.setRestrictionId(dBRestriction3.getId());
                                arrayList9.add(dBPlatform);
                                break;
                            }
                            DaoSession daoSession4 = daoSession2;
                            arrayList6 = list2;
                        }
                        DaoSession daoSession5 = daoSession2;
                        arrayList6 = list2;
                    }
                }
                List list3 = arrayList6;
                dBRestriction3.resetPlatforms();
                if (restriction.filters != null && !restriction.filters.isEmpty()) {
                    for (Filter next4 : restriction.filters) {
                        Iterator it4 = arrayList7.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                break;
                            }
                            DBFilter dBFilter = (DBFilter) it4.next();
                            if (dBFilter.getIdentifier().equals(next4.identifier)) {
                                dBFilter.setRestrictionId(dBRestriction3.getId());
                                arrayList10.add(dBFilter);
                                break;
                            }
                        }
                    }
                }
                dBRestriction3.resetFilters();
                DaoSession daoSession6 = daoSession2;
                arrayList6 = list3;
            }
            ArrayList arrayList11 = new ArrayList(allWithPropertyInData2.size());
            for (DBPlatform dBPlatform2 : allWithPropertyInData2) {
                if (dBPlatform2.getRestrictionId() == null) {
                    arrayList11.add(dBPlatform2);
                }
            }
            ArrayList arrayList12 = new ArrayList(allWithPropertyInData3.size());
            for (DBFilter dBFilter2 : allWithPropertyInData3) {
                if (dBFilter2.getRestrictionId() == null) {
                    arrayList12.add(dBFilter2);
                }
            }
            if (!arrayList11.isEmpty()) {
                daoSession2.getDBPlatformDao().deleteInTx(arrayList11);
            }
            if (!arrayList12.isEmpty()) {
                daoSession2.getDBFilterDao().deleteInTx(arrayList12);
            }
            if (!arrayList9.isEmpty()) {
                daoSession2.getDBPlatformDao().updateInTx(arrayList9);
            }
            if (!arrayList10.isEmpty()) {
                daoSession2.getDBFilterDao().updateInTx(arrayList10);
            }
            ArrayList arrayList13 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBRestrictionDao().updateInTx(arrayList13);
            return arrayList13;
        }
    }

    public DBContentItem getContentItem() {
        return (DBContentItem) this.daoSession.getDBContentItemDao().queryBuilder().where(DBContentItemDao.Properties.Id.eq(getContentItemId()), new WhereCondition[0]).unique();
    }
}
