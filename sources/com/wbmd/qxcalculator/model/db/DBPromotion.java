package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.common.Filter;
import com.wbmd.qxcalculator.model.contentItems.common.Platform;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAdDao;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBPlatformDao;
import com.wbmd.qxcalculator.model.db.DBPromotionDao;
import com.wbmd.qxcalculator.model.parsedObjects.Promotion;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBPromotion {
    public static final String TAG = DBPromotion.class.getSimpleName();
    private Long campaignAdId;
    private Long contentItemId;
    private transient DaoSession daoSession;
    private List<DBFilter> filters;
    private String footer;
    private Long id;
    private String identifier;
    private transient DBPromotionDao myDao;
    private List<DBPlatform> platforms;
    private String title;

    public DBPromotion() {
    }

    public DBPromotion(Long l) {
        this.id = l;
    }

    public DBPromotion(Long l, String str, String str2, String str3, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.footer = str3;
        this.campaignAdId = l2;
        this.contentItemId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBPromotionDao() : null;
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

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public Long getCampaignAdId() {
        return this.campaignAdId;
    }

    public void setCampaignAdId(Long l) {
        this.campaignAdId = l;
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
                List<DBPlatform> _queryDBPromotion_Platforms = daoSession2.getDBPlatformDao()._queryDBPromotion_Platforms(this.id);
                synchronized (this) {
                    if (this.platforms == null) {
                        this.platforms = _queryDBPromotion_Platforms;
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
                List<DBFilter> _queryDBPromotion_Filters = daoSession2.getDBFilterDao()._queryDBPromotion_Filters(this.id);
                synchronized (this) {
                    if (this.filters == null) {
                        this.filters = _queryDBPromotion_Filters;
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
        DBPromotionDao dBPromotionDao = this.myDao;
        if (dBPromotionDao != null) {
            dBPromotionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBPromotionDao dBPromotionDao = this.myDao;
        if (dBPromotionDao != null) {
            dBPromotionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBPromotionDao dBPromotionDao = this.myDao;
        if (dBPromotionDao != null) {
            dBPromotionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void preloadFilterRelations(DaoSession daoSession2, List<DBPromotion> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.PromotionId, list2);
        for (DBPromotion next : list) {
            next.filters = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBFilter dBFilter = (DBFilter) it.next();
                if (dBFilter.getPromotionId().equals(next.getId())) {
                    next.filters.add(dBFilter);
                    it.remove();
                }
            }
        }
        DBFilter.preloadRelations(daoSession2, allWithPropertyInData);
    }

    public static void preloadPlatformRelations(DaoSession daoSession2, List<DBPromotion> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.PromotionId, list2);
        for (DBPromotion next : list) {
            next.platforms = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBPlatform dBPlatform = (DBPlatform) it.next();
                if (dBPlatform.getPromotionId().equals(next.getId())) {
                    next.platforms.add(dBPlatform);
                    it.remove();
                }
            }
        }
    }

    public static void deleteUnusedPromotions(DaoSession daoSession2) {
        List list = daoSession2.getDBFeaturedContentAdDao().queryBuilder().where(DBFeaturedContentAdDao.Properties.PromotionId.isNotNull(), new WhereCondition[0]).list();
        List<DBPromotion> list2 = daoSession2.getDBPromotionDao().queryBuilder().where(DBPromotionDao.Properties.ContentItemId.isNull(), new WhereCondition[0]).list();
        ArrayList arrayList = new ArrayList();
        for (DBPromotion dBPromotion : list2) {
            if (!list.contains(dBPromotion)) {
                arrayList.add(dBPromotion);
            }
        }
        String str = TAG;
        Log.d(str, "Purging DBPromotion: " + arrayList.size());
        deletePromotions(daoSession2, arrayList);
    }

    public static void deletePromotions(DaoSession daoSession2, List<DBPromotion> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (DBPromotion next : list) {
                arrayList2.add(next.getId());
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBContentItem resetPromotions : allWithPropertyInData) {
                        resetPromotions.resetPromotions();
                    }
                }
            }
            List<DBFeaturedContentAd> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFeaturedContentAdDao(), DBFeaturedContentAdDao.Properties.PromotionId, arrayList2);
            if (allWithPropertyInData2 != null) {
                for (DBFeaturedContentAd promotion : allWithPropertyInData2) {
                    promotion.setPromotion((DBPromotion) null);
                }
            }
            List<DBPlatform> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.PromotionId, arrayList2);
            if (!allWithPropertyInData3.isEmpty()) {
                for (DBPlatform promotionId : allWithPropertyInData3) {
                    promotionId.setPromotionId((Long) null);
                }
                DBPlatform.deletePlatforms(daoSession2, allWithPropertyInData3);
            }
            List<DBFilter> allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.PromotionId, arrayList2);
            if (!allWithPropertyInData4.isEmpty()) {
                for (DBFilter promotionId2 : allWithPropertyInData4) {
                    promotionId2.setPromotionId((Long) null);
                }
                DBFilter.deleteFilters(daoSession2, allWithPropertyInData4);
            }
            daoSession2.getDBPromotionDao().deleteInTx(list);
        }
    }

    public static synchronized List<DBPromotion> insertAndRetrieveDbEntities(DaoSession daoSession2, List<Promotion> list) {
        List list2;
        Iterator it;
        DaoSession daoSession3 = daoSession2;
        synchronized (DBPromotion.class) {
            if (daoSession3 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (Promotion next : list) {
                arrayList2.add(next.identifier);
                if (next.platforms != null) {
                    arrayList3.addAll(next.platforms);
                }
                if (next.filters != null) {
                    arrayList4.addAll(next.filters);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPromotionDao(), DBPromotionDao.Properties.Identifier, arrayList2);
            List arrayList5 = new ArrayList();
            if (!arrayList3.isEmpty()) {
                arrayList5 = DBPlatform.insertAndRetrieveDbEntities(daoSession3, arrayList3);
            }
            List arrayList6 = new ArrayList();
            if (!arrayList4.isEmpty()) {
                arrayList6 = DBFilter.insertAndRetrieveDbEntities(daoSession3, arrayList4);
            }
            ArrayList arrayList7 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterator<Promotion> it2 = list.iterator();
            while (true) {
                DBPromotion dBPromotion = null;
                if (!it2.hasNext()) {
                    break;
                }
                Promotion next2 = it2.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBPromotion = (DBPromotion) linkedHashMap.get(next2);
                }
                if (dBPromotion == null) {
                    Iterator it3 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            break;
                        }
                        DBPromotion dBPromotion2 = (DBPromotion) it3.next();
                        if (dBPromotion2.getIdentifier().equals(next2.identifier)) {
                            dBPromotion = dBPromotion2;
                            break;
                        }
                    }
                }
                if (dBPromotion == null) {
                    dBPromotion = new DBPromotion();
                    arrayList7.add(dBPromotion);
                }
                dBPromotion.setIdentifier(next2.identifier);
                dBPromotion.setTitle(next2.title);
                dBPromotion.setFooter(next2.footer);
                dBPromotion.setCampaignAdId(next2.campaignAdId);
                linkedHashMap.put(next2, dBPromotion);
            }
            List<DBPlatform> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.PromotionId, arrayList2);
            for (DBPlatform promotionId : allWithPropertyInData2) {
                promotionId.setPromotionId((Long) null);
            }
            ArrayList arrayList8 = new ArrayList();
            List<DBFilter> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.PromotionId, arrayList2);
            for (DBFilter promotionId2 : allWithPropertyInData3) {
                promotionId2.setPromotionId((Long) null);
            }
            ArrayList arrayList9 = new ArrayList();
            Iterator it4 = linkedHashMap.entrySet().iterator();
            while (it4.hasNext()) {
                Map.Entry entry = (Map.Entry) it4.next();
                Promotion promotion = (Promotion) entry.getKey();
                DBPromotion dBPromotion3 = (DBPromotion) entry.getValue();
                dBPromotion3.platforms = new ArrayList();
                if (promotion.platforms != null && !promotion.platforms.isEmpty()) {
                    for (Platform next3 : promotion.platforms) {
                        Iterator it5 = arrayList5.iterator();
                        while (true) {
                            if (!it5.hasNext()) {
                                list2 = arrayList5;
                                it = it4;
                                break;
                            }
                            DBPlatform dBPlatform = (DBPlatform) it5.next();
                            list2 = arrayList5;
                            it = it4;
                            if (dBPlatform.getIdentifier().equals(next3.identifier)) {
                                dBPlatform.setPromotionId(dBPromotion3.getId());
                                arrayList8.add(dBPlatform);
                                dBPromotion3.platforms.add(dBPlatform);
                                break;
                            }
                            DaoSession daoSession4 = daoSession2;
                            it4 = it;
                            arrayList5 = list2;
                        }
                        DaoSession daoSession5 = daoSession2;
                        it4 = it;
                        arrayList5 = list2;
                    }
                }
                List list3 = arrayList5;
                Iterator it6 = it4;
                dBPromotion3.filters = new ArrayList();
                if (promotion.filters != null && !promotion.filters.isEmpty()) {
                    for (Filter next4 : promotion.filters) {
                        Iterator it7 = arrayList6.iterator();
                        while (true) {
                            if (!it7.hasNext()) {
                                break;
                            }
                            DBFilter dBFilter = (DBFilter) it7.next();
                            if (dBFilter.getIdentifier().equals(next4.identifier)) {
                                dBFilter.setPromotionId(dBPromotion3.getId());
                                arrayList9.add(dBFilter);
                                dBPromotion3.filters.add(dBFilter);
                                break;
                            }
                        }
                    }
                }
                DaoSession daoSession6 = daoSession2;
                it4 = it6;
                arrayList5 = list3;
            }
            ArrayList arrayList10 = new ArrayList(allWithPropertyInData2.size());
            for (DBPlatform dBPlatform2 : allWithPropertyInData2) {
                if (dBPlatform2.getPromotionId() == null) {
                    arrayList10.add(dBPlatform2);
                }
            }
            ArrayList arrayList11 = new ArrayList(allWithPropertyInData3.size());
            for (DBFilter dBFilter2 : allWithPropertyInData3) {
                if (dBFilter2.getPromotionId() == null) {
                    arrayList11.add(dBFilter2);
                }
            }
            if (arrayList10.size() > 0) {
                daoSession2.getDBPlatformDao().deleteInTx(arrayList10);
            }
            if (arrayList11.size() > 0) {
                daoSession2.getDBFilterDao().deleteInTx(arrayList11);
            }
            if (arrayList8.size() > 0) {
                daoSession2.getDBPlatformDao().updateInTx(arrayList8);
            }
            if (arrayList9.size() > 0) {
                daoSession2.getDBFilterDao().updateInTx(arrayList9);
            }
            if (arrayList7.size() > 0) {
                daoSession2.getDBPromotionDao().insertInTx(arrayList7);
            }
            daoSession2.getDBPromotionDao().updateInTx(linkedHashMap.values());
            ArrayList arrayList12 = new ArrayList(linkedHashMap.values());
            return arrayList12;
        }
    }
}
