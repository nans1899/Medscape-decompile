package com.wbmd.qxcalculator.model.db.v7;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBReferenceBookSection {
    private transient DaoSession daoSession;
    private String footer;
    private Long id;
    private String identifier;
    private transient DBReferenceBookSectionDao myDao;
    private Long position;
    private Long referenceBookId;
    private List<DBReferenceBookSectionItem> referenceBookSectionItems;
    private String subTitle;
    private String title;

    public DBReferenceBookSection() {
    }

    public DBReferenceBookSection(Long l) {
        this.id = l;
    }

    public DBReferenceBookSection(Long l, String str, String str2, String str3, String str4, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.subTitle = str3;
        this.footer = str4;
        this.position = l2;
        this.referenceBookId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBReferenceBookSectionDao() : null;
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

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public Long getPosition() {
        return this.position;
    }

    public void setPosition(Long l) {
        this.position = l;
    }

    public Long getReferenceBookId() {
        return this.referenceBookId;
    }

    public void setReferenceBookId(Long l) {
        this.referenceBookId = l;
    }

    public List<DBReferenceBookSectionItem> getReferenceBookSectionItems() {
        if (this.referenceBookSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBReferenceBookSectionItem> _queryDBReferenceBookSection_ReferenceBookSectionItems = daoSession2.getDBReferenceBookSectionItemDao()._queryDBReferenceBookSection_ReferenceBookSectionItems(this.id);
                synchronized (this) {
                    if (this.referenceBookSectionItems == null) {
                        this.referenceBookSectionItems = _queryDBReferenceBookSection_ReferenceBookSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.referenceBookSectionItems;
    }

    public synchronized void resetReferenceBookSectionItems() {
        this.referenceBookSectionItems = null;
    }

    public void delete() {
        DBReferenceBookSectionDao dBReferenceBookSectionDao = this.myDao;
        if (dBReferenceBookSectionDao != null) {
            dBReferenceBookSectionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBReferenceBookSectionDao dBReferenceBookSectionDao = this.myDao;
        if (dBReferenceBookSectionDao != null) {
            dBReferenceBookSectionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBReferenceBookSectionDao dBReferenceBookSectionDao = this.myDao;
        if (dBReferenceBookSectionDao != null) {
            dBReferenceBookSectionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
