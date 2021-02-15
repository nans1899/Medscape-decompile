package com.wbmd.qxcalculator.model.db.v9;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBFilter {
    private Long contentItemId;
    private transient DaoSession daoSession;
    private Long featuredContentAdId;
    private Long id;
    private String identifier;
    private List<DBLocation> locations;
    private transient DBFilterDao myDao;
    private List<DBProfession> professions;
    private Long promotionId;
    private Long restrictionId;
    private List<DBSpecialty> specialties;
    private String type;

    public DBFilter() {
    }

    public DBFilter(Long l) {
        this.id = l;
    }

    public DBFilter(Long l, String str, String str2, Long l2, Long l3, Long l4, Long l5) {
        this.id = l;
        this.identifier = str;
        this.type = str2;
        this.contentItemId = l2;
        this.featuredContentAdId = l3;
        this.restrictionId = l4;
        this.promotionId = l5;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBFilterDao() : null;
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
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

    public List<DBSpecialty> getSpecialties() {
        if (this.specialties == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBSpecialty> _queryDBFilter_Specialties = daoSession2.getDBSpecialtyDao()._queryDBFilter_Specialties(this.id);
                synchronized (this) {
                    if (this.specialties == null) {
                        this.specialties = _queryDBFilter_Specialties;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.specialties;
    }

    public synchronized void resetSpecialties() {
        this.specialties = null;
    }

    public List<DBProfession> getProfessions() {
        if (this.professions == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBProfession> _queryDBFilter_Professions = daoSession2.getDBProfessionDao()._queryDBFilter_Professions(this.id);
                synchronized (this) {
                    if (this.professions == null) {
                        this.professions = _queryDBFilter_Professions;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.professions;
    }

    public synchronized void resetProfessions() {
        this.professions = null;
    }

    public List<DBLocation> getLocations() {
        if (this.locations == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBLocation> _queryDBFilter_Locations = daoSession2.getDBLocationDao()._queryDBFilter_Locations(this.id);
                synchronized (this) {
                    if (this.locations == null) {
                        this.locations = _queryDBFilter_Locations;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.locations;
    }

    public synchronized void resetLocations() {
        this.locations = null;
    }

    public void delete() {
        DBFilterDao dBFilterDao = this.myDao;
        if (dBFilterDao != null) {
            dBFilterDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBFilterDao dBFilterDao = this.myDao;
        if (dBFilterDao != null) {
            dBFilterDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBFilterDao dBFilterDao = this.myDao;
        if (dBFilterDao != null) {
            dBFilterDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
