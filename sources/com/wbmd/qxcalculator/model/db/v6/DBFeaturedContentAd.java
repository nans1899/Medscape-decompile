package com.wbmd.qxcalculator.model.db.v6;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBFeaturedContentAd {
    private String backgroundColor;
    private Long contentItemId;
    private transient DaoSession daoSession;
    private String description;
    private Double displayFrequency;
    private List<DBFilter> filters;
    private String footer;
    private Long id;
    private String identifier;
    private String imageSource;
    private transient DBFeaturedContentAdDao myDao;
    private String name;
    private List<DBPlatform> platforms;

    public DBFeaturedContentAd() {
    }

    public DBFeaturedContentAd(Long l) {
        this.id = l;
    }

    public DBFeaturedContentAd(Long l, String str, String str2, String str3, String str4, String str5, String str6, Double d, Long l2) {
        this.id = l;
        this.identifier = str;
        this.name = str2;
        this.description = str3;
        this.footer = str4;
        this.imageSource = str5;
        this.backgroundColor = str6;
        this.displayFrequency = d;
        this.contentItemId = l2;
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
