package com.wbmd.qxcalculator.model.db.v9;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBFeaturedContentAd {
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
}
