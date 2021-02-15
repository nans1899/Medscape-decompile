package com.wbmd.qxcalculator.model.db.v4;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBMoreInfoSection {
    private Long contentItemId;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private List<DBMoreInfoSectionItem> moreInfoSectionItems;
    private transient DBMoreInfoSectionDao myDao;
    private Integer position;
    private String subTitle;
    private String title;

    public DBMoreInfoSection() {
    }

    public DBMoreInfoSection(Long l) {
        this.id = l;
    }

    public DBMoreInfoSection(Long l, String str, String str2, String str3, Integer num, Long l2) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.subTitle = str3;
        this.position = num;
        this.contentItemId = l2;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBMoreInfoSectionDao() : null;
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

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public List<DBMoreInfoSectionItem> getMoreInfoSectionItems() {
        if (this.moreInfoSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBMoreInfoSectionItem> _queryDBMoreInfoSection_MoreInfoSectionItems = daoSession2.getDBMoreInfoSectionItemDao()._queryDBMoreInfoSection_MoreInfoSectionItems(this.id);
                synchronized (this) {
                    if (this.moreInfoSectionItems == null) {
                        this.moreInfoSectionItems = _queryDBMoreInfoSection_MoreInfoSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.moreInfoSectionItems;
    }

    public synchronized void resetMoreInfoSectionItems() {
        this.moreInfoSectionItems = null;
    }

    public void delete() {
        DBMoreInfoSectionDao dBMoreInfoSectionDao = this.myDao;
        if (dBMoreInfoSectionDao != null) {
            dBMoreInfoSectionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBMoreInfoSectionDao dBMoreInfoSectionDao = this.myDao;
        if (dBMoreInfoSectionDao != null) {
            dBMoreInfoSectionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBMoreInfoSectionDao dBMoreInfoSectionDao = this.myDao;
        if (dBMoreInfoSectionDao != null) {
            dBMoreInfoSectionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
