package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd;
import com.wbmd.qxcalculator.model.contentItems.common.Filter;
import com.wbmd.qxcalculator.model.contentItems.common.Platform;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAdDao;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBPlatformDao;
import com.wbmd.qxcalculator.model.db.DBPromotionDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBFeaturedContentAd {
    public static final String TAG = DBFeaturedContentAd.class.getSimpleName();
    private String backgroundColor;
    private Long contentItemId;
    private transient DaoSession daoSession;
    private String description;
    private String disclaimer;
    private Double displayFrequency;
    private List<DBFilter> filters;
    private String footer;
    private Long id;
    private String identifier;
    private String imageSource;
    private transient DBFeaturedContentAdDao myDao;
    private String name;
    private List<DBPlatform> platforms;
    private DBPromotion promotion;
    private Long promotionId;
    public DBPromotion promotionToUse;
    private Long promotion__resolvedKey;

    public DBFeaturedContentAd() {
    }

    public DBFeaturedContentAd(Long l) {
        this.id = l;
    }

    public DBFeaturedContentAd(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, Double d, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.name = str2;
        this.description = str3;
        this.disclaimer = str4;
        this.footer = str5;
        this.imageSource = str6;
        this.backgroundColor = str7;
        this.displayFrequency = d;
        this.contentItemId = l2;
        this.promotionId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBFeaturedContentAdDao() : null;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getDisclaimer() {
        return this.disclaimer;
    }

    public void setDisclaimer(String str) {
        this.disclaimer = str;
    }

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public String getImageSource() {
        return this.imageSource;
    }

    public void setImageSource(String str) {
        this.imageSource = str;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public Double getDisplayFrequency() {
        return this.displayFrequency;
    }

    public void setDisplayFrequency(Double d) {
        this.displayFrequency = d;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public Long getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(Long l) {
        this.promotionId = l;
    }

    public DBPromotion getPromotion() {
        Long l = this.promotionId;
        Long l2 = this.promotion__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBPromotion dBPromotion = (DBPromotion) daoSession2.getDBPromotionDao().load(l);
                synchronized (this) {
                    this.promotion = dBPromotion;
                    this.promotion__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.promotion;
    }

    public void setPromotion(DBPromotion dBPromotion) {
        Long l;
        synchronized (this) {
            this.promotion = dBPromotion;
            if (dBPromotion == null) {
                l = null;
            } else {
                l = dBPromotion.getId();
            }
            this.promotionId = l;
            this.promotion__resolvedKey = l;
        }
    }

    public List<DBPlatform> getPlatforms() {
        if (this.platforms == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBPlatform> _queryDBFeaturedContentAd_Platforms = daoSession2.getDBPlatformDao()._queryDBFeaturedContentAd_Platforms(this.id);
                synchronized (this) {
                    if (this.platforms == null) {
                        this.platforms = _queryDBFeaturedContentAd_Platforms;
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
                List<DBFilter> _queryDBFeaturedContentAd_Filters = daoSession2.getDBFilterDao()._queryDBFeaturedContentAd_Filters(this.id);
                synchronized (this) {
                    if (this.filters == null) {
                        this.filters = _queryDBFeaturedContentAd_Filters;
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
        DBFeaturedContentAdDao dBFeaturedContentAdDao = this.myDao;
        if (dBFeaturedContentAdDao != null) {
            dBFeaturedContentAdDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBFeaturedContentAdDao dBFeaturedContentAdDao = this.myDao;
        if (dBFeaturedContentAdDao != null) {
            dBFeaturedContentAdDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBFeaturedContentAdDao dBFeaturedContentAdDao = this.myDao;
        if (dBFeaturedContentAdDao != null) {
            dBFeaturedContentAdDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void preloadFilterRelations(DaoSession daoSession2, List<DBFeaturedContentAd> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.FeaturedContentAdId, list2);
        for (DBFeaturedContentAd next : list) {
            next.filters = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBFilter dBFilter = (DBFilter) it.next();
                if (dBFilter.getFeaturedContentAdId().equals(next.getId())) {
                    next.filters.add(dBFilter);
                    it.remove();
                }
            }
        }
        DBFilter.preloadRelations(daoSession2, allWithPropertyInData);
    }

    public static void preloadPlatformRelations(DaoSession daoSession2, List<DBFeaturedContentAd> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.FeaturedContentAdId, list2);
        for (DBFeaturedContentAd next : list) {
            next.platforms = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBPlatform dBPlatform = (DBPlatform) it.next();
                if (dBPlatform.getFeaturedContentAdId().equals(next.getId())) {
                    next.platforms.add(dBPlatform);
                    it.remove();
                }
            }
        }
    }

    public static void deleteUnusedFeaturedContentAds(DaoSession daoSession2) {
        List list = daoSession2.getDBFeaturedContentAdDao().queryBuilder().where(DBFeaturedContentAdDao.Properties.ContentItemId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBFeaturedContentAd: " + list.size());
        deleteFeaturedContentAds(daoSession2, list);
    }

    public static void deleteFeaturedContentAds(DaoSession daoSession2, List<DBFeaturedContentAd> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            ArrayList arrayList3 = new ArrayList();
            for (DBFeaturedContentAd next : list) {
                arrayList2.add(next.getId());
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                }
                if (next.getPromotionId() != null) {
                    arrayList3.add(next.getPromotionId());
                }
            }
            List<DBFilter> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.FeaturedContentAdId, arrayList2);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBFilter featuredContentAdId : allWithPropertyInData) {
                    featuredContentAdId.setFeaturedContentAdId((Long) null);
                }
                DBFilter.deleteFilters(daoSession2, allWithPropertyInData);
            }
            List<DBPlatform> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.FeaturedContentAdId, arrayList2);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBPlatform featuredContentAdId2 : allWithPropertyInData2) {
                    featuredContentAdId2.setFeaturedContentAdId((Long) null);
                }
                DBPlatform.deletePlatforms(daoSession2, allWithPropertyInData2);
            }
            if (!arrayList3.isEmpty()) {
                DBPromotion.deletePromotions(daoSession2, DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPromotionDao(), DBPromotionDao.Properties.Id, arrayList3));
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData3.isEmpty()) {
                    for (DBContentItem resetFeaturedContentAds : allWithPropertyInData3) {
                        resetFeaturedContentAds.resetFeaturedContentAds();
                    }
                }
            }
            daoSession2.getDBFeaturedContentAdDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBFeaturedContentAd insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBFeaturedContentAd> r0 = com.wbmd.qxcalculator.model.db.DBFeaturedContentAd.class
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
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd r1 = (com.wbmd.qxcalculator.model.db.DBFeaturedContentAd) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBFeaturedContentAd.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd):com.wbmd.qxcalculator.model.db.DBFeaturedContentAd");
    }

    public static synchronized List<DBFeaturedContentAd> insertAndRetrieveDbEntities(DaoSession daoSession2, List<FeaturedContentAd> list) {
        Object obj;
        Iterator<Filter> it;
        List list2;
        DaoSession daoSession3 = daoSession2;
        synchronized (DBFeaturedContentAd.class) {
            if (daoSession3 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            for (FeaturedContentAd next : list) {
                arrayList2.add(next.identifier);
                if (next.platforms != null) {
                    arrayList3.addAll(next.platforms);
                }
                if (next.filters != null) {
                    arrayList4.addAll(next.filters);
                }
                if (next.promotion != null) {
                    arrayList5.add(next.promotion);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFeaturedContentAdDao(), DBFeaturedContentAdDao.Properties.Identifier, arrayList2);
            ArrayList arrayList6 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList7 = new ArrayList();
            if (!arrayList3.isEmpty()) {
                arrayList7 = DBPlatform.insertAndRetrieveDbEntities(daoSession3, arrayList3);
            }
            List arrayList8 = new ArrayList();
            if (!arrayList4.isEmpty()) {
                arrayList8 = DBFilter.insertAndRetrieveDbEntities(daoSession3, arrayList4);
            }
            List arrayList9 = new ArrayList();
            if (!arrayList5.isEmpty()) {
                arrayList9 = DBPromotion.insertAndRetrieveDbEntities(daoSession3, arrayList5);
            }
            Iterator<FeaturedContentAd> it2 = list.iterator();
            while (true) {
                DBFeaturedContentAd dBFeaturedContentAd = null;
                if (!it2.hasNext()) {
                    break;
                }
                FeaturedContentAd next2 = it2.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBFeaturedContentAd = (DBFeaturedContentAd) linkedHashMap.get(next2);
                }
                if (dBFeaturedContentAd == null) {
                    Iterator it3 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        }
                        DBFeaturedContentAd dBFeaturedContentAd2 = (DBFeaturedContentAd) it3.next();
                        if (dBFeaturedContentAd2.getIdentifier().equals(next2.identifier)) {
                            dBFeaturedContentAd = dBFeaturedContentAd2;
                            break;
                        }
                    }
                }
                if (dBFeaturedContentAd == null) {
                    dBFeaturedContentAd = new DBFeaturedContentAd();
                    arrayList6.add(dBFeaturedContentAd);
                }
                dBFeaturedContentAd.setIdentifier(next2.identifier);
                dBFeaturedContentAd.setName(next2.name);
                dBFeaturedContentAd.setDescription(next2.description);
                dBFeaturedContentAd.setDisclaimer(next2.disclaimer);
                dBFeaturedContentAd.setBackgroundColor(next2.backgroundColor);
                dBFeaturedContentAd.setFooter(next2.footer);
                dBFeaturedContentAd.setImageSource(next2.imageSource);
                dBFeaturedContentAd.setDisplayFrequency(next2.displayFrequency);
                linkedHashMap.put(next2, dBFeaturedContentAd);
            }
            if (arrayList6.size() > 0) {
                daoSession2.getDBFeaturedContentAdDao().insertInTx(arrayList6);
            }
            ArrayList arrayList10 = new ArrayList(linkedHashMap.size());
            for (DBFeaturedContentAd id2 : linkedHashMap.values()) {
                arrayList10.add(id2.getId());
            }
            List<DBPlatform> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.FeaturedContentAdId, arrayList10);
            for (DBPlatform featuredContentAdId : allWithPropertyInData2) {
                featuredContentAdId.setFeaturedContentAdId((Long) null);
            }
            ArrayList arrayList11 = new ArrayList();
            List<DBFilter> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.FeaturedContentAdId, arrayList10);
            for (DBFilter featuredContentAdId2 : allWithPropertyInData3) {
                featuredContentAdId2.setFeaturedContentAdId((Long) null);
            }
            ArrayList arrayList12 = new ArrayList();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                FeaturedContentAd featuredContentAd = (FeaturedContentAd) entry.getKey();
                DBFeaturedContentAd dBFeaturedContentAd3 = (DBFeaturedContentAd) entry.getValue();
                if (featuredContentAd.platforms != null && !featuredContentAd.platforms.isEmpty()) {
                    for (Platform next3 : featuredContentAd.platforms) {
                        Iterator it4 = arrayList7.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                list2 = arrayList7;
                                break;
                            }
                            DBPlatform dBPlatform = (DBPlatform) it4.next();
                            list2 = arrayList7;
                            if (dBPlatform.getIdentifier().equals(next3.identifier)) {
                                dBPlatform.setFeaturedContentAdId(dBFeaturedContentAd3.getId());
                                arrayList11.add(dBPlatform);
                                break;
                            }
                            DaoSession daoSession4 = daoSession2;
                            arrayList7 = list2;
                        }
                        DaoSession daoSession5 = daoSession2;
                        arrayList7 = list2;
                    }
                }
                List list3 = arrayList7;
                dBFeaturedContentAd3.resetPlatforms();
                if (featuredContentAd.filters != null && !featuredContentAd.filters.isEmpty()) {
                    Iterator<Filter> it5 = featuredContentAd.filters.iterator();
                    while (it5.hasNext()) {
                        Filter next4 = it5.next();
                        Iterator it6 = arrayList8.iterator();
                        while (true) {
                            if (!it6.hasNext()) {
                                it = it5;
                                break;
                            }
                            DBFilter dBFilter = (DBFilter) it6.next();
                            it = it5;
                            if (dBFilter.getIdentifier().equals(next4.identifier)) {
                                dBFilter.setFeaturedContentAdId(dBFeaturedContentAd3.getId());
                                arrayList12.add(dBFilter);
                                break;
                            }
                            it5 = it;
                        }
                        it5 = it;
                    }
                }
                dBFeaturedContentAd3.resetFilters();
                if (featuredContentAd.promotion != null) {
                    Iterator it7 = arrayList9.iterator();
                    while (true) {
                        if (!it7.hasNext()) {
                            break;
                        }
                        DBPromotion dBPromotion = (DBPromotion) it7.next();
                        if (dBPromotion.getIdentifier().equals(featuredContentAd.promotion.identifier)) {
                            dBFeaturedContentAd3.setPromotion(dBPromotion);
                            break;
                        }
                    }
                    obj = null;
                } else {
                    obj = null;
                    dBFeaturedContentAd3.setPromotion((DBPromotion) null);
                }
                Object obj2 = obj;
                arrayList7 = list3;
                DaoSession daoSession6 = daoSession2;
            }
            ArrayList arrayList13 = new ArrayList(allWithPropertyInData2.size());
            for (DBPlatform dBPlatform2 : allWithPropertyInData2) {
                if (dBPlatform2.getFeaturedContentAdId() == null) {
                    arrayList13.add(dBPlatform2);
                }
            }
            ArrayList arrayList14 = new ArrayList(allWithPropertyInData3.size());
            for (DBFilter dBFilter2 : allWithPropertyInData3) {
                if (dBFilter2.getFeaturedContentAdId() == null) {
                    arrayList14.add(dBFilter2);
                }
            }
            if (!arrayList13.isEmpty()) {
                daoSession2.getDBPlatformDao().deleteInTx(arrayList13);
            }
            if (!arrayList14.isEmpty()) {
                daoSession2.getDBFilterDao().deleteInTx(arrayList14);
            }
            if (!arrayList11.isEmpty()) {
                daoSession2.getDBPlatformDao().updateInTx(arrayList11);
            }
            if (!arrayList12.isEmpty()) {
                daoSession2.getDBFilterDao().updateInTx(arrayList12);
            }
            ArrayList arrayList15 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBFeaturedContentAdDao().updateInTx(arrayList15);
            return arrayList15;
        }
    }

    public DBContentItem getContentItem() {
        Long contentItemId2 = getContentItemId();
        if (contentItemId2 == null) {
            return null;
        }
        return (DBContentItem) this.daoSession.getDBContentItemDao().queryBuilder().where(DBContentItemDao.Properties.Id.eq(contentItemId2), new WhereCondition[0]).unique();
    }
}
