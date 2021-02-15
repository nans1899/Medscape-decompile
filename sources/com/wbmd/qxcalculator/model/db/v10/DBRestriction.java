package com.wbmd.qxcalculator.model.db.v10;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBRestriction {
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
}
