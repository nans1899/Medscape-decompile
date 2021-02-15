package com.wbmd.qxcalculator.model.db.v9;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBPromotion {
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
}
